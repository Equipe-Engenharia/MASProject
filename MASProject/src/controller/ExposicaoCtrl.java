package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import view.FrmCalendario;
import view.FrmObraArtistaSelec;

public class ExposicaoCtrl {

	private static JCalendar calendar;
	private static  JTextField txtDataIni;
	private static  JTextField txtDataFim;
	private JTextField txtNomeArtista;
	private JTable tObras;
	private static int flag;

	public ExposicaoCtrl(JTextField txtDataI, JTextField txtDataF, JTextField txtNomeArtista,
			JTable tObras) {
		ExposicaoCtrl.txtDataIni = txtDataI; //neste caso nao se usa this, porque o metodo que utiliza a variavel � estatico
		ExposicaoCtrl.txtDataFim = txtDataF;
		this.txtNomeArtista = txtNomeArtista;
		this.tObras = tObras;
	}

	public ExposicaoCtrl(JCalendar calendar) {
		ExposicaoCtrl.calendar = calendar;
	}
	
   
	
	/*As flags funcionam para quando se tem mais de uma chamada de 
	calendario na mesma tela, ajuda no tratamento de retorno*/
	public static int getFlag() {
		return flag;
	}

	public void setFlag(int n) {
		flag = n;
	}

	public static void leCalendario() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String data = formato.format(calendar.getDate()); //le data selecionada pelo usuario
		switch (getFlag()) {
		//tratamento das flags
		case 1:
			txtDataIni.setText(null);
			txtDataIni.setText(data);
			break;
		case 2:
			txtDataFim.setText(null);
			txtDataFim.setText(data);
			break;
		}
		
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
			JDialog frmOASelec = new FrmObraArtistaSelec(txtNomeArtista.getText());
			frmOASelec.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmOASelec.setLocationRelativeTo(null);
			frmOASelec.setModal(true);
			frmOASelec.setVisible(true);
//			tObras = new JTable();
		}
	};

//	public ActionListener leCalendario = new ActionListener() {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			leCalendario();
//		}
//	};
	
	
	//Este listener trata a busca da data selecionada ao fechar a tela
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
