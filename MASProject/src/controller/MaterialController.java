package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Material;

public class MaterialController implements ComponentListener {
	
	private JComboBox<String> listaCategoria;
	private JTextField nomeMaterial, idMaterial;
	private JButton btGravar;
	private JLabel msgPositivo, msgNegativo;
	private ArquivosController ctrlArquivos;
	
	Material material = new Material();

	public MaterialController(JComboBox<String> cbCategoria, JTextField txtID, 
			JTextField txtMaterial, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {
		
		this.listaCategoria = cbCategoria;
		this.idMaterial = txtID;
		this.btGravar = btnGravar;
		this.msgPositivo = msgGravado;
		this.msgNegativo = msgVazio;
		this.nomeMaterial = txtMaterial;
	}

	public void atualizaID() {
		File arquivo = new File("../MASProject/dados/", "materiais");// verifica se o arquivo existe
		if (arquivo.exists()) {
			String linha = new String();
			ctrlArquivos = new ArquivosController();
			try {
				ctrlArquivos.leArquivo("../MASProject/dados/", "materiais");
				linha = ctrlArquivos.getBuffer();
				String[] id = linha.split(";");
				if (id.length == 1) {//Se o arquivo está vazio
					idMaterial.setText("MT" + 1);
				} else {
					int num = Integer.parseInt(id[(id.length - 1) - 2]);//Extrai a última posição do identificador
					idMaterial.setText("MT" + Integer.toString(num + 1));//Incrementa o identificador
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			idMaterial.setText("MT" + 1);
		}
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

		material.setID(idMaterial.getText().replaceAll("\\D", ""));
		material.setCategoria(listaCategoria.getSelectedItem().toString());
		material.setNome(nomeMaterial.getText());

		if (!nomeMaterial.getText().isEmpty()) { // se o campo não estiver vazio
			try {
				ctrlArquivos.escreveArquivo("../MASProject/dados/", "materiais", idMaterial.getText().replaceAll("\\D", "") + ";"
			+ listaCategoria.getSelectedItem().toString() + ";" + nomeMaterial.getText(), material); // Gravando o novo registro no arquivo.
			} catch (IOException e) {
				e.printStackTrace();
			}
			atualizaID();
			msgPositivo.setText("O material " + nomeMaterial.getText() + " salvo.");
			msgPositivo.setVisible(true);
			nomeMaterial.setText(null);// limpa o campo previnindo gravar em duplicidade
		} else {
			msgPositivo.setVisible(false);
			msgNegativo.setVisible(true);
		}
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
			msgPositivo.setVisible(false); // para que a mensagem não fique visível a todo momento
			msgNegativo.setVisible(false);
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