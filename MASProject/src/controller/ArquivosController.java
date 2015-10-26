package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArquivosController implements IArquivosController {

	private StringBuffer buffer;

	public String getBuffer() {
		return buffer.toString();
	}

	@Override
	public void leArquivo(String diretorio, String arquivo) throws IOException {
		File arq = new File(diretorio, arquivo);
		if (arq.exists()) {
			FileInputStream leFluxo = new FileInputStream(arq);
			InputStreamReader leDados = new InputStreamReader(leFluxo);
			BufferedReader bufferLeitura = new BufferedReader(leDados);
			String linha = bufferLeitura.readLine();
			buffer = new StringBuffer();
			while (linha != null) {
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
	public void escreveArquivo(String diretorio, String arquivo, String texto, Object object) throws IOException {
		String linha = texto;
		StringBuffer buffer = new StringBuffer();
			buffer.append(linha);
			buffer.append("\r\n"); // no bloco de notas a quebra de linha Ã© \r\n, os demais ignoram o \r
		File arq = new File(diretorio, arquivo);
		boolean arquivoExiste;
		if (arq.exists()) {
			arquivoExiste = true;
		} else {
			arq.createNewFile();
			arquivoExiste = false;
		}
		FileWriter escreveArquivo = new FileWriter(arq, arquivoExiste);
		PrintWriter gravaDados = new PrintWriter(escreveArquivo);
		gravaDados.write(buffer.toString());
		gravaDados.flush();
		gravaDados.close();
		escreveArquivo.close();
	}

	@Override
	public void leDiretorio(String diretorio) throws IOException {
		File dir = new File(diretorio); // Abrindo o conteudo do diretÃ³rio
		if (dir.exists()) {
			File[] listaArquivos = dir.listFiles();
			for (File f : listaArquivos) {
				if (f.isDirectory()) {
					System.out.println("[" + f.getName() + "]");
				}
			}
			for (File f : listaArquivos) {
				if (f.isFile()) {
					System.out.println(f.getName());
				}
			}
		} else {
			throw new IOException("DiretÃ³rio inexistente");
		}
	}
 
	//não testado************
	@Override
	public void excluiDadosArquivo(String diretorio, String arquivo, String registro[]) throws IOException {
		File file = new File(diretorio, arquivo);
		try {
			FileReader freader = new FileReader(file);
			BufferedReader breader = new BufferedReader(freader);
			
			String linha = breader.readLine();
			
			//lista para gravar o que não for igual ao conteúdo do vetor registro
			ArrayList <String>salvar = new ArrayList<String>();
			System.out.println(registro[0]);
			while(linha != null){
				//se a linha não for igual a id do selecionado, salva.
				if (linha.equals(registro[0]) == false){
					salvar.add(linha);
				}else{
					/*se a linha for a id do selecionado, o laço conta o até a posição 
					final do vetor, para gravar somente o que vir depois*/
				for(int i=0;i<registro.length;i++){
						linha = breader.readLine();
				 }
				linha=breader.readLine();
				}
			}
			freader.close();
			breader.close();
			FileWriter fwriter2 = new FileWriter(file, true);
			fwriter2.close();
			
			FileWriter fwriter = new FileWriter(file);
			BufferedWriter bwriter = new BufferedWriter(fwriter);
			//salva todo o conteudo da lista novamente no arquivo
			for (int i = 0; i < salvar.size(); i++){
				bwriter.write(salvar.get(i));
				bwriter.newLine();
			}
			bwriter.close();
			fwriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}