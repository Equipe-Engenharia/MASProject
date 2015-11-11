package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmExp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnCalendario;

	public static void main(String[] args) {
		new FrmExp().setVisible(true);
	}

	public FrmExp() {
		FrmExp();
		setLocationRelativeTo(null);
	}

	public void FrmExp() {
		contentPane = new JPanel();
		btnCalendario = new JButton();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		btnCalendario = new JButton("");
		btnCalendario.setIcon(new ImageIcon("../MASProject/icons/calendario.png"));
		btnCalendario.setBounds(17, 49, 117, 29);
		contentPane.add(btnCalendario);

		btnCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmCalendario(null, true).setVisible(true);
			}

		});

	}

}
