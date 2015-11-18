package view;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import controller.ExposicaoCtrl;

import javax.swing.JButton;

public class FrmCalendario extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCalendar calendar;
	private JComponent contentPane;

	public FrmCalendario() {
		setTitle("Calend\u00E1rio");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Cal();
	}

	public void Cal() {
		
		contentPane = new JPanel();
		setBounds(100, 100, 447, 272);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		calendar = new JCalendar();
		calendar.setBounds(5, 5, 424, 225);
		contentPane.add(calendar);
 
		ExposicaoCtrl ExpCtrl = new ExposicaoCtrl(calendar);
		
		addWindowListener(ExpCtrl.fechaTela);
	}
}
