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

import controller.MaterialCtrl;

public class FrmMaterialEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCategoria;
	private JTextField txtId;
	private JTextField txtMaterial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMaterialEdit frame = new FrmMaterialEdit();
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
	public FrmMaterialEdit() {
		setTitle("Editar/Excluir Material da Obra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdMaterial = new JLabel("ID");
		lblIdMaterial.setBounds(146, 35, 22, 16);
		contentPane.add(lblIdMaterial);
		
		JLabel lblEditCategoria = new JLabel("Categoria da Obra");
		lblEditCategoria.setBounds(50, 108, 117, 16);
		contentPane.add(lblEditCategoria);
		
		JLabel lblEditMaterial = new JLabel("ID ou o Material");
		lblEditMaterial.setBounds(68, 71, 100, 16);
		contentPane.add(lblEditMaterial);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setToolTipText("Digite o ID do Material");
		txtId.setBounds(180, 33, 178, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		cbCategoria = new JComboBox<String>(); //PARA COMPATIBILIZAR COM CONTROLLER
		cbCategoria.setBounds(179, 102, 178, 28);
		contentPane.add(cbCategoria);
		
		txtMaterial = new JTextField();
		txtMaterial.setToolTipText("Digite o nome do material");
		txtMaterial.setBounds(179, 65, 178, 28);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		
		JButton btnPesquisaMaterial = new JButton("Pesquisar");
		btnPesquisaMaterial.setToolTipText("Use o campo e clique para realizar a busca por número Material");
		btnPesquisaMaterial.setBounds(377, 62, 117, 34);
		btnPesquisaMaterial.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaMaterial);
		
		JButton btnGravar = new JButton("Editar");
		btnGravar.setBounds(288, 166, 97, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		
		JButton btnApagar = new JButton("Excluir");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApagar.setBounds(397, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		MaterialCtrl ctrlMaterial = new MaterialCtrl(contentPane, txtId, cbCategoria, txtMaterial);
		
		ctrlMaterial.preencherComboBoxCategoria();
		btnPesquisaMaterial.addActionListener(ctrlMaterial.pesquisarMaterial);
		btnApagar.addActionListener(ctrlMaterial.excluirMaterial);
		btnGravar.addActionListener(ctrlMaterial.editarMaterial);
		txtId.addMouseListener(ctrlMaterial.limpaCampo);
		txtMaterial.addMouseListener(ctrlMaterial.limpaCampo);
		txtMaterial.addActionListener(ctrlMaterial.pesquisarMaterial);
	}
}
