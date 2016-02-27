package com.cristian;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.TreePath;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;

/**
* Classe que cria a interface principal e manipula parte dos eventos
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  2.0
* @since    Desde a primeira versão
*/

public class JCEditor extends JFrame {
	private AreaDeTexto at;
	private JTabbedPane arquivos;
	private Font roboto = new Font("Roboto Light", Font.PLAIN, 14);
	private JLabel separador = new JLabel("   ");
	private JLabel separador2 = new JLabel("   ");
	private JLabel fonteAtual, linguagem;
	private JToolBar barraS;
	private JMenuItem novoArq, salvarArq, salvarArqComo, abrirArq, addProjeto, sair, recortar, copiar, colar, versao, sobrePC, fonte, pesquisar, fontePadrao, aumentarFonte,
		diminuirFonte, executarPotigol, imprimir, fecharAba, sobrePotigol, delProjeto, props, desfazer, refazer, selecionarTudo;
	private JRadioButtonMenuItem java, cPlusPlus, pythonL, html, css, javaScript, xml, c, unixShell, properties, groovy, jsp,
		actionScript, assembly, clojure, d, delphi, fortran, json, latex, lisp, lua, perl, php, ruby, scala, portugol, pascal, potigol, cSharp, vb, batch, plainText;
	private JRadioButtonMenuItem gerarEstrutura, dobrarCodigo, quebrarLinha;
	private JMenuBar barraDeMenu;
	private JMenu menu, editar, sobre, preferencias, lookAndFeel, formatar, linguagemMenu, tema, projeto;
	private InputStream in;
	private JButton bNovo, bAbrir, bSalvar, bSalvarComo, bCopiar, bColar, bRecortar, bPesquisar, bExecutarPotigol, bImprimir, bDesfazer, bRefazer;
	private Image icone;
	private ButtonGroup bg, bg2, bg3;
	private String fonteEscolhida = "Monospaced";
	private int tamanhoFonte = 12;
	private String titulo, auxArquivo, auxLinguagem;
	public String sLAF, sTema;
	private ArrayList<AreaDeTexto> lista = new ArrayList<>();
	private ArrayList<String> arquivosAbertos = new ArrayList<>();
	private JRadioButtonMenuItem[] menusAparencia = new JRadioButtonMenuItem[14];
	private JScrollPane scrollPane;
	private JSplitPane painelSeparador, painelPrincipal;
	private ArvoreDeProjetos adp;
	private TerminalPotigol terminal;
	private String sistemaOperacional = System.getProperty("os.name");

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
		projeto = new JMenu("Projeto");
		formatar = new JMenu("Formatar");
		linguagemMenu = new JMenu("Linguagem");
		preferencias = new JMenu("Preferências");
		lookAndFeel = new JMenu("LAF");
		tema = new JMenu("Tema");
		sobre = new JMenu("Sobre");

		menu.setMnemonic('A');
		editar.setMnemonic('E');
		projeto.setMnemonic('R');
		sobre.setMnemonic('S');
		formatar.setMnemonic('F');
		preferencias.setMnemonic('P');
		linguagemMenu.setMnemonic('L');

		/* Cria a primeira aba do programa, esta aba é adicionada a uma ArrayList. */
		lista.add(at);
		arquivos.addTab("Sem nome", at);
		arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), "Sem nome");

		int i = arquivos.getSelectedIndex();	// índice a aba atual
		arquivos.setTabComponentAt(i, new ButtonTabComponent(arquivos, lista, arquivosAbertos));	// adiciona o botão de fechar à aba
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
		novoArq = configMenu("Novo", "imagens/novo.png", new NovoListener(), KeyEvent.VK_N, ActionEvent.CTRL_MASK, menu);
		abrirArq = configMenu("Abrir", "imagens/abrir.png", new AbrirListener(), KeyEvent.VK_O, ActionEvent.CTRL_MASK, menu);
		salvarArq = configMenu("Salvar", "imagens/salvar.png", new SalvarListener(), KeyEvent.VK_S, ActionEvent.CTRL_MASK, menu);
		salvarArqComo = configMenu("Salvar como", "imagens/salvarComo.png", new SalvarComoListener(), KeyEvent.VK_S, Event.CTRL_MASK | Event.SHIFT_MASK, menu);
		menu.addSeparator();
		imprimir = configMenu("Imprimir", "imagens/imprimir.png", new ImprimirPotigolListener(), KeyEvent.VK_P, ActionEvent.CTRL_MASK, menu);
		executarPotigol = configMenu("Executar Potigol", "imagens/play.png", new ExecutarPotigolListener(), KeyEvent.VK_F9, 0, menu);
		fecharAba = configMenu("Fechar aba", "imagens/fecharAba.png", new FecharAbaListener(), KeyEvent.VK_W, ActionEvent.CTRL_MASK, menu);
		menu.addSeparator();
		sair = configMenu("Sair", "imagens/sair.png", new SairListener(), KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK, menu);
		desfazer = configMenu("Desfazer", "imagens/desfazer.png", new DesfazerListener(), KeyEvent.VK_Z, ActionEvent.CTRL_MASK, editar);
		refazer = configMenu("Refazer", "imagens/refazer.png", new RefazerListener(), KeyEvent.VK_Y, ActionEvent.CTRL_MASK, editar);
		editar.addSeparator();
		recortar = configMenu("Recortar", "imagens/recortar.png", new RecortarListener(), KeyEvent.VK_X, ActionEvent.CTRL_MASK, editar);
		copiar = configMenu("Copiar", "imagens/copiar.png", new CopiarListener(), KeyEvent.VK_C, ActionEvent.CTRL_MASK, editar);
		colar = configMenu("Colar", "imagens/colar.png", new ColarListener(), KeyEvent.VK_V, ActionEvent.CTRL_MASK, editar);
		editar.addSeparator();
		selecionarTudo = configMenu("Selecionar tudo", "imagens/selecionarTudo.png", new SelecionarTudoListener(), KeyEvent.VK_A, ActionEvent.CTRL_MASK, editar);
		sobrePotigol = configMenu("Potigol", "imagens/potigol.png", new SobrePotigolListener(), KeyEvent.VK_I, ActionEvent.CTRL_MASK, sobre);
		sobrePC = configMenu("Sobre este PC", "imagens/config.png", new SobrePCListener(), KeyEvent.VK_F3, 0, sobre);
		versao = configMenu("Versão", "imagens/versaoIcone.png", new VersaoListener(), KeyEvent.VK_F1, 0, sobre);
		pesquisar = configMenu("Pesquisar","imagens/pesquisar.png", new PesquisarListener(), KeyEvent.VK_F, ActionEvent.CTRL_MASK, formatar);
		fonte = configMenu("Fonte", "imagens/fonte.png", new EscolherFonteListener(), KeyEvent.VK_R, ActionEvent.CTRL_MASK, formatar);
		formatar.addSeparator();
		fontePadrao = configMenu("Normal", "imagens/fontePadrao.png", new FontePadraoListener(), KeyEvent.VK_0, Event.CTRL_MASK, formatar);
		aumentarFonte = configMenu("Aumentar", "imagens/aumentarFonte.png", new AumentarFonteListener(), KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK, formatar);
		diminuirFonte = configMenu("Diminuir", "imagens/diminuirFonte.png", new DiminuirFonteListener(), KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK, formatar);
		formatar.addSeparator();
		addProjeto = configMenu("Adicionar", "imagens/addProjeto.png", new AddProjetoListener(), KeyEvent.VK_O, Event.CTRL_MASK | Event.SHIFT_MASK, projeto);
		delProjeto = configMenu("Remover", "imagens/remover.png", new RemoverProjetoListener(), KeyEvent.VK_D, Event.CTRL_MASK | Event.SHIFT_MASK, projeto);
		props = configMenu("Propriedades", "imagens/propriedades.png", new PropriedadesProjetoListener(), KeyEvent.VK_A, Event.CTRL_MASK | Event.SHIFT_MASK, projeto);

		/* Código de configuração dos menus de Look And Feel
		Sua estrutura é semelhante a do método "configMenu" exceto por utilizar um ButtonGroup(para que
		só exista um botão selecionado) e também pelo fato de não existir um ícone */
		bg = new ButtonGroup();
		menusAparencia[0] = configRadioMenus("JCE", new LAFPadraoListener(), bg, lookAndFeel);
		menusAparencia[1] = configRadioMenus("Nimbus", new LAFListener("javax.swing.plaf.nimbus.NimbusLookAndFeel"), bg, lookAndFeel);
		menusAparencia[2] = configRadioMenus("Metal", new LAFListener("javax.swing.plaf.metal.MetalLookAndFeel"), bg, lookAndFeel);
		menusAparencia[3] = configRadioMenus("Sistema", new LAFListener(UIManager.getSystemLookAndFeelClassName()), bg, lookAndFeel);
		menusAparencia[4] = configRadioMenus("Motif", new LAFListener("com.sun.java.swing.plaf.motif.MotifLookAndFeel"), bg, lookAndFeel);

		/* Código de configuração dos menus de temas */
		bg3 = new ButtonGroup();
		menusAparencia[5] = configRadioMenus("JCE", new TemaListener("jce"), bg3, tema);
		menusAparencia[6] = configRadioMenus("Dark", new TemaListener("dark"), bg3, tema);
		menusAparencia[7] = configRadioMenus("Dark II", new TemaListener("darkii"), bg3, tema);
		menusAparencia[8] = configRadioMenus("Default", new TemaListener("default"), bg3, tema);
		menusAparencia[9] = configRadioMenus("Default-Alt", new TemaListener("default-alt"), bg3, tema);
		menusAparencia[10] = configRadioMenus("Eclipse", new TemaListener("eclipse"), bg3, tema);
		menusAparencia[11] = configRadioMenus("IDEA", new TemaListener("idea"), bg3, tema);
		menusAparencia[12] = configRadioMenus("IDLE", new TemaListener("idle"), bg3, tema);
		menusAparencia[13] = configRadioMenus("Visual Studio", new TemaListener("vs"), bg3, tema);

		gerarEstrutura = new JRadioButtonMenuItem("Gerar estrutura");
		gerarEstrutura.setIcon(new ImageIcon(getClass().getResource("imagens/estrutura.png")));
		gerarEstrutura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		formatar.add(gerarEstrutura);

		/* Código de configuração dos menus de linguagem, este método também é utilizado para a configuração dos itens de LAF e tema
		Sua estrutura é composta por: JRadioButtonMenuItem, String(nome no item de menu), ActionListener(recebe como
		argumento uma String contendo o nome da linguagem e sua sintaxe), ButtonGroup, JMenu */
		bg2 = new ButtonGroup();
		actionScript = configRadioMenus("ActionScript", new LinguagemListener("ActionScript", SyntaxConstants.SYNTAX_STYLE_ACTIONSCRIPT), bg2, linguagemMenu);
		assembly = configRadioMenus("Assembly", new LinguagemListener("Assembly", SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86), bg2, linguagemMenu);
		batch = configRadioMenus("Batch", new LinguagemListener("Windows Batch", SyntaxConstants.SYNTAX_STYLE_WINDOWS_BATCH), bg2, linguagemMenu);
		clojure = configRadioMenus("Clojure", new LinguagemListener("Clojure", SyntaxConstants.SYNTAX_STYLE_CLOJURE), bg2, linguagemMenu);
		css = configRadioMenus("CSS", new LinguagemListener("CSS", SyntaxConstants.SYNTAX_STYLE_CSS), bg2, linguagemMenu);
		c = configRadioMenus("C", new LinguagemListener("C", SyntaxConstants.SYNTAX_STYLE_C), bg2, linguagemMenu);
		cPlusPlus = configRadioMenus("C++", new LinguagemListener("C++", SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS), bg2, linguagemMenu);
		cSharp = configRadioMenus("C#", new LinguagemListener("C#", SyntaxConstants.SYNTAX_STYLE_CSHARP), bg2, linguagemMenu);
		d = configRadioMenus("D", new LinguagemListener("D", SyntaxConstants.SYNTAX_STYLE_D), bg2, linguagemMenu);
		delphi = configRadioMenus("Delphi", new LinguagemListener("Delphi", SyntaxConstants.SYNTAX_STYLE_DELPHI), bg2, linguagemMenu);
		fortran = configRadioMenus("Fortran", new LinguagemListener("Fortran", SyntaxConstants.SYNTAX_STYLE_FORTRAN), bg2, linguagemMenu);
		groovy = configRadioMenus("Groovy", new LinguagemListener("Groovy", SyntaxConstants.SYNTAX_STYLE_GROOVY), bg2, linguagemMenu);
		html = configRadioMenus("HTML", new LinguagemListener("HTML", SyntaxConstants.SYNTAX_STYLE_HTML), bg2, linguagemMenu);
		java = configRadioMenus("Java", new LinguagemListener("Java", SyntaxConstants.SYNTAX_STYLE_JAVA), bg2, linguagemMenu);
		javaScript = configRadioMenus("JavaScript", new LinguagemListener("JavaScript", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT), bg2, linguagemMenu);
		jsp = configRadioMenus("Java Server Pages", new LinguagemListener("Java Server Pages", SyntaxConstants.SYNTAX_STYLE_JSP), bg2, linguagemMenu);
		json = configRadioMenus("JSON", new LinguagemListener("JSON", SyntaxConstants.SYNTAX_STYLE_JSON), bg2, linguagemMenu);
		latex = configRadioMenus("LaTex", new LinguagemListener("LaTex", SyntaxConstants.SYNTAX_STYLE_LATEX), bg2, linguagemMenu);
		lisp = configRadioMenus("Lisp", new LinguagemListener("Lisp", SyntaxConstants.SYNTAX_STYLE_LISP), bg2, linguagemMenu);
		lua = configRadioMenus("Lua", new LinguagemListener("Lua", SyntaxConstants.SYNTAX_STYLE_LUA), bg2, linguagemMenu);
		pascal = configRadioMenus("Pascal", new LinguagemListener("Pascal", SyntaxConstants.SYNTAX_STYLE_DELPHI), bg2, linguagemMenu);
		perl = configRadioMenus("Perl", new LinguagemListener("Perl", SyntaxConstants.SYNTAX_STYLE_PERL), bg2, linguagemMenu);
		php = configRadioMenus("PHP", new LinguagemListener("PHP", SyntaxConstants.SYNTAX_STYLE_PHP), bg2, linguagemMenu);
		plainText = configRadioMenus("Plain text", new LinguagemListener("Texto simples", SyntaxConstants.SYNTAX_STYLE_NONE), bg2, linguagemMenu);
		portugol = configRadioMenus("Portugol", new PortugolListener(), bg2, linguagemMenu);
		potigol = configRadioMenus("Potigol", new PotigolListener(), bg2, linguagemMenu);
		properties = configRadioMenus("Properties", new LinguagemListener("Properties", SyntaxConstants.SYNTAX_STYLE_PROPERTIES_FILE), bg2, linguagemMenu);
		pythonL = configRadioMenus("Python", new LinguagemListener("Python", SyntaxConstants.SYNTAX_STYLE_PYTHON), bg2, linguagemMenu);	
		ruby = configRadioMenus("Ruby", new LinguagemListener("Ruby", SyntaxConstants.SYNTAX_STYLE_RUBY), bg2, linguagemMenu);
		scala = configRadioMenus("Scala", new LinguagemListener("Scala", SyntaxConstants.SYNTAX_STYLE_SCALA), bg2, linguagemMenu);
		vb = configRadioMenus("Visual Basic", new LinguagemListener("Visual Basic", SyntaxConstants.SYNTAX_STYLE_VISUAL_BASIC), bg2, linguagemMenu);
		unixShell = configRadioMenus("Unix Shell", new LinguagemListener("Unix Shell", SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL), bg2, linguagemMenu);
		xml = configRadioMenus("XML", new LinguagemListener("XML", SyntaxConstants.SYNTAX_STYLE_XML), bg2, linguagemMenu);

		/* Código de configuração dos JButtons da JToolBar.
		O método recebe o botão a ser configurado, seu ToolTipText, seu ícone e seu listener */
		bNovo = configBtns("Novo arquivo", "imagens/25x25/novo25.png", new NovoListener());
		bAbrir = configBtns("Abrir arquivo", "imagens/25x25/abrir25.png", new AbrirListener());
		bSalvar = configBtns("Salvar arquivo", "imagens/25x25/salvar25.png", new SalvarListener());
		bSalvarComo = configBtns("Salvar como", "imagens/25x25/salvarComo25.png", new SalvarComoListener());
		barraS.add(separador);
		bCopiar = configBtns("Copiar", "imagens/25x25/copiar25.png", new CopiarListener());
		bColar = configBtns("Colar", "imagens/25x25/colar25.png", new ColarListener());
		bRecortar = configBtns("Recortar", "imagens/25x25/recortar25.png", new RecortarListener());
		bDesfazer = configBtns("Desfazer", "imagens/25x25/desfazer25.png", new DesfazerListener());
		bRefazer = configBtns("Refazer", "imagens/25x25/refazer25.png", new RefazerListener());
		bPesquisar = configBtns("Pesquisar", "imagens/25x25/pesquisar25.png", new PesquisarListener());
		barraS.add(separador2);
		bExecutarPotigol = configBtns("Executar Potigol", "imagens/25x25/play25.png", new ExecutarPotigolListener());
		bExecutarPotigol.setEnabled(false);
		bImprimir = configBtns("Imprimir", "imagens/25x25/imprimir25.png", new ImprimirPotigolListener());

		/* Define o tamanho do ícone com base no SO */
		if (sistemaOperacional.equals("Linux") || sistemaOperacional.equals("Mac OS X")) {
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

		linguagem = new JLabel(lista.get(arquivos.getSelectedIndex()).getLinguagem() + "   ");
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
					updateLanguage(lista.get(arquivos.getSelectedIndex()).getLinguagem());
				}

				if (lista.get(arquivos.getSelectedIndex()).isPotigol() && lista.get(arquivos.getSelectedIndex()).getArquivo() != null) {
					bExecutarPotigol.setEnabled(true);
				} else {
					bExecutarPotigol.setEnabled(false);
				}
			}
		};

		/* Adiciona o evento ChangeListener e o evento de arrastar e soltar */
		arquivos.addChangeListener(changeListener);
		arrastarESoltar();

		dobrarCodigo = new JRadioButtonMenuItem("Dobrar código");
		dobrarCodigo.setIcon(new ImageIcon(getClass().getResource("imagens/dobrarCodigo.png")));
		dobrarCodigo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		dobrarCodigo.addActionListener(new DobrarCodigoListener());

		quebrarLinha = new JRadioButtonMenuItem("Quebrar linha");
		quebrarLinha.setIcon(new ImageIcon(getClass().getResource("imagens/quebrarLinha.png")));
		quebrarLinha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		quebrarLinha.addActionListener(new QuebrarLinhaListener());

		preferencias.add(lookAndFeel);
		preferencias.add(tema);
		preferencias.addSeparator();
		preferencias.add(dobrarCodigo);
		preferencias.add(quebrarLinha);

		/* Adiciona os menus na barra principal */
		barraDeMenu.add(menu);
		barraDeMenu.add(editar);
		barraDeMenu.add(projeto);
		barraDeMenu.add(formatar);
		barraDeMenu.add(linguagemMenu);
		barraDeMenu.add(preferencias);
		barraDeMenu.add(sobre);

		/* Abre o arquivo selecionado na JTree quando o usuário clicar duas vezes sobre ele,
		exceto para pastas. */
		adp = new ArvoreDeProjetos();
		adp.getArvore().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				TreePath tp = adp.getArvore().getPathForLocation(ev.getX(), ev.getY());
				
				if (tp != null && !adp.getArq().isDirectory() && ev.getClickCount() == 2) {
					adicionarAba(adp.getArq());
					lista.get(arquivos.getSelectedIndex()).getRSyntax().requestFocus();
				}
			}
		});

		/* Atalho para fechar a aba. */
		arquivos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				if (ev.getButton() == MouseEvent.BUTTON2) {
					fecharAba(arquivos.getSelectedIndex());
				}
			}
		});

		scrollPane = new JScrollPane(adp);
		scrollPane.setBorder(null);
		painelSeparador = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, scrollPane, arquivos);
		painelSeparador.setDividerLocation(0);
		painelSeparador.setOneTouchExpandable(true);
		painelSeparador.setBorder(null);

		terminal = new TerminalPotigol();
		painelPrincipal = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, painelSeparador, terminal);
		painelPrincipal.setDividerLocation(600);
		painelPrincipal.setOneTouchExpandable(true);
		painelPrincipal.setBorder(null);

		getContentPane().add(BorderLayout.NORTH, barraS);
		getContentPane().add(BorderLayout.SOUTH, panel);	// apenas define o layout dos componentes
		getContentPane().add(BorderLayout.CENTER, painelPrincipal);
		this.setJMenuBar(barraDeMenu);
		this.setIconImage(icone);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(844, 635);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(468, 328));
	}

	/**
	* Método que define o nome do JFrame.
	* Leva em consideração se o arquivo existe e se foi modificado
	*/
	private void definirTitulo() {
		titulo = "Sem nome - JCEditor";

		if (lista.get(arquivos.getSelectedIndex()).getArquivo() != null && lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
			titulo = lista.get(arquivos.getSelectedIndex()).getArquivo().toString() + " •- JCEditor";
			arquivos.setTitleAt(arquivos.getSelectedIndex(), "• " + lista.get(arquivos.getSelectedIndex()).getArquivo().getName());
		} else if (lista.get(arquivos.getSelectedIndex()).getArquivo() != null) {
			titulo = lista.get(arquivos.getSelectedIndex()).getArquivo().toString() + " - JCEditor";
			arquivos.setTitleAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).getArquivo().getName());
		} else if (lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
			titulo = "Sem nome •- JCEditor";
			arquivos.setTitleAt(arquivos.getSelectedIndex(), "• Sem nome");
		}
		setTitle(titulo);
	}

	/**
	* Método que cria os menus para as funções do programa (copiar, colar, abrir, etc.)
	* @param nome String - nome que será dado ao JMenuItem
	* @param img String - caminho da imagem PNG do JMenuItem
	* @param ev ActionListener - evento que será executado ao pressionar o menu
	* @param ac int - accelerator (KeyEvent)
	* @param ac2 int - accelerator (ActionEvent)
	* @param principal JMenu - menu ao qual o JMenuItem pertence
	*/
	private JMenuItem configMenu(String nome , String img, ActionListener ev, int ac, int ac2, JMenu principal) {
		JMenuItem itemDeMenu = new JMenuItem(nome);
		itemDeMenu.setIcon(new ImageIcon(getClass().getResource(img)));
		itemDeMenu.addActionListener(ev);
		itemDeMenu.setFont(roboto);
		itemDeMenu.setAccelerator(KeyStroke.getKeyStroke(ac, ac2));
		principal.add(itemDeMenu);

		return itemDeMenu;
	}

	/**
	* Método que cria o ToolTipText, adiciona a imagem, ActionListener, efeito(classe EfeitoBtn) e,
	* por fim, adiciona o JButton na JToolBar (barraS)
	* @param toolTipText String - ToolTipText que será exibido
	* @param img String - caminho da imagem do JButton
	* @param ev ActionListener - evento que será executado ao pressionar o botão
	*/
	private JButton configBtns(String toolTipText, String img, ActionListener ev) {
		JButton btn = new JButton();
		btn.setToolTipText(toolTipText);
		btn.setIcon(new ImageIcon(getClass().getResource(img)));
		btn.addActionListener(ev);
		EfeitoBtn eb = new EfeitoBtn(btn);
		barraS.add(btn);

		return btn;
	}

	/**
	* Método que cria os JRadioButtonMenuItem(s)
	* @param nome String - nome do menu
	* @param ev ActionListener - evento que será executado ao pressionar o menu
	* @param bg ButtonGroup - grupo a qual o menu pertence (não é possível ter mais de um selecionado)
	* @param mPrincipal - menu ao qual o menu de rádio pertence
	*/
	private JRadioButtonMenuItem configRadioMenus(String nome, ActionListener ev, ButtonGroup bg, JMenu mPrincipal) {
		JRadioButtonMenuItem menu = new JRadioButtonMenuItem(nome);
		menu.addActionListener(ev);
		menu.setFont(roboto);
		bg.add(menu);

		mPrincipal.add(menu);
		return menu;
	}

	/**
	* Este método é chamado quando o usuário clica no botão fechar ou no menu sair. Percorre todos os
	* arquivos abertos em busca de modificações, em caso positivo, pergunta se o usuário deseja salvar
	* o arquivo. Também chama o método que salva o Look and Feel, tema, fonte e tamanho da fonte e o
	* método que salva uma lista com os arquivos abertos.
	*/
	private void salvarAoSair() {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).arquivoModificado()) {
				String nomeArquivo = null;
				if (lista.get(i).getArquivo() == null) {
					nomeArquivo = "Sem nome";
				} else {
					nomeArquivo = lista.get(i).getArquivo().getName();
				}
				int r = JOptionPane.showConfirmDialog(JCEditor.this, "Você deseja salvar o arquivo \"" + nomeArquivo + "\"?",
					"Sair", JOptionPane.YES_NO_CANCEL_OPTION);
				lista.get(i).setTexto(lista.get(i).getRSyntax().getText());
				if (r == JOptionPane.OK_OPTION) {
					if (lista.get(i).getArquivo() == null) {
						lista.get(i).salvarComo();
					} else {
						lista.get(i).salvar(lista.get(i).getRSyntax().getText());
					}
				} else if (r == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}
		}

		String dobCodigo = null, quebLinha = null;
		if (dobrarCodigo.isSelected()) {
			dobCodigo = "dobrarCodigo";
		}

		if (quebrarLinha.isSelected()) {
			quebLinha = "quebrarLinha";
		}

		new Preferencias().salvarPreferencias(sLAF, sTema, fonteEscolhida, tamanhoFonte, dobCodigo, quebLinha);
		new Preferencias().salvarArquivosAbertos(arquivosAbertos);
		adp.salvarProjetos();
		System.exit(0);
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

					for (int i = 0; i < lista2.size(); i++) {
						File arquivoD = (File) lista2.get(i);

						if (arquivoD.isDirectory()) {
							adp.adicionarFilhos(arquivoD);
							return;
						}

						adicionarAba(arquivoD);
					}

				} catch (Exception ex) {  }
			}
		});
	}

	/**
	* Adiciona uma aba ao JTabbedPane, também adiciona
	* os eventos que possibilitam a função de arrastar e soltar.
	* Além de configurar a fonte e definir o título.
	* @param arquivo File - arquivo que será adicionado
	*/
	public void adicionarAba(File arquivo) {
		for (int i = 0; i < lista.size(); i++) {
			if (arquivos.getTitleAt(i).equals(arquivo.getName())
				&& lista.get(i).getArquivo().toString().equals(arquivo.toString())) {
				arquivos.setSelectedIndex(i);
				return;
			}
		}

		if (!arquivo.exists()) {
			return;
		}

		at = new AreaDeTexto();
		lista.add(at);
		arquivos.addTab("Sem nome", at);
		arquivos.setSelectedIndex(lista.size() - 1);
		arquivos.setTabComponentAt(arquivos.getSelectedIndex(), new ButtonTabComponent(arquivos, lista, arquivosAbertos));
		arquivos.setTitleAt(arquivos.getSelectedIndex(), arquivo.getName());

		lista.get(arquivos.getSelectedIndex()).abrir(arquivo);
		lista.get(arquivos.getSelectedIndex()).getRSyntax().discardAllEdits();
		lista.get(arquivos.getSelectedIndex()).arquivoModificado(false);
		lista.get(arquivos.getSelectedIndex()).getRSyntax().setCaretPosition(0);

		arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), arquivo.toString());
		linguagem.setText(lista.get(arquivos.getSelectedIndex()).getLinguagem() + "   ");
		arquivosAbertos.add(arquivo.toString());
		carregarTema(sTema);

		if (lista.get(arquivos.getSelectedIndex()).isPotigol()) {
			bExecutarPotigol.setEnabled(true);
			completarPotigol();
		}
	}

	/**
	* Método que atualiza o LAF utilizando o método updateComponentTreeUI, da classe SwingUtilities
	* Este método também retira a borda da barra de menu e do JTextArea
	*/
	private void atualizarLAF() {
		SwingUtilities.updateComponentTreeUI(this);
		barraDeMenu.setBorder(null);
		painelPrincipal.setBorder(null);
		painelSeparador.setBorder(null);
		scrollPane.setBorder(null);
		terminal.getBarra().setBorder(null);

		for (AreaDeTexto adt : lista) {
			adt.setBorder(null);
			adt.barraDeRolagem().setBorder(null);
			SwingUtilities.updateComponentTreeUI(adt.fileChooser());
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

		if (dobrarCodigo.isSelected()) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().setCodeFoldingEnabled(true);
			lista.get(arquivos.getSelectedIndex()).barraDeRolagem().setFoldIndicatorEnabled(true);
		}

		if (quebrarLinha.isSelected()) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().setLineWrap(true);
			lista.get(arquivos.getSelectedIndex()).getRSyntax().setWrapStyleWord(true);
		}

		adicionarDocumentListener();
		updateFonte();
		arrastarESoltar();
		definirTitulo();
	}

	/**
	* Método que atualiza o nome da linguagem (exibido no canto inferior direito)
	* @param nome String - nome da linguagem
	*/
	private void updateLanguage(String nome) {
		lista.get(arquivos.getSelectedIndex()).setLinguagem(nome);
		linguagem.setText(lista.get(arquivos.getSelectedIndex()).getLinguagem() + "   ");
	}

	/**
	* Método que atualiza o tamanho da fonte e a própria fonte utilizada em
	* todos os campos de textos de todas as abas
	*/
	public void updateFonte() {
		for (AreaDeTexto adt : lista) {
			adt.getRSyntax().setFont(new Font(fonteEscolhida, Font.PLAIN, tamanhoFonte));
			adt.barraDeRolagem().getGutter().setLineNumberFont(new Font("Monospaced", Font.PLAIN, tamanhoFonte));
		}
		fonteAtual.setText(fonteEscolhida + " / Font.PLAIN / " + tamanhoFonte + "  |   ");
	}
	/**
	* Cria um JFileChooser para seleção de pasta e em seguida chama o método adicionarFilhos, da classe
	* ArvoreDeProjetos, que adiciona um nó a JTree contendo o projeto selecionado.
	*/
	private void abrirProjeto() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
			return;
		}
		adp.adicionarFilhos(new File(jfc.getSelectedFile().toString()));
	}

	/**
	* Utilizado na classe Preferencias, apenas remove o primeiro índice do JTabbedPane	
	* em caso de existirem arquivos a serem abertos.
	*/
	public void configAoAbrir() {
		lista.remove(0);
		arquivos.remove(0);
		definirTitulo();
		updateLanguage(lista.get(arquivos.getSelectedIndex()).getLinguagem());
	}

	/**
	* Retorna uma lista contendo todos os arquivos abertos.
	*/
	public ArrayList<String> getArquivosAbertos() {
		return this.arquivosAbertos;
	}

	/**
	* Configura a fonte.
	* @param f String - nova fonte
	*/
	public void setFonteEscolhida(String f) {
		this.fonteEscolhida = f;
	}

	/**
	* Configura o tamanho da fonte.
	* @param tam int - novo tamanho da fonte
	*/
	public void setTamanhoFonte(int tam) {
		this.tamanhoFonte = tam;
	}

	/**
	* Retorna o menu de dobramento de código.
	*/
	public JRadioButtonMenuItem getDobrarCodigo() {
		return this.dobrarCodigo;
	}

	/**
	* Retorna o menu de quebra de linha.
	*/
	public JRadioButtonMenuItem getQuebrarLinha() {
		return this.quebrarLinha;
	}

	/**
	* Retorna a matriz que contém os itens de menu que fazem
	* o controle da aparência (tema e LAF).
	*/
	public JRadioButtonMenuItem[] getMenusDeAparencia() {
		return this.menusAparencia;
	}

	/**
	* Método que fecha a aba informada. Antes de fechar a aba,
	* verifica se o arquivo foi modificado, em caso positivo,
	* pergunta se o usuário deseja salvar o mesmo.
	* @param indice int - índice da aba que será fechada
	*/
	private void fecharAba(int indice) {
		if (indice != -1 && lista.size() != 1) {
			if (lista.get(indice).arquivoModificado()) {
				int r = JOptionPane.showConfirmDialog(null, "Você deseja salvar o arquivo?",
					"Fechar", JOptionPane.YES_NO_CANCEL_OPTION);
				if (r == JOptionPane.OK_OPTION) {
					lista.get(indice).setTexto(lista.get(indice).getRSyntax().getText());
					if (lista.get(indice).getArquivo() == null) {
						lista.get(indice).salvarComo();
					} else {
						lista.get(indice).salvar(lista.get(indice).getRSyntax().getText());
					}
				} else if (r == JOptionPane.CANCEL_OPTION) {
					return;
				}
			}

			if (lista.get(indice).getArquivo() != null && !arquivosAbertos.isEmpty()) {
				arquivosAbertos.remove(lista.get(indice).getArquivo().toString());
			}
			lista.remove(indice);
			arquivos.remove(indice);
		}
	}

	private void completarPotigol() {
		CompletionProvider provedor = new AutocompletarPotigol().criar();
		AutoCompletion ac = new AutoCompletion(provedor);
		ac.setAutoActivationDelay(300);
		ac.setAutoActivationEnabled(true);
		ac.setAutoCompleteSingleChoices(false);
		ac.setParameterAssistanceEnabled(true);
		ac.setShowDescWindow(true);
		ac.install(lista.get(arquivos.getSelectedIndex()).getRSyntax());
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
		}
	}

	/**
	* Classe interna que implementa ActionListener
	* esta chama o método que adiciona uma aba ao JTabbedPane.
	*/
	class AbrirListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (lista.get(arquivos.getSelectedIndex()).fileChooser().showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
				return;
			}

			adicionarAba(lista.get(arquivos.getSelectedIndex()).fileChooser().getSelectedFile());
		}
	}

	/**
	* Esta classe é responsável por salvar o arquivo selecionado pelo usuário.
	* Antes de salvar, é feita uma verificação para constatar se o arquivo foi ou não modificado,
	* se sim, ele apenas salva levando em consideração o caminho do arquivo existente, caso contrário,
	* o usuário deverá informar um caminho. Em seguida, é definido a linguagem do JLabel e o título do JFrame.
	*/
	class SalvarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).setTexto(lista.get(arquivos.getSelectedIndex()).getRSyntax().getText());
			if (lista.get(arquivos.getSelectedIndex()).getArquivo() == null) {
				lista.get(arquivos.getSelectedIndex()).salvarComo();
				arquivosAbertos.add(lista.get(arquivos.getSelectedIndex()).getArquivo().toString());
			} else {
				if (lista.get(arquivos.getSelectedIndex()).arquivoModificado()) {
					lista.get(arquivos.getSelectedIndex()).salvar(lista.get(arquivos.getSelectedIndex()).getRSyntax().getText());
				}
			}

			arquivos.setTitleAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).getArquivo().getName());
			arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), lista.get(arquivos.getSelectedIndex()).getArquivo().toString());
			linguagem.setText(lista.get(arquivos.getSelectedIndex()).getLinguagem() + "   ");
			definirTitulo();

			if (lista.get(arquivos.getSelectedIndex()).isPotigol()) {
				bExecutarPotigol.setEnabled(true);
			}
		}
	}

	/**
	* Responsável por salvar o arquivo mesmo se ele já existe ou não foi modificado (recurso "salvar como")
	*/
	class SalvarComoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).setTexto(lista.get(arquivos.getSelectedIndex()).getRSyntax().getText());
			if (lista.get(arquivos.getSelectedIndex()).getArquivo() == null) {
				lista.get(arquivos.getSelectedIndex()).salvarComo();
				lista.get(arquivos.getSelectedIndex()).setArquivo(null);
				lista.get(arquivos.getSelectedIndex()).arquivoModificado(true);
				definirTitulo();
			} else {
				auxArquivo = lista.get(arquivos.getSelectedIndex()).getArquivo().toString();
				auxLinguagem = lista.get(arquivos.getSelectedIndex()).getLinguagem();

				File arquivoAnterior = new File(auxArquivo);
				lista.get(arquivos.getSelectedIndex()).salvarComo();
				lista.get(arquivos.getSelectedIndex()).setArquivo(arquivoAnterior);
				updateLanguage(auxLinguagem);
				lista.get(arquivos.getSelectedIndex()).extensao(arquivoAnterior);
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
			arquivos.setTabComponentAt(i, new ButtonTabComponent(arquivos, lista, arquivosAbertos));

			lista.get(arquivos.getSelectedIndex()).getRSyntax().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
			arquivos.setToolTipTextAt(arquivos.getSelectedIndex(), "Sem nome");
			linguagem.setText(lista.get(arquivos.getSelectedIndex()).getLinguagem() + "   ");
			bg2.clearSelection();
			carregarTema(sTema);
		}
	}

	/**
	* Abre a JToolBar que realiza a pesquisa por palavras no JTextArea da aba atual
	*/
	class PesquisarListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getBarraPesquisa().setVisible(true);
			lista.get(arquivos.getSelectedIndex()).getBarraPesquisa().getFieldPesquisar().requestFocus();
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
	* Evento que chama o método abrirProjeto.
	*/
	class AddProjetoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			abrirProjeto();
		}
	}

	/**
	* Chama o método que remove o projeto selecionado na JTree.
	*/
	class RemoverProjetoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			adp.removerProjeto();
		}
	}

	/**
	* Chama o método que mostra informações básicas sobre o projeto
	* (nome, quantidade total de arquivos e tamanho).
	*/
	class PropriedadesProjetoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			adp.propriedadesProjeto();
		}
	}

	/**
	* Evento responsável por capturar a fonte escolhida pelo usuário e a aplicar aos JTextArea(s)
	*/
	class EscolherFonteListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			Object[] nomesFonte = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			String auxFonte = fonteEscolhida;

			fonteEscolhida = (String) JOptionPane.showInputDialog(JCEditor.this, "Escolha a fonte", "Fonte", JOptionPane.PLAIN_MESSAGE, null,
				nomesFonte, "");
			if (fonteEscolhida == null) {
				fonteEscolhida = auxFonte;
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

			if (lista.get(arquivos.getSelectedIndex()).getArquivo() == null && gerarEstrutura.isSelected()) {
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

			if (lista.get(arquivos.getSelectedIndex()).getArquivo() == null && gerarEstrutura.isSelected()) {
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
			completarPotigol();

			if (lista.get(arquivos.getSelectedIndex()).getArquivo() != null && lista.get(arquivos.getSelectedIndex()).isPotigol()) {
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

			if (lista.get(arquivos.getSelectedIndex()).isPotigol() && lista.get(arquivos.getSelectedIndex()).getArquivo() != null) {
				terminal.executarComando(lista.get(arquivos.getSelectedIndex()).getArquivo());
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
	* Chama o método que fecha a aba atual.
	*/
	class FecharAbaListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			fecharAba(arquivos.getSelectedIndex());
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

	/**
	* Evento que define se o recurso de dobramento de código será ativado ou não.
	*/
	class DobrarCodigoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (!dobrarCodigo.isSelected()) {
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).barraDeRolagem().setFoldIndicatorEnabled(false);
					lista.get(i).getRSyntax().setCodeFoldingEnabled(false);
				}
			} else {
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).barraDeRolagem().setFoldIndicatorEnabled(true);
					lista.get(i).getRSyntax().setCodeFoldingEnabled(true);
				}
			}
		}
	}

	/**
	* Evento que define se o recurso de quebra de linha será ativado ou não.
	*/
	class QuebrarLinhaListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (!quebrarLinha.isSelected()) {
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).getRSyntax().setLineWrap(false);
					lista.get(i).getRSyntax().setWrapStyleWord(false);
				}
			} else {
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).getRSyntax().setLineWrap(true);
					lista.get(i).getRSyntax().setWrapStyleWord(true);
				}
			}
		}
	}

	/**
	* Evento de desfaz a última ação no JTextArea atual.
	*/
	class DesfazerListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().undoLastAction();
		}
	}

	/**
	* Evento de refaz a última ação no JTextArea atual.
	*/
	class RefazerListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().redoLastAction();	
		}
	}

	/**
	* Evento que faz a seleção de todo o texto na aba atual.
	*/
	class SelecionarTudoListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			lista.get(arquivos.getSelectedIndex()).getRSyntax().selectAll();
		}
	}
}