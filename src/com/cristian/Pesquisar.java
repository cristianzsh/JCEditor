package com.cristian;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* Classe que realiza a pesquisa no JTextArea atual
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.5
* @since     Segunda atualização
*/

public class Pesquisar extends JDialog {
	private JLabel pesquisarLabel, substituirLabel;
	private JTextField fieldPesquisar, fieldSubstituir;
	private JButton btnPesquisar, btnSubstituir, btnSubstituirTodos, btnProximo, btnVoltar;
	private JPanel painelNorte, painelSul;
	private JTextArea areaDeTexto;
	private int posicaoInicial = 0;

	/**
	* O construtor é responsável por iniciar a interface de pesquisa.
	*/
	public Pesquisar(JTextArea adt, JCEditor jce) {
		super(jce);
		construirGUI();
		this.areaDeTexto = adt;
	}

	/**
	* Constroí a GUI e adiciona eventos aos botões.
	*/
	public void construirGUI() {
		pesquisarLabel = new JLabel("Pesquisar");
		substituirLabel = new JLabel("Substituir");
		fieldPesquisar = new JTextField(15);
		fieldSubstituir = new JTextField(15);

		btnPesquisar = new JButton("Pesquisar");
		btnSubstituir = new JButton("Substituir");
		btnSubstituirTodos = new JButton("Substituir todos");
		btnProximo = new JButton("Próximo");
		btnVoltar = new JButton("Voltar");

		btnPesquisar.addActionListener(new PesquisarListener());
		btnProximo.addActionListener(new ProximoListener());
		btnSubstituir.addActionListener(new SubstituirListener());
		btnSubstituirTodos.addActionListener(new SubstituirTodosListener());
		btnVoltar.addActionListener(new VoltarListener());

		painelNorte = new JPanel();
		painelNorte.add(pesquisarLabel);
		painelNorte.add(fieldPesquisar);
		painelNorte.add(substituirLabel);
		painelNorte.add(fieldSubstituir);

		painelSul = new JPanel();
		painelSul.add(btnPesquisar);
		painelSul.add(btnSubstituir);
		painelSul.add(btnSubstituirTodos);
		painelSul.add(btnProximo);
		painelSul.add(btnVoltar);

		this.getContentPane().add(BorderLayout.NORTH, painelNorte);
		this.getContentPane().add(BorderLayout.SOUTH, painelSul);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Pesquisar");
		this.setVisible(true);
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
			//areaDeTexto.requestFocus();
			areaDeTexto.select(pos, pos + textoPesquisar.length());
			posicaoInicial = pos + textoPesquisar.length();
		}
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
				//areaDeTexto.requestFocus();
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
}