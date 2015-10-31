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
	private JTextField idCategoria;
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
		
		idCategoria = new JTextField();
		idCategoria.setEditable(false);
		idCategoria.setEnabled(false);
		idCategoria.setBounds(181, 49, 178, 20);
		idCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(idCategoria);
		idCategoria.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.setToolTipText("Digite o novo Artista…");
		txtCategoria.setBounds(180, 81, 178, 28);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JLabel msgGravado = new JLabel("");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(43, 177, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 177, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(288, 166, 97, 34);
		contentPane.add(btnGravar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon("../MASProject/icons/out.png"));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});				
		btnFechar.setBounds(397, 166, 97, 34);
		contentPane.add(btnFechar);
		
		CategoriaCtrl ctrlCategoria = new CategoriaCtrl(idCategoria, txtCategoria, btnGravar, msgGravado, msgVazio);
		
		ctrlCategoria.gerarIdSetor();
		btnGravar.addActionListener(ctrlCategoria.gravarCategoria);
		txtCategoria.addMouseListener(ctrlCategoria.limpaCampo);
		txtCategoria.addActionListener(ctrlCategoria.gravarCategoria);
	}
}
