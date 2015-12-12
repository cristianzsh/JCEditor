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

	/**
	* Método que inicia o programa a partir da classe "Preferencias"
	* @param   args
	*/
	public static void main (String[] args) {
		new Preferencias().verificar();
	}
}