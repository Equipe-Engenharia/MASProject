package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Material;

public class MaterialController implements ComponentListener {

	private JLabel msgGravado, msgVazio;
	private JComboBox<String> Categoria;
	private JTextField nomeMaterial, idsetor;
	private JButton btnGravar;
	private ArquivosController arqController;
	Material material = new Material();

	public MaterialController(JComboBox<String> Categoria, JTextField id_setor, JTextField nomeDigit, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {
		this.Categoria = Categoria;
		this.idsetor = id_setor;
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.nomeMaterial = nomeDigit;
		this.btnGravar = btnGravar;
	}

	private void preencherComboBoxCategoria() {
		String linha = new String();

		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados/", "categorias");
			linha = arqController.getBuffer();
			String[] categoria = linha.split(";");
			for (String s : categoria) {
				Categoria.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void gravaMaterial() {

		IArquivosController arqContr = new ArquivosController();
		material.setNome(nomeMaterial.getText());

		if (nomeMaterial.getText().equals("Digite o novo material…")) { //se o usuário tentar gravar sem preencher
			nomeMaterial.setText(null);
		}
		if (!nomeMaterial.getText().isEmpty()) { // se o campo não estiver vazio
			try {
				arqContr.escreveArquivo("../MASProject/dados/", "materiais", nomeMaterial.getText(), material); // Gravando o novo registro no arquivo.
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgGravado.setText(nomeMaterial.getText() + " salvo.");
			msgGravado.setVisible(true);
			nomeMaterial.setText(null);// limpa o campo previnindo gravar em duplicidade
		} else {
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	public ActionListener gravarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravaMaterial();
		}
	};

	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			nomeMaterial.setText(null); // limpa o campo
			msgGravado.setVisible(false); // para que a mensagem não fique visível a todo momento
			msgVazio.setVisible(false);
		}
	};

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		preencherComboBoxCategoria();
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

}