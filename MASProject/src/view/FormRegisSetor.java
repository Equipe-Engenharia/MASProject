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
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FormRegisSetor extends JFrame {

	private JPanel contentPane;
	private JTextField txtDigitado;
	private JTextField id_setor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormRegisSetor frame = new FormRegisSetor();
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
	public FormRegisSetor() {
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

		JLabel lblNomeDoSetor = new JLabel("Setor:");
		lblNomeDoSetor.setBounds(10, 51, 76, 14);
		contentPane.add(lblNomeDoSetor);

		txtDigitado = new JTextField();
		txtDigitado.setText("Digite o nome do setor...");
		txtDigitado.setBounds(69, 48, 169, 20);
		contentPane.add(txtDigitado);
		txtDigitado.setColumns(10);
		
		id_setor = new JTextField();
		id_setor.setEditable(false);
		id_setor.setBounds(69, 20, 86, 20);
		contentPane.add(id_setor);
		id_setor.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(342, 96, 89, 23);
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		JLabel mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(46, 96, 192, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		JLabel mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(10, 96, 192, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);
		
		
		RegisSetorController RsContrl = new RegisSetorController(id_setor, txtDigitado, mensagemGravado, mensagemVazio, btnGravar);

		txtDigitado.addMouseListener(RsContrl.limpaCampo);
		txtDigitado.addActionListener(RsContrl.gravarSetor);
		btnGravar.addActionListener(RsContrl.gravarSetor);

	}
}
