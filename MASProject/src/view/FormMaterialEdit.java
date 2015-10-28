package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MaterialController;

public class FormMaterialEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCategoria;
	private JTextField idMaterial;
	private JTextField txtMaterial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMaterialEdit frame = new FormMaterialEdit();
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
	public FormMaterialEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdMaterial = new JLabel("ID Material");
		lblIdMaterial.setBounds(93, 35, 75, 16);
		contentPane.add(lblIdMaterial);
		
		JLabel lblEditCategoria = new JLabel("Categoria da Obra");
		lblEditCategoria.setBounds(50, 70, 117, 16);
		contentPane.add(lblEditCategoria);
		
		JLabel lblEditMaterial = new JLabel("Editar Material");
		lblEditMaterial.setBounds(71, 101, 97, 16);
		contentPane.add(lblEditMaterial);
		
		idMaterial = new JTextField();
		idMaterial.setToolTipText("Digite o ID do Material");
		idMaterial.setBounds(180, 33, 86, 20);
		contentPane.add(idMaterial);
		idMaterial.setColumns(10);
		
		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(179, 65, 178, 28);
		contentPane.add(cbCategoria);
		
		txtMaterial = new JTextField();
		txtMaterial.setToolTipText("Digite o nome do material");
		txtMaterial.setBounds(179, 101, 178, 28);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		
		JLabel msgGravado = new JLabel("");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(36, 142, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(36, 142, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setBounds(159, 166, 117, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		JButton btnBuscaId = new JButton(" Busca ID");
		btnBuscaId.setToolTipText("Use o campo e clique para realizar a busca por número ID");
		btnBuscaId.setBounds(377, 26, 117, 34);
		btnBuscaId.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnBuscaId);
		
		JButton btnBuscaMaterial = new JButton(" Busca Material");
		btnBuscaMaterial.setToolTipText("Use o campo e clique para realizar a busca por número Material");
		btnBuscaMaterial.setBounds(377, 98, 117, 34);
		btnBuscaMaterial.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnBuscaMaterial);
		
		MaterialController ctrlMaterial = new MaterialController(cbCategoria,idMaterial, txtMaterial, btnApagar, btnGravar, msgGravado, msgVazio);
		
		ctrlMaterial.preencherComboBoxCategoria();
		btnGravar.addActionListener(ctrlMaterial.gravarMaterial);
		txtMaterial.addMouseListener(ctrlMaterial.limpaCampo);
		txtMaterial.addActionListener(ctrlMaterial.gravarMaterial);
		
	}
}
