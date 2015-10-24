package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.FormAcervo;

public class AcervoController {
	private JLabel imagem;
	private JButton inserir;
	private JButton remover;
	
	public AcervoController(JLabel imagem,  JButton inserir, JButton remover){
		this.inserir = inserir;
		this.imagem = imagem;
		this.remover = remover;
	}
	
	public void procuraArquivo() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de imagem (jpg, png, gif)", "jpg", "png",
				"gif");
		String diretorioBase = System.getProperty("user.home") + "/Desktop";
		File dir = new File(diretorioBase);

		JFileChooser choose = new JFileChooser();
		choose.setCurrentDirectory(dir);
		choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choose.setAcceptAllFileFilterUsed(false);
		choose.addChoosableFileFilter(filtro);
		String caminhoArquivo = "";

		int retorno = choose.showOpenDialog(null);
		if (retorno == JFileChooser.APPROVE_OPTION) {
			caminhoArquivo = choose.getSelectedFile().getAbsolutePath();
			imagem.setIcon(new ImageIcon(caminhoArquivo));
            
		}
	}
	
	public ActionListener Inserir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			procuraArquivo();
		}

	};

	public ActionListener Remover = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
			
		}

	};
}
