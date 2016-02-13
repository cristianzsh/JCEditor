package com.cristian;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
* Classe que executa os arquivos do Potigol.
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  1.0
*/

public class TerminalPotigol extends JTabbedPane {
	private Comando cmd;
	private Console consolePotigol;
	private JScrollPane barra;

	public TerminalPotigol() {
		consolePotigol = new Console();
		barra = new JScrollPane(consolePotigol);
		barra.setBorder(null);
		this.addTab("Terminal do Potigol  ", barra);
		this.setIconAt(0, new ImageIcon(getClass().getResource("imagens/console.png")));
	}

	interface ComandoListener {
		public void saidaComando(String s);

		public void comandoConcluido(String cmd, int r);

		public void falhaAoExecutar(Exception ex);
	}

	class Console extends JTextArea implements ComandoListener, Terminal {
		private int entradaUsuario;

		public Console() {
			cmd = new Comando(this);
			this.setBorder(null);

			try {
				InputStream is = TerminalPotigol.class.getResourceAsStream("DejaVuSansMono.ttf");
				Font fonte = Font.createFont(Font.TRUETYPE_FONT, is);
				fonte = fonte.deriveFont(13.0F);
				this.setFont(fonte);
			} catch (Exception ex) {  }

			this.setForeground(Color.WHITE);
			this.setCaretColor(Color.WHITE);
			this.setBackground(Color.BLACK);
			((AbstractDocument) this.getDocument()).setDocumentFilter(new DFilter(this));

			InputMap im = this.getInputMap(WHEN_FOCUSED);
			ActionMap am = this.getActionMap();

			Action acaoAntiga = am.get("insert-break");
			am.put("insert-break", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					if (cmd.emExecucao()) {
						int alcance = Console.this.getCaretPosition() - entradaUsuario;

						try {
							String s = Console.this.getText(entradaUsuario, alcance).trim();
							entradaUsuario += alcance;

							cmd.enviar(s + "\n");
						} catch (Exception ex) {  }

						acaoAntiga.actionPerformed(ev);
					}
				}
			});
		}

		@Override
		public void saidaComando(String s) {
			SwingUtilities.invokeLater(new Anexar(this, s));
		}

		@Override
		public void comandoConcluido(String cmd, int r) {
			try {
				Thread.sleep(500);
				inserir("Operação finalizada.\n");
				atualizarEntrada();
			} catch (Exception ex) {  }
		}

		@Override
		public void falhaAoExecutar(Exception ex) {
			SwingUtilities.invokeLater(new Anexar(this, "Falha ao executar o Potigol!"));
		}

		@Override
		public int getEntradaUsuario() {
			return this.entradaUsuario;
		}

		@Override
		public void inserir(String s) {
			this.append(s);
			atualizarEntrada();
		}

		protected void atualizarEntrada() {
			int pos = this.getCaretPosition();
			this.setCaretPosition(this.getText().length());
			entradaUsuario = pos;
		}
	}

	public void executarComando(File arquivo) {
		if (!cmd.emExecucao()) {
			consolePotigol.requestFocus();
			consolePotigol.inserir("Aguarde...\n");
			cmd.executar("java -jar " + System.getProperty("user.home") + "/ConfigJCE/.potigol/potigol.jar "
				+ arquivo.toString());
		}
	}

	public JScrollPane getBarra() {
		return this.barra;
	}

	interface EntradaUsuario {
		public int getEntradaUsuario();
	}

	interface Terminal extends EntradaUsuario {
		public void inserir(String s);
	}

	class Anexar implements Runnable {
		private Terminal terminal;
		private String s;

		public Anexar(Terminal adt, String st) {
			this.terminal = adt;
			this.s = st;
		}

		@Override
		public void run() {
			terminal.inserir(s);
		}
	}

	class Comando {
		private ComandoListener cmdListener;
		private Processo p;

		public Comando(ComandoListener cl) {
			this.cmdListener = cl;
		}

		public boolean emExecucao() {
			return p != null && p.isAlive();
		}

		public void executar(String cmd) {
			if (!cmd.trim().isEmpty()) {
				List<String> vals = new ArrayList<>();
				if (!cmd.trim().isEmpty()) {
					String partes[] = cmd.trim().split(" ");
					vals.addAll(Arrays.asList(partes));
				}

				p = new Processo(cmdListener, vals);
			}
		}

		public void enviar(String s) throws Exception {
			p.escrever(s);
		}
	}

	class Processo extends Thread {
		private List<String> comandos;
		private ComandoListener cmdListener;
		private Process p;

		public Processo(ComandoListener cl, List<String> cmds) {
			this.comandos = cmds;
			this.cmdListener = cl;
			this.start();
		}

		@Override
		public void run() {
			try {
				ProcessBuilder pb = new ProcessBuilder(comandos);
				pb.redirectErrorStream();
				p = pb.start();
				FluxoLeitor leitor = new FluxoLeitor(cmdListener, p.getInputStream());

				int r = p.waitFor();
				leitor.join();

				StringJoiner sj = new StringJoiner(" ");
				comandos.stream().forEach((cmd) -> {
					sj.add(cmd);
				});

				cmdListener.comandoConcluido(sj.toString(), r);
			} catch (Exception ex) {
				cmdListener.falhaAoExecutar(ex);
			}
		}

		public void escrever(String s) throws Exception {
			if (p != null && p.isAlive()) {
				p.getOutputStream().write(s.getBytes());
				p.getOutputStream().flush();
			}
		}
	}

	class FluxoLeitor extends Thread {
		private InputStream is;
		private ComandoListener cmdListener;

		public FluxoLeitor(ComandoListener l, InputStream i) {
			this.is = i;
			this.cmdListener = l;
			start();
		}

		@Override
		public void run() {
			try {
				int valor = -1;
				while ((valor = is.read()) != -1) {
					cmdListener.saidaComando(Character.toString((char) valor));
				}
			} catch (Exception ex) {  }
		}
	}

	public class DFilter extends DocumentFilter {
		private EntradaUsuario eUsuario;

		public DFilter(EntradaUsuario eu) {
			this.eUsuario = eu;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
			if (offset >= eUsuario.getEntradaUsuario()) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
			if (offset >= eUsuario.getEntradaUsuario()) {
				super.remove(fb, offset, length);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
			if (offset >= eUsuario.getEntradaUsuario()) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}
}