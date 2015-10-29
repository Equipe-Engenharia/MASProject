package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AlteraDelSetorController;

import java.awt.Color;

public class FormAlteraDelSetor extends JFrame {

	private JPanel contentPane;
	private JTextField txtDigitadoN;
	private JTextField txtDigiteOId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAlteraDelSetor frame = new FormAlteraDelSetor();
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
	public FormAlteraDelSetor() {
		setTitle("Editar ou Excluir Setor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdDoSetor = new JLabel("ID. Setor:");
		lblIdDoSetor.setBounds(10, 54, 67, 14);
		contentPane.add(lblIdDoSetor);

		JLabel lblNomeDoSetor = new JLabel("Setor:");
		lblNomeDoSetor.setBounds(10, 93, 76, 14);
		contentPane.add(lblNomeDoSetor);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(209, 154, 106, 23);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		JLabel mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(46, 154, 192, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		JLabel mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(10, 154, 192, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);
		
		txtDigitadoN = new JTextField();
		txtDigitadoN.setText("Digite o nome do setor...");
		txtDigitadoN.setBounds(69, 90, 169, 20);
		contentPane.add(txtDigitadoN);
		txtDigitadoN.setColumns(10);
		
		JButton btnPesqIdSet = new JButton("");
		btnPesqIdSet.setBounds(251, 48, 33, 23);
		btnPesqIdSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqIdSet);
		
		JButton btnPesqNomSet = new JButton("");
		btnPesqNomSet.setBounds(251, 89, 33, 23);
		btnPesqNomSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqNomSet);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(325, 154, 106, 23);
		btnExcluir.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnExcluir);
		
		txtDigiteOId = new JTextField();
		txtDigiteOId.setText("Digite o ID do setor...");
		txtDigiteOId.setBounds(69, 51, 169, 20);
		contentPane.add(txtDigiteOId);
		txtDigiteOId.setColumns(10);
		
		JLabel lblDigiteUmDos = new JLabel("Digite em um dos campos e aperte o bot\u00E3o para pesquisar...");
		lblDigiteUmDos.setForeground(Color.RED);
		lblDigiteUmDos.setBounds(10, 11, 362, 14);
		contentPane.add(lblDigiteUmDos);
		
		AlteraDelSetorController ADSetor = new AlteraDelSetorController(txtDigiteOId,txtDigitadoN,btnPesqIdSet,btnPesqNomSet,btnGravar,btnExcluir);
	
		btnPesqIdSet.addActionListener(ADSetor.pesquisaIDSetor);
		btnPesqNomSet.addActionListener(ADSetor.pesquisaNomeSetor);
		btnGravar.addActionListener(ADSetor.gravarAlteracoesSetor);
		btnExcluir.addActionListener(ADSetor.excluirSetor);
		
	}
	
}
