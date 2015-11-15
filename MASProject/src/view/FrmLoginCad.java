package view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginCtrl;

public class FrmLoginCad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtUsuario;
	private JLabel lblId, lblUsuario;
	private JButton btnCadastrar, btnEditar, btnPesquisar, btnApagar;
	private JLabel lblSenha;
	private JPasswordField pwdSenha;
	private JCheckBox chckbxAdm, chckbxOpera;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLoginCad frame = new FrmLoginCad();
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
	
	public FrmLoginCad() {
		setType(Type.UTILITY);
		setTitle("Login de Acesso");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblId = new JLabel("ID");
		lblId.setVisible(true);
		lblId.setBounds(36, 34, 61, 16);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(true);
		txtId.setBounds(98, 32, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(36, 71, 61, 16);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Digite aqui o usuário…");
		txtUsuario.setBounds(98, 65, 178, 28);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(36, 111, 61, 16);
		contentPane.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setToolTipText("Digite aqui a senha…");
		pwdSenha.setBounds(98, 105, 178, 28);
		contentPane.add(pwdSenha);
		
		chckbxAdm = new JCheckBox("Administrativo");
		buttonGroup.add(chckbxAdm);
		chckbxAdm.setBounds(366, 32, 128, 23);
		contentPane.add(chckbxAdm);
		
		chckbxOpera = new JCheckBox("Operacional");
		chckbxOpera.setSelected(true);
		buttonGroup.add(chckbxOpera);
		chckbxOpera.setBounds(366, 55, 128, 23);
		contentPane.add(chckbxOpera);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(397, 166, 97, 34);
		btnCadastrar.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		contentPane.add(btnCadastrar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(288, 166, 97, 34);
		btnEditar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnEditar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(288, 100, 97, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);

		btnApagar = new JButton("Excluir");
		btnApagar.setBounds(180, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		LoginCtrl controle = new LoginCtrl(contentPane, txtId, txtUsuario, pwdSenha, chckbxAdm, chckbxOpera);
		
		controle.gerarId();
		txtUsuario.addMouseListener(controle.limpaCampo);
		txtUsuario.addActionListener(controle.pesquisar);
		pwdSenha.addActionListener(controle.gravar);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnApagar.addActionListener(controle.excluir);
		btnEditar.addActionListener(controle.editar);
		btnCadastrar.addActionListener(controle.gravar);
	}
}
