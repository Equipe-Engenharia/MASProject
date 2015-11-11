package view;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class FrmVisitanteCad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDataNasc;
	private JLabel lblNomeDoVisitante, lblNacionalidade, lblSexo, lblIdiomas, lblData, lblContinente;
	private JComboBox<String> cbContinente, cbNacionali;
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
		setResizable(false);
		setTitle("Cadastrar Novo Visitante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNome = new JTextField();
		txtNome.setBounds(106, 11, 110, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		lblNomeDoVisitante = new JLabel("Nome do Visitante");
		lblNomeDoVisitante.setBounds(10, 14, 86, 14);
		contentPane.add(lblNomeDoVisitante);
 
		maskData = new MaskFormatter("##/##/####");
		
		txtDataNasc = new JFormattedTextField(maskData);
		txtDataNasc.setBounds(357, 11, 77, 20);
		contentPane.add(txtDataNasc);
		txtDataNasc.setColumns(10);

		lblData = new JLabel("Data de nascimento");
		lblData.setBounds(237, 14, 101, 14);
		contentPane.add(lblData);

		lblContinente = new JLabel("Continente");
		lblContinente.setBounds(34, 58, 62, 14);
		contentPane.add(lblContinente);

		cbContinente = new JComboBox();
		cbContinente.setBounds(106, 55, 110, 20);
		contentPane.add(cbContinente);

		cbNacionali = new JComboBox();
		cbNacionali.setBounds(324, 55, 110, 20);
		contentPane.add(cbNacionali);

		lblNacionalidade = new JLabel("Nascionalidade");
		lblNacionalidade.setBounds(237, 58, 77, 14);
		contentPane.add(lblNacionalidade);

		lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(67, 106, 29, 14);
		contentPane.add(lblSexo);

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(107, 102, 76, 23);
		contentPane.add(rdbtnMasculino);

		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(197, 102, 77, 23);
		contentPane.add(rdbtnFeminino);

		lblIdiomas = new JLabel("Idiomas");
		lblIdiomas.setBounds(50, 144, 46, 14);
		contentPane.add(lblIdiomas);

		checkPT = new JCheckBox("Portugu\u00EAs ");
		checkPT.setBounds(106, 140, 77, 23);
		contentPane.add(checkPT);

		checkING = new JCheckBox("Ingl\u00EAs");
		checkING.setBounds(197, 140, 69, 23);
		contentPane.add(checkING);

		checkESP = new JCheckBox("Espanhol");
		checkESP.setBounds(279, 140, 86, 23);
		contentPane.add(checkESP);

	    btnGravar = new JButton("Gravar");
		btnGravar.setBounds(345, 191, 89, 23);
		contentPane.add(btnGravar);
	}
}
