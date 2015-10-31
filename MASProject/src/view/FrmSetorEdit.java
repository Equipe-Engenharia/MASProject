package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.SetorEditCtrl;

public class FrmSetorEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		
		JLabel lblIdDoSetor = new JLabel("ID");
		lblIdDoSetor.setBounds(146, 56, 22, 16);
		contentPane.add(lblIdDoSetor);

		JLabel lblNomeDoSetor = new JLabel("Setor");
		lblNomeDoSetor.setBounds(135, 93, 32, 16);
		contentPane.add(lblNomeDoSetor);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(159, 166, 117, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		JLabel mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(36, 141, 230, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		JLabel mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(36, 141, 230, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);
		
		txtDigitadoN = new JTextField();
		txtDigitadoN.setToolTipText("Digite o nome do setor...");
		txtDigitadoN.setBounds(178, 93, 178, 28);
		contentPane.add(txtDigitadoN);
		txtDigitadoN.setColumns(10);
		
		JButton btnPesqIdSet = new JButton(" Busca ID");
		btnPesqIdSet.setBounds(377, 47, 117, 34);
		btnPesqIdSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqIdSet);
		
		JButton btnPesqNomSet = new JButton("Busca Setor");
		btnPesqNomSet.setBounds(376, 90, 117, 34);
		btnPesqNomSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqNomSet);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setEnabled(false);
		btnApagar.setBounds(288, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setBounds(397, 166, 97, 34);
		btnFechar.setIcon(new ImageIcon("../MASProject/icons/out.png"));
		contentPane.add(btnFechar);
		
		txtDigiteOId = new JTextField();
		txtDigiteOId.setToolTipText("Digite o ID do setor...");
		txtDigiteOId.setBounds(180, 54, 178, 20);
		contentPane.add(txtDigiteOId);
		txtDigiteOId.setColumns(10);
		
		JLabel lblDigiteUmDos = new JLabel("Digite em um dos campos e aperte o bot\u00E3o para pesquisar...");
		lblDigiteUmDos.setForeground(Color.RED);
		lblDigiteUmDos.setBounds(10, 11, 362, 14);
		contentPane.add(lblDigiteUmDos);
		
		SetorEditCtrl ADSetor = new SetorEditCtrl(txtDigiteOId,txtDigitadoN,btnPesqIdSet,btnPesqNomSet, btnApagar,btnGravar);
	
		btnPesqIdSet.addActionListener(ADSetor.pesquisaIDSetor);
		btnPesqNomSet.addActionListener(ADSetor.pesquisaNomeSetor);
		btnGravar.addActionListener(ADSetor.gravarAlteracoesSetor);
		btnApagar.addActionListener(ADSetor.excluirSetor);
		
	}
	
}
