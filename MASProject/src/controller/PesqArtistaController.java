package controller;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

public class PesqArtistaController{
	 
	private ArquivosController arqController;
	private String[] artista;
	
	public PesqArtistaController(){
		this.artista = preencherComboBoxArtista();
	}
	
	public PesqArtistaController(JButton btnGravar, JTextField tfNomeArtista){
		
	}
	
	public String[] getArtista(){
		return artista;
	}
	
	private String[] preencherComboBoxArtista(){
		String linha = new String();
		String nArtista[] = null; 
		arqController = new ArquivosController();
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
