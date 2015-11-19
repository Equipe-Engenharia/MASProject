package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ObraArtistaCtrl implements ActionListener, ComponentListener{
	private static JList<Object> listObras;
	private JList<Object> listObrasSelecionadas;
	private JButton btnEnviarObras;
	private JButton btnMoveObra, btnUndoMoveObra;
	private String nomeArtista;
	public ObraArtistaCtrl(JList<Object> listObras, JList<Object> listObrasSelecionadas,
			JButton btnEnviarObras, JButton btnMoveObra, JButton btnUndoMoveObra,
			String nomeArtista) {
		this.listObras = listObras;
		this.listObrasSelecionadas = listObrasSelecionadas;
		this.btnEnviarObras = btnEnviarObras;
		this.btnMoveObra = btnMoveObra;
		this.btnUndoMoveObra = btnUndoMoveObra;
		this.nomeArtista = nomeArtista;
	}
	
	public static void preencheJListEsquerdo(){
		ArtistaCtrl aController = new ArtistaCtrl();
		final Object artista[] = aController.preencherComboBoxArtista();
		listObras = new JList<Object>(artista);
	}
	
	private void enviarObraJListDireito(){
		Object selectValue = listObras.getSelectedValue();
		
	}
	
	private void desfazerEnvioObra(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String botaoPressionado = e.getActionCommand();
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		preencheJListEsquerdo();		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
	
	

}
