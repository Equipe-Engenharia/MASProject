package view;

import java.awt.Color;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSetor, txtId;
	private JButton btnGravar, btnPesqNomSet, btnApagar;
	private JLabel lblDigiteUmDos, lblIdDoSetor, lblNomeDoSetor, mensagemGravado, mensagemVazio  ;

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

		mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(36, 141, 230, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(36, 141, 230, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);

		txtSetor = new JTextField();
		txtSetor.setToolTipText("Digite o nome do setor...");
		txtSetor.setBounds(178, 93, 178, 28);
		contentPane.add(txtSetor);
		txtSetor.setColumns(10);
		
		btnGravar = new JButton("Editar");
		btnGravar.setBounds(288, 166, 97, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);

		btnPesqNomSet = new JButton("Pesquisar");
		btnPesqNomSet.setBounds(376, 90, 117, 34);
		btnPesqNomSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqNomSet);

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

		lblDigiteUmDos = new JLabel("Digite em um dos campos e aperte o bot\u00E3o para pesquisar...");
		lblDigiteUmDos.setForeground(Color.RED);
		lblDigiteUmDos.setBounds(10, 11, 434, 14);
		lblDigiteUmDos.setVisible(false);
		contentPane.add(lblDigiteUmDos);

		SetorCtrl ctrlSetor = new SetorCtrl(contentPane, txtId, txtSetor);
		btnPesqNomSet.addActionListener(ctrlSetor.pesquisarSetor);
		btnGravar.addActionListener(ctrlSetor.gravarSetor);
		btnApagar.addActionListener(ctrlSetor.excluirSetor);
		txtSetor.addActionListener(ctrlSetor.pesquisarSetor);

	}

}