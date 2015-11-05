package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ArtistaEditCtrl;
import model.Artista; 
  
public class FrmArtistaEdit extends JDialog{  
  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtArtista;

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
	Artista artista = new Artista();
	public FrmArtistaEdit(FrmAcervoCad parent, boolean modal) {
		setTitle("Editar/Excluir Artista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdArtista = new JLabel("ID");
		lblIdArtista.setBounds(146, 56, 22, 16);
		contentPane.add(lblIdArtista);
		
		JLabel lblEditArtista = new JLabel("Editar Artista");
		lblEditArtista.setBounds(70, 93, 97, 16);
		contentPane.add(lblEditArtista);
		
		txtId = new JTextField();
		txtId.setToolTipText("Digite o ID do Artista…");
		txtId.setBounds(180, 54, 178, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtArtista = new JTextField();
		txtArtista.setToolTipText("Digite o nome do material");
		txtArtista.setBounds(178, 93, 178, 28);
		contentPane.add(txtArtista);
		txtArtista.setColumns(10);
		
		JLabel msgGravar = new JLabel("");
		msgGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravar.setBounds(36, 141, 230, 23);
		msgGravar.setVisible(false);
		contentPane.add(msgGravar);

		JLabel msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(36, 142, 322, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setBounds(288, 166, 97, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApagar.setEnabled(false);
		btnApagar.setBounds(397, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		final ArtistaEditCtrl ctrlADArtista = new ArtistaEditCtrl(txtId, txtArtista, btnApagar, btnGravar, msgGravar, msgVazio);
	
		JButton btnEditar = new JButton("Editar");
	//	btnEditar.setEnabled(false);
		btnEditar.setBounds(200, 166, 97, 34);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrlADArtista.editar("../MASProject/dados", "artistas","../MASProject/dados", "editado",artista);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnEditar);
		
		JButton btnPesquisaId = new JButton(" Busca ID");
		btnPesquisaId.setToolTipText("Use o campo e clique para realizar a busca por número ID");
		btnPesquisaId.setBounds(377, 47, 117, 34);
		btnPesquisaId.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaId);
		
		JButton btnPesquisaArtista = new JButton(" Busca Artista");
		btnPesquisaArtista.setToolTipText("Use o campo e clique para realizar a busca por nome de Artista");
		btnPesquisaArtista.setBounds(376, 90, 117, 34);
		btnPesquisaArtista.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaArtista);
		
		//ArtistaEditCtrl ctrlADArtista = new ArtistaEditCtrl(txtId, txtArtista, btnApagar, btnGravar, msgGravar, msgVazio);
		
		btnPesquisaId.addActionListener(ctrlADArtista.pesquisarArtista);
		btnPesquisaArtista.addActionListener(ctrlADArtista.pesquisarArtista);
		btnApagar.addActionListener(ctrlADArtista.apagarArtista);
		btnGravar.addActionListener(ctrlADArtista.gravarArtista);
	//	btnEditar.addActionListener(ctrlADArtista.editar(diretorio, arquivo, diretorio2, arquivo2);;
		txtId.addMouseListener(ctrlADArtista.limpaCampo);
		txtArtista.addMouseListener(ctrlADArtista.limpaCampo);
		txtArtista.addActionListener(ctrlADArtista.gravarArtista);
	}
}
       