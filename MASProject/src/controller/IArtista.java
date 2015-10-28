package controller;

import java.io.IOException;

import javax.swing.JTextField;

public interface IArtista {

	public void lerArquivo(String diretorio,String arquivo,JTextField paraPesquisar)throws IOException;
	  public void escreverArquivo(String diretorio,String arquivo,JTextField nomeDoArtista,JTextField idArtista)throws IOException;
	  public void lerDiretorio(String diretorio)throws IOException;	  
		  
	
}