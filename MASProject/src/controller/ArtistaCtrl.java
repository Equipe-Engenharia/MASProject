package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Artista;
import persistence.ArtistaArquivo;

public class ArtistaCtrl implements ComponentListener {

	private JTextField idArtista, nomeArtista;
	private JButton btGravar;
	private JLabel msgGravado, msgVazio;
	private List<Artista> artistas;
	private static int contador = 1;
	private ArquivosCtrl ctrlArquivos;

	public ArtistaCtrl(JTextField idArtista, JTextField nomeArtista,
			JButton btnGravar, JLabel msgGravar, JLabel msgVazio) {

		this.idArtista = idArtista;
		this.btGravar = btnGravar;
		this.msgGravado = msgGravar;
		this.msgVazio = msgVazio;
		this.nomeArtista = nomeArtista;
		this.artistas = new ArrayList<Artista>();
		
		lerArtista();
	}

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idArtista.setText("ART" + id);
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
		ArtistaArquivo artistalImpl = new ArtistaArquivo();

		artista.setId(idArtista.getText());
		artista.setNome(nomeArtista.getText());

		if (!nomeArtista.getText().isEmpty()) {
			try {
				artistalImpl.escreveArquivo("../MASProject/dados/", "artistas", nomeArtista.getText(), artista);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			msgGravado.setText(nomeArtista.getText() + " salvo com sucesso!");
//			msgGravado.setVisible(true);
			JOptionPane.showMessageDialog(null,"Artista Gravado");
			nomeArtista.setText(null);
			gerarId();
		} else {
			msgGravado.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

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
				nomeArtista.setText(null);
				contador += 1;
			}
			
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