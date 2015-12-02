package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.MaterialCtrl;

public class FrmMaterialCad extends JInternalFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCategoria;
	private JTextField txtMaterial,txtId;
	private JButton btnGravar;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMaterialCad frame = new FrmMaterialCad();
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
	
	public FrmMaterialCad() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Registro de Material da Obra");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocation(0,0);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdMaterial = new JLabel("ID");
		lblIdMaterial.setBounds(154, 35, 14, 16);
		contentPane.add(lblIdMaterial);

		JLabel lblCategoria = new JLabel("Categoria da Obra");
		lblCategoria.setBounds(51, 108, 117, 16);
		contentPane.add(lblCategoria);
		
		JLabel lblNovoMaterial = new JLabel("Novo Material");
		lblNovoMaterial.setBounds(81, 71, 87, 16);
		contentPane.add(lblNovoMaterial);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(180, 33, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
		
		
		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(180, 102, 178, 28);
		contentPane.add(cbCategoria);
		
		txtMaterial = new JTextField();
		txtMaterial.setToolTipText("Digite o novo material…");
		txtMaterial.setBounds(179, 65, 178, 28);
		txtMaterial.setColumns(10);
		contentPane.add(txtMaterial);
		

		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		MaterialCtrl controle = new MaterialCtrl(contentPane, txtId, cbCategoria, txtMaterial);
		
		controle.gerarId();
		controle.preencherComboBoxCategoria();
		btnGravar.addActionListener(controle.gravar);
		txtMaterial.addMouseListener(controle.limpaCampo);
		txtMaterial.addActionListener(controle.gravar);
	}
}
