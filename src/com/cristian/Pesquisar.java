package com.cristian;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;

/**
* Classe que realiza a pesquisa no JTextArea atual
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.9
* @since     Segunda atualização
*/

public class Pesquisar extends JToolBar {
	private JTextField fieldPesquisar, fieldSubstituir;
	private JButton[] botoes = new JButton[6];
	private ActionListener[] eventos = {new PesquisarListener(), new SubstituirListener(),
		new SubstituirTodosListener(), new VoltarListener(), new ProximoListener(), new OcultarListener()};
	private String[] imagens = {"pesquisar25", "substituir25", "substituirTodos25", "anterior25", "proximo25", "sair25"};
	private String[] toolTip = {"Pesquisar", "Substituir", "Substituir todos", "Anterior", "Próximo", "Ocultar"};
	private JTextArea areaDeTexto;
	private int posicaoInicial = 0;

	/**
	* O construtor é responsável por iniciar a interface de pesquisa.
	*/
	public Pesquisar(JTextArea adt) {
		construirGUI();
		this.areaDeTexto = adt;
		this.areaDeTexto.requestFocus();
		this.setFloatable(false);
	}

	/**
	* Constrói a GUI e adiciona eventos aos botões.
	*/
	public void construirGUI() {
		fieldPesquisar = new JTextField(15) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setBackground(Color.GRAY);
					g2.setFont(getFont().deriveFont(Font.PLAIN));
					g2.drawString("Pesquisar", 5, 18);
					g2.dispose();
				}
			}};
		fieldSubstituir = new JTextField(15) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setBackground(Color.GRAY);
					g2.setFont(getFont().deriveFont(Font.PLAIN));
					g2.drawString("Substituir", 5, 18);
					g2.dispose();
				}
			}};;

		fieldPesquisar.addActionListener(new PesquisarListener());
		fieldPesquisar.addKeyListener(new OcultarBarraListener());
		fieldSubstituir.addKeyListener(new OcultarBarraListener());
		this.add(fieldPesquisar);
		this.add(fieldSubstituir);

		for (int i = 0; i < botoes.length; i++) {
			botoes[i] = new JButton(new ImageIcon(getClass().getResource("imagens/25x25/" + imagens[i] + ".png")));
			botoes[i].addActionListener(eventos[i]);
			botoes[i].setToolTipText(toolTip[i]);
			EfeitoBtn efBtn = new EfeitoBtn(botoes[i]);
			this.add(botoes[i]);
		}
	}

	/**
	* Realiza a pesquisa no JTextArea, se o botão for pressionado mais de uma vez,
	* a próxima String com o texto digitado em "fieldPesquisar" será selecionada.
	*/
	private void pesquisar() {
		if (fieldPesquisar.getText().equals("")) {
			return;
		}

		String textoPesquisar = fieldPesquisar.getText();
		int pos = areaDeTexto.getText().indexOf(textoPesquisar, posicaoInicial);

		if (pos < 0) {
			posicaoInicial = 0;
			return;
		} else {
			areaDeTexto.select(pos, pos + textoPesquisar.length());
			posicaoInicial = pos + textoPesquisar.length();
		}
	}

	/**
	* Retorna o campo de pesquisa, este receberá focus quando a barra estiver visível.
	*/
	public JTextField getFieldPesquisar() {
		return this.fieldPesquisar;
	}

	/**
	* Evento de pesquisa, apenas chama o próprio método pesquisar.
	*/
	class PesquisarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			pesquisar();
		}
	}

	/**
	* Evento que chama o método pesquisar (que também tem a função de pesquisar a próxima String).
	*/
	class ProximoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			pesquisar();
		}
	}

	/**
	* Seleciona o local que possui a String selecionada anteriormente.
	*/
	class VoltarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			String textoPesquisar = fieldPesquisar.getText();
			int pos = areaDeTexto.getText().lastIndexOf(textoPesquisar,
				areaDeTexto.getCaretPosition() - textoPesquisar.length() - 1);

			if (pos < 0) {
				posicaoInicial = 1;
				return;
			} else {
				areaDeTexto.select(pos, pos + textoPesquisar.length());
				posicaoInicial = pos + textoPesquisar.length();
			}
		}
	}

	/**
	* Substitui a String selecionada.
	*/
	class SubstituirListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (fieldSubstituir.getText().equals("")) {
				return;
			}

			areaDeTexto.replaceSelection(fieldSubstituir.getText());
		}
	}

	/**
	* Substitui todas as Strings que possuem o texto digitado no fieldPesquisar por
	* o texto digitado no fieldSubstituir.
	*/
	class SubstituirTodosListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (fieldSubstituir.getText().equals("")) {
				return;
			}

			String textoPesquisar = fieldPesquisar.getText();
			String textoSubstituir = fieldSubstituir.getText();
			areaDeTexto.setText(areaDeTexto.getText().replaceAll(textoPesquisar, textoSubstituir));
		}
	}

	/**
	* Substitui a String selecionada quando o usuário pressionar enter.
	*/
	class FieldSubstituirListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			pesquisar();
			if (fieldSubstituir.getText().equals("") || fieldPesquisar.getText().equals("")) {
				return;
			}

			areaDeTexto.replaceSelection(fieldSubstituir.getText());
		}
	}

	/**
	* Oculta a área de pesquisa quando Esc for pressionado.
	*/
	class OcultarBarraListener implements KeyListener {
		public void keyPressed(KeyEvent ev) {
			if (ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Pesquisar.this.setVisible(false);
			}
		}
		public void keyReleased(KeyEvent ev) {  }
		public void keyTyped(KeyEvent ev) {  }
	}

	/**
	* Oculta a área de pesquisa quando o botão fechar for clicado.
	*/
	class OcultarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Pesquisar.this.setVisible(false);
		}
	}
}