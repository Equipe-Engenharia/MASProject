package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;

import controller.RegisCategObraController;

import java.awt.Font;

public class FormRegisCategObra extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeCategoria;
	private JTextField tfIdCategoria;
	private JLabel lblMensagemGravada;
	private JLabel lblMensagemVazio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormRegisCategObra frame = new FormRegisCategObra();
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
	public FormRegisCategObra(){
		setTitle("Registro de Categoria de Obra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id Categoria");
		lblNewLabel.setBounds(10, 30, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da Categoria");
		lblNewLabel_1.setBounds(10, 68, 123, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNomeCategoria = new JTextField("Digite o nome da categoria...");
		tfNomeCategoria.setBounds(143, 65, 204, 20);
		contentPane.add(tfNomeCategoria);
		tfNomeCategoria.setColumns(10);
		
		tfIdCategoria = new JTextField();
		tfIdCategoria.setEditable(false);
		tfIdCategoria.setBackground(Color.LIGHT_GRAY);
		tfIdCategoria.setBounds(89, 24, 89, 20);
		contentPane.add(tfIdCategoria);
		tfIdCategoria.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(276, 96, 89, 23);
		contentPane.add(btnGravar);
		
		JLabel lblMensagemGravada = new JLabel("");
		lblMensagemGravada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMensagemGravada.setBounds(53, 93, 213, 26);
		lblMensagemGravada.setVisible(false);
		contentPane.add(lblMensagemGravada);
		
		JLabel lblMensagemVazio = new JLabel("CAMPO VAZIO!");
		lblMensagemVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		lblMensagemVazio.setBounds(10, 93, 213, 26);
		lblMensagemVazio.setVisible(false);
		contentPane.add(lblMensagemVazio);
		
		RegisCategObraController rCatObra = new RegisCategObraController(lblMensagemGravada, lblMensagemVazio, btnGravar, tfIdCategoria, tfNomeCategoria); 
		//Essa linha abaixo será excluida quando o menu estiver pronto***
        rCatObra.gerarIdSetor();
		tfNomeCategoria.addMouseListener(rCatObra.limpaCampo);
		tfNomeCategoria.addActionListener(rCatObra.gravarCategoria);
		btnGravar.addActionListener(rCatObra.gravarCategoria);
	}
}

