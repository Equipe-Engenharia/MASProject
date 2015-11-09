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
	private JTextField id, nome;
	private List<Setor> setores;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivo = new ArquivosCtrl();
	private SetorArquivo formatar = new SetorArquivo();

	public SetorCtrl (JPanel form, JTextField id, JTextField nome) {

		this.id = id;
		this.nome = nome;
		this.setores = new ArrayList<Setor>();

		lerSetor();
	}
	
	// METODOS DE SUPORTE ////////////////////////

	public void gerarId(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		id.setText("SET" + NewId);
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
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.", 
					"Pesquisa de Setor", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite para pesquisar!", 
					"Registro de Setor",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' salvo com sucesso.", 
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nNão foi possível apagar o registro: " + id.getText() + " "
					+ nome.getText() + "!\nVerifique sua digitação!", 
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' editado com sucesso.", 
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroredit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' já existe!",
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' apagado com sucesso.", 
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errordelete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' não pode ser alterado para a exclusão.",
					"Registro de Setor", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null, 
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nVolte ao trabalho e conserte isso!!!", 
					"Erro no Controller Setor", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}

	// CRUD //////////////////////////

	public void lerSetor() {
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
		List<Setor> dados = setores;
		String not = "";
		if (!nome.getText().isEmpty() || !id.getText().isEmpty()) {

			for (int i = 0; i < setores.size(); i++) {
				if (nome.getText().equalsIgnoreCase(setores.get(i).getId())) {
					id.setText(setores.get(i).getId());
					nome.setText(setores.get(i).getNome());
					validar = true;
				} else if (nome.getText().equalsIgnoreCase(setores.get(i).getNome())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < dados.size(); i++) {

					boolean filtro = nome.getText().equalsIgnoreCase(setores.get(i).getNome());

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
				}
				String s = (String) JOptionPane.showInputDialog(form, "Escolha o ID:\n", "Selecione o ID",
						JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				if (s != null && s.length() > 0) {
					for (int i = 0; i < setores.size(); i++) {
						if (s.equalsIgnoreCase(setores.get(i).getId())) {
							id.setText(setores.get(i).getId());
							nome.setText(setores.get(i).getNome());
						}
					}
					validar = false;
				} 
			} else {
				if (not == "") {
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
		Setor setor = new Setor();
		validar = false;
		if (!id.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (!id.getText().equalsIgnoreCase(setores.get(i).getId()) && nome.getText().equalsIgnoreCase(setores.get(i).getNome())) {				
					msg("erroredit",setores.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < setores.size(); i++) {
					if (nome.getText().equalsIgnoreCase(setores.get(i).getId())) {
						setor.setId(id.getText());
						setor.setNome(nome.getText());
						setores.set(i, setor);
						atualizaDados(setores);
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
			for (int i = 0; i < setores.size(); i++) {
				if (id.getText().equalsIgnoreCase(setores.get(i).getId()) && nome.getText().equalsIgnoreCase(setores.get(i).getNome())) {
					setores.remove(i);
					validar = true;
				}
			}
			if (validar == true) {
				atualizaDados(setores);
				msg("delete", nome.getText());
				limpaCampos();
			} else {
				validar = false;
				msg("errordelete", id.getText());
			}
		} else {
			pesquisar();
		}
	}

	public void gravar() {
		new SetorArquivo();
		Setor setor = new Setor();
		validar = false;
		if (!nome.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (nome.getText().equalsIgnoreCase(setores.get(i).getNome())){
					msg("erroredit", setores.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
				setor.setId(id.getText());
				setor.setNome(nome.getText());
				setores.add(setor);
				msg("save", nome.getText());
				atualizaDados(setores);
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
