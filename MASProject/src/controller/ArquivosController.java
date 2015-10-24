package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{
	
	private StringBuffer buffer;
	
	public String getBuffer() {
		return buffer.toString();
	}

	@Override
	public void leArquivo(String diretorio, String arquivo) throws IOException {
		File arq = new File(diretorio, arquivo);
		if(arq.exists()){
			FileInputStream leFluxo = new FileInputStream(arq);
			InputStreamReader leDados = new InputStreamReader(leFluxo);
			BufferedReader bufferLeitura = new BufferedReader(leDados);
			String linha = bufferLeitura.readLine();
			buffer = new StringBuffer();
			while(linha != null){
				System.out.println(linha);
				buffer.append(linha);
				buffer.append(";");
				linha = bufferLeitura.readLine();
			}
			bufferLeitura.close();
			leDados.close();
			leFluxo.close();
		} else {
			throw new IOException("Arquivo inexistente");
		}
	}
	
	@Override
	public void escreveArquivo(String diretorio, String arquivo, Object object)
			throws IOException {
		String linha = JOptionPane.showInputDialog("Digite algo: ");
		StringBuffer buffer = new StringBuffer();
		while (!linha.trim().equals("")){
			buffer.append(linha);
			buffer.append("\r\n"); //no bloco de notas a quebra de linha é \r\n, os demais ignoram o \r
			linha = JOptionPane.showInputDialog("Digite algo: ");
		}
		File arq = new File(diretorio, arquivo);
		boolean arquivoExiste;
		if(arq.exists()){
			arquivoExiste = true;
		}else{
			arq.createNewFile();
			arquivoExiste = false;
		}
		FileWriter escreveArquivo = new FileWriter(arq, arquivoExiste);
		PrintWriter gravaDados = new PrintWriter(escreveArquivo);
		gravaDados.write(buffer.toString());
		gravaDados.flush();
		gravaDados.close();
		escreveArquivo.close();
//		Desktop desk = Desktop.getDesktop();
//		desk.open(arq);
	}

	@Override
	public void leDiretorio(String diretorio) throws IOException {
		File dir = new File(diretorio); //Abrindo o conteudo do diretório
		if(dir.exists()){
			File[] listaArquivos = dir.listFiles();
			for (File f : listaArquivos){
				if (f.isDirectory()){
					System.out.println("["+f.getName()+"]");
				}
			}
			for(File f : listaArquivos){
				if(f.isFile()){
					System.out.println(f.getName());
				}
			}
		} else {
			throw new IOException("Diretório inexistente");
		}
	}
}