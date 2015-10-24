package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class FormPesqObra extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbProprietario;
	private JComboBox<String> cbArtista;
	private JComboBox<String> cbIdObra;
	private JComboBox<String> cbNomeObra;
	private JButton btnEncontreiObra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPesqObra frame = new FormPesqObra();
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
	public FormPesqObra() {
		setTitle("Pesquisar Obra de Arte - MASP");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProprietario = new JLabel("Proprietário:");
		lblProprietario.setBounds(45, 35, 102, 15);
		contentPane.add(lblProprietario);
		
		cbProprietario = new JComboBox<String>();
		cbProprietario.setBounds(148, 30, 102, 24);
		contentPane.add(cbProprietario);
		
		JLabel lblArtista = new JLabel("Artista:");
		lblArtista.setBounds(45, 100, 70, 15);
		contentPane.add(lblArtista);
		
		cbArtista = new JComboBox<String>();
		cbArtista.setBounds(148, 95, 102, 24);
		contentPane.add(cbArtista);
		
		JLabel lblIdObra = new JLabel("ID. Obra:");
		lblIdObra.setBounds(282, 35, 70, 15);
		contentPane.add(lblIdObra);
		
		cbIdObra = new JComboBox<String>();
		cbIdObra.setBounds(403, 30, 102, 24);
		contentPane.add(cbIdObra);
		
		JLabel lblNomeDaObra = new JLabel("Nome da Obra:");
		lblNomeDaObra.setBounds(282, 100, 115, 15);
		contentPane.add(lblNomeDaObra);
		
		cbNomeObra = new JComboBox<String>();
		cbNomeObra.setBounds(403, 95, 102, 24);
		contentPane.add(cbNomeObra);
		
		btnEncontreiObra = new JButton("ENCONTREI A OBRA");
		btnEncontreiObra.setBounds(45, 165, 184, 25);
		contentPane.add(btnEncontreiObra);
	}
}
