package com.cristian;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

/**
* Classe responsável por alterar o ícone de arquivos em Potigol (.poti)
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  1.5
* @since    Segunda atualização
*/
public class JFCFileView extends FileView {
	private Icon icone;

	/**
	* Configura o ícone com base no método isPotigol
	* @param arquivo File - arquivo a ser verificado
	*/
	public Icon getIcon(File arquivo) {
		Icon icon = null;

		if (isPotigol(arquivo)) {
			icon = icone;
		}

		return icon;
	}

	/**
	* Verifica se o arquivo é um arquivo Potigol, leva-se em consideração
	* a extensão do mesmo
	* @param arquivo File - arquivo a ser verificado
	*/
	private boolean isPotigol(File arquivo) {
		String extensao = getExtensao(arquivo);
		boolean isPotigol = false;

		if (extensao != null) {
			isPotigol = extensao.equals("poti");
			icone = new ImageIcon(getClass().getResource("imagens/potigol.png"));
		}
		return isPotigol;
	}

	/**
	* Retorna a extensão do arquivo
	* @param arquivo File - arquivo a ser verificado
	*/
	private String getExtensao(File arquivo) {
		String arq = arquivo.getPath();
		String extensao = null;
		int i = arq.lastIndexOf('.');

		if (i > 0 && i < arq.length()) {
			extensao = arq.substring(i + 1).toLowerCase();
		}

		return extensao;
	}
}