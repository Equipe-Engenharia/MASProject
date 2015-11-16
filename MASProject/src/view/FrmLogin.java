package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import controller.SessionCtrl;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtUsuario;
	private JLabel lblId, lblUsuario;
	private JButton btnNovo, btnEntrar;
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
		setType(Type.UTILITY);
		setTitle("Login de Acesso");
		setResizable(false);
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblId = new JLabel("ID");
		lblId.setVisible(false);
		lblId.setBounds(36, 34, 61, 16);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(false);
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
		chckbxAdm.setVisible(false);
		chckbxAdm.setBounds(366, 32, 128, 23);
		contentPane.add(chckbxAdm);
		
		chckbxOpera = new JCheckBox("Operacional");
		buttonGroup.add(chckbxOpera);
		chckbxOpera.setVisible(false);
		chckbxOpera.setBounds(366, 55, 128, 23);
		contentPane.add(chckbxOpera);
		
		btnNovo = new JButton("Novo");
		btnNovo.setVisible(false);
		btnNovo.setBounds(286, 166, 97, 34);
		btnNovo.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		contentPane.add(btnNovo);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(397, 166, 97, 34);
		btnEntrar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		contentPane.add(btnEntrar);

		LoginCtrl controle = new LoginCtrl(contentPane, txtId, txtUsuario, pwdSenha, chckbxAdm, chckbxOpera, btnNovo);

		txtUsuario.addMouseListener(controle.limpaCampo);
		txtUsuario.addActionListener(controle.entrar);
		pwdSenha.addActionListener(controle.entrar);
		btnNovo.addActionListener(controle.cadastrar);
		btnEntrar.addActionListener(controle.entrar);
		
		
	///   PASSAR PARA O CONTROLLER LOGINCTRL  ////////////////
		
		pwdSenha.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				SessionCtrl session = new SessionCtrl();
				if (session.acesso() == false){
					dispose();
				}
			} 
		});
		
		btnEntrar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				SessionCtrl session = new SessionCtrl();
				if (session.acesso() == false){
					dispose();
				}
			} 
		});
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				dispose();	
			} 
		});
	}
}
