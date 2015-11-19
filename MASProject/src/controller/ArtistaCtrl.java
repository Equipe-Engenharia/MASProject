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

import model.ArtistaMdl;
import persistence.ArtistaFile;

public class ArtistaCtrl implements ComponentListener {

	private JPanel form;
	private JTextField txtId, txtNome;
	private List<ArtistaMdl> artistas;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivos = new ArquivosCtrl();
	ArtistaFile arquivo = new ArtistaFile();
	private String[] artista;

	public ArtistaCtrl(JPanel form, JTextField txtId, JTextField txtNome) {

		this.txtId = txtId;
		this.txtNome = txtNome;
		this.artistas = new ArrayList<ArtistaMdl>();
		
		lerArquivo();
	}
	
	public ArtistaCtrl(){
		
		this.artista = preencherComboBoxArtista();
	}

	
	// METODOS DE SUPORTE ////////////////////////

	
	public void gerarId() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("ART" + NewId);
	}
	
	public String[] getArtista(){
		
		return artista;
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
	
	
	// PREENCHE COMBOBOX /////////////////////
	
	
	public String[] preencherComboBoxArtista(){
		
		String linha = new String();
		String nArtista[] = null; 
		StringBuffer nomeArtista;
		arquivos = new ArquivosCtrl();
		try {
			arquivos.leArquivo("../MASProject/dados", "artistas");
			linha = arquivos.getBuffer();
			nArtista = linha.split(";");
			nomeArtista = new StringBuffer();
			for(String nome : nArtista){
				if(nome.contains("Artista")){
					nomeArtista.append(nome.substring(10));
					nomeArtista.append(";");
				}
			}
			linha = nomeArtista.toString();
			nArtista = linha.split(";");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nArtista;
	}
	

	// CRUD //////////////////////////

	
	public void lerArquivo() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
	
		try {
			arquivos.leArquivo("../MASProject/dados/", "artistas");
			linha = arquivos.getBuffer();
			String[] listaArtista = linha.split(";");
			for (String s : listaArtista) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					ArtistaMdl artista = new ArtistaMdl();
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

	public void atualizaDados(List<ArtistaMdl> listArtistas) {
		File f = new File("../MASProject/dados/artistas");
		f.delete();	
		for (ArtistaMdl artista : listArtistas) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "artistas", "", artista);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void pesquisar() {

		ArrayList<ArtistaMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtNome.getText().isEmpty() || !txtId.getText().isEmpty()) {

			for (int i = 0; i < artistas.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(artistas.get(i).getId())) {
					txtId.setText(artistas.get(i).getId());
					txtNome.setText(artistas.get(i).getNome());
					validar = true;
				} else if (artistas.get(i).getNome().toLowerCase().startsWith(txtNome.getText().toLowerCase())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < artistas.size(); i++) {

					boolean filtro = artistas.get(i).getNome().toLowerCase().startsWith(txtNome.getText().toLowerCase());
					if (filtro == true) {
						ArtistaMdl item = new ArtistaMdl();
						item.setId(artistas.get(i).getId());
						item.setNome(artistas.get(i).getNome());
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
				for (int i = 0; i < artistas.size(); i++) {
					if (pesquisa.equalsIgnoreCase(artistas.get(i).getId())) {
						txtId.setText(artistas.get(i).getId());
						txtNome.setText(artistas.get(i).getNome());
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
		
		ArtistaMdl artista = new ArtistaMdl();
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < artistas.size(); i++) {
				if (!txtId.getText().equalsIgnoreCase(artistas.get(i).getId()) && txtNome.getText().equalsIgnoreCase(artistas.get(i).getNome())) {				
					msg("erroredit",artistas.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < artistas.size(); i++) {
					if (txtId.getText().equalsIgnoreCase(artistas.get(i).getId())) {
						artista.setId(txtId.getText());
						artista.setNome(txtNome.getText());
						artistas.set(i, artista);
						atualizaDados(artistas);
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
			for (int i = 0; i < artistas.size(); i++) {
				if (txtId.getText().equalsIgnoreCase(artistas.get(i).getId()) && txtNome.getText().equalsIgnoreCase(artistas.get(i).getNome())) {
					artistas.remove(i);
					validar = true;
				}
			}
			if (validar == true) {
				msg("deleteconfirm", txtNome.getText());
				if (validar == false){
					atualizaDados(artistas);
					msg("delete", txtNome.getText());
					limpaCampos();
				} else {
					artistas.clear();
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
		
		new ArtistaFile();
		ArtistaMdl artista = new ArtistaMdl();
		validar = false;
		if (!txtNome.getText().isEmpty()) {
			for (int i = 0; i < artistas.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(artistas.get(i).getNome())){
					msg("erroredit", artistas.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
			artista.setId(txtId.getText());
			artista.setNome(txtNome.getText());
			artistas.add(artista);
			msg("save", txtNome.getText());
			atualizaDados(artistas);
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