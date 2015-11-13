package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.VisitanteCtrl;

public class FrmVisitanteEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId, txtNome, txtDataNasc;
	private JLabel lblNomeDoVisitante, lblNacionalidade, lblSexo, lblIdiomas, lblData;
	private JComboBox<String> cbNacionali;
	private JRadioButton rdbtnMasculino, rdbtnFeminino;
	private JCheckBox checkPT, checkING, checkESP;
	private JButton btnPesquisar, btnEditar, btnApagar;
	private MaskFormatter maskData;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmVisitanteEdit frame = new FrmVisitanteEdit();
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
	public FrmVisitanteEdit() throws ParseException {
		setTitle("Editar Visitante");
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
		txtId.setColumns(10);
		txtId.setVisible(false);
		txtId.setBounds(6, 202, 121, 20);
		contentPane.add(txtId);
		
		lblNomeDoVisitante = new JLabel("Nome do Visitante");
		lblNomeDoVisitante.setBounds(31, 36, 128, 14);
		contentPane.add(lblNomeDoVisitante);

		txtNome = new JTextField();
		txtNome.setBounds(156, 33, 229, 20);
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

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setToolTipText("Use o campo para realizar a busca pelo número de Identificação ou o Material");
		btnPesquisar.setBounds(397, 27, 97, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(288, 166, 97, 34);
		btnEditar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnEditar);
		
		btnApagar = new JButton("Excluir");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApagar.setBounds(397, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		VisitanteCtrl controle = new VisitanteCtrl(contentPane, txtId, txtNome,txtDataNasc, cbNacionali,rdbtnMasculino,rdbtnFeminino, checkING,checkPT,checkESP);
		
		controle.preencherComboBoxNacional();
		txtId.addMouseListener(controle.limpaCampo);
		txtNome.addMouseListener(controle.limpaCampo);
		txtNome.addActionListener(controle.pesquisar);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnApagar.addActionListener(controle.excluir);
		btnEditar.addActionListener(controle.editar);
	}
}
