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

import model.CategoriaMdl;
import persistence.CategoriaFile;

public class CategoriaCtrl implements ComponentListener {

	private JPanel form;
	private JTextField txtId, txtNome;
	private List<CategoriaMdl> categorias;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivos = new ArquivosCtrl();
	private CategoriaFile arquivo = new CategoriaFile();

	public CategoriaCtrl(JPanel form, JTextField txtId, JTextField txtNome) {

		this.txtId = txtId;
		this.txtNome = txtNome;
		this.categorias = new ArrayList<CategoriaMdl>();

		
		lerArquivo();
	}
	
	// METODOS DE SUPORTE ////////////////////////
	

	public void gerarId(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("CAT" + NewId);
	}

	public void limpaCampos() {
		
		txtId.setText(null);
		txtNome.setText(null);	
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
					"ATENÇÃO!\nNão foi possível apagar o registro: " + txtId.getText() + " "
					+ txtNome.getText() + "!\nVerifique sua digitação!", 
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
	

	public void lerArquivo() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "categorias");
			linha = arquivos.getBuffer();
			String[] listaCategoria = linha.split(";");
			for (String s : listaCategoria) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					CategoriaMdl categoria = new CategoriaMdl();
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
	

	public void atualizaDados(List<CategoriaMdl> listCategorias) {
		File f = new File("../MASProject/dados/categorias");
		f.delete();	
		for (CategoriaMdl categoria : listCategorias) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "categorias", "", categoria);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void pesquisar() {

		ArrayList<CategoriaMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtNome.getText().isEmpty() || !txtId.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(categorias.get(i).getId())) {
					txtId.setText(categorias.get(i).getId());
					txtNome.setText(categorias.get(i).getNome());
					validar = true;
				} else if (categorias.get(i).getNome().toLowerCase().startsWith(txtNome.getText().toLowerCase())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < categorias.size(); i++) {

					boolean filtro = categorias.get(i).getNome().toLowerCase().startsWith(txtNome.getText().toLowerCase());
					if (filtro == true) {
						CategoriaMdl item = new CategoriaMdl();
						item.setId(categorias.get(i).getId());
						item.setNome(categorias.get(i).getNome());
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
				for (int i = 0; i < categorias.size(); i++) {
					if (pesquisa.equalsIgnoreCase(categorias.get(i).getId())) {
						txtId.setText(categorias.get(i).getId());
						txtNome.setText(categorias.get(i).getNome());
					}
				}
				validar = false; 
			} else {
				if (pesquisa == "") {
					msg("nosearch", txtNome.getText());
					limpaCampos();
				}
				validar = false;
			}
		} else {
			msg("errorsearch", txtNome.getText());
		}
	}
	

	public void editar() {
		
		CategoriaMdl categoria = new CategoriaMdl();
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (!txtId.getText().equalsIgnoreCase(categorias.get(i).getId()) && txtNome.getText().equalsIgnoreCase(categorias.get(i).getNome())) {				
					msg("erroredit",categorias.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < categorias.size(); i++) {
					if (txtId.getText().equalsIgnoreCase(categorias.get(i).getId())) {
						categoria.setId(txtId.getText());
						categoria.setNome(txtNome.getText());
						categorias.set(i, categoria);
						atualizaDados(categorias);
						msg("edit", txtNome.getText());
						limpaCampos();
					}
				}
			}
		} else {
			msg("errorsearch", txtNome.getText());
		}
	}

	
	public void excluir() {
		
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (txtId.getText().equalsIgnoreCase(categorias.get(i).getId()) && txtNome.getText().equalsIgnoreCase(categorias.get(i).getNome())) {
					categorias.remove(i);
					validar = true;
				}
			}
			if (validar == true) {
				msg("deleteconfirm", txtNome.getText());
				if (validar == false){
					atualizaDados(categorias);
					msg("delete", txtNome.getText());
					limpaCampos();
				} else {
					categorias.clear();
					lerArquivo();	
				}
			} else {
				validar = false;
				msg("errordelete", txtId.getText());
			}
		} else {
			pesquisar();
		}
	}
	

	public void gravar() {
		
		new CategoriaFile();
		CategoriaMdl categoria = new CategoriaMdl();
		validar = false;
		if (!txtNome.getText().isEmpty()) {
			for (int i = 0; i < categorias.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(categorias.get(i).getNome())){
					msg("erroredit", categorias.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
				categoria.setId(txtId.getText());
				categoria.setNome(txtNome.getText());
				categorias.add(categoria);
				msg("save", txtNome.getText());
				atualizaDados(categorias);
				limpaCampos();
				gerarId();
			}
		} else {
			msg("errornull", txtNome.getText());
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
				txtId.setText(null);
				txtNome.setText(null);
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
