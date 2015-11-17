package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.SetorCtrl;

public class FrmSetorCad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtSetor;
	private JLabel lblIdDoSetor, lblNomeDoSetor;
	private JButton btnGravar;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSetorCad frame = new FrmSetorCad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public FrmSetorCad() {
		setTitle("Registro de Setor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIdDoSetor = new JLabel("ID");
		lblIdDoSetor.setBounds(148, 35, 20, 16);
		contentPane.add(lblIdDoSetor);

		lblNomeDoSetor = new JLabel("Novo Setor");
		lblNomeDoSetor.setBounds(92, 87, 89, 16);
		contentPane.add(lblNomeDoSetor);

		txtSetor = new JTextField();
		txtSetor.setToolTipText("Digite aqui o novo setor!");
		txtSetor.setBounds(180, 81, 178, 28);
		contentPane.add(txtSetor);
		txtSetor.setColumns(10);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(180, 33, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtId);
		txtId.setColumns(10);

		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);

		SetorCtrl controle = new SetorCtrl(contentPane, txtId, txtSetor);

		controle.gerarId();

		txtSetor.addMouseListener(controle.limpaCampo);
		txtSetor.addActionListener(controle.gravar);
		btnGravar.addActionListener(controle.gravar);
	}
}
