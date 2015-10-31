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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Categoria;
import model.Material;
import persistence.MaterialArquivo;

public class MaterialCtrl implements ComponentListener {

	private JTextField idMaterial, nomeMaterial;
	private JComboBox<String> cbCategoria;
	private JButton btApagar, btGravar;
	private JLabel msgGravar, msgVazio;
	private List<Material> materiais;
	private static int contador = 1;
	private ArquivosCtrl ctrlArquivos;

	public MaterialCtrl(JTextField idMaterial, JComboBox<String> cbCategoria, JTextField nomeMaterial,
			JButton btnApagar, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {

		this.cbCategoria = cbCategoria;
		this.idMaterial = idMaterial;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		this.msgGravar = msgGravado;
		this.msgVazio = msgVazio;
		this.nomeMaterial = nomeMaterial;
		this.materiais = new ArrayList<Material>();
		
		lerMaterial();
	}

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idMaterial.setText("MAT" + id);
	}

	public void lerMaterial() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		ctrlArquivos = new ArquivosCtrl();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "materiais");
			linha = ctrlArquivos.getBuffer();
			String[] listaMaterial = linha.split(";");
			for (String s : listaMaterial) {
				String text = s.replaceAll(".*:", "");
				list.add(text);
				if (s.contains("---")) {
					Material material = new Material();
					material.setId(list.get(0));
					material.setNome(list.get(1));
					material.setCategoria(list.get(2));
					materiais.add(material);
					list.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*for(Material m :  materiais){
			System.out.println(m.getNome());
		}*/
	}

	public void preencherComboBoxCategoria() {
		String linha = new String();
		ctrlArquivos = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategorias = new ArrayList<>();

		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "categorias");
			linha = ctrlArquivos.getBuffer();
			String[] categorias = linha.split(";");
			for (String s : categorias) {
				String text = s.replaceAll(".*:", "");
				listString.add(text);
				if (s.contains("---")) {
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
			cbCategoria.addItem(c.getNome());
		}
	}
	
	public void pesquisarMaterial() {

		if (!nomeMaterial.getText().isEmpty() || !idMaterial.getText().isEmpty()) {
			msgGravar.setText(nomeMaterial.getText() + " localizado com sucesso!");
			msgGravar.setVisible(true);
			nomeMaterial.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setText("Por favor, use um dos campos de Pesquisa!");
			msgVazio.setVisible(true);
		}		
	}

	public void apagarMaterial() {
		
		if (!nomeMaterial.getText().isEmpty()) {
			msgGravar.setText(nomeMaterial.getText() + " excluído com sucesso!");
			msgGravar.setVisible(true);
			nomeMaterial.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	public void gravarMaterial() {
		Material material = new Material();
		MaterialArquivo materialImpl = new MaterialArquivo();

		material.setId(idMaterial.getText());
		material.setNome(nomeMaterial.getText());
		material.setCategoria(cbCategoria.getSelectedItem().toString());

		if (!nomeMaterial.getText().isEmpty()) {
			try {
				materialImpl.escreveArquivo("../MASProject/dados/", "materiais", nomeMaterial.getText(), material);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgGravar.setText(nomeMaterial.getText() + " salvo com sucesso!");
			msgGravar.setVisible(true);
			nomeMaterial.setText(null);
			gerarId();
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	public ActionListener pesquisarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarMaterial();
		}
	};
	
	public ActionListener apagarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			apagarMaterial();
		}
	};
	
	public ActionListener gravarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarMaterial();
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
				nomeMaterial.setText(null);
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