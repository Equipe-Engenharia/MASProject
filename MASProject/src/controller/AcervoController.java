package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class AcervoController implements ComponentListener {
	
	private JLabel imagem;
	private JComboBox<String> cbMaterial;
	private JComboBox<String> cbSetor;
	private ArquivosController arqController;
	
	public AcervoController(JLabel imagem, JComboBox<String> comboSetor,JComboBox<String> cbMaterial){
		this.imagem = imagem;
		this.cbMaterial = cbMaterial;
		this.cbSetor = comboSetor;
	}
	
		
	
	
	public void procuraImagem() {
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
			ImageIcon img = new ImageIcon(caminhoArquivo);
			Image newImg= img.getImage().getScaledInstance(imagem.getWidth(),imagem.getHeight(), Image.SCALE_DEFAULT);
			imagem.setIcon(new ImageIcon(newImg));
			 
		}
		
	}
	
	
	
	
	public ActionListener inserir_imagem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			procuraImagem();
		}

	};

	public ActionListener remover_imagem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
			
		}

	};
	
	
	private void preencherComboBoxMaterial(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "materiais");
			linha = arqController.getBuffer();
			String [] categoria = linha.split(";");
			for(String s : categoria){
				cbMaterial.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void preencherComboBoxSetores(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "setores");
			linha = arqController.getBuffer();
			String [] setor = linha.split(";");
			for(String s : setor){
				cbSetor.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		preencherComboBoxMaterial();
		preencherComboBoxSetores();

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
