package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JTextField txtArtista;
	private JTextField txtId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMaterialEdit frame = new FrmMaterialEdit();
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
	public FrmArtistaCad(FrmAcervoCad parent, boolean modal) {  
		setTitle("Registro de Artista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdArtista = new JLabel("ID");
		lblIdArtista.setBounds(148, 51, 21, 16);
		contentPane.add(lblIdArtista);
		
		JLabel lblNovoArtista = new JLabel("Novo Artista");
		lblNovoArtista.setBounds(84, 87, 97, 16);
		contentPane.add(lblNovoArtista);
		
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
		
		JLabel msgGravar = new JLabel("");
		msgGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravar.setBounds(43, 177, 230, 23);
		msgGravar.setVisible(false);
		contentPane.add(msgGravar);

		JLabel msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 177, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(397, 166, 97, 34);
		contentPane.add(btnGravar);
		
		ArtistaCtrl ctrlArtista = new ArtistaCtrl(txtId, txtArtista, btnGravar, msgGravar, msgVazio);
		
		ctrlArtista.gerarId();
		btnGravar.addActionListener(ctrlArtista.gravarArtista);
		txtArtista.addMouseListener(ctrlArtista.limpaCampo);
		txtArtista.addActionListener(ctrlArtista.gravarArtista);
    }  
}  

