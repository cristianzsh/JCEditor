package com.cristian;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
* Classe responsável por criar a árvore de gerenciamento de projetos
* @author   Cristian Henrique (cristianmsbr@gmail.com)
* @version  1.5
* @since    Terceira atualização
*/

public class ArvoreDeProjetos extends JPanel {
	File arq;
	JTree arvore;
	private Map<String, String> arquivos = new HashMap<>();
	private ArrayList<String> projetosList = new ArrayList<>();
	private DefaultMutableTreeNode pai;
	private File dir = new File(System.getProperty("user.home") + "/ConfigJCE/projetos.list");

	public ArvoreDeProjetos() {
		pai = new DefaultMutableTreeNode("root");
		arvore = new JTree(pai);
		arvore.setRootVisible(false);

		arvore.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				TreePath tp = arvore.getPathForLocation(ev.getX(), ev.getY());
				if (tp != null) {
					arq = new File(arquivos.get(arvore.getLastSelectedPathComponent().toString()));
				}
			}
		});

		DropTarget dt = new DropTarget(arvore, new DropTargetListener() {
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
							adicionarFilhos(arquivoD);
						}
					}

				} catch (Exception ex) {  }
			}
		});

		listarProjetos();
		this.setLayout(new BorderLayout());
		this.add(arvore, BorderLayout.CENTER);
		this.add(arvore);
	}

	public void adicionarFilhos(File diretorio) {
		DefaultMutableTreeNode filho = new DefaultMutableTreeNode(diretorio.getName());
		pai.add(filho);
		((DefaultTreeModel) arvore.getModel()).insertNodeInto(filho, pai, 0);
		((DefaultTreeModel) arvore.getModel()).reload();
		listarArquivos(diretorio.toString(), filho, true);
		projetosList.add(diretorio.toString());
		repaint();
	}

	public void listarProjetos() {
		try {
			FileReader fr = new FileReader(dir);
			BufferedReader leitor = new BufferedReader(fr);
			String linha = null;

			while ((linha = leitor.readLine()) != null) {
				adicionarFilhos(new File(linha));
			}

			leitor.close();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	public void salvarProjetos() {
		try {
			FileWriter fw = new FileWriter(dir);
			for (String s : projetosList) {
				fw.write(s + "\n");
			}
			fw.close();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	public void listarArquivos(String caminho, DefaultMutableTreeNode principal, Boolean recursivo) {
		File[] filhos = new File(caminho).listFiles();

		for (int i = 0; i < filhos.length; i++) {
			DefaultMutableTreeNode no = new DefaultMutableTreeNode(filhos[i].getName());
			arquivos.put(filhos[i].getName(), filhos[i].toString());
			if (filhos[i].isDirectory() && recursivo) {
				principal.add(no);
				listarArquivos(filhos[i].getPath(), no, recursivo);
			} else if (!filhos[i].isDirectory()) {
				principal.add(no);
			}
		}
	}
}