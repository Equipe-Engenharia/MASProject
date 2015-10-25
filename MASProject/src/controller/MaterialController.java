package controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JComboBox;

public class MaterialController implements ComponentListener{
	
	private JComboBox<String> cbCategoria;
	private ArquivosController arqController;
	
	public MaterialController(JComboBox<String> cbCategoria){
		this.cbCategoria = cbCategoria;
	}
	
	private void preencherComboBoxCategoria(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/", "material");
			linha = arqController.getBuffer();
			String [] categoria = linha.split(";");
			for(String s : categoria){
				cbCategoria.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {
		preencherComboBoxCategoria();
	}

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

}