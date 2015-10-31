package controller;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ArtistaPesqCtrl{
	 
	private ArquivosCtrl arqController;
	private String[] artista;
	
	public ArtistaPesqCtrl(){
		this.artista = preencherComboBoxArtista();
	}
	
	public ArtistaPesqCtrl(JButton btnGravar, JTextField tfNomeArtista){
		
	}
	
	public String[] getArtista(){
		return artista;
	}
	
	private String[] preencherComboBoxArtista(){
		String linha = new String();
		String nArtista[] = null; 
		arqController = new ArquivosCtrl();
		try {
			arqController.leArquivo("../MASProject/dados", "artistas");
			linha = arqController.getBuffer();
			nArtista = linha.split(";");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nArtista;
	}
	
	
}
