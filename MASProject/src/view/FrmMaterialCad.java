package view;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.MaterialCtrl;

public class FrmMaterialCad extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> cbCategoria;
	private JTextField txtMaterial;
	private JTextField txtId;
	

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
		setTitle("Registro de Material da Obra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
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
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(180, 102, 178, 28);
		contentPane.add(cbCategoria);
		
		txtMaterial = new JTextField();
		txtMaterial.setToolTipText("Digite o novo material…");
		txtMaterial.setBounds(179, 65, 178, 28);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		MaterialCtrl ctrlMaterial = new MaterialCtrl(contentPane, txtId, cbCategoria, txtMaterial);
		
		ctrlMaterial.gerarId();//ESTA CHAMADA É TEMPORÁRIO - SERÁ IMPLEMNTADO NO MENU COMO EVENTO NA CHAMADA DESTE FORM
		ctrlMaterial.preencherComboBoxCategoria();
		btnGravar.addActionListener(ctrlMaterial.gravarMaterial);
		txtMaterial.addMouseListener(ctrlMaterial.limpaCampo);
		txtMaterial.addActionListener(ctrlMaterial.gravarMaterial);
	}
}
