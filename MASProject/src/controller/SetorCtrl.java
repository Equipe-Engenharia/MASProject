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

import model.Setor;
import persistence.SetorArquivo;

public class SetorCtrl implements ComponentListener {

	private JPanel form;
	private JTextField txtId, txtNome;
	private List<Setor> setores;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivo = new ArquivosCtrl();
	private SetorArquivo formatar = new SetorArquivo();

	public SetorCtrl (JPanel form, JTextField txtId, JTextField txtNome) {

		this.txtId = txtId;
		this.txtNome = txtNome;
		this.setores = new ArrayList<Setor>();

		lerArquivo();
	}
	
	// METODOS DE SUPORTE ////////////////////////

	public void gerarId(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("SET" + NewId);
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
			arquivo.leArquivo("../MASProject/dados/", "setores");
			linha = arquivo.getBuffer();
			String[] listaSetor = linha.split(";");
			for (String s : listaSetor) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					Setor setor = new Setor();
					setor.setId(list.get(0));
					setor.setNome(list.get(1));
					setores.add(setor);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizaDados(List<Setor> lista) {
		File f = new File("../MASProject/dados/setores");
		f.delete();	
		for (Setor setor : lista) {
			try {
				formatar.escreveArquivo("../MASProject/dados/", "setores", "", setor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisar() {

		ArrayList<Setor> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtNome.getText().isEmpty() || !txtId.getText().isEmpty()) {

			for (int i = 0; i < setores.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(setores.get(i).getId())) {
					txtId.setText(setores.get(i).getId());
					txtNome.setText(setores.get(i).getNome());
					validar = true;
				} else if (txtNome.getText().equalsIgnoreCase(setores.get(i).getNome())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < setores.size(); i++) {

					boolean filtro = txtNome.getText().equalsIgnoreCase(setores.get(i).getNome());
					if (filtro == true) {
						Setor item = new Setor();
						item.setId(setores.get(i).getId());
						item.setNome(setores.get(i).getNome());
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
				for (int i = 0; i < setores.size(); i++) {
					if (pesquisa.equalsIgnoreCase(setores.get(i).getId())) {
						txtId.setText(setores.get(i).getId());
						txtNome.setText(setores.get(i).getNome());
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
		Setor setor = new Setor();
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (!txtId.getText().equalsIgnoreCase(setores.get(i).getId()) && txtNome.getText().equalsIgnoreCase(setores.get(i).getNome())) {				
					msg("erroredit",setores.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < setores.size(); i++) {
					if (txtNome.getText().equalsIgnoreCase(setores.get(i).getId())) {
						setor.setId(txtId.getText());
						setor.setNome(txtNome.getText());
						setores.set(i, setor);
						atualizaDados(setores);
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
			for (int i = 0; i < setores.size(); i++) {
				if (txtId.getText().equalsIgnoreCase(setores.get(i).getId()) && txtNome.getText().equalsIgnoreCase(setores.get(i).getNome())) {
					setores.remove(i);
					validar = true;
				}
			}
			if (validar == true) {
				msg("deleteconfirm", txtNome.getText());
				if (validar == false){
					atualizaDados(setores);
					msg("delete", txtNome.getText());
					limpaCampos();
				} else {
					setores.clear();
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
		new SetorArquivo();
		Setor setor = new Setor();
		validar = false;
		if (!txtNome.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(setores.get(i).getNome())){
					msg("erroredit", setores.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
				setor.setId(txtId.getText());
				setor.setNome(txtNome.getText());
				setores.add(setor);
				msg("save", txtNome.getText());
				atualizaDados(setores);
				txtNome.setText(null);
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
