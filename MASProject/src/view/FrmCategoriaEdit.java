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
import javax.swing.border.EmptyBorder;

import controller.CategoriaCtrl;

public class FrmCategoriaEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtCategoria;
	private JButton btnApagar, btnGravar, btnIdPesquisa, btnCategoriaPesquisa;
	private JLabel msgGravar, msgVazio; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCategoriaEdit frame = new FrmCategoriaEdit();
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
	public FrmCategoriaEdit() {
		setTitle("Editar/Excluir Categoria de Obra");
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
		
		JLabel lblEditCategoria = new JLabel("Editar Categoria");
		lblEditCategoria.setBounds(67, 93, 100, 16);
		contentPane.add(lblEditCategoria);
		
		txtId = new JTextField();
		txtId.setToolTipText("Digite o ID do Artista…");
		txtId.setBounds(180, 54, 178, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.setToolTipText("Digite o nome do material");
		txtCategoria.setBounds(178, 93, 178, 28);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		msgGravar = new JLabel("");
		msgGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravar.setBounds(36, 141, 230, 23);
		msgGravar.setVisible(false);
		contentPane.add(msgGravar);

		msgVazio = new JLabel("CAMPO VAZIO!");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(36, 142, 322, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setEnabled(false);
		btnGravar.setBounds(288, 166, 97, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApagar.setEnabled(false);
		btnApagar.setBounds(397, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		btnIdPesquisa = new JButton(" Busca ID");
		btnIdPesquisa.setToolTipText("Use o campo e clique para realizar a busca por número ID");
		btnIdPesquisa.setBounds(377, 47, 117, 34);
		btnIdPesquisa.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnIdPesquisa);
		
		btnCategoriaPesquisa = new JButton(" Busca Categoria");
		btnCategoriaPesquisa.setToolTipText("Use o campo e clique para realizar a busca por nome da Categoria");
		btnCategoriaPesquisa.setBounds(376, 90, 117, 34);
		btnCategoriaPesquisa.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnCategoriaPesquisa);
		
		CategoriaCtrl ctrlCategoria = new CategoriaCtrl(txtId, txtCategoria, btnApagar, btnGravar, msgGravar, msgVazio, btnIdPesquisa, btnCategoriaPesquisa);
		
		btnIdPesquisa.addActionListener(ctrlCategoria.pesquisaIdCat);
		btnCategoriaPesquisa.addActionListener(ctrlCategoria.pesquisarNomeCat);
		btnApagar.addActionListener(ctrlCategoria.apagarCategoria);
		btnGravar.addActionListener(ctrlCategoria.gravarCategoriaEdit);

	}
}
