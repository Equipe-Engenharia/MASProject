package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Material;

import persistence.MaterialArquivoImpl;

public class MaterialController implements ComponentListener {
	
	private JComboBox<String> listaCategoria;
	private JTextField nomeMaterial, idMaterial;
	private JButton btGravar;
	private JLabel msgGravado, msgVazio;
	private ArquivosController ctrlArquivos;
	
	Material material = new Material();

	public MaterialController(JComboBox<String> cbCategoria, JTextField txtID, 
			JTextField txtMaterial, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {
		
		this.listaCategoria = cbCategoria;
		this.idMaterial = txtID;
		this.btGravar = btnGravar;
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.nomeMaterial = txtMaterial;
	}
	
	public void atualizaID() {
		Random random = new Random();  
		int idNum = random.nextInt(9999);
		idMaterial.setText("MT" + String.format("%04d",idNum));
	}
	
	public void preencherComboBoxCategoria() {
		String linha = new String();
		ctrlArquivos = new ArquivosController();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados", "categorias");
			linha = ctrlArquivos.getBuffer();
			String[] categoria = linha.split(";");
			for (String s : categoria) {
				listaCategoria.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			nomeMaterial.setText(null);
			atualizaID();
		}else{
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
		}
		// implementar a acao de apagar o campo de nome e criar uma nova id
		// quando clicar em gravar
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