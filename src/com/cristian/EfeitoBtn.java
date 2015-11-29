package com.cristian;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;

/**
* Classe que cria o ofeito ao passar o cursor do mouse em cima de qualquer JButton
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.5
* @since     Segunda atualização
*/
public class EfeitoBtn {
	private JButton btn;

	/**
	* Construtor da classe EfeitoBtn, se encarrega da criação de bordas e efeitos das mesmas
	* @param btn JButton - botão que receberá os efeitos
	*/
	public EfeitoBtn(final JButton btn) {
		this.btn = btn;
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setFocusable(false);
		btn.setRequestFocusEnabled(false);
		btn.setRolloverEnabled(false);
		btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
		btn.addMouseListener(new MouseAdapter() {
			/**
			* Método que captura quando o mouse estiver em cima do JButton,
			* este método vai permitir que o botão seja preenchido e logo
			* depois irá colocar um tom de cinza como fundo do botão.
			* @param ev MouseEvent - contém o evento do mouse
			*/
			public void mouseEntered(MouseEvent ev) {
				btn.setContentAreaFilled(true);
				btn.setBackground(new Color(109, 117, 121));
			}

			/**
			* Método que captura quando o mouse estiver em fora do JButton,
			* este método não vai permitir que o botão tenha um fundo.
			* @param ev MouseEvent - contém o evento do mouse
			*/
			public void mouseExited(MouseEvent ev) {
				btn.setContentAreaFilled(false);
			}
		});
	}
}