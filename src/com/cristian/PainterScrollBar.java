package com.cristian;

import javax.swing.Painter;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
* Classe responsável pela criação e cor da JScrollBar do LookAndFeel JCE
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.5
* @since     Segunda atualização
*/

public class PainterScrollBar implements Painter<JComponent> {
	private final Color cor;

	/**
	* O construtor recebe uma cor como argumento e a usa como cor da JScrollBar.
	*/
	public PainterScrollBar(Color c) {
		this.cor = c;
	}
		
	@Override
	public void paint(Graphics2D g, JComponent obj, int largura, int altura) {
		g.setColor(cor);
		g.fillRect(0, 0, largura, altura );
	}
}