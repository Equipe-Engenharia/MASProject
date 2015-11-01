package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Categoria;
import persistence.CategoriaArquivo;

public class CategoriaCtrl implements ComponentListener {

	private JTextField idCategoria, nomeCategoria;
	private JButton btGravar, btnApagar, btnIdPesquisa, btnCategoriaPesquisa;
	private JLabel msgGravado, msgVazio;
	private List<Categoria> categorias;
	private static int contador = 1;
	private ArquivosCtrl ctrlArquivos;

	public CategoriaCtrl(JTextField idCategoria, JTextField nomeCategoria,
			JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {

		this.idCategoria = idCategoria;
		this.btGravar = btnGravar;
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.nomeCategoria = nomeCategoria;
		this.categorias = new ArrayList<Categoria>();
		
		lerCategoria();
	}
	
	public CategoriaCtrl(JTextField tfIdCategoria, JTextField tfNomeCategoria,
			JButton btnApagar, JButton btnGravarEdit, JLabel msgGravar,
			JLabel msgVazio, JButton btnIdPesquisa,
			JButton btnCategoriaPesquisa) {
		
		this.idCategoria = tfIdCategoria;
		this.btGravar = btnGravarEdit;
		this.btnApagar = btnApagar;
		this.btnCategoriaPesquisa = btnCategoriaPesquisa;
		this.btnIdPesquisa = btnIdPesquisa;
		this.nomeCategoria = tfNomeCategoria;
		this.msgGravado = msgGravar;
		this.msgVazio = msgVazio;
	}

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idCategoria.setText("CTG" + id);
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
		CategoriaArquivo categorialImpl = new CategoriaArquivo();

		categoria.setId(idCategoria.getText());
		categoria.setNome(nomeCategoria.getText());

		if (!nomeCategoria.getText().isEmpty()) {
			try {
				categorialImpl.escreveArquivo("../MASProject/dados/", "categorias", nomeCategoria.getText(), categoria);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgGravado.setText(nomeCategoria.getText() + " salvo com sucesso!");
			msgGravado.setVisible(true);
			nomeCategoria.setText(null);
			gerarId();
		} else {
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	public ActionListener gravarCategoria = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarCategoria();
		}
	};
	
	public ActionListener fecharCategoria = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
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
				nomeCategoria.setText(null);
				contador += 1;
			}
			
			btGravar.setEnabled(true);
			msgGravado.setVisible(false);
			msgVazio.setVisible(false);
		}
	};
	
	//Manipula�ao da Tela de CategoriaEdit
	
	public void pesquisaIdCat(){
		
		/*
		 	if (!nomeCategoria.getText().isEmpty() || !idCategoria.getText().isEmpty()) {
			msgGravado.setText(nomeCategoria.getText() + " localizado com sucesso!");
			msgGravado.setVisible(true);
			nomeCategoria.setText(null);
		} else {
			msgGravado.setVisible(false);
			msgVazio.setText("Por favor, use um dos campos de Pesquisa!");
			msgVazio.setVisible(true);
		}
		*/
	}
	
	public void pesquisarNomeCat(){
		
	}
	
	public void apagarCategoria(){
		/*
		 	if (!nomeCategoria.getText().isEmpty()) {
			msgGravado.setText(nomeCategoria.getText() + " excluído com sucesso!");
			msgGravado.setVisible(true);
			nomeCategoria.setText(null);
		} else {
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
		}
		
		*/

	}
	
	public void gravarCategoriaEdit(){
		
	}
	
	public ActionListener pesquisaIdCat = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisaIdCat();
		}
	};
	
	public ActionListener pesquisarNomeCat = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarNomeCat();
		}
	};
	
	public ActionListener apagarCategoria = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			apagarCategoria();
		}
	};
	
	public ActionListener gravarCategoriaEdit = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			gravarCategoriaEdit();
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
