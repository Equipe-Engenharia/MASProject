package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class PesqObraController implements ComponentListener, ActionListener{

	private JComboBox<String> cbProprietario;
	private JComboBox<String> cbArtista;
	private JComboBox<String> cbIdObra;
	private JComboBox<String> cbNomeObra;
	private JButton btnEncontreiObra;
	
	public PesqObraController(JComboBox<String> cbProprietario,
			JComboBox<String> cbArtista, JComboBox<String> cbIdObra,
			JComboBox<String> cbNomeObra, JButton btnEncontreiObra){

		this.cbProprietario = cbProprietario;
		this.cbArtista = cbArtista;
		this.cbIdObra = cbIdObra;
		this.cbNomeObra = cbNomeObra;
		this.btnEncontreiObra = btnEncontreiObra;
	}
	
	private void preencheComboBoxProprietario(){
		String [] proprietario = {"MASP", "Terceiros"};
		for(String s : proprietario){
			cbProprietario.addItem(s);
		}
	}
	
	private void preencheComboBoxIDObra(){
		if(cbProprietario.getSelectedItem().equals("MASP")){
			//TODO
		}else if(cbProprietario.getSelectedItem().equals("Terceiros")){
			//TODO
		}
	}
	
	private void preencheComboBoxNomeObra(){
		//TODO
	}
	
	private void enviarObra(){
		//TODO
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		enviarObra();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		preencheComboBoxProprietario();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
