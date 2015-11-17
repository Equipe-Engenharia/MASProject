package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ExposicaoCtrl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

public class FrmExposicaoCad extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblIdExposio, lblTtuloDaExposio, lblDataInicio, lblDataFim, lblDescrio, lblArtista, lblTema;
	private JPanel contentPane;
	private JButton btnCalendario, btnCalenIni, btnPesqArtista, btnCalenFim, btnGravar, btnLimpar;
	private JTextField txtID, txtTitulo, txtDataIni, txtDataFim, txtDescri, txtNomeArtista, txtTema;
	private JTable tableLista;

	public static void main(String[] args) {
		new FrmExposicaoCad().setVisible(true);
	}

	public FrmExposicaoCad() {
		setTitle("Nova Exposi\u00E7\u00E3o");
		setResizable(false);
		FrmExp();
		setLocationRelativeTo(null);
	}

	public void FrmExp() {
		btnCalendario = new JButton();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCalenIni = new JButton("");
		btnCalenIni.setIcon(new ImageIcon(
				FrmExposicaoCad.class.getResource("/com/toedter/calendar/images/JDateChooserColor32.gif")));
		btnCalenIni.setBounds(251, 107, 29, 23);
		contentPane.add(btnCalenIni);

		lblIdExposio = new JLabel("ID. Exposi\u00E7\u00E3o");
		lblIdExposio.setBounds(56, 30, 78, 14);
		contentPane.add(lblIdExposio);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(157, 27, 226, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(157, 58, 301, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		lblTtuloDaExposio = new JLabel("T\u00EDtulo da Exposi\u00E7\u00E3o");
		lblTtuloDaExposio.setBounds(33, 61, 114, 14);
		contentPane.add(lblTtuloDaExposio);

		txtDataIni = new JTextField();
		txtDataIni.setBounds(157, 110, 86, 20);
		contentPane.add(txtDataIni);
		txtDataIni.setColumns(10);

		lblDataInicio = new JLabel("Data Inicio");
		lblDataInicio.setBounds(73, 113, 61, 14);
		contentPane.add(lblDataInicio);

		lblDataFim = new JLabel("Data Fim");
		lblDataFim.setBounds(319, 113, 61, 14);
		contentPane.add(lblDataFim);

		txtDataFim = new JTextField();
		txtDataFim.setBounds(373, 110, 86, 20);
		contentPane.add(txtDataFim);
		txtDataFim.setColumns(10);

	    btnCalenFim = new JButton("");
		btnCalenFim.setIcon(new ImageIcon(
				FrmExposicaoCad.class.getResource("/com/toedter/calendar/images/JDateChooserColor32.gif")));
		btnCalenFim.setBounds(469, 107, 29, 23);
		contentPane.add(btnCalenFim);

		lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(73, 222, 61, 14);
		contentPane.add(lblDescrio);

		txtDescri = new JTextField();
		txtDescri.setBounds(155, 219, 709, 81);
		contentPane.add(txtDescri);
		txtDescri.setColumns(10);

		lblArtista = new JLabel("Artista");
		lblArtista.setBounds(88, 385, 46, 14);
		contentPane.add(lblArtista);

		txtNomeArtista = new JTextField();
		txtNomeArtista.setBounds(157, 382, 262, 20);
		contentPane.add(txtNomeArtista);
		txtNomeArtista.setColumns(10);

		btnPesqArtista = new JButton("");
		btnPesqArtista.setBounds(429, 379, 29, 23);
		btnPesqArtista.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesqArtista);

		txtTema = new JTextField();
		txtTema.setBounds(155, 156, 303, 20);
		contentPane.add(txtTema);
		txtTema.setColumns(10);

		lblTema = new JLabel("Tema");
		lblTema.setBounds(97, 159, 37, 14);
		contentPane.add(lblTema);

		tableLista = new JTable();
		tableLista.setColumnSelectionAllowed(true);
		tableLista.setCellSelectionEnabled(true);
		tableLista.setBounds(157, 429, 417, 157);
		contentPane.add(tableLista);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(775, 618, 89, 23);
		contentPane.add(btnGravar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(676, 618, 89, 23);
		contentPane.add(btnLimpar);
		
		ExposicaoCtrl expCtrl = new ExposicaoCtrl();

		btnCalenIni.addActionListener(expCtrl.abreCalendario);
		btnCalenFim.addActionListener(expCtrl.abreCalendario);
		btnPesqArtista.addActionListener(expCtrl.pesquisaArtista);

	}
}
