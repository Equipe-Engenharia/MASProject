package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MaterialController;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMaterial extends JFrame {

 static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCategoria;
	private JTextField txtMaterial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMaterial frame = new FormMaterial();
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
	public FormMaterial() {
		setTitle("Registrar Material - MASP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(172, 38, 178, 28);
		contentPane.add(cbCategoria);
		
		txtMaterial = new JTextField();
		txtMaterial.setText("Digite o novo material…");
		txtMaterial.setBounds(172, 83, 178, 28);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria da Obra");
		lblCategoria.setBounds(47, 43, 113, 16);
		contentPane.add(lblCategoria);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(99, 89, 61, 16);
		contentPane.add(lblMaterial);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gravar();
				
			}
		});
		btnGravar.setBounds(369, 155, 117, 29);
		contentPane.add(btnGravar);
		
		MaterialController listaCategoria = new MaterialController(cbCategoria); //Preechendo a comboBox CATEGORIA
		cbCategoria.addComponentListener(listaCategoria);
	}
	

	
	private void gravar(){ //Gravando o novo registro no arquivo.
		
	}
}
