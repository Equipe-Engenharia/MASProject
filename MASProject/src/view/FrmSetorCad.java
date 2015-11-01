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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSetor;
	private JTextField txtId;
	private JLabel lblIdDoSetor, lblNomeDoSetor, msgGravar, msgVazio;
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

		msgGravar = new JLabel("");
		msgGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravar.setBounds(43, 177, 230, 23);
		msgGravar.setVisible(false);
		contentPane.add(msgGravar);

		msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 177, 230, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		SetorCtrl ctrlSetor = new SetorCtrl(txtId, txtSetor, msgGravar, msgVazio, btnGravar);

		// Essa linha abaixo será excluida quando o menu estiver pronto pois
		// será um evento do botão que chama este Form***
		ctrlSetor.gerarId();

		txtSetor.addMouseListener(ctrlSetor.limpaCampo);
		txtSetor.addActionListener(ctrlSetor.gravarSetor);
		txtSetor.addKeyListener(ctrlSetor.ativaGravar);
		btnGravar.addActionListener(ctrlSetor.gravarSetor);

	}
}
