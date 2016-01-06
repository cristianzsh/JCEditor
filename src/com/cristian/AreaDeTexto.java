package com.cristian;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;

/**
* Classe responsável por funções lógicas do JTextArea atual.
* Possui métodos para detecção da linguagem de programação,
* além de métodos para salvar e abrir arquivos.
* @author    Cristian Henrique (cristianmsbr@gmail.com)
* @version   1.8
* @since     Segunda atualização
*/

public class AreaDeTexto extends RSyntaxTextArea {
	private RSyntaxTextArea display = null;
	private JPanel pnlText = null;
	private InputStream in;
	private Map<String, String> extensao = new HashMap<>();
	private JFileChooser jfc;
	private boolean modificado = false;
	private RTextScrollPane barra = null;
	private File arquivo = null;
	private String linguagem = "Texto simples";
	private String texto;
	private boolean potigol;

	/**
	* O construtor da classe define uma lista do tipo HashMap, esta lista
	* armazena a extensão e sintaxe da linguagem. Também define o recurso
	* de autocompletar o código.
	*/
	public AreaDeTexto() {

		extensao.put("java", SyntaxConstants.SYNTAX_STYLE_JAVA);
		extensao.put("cpp", SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
		extensao.put("py", SyntaxConstants.SYNTAX_STYLE_PYTHON);
		extensao.put("xml", SyntaxConstants.SYNTAX_STYLE_XML);
		extensao.put("html", SyntaxConstants.SYNTAX_STYLE_HTML);
		extensao.put("js", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		extensao.put("css", SyntaxConstants.SYNTAX_STYLE_CSS);
		extensao.put("c", SyntaxConstants.SYNTAX_STYLE_C);
		extensao.put("sh", SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
		extensao.put("properties", SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE);
		extensao.put("groovy", SyntaxConstants.SYNTAX_STYLE_GROOVY);
		extensao.put("jsp", SyntaxConstants.SYNTAX_STYLE_JSP);
		extensao.put("as", SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT);
		extensao.put("asm", SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
		extensao.put("clj", SyntaxConstants.SYNTAX_STYLE_CLOJURE);
		extensao.put("d", SyntaxConstants.SYNTAX_STYLE_D);
		extensao.put("dfm", SyntaxConstants.SYNTAX_STYLE_DELPHI);
		extensao.put("pas", SyntaxConstants.SYNTAX_STYLE_DELPHI);
		extensao.put("f", SyntaxConstants.SYNTAX_STYLE_FORTRAN);
		extensao.put("json", SyntaxConstants.SYNTAX_STYLE_JSON);
		extensao.put("lof", SyntaxConstants.SYNTAX_STYLE_LATEX);
		extensao.put("lisp", SyntaxConstants.SYNTAX_STYLE_LISP);
		extensao.put("lua", SyntaxConstants.SYNTAX_STYLE_LUA);
		extensao.put("perl", SyntaxConstants.SYNTAX_STYLE_PERL);
		extensao.put("php", SyntaxConstants.SYNTAX_STYLE_PHP);
		extensao.put("rb", SyntaxConstants.SYNTAX_STYLE_RUBY);
		extensao.put("scala", SyntaxConstants.SYNTAX_STYLE_SCALA);
		extensao.put("cs", SyntaxConstants.SYNTAX_STYLE_CSHARP);
		extensao.put("vb", SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC);
		extensao.put("bat", SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH);
		extensao.put("alg", "text/portugol");
		extensao.put("poti", "text/potigol");

		this.setLayout(new BorderLayout());
		this.setBorder(null);
		this.add(barraDeRolagem());

		LanguageSupportFactory lsf = LanguageSupportFactory.get();
		LanguageSupport support = lsf.getSupportFor(SyntaxConstants.SYNTAX_STYLE_JAVA);
		JavaLanguageSupport jls = (JavaLanguageSupport) support;

		try {
			jls.getJarManager().addCurrentJreClassFileSource();
		} catch (IOException ex) {  }

		lsf.register(getRSyntax());
	}

	/**
	* Método responsável por setar o arquivo.
	* @param a File - arquivo
	*/
	public void setArquivo(File a) {
		if (a == null) {
			throw new IllegalArgumentException("O arquivo não pode ser nulo");
		}

		this.arquivo = a;
	}

	/**
	* Retorna o valor da variável arquivo.
	*/
	public File getArquivo() {
		return this.arquivo;
	}

	/**
	* Método responsável por abrir determinado arquivo selecionado pelo usuário. Existem casos em que podem
	* ser lançadas exceções. Como o caso de o arquivo ser um diretório ou não ser um formato válido.
	* @param a File - arquivo que será aberto
	*/
	public void abrir(File a) {
		if (a == null) {
			throw new IllegalArgumentException("O arquivo não pode ser aberto");
		}
		if (a.isDirectory()) {
			throw new IllegalArgumentException("O arquivo não pode ser aberto, pois é um diretório");
		}
		if (!a.canRead()) {
			throw new IllegalArgumentException("O arquivo não é legível");
		}
		ler(a);
	}

	/**
	* Lê o arquivo e define o texto do JTextArea utilizando o método append().
	* @param a File - arquivo a ser lido
	*/
	public void ler(File a) {
		setArquivo(a);

		StringBuffer st = new StringBuffer();

		try {
			BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(a), "UTF-8"));

			String linha = null;
			while ((linha = leitor.readLine()) != null) {
				st.append(linha).append("\n");
			}
			leitor.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo!");
		}

		extensao(a);
		getRSyntax().setText(st.toString());
		arquivoModificado(false);
	}

	/**
	* Método responsável por salvar o arquivo e, por consequência,
	* defini-lo como não modificado.
	*/
	public void salvar(String texto) {
		this.texto = texto;
		try {
			OutputStreamWriter escritor = new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8");
			escritor.write(texto);
			escritor.flush();
			escritor.close();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Não foi possível salvar o arquivo!");
		}

		arquivoModificado(false);
	}

	/**
	* Este método faz com que o usuário escolha um arquivo para então salva-lo.
	* Será usado em duas situações, caso o usuário ainda não tenha salvo o arquivo
	* (o arquivo é nulo) ou quando o usuário pressionar o botão de salvar como.
	*/
	public void salvarComo() {
		if (fileChooser().showSaveDialog(this) == JFileChooser.CANCEL_OPTION) {
			return;
		} else {
			setArquivo(fileChooser().getSelectedFile());
			String s = fileChooser().getSelectedFile().toString();
			extensao(fileChooser().getSelectedFile());
				if (arquivo.exists()) {
					JOptionPane.showMessageDialog(this, "Sobrescrever?");
				}
			salvar(texto);
			extensao(arquivo);
		}
	}

	/**
	* Método que configura a variável "modificado", esta será usada para definir o título.
	* Entre outras funções, como verificar se o arquivo será fechado sem estar salvo.
	* @param modificado boolean - valor que será passado à variável de instância
	*/
	public void arquivoModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	* Retorna o valor da variável "modificado".
	*/
	public boolean arquivoModificado() {
		return this.modificado;
	}

	/**
	* Método que pega a extensão do arquivo, define a linguagem (JLabel do canto inferior direito)
	* e sintaxe. Se o arquivo for um Potigol(.poti), o botão para executar o código será liberado
	* (contanto que o arquivo não seja nulo).
	* @param a File - arquivo no qual a extensão será comparada
	*/
	public void extensao(File a) {
		String s = a.toString();

		if (s.indexOf('.') != -1) {
			String ext = s.substring(s.lastIndexOf('.') + 1);
			if (extensao.containsKey(ext.toLowerCase())) {

				if (ext.toLowerCase().equals("alg")) {
					AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory)TokenMakerFactory.getDefaultInstance();
					atmf.putMapping("text/portugol", "com.cristian.PortugolTokenMaker");
					display.setSyntaxEditingStyle("text/portugol");
				} else if (ext.toLowerCase().equals("poti")) {
					AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
					atmf.putMapping("text/potigol", "com.cristian.PotigolTokenMaker");
					display.setSyntaxEditingStyle("text/potigol");
				}

				display.setSyntaxEditingStyle(extensao.get(ext.toLowerCase()));
				linguagem = ext.toUpperCase();

				if (ext.equalsIgnoreCase("poti")) {
					potigol = true;
				}
				verificarNome();
			}
		}
	}

	/**
	* Método que compara a extensão com a linguagem para, enfim,
	* definir o nome que será exibido no JLabel.
	*/
	public void verificarNome() {
		HashMap<String, String> linguagens = new HashMap<>();
		linguagens.put("java", "Java");
		linguagens.put("py", "Python");
		linguagens.put("cpp", "C++");
		linguagens.put("js", "JavaScript");
		linguagens.put("sh", "Unix Shell");
		linguagens.put("groovy", "Groovy");
		linguagens.put("jsp", "Java Server Pages");
		linguagens.put("properties", "Properties");
		linguagens.put("html", "HTML");
		linguagens.put("css", "CSS");
		linguagens.put("xml", "XML");
		linguagens.put("c", "C");
		linguagens.put("as", "ActionScript");
		linguagens.put("asm", "Assembly");
		linguagens.put("d", "D");
		linguagens.put("clj", "Clojure");
		linguagens.put("dfm", "Delphi");
		linguagens.put("pas", "Pascal");
		linguagens.put("json", "JSON");
		linguagens.put("lof", "LaTeX");
		linguagens.put("lisp", "Lisp");
		linguagens.put("perl", "Perl");
		linguagens.put("lua", "Lua");
		linguagens.put("php", "PHP");
		linguagens.put("rb", "Ruby");
		linguagens.put("scala", "Scala");
		linguagens.put("cs", "C#");
		linguagens.put("vb", "Visual Basic");
		linguagens.put("bat", "Windows Batch");
		linguagens.put("alg", "Portugol");
		linguagens.put("poti", "Potigol");
		Set<String> chaves = linguagens.keySet();
		for (String chave : chaves) {
			if (chave.equalsIgnoreCase(linguagem)) {
				linguagem = linguagens.get(chave);
			}
		}
	}

	/**
	* Método responsável por criar e configurar o JTextArea,
	* cria um, caso não exista, define o tema e fonte.
	*/
	public RSyntaxTextArea getRSyntax() {
		if (display == null) {
			display = new RSyntaxTextArea();
			in = getClass().getResourceAsStream("temas/jce.xml");
			try {
				Theme tema = Theme.load(in);
				tema.apply(display);
			} catch (IOException ex) {  }
			display.setHighlightCurrentLine(true);
			display.setAnimateBracketMatching(true);
			display.setAntiAliasingEnabled(true);
			display.setFont(new Font("Monospaced", Font.BOLD, 12));
		}
		return display;
	}

	/**
	* Método que configura a barra de rolagem do JTextArea.
	*/
	public RTextScrollPane barraDeRolagem() {
		if (barra == null) {
			barra = new RTextScrollPane(getRSyntax(), false, new Color(143, 144, 138));
			barra.setBorder(null);
			barra.setBackground(new Color(39, 39, 39));
			barra.setLineNumbersEnabled(true);
			barra.setFoldIndicatorEnabled(false);
			barra.setName("Barra");
			barra.setViewportView(getRSyntax());
			barra.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		return barra;
	}

	/**
	* Configura o diálogo para abrir ou salvar o arquivo.
	*/
	public JFileChooser fileChooser() {
		if (jfc == null) {
			jfc = new JFileChooser();
			jfc.setDialogTitle("Escolha o diretório de destino");
			jfc.setFileView(new JFCFileView());
		}
		return jfc;
	}

	/**
	* Retorna o valor da barra de rolagem.
	*/
	public RTextScrollPane getBarra() {
		return this.barra;
	}

	/**
	* Retorna o valor da variável linguagem.
	*/
	public String getLinguagem() {
		return this.linguagem;
	}

	/**
	* Modifica o valor da variável linguagem.
	*/
	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	/**
	* Modifica o valor da variável texto.
	*/
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	* Retorna o valor da variável potigol.
	*/
	public boolean isPotigol() {
		return this.potigol;
	}
}