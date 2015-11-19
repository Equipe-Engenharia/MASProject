package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ObraArtistaCtrl{
	private JList<Object> listObras;
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
	
	private void carregarJListEsquerda(){
		ArtistaCtrl aController = new ArtistaCtrl();//mudar aqui para OBRAS
		String artistas[] = aController.getArtista();//usando artista para teste
		final DefaultListModel<Object> listModel = new DefaultListModel<>();
		listModel.addElement(artistas);
		listObras.setListData(artistas);
	}
	
	//corrigir este método
	private void enviarObraJListDireito(){
		int index = listObrasSelecionadas.getLastVisibleIndex();
		DefaultListModel<Object> listModel = new DefaultListModel<>();
		if(index == -1){
			index = 0;
		}else{
			listModel.addElement(listObrasSelecionadas.getModel());
			index++;
		}
		listModel.add(index, listObras.getSelectedValue());
		listObrasSelecionadas.setModel(listModel);
	}
	
	private void desfazerEnvioObra(){
		
	}
	
	public WindowListener carregarObras = new WindowListener() {
		@Override
		public void windowOpened(WindowEvent e) {
			carregarJListEsquerda();
		}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowDeactivated(WindowEvent e) {}
		@Override
		public void windowClosing(WindowEvent e) {}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}
	};
	
	public ActionListener enviarObraSelecionada = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			enviarObraJListDireito();
		}
	};
}
