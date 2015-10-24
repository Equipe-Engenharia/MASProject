package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import controller.ComboArtistaController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormPesqArtista extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbArtista;
	private JLabel lblArtista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPesqArtista frame = new FormPesqArtista();
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
	public FormPesqArtista() {
		setTitle("Pesquisar Artista - MASP");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblArtista = new JLabel("Artista:");
		lblArtista.setBounds(47, 40, 70, 15);
		contentPane.add(lblArtista);
		
		cbArtista = new JComboBox<String>();
		cbArtista.setBounds(135, 37, 224, 20);
		contentPane.add(cbArtista);
		
		JButton btnInserirNome = new JButton("Inserir Nome");
		btnInserirNome.setBounds(50, 160, 141, 25);
		contentPane.add(btnInserirNome);
		
		JButton btnNovoArtista = new JButton("Novo Artista");
		btnNovoArtista.setBounds(218, 160, 141, 25);
		contentPane.add(btnNovoArtista);
		
		ComboArtistaController pesqArtista = new ComboArtistaController(cbArtista);
		cbArtista.addComponentListener(pesqArtista);
	}
}
