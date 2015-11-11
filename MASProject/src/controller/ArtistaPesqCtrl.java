package controller;
import java.io.IOException;


public class ArtistaPesqCtrl{
	private ArquivosCtrl arqController;
	private String[] artista;
	
	public ArtistaPesqCtrl(){
		this.artista = preencherComboBoxArtista();
	}
	
	public String[] getArtista(){
		return artista;
	}
	
	//método modificado novamente, por favor não mexer - Vitor
	//
	private String[] preencherComboBoxArtista(){
		String linha = new String();
		String nArtista[] = null; 
		StringBuffer nomeArtista;
		arqController = new ArquivosCtrl();
		try {
			arqController.leArquivo("../MASProject/dados", "artistas");
			linha = arqController.getBuffer();
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
}