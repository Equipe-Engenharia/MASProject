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
	private JTextField idMaterial;
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
		lblEditCategoria.setBounds(50, 70, 117, 16);
		contentPane.add(lblEditCategoria);
		
		JLabel lblEditMaterial = new JLabel("Editar Material");
		lblEditMaterial.setBounds(71, 101, 97, 16);
		contentPane.add(lblEditMaterial);
		
		idMaterial = new JTextField();
		idMaterial.setToolTipText("Digite o ID do Material");
		idMaterial.setBounds(180, 33, 178, 20);
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
		
		JLabel msgGravar = new JLabel("");
		msgGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravar.setBounds(36, 141, 230, 23);
		msgGravar.setVisible(false);
		contentPane.add(msgGravar);

		JLabel msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(36, 142, 322, 23);
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
		
		JButton btnPesquisaId = new JButton(" Busca ID");
		btnPesquisaId.setToolTipText("Use o campo e clique para realizar a busca por número ID");
		btnPesquisaId.setBounds(377, 26, 117, 34);
		btnPesquisaId.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaId);
		
		JButton btnPesquisaMaterial = new JButton(" Busca Material");
		btnPesquisaMaterial.setToolTipText("Use o campo e clique para realizar a busca por número Material");
		btnPesquisaMaterial.setBounds(377, 98, 117, 34);
		btnPesquisaMaterial.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaMaterial);
		
		MaterialCtrl ctrlADMaterial = new MaterialCtrl(idMaterial, cbCategoria, txtMaterial, btnApagar, btnGravar, msgGravar, msgVazio);
		
		ctrlADMaterial.preencherComboBoxCategoria();
		btnPesquisaId.addActionListener(ctrlADMaterial.pesquisarMaterial);
		btnPesquisaMaterial.addActionListener(ctrlADMaterial.pesquisarMaterial);
		btnApagar.addActionListener(ctrlADMaterial.apagarMaterial);
		btnGravar.addActionListener(ctrlADMaterial.gravarMaterial);
		idMaterial.addMouseListener(ctrlADMaterial.limpaCampo);
		txtMaterial.addMouseListener(ctrlADMaterial.limpaCampo);
		txtMaterial.addActionListener(ctrlADMaterial.gravarMaterial);
	}
}
