package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CategoriaCtrl;

public class FrmCategoriaCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCategoria;
	private JTextField txtId;
	private JButton btnGravar;
	private JLabel msgGravar, msgVazio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCategoriaCad frame = new FrmCategoriaCad();
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
	public FrmCategoriaCad(){
		setResizable(false);
		setTitle("Registro de Categoria de Obra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdCategoria = new JLabel("ID");
		lblIdCategoria.setBounds(148, 51, 21, 16);
		contentPane.add(lblIdCategoria);
		
		JLabel lblNovaCategoria = new JLabel("Nova Categoria");
		lblNovaCategoria.setBounds(74, 87, 107, 16);
		contentPane.add(lblNovaCategoria);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setBounds(181, 49, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.setToolTipText("Digite o novo Artista…");
		txtCategoria.setBounds(180, 81, 178, 28);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		msgGravar = new JLabel("");
		msgGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravar.setBounds(43, 177, 230, 23);
		msgGravar.setVisible(false);
		contentPane.add(msgGravar);

		msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 177, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		CategoriaCtrl ctrlCategoria = new CategoriaCtrl(txtId, txtCategoria, btnGravar, msgGravar, msgVazio);
		
		ctrlCategoria.gerarId(); //LEMBRAR DE APAGAR ESSA LINHA APOS A CRIACAO DO MENU
		
		btnGravar.addActionListener(ctrlCategoria.gravarCategoria);
		txtCategoria.addMouseListener(ctrlCategoria.limpaCampo);
		txtCategoria.addActionListener(ctrlCategoria.gravarCategoria);

	}
}
