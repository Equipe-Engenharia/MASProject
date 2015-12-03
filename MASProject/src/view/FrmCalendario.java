package view;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import controller.AgendamentoCtrl;
import controller.EmprestimoCtrl;
import controller.ExposicaoCtrl;
import controller.RelatorioEstCtrl;
import controller.RelatorioFinCtrl;
import controller.SessaoCtrl;


public class FrmCalendario extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCalendar calendar;
	private JComponent contentPane;

	public FrmCalendario() {
		
		//APLICA O TEMA "Nimbus"
		/*try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}*/
		setTitle("Calend\u00E1rio");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Cal();
	}

	public void Cal() {
		
		contentPane = new JPanel();
		setBounds(100, 100, 452, 245);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		calendar = new JCalendar();
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.setBounds(5, 5, 424, 225);
		contentPane.add(calendar);
 
		sessao();
	}
	
	public void sessao() {

		SessaoCtrl log = SessaoCtrl.getInstance();

		//VERIFICA O NOME DO CONTROLPANE DA TELA
		switch (log.getLogon().get(0).getTela()) {

		case "EXP":
			ExposicaoCtrl ExpCtrl = new ExposicaoCtrl(calendar);
			addWindowListener(ExpCtrl.fechaTela);
			break;

		case "EPT":
			EmprestimoCtrl EmpCtrl = new EmprestimoCtrl(calendar);
			addWindowListener(EmpCtrl.fechaTela);
			break;
			
		case "AGD":
			AgendamentoCtrl AgdCtrl = new AgendamentoCtrl(calendar);
			addWindowListener(AgdCtrl.fechaTela);
			break;
			
		case "RLE":
			//RelatorioEstCtrl RelCtrl = new RelatorioEstCtrl(calendar);
			//addWindowListener(RelCtrl.fechaTela);
			break;
			
		

		default:
			JOptionPane.showMessageDialog(null, 
					"Erro na identificação da Tela. Por Favor, notifique ao desenvolvedor.", 
					"Erro no Calendário", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
		}		
	}
}