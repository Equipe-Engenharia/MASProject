package view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;



public class FrmCalendario extends JDialog {

	public FrmCalendario() {
		Cal();
	}
	public void Cal()
	{
		JComponent contentPane = new JPanel();
		setBounds(100, 100, 450, 305);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		 JCalendar calendar = new JCalendar();
	        calendar.setBounds(92, 179, 191, 153);
	        contentPane.add(calendar);
		
	}
}
