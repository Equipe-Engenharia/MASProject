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

	private JPanel form;
	private JTextField id, nome;
	private JComboBox<String> cbCategoria;
	private List<Material> materiais;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivo = new ArquivosCtrl();
	private MaterialArquivo formatar = new MaterialArquivo();

	public MaterialCtrl(JPanel form, JTextField id, JComboBox<String> cbCategoria,
			JTextField txtMaterial) {

		this.form = form;
		this.cbCategoria = cbCategoria;
		this.id = id;
		this.nome = txtMaterial;
		this.materiais = new ArrayList<Material>();

		lerMaterial();
	}

	// METODOS DE SUPORTE ////////////////////////

	public void gerarId() { // USO DESTE METODO NO GRAVARMATERIAL E FRMMATERIAL (CAD E EDIT)
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		id.setText("MAT" + NewId);
	}

	public void limpaCampos() {
		nome.setText(null);
		id.setText(null);
		cbCategoria.setSelectedIndex(0);
	}
	

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Setor.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.", 
					"Não Localizado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' salvo com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nNão foi possível apagar o registro: " + id.getText() + " "
					+ nome.getText() + "!\nVerifique sua digitação!", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' editado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroredit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' já existe!",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "deleteconfirm":
			Object[] options = { "Confirmar", "Cancelar" };  
			int r = JOptionPane.showOptionDialog(null, "Você confirma a exclusão do registro '" + mensagem + "'?",
					"Exclusão de Registro", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), options, options[0]);
			if (r == 0) {
				validar = false;
			}
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' excluído com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errordelete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null, 
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nVolte ao trabalho duro e conserte isso!!!", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}


	// PREENCHE COMBOBOX /////////////////////

	public void preencherComboBoxCategoria() {
		String linha = new String();
		arquivo = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategorias = new ArrayList<>();

		try {
			arquivo.leArquivo("../MASProject/dados/", "categorias");
			linha = arquivo.getBuffer();
			String[] categorias = linha.split(";");
			for (String s : categorias) {
				String text = s.replaceAll(".*: ", "");
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
	
		try {
			arquivo.leArquivo("../MASProject/dados/", "materiais");
			linha = arquivo.getBuffer();
			String[] listaMaterial = linha.split(";");
			for (String s : listaMaterial) {
				String text = s.replaceAll(".*: ", "");
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
		for (Material material : listMateriais) {
			try {
				formatar.escreveArquivo("../MASProject/dados/", "materiais", "", material);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisar() {

		ArrayList<Material> lista = new ArrayList<>();
		String pesquisa ="";
		if (!nome.getText().isEmpty() || !id.getText().isEmpty()) {

			for (int i = 0; i < materiais.size(); i++) {
				if (nome.getText().equalsIgnoreCase(materiais.get(i).getId())) {
					id.setText(materiais.get(i).getId());
					nome.setText(materiais.get(i).getNome());
					cbCategoria.getModel().setSelectedItem(materiais.get(i).getCategoria());
					validar = true;
				} else if (nome.getText().equalsIgnoreCase(materiais.get(i).getNome())) {
					validar = true;
					cbCategoria.getModel().setSelectedItem(materiais.get(i).getCategoria());
				}
			}
			if (validar == true) {
				for (int i = 0; i < materiais.size(); i++) {
					boolean filtro = nome.getText().equalsIgnoreCase(materiais.get(i).getNome());
					if (filtro == true) {
						Material item = new Material();
						item.setId(materiais.get(i).getId());
						item.setNome(materiais.get(i).getNome());
						item.setCategoria(materiais.get(i).getCategoria());
						lista.add(item);
					}
				}
				String[] filtro = new String[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					filtro[i] = lista.get(i).getId();
					pesquisa = lista.get(i).getId();					
				}
				if (filtro != null && filtro.length > 1) {
					pesquisa = (String) JOptionPane.showInputDialog(form, "Escolha o ID:\n", "Selecione o ID",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				} 
				if (pesquisa == "0" || pesquisa != null){
				for (int i = 0; i < materiais.size(); i++) {
					if (pesquisa.equalsIgnoreCase(materiais.get(i).getId())) {
						id.setText(materiais.get(i).getId());
						nome.setText(materiais.get(i).getNome());
						cbCategoria.getModel().setSelectedItem(materiais.get(i).getCategoria());
					}
				}
				}
				validar = false;
			} else {
				if (pesquisa == "") {
					msg("nosearch", nome.getText());
					limpaCampos();
				}
				validar = false;
			}
		} else {
			msg("errorsearch", nome.getText());
		}
	}

	public void editar() {
		Material material = new Material();
		validar = false;
		if (!id.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (!id.getText().equalsIgnoreCase(materiais.get(i).getId()) 
						&& nome.getText().equalsIgnoreCase(materiais.get(i).getNome())
						&& cbCategoria.getSelectedItem().toString().equalsIgnoreCase(materiais.get(i).getCategoria())) {
					msg("erroredit", materiais.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < materiais.size(); i++) {
					if (id.getText().equalsIgnoreCase(materiais.get(i).getId())) {
						material.setId(id.getText());
						material.setNome(nome.getText());
						material.setCategoria((String) cbCategoria.getSelectedItem());
						materiais.set(i, material);
						atualizaDados(materiais);
						msg("edit", nome.getText());
						limpaCampos();	
					}
				}
			}
		} else {
			msg("errorsearch", nome.getText());
		}
	}

	public void excluir() {
		validar = false;
		if (!id.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (id.getText().equalsIgnoreCase(materiais.get(i).getId()) 
						&& nome.getText().equalsIgnoreCase(materiais.get(i).getNome())
						&& cbCategoria.getSelectedItem().toString().equalsIgnoreCase(materiais.get(i).getCategoria())) {
					materiais.remove(i);
					validar = true;
				} else {
					if(i == materiais.size()){
						msg("errordelete", materiais.get(i).getNome());
					}
				}
			}
			if (validar == true) {
				msg("deleteconfirm", nome.getText());
				if (validar == false){
					atualizaDados(materiais);
					msg("delete", nome.getText());
					limpaCampos();
				} else {
					materiais.clear();
					lerMaterial();	
				}
			} else {
				validar = false;
				msg("errordelete", id.getText());
			}
		} else {
			pesquisar();
		}
	}

	public void gravar() {
		new MaterialArquivo();
		Material material = new Material();
		validar = false;
		if (!nome.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {	
				if (nome.getText().equalsIgnoreCase(materiais.get(i).getNome()) && cbCategoria.getSelectedItem().toString().equalsIgnoreCase(materiais.get(i).getCategoria())) {
					msg("erroredit", materiais.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){
				material.setId(id.getText());
				material.setNome(nome.getText());
				material.setCategoria(cbCategoria.getSelectedItem().toString());
				materiais.add(material);
				msg("save", nome.getText());
				atualizaDados(materiais);
				nome.setText(null);
				gerarId();
			}
		} else {
			msg("errornull", nome.getText());
		}
	}

	// CONTROLE BOTAO //////////////////////////////

	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisar();
		}
	};

	public ActionListener excluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
				excluir();
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			editar();
		}
	};

	public ActionListener gravar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravar();
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
				nome.setText(null);
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