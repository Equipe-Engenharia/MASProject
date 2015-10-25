package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import controller.MaterialController;
import model.Material;
import controller.ArquivosController;
import controller.IArquivosController;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class FormMaterial extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCategoria;
	private JTextField txtMaterial;
	Material material = new Material();

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
		txtMaterial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMaterial.setText(null);  //limpa a caixa de texto
			}
		});
		txtMaterial.setToolTipText("Digite o novo material…");
		txtMaterial.setText("Digite o novo material…");
		txtMaterial.setBounds(172, 83, 178, 28);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria da Obra");
		lblCategoria.setBounds(43, 43, 117, 16);
		contentPane.add(lblCategoria);

		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(110, 89, 50, 16);
		contentPane.add(lblMaterial);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gravar();

			}
		});
		btnGravar.setBounds(288, 166, 97, 34);
		contentPane.add(btnGravar);

		MaterialController listaCategoria = new MaterialController(cbCategoria); // Preechendo a comboBox CATEGORIA
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setBounds(397, 166, 97, 34);
		contentPane.add(btnFechar);
		cbCategoria.addComponentListener(listaCategoria);
	}

	private void gravar() { 

		IArquivosController arqContr = new ArquivosController();
		material.setNome(txtMaterial.getText());
		try {
			arqContr.escreveArquivo("../MASProject/dados/", "materiais", txtMaterial.getText(), material); // Gravando o novo registro no arquivo.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
