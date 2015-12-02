package com.cristian;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.KeyboardFocusManager;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.FlowLayout;
import java.awt.Event;
import java.awt.Desktop;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.net.URI;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;

/**
* Classe que cria a interface principal e manipula parte dos eventos
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  1.5
* @since    Desde a primeira versão
*/

public class JCEditor extends JFrame {
	AreaDeTexto at;
	private JTabbedPane arquivos;
	private Font roboto = new Font("Roboto Light", Font.PLAIN, 14);
	private JLabel separador = new JLabel("   ");
	private JLabel separador2 = new JLabel("   ");
	private JLabel fonteAtual;
	private JLabel linguagem;
	private JToolBar barraS;
	private JMenuItem novoArq, salvarArq, salvarArqComo, abrirArq, sair, recortar, copiar, colar, versao, sobrePC, fonte, pesquisar, fontePadrao, aumentarFonte,
		diminuirFonte, executarPotigol, imprimir, fecharAba, sobrePotigol;
	private JRadioButtonMenuItem java, cPlusPlus, pythonL, html, css, javaScript, xml, c, unixShell, properties, groovy, jsp,
		actionScript, assembly, clojure, d, delphi, fortran, json, latex, lisp, lua, perl, php, ruby, scala, portugol, pascal, potigol;
	private JRadioButtonMenuItem padrao, nimbus, metal, sistema, motif, btnJava, btnPython;
	private JRadioButtonMenuItem dark, jce, defaultT, defaultAlt, eclipse, idea, darkii, idle;
	private JRadioButtonMenuItem gerarEstrutura;
	private JMenuBar barraDeMenu;
	private JMenu menu, editar, sobre, preferencias, lookAndFeel, formatar, linguagemMenu, tema;
	private InputStream in;
	private JButton bNovo, bAbrir, bSalvar, bSalvarComo, bCopiar, bColar, bRecortar, bPesquisar, bExecutarPotigol, bImprimir;
	private Image icone;
	private ButtonGroup bg, bg2, bg3;
	private String fonteEscolhida = "Monospaced";
	private int tamanhoFonte = 12;
	private String titulo;
	public String sLAF, sTema, auxArquivo;
	private ArrayList<AreaDeTexto> lista = new ArrayList<>();

	/**
	* O construtor define um título e chama o método de construção da interface gráfica.
	*/
	public JCEditor() {
		setTitle("JCEditor");
		construirGUI();
	}

	/**
	* Cria e configura a parte gráfica da janela principal. São utilizados métodos
	* auxiliares como "configMenu" e "configRadioMenus" para facilitar e reduzir
	* as linhas de código para a criação desses componentes (JMenu e JRadioButtonMenuItem).
	*/
	public void construirGUI() {
		/* Instâncias de alguns objetos que serão utilizados. */
		barraDeMenu = new JMenuBar();
		arquivos = new JTabbedPane();
		barraS = new JToolBar();
		at = new AreaDeTexto();

		/* Cria objetos de menu */
		menu = new JMenu("Arquivo");
		editar = new JMenu("Editar");
		formatar = new JMenu("Formatar");
		linguagemMenu = new JMenu("Linguagem");
		preferencias = new JMenu("Preferências");
		lookAndFeel = new JMenu("LAF");
		tema = new JMenu("Tema");
		sobre = new JMenu("Sobre");

		menu.setMnemonic('A');
		editar.setMnemonic('E');
		sobre.setMnemonic('S');
		formatar.setMnemonic('F');
		preferencias.setMnemonic('P');
		linguagemMenu.setMnemonic('L');

		/* Cria a primeira aba do programa, esta aba é adicionada a uma ArrayList. */
		lista.add(at);
		arquivos.addTab("Sem nome", at);
		arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), "Sem nome");

		int i = arquivos.getSelectedIndex();	// índice a aba atual
		arquivos.setTabComponentAt(i, new ButtonTabComponent(arquivos, lista));	// adiciona o botão de fechar à aba
		adicionarDocumentListener();

		/* Permite a navegação entre as abas utilizando Ctrl+Tab. */
		Set chave = new HashSet();
		chave.add(KeyStroke.getKeyStroke("TAB"));
		arquivos.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, chave);

		barraDeMenu.setBorder(null);
		barraS.setBorderPainted(false);
		tema.setIcon(new ImageIcon(getClass().getResource("imagens/escolherTema.png")));
		lookAndFeel.setIcon(new ImageIcon(getClass().getResource("imagens/pincel.png")));

		/* Código de configuração dos menus de funções, sua estrutura é:
		JMenuItem, String(nome do menu), String(imagem do menu), ActionListener, KeyEvent, ActionEvent, JMenu
		(menu ao qual o JMenuItem pertence) */
		configMenu(novoArq, "Novo", "imagens/novo.png", new NovoListener(), KeyEvent.VK_N, ActionEvent.CTRL_MASK, menu);
		configMenu(abrirArq, "Abrir", "imagens/abrir.png", new AbrirListener(), KeyEvent.VK_O, ActionEvent.CTRL_MASK, menu);
		configMenu(salvarArq, "Salvar", "imagens/salvar.png", new SalvarListener(), KeyEvent.VK_S, ActionEvent.CTRL_MASK, menu);
		configMenu(salvarArqComo, "Salvar como", "imagens/salvarComo.png", new SalvarComoListener(), KeyEvent.VK_S, Event.CTRL_MASK | Event.SHIFT_MASK, menu);
		configMenu(imprimir, "Imprimir", "imagens/imprimir.png", new ImprimirPotigolListener(), KeyEvent.VK_P, ActionEvent.CTRL_MASK, menu);
		configMenu(executarPotigol, "Executar Potigol", "imagens/play.png", new ExecutarPotigolListener(), KeyEvent.VK_F9, 0, menu);
		configMenu(fecharAba, "Fechar aba", "imagens/fecharAba.png", new FecharAbaListener(), KeyEvent.VK_W, ActionEvent.CTRL_MASK, menu);
		configMenu(sair, "Sair", "imagens/sair.png", new SairListener(), KeyEvent.VK_E, ActionEvent.CTRL_MASK, menu);
		configMenu(recortar, "Recortar", "imagens/recortar.png", new RecortarListener(), KeyEvent.VK_X, ActionEvent.CTRL_MASK, editar);
		configMenu(copiar, "Copiar", "imagens/copiar.png", new CopiarListener(), KeyEvent.VK_C, ActionEvent.CTRL_MASK, editar);
		configMenu(colar, "Colar", "imagens/colar.png", new ColarListener(), KeyEvent.VK_V, ActionEvent.CTRL_MASK, editar);
		configMenu(sobrePotigol, "Potigol", "imagens/potigol.png", new SobrePotigolListener(), KeyEvent.VK_I, ActionEvent.CTRL_MASK, sobre);
		configMenu(sobrePC, "Sobre este PC", "imagens/config.png", new SobrePCListener(), KeyEvent.VK_F2, ActionEvent.CTRL_MASK, sobre);
		configMenu(versao, "Versão", "imagens/versaoIcone.png", new VersaoListener(), KeyEvent.VK_F1, ActionEvent.CTRL_MASK, sobre);
		configMenu(pesquisar, "Pesquisar","imagens/pesquisar.png", new PesquisarListener(), KeyEvent.VK_F, ActionEvent.CTRL_MASK, formatar);
		configMenu(fonte, "Fonte", "imagens/fonte.png", new EscolherFonteListener(), KeyEvent.VK_D, ActionEvent.CTRL_MASK, formatar);
		configMenu(fontePadrao, "Normal", "imagens/fontePadrao.png", new FontePadraoListener(), KeyEvent.VK_P, Event.CTRL_MASK | Event.SHIFT_MASK, formatar);
		configMenu(aumentarFonte, "Aumentar", "imagens/aumentarFonte.png", new AumentarFonteListener(), KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK, formatar);
		configMenu(diminuirFonte, "Diminuir", "imagens/diminuirFonte.png", new DiminuirFonteListener(), KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK, formatar);

		/* Código de configuração dos menus de Look And Feel
		Sua estrutura é semelhante a do método "configMenu" exceto por utilizar um ButtonGroup(para que
		só exista um botão selecionado) e também pelo fato de não existir um ícone */
		bg = new ButtonGroup();
		configRadioMenus(padrao, "JCE", new LAFPadraoListener(), bg, lookAndFeel);
		configRadioMenus(nimbus, "Nimbus", new LAFListener("javax.swing.plaf.nimbus.NimbusLookAndFeel"), bg, lookAndFeel);
		configRadioMenus(metal, "Metal", new LAFListener("javax.swing.plaf.metal.MetalLookAndFeel"), bg, lookAndFeel);
		configRadioMenus(sistema, "Sistema", new LAFListener(UIManager.getSystemLookAndFeelClassName()), bg, lookAndFeel);
		configRadioMenus(motif, "Motif", new LAFListener("com.sun.java.swing.plaf.motif.MotifLookAndFeel"), bg, lookAndFeel);

		/* Código de configuração dos menus de temas */
		bg3 = new ButtonGroup();
		configRadioMenus(jce, "JCE", new TemaListener("jce"), bg3, tema);
		configRadioMenus(dark, "Dark", new TemaListener("dark"), bg3, tema);
		configRadioMenus(dark, "Dark II", new TemaListener("darkii"), bg3, tema);
		configRadioMenus(defaultT, "Default", new TemaListener("default"), bg3, tema);
		configRadioMenus(defaultAlt, "Default-Alt", new TemaListener("default-alt"), bg3, tema);
		configRadioMenus(eclipse, "Eclipse", new TemaListener("eclipse"), bg3, tema);
		configRadioMenus(idea, "IDEA", new TemaListener("idea"), bg3, tema);
		configRadioMenus(dark, "IDLE", new TemaListener("idle"), bg3, tema);

		gerarEstrutura = new JRadioButtonMenuItem("Gerar estrutura");
		gerarEstrutura.setIcon(new ImageIcon(getClass().getResource("imagens/estrutura.png")));
		gerarEstrutura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		formatar.add(gerarEstrutura);

		/* Código de configuração dos menus de linguagem, este método também é utilizado para a configuração dos itens de LAF e tema
		Sua estrutura é composta por: JRadioButtonMenuItem, String(nome no item de menu), ActionListener(recebe como
		argumento uma String contendo o nome da linguagem e sua sintaxe), ButtonGroup, JMenu */
		bg2 = new ButtonGroup();
		configRadioMenus(java, "Java", new LinguagemListener("Java", SyntaxConstants.SYNTAX_STYLE_JAVA), bg2, linguagemMenu);
		configRadioMenus(cPlusPlus, "C++", new LinguagemListener("C++", SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS), bg2, linguagemMenu);
		configRadioMenus(pythonL, "Python", new LinguagemListener("Python", SyntaxConstants.SYNTAX_STYLE_PYTHON), bg2, linguagemMenu);
		configRadioMenus(html, "HTML", new LinguagemListener("HTML", SyntaxConstants.SYNTAX_STYLE_HTML), bg2, linguagemMenu);
		configRadioMenus(css, "CSS", new LinguagemListener("CSS", SyntaxConstants.SYNTAX_STYLE_CSS), bg2, linguagemMenu);
		configRadioMenus(javaScript, "JavaScript", new LinguagemListener("JavaScript", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT), bg2, linguagemMenu);
		configRadioMenus(xml, "XML", new LinguagemListener("XML", SyntaxConstants.SYNTAX_STYLE_XML), bg2, linguagemMenu);
		configRadioMenus(c, "C", new LinguagemListener("C", SyntaxConstants.SYNTAX_STYLE_C), bg2, linguagemMenu);
		configRadioMenus(potigol, "Potigol", new PotigolListener(), bg2, linguagemMenu);
		configRadioMenus(portugol, "Portugol", new PortugolListener(), bg2, linguagemMenu);
		configRadioMenus(pascal, "Pascal", new LinguagemListener("Pascal", SyntaxConstants.SYNTAX_STYLE_DELPHI), bg2, linguagemMenu);
		configRadioMenus(unixShell, "Unix Shell", new LinguagemListener("Unix Shell", SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL), bg2, linguagemMenu);
		configRadioMenus(properties, "Properties", new LinguagemListener("Properties", SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE), bg2, linguagemMenu);
		configRadioMenus(groovy, "Groovy", new LinguagemListener("Groovy", SyntaxConstants.SYNTAX_STYLE_GROOVY), bg2, linguagemMenu);
		configRadioMenus(jsp, "Java Server Pages", new LinguagemListener("Java Server Pages", SyntaxConstants.SYNTAX_STYLE_JSP), bg2, linguagemMenu);
		configRadioMenus(actionScript, "ActionScript", new LinguagemListener("ActionScript", SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT), bg2, linguagemMenu);
		configRadioMenus(assembly, "Assembly", new LinguagemListener("Assembly", SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86), bg2, linguagemMenu);
		configRadioMenus(clojure, "Clojure", new LinguagemListener("Clojure", SyntaxConstants.SYNTAX_STYLE_CLOJURE), bg2, linguagemMenu);
		configRadioMenus(d, "D", new LinguagemListener("D", SyntaxConstants.SYNTAX_STYLE_D), bg2, linguagemMenu);
		configRadioMenus(delphi, "Delphi", new LinguagemListener("Delphi", SyntaxConstants.SYNTAX_STYLE_DELPHI), bg2, linguagemMenu);
		configRadioMenus(fortran, "Fortran", new LinguagemListener("Fortran", SyntaxConstants.SYNTAX_STYLE_FORTRAN), bg2, linguagemMenu);
		configRadioMenus(json, "JSON", new LinguagemListener("JSON", SyntaxConstants.SYNTAX_STYLE_JSON), bg2, linguagemMenu);
		configRadioMenus(latex, "LaTex", new LinguagemListener("LaTex", SyntaxConstants.SYNTAX_STYLE_LATEX), bg2, linguagemMenu);
		configRadioMenus(lisp, "Lisp", new LinguagemListener("Lisp", SyntaxConstants.SYNTAX_STYLE_LISP), bg2, linguagemMenu);
		configRadioMenus(lua, "Lua", new LinguagemListener("Lua", SyntaxConstants.SYNTAX_STYLE_LUA), bg2, linguagemMenu);
		configRadioMenus(perl, "Perl", new LinguagemListener("Perl", SyntaxConstants.SYNTAX_STYLE_PERL), bg2, linguagemMenu);
		configRadioMenus(php, "PHP", new LinguagemListener("PHP", SyntaxConstants.SYNTAX_STYLE_PHP), bg2, linguagemMenu);
		configRadioMenus(ruby, "Ruby", new LinguagemListener("Ruby", SyntaxConstants.SYNTAX_STYLE_RUBY), bg2, linguagemMenu);
		configRadioMenus(scala, "Scala", new LinguagemListener("Scala", SyntaxConstants.SYNTAX_STYLE_SCALA), bg2, linguagemMenu);

		/* Código de configuração dos JButtons da JToolBar.
		O método recebe o botão a ser configurado, seu ToolTipText, seu ícone e seu listener */
		bNovo = new JButton();
		configBtns(bNovo, "Novo arquivo", "imagens/25x25/novo25.png", new NovoListener());

		bAbrir = new JButton();
		configBtns(bAbrir, "Abrir arquivo", "imagens/25x25/abrir25.png", new AbrirListener());

		bSalvar = new JButton();
		configBtns(bSalvar, "Salvar arquivo", "imagens/25x25/salvar25.png", new SalvarListener());

		bSalvarComo = new JButton();
		configBtns(bSalvarComo, "Salvar como", "imagens/25x25/salvarComo25.png", new SalvarComoListener());
		barraS.add(separador);

		bCopiar = new JButton();
		configBtns(bCopiar, "Copiar", "imagens/25x25/copiar25.png", new CopiarListener());

		bColar = new JButton();
		configBtns(bColar, "Colar", "imagens/25x25/colar25.png", new ColarListener());

		bRecortar = new JButton();
		configBtns(bRecortar, "Recortar", "imagens/25x25/recortar25.png", new RecortarListener());

		bPesquisar = new JButton();
		configBtns(bPesquisar, "Pesquisar", "imagens/25x25/pesquisar25.png", new PesquisarListener());
		barraS.add(separador2);

		bExecutarPotigol = new JButton();
		configBtns(bExecutarPotigol, "Executar Potigol", "imagens/25x25/play25.png", new ExecutarPotigolListener());
		bExecutarPotigol.setEnabled(false);

		bImprimir = new JButton();
		configBtns(bImprimir, "Imprimir", "imagens/25x25/imprimir25.png", new ImprimirPotigolListener());

		/* Define o tamanho do ícone com base no SO */
		if (System.getProperty("os.name").equals("Linux")) {
			icone = new ImageIcon(getClass().getResource("imagens/jceIcone.png")).getImage();
		} else {
			icone = new ImageIcon(getClass().getResource("imagens/jceIcone32.png")).getImage();
		}

		/* Cria o painel que exibirá a barra de memória e informações sobre linguagem e fonte. */
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		fonteAtual = new JLabel("Monospaced / Font.PLAIN / " + tamanhoFonte + "  |   ");
		fonteAtual.setFont(new Font("Roboto Light", Font.BOLD, 12));
		fonteAtual.setForeground(new Color(234, 234, 235));
		panel.setBackground(new Color(91, 91, 91));
		panel2.setBackground(new Color(91, 91, 91));

		linguagem = new JLabel(lista.get(arquivos.getSelectedIndex()).linguagem + "   ");
		linguagem.setFont(new Font("Roboto Light", Font.BOLD, 12));
		linguagem.setForeground(new Color(234, 234, 235));

		BarraDeMemoria bm = new BarraDeMemoria();
		panel2.add(bm);
		panel.add(panel2);
		panel.add(fonteAtual);
		panel.add(linguagem);

		/* Evento que verifica se o arquivo foi modificado quando o usuário clicar no botão fechar,
		se o arquivo foi modificado o usuário poderá escolher entre salvar ou não. Também é levado
		em consideração se o arquivo existe ou se é nulo */
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				new MainClass().salvarPreferencias();

				salvarAoSair();
			}
		});

		/* Evento de troca de aba. É responsável por definir o título do JFrame
		com base no arquivo que está na aba atual, também faz com que o botão de execução de arquivos Potigol seja
		liberado, para isso, leva em conta a variável booleana "isPotigol", esta variável tem valor true quando a
		extensão do arquivo for .poti(arquivos Potigol) */
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				JTabbedPane source = (JTabbedPane) ev.getSource();
				int index = source.getSelectedIndex();
				if (index == arquivos.getSelectedIndex()) {
					definirTitulo();
					updateLanguage(lista.get(arquivos.getSelectedIndex()).linguagem);
				}

				if (lista.get(arquivos.getSelectedIndex()).isPotigol && lista.get(arquivos.getSelectedIndex()).arquivo != null) {
					bExecutarPotigol.setEnabled(true);
				} else {
					bExecutarPotigol.setEnabled(false);
				}
			}
		};

		/* Adiciona o evento ChangeListener e o evento de arrastar e soltar */
		arquivos.addChangeListener(changeListener);
		arrastarESoltar();

		preferencias.add(lookAndFeel);
		preferencias.add(tema);

		/* Adiciona os menus na barra principal */
		barraDeMenu.add(menu);
		barraDeMenu.add(editar);
		barraDeMenu.add(formatar);
		barraDeMenu.add(linguagemMenu);
		barraDeMenu.add(preferencias);
		barraDeMenu.add(sobre);

		getContentPane().add(BorderLayout.NORTH, barraS);
		getContentPane().add(BorderLayout.SOUTH, panel);	// apenas define o layout dos componentes
		getContentPane().add(BorderLayout.CENTER, arquivos);
		this.setJMenuBar(barraDeMenu);
		this.setIconImage(icone);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(844, 635);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(468, 328));
		this.setVisible(true);
	}

	/**
	* Método que define o nome do JFrame.
	* Leva em consideração se o arquivo existe e se foi modificado
	*/
	private void definirTitulo() {
		titulo = "Sem nome - JCEditor";

		if (lista.get(arquivos.getSelectedIndex()).arquivo != null && lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
			titulo = lista.get(arquivos.getSelectedIndex()).arquivo.toString() + " •- JCEditor";
			arquivos.setTitleAt(arquivos.getSelectedIndex(), "• " + lista.get(arquivos.getSelectedIndex()).arquivo.getName());
		} else if (lista.get(arquivos.getSelectedIndex()).arquivo != null) {
			titulo = lista.get(arquivos.getSelectedIndex()).arquivo.toString() + " - JCEditor";
			arquivos.setTitleAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).arquivo.getName());
		} else if (lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
			titulo = "Sem nome •- JCEditor";
			arquivos.setTitleAt(arquivos.getSelectedIndex(), "• Sem nome");
		}
		setTitle(titulo);
	}

	/**
	* Método que cria os menus para as funções do programa (copiar, colar, abrir, etc.)
	* @param itemDeMenu JMenuItem - item de menu a ser configurado
	* @param nome String - nome que será dado ao JMenuItem
	* @param img String - caminho da imagem PNG do JMenuItem
	* @param ev ActionListener - evento que será executado ao pressionar o menu
	* @param ac int - accelerator (KeyEvent)
	* @param ac2 int - accelerator (ActionEvent)
	* @param principal JMenu - menu ao qual o JMenuItem pertence
	*/
	private void configMenu(JMenuItem itemDeMenu, String nome , String img, ActionListener ev, int ac, int ac2, JMenu principal) {
		itemDeMenu = new JMenuItem(nome);
		itemDeMenu.setIcon(new ImageIcon(getClass().getResource(img)));
		itemDeMenu.addActionListener(ev);
		itemDeMenu.setFont(roboto);
		itemDeMenu.setAccelerator(KeyStroke.getKeyStroke(ac, ac2));
		principal.add(itemDeMenu);
	}

	/**
	* Método que cria o ToolTipText, adiciona a imagem, ActionListener, efeito(classe EfeitoBtn) e,
	* por fim, adiciona o JButton na JToolBar (barraS)
	* @param btn JButton - botão que será configurado
	* @param toolTipText String - ToolTipText que será exibido
	* @param img String - caminho da imagem do JButton
	* @param ev ActionListener - evento que será executado ao pressionar o botão
	*/
	private void configBtns(JButton btn , String toolTipText, String img, ActionListener ev) {
		btn.setToolTipText(toolTipText);
		btn.setIcon(new ImageIcon(getClass().getResource(img)));
		btn.addActionListener(ev);
		EfeitoBtn eb = new EfeitoBtn(btn);
		barraS.add(btn);
	}

	/**
	* Método que cria os JRadioButtonMenuItem(s)
	* @param menu JRadioButtonMenuItem - menu de rádio que será configurado
	* @param nome String - nome do menu
	* @param ev ActionListener - evento que será executado ao pressionar o menu
	* @param bg ButtonGroup - grupo a qual o menu pertence (não é possível ter mais de um selecionado)
	* @param mPrincipal - menu ao qual o menu de rádio pertence
	*/
	private void configRadioMenus(JRadioButtonMenuItem menu, String nome, ActionListener ev, ButtonGroup bg, JMenu mPrincipal) {
		menu = new JRadioButtonMenuItem(nome);
		menu.addActionListener(ev);
		menu.setFont(roboto);
		bg.add(menu);

		mPrincipal.add(menu);
	}

	private void salvarAoSair() {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).arquivoModificado()) {
				String nomeArquivo = null;
				if (lista.get(i).arquivo == null) {
					nomeArquivo = "Sem nome";
				} else {
					nomeArquivo = lista.get(i).arquivo.getName();
				}
				int r = JOptionPane.showConfirmDialog(JCEditor.this, "Você deseja salvar o arquivo \"" + nomeArquivo + "\"?",
					"Sair", JOptionPane.YES_NO_OPTION);
				lista.get(i).texto = lista.get(i).getRSyntax().getText();
				if (r == JOptionPane.OK_OPTION) {
					if (lista.get(i).arquivo == null) {
						lista.get(i).salvarComo();
					} else {
						lista.get(i).salvar(lista.get(i).getRSyntax().getText());
					}
				}
			}
		}
	}

	/**
	* Método que adiciona eventos ao JTextArea atual, tais eventos definem o título do JFrame
	*/
	private void adicionarDocumentListener() {
		lista.get(arquivos.getSelectedIndex()).getRSyntax().getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent ev) {  }
		
			public void insertUpdate(DocumentEvent ev) {
				lista.get(arquivos.getSelectedIndex()).arquivoModificado(true);
				definirTitulo();
			}

			public void removeUpdate(DocumentEvent ev) {
				lista.get(arquivos.getSelectedIndex()).arquivoModificado(true);
				definirTitulo();
			}
		});
	}

	/**
	* Método que adiciona o recurso de Drag-and-drop (arrastar e soltar) ao JTextArea da aba atual
	*/
	private void arrastarESoltar() {
		DropTarget dt = new DropTarget(lista.get(arquivos.getSelectedIndex()).getRSyntax(), new DropTargetListener() {
			public void dragEnter(DropTargetDragEvent ev) {  }

			public void dragExit(DropTargetEvent ev) {  }

			public void dragOver(DropTargetDragEvent ev) {  }

			public void dropActionChanged(DropTargetDragEvent ev) {  }

			public void drop(DropTargetDropEvent ev) {
				try {
					ev.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					java.util.List lista2 = (java.util.List) ev.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					File arquivoD = (File) lista2.get(0);

					at = new AreaDeTexto();
					lista.add(at);
					arquivos.addTab("Sem nome", at);
					arquivos.setSelectedIndex(lista.size() - 1);
					adicionarDocumentListener();
					arquivos.setTabComponentAt(arquivos.getSelectedIndex(), new ButtonTabComponent(arquivos, lista));
					arquivos.setTitleAt(arquivos.getSelectedIndex(), arquivoD.getName());

					lista.get(arquivos.getSelectedIndex()).abrir(arquivoD);
					lista.get(arquivos.getSelectedIndex()).arquivoModificado(false);

					linguagem.setText(lista.get(arquivos.getSelectedIndex()).linguagem + "   ");
					definirTitulo();
					carregarTema(sTema);
					updateFonte();
					arrastarESoltar();

					if (lista.get(arquivos.getSelectedIndex()).isPotigol) {
						bExecutarPotigol.setEnabled(true);
					}

				} catch (Exception ex) {  }
			}
		});
	}

	/**
	* Método que atualiza o LAF utilizando o método updateComponentTreeUI, da classe SwingUtilities
	* Este método também retira a borda da barra de menu e do JTextArea
	*/
	private void atualizarLAF() {
		SwingUtilities.updateComponentTreeUI(this);
		barraDeMenu.setBorder(null);
		for (AreaDeTexto adt : lista) {
			adt.setBorder(null);
			adt.barra.setBorder(null);
		}
	}

	/**
	* Método que carrega o tema a ser utilizado (cores da sintaxe)
	* @param nomeDoTema String - nome do tema a ser carregado (ex.: dark, darkii, etc.)
	*/
	public void carregarTema(String nomeDoTema) {
		for (AreaDeTexto adt : lista) {
			in = getClass().getResourceAsStream("temas/" + nomeDoTema + ".xml");
			sTema = nomeDoTema;
			try {
				Theme tema = Theme.load(in);
				tema.apply(adt.getRSyntax());
			} catch (IOException ex) {  }
		}
	}

	/**
	* Método que atualiza o nome da linguagem (exibido no canto inferior direito)
	* @param nome String - nome da linguagem
	*/
	private void updateLanguage(String nome) {
		lista.get(arquivos.getSelectedIndex()).linguagem = nome;
		linguagem.setText(lista.get(arquivos.getSelectedIndex()).linguagem + "   ");
	}

	/**
	* Método que atualiza o tamanho da fonte e a própria fonte utilizada em
	* todos os campos de textos de todas as abas
	*/
	private void updateFonte() {
		for (AreaDeTexto adt : lista) {
			adt.getRSyntax().setFont(new Font(fonteEscolhida, Font.PLAIN, tamanhoFonte));
		}
		fonteAtual.setText(fonteEscolhida + " / Font.PLAIN / " + tamanhoFonte + "  |   ");
	}

	/**
	* Classe interna que implementa ActionListener
	* Esta classe chama o JDialog que contém informações sobre o JCE
	*/
	class VersaoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			new VersaoDialog();
		}
	}

	/**
	* Classe interna que implementa ActionListener
	* Esta classe é responsável pelo evento de recortar a seleção do JTextArea atual
	*/
	class RecortarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().cut();
		}
	}

	/**
	* Classe interna que implementa ActionListener
	* Esta classe é responsável pelo evento de copiar a seleção do JTextArea atual
	*/
	class CopiarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().copy();
		}
	}

	/**
	* Classe interna que implementa ActionListener
	* Esta classe é responsável pelo evento de colar da área de transferência o texto no JTextArea atual
	*/
	class ColarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().paste();
		}
	}

	/**
	* Classe interna que implementa ActionListener
	* Esta classe apenas fecha o programa
	*/
	class SairListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			salvarAoSair();
			System.exit(0);
		}
	}

	/**
	* Classe interna que implementa ActionListener
	* Esta classe é responsável por abrir o arquivo selecionado pelo usuário em uma nova aba,
	* também adiciona os eventos que possibilitam a função de arrastar e soltar.
	* Além de configurar a fonte e definir o título.
	*/
	class AbrirListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			at = new AreaDeTexto();
			lista.add(at);
			arquivos.addTab("Sem nome", at);
			arquivos.setSelectedIndex(lista.size() - 1);
			int i = arquivos.getSelectedIndex();
			arquivos.setTabComponentAt(i, new ButtonTabComponent(arquivos, lista));
			carregarTema(sTema);

			if (lista.get(arquivos.getSelectedIndex()).fileChooser().showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
				lista.remove(arquivos.getSelectedIndex());
				arquivos.remove(arquivos.getSelectedIndex());
				return;
			}

			lista.get(arquivos.getSelectedIndex()).abrir(lista.get(arquivos.getSelectedIndex()).fileChooser().getSelectedFile());
			lista.get(arquivos.getSelectedIndex()).arquivoModificado(false);
			adicionarDocumentListener();
			updateFonte();
			arquivos.setTitleAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).arquivo.getName());
			arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).arquivo.toString());
			linguagem.setText(lista.get(arquivos.getSelectedIndex()).linguagem + "   ");
			definirTitulo();
			arrastarESoltar();

			if (lista.get(arquivos.getSelectedIndex()).isPotigol) {
				bExecutarPotigol.setEnabled(true);
			}
		}
	}

	/**
	* Classe é responsável por abrir o arquivo selecionado pelo usuário.
	* Antes de salvar, é feita uma verificação para constatar se o arquivo foi ou não modificado,
	* se sim, ele apenas salva levando em consideração o caminho do arquivo existente, caso contrário,
	* o usuário deverá informar um caminho. Em seguida, é definido a linguagem do JLabel e o título do JFrame.
	*/
	class SalvarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).texto = lista.get(arquivos.getSelectedIndex()).getRSyntax().getText();
			if (lista.get(arquivos.getSelectedIndex()).arquivo == null) {
				lista.get(arquivos.getSelectedIndex()).salvarComo();
			} else {
				if (lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
					lista.get(arquivos.getSelectedIndex()).salvar(lista.get(arquivos.getSelectedIndex()).getRSyntax().getText());
				}
			}

			arquivos.setTitleAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).arquivo.getName());
			arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).arquivo.toString());
			linguagem.setText(lista.get(arquivos.getSelectedIndex()).linguagem + "   ");
			definirTitulo();

			if (lista.get(arquivos.getSelectedIndex()).isPotigol) {
				bExecutarPotigol.setEnabled(true);
			}
		}
	}

	/**
	* Responsável por salvar o arquivo mesmo se ele já existe ou não foi modificado (recurso "salvar como")
	*/
	class SalvarComoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).texto = lista.get(arquivos.getSelectedIndex()).getRSyntax().getText();
			if (lista.get(arquivos.getSelectedIndex()).arquivo == null) {
				lista.get(arquivos.getSelectedIndex()).salvarComo();
				lista.get(arquivos.getSelectedIndex()).arquivo = null;
				lista.get(arquivos.getSelectedIndex()).arquivoModificado(true);
			} else {
				auxArquivo = lista.get(arquivos.getSelectedIndex()).arquivo.toString();
				lista.get(arquivos.getSelectedIndex()).salvarComo();
				lista.get(arquivos.getSelectedIndex()).arquivo = new File(auxArquivo);
			}
		}
	}

	/**
	* Classe que tem como função criar uma aba com um JTextArea vazio.
	* Também possui códigos para definição de sintaxe e nome da linguagem.
	*/
	class NovoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			at = new AreaDeTexto();
			lista.add(at);
			arquivos.addTab("Sem nome", at);
			arquivos.setSelectedIndex(lista.size() - 1);

			int i = arquivos.getSelectedIndex();
			arquivos.setTabComponentAt(i, new ButtonTabComponent(arquivos, lista));

			lista.get(arquivos.getSelectedIndex()).verificarNome();

			lista.get(arquivos.getSelectedIndex()).getRSyntax().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
			carregarTema(sTema);

			if (lista.get(arquivos.getSelectedIndex()).nomeIgual = true) {
				lista.get(arquivos.getSelectedIndex()).linguagem = "Texto simples";
			}

			adicionarDocumentListener();
			arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), "Sem nome");
			linguagem.setText(lista.get(arquivos.getSelectedIndex()).linguagem + "   ");
			definirTitulo();
			bg2.clearSelection();
			arrastarESoltar();
			updateFonte();
		}
	}

	/**
	* Abre o JDialog que realiza a pesquisa por palavras no JTextArea da aba atual
	*/
	class PesquisarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			new Pesquisar(lista.get(arquivos.getSelectedIndex()).getRSyntax(), JCEditor.this);
		}
	}

	/**
	* Abre o JDialog que mostra informações sobre o computador (SO, arquitetura, versão do Java, etc.)
	*/
	class SobrePCListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			new PropriedadesSistema();
		}
	}

	/**
	* Responsável pelo evento de definir a fonte como padrão (tamanho 12)
	*/
	class FontePadraoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			tamanhoFonte = 12;
			updateFonte();
		}
	}

	/**
	* Responsável pelo evento de aumentar a fonte
	*/
	class AumentarFonteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			tamanhoFonte += 3;
			updateFonte();
		}
	}

	/**
	* Responsável pelo evento de diminuir a fonte
	*/
	class DiminuirFonteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (tamanhoFonte != 9) {
				tamanhoFonte -= 3;
				updateFonte();
			}
		}
	}

	/**
	* Define o Look And Feel padrão do editor (Nimbus, mas que possui alterações na JScrollBar),
	* a String "sLAF" é utilizada pela classe MainClass para carregar o LAF toda
	* vez que o programa for iniciado. A classe PainterScrollBar é utilizada para criar
	* a aparência barra.
	*/
	class LAFPadraoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
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
				atualizarLAF();
				sLAF = "jce";
			} catch (Exception ex) {  }
			updateFonte();
		}
	}

	/**
	* Utiliza uma String passada como argumento no construtor para definir o Look And Feel
	* e depois atualiza o mesmo.
	*/
	class LAFListener implements ActionListener {
		private String laf;

		public LAFListener(String laf) {
			this.laf = laf;
		}

		public void actionPerformed(ActionEvent ev) {
			try {
				UIManager.setLookAndFeel(laf);
				atualizarLAF();
			} catch (Exception ex) {  }
			sLAF = this.laf;

			updateFonte();
		}
	}

	/**
	* Evento responsável por capturar a fonte escolhida pelo usuário e a aplicar aos JTextArea(s)
	*/
	class EscolherFonteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object[] nomesFonte = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

			fonteEscolhida = (String) JOptionPane.showInputDialog(JCEditor.this, "Escolha a fonte", "Fonte", JOptionPane.PLAIN_MESSAGE, null,
				nomesFonte, "");
			if (fonteEscolhida == null) {
				lista.get(arquivos.getSelectedIndex()).setFont(new Font(fonteEscolhida, Font.PLAIN, tamanhoFonte));
			} else {
				updateFonte();
			}
		}
	}

	/**
	* Responsável por carregar o tema e aplicá-lo aos JTextArea(s)
	*/
	class TemaListener implements ActionListener {
		String nomeDoTema;

		public TemaListener(String n) {
			this.nomeDoTema = n;
		}

		public void actionPerformed(ActionEvent ev) {
			carregarTema(nomeDoTema);
			updateFonte();
		}
	}

	/**
	* Responsável por definir o nome da linguagem (JLabel do canto inferior direito) e sintaxe
	* do JTextArea da aba atual. Se o JRadioButtonMenuItem "gerarEstrutura" estiver selecionado
	* e o arquivo ainda não existir, será gerada a estrutura básica de determinadas linguagens.
	*/
	class LinguagemListener implements ActionListener {
		private String nomeLinguagem;
		private String sintaxe;

		public LinguagemListener(String nomeLinguagem, String sintaxe) {
			this.nomeLinguagem = nomeLinguagem;
			this.sintaxe = sintaxe;
		}

		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().setSyntaxEditingStyle(sintaxe);
			updateLanguage(nomeLinguagem);

			if (lista.get(arquivos.getSelectedIndex()).arquivo == null && gerarEstrutura.isSelected()) {
				new GerarEstrutura(lista.get(arquivos.getSelectedIndex()).getRSyntax(), nomeLinguagem);
			}

			bExecutarPotigol.setEnabled(false);
		}
	}

	/**
	* Classe necessária para adicionar suporte à linguagem Portugol.
	*/
	class PortugolListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
			atmf.putMapping("text/portugol", "com.cristian.PortugolTokenMaker");
			lista.get(arquivos.getSelectedIndex()).getRSyntax().setSyntaxEditingStyle("text/portugol");
			updateLanguage("Portugol");

			if (lista.get(arquivos.getSelectedIndex()).arquivo == null && gerarEstrutura.isSelected()) {
				new GerarEstrutura(lista.get(arquivos.getSelectedIndex()).getRSyntax(), "Portugol");
			}

			bExecutarPotigol.setEnabled(false);
		}
	}

	/**
	* Classe necessária para adicionar suporte à linguagem Potigol. Verifica se o arquivo existe
	* e se o índice é um código em Potigol (através da variável "isPotigol"), em caso positivo,
	* libera a execução do código.
	*/
	class PotigolListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
			atmf.putMapping("text/potigol", "com.cristian.PotigolTokenMaker");
			lista.get(arquivos.getSelectedIndex()).getRSyntax().setSyntaxEditingStyle("text/potigol");
			updateLanguage("Potigol");

			if (lista.get(arquivos.getSelectedIndex()).arquivo != null && lista.get(arquivos.getSelectedIndex()).isPotigol) {
				bExecutarPotigol.setEnabled(true);
			}
		}
	}

	/**
	* Classe responsável por executar o código em Potigol. Antes de mais nada
	* é verificado se o arquivo foi modificado, em caso potivo o arquivo é salvo.
	* Em seguida, verifica-se o sistema operacional para decidir qual arquivo de
	* execução do Potigol irá ser executado. São necessários alguns pequenos ajustes
	* dependendo do SO.
	*/
	class ExecutarPotigolListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			if (lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
				lista.get(arquivos.getSelectedIndex()).salvar(lista.get(arquivos.getSelectedIndex()).getRSyntax().getText());
				definirTitulo();
			}

			if (lista.get(arquivos.getSelectedIndex()).isPotigol && lista.get(arquivos.getSelectedIndex()).arquivo != null) {
				Runtime rt = Runtime.getRuntime();
				Process p;
				String cmd;
				String usuario = System.getProperty("user.home") + "/ConfigJCE/.potigol";

				if (System.getProperty("os.name").equals("Linux")) {
					try {
						cmd = usuario + "/ExecPotigol.sh " + lista.get(arquivos.getSelectedIndex()).arquivo.toString().replace(" ", "?")
							+ " " + lista.get(arquivos.getSelectedIndex()).arquivo.getName();
						p = rt.exec(cmd);
					} catch (Exception ex) { ex.printStackTrace(); }
				} else {
					try {
						cmd = "cmd.exe /c start " + usuario + "/ExecPotigol.bat " +
							"\"" + lista.get(arquivos.getSelectedIndex()).arquivo.toString() + "\" " + lista.get(arquivos.getSelectedIndex()).arquivo.getName();
						p = rt.exec(cmd);
					} catch (Exception ex) { ex.printStackTrace(); }
				}
			}
		}
	}
	
	/**
	* Classe responsável por abrir o dialógo de impressão e realizá-la.
	*/
	class ImprimirPotigolListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				Boolean c = lista.get(arquivos.getSelectedIndex()).getRSyntax().print();
			} catch (Exception ex) { ex.printStackTrace(); }
		}
	}

	/**
	* Classe responsável pela ação de fechar a aba. Se torna mais simples que o evento
	* de fechar pelo botão "X", pois o usuário só poderá fechar a aba se o índice selecionado
	* for ela mesma, logo, só foi verificado o índice da aba atual (getSelectedIndex()).
	*/
	class FecharAbaListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			int indice = arquivos.getSelectedIndex();
			if (indice != -1 && lista.size() != 1) {
				if (lista.get(indice).arquivoModificado()) {
					int r = JOptionPane.showConfirmDialog(null, "Você deseja salvar o arquivo?",
						"Fechar", JOptionPane.YES_NO_OPTION);
					if (r == JOptionPane.OK_OPTION) {
						lista.get(indice).texto = lista.get(indice).getRSyntax().getText();
						if (lista.get(indice).arquivo == null) {
							lista.get(indice).salvarComo();
						} else {
							lista.get(indice).salvar(lista.get(indice).getRSyntax().getText());
						}
					}
				}
				lista.remove(indice);
				arquivos.remove(indice);
			}
		}
	}

	/**
	* Evento que abre a página da linguagem Potigol
	*/
	class SobrePotigolListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				Desktop.getDesktop().browse(URI.create("http://potigol.github.io"));
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Não foi possível abrir a página.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}