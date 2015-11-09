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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Categoria;
import persistence.CategoriaArquivo;

public class CategoriaCtrl implements ComponentListener {

	private JPanel form;
	private JTextField id, nome;
	private List<Categoria> categorias;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivo = new ArquivosCtrl();
	private CategoriaArquivo formatar = new CategoriaArquivo();

	public CategoriaCtrl(JPanel form, JTextField id, JTextField nome) {

		this.id = id;
		this.nome = nome;
		this.categorias = new ArrayList<Categoria>();

		
		lerCategoria();
	}
	
	// METODOS DE SUPORTE ////////////////////////

	public void gerarId(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		id.setText("CAT" + NewId);
	}

	public void limpaCampos() {
		nome.setText(null);
		id.setText(null);
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

	// CRUD //////////////////////////

	public void lerCategoria() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
	
		try {
			arquivo.leArquivo("../MASProject/dados/", "categorias");
			linha = arquivo.getBuffer();
			String[] listaCategoria = linha.split(";");
			for (String s : listaCategoria) {
				String text = s.replaceAll(".*: ", "");
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
	}

	public void atualizaDados(List<Categoria> listCategorias) {
		File f = new File("../MASProject/dados/categorias");
		f.delete();	
		for (Categoria categoria : listCategorias) {
			try {
				formatar.escreveArquivo("../MASProject/dados/", "categorias", "", categoria);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisar() {

		ArrayList<Categoria> listCategoria = new ArrayList<>();
		String pesquisa ="";
		if (!nome.getText().isEmpty() || !id.getText().isEmpty()) {

			for (int i = 0; i < categorias.size(); i++) {
				if (nome.getText().equalsIgnoreCase(categorias.get(i).getId())) {
					id.setText(categorias.get(i).getId());
					nome.setText(categorias.get(i).getNome());
					validar = true;
				} else if (nome.getText().equalsIgnoreCase(categorias.get(i).getNome())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < categorias.size(); i++) {

					boolean filtro = nome.getText().equalsIgnoreCase(categorias.get(i).getNome());
					if (filtro == true) {
						Categoria item = new Categoria();
						item.setId(categorias.get(i).getId());
						item.setNome(categorias.get(i).getNome());
						listCategoria.add(item);
					}
				}
				String[] filtro = new String[listCategoria.size()];
				for (int i = 0; i < listCategoria.size(); i++) {
					filtro[i] = listCategoria.get(i).getId();
					pesquisa = listCategoria.get(i).getId();
				}
				if (filtro != null && filtro.length > 1) {
					pesquisa = (String) JOptionPane.showInputDialog(form, "Escolha o ID:\n", "Selecione o ID",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				}
				for (int i = 0; i < categorias.size(); i++) {
					if (pesquisa.equalsIgnoreCase(categorias.get(i).getId())) {
						id.setText(categorias.get(i).getId());
						nome.setText(categorias.get(i).getNome());
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
		Categoria categoria = new Categoria();
		validar = false;
		if (!id.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (!id.getText().equalsIgnoreCase(categorias.get(i).getId()) && nome.getText().equalsIgnoreCase(categorias.get(i).getNome())) {				
					msg("erroredit",categorias.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < categorias.size(); i++) {
					if (id.getText().equalsIgnoreCase(categorias.get(i).getId())) {
						categoria.setId(id.getText());
						categoria.setNome(nome.getText());
						categorias.set(i, categoria);
						atualizaDados(categorias);
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
		if (!id.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (id.getText().equalsIgnoreCase(categorias.get(i).getId()) && nome.getText().equalsIgnoreCase(categorias.get(i).getNome())) {
					categorias.remove(i);
					validar = true;
				}
			}
			if (validar == true) {
				msg("deleteconfirm", nome.getText());
				if (validar == false){
					atualizaDados(categorias);
					msg("delete", nome.getText());
					limpaCampos();
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
		new CategoriaArquivo();
		Categoria categoria = new Categoria();
		validar = false;
		if (!nome.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (nome.getText().equalsIgnoreCase(categorias.get(i).getNome())){
					msg("erroredit", categorias.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
				categoria.setId(id.getText());
				categoria.setNome(nome.getText());
				categorias.add(categoria);
				msg("save", nome.getText());
				atualizaDados(categorias);
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
				id.setText(null);
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
