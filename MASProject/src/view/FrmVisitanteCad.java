package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.VisitanteCtrl;

public class FrmVisitanteCad extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	public JTextField txtNome;
	private JTextField txtDataNasc;
	private JLabel lblNomeDoVisitante, lblNacionalidade, lblSexo, lblIdiomas, lblData;
	private JComboBox<String> cbNacional;
	private JRadioButton rdbtnMasculino, rdbtnFeminino;
	private JCheckBox checkPT, checkING, checkESP;
	private JButton btnGravar;
	private MaskFormatter maskData;
	private final ButtonGroup idiomas = new ButtonGroup();
	private final ButtonGroup sexo = new ButtonGroup();

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
		setClosable(true);
		setIconifiable(true);
		setTitle("Cadastrar Novo Visitante");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocation(0,0);
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
		lblNomeDoVisitante.setBounds(31, 23, 128, 14);
		contentPane.add(lblNomeDoVisitante);

		txtNome = new JTextField();
		txtNome.setBounds(156, 20, 338, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
 
		maskData = new MaskFormatter("##/##/####");
		
		lblData = new JLabel("Data de nascimento");
		lblData.setBounds(32, 62, 138, 14);
		contentPane.add(lblData);
		
		txtDataNasc = new JFormattedTextField(maskData);
		txtDataNasc.setBounds(157, 59, 101, 20);
		contentPane.add(txtDataNasc);
		txtDataNasc.setColumns(10);
		
		lblNacionalidade = new JLabel("Nacionalidade");
		lblNacionalidade.setBounds(261, 62, 91, 14);
		contentPane.add(lblNacionalidade);

		cbNacional = new JComboBox<String>();
		cbNacional.setBounds(350, 59, 144, 20);
		contentPane.add(cbNacional);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(31, 101, 39, 14);
		contentPane.add(lblSexo);

		rdbtnMasculino = new JRadioButton("Masculino");
		sexo.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(31, 121, 96, 23);
		contentPane.add(rdbtnMasculino);

		rdbtnFeminino = new JRadioButton("Feminino");
		sexo.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(31, 141, 101, 23);
		contentPane.add(rdbtnFeminino);

		lblIdiomas = new JLabel("Idioma do assistente auditivo:");
		lblIdiomas.setBounds(156, 101, 190, 14);
		contentPane.add(lblIdiomas);

		checkPT = new JCheckBox("Portugu\u00EAs ");
		idiomas.add(checkPT);
		checkPT.setBounds(176, 121, 110, 23);
		contentPane.add(checkPT);

		checkING = new JCheckBox("Ingl\u00EAs");
		idiomas.add(checkING);
		checkING.setBounds(176, 141, 81, 23);
		contentPane.add(checkING);

		checkESP = new JCheckBox("Espanhol");
		idiomas.add(checkESP);
		checkESP.setBounds(176, 161, 101, 23);
		contentPane.add(checkESP);

		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		VisitanteCtrl controle = new VisitanteCtrl(contentPane, txtId, txtNome,txtDataNasc, cbNacional, rdbtnMasculino,rdbtnFeminino,checkPT, checkING,checkESP);
		
		controle.gerarId();
		controle.preencherComboBoxNacional();
		btnGravar.addActionListener(controle.gravar);
		txtNome.addMouseListener(controle.limpaCampo);
		txtNome.addActionListener(controle.gravar);
	}
}
