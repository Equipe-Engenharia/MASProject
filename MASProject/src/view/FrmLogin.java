package view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginCtrl;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtNivel, txtUsuario;
	private JLabel lblId, lblUsuario;
	private JButton btnCadastrar, btnEntrar;
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
					FrmLogin frame = new FrmLogin();
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
	
	public FrmLogin() {
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
		lblId.setVisible(false);
		lblId.setBounds(118, 32, 61, 16);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setBounds(180, 30, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
		
		txtNivel = new JTextField();
		txtNivel.setEnabled(false);
		txtNivel.setEditable(false);
		txtNivel.setVisible(false);
		txtNivel.setBounds(370, 26, 124, 28);
		contentPane.add(txtNivel);
		txtNivel.setColumns(10);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(118, 69, 61, 16);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Digite aqui o usuário…");
		txtUsuario.setBounds(180, 63, 178, 28);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(118, 109, 61, 16);
		contentPane.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setToolTipText("Digite aqui a senha…");
		pwdSenha.setBounds(180, 103, 178, 28);
		contentPane.add(pwdSenha);
		
		chckbxAdm = new JCheckBox("Administrativo");
		buttonGroup.add(chckbxAdm);
		chckbxAdm.setVisible(false);
		chckbxAdm.setBounds(386, 105, 128, 23);
		contentPane.add(chckbxAdm);
		
		chckbxOpera = new JCheckBox("Operacional");
		buttonGroup.add(chckbxOpera);
		chckbxOpera.setVisible(false);
		chckbxOpera.setBounds(386, 128, 128, 23);
		contentPane.add(chckbxOpera);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(286, 166, 97, 34);
		btnCadastrar.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		contentPane.add(btnCadastrar);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(397, 166, 97, 34);
		btnEntrar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		contentPane.add(btnEntrar);

		LoginCtrl controle = new LoginCtrl(contentPane, txtId, txtUsuario, pwdSenha, chckbxAdm, chckbxOpera);

		txtUsuario.addMouseListener(controle.limpaCampo);
		txtUsuario.addActionListener(controle.entrar);
		pwdSenha.addActionListener(controle.entrar);
		btnEntrar.addActionListener(controle.entrar);
	}
}
