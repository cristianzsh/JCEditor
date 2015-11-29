package com.cristian;

import javax.swing.JTextArea;

/**
* Classe que gera a estrutura inicial de determinadas linguagens
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.5
* @since     Segunda atualização
*/

public class GerarEstrutura {
	private JTextArea area;
	private String linguagem;
	private String t;

	/**
	* O construtor recebe o JTextArea no qual será gerada a estrutura e a String
	* que contém o nome da linguagem de programação.
	*/
	public GerarEstrutura(JTextArea area, String linguagem) {
		this.area = area;
		this.linguagem = linguagem;

		gerar();
	}

	/**
	* Método que verifica a linguagem e gera a estrutura correspondente.
	*/
	private void gerar() {
		if (linguagem.equals("Java")) {
			t = "public class Nome {\n\tpublic static void main (String[] args) {\n\t\t\n\t}\n}";
		} else if (linguagem.equals("Scala")) {
			t = "object Nome extends Application {\n\n}";
		} else if (linguagem.equals("Portugol")) {
			t = "algoritmo : \"Nome\"\n\nvar\n\ninicio\n\nfimalgoritmo";
		} else if (linguagem.equals("PHP")) {
			t = "<?php\n\n?>";
		}

		area.setText(t);
	}
}