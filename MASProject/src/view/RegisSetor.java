package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RegisSetorController;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisSetor extends JFrame {

	private JPanel contentPane;
	private JTextField txtDigiteUmNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisSetor frame = new RegisSetor();
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
	public RegisSetor() {
		setTitle("Cadastrar um novo setor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdDoSetor = new JLabel("ID. Setor:");
		lblIdDoSetor.setBounds(10, 23, 67, 14);
		contentPane.add(lblIdDoSetor);
		
		JLabel id_setor = new JLabel("");
		id_setor.setBounds(69, 23, 67, 14);
		contentPane.add(id_setor);
		
		JLabel lblNomeDoSetor = new JLabel("Setor:");
		lblNomeDoSetor.setBounds(10, 51, 76, 14);
		contentPane.add(lblNomeDoSetor);
		
		txtDigiteUmNome = new JTextField();
		txtDigiteUmNome.setText("Digite o nome do setor...");
		txtDigiteUmNome.setBounds(69, 48, 133, 20);
		contentPane.add(txtDigiteUmNome);
		txtDigiteUmNome.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(10, 89, 89, 23);
		contentPane.add(btnGravar);
		
		RegisSetorController RsContrl = new RegisSetorController(id_setor,lblNomeDoSetor,btnGravar);
	}
}
