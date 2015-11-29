package com.cristian;

import javax.swing.UIManager;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
* Classe que inicia o JCEditor
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  1.5
* @since    Desde a primeira versão
*/

public class MainClass {
	private static JCEditor editor;
	private static final String HOME = System.getProperty("user.home") + "/ConfigJCE/configJCE.conf";
	/**
	* Método que inicia o programa são feitas verificações para comprovar a existência do arquivo de configuraçõs,
	* se o arquivo existir, o JCE será aberto com as últimas configurações. Caso contrário, o arquivo é criado.
	* @param   args
	*/
	public static void main (String[] args) {

		File arquivoConfig = new File(System.getProperty("user.home") + "/ConfigJCE");
		if (arquivoConfig.exists()) {
			abrirPreferencias();
		} else {
			try {
				arquivoConfig.mkdir();
				FileWriter fw = new FileWriter(HOME);
				fw.write("1jce\n");
				fw.write("2jce\n");
				fw.flush();
				fw.close();
				abrirPreferencias();
			} catch (Exception ex) {  }
		}

		/* Descompacta o arquivo de configurações do Potigol caso ele não exista. */
		File pastaPotigol = new File(System.getProperty("user.home") + "/ConfigJCE/.potigol");
		File arquivoDeConfiguracoes = new File(System.getProperty("user.dir") + "/configPotigol.zip");
		if (!pastaPotigol.exists()) {
			pastaPotigol.mkdir();
			descompactar(arquivoDeConfiguracoes, pastaPotigol);
		} else {
			File exec = new File(pastaPotigol + "/potigol.jar");
			if (!exec.exists()) {
				descompactar(arquivoDeConfiguracoes, pastaPotigol);
			}
		}
	}

	/**
	* Método responsável por carregar as preferências do usuário (Look And Feel) e tema.
	*/
	private static void abrirPreferencias() {
		try {
			FileReader fr = new FileReader(new File(HOME));
			BufferedReader leitor = new BufferedReader(fr);
			String linha = null;

			while ((linha = leitor.readLine()) != null) {
				String sub = linha.substring(0, 1);
				if (sub.equals("1")) {
					String laf = linha.substring(linha.indexOf("1") + 1, linha.length());
					if (laf.equals("jce")) {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

						UIManager.getLookAndFeelDefaults().put(
							"ScrollBar:ScrollBarThumb[Enabled].backgroundPainter",
							new PainterScrollBar(new Color(69, 69, 69)));
						UIManager.getLookAndFeelDefaults().put(
							"ScrollBar:ScrollBarThumb[MouseOver].backgroundPainter",
							new PainterScrollBar(new Color(69, 69, 69)));
						UIManager.getLookAndFeelDefaults().put(
							"ScrollBar:ScrollBarTrack[Enabled].backgroundPainter",
							new PainterScrollBar(new Color(39, 39, 39)));

						UIManager.getLookAndFeelDefaults().put(
							"ScrollBar:\"ScrollBar.button\".size", 0);
						UIManager.getLookAndFeelDefaults().put(
							"ScrollBar.decrementButtonGap", 0);
						UIManager.getLookAndFeelDefaults().put(
							"ScrollBar.incrementButtonGap", 0);
						editor = new JCEditor();
					} else {
						UIManager.setLookAndFeel(laf);
						editor = new JCEditor();
						editor.sLAF = laf;
					}
					editor.sLAF = laf;
				}

				if (sub.equals("2")) {
					String tema = linha.substring(linha.indexOf("2") + 1, linha.length());
					editor.carregarTema(tema);
				}
			}
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	/**
	* Método responsável por salvar as preferências do usuário, este método é
	* chamado toda vez que o usuário fechar o programa.
	*/
	public static void salvarPreferencias() {
		try {
			FileWriter fw = new FileWriter(HOME);
			fw.write("1" + editor.sLAF + "\n");
			fw.write("2" + editor.sTema + "\n");
			fw.flush();
			fw.close();
		} catch (Exception ex) {  }
	}

	/**
	* Método responsável por descompactar o arquivo de configurações do Potigol.
	* Se a descompactação for bem sucedida, será exibida uma mensagem dizendo isso.
	* Vale notar que em sistemas como o Linux, será necessária a primeira execução via
	* Terminal, utilizando o comando "java -jar JCEditor.jar". Em seguida, deve ser dada
	* a permissão para a execução do compilador do Potigol "chmod 777 ${HOME}/ConfigJCE/.potigol/potigol.jar".
	*/
	private static void descompactar(File arq, File dir) {
		ZipFile zip = null;
		File arquivo = null;
		InputStream is = null;
		OutputStream os = null;
		byte[] buffer = new byte[1024];

		try {
			zip = new ZipFile(arq);
			Enumeration e = zip.entries();
			while (e.hasMoreElements()) {
				ZipEntry entrada = (ZipEntry) e.nextElement();
				arquivo = new File(dir, entrada.getName());

				if (entrada.isDirectory() && !arquivo.exists()) {
					arquivo.mkdirs();
					continue;
				}

				try {
					is = zip.getInputStream(entrada);
					os = new FileOutputStream(arquivo);
					int bytes = 0;

					while ((bytes = is.read(buffer)) > 0) {
						os.write(buffer, 0, bytes);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (Exception ex) { ex.printStackTrace(); }
					}

					if (os != null) {
						try {
							os.close();
						} catch (Exception ex) { ex.printStackTrace(); }
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Potigol configurado com sucesso!");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (zip != null) {
				try {
					zip.close();
				} catch (Exception ex) { ex.printStackTrace(); }
			}
		}
	}
}