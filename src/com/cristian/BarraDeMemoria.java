package com.cristian;

import javax.swing.JProgressBar;
import java.util.List;
import java.util.ArrayList;
import java.awt.EventQueue;

/**
* Exibe uma JProgressBar contendo a quantidade de memória utilizada e total.
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.5
* @since     Segunda atualização
*/

public class BarraDeMemoria extends JProgressBar {
	private Thread t = null;
	private LeitorDeMemoria leitor = new LeitorDeMemoria();
	final int MEMORIA_MAXIMA = (int) ((float) Runtime.getRuntime().maxMemory() / 1048576);

	/**
	* O construtor se encarrega de definir o valor máximo da barra de progresso,
	* determina se a barra deve processar uma cadeia de progresso, define uma
	* String nula para a barra e chama o método que inicia o Thread.
	*/
	public BarraDeMemoria() {
		setMaximum(100);
		setStringPainted(true);
		setString("");
		iniciar();
	}

	/**
	* Cria um novo Thread, define ele como daemon, para não impedir a JVM de sair enquanto
	* ele estiver em execução, por fim, o Thread é iniciado.
	*/
	public void iniciar() {
		t = new Thread(leitor, "Barra de memória");
		t.setDaemon(true);
		t.start();
	}

	/**
	* Responsável por capturar a quantidade de mémoria e atualizar a barra de progresso
	* (String e valor da barra).
	*/
	private class LeitorDeMemoria implements Runnable {
		/* Captura a quantidade de memória total e livre, depois é realizada a divisão da subtração
		   das mesmas por 1048576.0, para se obter o valor em MB. Em seguida, o método atualizarBarra
		   é chamado para fazer a exibição dos valores. */
		public void run() {
			try {
				while (!Thread.interrupted()) {
					long total = Runtime.getRuntime().totalMemory();
					long livre = Runtime.getRuntime().freeMemory();
					double memUsada = (total - livre) / 1048576.0;
					atualizarBarra(memUsada);

					Thread.sleep(1000);		// espera 1 seg para fazer a nova atualização
				}
			} catch (Exception ex) {  }
		}

		/* Define o valor da barra de progresso, seu texto e ToolTip */
		public void atualizarBarra(final double memoriaUsada) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					setValue((int) (memoriaUsada * 100.0 / MEMORIA_MAXIMA));
					setString(String.format("%.2fMB de %dMB", memoriaUsada, MEMORIA_MAXIMA));
					setToolTipText(String.format("Memória: %.2fMB de %dMB", memoriaUsada, MEMORIA_MAXIMA));
				}
			});
		}
	}
}