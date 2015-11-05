package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Artista;
import model.Categoria;
import persistence.ArtistaArquivo;

public class ArtistaEditCtrl implements ComponentListener {

	private JTextField nomeArtista, idArtista;
	private JButton btApagar, btGravar;
	private JLabel msgGravar, msgVazio;
	private List<Artista> artistas;
	private static int contador = 1;
	StringBuffer buffer;

	public String getBuffer() {
		return buffer.toString();
	}

	private ArquivosCtrl ctrlArquivos;

	public ArtistaEditCtrl(JTextField idArtista, JTextField nomeArtista,
			JButton btnApagar, JButton btnGravar, JLabel msgGravar, JLabel msgVazio) {

		this.idArtista = idArtista;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		this.msgGravar = msgGravar;
		this.msgVazio = msgVazio;
		this.nomeArtista = nomeArtista;
		this.artistas = new ArrayList<Artista>();
		
		lerArtista();
	}
	Artista artista = new Artista();
	public void lerArtista() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		ctrlArquivos = new ArquivosCtrl();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "artistas");
			linha = ctrlArquivos.getBuffer();
			String[] listaArtista = linha.split(";");
			for (String s : listaArtista) {
				String text = s.replaceAll(".*:", "");
				list.add(text);
				if (s.contains("---")) {
					Artista artista = new Artista();
					artista.setId(list.get(0));
					artista.setNome(list.get(1));
					artistas.add(artista);
					list.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*for(Material a :  artistas){
			System.out.println(a.getNome());
		}*/
	}

	public void gravarArtista() {
		Artista artista = new Artista();
		ArtistaArquivo artistaImpl = new ArtistaArquivo();

		artista.setId(idArtista.getText());
		artista.setNome(nomeArtista.getText());

		if (!nomeArtista
				.getText().isEmpty()) {
			try {
				artistaImpl.escreveArquivo("../MASProject/dados/", "artistas", nomeArtista.getText(), artista);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			msgGravar.setText(nomeArtista.getText() + " salvo com sucesso!");
//			msgGravar.setVisible(true);
			 JOptionPane.showMessageDialog(null, "Artista  gravado com sucesso!");
			nomeArtista.setText(null);
		} else {
			msgGravar.setVisible(false);
			msgVazio.setVisible(true);
		}
	}

	
		public void   pesquisarArtista(String diretorio, String arquivo) throws IOException {
			
			Artista artista = new Artista();
	
				File arq = new File(diretorio, arquivo);
				if (arq.exists()) {
					
					
					FileInputStream leFluxo = new FileInputStream(arq);
					InputStreamReader leDados = new InputStreamReader(leFluxo);
					BufferedReader bufferLeitura = new BufferedReader(leDados);
					String linha = bufferLeitura.readLine();
					buffer = new StringBuffer();
					String conversaoPesquisa = nomeArtista.getText();
					String conversaoPesquisa2 =  idArtista.getText();
					if(nomeArtista.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Campo Vazio!Digite o que deseja procurar");
						
					}
					else{
					        while (linha != null) {
						    linha = bufferLeitura.readLine();
						
						    if (linha.equals(conversaoPesquisa)||linha.equals(conversaoPesquisa2))

						    {
							
							    JOptionPane.showMessageDialog(null, "Artista  encontrado na base de dados");
							
						    }
					
						

					                               } 

					      bufferLeitura.close();
					      leDados.close();
					        leFluxo.close();
					

				       }
					JOptionPane.showMessageDialog(null, "Escolha uma das opções abaixo");
				}
				

			}

		
		public void editar(String diretorio, String arquivo,String diretorio2, String arquivo2,Object object) throws IOException {
			artista.setNome(nomeArtista.getText());

			File arq = new File(diretorio, arquivo);
			if (arq.exists()) {
				
				String editado;
				FileInputStream leFluxo = new FileInputStream(arq);
				InputStreamReader leDados = new InputStreamReader(leFluxo);
				BufferedReader bufferLeitura = new BufferedReader(leDados);
				String linha = bufferLeitura.readLine();
				buffer = new StringBuffer();
				String conversaoPesquisa =  nomeArtista.getText();
				if(nomeArtista.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Campo Vazio!");
					
				}
				else{
				while (linha != null ) {
					linha = bufferLeitura.readLine();
					
					
					 if (linha.equals(conversaoPesquisa))
					{
						
						StringBuffer buffer = new StringBuffer();
						buffer.append(JOptionPane.showInputDialog("Digite o novo nome desse artista "));
						JOptionPane.showMessageDialog(null,"Artista alterado com sucesso");
						buffer.append("\r\n");
						buffer.append( ((Artista) object).getId());
						buffer.append("\r\n");
						File arq1 = new File(diretorio2, arquivo2);
						boolean arquivoExiste;
						if (arq1.exists()) {
							arquivoExiste = true;
						} else {
							arq1.createNewFile();
							arquivoExiste = false;
						}
						FileWriter escreveArquivo = new FileWriter(arq1, arquivoExiste);
						PrintWriter gravaDados = new PrintWriter(escreveArquivo);
						gravaDados.write(buffer.toString());
						gravaDados.flush();
						gravaDados.close();
						escreveArquivo.close();


						
					}else
					{
						

						StringBuffer buffer = new StringBuffer();
						buffer.append( ((Artista) object).getNome());
						buffer.append("\r\n");
						buffer.append( ((Artista) object).getId());
						buffer.append("\r\n");
						File arq1 = new File(diretorio2, arquivo2);
						boolean arquivoExiste;
						if (arq1.exists()) {
							arquivoExiste = true;
						} else {
							arq1.createNewFile();
							arquivoExiste = false;
						}
						FileWriter escreveArquivo = new FileWriter(arq1, arquivoExiste);
						PrintWriter gravaDados = new PrintWriter(escreveArquivo);
						gravaDados.write(buffer.toString());
						gravaDados.flush();
						gravaDados.close();
						escreveArquivo.close();
						
					}

					
						
					
					
				}

				bufferLeitura.close();
				leDados.close();
				leFluxo.close();


			}
				//Se conseguir arrumar o problema com os getters depois
			// Precisa Abilitar abaixo para substituir para o arquivo já editado
//			 new File(arquivo).delete();
//			    new File(arquivo2).renameTo(new File(arquivo));

		}


		}

		public void apagarArtista(String diretorio, String arquivo,String diretorio3, String arquivo3,Object object) throws IOException {
			

				File arq = new File(diretorio, arquivo);
				if (arq.exists()) {
					
					FileInputStream leFluxo = new FileInputStream(arq);
					InputStreamReader leDados = new InputStreamReader(leFluxo);
					BufferedReader bufferLeitura = new BufferedReader(leDados);
					String linha = bufferLeitura.readLine();
					buffer = new StringBuffer();
					
					String conversaoPesquisa = nomeArtista.getText();
					String conversaoPesquisa2 = idArtista.getText();
					while (linha != null) {
						linha = bufferLeitura.readLine();
						
						

						if (linha.equals(conversaoPesquisa)||linha.equals(conversaoPesquisa2))
						{
							JOptionPane.showMessageDialog(null, "Artista Apagado");
						}
						else
						{
							

							StringBuffer buffer = new StringBuffer();
							buffer.append(((Artista) object).getId());
							buffer.append("\r\n");
							buffer.append(((Artista) object).getNome());
							buffer.append("\r\n");
						    buffer.append("\r\n");
							File arq1 = new File(diretorio3, arquivo3);
							boolean arquivoExiste;
							if (arq1.exists()) {
								arquivoExiste = true;
							} else {
								arq1.createNewFile();
								arquivoExiste = false;
							}
							FileWriter escreveArquivo = new FileWriter(arq1, arquivoExiste);
							PrintWriter gravaDados = new PrintWriter(escreveArquivo);
							gravaDados.write(buffer.toString());
							gravaDados.flush();
							gravaDados.close();
							escreveArquivo.close();
						}
							
						
						
					}

					bufferLeitura.close();
					leDados.close();
					leFluxo.close();

				}
				//Se conseguir arrumar o problema com os getters depois
				// Precisa Abilitar abaixo para substituir para o arquivo já editado
//				 new File(arquivo).delete();
//				    new File(arquivo3).renameTo(new File(arquivo));

			}

	public ActionListener pesquisarArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				pesquisarArtista("../MASProject/dados/", "artistas");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
	public ActionListener apagarArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				apagarArtista("../MASProject/dados/", "artistas","../MASProject/dados/", "apagado",artista);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	public ActionListener gravarArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarArtista();
		}
	};

	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (contador == 1) {
				idArtista.setText(null);
				nomeArtista.setText(null);
				contador += 1;
			}

			btApagar.setEnabled(true);
			btGravar.setEnabled(true);
			msgGravar.setVisible(false);
			msgVazio.setVisible(false);
		}
	};

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
