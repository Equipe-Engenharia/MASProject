package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import view.FrmCalendario;
import view.FrmExposicaoCad;

public class ExposicaoCtrl {

	private static JCalendar calendar;
	private static  JTextField txtDataIni;
	private static  JTextField txtDataFim;
	private JTextField txtNomeArtista;
	private static int flag;

	public ExposicaoCtrl(JTextField txtDataI, JTextField txtDataF, JTextField txtNomeArtista) {
		ExposicaoCtrl.txtDataIni = txtDataI;
		ExposicaoCtrl.txtDataFim = txtDataF;
		this.txtNomeArtista = txtNomeArtista;
	}

	public ExposicaoCtrl(JCalendar calendar) {
		ExposicaoCtrl.calendar = calendar;
	}

	public static int getFlag() {
		return flag;
	}

	public void setFlag(int n) {
		flag = n;
	}

	public static void leCalendario() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String data = formato.format(calendar.getDate());
		switch (getFlag()) {
		case 1:
			txtDataIni.setText(null);
			txtDataIni.setText(data);
			break;
		case 2:
			txtDataFim.setText(null);
			txtDataFim.setText(data);
			break;
		}
		new FrmCalendario().setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		
	}

	public void chamaCalendario() {
		FrmCalendario frmCal = new FrmCalendario();
		frmCal.setVisible(true);
	}

	public ActionListener abreCalendario1 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			chamaCalendario();
			setFlag(1);
		}
	};

	public ActionListener abreCalendario2 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			chamaCalendario();
			setFlag(2);
		}
	};

	public ActionListener pesquisaArtista = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	};

//	public ActionListener leCalendario = new ActionListener() {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			leCalendario();
//		}
//	};
	
	public WindowListener fechaTela = new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			//leCalendario();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			leCalendario();
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	}; 
	

}
