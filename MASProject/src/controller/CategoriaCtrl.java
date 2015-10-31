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

public class CategoriaCtrl implements ComponentListener {

	private JTextField idCategoria, nomeCategoria;
	private JButton btGravar;
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

	public void gerarIdSetor() {
		GeradordeID geraID = new GeradordeID();
		idCategoria.setText("CTG"+geraID.getIndice());
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
			gerarIdSetor();
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
