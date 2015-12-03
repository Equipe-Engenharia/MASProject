package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.ArquivosICtrl;
import model.AgendamentoMdl;

public class AgendamentoFile implements ArquivosICtrl {
	StringBuffer buffer;

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
				// System.out.println(linha);
				buffer.append(linha);
				buffer.append(";");
				linha = bufferLeitura.readLine();
			}
			bufferLeitura.close();
			leDados.close();
			leFluxo.close();
		} else {
			//throw new IOException("Arquivo inexistente");
			File novoArquivo = new File(diretorio, arquivo);
			novoArquivo.createNewFile();
			buffer = new StringBuffer();
			buffer.append("");
		}
	}

	@Override
	public void escreveArquivo(String diretorio, String arquivo, String texto, Object object) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ID             : " + ((AgendamentoMdl) object).getId());
		buffer.append("\r\n");
		buffer.append("ID Instituto   : " + ((AgendamentoMdl) object).getInstitutoId());
		buffer.append("\r\n");
		buffer.append("Instituto      : " + ((AgendamentoMdl) object).getInstituto());
		buffer.append("\r\n");
		buffer.append("Telefone       : " + ((AgendamentoMdl) object).getFone());
		buffer.append("\r\n");
		buffer.append("ID Responsável : " + ((AgendamentoMdl) object).getResponsavelId());
		buffer.append("\r\n");
		buffer.append("Responsável    : " + ((AgendamentoMdl) object).getResponsavel());
		buffer.append("\r\n");
		buffer.append("Tipo           : " + ((AgendamentoMdl) object).getTipo());
		buffer.append("\r\n");
		buffer.append("Data           : " + ((AgendamentoMdl) object).getData());
		buffer.append("\r\n");
		buffer.append("Período        : " + ((AgendamentoMdl) object).getPeriodo());
		buffer.append("\r\n");
		buffer.append("Qtd. Pessoas   : " + ((AgendamentoMdl) object).getQtd());
		buffer.append("\r\n");
		buffer.append("ID Expo        : " + ((AgendamentoMdl) object).getExpoId());
		buffer.append("\r\n");
		buffer.append("Nome Expo      : " + ((AgendamentoMdl) object).getExpoNome());
		buffer.append("\r\n");
		buffer.append("Nome Ingresso  : " + ((AgendamentoMdl) object).getIngresso());
		buffer.append("\r\n");
		buffer.append("Valor Unitário : " + ((AgendamentoMdl) object).getVlrUnitario());
		buffer.append("\r\n");
		buffer.append("Valor Total    : " + ((AgendamentoMdl) object).getVlrTotal());
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

	}

	@Override
	public void excluiDadosArquivo(String diretorio, String arquivo, String[] registro) throws IOException {

	}
}