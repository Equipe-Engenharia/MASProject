package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Categoria;
import model.Material;
import persistence.MaterialArquivo;

public class MaterialCtrl implements ComponentListener {

	private JPanel frmMaterial;
	private JTextField idMaterial, nomeMaterial;
	private JComboBox<String> cbCategoria;
	private List<Material> materiais;
	private static int contador = 1;
	private String validar;
	private ArquivosCtrl ctrlArquivos;

	public MaterialCtrl(JPanel frmMaterial, JTextField idMaterial, JComboBox<String> cbCategoria,
			JTextField txtMaterial) {

		this.frmMaterial = frmMaterial;
		this.cbCategoria = cbCategoria;
		this.idMaterial = idMaterial;
		this.nomeMaterial = txtMaterial;
		this.materiais = new ArrayList<Material>();

		lerMaterial();
	}

	// METODOS DE SUPORTE ////////////////////////

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idMaterial.setText("MAT" + id);
	}

	public void limpaCampos() {
		nomeMaterial.setText(null);
		idMaterial.setText(null);
		cbCategoria.setSelectedIndex(0);
	}

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Material.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "cancelsearch":
			// JOptionPane.showMessageDialog(null, "ATENÇÃO! Cancelando sua
			// pesquisa!", "Registro de Material",
			// JOptionPane.PLAIN_MESSAGE, new
			// ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.",
					"Pesquisa de Material", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, "ATENÇÃO! Por favor, digite para pesquisar!", "Registro de Material",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' salvo com sucesso.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\nNão foi possível apagar o registro: " + idMaterial.getText() + " "
							+ nomeMaterial.getText() + "!\nVerifique sua digitação!",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' editado com sucesso.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' apagado com sucesso.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null,
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\n" + mensagem
							+ "\n\nVolte ao trabalho e conserte isso!!!",
					"Erro no Controller Material", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}

	public void msgTeste(String teste) { // USO PARA TESTE NO SISTEMA - SERÁ
											// INUTILIZADA
		JOptionPane.showMessageDialog(null, teste, "Teste do Controller Material", JOptionPane.PLAIN_MESSAGE,
				new ImageIcon("../MASProject/icons/warning.png"));
	}

	// PREENCHE COMBOBOX /////////////////////

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
				String text = s.replaceAll(".*: ", ""); // CORRIGIDO ERRO NA RECUPERAÇÃO DO REGISTRO - PRECISA DE 2 ESPAÇOS DEPOIS DO *:
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

	// CRUD //////////////////////////

	public void lerMaterial() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		ctrlArquivos = new ArquivosCtrl();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "materiais");
			linha = ctrlArquivos.getBuffer();
			String[] listaMaterial = linha.split(";");
			for (String s : listaMaterial) {
				String text = s.replaceAll(".*: ", ""); // CORRIGIDO ERRO QUE IMPEDE A EXCLUSÃO DO REGISTRO - PRECISA DE 2 ESPAÇOS DEPOIS DO *:
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
	}

	public void atualizaDados(List<Material> listMateriais) {
		File f = new File("../MASProject/dados/materiais");
		f.delete();
		MaterialArquivo materialImpl = new MaterialArquivo();
		for (Material material : listMateriais) {
			try {
				materialImpl.escreveArquivo("../MASProject/dados/", "materiais", "", material);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisarMaterial(String pesquisa) {

		ArrayList<Material> listMaterial = new ArrayList<>();
		List<Material> dados = materiais;

		if (!nomeMaterial.getText().isEmpty() || !idMaterial.getText().isEmpty()) {

			for (int i = 0; i < materiais.size(); i++) {
				if (pesquisa.equalsIgnoreCase(materiais.get(i).getId())) {
					idMaterial.setText(materiais.get(i).getId());
					nomeMaterial.setText(materiais.get(i).getNome());
					cbCategoria.getModel().setSelectedItem(materiais.get(i).getCategoria());
					validar = "id";
				} else if (pesquisa.equalsIgnoreCase(materiais.get(i).getNome())) {
					validar = "nome";
					cbCategoria.getModel().setSelectedItem(materiais.get(i).getCategoria());
				}
			}
			if (validar == "nome") {
				for (int i = 0; i < dados.size(); i++) {

					boolean filtro = pesquisa.equalsIgnoreCase(materiais.get(i).getNome());

					if (filtro == true) {

						Material m = new Material();

						m.setId(materiais.get(i).getId());
						m.setNome(materiais.get(i).getNome());
						m.setCategoria(materiais.get(i).getCategoria());
						listMaterial.add(m);
					}
				}
				String[] filtro = new String[listMaterial.size()];
				for (int i = 0; i < listMaterial.size(); i++) {
					filtro[i] = listMaterial.get(i).getId();
				}
				String s = (String) JOptionPane.showInputDialog(frmMaterial, "Escolha o ID:\n", "Selecione o ID",
						JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				if (s != null && s.length() > 0) {
					for (int i = 0; i < materiais.size(); i++) {
						if (s.equalsIgnoreCase(materiais.get(i).getId())) {
							idMaterial.setText(materiais.get(i).getId());
							nomeMaterial.setText(materiais.get(i).getNome());
							cbCategoria.getModel().setSelectedItem(materiais.get(i).getCategoria());
						}
					}
					validar = "";
				} else {
					msg("cancelsearch", "");
				}
			} else {
				validar = "";
				if (validar != "id") {
					msg("nosearch", pesquisa);
				}
			}

		} else {
			msg("errorsearch", pesquisa);
		}
	}

	public void editarMaterial(String pesquisa) {
		Material material = new Material();
		if (!idMaterial.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (pesquisa.equalsIgnoreCase(materiais.get(i).getId())) {
					material.setId(idMaterial.getText());
					material.setNome(nomeMaterial.getText());
					material.setCategoria((String) cbCategoria.getSelectedItem());
					materiais.set(i, material);
					atualizaDados(materiais);
					msg("edit", pesquisa);
					limpaCampos();
				}
			}
		} else {
			msg("errorsearch", pesquisa);
		}
	}

	public void apagarMaterial(String pesquisa) {
		if (!idMaterial.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (pesquisa.equalsIgnoreCase(materiais.get(i).getId())) {
					materiais.remove(i);
					validar = "ok";
				}
			}
			if (validar == "ok") {
				atualizaDados(materiais);
				msg("delete", pesquisa);
				limpaCampos();
			} else {
				validar = "";
				msg("erroApaga", pesquisa);
			}
		} else {
			pesquisarMaterial(pesquisa);
		}
	}

	public void gravarMaterial(String pesquisa) {

		Material material = new Material();
		new MaterialArquivo();

		if (!nomeMaterial.getText().isEmpty()) {
			material.setId(idMaterial.getText());
			material.setNome(nomeMaterial.getText());
			material.setCategoria(cbCategoria.getSelectedItem().toString());
			materiais.add(material);
			msg("save", pesquisa);
			atualizaDados(materiais);
			nomeMaterial.setText(null);
			gerarId();
		} else {
			msg("errornull", pesquisa);
		}
	}

	// CONTROLE BOTAO //////////////////////////////

	public ActionListener pesquisarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarMaterial(nomeMaterial.getText());
		}
	};

	public ActionListener apagarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (idMaterial.getText().isEmpty()) {
				apagarMaterial(nomeMaterial.getText());
			} else {
				apagarMaterial(idMaterial.getText());
			}
		}
	};

	public ActionListener editarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			editarMaterial(idMaterial.getText());
		}
	};

	public ActionListener gravarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarMaterial(idMaterial.getText());
		}
	};

	// CONTROLE MOUSE ///////////////////////////////

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