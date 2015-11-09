package view;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ArtistaCtrl;  
  
public class FrmArtistaCad extends JFrame {  

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblId, lblArtista;
	private JTextField txtArtista, txtId;
	private JButton btnGravar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmArtistaCad frame = new FrmArtistaCad();
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
	
	public FrmArtistaCad() {  
		setTitle("Registro de Artista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(148, 51, 21, 16);
		contentPane.add(lblId);
		
		lblArtista = new JLabel("Novo Artista");
		lblArtista.setBounds(84, 87, 97, 16);
		contentPane.add(lblArtista);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(181, 49, 178, 20);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtArtista = new JTextField();
		txtArtista.setToolTipText("Digite o novo Artista…");
		txtArtista.setBounds(180, 81, 178, 28);
		contentPane.add(txtArtista);
		txtArtista.setColumns(10);

		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		ArtistaCtrl controle = new ArtistaCtrl (contentPane, txtId, txtArtista);
		
		controle.gerarId();
		btnGravar.addActionListener(controle.gravar);
		txtArtista.addMouseListener(controle.limpaCampo);
		txtArtista.addActionListener(controle.gravar);
    }  
}  

