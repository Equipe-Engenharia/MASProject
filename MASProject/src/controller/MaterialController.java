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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Categoria;
import model.Material;

import persistence.MaterialArquivoImpl;

public class MaterialController implements ComponentListener {
	
	private JComboBox<String> listaCategoria;
	private JTextField nomeMaterial, idMaterial;
	private JButton btApagar;
	private JButton btGravar;
	private JLabel msgGravado, msgVazio;
	private ArquivosController ctrlArquivos;
	
	Material material = new Material();

	public MaterialController(JComboBox<String> cbCategoria, JTextField txtID, 
			JTextField txtMaterial, JButton btnApagar, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {
		
		this.listaCategoria = cbCategoria;
		this.idMaterial = txtID;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.nomeMaterial = txtMaterial;
	}
	
	public void atualizaID() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		idMaterial.setText("MT" + dateFormat.format(date));
	}
	
	public void preencherComboBoxCategoria() {
		String linha = new String();
		ctrlArquivos = new ArquivosController();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategorias = new ArrayList<>();

		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "categorias");
			linha = ctrlArquivos.getBuffer();
			String[] materias = linha.split(";");
			for (String s : materias) {
				String text = s.replaceAll(".*:", "");
				listString.add(text);
				if (s.contains("-")) {
					Categoria c = new Categoria();
					c.setNome(listString.get(1));
					listCategorias.add(c);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Categoria c : listCategorias) {
			listaCategoria.addItem(c.getNome());
		}
	}

	public void gravaMaterial() {
		Material material = new Material();
		MaterialArquivoImpl materialImpl = new MaterialArquivoImpl();
		
		material.setID(idMaterial.getText());
		material.setNome(nomeMaterial.getText());
		material.setCategoria(listaCategoria.getSelectedItem().toString());
		
		if (!nomeMaterial.getText().isEmpty()) {
			try {
				materialImpl.escreveArquivo("../MASProject/dados/", "materiais", nomeMaterial.getText(), material);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msgGravado.setText(nomeMaterial.getText()+" salvo com sucesso!!!");
			msgGravado.setVisible(true);
			
			
		}else{
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
		}
		nomeMaterial.setText(null);
		atualizaID();
	}

	public ActionListener gravarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravaMaterial();			
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
			
			nomeMaterial.setText(null); // limpa o campo
			btGravar.setEnabled(true);
			msgGravado.setVisible(false); // para que a mensagem não fique visível a todo momento
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