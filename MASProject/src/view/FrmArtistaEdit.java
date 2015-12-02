package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ArtistaCtrl; 
  
public class FrmArtistaEdit extends JInternalFrame{  
  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblId, lblArtista;
	private JTextField txtId, txtArtista;
	private JButton btnPesquisar, btnEditar, btnApagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmArtistaEdit frame = new FrmArtistaEdit();
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
	
	public FrmArtistaEdit() {
		setTitle("Editar/Excluir Artista");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		setClosable(true);
		setIconifiable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocation(0,0);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(146, 35, 22, 16);
		contentPane.add(lblId);
		
		lblArtista = new JLabel("ID ou Artista");
		lblArtista.setBounds(77, 71, 91, 16);
		contentPane.add(lblArtista);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(180, 33, 178, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtArtista = new JTextField();
		txtArtista.setToolTipText("Digite o nome do Artista");
		txtArtista.setBounds(179, 65, 178, 28);
		contentPane.add(txtArtista);
		txtArtista.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setToolTipText("Use o campo para realizar a busca pelo Número de Identificação ou nome do Artista");
		btnPesquisar.setBounds(377, 62, 117, 34);
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
		
		ArtistaCtrl controle = new ArtistaCtrl (contentPane, txtId, txtArtista);
		
		txtId.addMouseListener(controle.limpaCampo);
		txtArtista.addMouseListener(controle.limpaCampo);
		txtArtista.addActionListener(controle.pesquisar);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnApagar.addActionListener(controle.excluir);
		btnEditar.addActionListener(controle.editar);
	}
}
       