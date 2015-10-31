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
	private JTextField txtDigitadoN, txtDigiteId;
	private JButton btnGravar,  btnPesqIdSet, btnPesqNomSet, btnApagar;
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
		lblNomeDoSetor.setBounds(135, 93, 32, 16);
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

		txtDigitadoN = new JTextField();
		txtDigitadoN.setToolTipText("Digite o nome do setor...");
		txtDigitadoN.setBounds(178, 93, 178, 28);
		contentPane.add(txtDigitadoN);
		txtDigitadoN.setColumns(10);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(288, 166, 97, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		btnGravar.setEnabled(false);

		 btnPesqIdSet = new JButton(" Busca ID");
		btnPesqIdSet.setBounds(377, 47, 117, 34);
		btnPesqIdSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqIdSet);

		btnPesqNomSet = new JButton("Busca Setor");
		btnPesqNomSet.setBounds(376, 90, 117, 34);
		btnPesqNomSet.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqNomSet);

		btnApagar = new JButton("Apagar");
		btnApagar.setEnabled(false);
		btnApagar.setBounds(397, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);

		txtDigiteId = new JTextField();
		txtDigiteId.setToolTipText("Digite o ID do setor...");
		txtDigiteId.setBounds(180, 54, 178, 20);
		contentPane.add(txtDigiteId);
		txtDigiteId.setColumns(10);

		lblDigiteUmDos = new JLabel("Digite em um dos campos e aperte o bot\u00E3o para pesquisar...");
		lblDigiteUmDos.setForeground(Color.RED);
		lblDigiteUmDos.setBounds(10, 11, 362, 14);
		contentPane.add(lblDigiteUmDos);

		SetorCtrl RsCtrl = new SetorCtrl(txtDigiteId, txtDigitadoN, btnPesqIdSet, btnPesqNomSet, btnApagar, btnGravar);

		btnPesqIdSet.addActionListener(RsCtrl.pesquisaIDSetor);
		btnPesqNomSet.addActionListener(RsCtrl.pesquisaNomeSetor);
		btnGravar.addActionListener(RsCtrl.gravarAlteracoesSetor);
		btnApagar.addActionListener(RsCtrl.excluirSetor);

	}

}
