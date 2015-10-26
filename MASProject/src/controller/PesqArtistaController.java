package controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JComboBox;

public class PesqArtistaController implements ComponentListener{

	private JComboBox<String> cbArtista;
	private ArquivosController arqController;
	
	public PesqArtistaController(JComboBox<String> cbArtista){
		this.cbArtista = cbArtista;
	}
	
	private void preencherComboBoxArtista(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados/", "artistas");
			linha = arqController.getBuffer();
			String [] artista = linha.split(";");
			for(String s : artista){
				cbArtista.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		preencherComboBoxArtista();
	}

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}
	
}
