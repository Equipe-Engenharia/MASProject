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

import controller.UsuarioCtrl;

public class FrmUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtUsuario;
	private JLabel lblId, lblUsuario, lblSenha, lblSenha2;
	private JButton btnCadastrar, btnEditar, btnPesquisar, btnApagar;
	private JPasswordField pwdSenha, pwdSenha2;
	private JCheckBox chckbxAdm, chckbxOpera;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUsuario frame = new FrmUsuario();
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
	
	public FrmUsuario() {
		setType(Type.UTILITY);
		setTitle("Administrar Usuários");
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
		lblId.setBounds(35, 21, 61, 16);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(true);
		txtId.setBounds(128, 19, 149, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
		
		lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(35, 56, 61, 16);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("Digite aqui o usuário…");
		txtUsuario.setBounds(128, 50, 149, 28);
		txtUsuario.setColumns(10);
		contentPane.add(txtUsuario);
		
		lblSenha = new JLabel("Nova Senha");
		lblSenha.setBounds(35, 94, 91, 16);
		contentPane.add(lblSenha);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setToolTipText("Digite aqui a senha…");
		pwdSenha.setBounds(128, 88, 149, 28);
		contentPane.add(pwdSenha);
		
		lblSenha2 = new JLabel("Repita a Senha");
		lblSenha2.setBounds(35, 132, 91, 16);
		contentPane.add(lblSenha2);
		
		pwdSenha2 = new JPasswordField();
		pwdSenha2.setToolTipText("Digite aqui a senha…");
		pwdSenha2.setBounds(128, 126, 149, 28);
		contentPane.add(pwdSenha2);
		
		chckbxAdm = new JCheckBox("Administrativo");
		buttonGroup.add(chckbxAdm);
		chckbxAdm.setBounds(366, 98, 128, 23);
		contentPane.add(chckbxAdm);
		
		chckbxOpera = new JCheckBox("Operacional");
		chckbxOpera.setSelected(true);
		buttonGroup.add(chckbxOpera);
		chckbxOpera.setBounds(366, 121, 128, 23);
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
		btnPesquisar.setBounds(288, 47, 97, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);

		btnApagar = new JButton("Excluir");
		btnApagar.setBounds(180, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		UsuarioCtrl controle = new UsuarioCtrl(contentPane, txtId, txtUsuario, pwdSenha, pwdSenha2, chckbxAdm, chckbxOpera, btnCadastrar);
		
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
