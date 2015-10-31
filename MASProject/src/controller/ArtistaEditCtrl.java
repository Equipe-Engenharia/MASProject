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
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Artista;
import persistence.ArtistaArquivo;

public class ArtistaEditCtrl implements ComponentListener {

	private JTextField nomeArtista, idArtista;
	private JButton btApagar, btGravar;
	private JLabel msgGravar, msgVazio;
	private List<Artista> artistas;
	private static int contador = 1;

	private ArquivosCtrl ctrlArquivos;

	public ArtistaEditCtrl(JTextField idArtista, JTextField nomeArtista,
			JButton btnApagar, JButton btnGravar, JLabel msgGravar, JLabel msgVazio) {

		this.idArtista = idArtista;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		this.msgGravar = msgGravar;
		this.msgVazio = msgVazio;
		this.nomeArtista = nomeArtista;
		this.artistas = new ArrayList<Artista>();
		
		lerArtista();
	}
	
	public void lerArtista() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		ctrlArquivos = new ArquivosCtrl();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "artistas");
			linha = ctrlArquivos.getBuffer();
			String[] listaArtista = linha.split(";");
			for (String s : listaArtista) {
				String text = s.replaceAll(".*:", "");
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
		/*for(Material a :  artistas){
			System.out.println(a.getNome());
		}*/
	}

	public void gravarArtista() {
		Artista artista = new Artista();
		ArtistaArquivo artistaImpl = new ArtistaArquivo();

		artista.setId(idArtista.getText());
		artista.setNome(nomeArtista.getText());

		if (!nomeArtista
				.getText().isEmpty()) {
			try {
				artistaImpl.escreveArquivo("../MASProject/dados/", "artistas", nomeArtista.getText(), artista);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msgGravar.setText(nomeArtista.getText() + " salvo com sucesso!");
			msgGravar.setVisible(true);
			nomeArtista.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	public void pesquisarArtista() {

		if (!nomeArtista.getText().isEmpty() || !idArtista.getText().isEmpty()) {
			msgGravar.setText(nomeArtista.getText() + " localizado com sucesso!");
			msgGravar.setVisible(true);
			nomeArtista.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setText("Por favor, use um dos campos de Pesquisa!");
			msgVazio.setVisible(true);
		}
		
	}

	public void apagarArtista() {
		
		if (!nomeArtista.getText().isEmpty()) {
			msgGravar.setText(nomeArtista.getText() + " excluído com sucesso!");
			msgGravar.setVisible(true);
			nomeArtista.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}

	}

	public ActionListener pesquisarArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarArtista();
		}
	};
	
	public ActionListener apagarArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			apagarArtista();
		}
	};

	public ActionListener gravarArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarArtista();
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
				idArtista.setText(null);
				nomeArtista.setText(null);
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
