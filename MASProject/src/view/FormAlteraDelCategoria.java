package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAlteraDelCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField tfPeqNomeCategoria;
	private JTextField tfPesqIdCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAlteraDelCategoria frame = new FormAlteraDelCategoria();
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
	public FormAlteraDelCategoria() {
		setTitle("Editar/Excluir Categoria de Obra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id Categoria");
		lblNewLabel.setBounds(10, 59, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da Categoria");
		lblNewLabel_1.setBounds(10, 96, 126, 14);
		contentPane.add(lblNewLabel_1);
		
		tfPeqNomeCategoria = new JTextField();
		tfPeqNomeCategoria.setBounds(133, 93, 141, 20);
		contentPane.add(tfPeqNomeCategoria);
		tfPeqNomeCategoria.setColumns(10);
		
		tfPesqIdCategoria = new JTextField();
		tfPesqIdCategoria.setBounds(90, 53, 95, 20);
		contentPane.add(tfPesqIdCategoria);
		tfPesqIdCategoria.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(187, 144, 104, 23);
		contentPane.add(btnGravar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluir.setBounds(301, 144, 106, 23);
		contentPane.add(btnExcluir);
		
		JLabel mensagemGravado = new JLabel("");
		mensagemGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		mensagemGravado.setBounds(46, 144, 192, 23);
		mensagemGravado.setVisible(false);
		contentPane.add(mensagemGravado);

		JLabel mensagemVazio = new JLabel("CAMPO VAZIO!");
		mensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		mensagemVazio.setBounds(10, 144, 192, 23);
		mensagemVazio.setVisible(false);
		contentPane.add(mensagemVazio);
		
		JButton btnPesqId = new JButton("");
		btnPesqId.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesqId.setBounds(205, 50, 33, 23);
		contentPane.add(btnPesqId);
		
		JButton btnPesqNome = new JButton("");
		btnPesqNome.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesqNome.setBounds(284, 93, 33, 23);
		contentPane.add(btnPesqNome);
		
		JLabel lblDigiteUmDos = new JLabel("Digite em um dos campos e aperte o bot\u00E3o para pesquisar...");
		lblDigiteUmDos.setForeground(Color.RED);
		lblDigiteUmDos.setBounds(10, 11, 362, 14);
		contentPane.add(lblDigiteUmDos);
	}
}

