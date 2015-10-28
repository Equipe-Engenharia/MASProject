package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Artista;

public class Operacoes implements IArtista {

	Artista art=new Artista();

	public void lerArquivo(String diretorio, String arquivo, JTextField paraPesquisar) throws IOException {
		File arq = new File(diretorio, arquivo);
		if (arq.exists()) {
			
			String conversaoPesquisa = paraPesquisar.getText();
			FileInputStream leFluxo = new FileInputStream(arq);
			InputStreamReader leDados = new InputStreamReader(leFluxo);
			BufferedReader bufferLeitura = new BufferedReader(leDados);
			String linha = bufferLeitura.readLine();
			while (linha != null) {

				linha = bufferLeitura.readLine();
				if (linha == conversaoPesquisa)

				{
					JOptionPane.showMessageDialog(null, "Artista Na base de Dados");

				}
			}

			bufferLeitura.close();
			leDados.close();
			leFluxo.close();

		}

	}


	public void escreverArquivo(String diretorio, String arquivo, JTextField nomeDoArtista, JTextField idArtista)
			throws IOException {

		JTextField linhaDoArtista = nomeDoArtista;
		StringBuffer buffer = new StringBuffer();
		String conversao = linhaDoArtista.getText();

		buffer.append(conversao);
		buffer.append("\r\n");

		JTextField linhaDoId = idArtista;
		StringBuffer buffer2 = new StringBuffer();
		int id = (int) (1000 + Math.random() * 10);

		buffer2.append(id);
		buffer2.append("\r\n");

		File arq = new File(diretorio, arquivo);
		boolean arquivoExiste;
		if (arq.exists()) {
			arquivoExiste = true;

		} else {
			arq.createNewFile();
			arquivoExiste = false;

		}
		FileWriter escreverArquivo = new FileWriter(arq, arquivoExiste);
		PrintWriter gravaDados = new PrintWriter(escreverArquivo);
		gravaDados.write(buffer.toString());
		gravaDados.write(buffer2.toString());
		gravaDados.flush();
		gravaDados.close();
		escreverArquivo.close();
		JOptionPane.showMessageDialog(null, "Artista cadastrado ");
		

	}

	public void lerDiretorio(String diretorio) throws IOException {
		File dir = new File(diretorio);
		if (dir.exists()) {
			File[] listaArquivos = dir.listFiles();
			for (File f : listaArquivos) {
				if (f.isDirectory()) {
					System.out.println("[" + f.getName() + "]");
				}

			}
			for (File f : listaArquivos) {
				if (f.isFile()) {
					System.out.println("[" + f.getName() + "]");
				} else {
					System.out.println("Não existe esse diretorio");
					throw new IOException("Diretorio Inexistente");
				}

			}
		}

	}

}
