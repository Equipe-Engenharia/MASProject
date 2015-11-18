package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.FrmCalendario;

public class ExposicaoCtrl {

	public ActionListener abreCalendario = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			FrmCalendario frmCal = new FrmCalendario();
			frmCal.setVisible(true);
		}
	};
	public ActionListener pesquisaArtista = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	

}
