package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.SetorCtrl;

public class FrmSetorEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtSetor;
	private JButton btnEditar, btnPesquisar, btnApagar;
	private JLabel lblIdDoSetor, lblNomeDoSetor;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSetorEdit frame = new FrmSetorEdit();
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
	
	public FrmSetorEdit() {
		setTitle("Editar/Excluir Setor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIdDoSetor = new JLabel("ID");
		lblIdDoSetor.setBounds(146, 56, 22, 16);
		contentPane.add(lblIdDoSetor);

		lblNomeDoSetor = new JLabel("Setor");
		lblNomeDoSetor.setBounds(135, 99, 32, 16);
		contentPane.add(lblNomeDoSetor);

		txtSetor = new JTextField();
		txtSetor.setToolTipText("Digite o nome do setor...");
		txtSetor.setBounds(178, 93, 178, 28);
		contentPane.add(txtSetor);
		txtSetor.setColumns(10);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(288, 166, 97, 34);
		btnEditar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnEditar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(376, 90, 117, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);

		btnApagar = new JButton("Excluir");
		btnApagar.setBounds(397, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setToolTipText("Digite o ID do setor...");
		txtId.setBounds(178, 54, 178, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		SetorCtrl controle = new SetorCtrl (contentPane, txtId, txtSetor);
		
		txtSetor.addActionListener(controle.pesquisar);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnEditar.addActionListener(controle.editar);
		btnApagar.addActionListener(controle.excluir);
	}
}
