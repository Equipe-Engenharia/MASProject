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
	private JTextField idArtista;
	
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
		
		idArtista = new JTextField();
		idArtista.setEnabled(false);
		idArtista.setEditable(false);
		idArtista.setBounds(181, 49, 178, 20);
		idArtista.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(idArtista);
		idArtista.setColumns(10);
		
		txtArtista = new JTextField();
		txtArtista.setToolTipText("Digite o novo Artista…");
		txtArtista.setBounds(180, 81, 178, 28);
		contentPane.add(txtArtista);
		txtArtista.setColumns(10);
		
		JLabel msgGravado = new JLabel("");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(43, 177, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 177, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(288, 166, 97, 34);
		contentPane.add(btnGravar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon("../MASProject/icons/out.png"));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});				
		btnFechar.setBounds(397, 166, 97, 34);
		contentPane.add(btnFechar);
		
		ArtistaCtrl ctrlArtista = new ArtistaCtrl(idArtista, txtArtista, btnGravar, msgGravado, msgVazio);
		
		ctrlArtista.gerarIdSetor();
		btnGravar.addActionListener(ctrlArtista.gravarArtista);
		txtArtista.addMouseListener(ctrlArtista.limpaCampo);
		txtArtista.addActionListener(ctrlArtista.gravarArtista);
    }  
}  

