package com.cristian;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
* Exibe o diálogo de "Sobre" quando o botão versão for pressionado
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.9
* @since     Desde a primeira versão
*/

public class VersaoDialog extends JDialog {
	private Image icone;

	/**
	* O construtor se encarrega de criar e exibir todo o diálogo
	*/
	public VersaoDialog() {
		this.setTitle("Versão do JCE");
		if (System.getProperty("os.name").equals("Linux")) {
			icone = new ImageIcon(getClass().getResource("imagens/jceIcone.png")).getImage();
		} else {
			icone = new ImageIcon(getClass().getResource("imagens/jceIcone32.png")).getImage();
		}

		JLabel creditos = new JLabel(new ImageIcon(getClass().getResource("imagens/versao.png")));

		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev) {
				if (ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
					VersaoDialog.this.dispose();
				}
			}
		});

		this.setIconImage(icone);
		this.getContentPane().add(BorderLayout.CENTER, creditos);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
}