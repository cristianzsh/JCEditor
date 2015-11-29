package com.cristian;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
* Classe que exibe as propriedades do SO do usuário
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.5
* @since     Segunda atualização
*/
public class PropriedadesSistema extends JDialog {
	private Locale loc = Locale.getDefault();
	private JTable tabela;
	private JLabel label;

	/**
	* O construtor se encarrega de exibir todo o diálogo
	* O método getProperty(), da classe System foi utilizado para obter as informações do sistema
	*/
	public PropriedadesSistema() {
		setTitle("Sobre este Computador");
		
		tabela = new JTable();
		tabela.setEnabled(false);
		tabela.setShowHorizontalLines(false);
		tabela.setShowVerticalLines(false);
		tabela.setBackground(new Color(238, 238, 238));

		label = new JLabel("Informações");
		label.setFont(new Font("Roboto Light", Font.PLAIN, 28));

		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{"Sistema Operacional", System.getProperty("os.name") + " " + System.getProperty("os.version")},
				{"Arquitetura", System.getProperty("os.arch")},
				{"Usuário", System.getProperty("user.name")},
				{"Idioma", loc.getDisplayLanguage()},
				{"Versão do Java", System.getProperty("java.version")},
				{"Pasta de instalação do Java", System.getProperty("java.home")},
				{"Class Path", System.getProperty("java.class.path")},

			},
			new String[] {null, null}
			));

		tabela.setBorder(BorderFactory.createTitledBorder(
			null, null,
			TitledBorder.DEFAULT_JUSTIFICATION,
			TitledBorder.DEFAULT_POSITION,
			new Font("Roboto Light", 1, 14)
			));

		this.getContentPane().add(BorderLayout.NORTH, label);
		this.getContentPane().add(BorderLayout.CENTER, tabela);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(397, 175);//145
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
}