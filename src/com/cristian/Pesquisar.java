package com.cristian;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* Classe que realiza a pesquisa no JTextArea atual
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.6
* @since     Segunda atualização
*/

public class Pesquisar extends JDialog {
	private JLabel pesquisarLabel, substituirLabel;
	private JTextField fieldPesquisar, fieldSubstituir;
	private JButton[] botoes = new JButton[5];
	private JPanel painelOeste, painelLeste;
	private JTextArea areaDeTexto;
	private int posicaoInicial = 0;

	/**
	* O construtor é responsável por iniciar a interface de pesquisa.
	*/
	public Pesquisar(JTextArea adt, JCEditor jce) {
		super(jce);
		construirGUI();
		this.areaDeTexto = adt;
		this.areaDeTexto.requestFocus();
	}

	/**
	* Constroí a GUI e adiciona eventos aos botões.
	*/
	public void construirGUI() {
		pesquisarLabel = new JLabel("Pesquisar");
		substituirLabel = new JLabel("Substituir");
		fieldPesquisar = new JTextField(15);
		fieldSubstituir = new JTextField(15);

		botoes[0] = new JButton("Pesquisar");
		botoes[1] = new JButton("Substituir");
		botoes[2] = new JButton("Substituir todos");
		botoes[3] = new JButton("Próximo");
		botoes[4] = new JButton("Voltar");

		fieldPesquisar.addActionListener(new PesquisarListener());
		fieldSubstituir.addActionListener(new FieldSubstituirListener());
		Box boxLeste = new Box(BoxLayout.Y_AXIS);

		for (int i = 0; i < botoes.length; i++) {
			botoes[i].setMinimumSize(new Dimension(130, 25));
			botoes[i].setPreferredSize(new Dimension(130, 25));
			botoes[i].setMaximumSize(new Dimension(130, 25));

			boxLeste.add(botoes[i]);
		}

		botoes[0].addActionListener(new PesquisarListener());
		botoes[3].addActionListener(new ProximoListener());
		botoes[1].addActionListener(new SubstituirListener());
		botoes[2].addActionListener(new SubstituirTodosListener());
		botoes[4].addActionListener(new VoltarListener());

		painelOeste = new JPanel();
		Box boxOeste = new Box(BoxLayout.Y_AXIS);
		boxOeste.add(pesquisarLabel);
		boxOeste.add(fieldPesquisar);
		boxOeste.add(substituirLabel);
		boxOeste.add(fieldSubstituir);
		painelOeste.add(boxOeste);

		painelLeste = new JPanel();
		painelLeste.add(boxLeste);

		this.getContentPane().add(BorderLayout.WEST, painelOeste);
		this.getContentPane().add(BorderLayout.EAST, painelLeste);
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

	class FieldSubstituirListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			pesquisar();
			if (fieldSubstituir.getText().equals("") || fieldPesquisar.getText().equals("")) {
				return;
			}

			areaDeTexto.replaceSelection(fieldSubstituir.getText());
		}
	}
}