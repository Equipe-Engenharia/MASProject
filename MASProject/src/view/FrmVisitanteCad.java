package view;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.VisitanteCtrl;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

public class FrmVisitanteCad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtNome, txtDataNasc;
	private JLabel lblNomeDoVisitante, lblNacionalidade, lblSexo, lblIdiomas, lblData;
	private JComboBox<String> cbNacionali;
	private JRadioButton rdbtnMasculino, rdbtnFeminino;
	private JCheckBox checkPT, checkING, checkESP;
	private JButton btnGravar;
	private MaskFormatter maskData;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmVisitanteCad frame = new FrmVisitanteCad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public FrmVisitanteCad() throws ParseException {
		setTitle("Cadastrar Novo Visitante");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setVisible(false);
		txtId.setColumns(10);
		txtId.setBounds(6, 202, 121, 20);
		contentPane.add(txtId);
		
		lblNomeDoVisitante = new JLabel("Nome do Visitante");
		lblNomeDoVisitante.setBounds(31, 36, 128, 14);
		contentPane.add(lblNomeDoVisitante);

		txtNome = new JTextField();
		txtNome.setBounds(156, 33, 337, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
 
		maskData = new MaskFormatter("##/##/####");
		
		lblData = new JLabel("Data de nascimento");
		lblData.setBounds(31, 78, 138, 14);
		contentPane.add(lblData);
		
		txtDataNasc = new JFormattedTextField(maskData);
		txtDataNasc.setBounds(156, 75, 101, 20);
		contentPane.add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		lblNacionalidade = new JLabel("Nacionalidade");
		lblNacionalidade.setBounds(260, 78, 91, 14);
		contentPane.add(lblNacionalidade);

		cbNacionali = new JComboBox<String>();
		cbNacionali.setBounds(349, 75, 144, 20);
		contentPane.add(cbNacionali);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(31, 116, 39, 14);
		contentPane.add(lblSexo);

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(31, 136, 96, 23);
		contentPane.add(rdbtnMasculino);

		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(31, 156, 101, 23);
		contentPane.add(rdbtnFeminino);

		lblIdiomas = new JLabel("Idioma do assistente auditivo:");
		lblIdiomas.setBounds(156, 116, 190, 14);
		contentPane.add(lblIdiomas);

		checkPT = new JCheckBox("Portugu\u00EAs ");
		checkPT.setBounds(176, 136, 110, 23);
		contentPane.add(checkPT);

		checkING = new JCheckBox("Ingl\u00EAs");
		checkING.setBounds(176, 156, 81, 23);
		contentPane.add(checkING);

		checkESP = new JCheckBox("Espanhol");
		checkESP.setBounds(176, 176, 101, 23);
		contentPane.add(checkESP);

		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		VisitanteCtrl controle = new VisitanteCtrl(contentPane, txtId, txtNome,txtDataNasc, cbNacionali,rdbtnMasculino,rdbtnFeminino, checkING,checkPT,checkESP);
		
		
		
		controle.gerarId();
		controle.preencherComboBoxNacional();
		btnGravar.addActionListener(controle.gravar);
		txtNome.addMouseListener(controle.limpaCampo);
		txtNome.addActionListener(controle.gravar);
	}
}
