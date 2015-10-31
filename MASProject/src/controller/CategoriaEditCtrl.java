package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Categoria;
import persistence.CategoriaArquivo;

public class CategoriaEditCtrl implements ComponentListener {

	private JTextField nomeCategoria, idCategoria;
	private JButton btApagar, btGravar;
	private JLabel msgGravar, msgVazio;
	private List<Categoria> categorias;
	private static int contador = 1;

	private ArquivosCtrl ctrlArquivos;

	public CategoriaEditCtrl(JTextField idCartegoria, JTextField nomeCategoria,
			JButton btnApagar, JButton btnGravar, JLabel msgGravar, JLabel msgVazio) {

		this.idCategoria = idCartegoria;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		this.msgGravar = msgGravar;
		this.msgVazio = msgVazio;
		this.nomeCategoria = nomeCategoria;
		this.categorias = new ArrayList<Categoria>();
		
		lerCategoria();
	}
	
	public void lerCategoria() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		ctrlArquivos = new ArquivosCtrl();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "categorias");
			linha = ctrlArquivos.getBuffer();
			String[] listaCategoria = linha.split(";");
			for (String s : listaCategoria) {
				String text = s.replaceAll(".*:", "");
				list.add(text);
				if (s.contains("---")) {
					Categoria categoria = new Categoria();
					categoria.setId(list.get(0));
					categoria.setNome(list.get(1));
					categorias.add(categoria);
					list.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*for(Material a :  categorias){
			System.out.println(a.getNome());
		}*/
	}

	public void gravarCategoria() {
		Categoria categoria = new Categoria();
		CategoriaArquivo categoriaImpl = new CategoriaArquivo();

		categoria.setId(idCategoria.getText());
		categoria.setNome(nomeCategoria.getText());

		if (!nomeCategoria
				.getText().isEmpty()) {
			try {
				categoriaImpl.escreveArquivo("../MASProject/dados/", "categorias", nomeCategoria.getText(), categoria);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgGravar.setText(nomeCategoria.getText() + " salvo com sucesso!");
			msgGravar.setVisible(true);
			nomeCategoria.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	public void pesquisarCategoria() {

		if (!nomeCategoria.getText().isEmpty() || !idCategoria.getText().isEmpty()) {
			msgGravar.setText(nomeCategoria.getText() + " localizado com sucesso!");
			msgGravar.setVisible(true);
			nomeCategoria.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setText("Por favor, use um dos campos de Pesquisa!");
			msgVazio.setVisible(true);
		}
		
	}

	public void apagarCategoria() {
		
		if (!nomeCategoria.getText().isEmpty()) {
			msgGravar.setText(nomeCategoria.getText() + " excluído com sucesso!");
			msgGravar.setVisible(true);
			nomeCategoria.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}

	}

	public ActionListener pesquisarCategoria = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarCategoria();
		}
	};
	
	public ActionListener apagarCategoria = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			apagarCategoria();
		}
	};

	public ActionListener gravarCategoria = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarCategoria();
		}
	};

	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (contador == 1) {
				idCategoria.setText(null);
				nomeCategoria.setText(null);
				contador += 1;
			}

			btApagar.setEnabled(true);
			btGravar.setEnabled(true);
			msgGravar.setVisible(false);
			msgVazio.setVisible(false);
		}
	};

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
