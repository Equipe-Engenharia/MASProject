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

import model.Artista;
import persistence.ArtistaArquivo;

public class ArtistaCtrl implements ComponentListener {

	private JPanel form;
	private JTextField id, nome;
	private List<Artista> artistas;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivo = new ArquivosCtrl();
	ArtistaArquivo formatar = new ArtistaArquivo();

	public ArtistaCtrl(JPanel form, JTextField id, JTextField nome) {

		this.id = id;
		this.nome = nome;
		this.artistas = new ArrayList<Artista>();
		
		lerArtista();
	}

	// METODOS DE SUPORTE ////////////////////////

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		id.setText("ART" + NewId);
	}

	public void limpaCampos() {
		nome.setText(null);
		id.setText(null);
	}

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Artista.", 
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.", 
					"Pesquisa de Artista", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite para pesquisar!", 
					"Registro de Artista",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' salvo com sucesso.", 
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nNão foi possível apagar o registro: " + id.getText() + " "
					+ nome.getText() + "!\nVerifique sua digitação!", 
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' editado com sucesso.", 
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroredit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' já existe!",
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' apagado com sucesso.", 
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errordelete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' não pode ser alterado para a exclusão.",
					"Registro de Artista", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null, 
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nVolte ao trabalho e conserte isso!!!", 
					"Erro no Controller Artista", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}

	// CRUD //////////////////////////

	public void lerArtista() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
	
		try {
			arquivo.leArquivo("../MASProject/dados/", "artistas");
			linha = arquivo.getBuffer();
			String[] listaArtista = linha.split(";");
			for (String s : listaArtista) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					Artista artista = new Artista();
					artista.setId(list.get(0));
					artista.setNome(list.get(1));
					artistas.add(artista);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizaDados(List<Artista> listArtistas) {
		File f = new File("../MASProject/dados/artistas");
		f.delete();	
		for (Artista artista : listArtistas) {
			try {
				formatar.escreveArquivo("../MASProject/dados/", "artistas", "", artista);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisar() {

		ArrayList<Artista> listArtista = new ArrayList<>();
		String pesquisa ="";
		if (!nome.getText().isEmpty() || !id.getText().isEmpty()) {

			for (int i = 0; i < artistas.size(); i++) {
				if (nome.getText().equalsIgnoreCase(artistas.get(i).getId())) {
					id.setText(artistas.get(i).getId());
					nome.setText(artistas.get(i).getNome());
					validar = false;
				} else if (nome.getText().equalsIgnoreCase(artistas.get(i).getNome())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < artistas.size(); i++) {

					boolean filtro = nome.getText().equalsIgnoreCase(artistas.get(i).getNome());
					if (filtro == true) {
						Artista item = new Artista();
						item.setId(artistas.get(i).getId());
						item.setNome(artistas.get(i).getNome());
						listArtista.add(item);
					}
				}
				String[] filtro = new String[listArtista.size()];
				for (int i = 0; i < listArtista.size(); i++) {
					filtro[i] = listArtista.get(i).getId();
					pesquisa = listArtista.get(i).getId();
				}
				if (filtro != null && filtro.length > 1) {
					pesquisa = (String) JOptionPane.showInputDialog(form, "Escolha o ID:\n", "Selecione o ID",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				}
				for (int i = 0; i < artistas.size(); i++) {
					if (pesquisa.equalsIgnoreCase(artistas.get(i).getId())) {
						id.setText(artistas.get(i).getId());
						nome.setText(artistas.get(i).getNome());
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
		Artista artista = new Artista();
		validar = false;
		if (!id.getText().isEmpty()) {
			for (int i = 0; i < artistas.size(); i++) {
				if (nome.getText().equalsIgnoreCase(artistas.get(i).getNome())) {				
					msg("erroredit",artistas.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < artistas.size(); i++) {
					if (id.getText().equalsIgnoreCase(artistas.get(i).getId())) {
						artista.setId(id.getText());
						artista.setNome(nome.getText());
						artistas.set(i, artista);
						atualizaDados(artistas);
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
			for (int i = 0; i < artistas.size(); i++) {
				if (id.getText().equalsIgnoreCase(artistas.get(i).getId()) && nome.getText().equalsIgnoreCase(artistas.get(i).getNome())) {
					artistas.remove(i);
					validar = true;
				}
			}
			if (validar == true) {
				atualizaDados(artistas);
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
		new ArtistaArquivo();
		Artista artista = new Artista();
		validar = false;
		if (!nome.getText().isEmpty()) {
			for (int i = 0; i < artistas.size(); i++) {
				if (nome.getText().equalsIgnoreCase(artistas.get(i).getNome())){
					msg("erroredit", artistas.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
			artista.setId(id.getText());
			artista.setNome(nome.getText());
			artistas.add(artista);
			msg("save", nome.getText());
			atualizaDados(artistas);
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