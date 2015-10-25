package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.Setor;

public class RegisSetorController {

	private JLabel idsetor;
	private JLabel nomeset;
	private JButton btnGravar;
	Setor setor = new Setor();

	public RegisSetorController(JLabel id_setor, JLabel lblNomeDoSetor, JButton btnGravar) {
		this.idsetor = id_setor;
		this.nomeset = lblNomeDoSetor;
		this.btnGravar = btnGravar;
	}
	
	public void gravaSetor(){
		ArquivosController arqController = new ArquivosController();
		//Falta implementar
		//setor.setIdentificacao(idsetor.getText());
		setor.setNome(nomeset.getText());
		try {
			arqController.escreveArquivo("../MASProject/", "setores", nomeset.getText(), setor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public ActionListener gravarSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravaSetor();
		}
	};
	

}
