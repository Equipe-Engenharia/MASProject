package view;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import controller.ExposicaoCtrl;

public class FrmCalendario extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCalendar calendar;
	private JComponent contentPane;

	public FrmCalendario() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		setTitle("Calend\u00E1rio");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Cal();
	}

	public void Cal() {
		
		contentPane = new JPanel();
		setBounds(100, 100, 452, 282);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		calendar = new JCalendar();
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.setBounds(5, 5, 424, 225);
		contentPane.add(calendar);
		
		ExposicaoCtrl ExpCtrl = new ExposicaoCtrl(calendar);
		
		addWindowListener(ExpCtrl.fechaTela);
	}
	
}
