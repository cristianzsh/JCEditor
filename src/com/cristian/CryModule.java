package com.cristian;

import java.io.File;
import javax.swing.JOptionPane;

/**
 * Cryptography Vigener implementation
 * @author http://leonardo.labolida.com  September 2017
 */
public class CryModule  {
	
	/**
	 * Aspect intercept_leer
	 * @param fileName
	 * @param fileContent
	 * @return fileContent
	 */
	public static String intercept_leer(File filename , String filecontent){
		filecontent=filecontent.replaceAll("\n", "");
		if (filename.getPath().endsWith(".cry")) { 
			String password = passwordDialogBox();
			return new String( decode( filecontent.getBytes(), password.getBytes() ) ); 
		}
		return filecontent;
	}
	
	
	/**
	 * Aspect intercept_salvar
	 * @param filename
	 * @param filecontent
	 * @return fileContent
	 */
	public static String intercept_salvar(File filename , String filecontent){
		if (filename.getPath().endsWith(".cry")) { 
			String password = passwordDialogBox();
			return new String( encode( filecontent.getBytes(), password.getBytes() ) ); 
		}
		return filecontent;
	}
	
	
	/**
	 * Password Dialog Box
	 * @return password
	 */
	private static String passwordDialogBox(){
		try{
			return (String) JOptionPane.showInputDialog("Introduce a password:");
		}
		catch(Exception e) {
			System.out.println( "Error at chooseFile() : " + e.getMessage() );
			return null;
		}
	}
	
	/**
	 * encode
	 * @param content
	 * @param password
	 * @return
	 */
	private static byte[] encode(byte[] content, byte[] password) {

		StringBuffer buff = new StringBuffer();
		try {
			byte c[] = content;
			byte p[] = password;
			
			for(int i=0; i<c.length; i++){
				
				int ic  = c[i];
				int ip1 = p[(i+0) %p.length];  
				int ip2 = p[(i+1) %p.length];
				int ip3 = p[(i+2) %p.length];
				
				if (ic <0)  { ic=(ic*-1)+128 ; } // unsigned
				if (ip1<0)  { ip1=(ip1*-1)+128 ; } // unsigned
				if (ip2<0)  { ip2=(ip2*-1)+128 ; } // unsigned
				if (ip3<0)  { ip3=(ip3*-1)+128 ; } // unsigned
				
				int r = mod( ic + ip1 + ip2 + ip3 ) ; // Vigenere
				
				buff.append( r + "#");
			}
			return new String(buff).getBytes();
		} 
		catch (Exception e) {
			System.out.println("error at vigenere_encode(): " + e.getMessage());
			JOptionPane.showMessageDialog(null, "error at vigenere_encode():", "InfoBox: ", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	/**
	 * decode
	 * @param content
	 * @param password
	 * @return
	 */
	private static byte[] decode(byte[] content, byte[] password) {
		StringBuffer buff = new StringBuffer();
		try {
			String c[] = new String(content).split("#");  // 65#65#65#65#65#
			byte p[] = password;
			
			if ( c.length<2 ) return null; // BugFix: This is not a TokenMessage '65#65#'. This is a plaintext, so, do not decode it.
			
			for(int i=0; i<c.length; i++){
				c[i] = c[i].replaceAll("\n", "");
				int ic = Integer.parseInt( c[i] ) ;
				int ip1 = p[(i+0) %p.length];  
				int ip2 = p[(i+1) %p.length];
				int ip3 = p[(i+2) %p.length];
				
				if (ic <0)  { ic=(ic*-1)+128 ; } // unsigned
				if (ip1<0)  { ip1=(ip1*-1)+128 ; } // unsigned
				if (ip2<0)  { ip2=(ip2*-1)+128 ; } // unsigned
				if (ip3<0)  { ip3=(ip3*-1)+128 ; } // unsigned
	
				int r = mod( ic - ip1 - ip2 - ip3 ) ; // Vigenere
				
				buff.append( (char)r  );
			}
			return new String(buff).getBytes();
		} 
		catch (Exception e) {
			System.out.println("error at vigenere_decode(): Can NOT decode the message." ); // + e.getMessage()  hide confidential information
			JOptionPane.showMessageDialog(null, "error at vigenere_decode():", "InfoBox: ", JOptionPane.ERROR_MESSAGE);
			return null;
		}	
	}
	
	/**
	 * Modulus
	 * @param x
	 * @return
	 */
	private static int mod(int x){
	    int result = x % 256;
	    return result < 0? result + 256 : result;
	}
	
	

}
