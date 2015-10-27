package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.IArquivosController;
import model.Obra;

public class ObraArquivoImpl implements IArquivosController{
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
				//System.out.println(linha);
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("Nome do Artista: "+((Obra) object).getArtista().getNome());
		buffer.append("\r\n");
		buffer.append("Nome da obra: "+((Obra) object).getNomeObra());
		buffer.append("\r\n");
		buffer.append("Descri��o: "+((Obra) object).getDescricaoObra());
		buffer.append("\r\n");
		buffer.append("Categoria: "+((Obra) object).getCategoria().getNome());
		buffer.append("\r\n");
		buffer.append("Data: "+((Obra) object).getDataComposicao());
		buffer.append("\r\n");
		buffer.append("Imagem: "+((Obra) object).getImagem());
		buffer.append("\r\n");
		buffer.append("Material: "+((Obra) object).getMaterial().getNome());
		buffer.append("\r\n");
		buffer.append("Setor: "+((Obra) object).getSetor().getNome());
		buffer.append("\r\n");
		buffer.append("Valor: "+((Obra) object).getPreco());
		buffer.append("\r\n");
		buffer.append("---------------------------");
		buffer.append("\r\n");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluiDadosArquivo(String diretorio, String arquivo, String[] registro) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
