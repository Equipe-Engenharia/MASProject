package controller;

import java.io.IOException;

public interface ArquivosICtrl {

	public void leArquivo(String diretorio, String arquivo) throws IOException;
	public void escreveArquivo(String diretorio, String arquivo, String texto,  Object object) throws IOException;
	public void leDiretorio(String diretorio) throws IOException; 
	public void excluiDadosArquivo(String diretorio,String arquivo, String registro[]) throws IOException;
	
}
