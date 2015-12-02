package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CategoriaCtrl;

public class FrmCategoriaCad extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblId, lblCategoria;
	private JTextField txtId, txtCategoria;
	private JButton btnGravar;
	
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
		setClosable(true);
		setIconifiable(true);
		setResizable(false);
		setTitle("Registro de Categoria de Obra");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocation(0,0);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(148, 51, 21, 16);
		contentPane.add(lblId);
		
		lblCategoria = new JLabel("Nova Categoria");
		lblCategoria.setBounds(74, 87, 107, 16);
		contentPane.add(lblCategoria);
		
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

		btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(true);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		CategoriaCtrl controle = new CategoriaCtrl (contentPane, txtId, txtCategoria);
		
		controle.gerarId();
		btnGravar.addActionListener(controle.gravar);
		txtCategoria.addMouseListener(controle.limpaCampo);
		txtCategoria.addActionListener(controle.gravar);
	}
}
