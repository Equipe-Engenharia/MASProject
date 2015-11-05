package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.Artista;

public class ArtistaPesqCtrl{
	 
	private ArquivosCtrl arqController;
	private String[] artista;
	
	private ArquivosCtrl ctrlArquivos;

	
	public ArtistaPesqCtrl(JButton btnGravar, JTextField tfNomeArtista,JComboBox<String> cbCategoria){
	}
	
	public String[] getArtista(){
		return artista;
	}
	JComboBox<String> combo;

	public void preencherComboBoxArtista() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader( "C:/artistas.txt"));  
		String linha;  
		do {  
		    linha = reader.readLine();  
		 //   JComboBox<String> combo;
			if (linha != null) combo.addItem(linha);  
		} while (linha != null);  
		reader.close();  
	}

	
}
