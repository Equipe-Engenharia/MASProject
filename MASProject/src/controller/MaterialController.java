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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Categoria;
import model.Material;
import persistence.MaterialArquivoImpl;

public class MaterialController implements ComponentListener {

	private JComboBox<String> cbCategoria;
	private JTextField nomeMaterial, idMaterial;
	private JButton btApagar;
	private JButton btGravar;
	private JLabel msgGravado, msgVazio;
	private List<Material> materiais;
	private ArquivosController ctrlArquivos;

	// Material material = new Material();

	public MaterialController(JComboBox<String> cbCategoria, JTextField txtID, JTextField txtMaterial,
			JButton btnApagar, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {

		this.cbCategoria = cbCategoria;
		this.idMaterial = txtID;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.nomeMaterial = txtMaterial;
		this.materiais = new ArrayList<Material>();
		
		//lerMaterial();
	}

	public void gerarIdSetor() {
		String indice = "MAT";
		GeradordeID geraID = new GeradordeID(indice);
		idMaterial.setText(geraID.geraID());
	}

	public void lerMaterial() {
		String linha = new String();
		ArrayList<String> tipoMaterial = new ArrayList<>();

		ctrlArquivos = new ArquivosController();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "materiais");
			linha = ctrlArquivos.getBuffer();
			String[] listaMaterial = linha.split(";");
			for (String s : listaMaterial) {
				String text = s.replaceAll(".*:", "");
				tipoMaterial.add(text);
				if (s.contains("-")) {
					Material material = new Material();
					material.setID(tipoMaterial.get(0));
					material.setNome(tipoMaterial.get(1));
					material.setCategoria(tipoMaterial.get(2));
					materiais.add(material);
					tipoMaterial.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//for(Material m :  materiais){
			//System.out.println(m.getNome());
		//}
	}

	public void preencherComboBoxCategoria() {
		String linha = new String();
		ctrlArquivos = new ArquivosController();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategorias = new ArrayList<>();

		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "categorias");
			linha = ctrlArquivos.getBuffer();
			String[] categorias = linha.split(";");
			for (String s : categorias) {
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
			cbCategoria.addItem(c.getNome());
		}
	}

	public void gravaMaterial() {
		Material material = new Material();
		MaterialArquivoImpl materialImpl = new MaterialArquivoImpl();

		material.setID(idMaterial.getText());
		material.setNome(nomeMaterial.getText());
		material.setCategoria(cbCategoria.getSelectedItem().toString());

		if (!nomeMaterial.getText().isEmpty()) {
			try {
				materialImpl.escreveArquivo("../MASProject/dados/", "materiais", nomeMaterial.getText(), material);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgGravado.setText(nomeMaterial.getText() + " salvo com sucesso!!!");
			msgGravado.setVisible(true);
			nomeMaterial.setText(null);
			gerarIdSetor();
		} else {
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
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
			nomeMaterial.setText(null);
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