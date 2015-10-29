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

import controller.AlteraDelCategoriaController;

public class FormAlteraDelCategoria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idCategoria;
	private JTextField txtCategoria;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAlteraDelCategoria frame = new FormAlteraDelCategoria();
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
	public FormAlteraDelCategoria() {
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
		
		idCategoria = new JTextField();
		idCategoria.setToolTipText("Digite o ID do Artista…");
		idCategoria.setBounds(180, 54, 178, 20);
		contentPane.add(idCategoria);
		idCategoria.setColumns(10);
		
		txtCategoria = new JTextField();
		txtCategoria.setToolTipText("Digite o nome do material");
		txtCategoria.setBounds(178, 93, 178, 28);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		
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
		btnGravar.setBounds(159, 166, 117, 34);
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		contentPane.add(btnGravar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApagar.setEnabled(false);
		btnApagar.setBounds(288, 166, 97, 34);
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		contentPane.add(btnApagar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setBounds(397, 166, 97, 34);
		btnFechar.setIcon(new ImageIcon("../MASProject/icons/out.png"));
		contentPane.add(btnFechar);
		
		JButton btnPesquisaId = new JButton(" Busca ID");
		btnPesquisaId.setToolTipText("Use o campo e clique para realizar a busca por número ID");
		btnPesquisaId.setBounds(377, 47, 117, 34);
		btnPesquisaId.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaId);
		
		JButton btnPesquisaCategoria = new JButton(" Busca Categoria");
		btnPesquisaCategoria.setToolTipText("Use o campo e clique para realizar a busca por nome da Categoria");
		btnPesquisaCategoria.setBounds(376, 90, 117, 34);
		btnPesquisaCategoria.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaCategoria);
		
		AlteraDelCategoriaController ctrlADCategoria = new AlteraDelCategoriaController(idCategoria, txtCategoria, btnApagar, btnGravar, msgGravar, msgVazio);
		
		btnPesquisaId.addActionListener(ctrlADCategoria.pesquisarCategoria);
		btnPesquisaCategoria.addActionListener(ctrlADCategoria.pesquisarCategoria);
		btnApagar.addActionListener(ctrlADCategoria.apagarCategoria);
		btnGravar.addActionListener(ctrlADCategoria.gravarCategoria);
		idCategoria.addMouseListener(ctrlADCategoria.limpaCampo);
		txtCategoria.addMouseListener(ctrlADCategoria.limpaCampo);
		txtCategoria.addActionListener(ctrlADCategoria.gravarCategoria);
	}
}

