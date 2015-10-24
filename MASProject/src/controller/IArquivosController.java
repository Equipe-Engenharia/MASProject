package controller;

import java.io.IOException;

public interface IArquivosController {

	public void leArquivo(String diretorio, String arquivo) throws IOException;
	public void escreveArquivo(String diretorio, String arquivo) throws IOException;
	public void leDiretorio(String diretorio) throws IOException; 
	
}
