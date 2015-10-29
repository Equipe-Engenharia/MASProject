package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AlteraDelArtistaController; 
  
public class FormAlteraDelArtista extends JDialog{  
  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idArtista;
	private JTextField txtArtista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAlteraDelMaterial frame = new FormAlteraDelMaterial();
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
	public FormAlteraDelArtista(FormAcervo parent, boolean modal) {
		setTitle("MASP - Artista");
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
		
		idArtista = new JTextField();
		idArtista.setToolTipText("Digite o ID do Artista…");
		idArtista.setBounds(180, 54, 178, 20);
		contentPane.add(idArtista);
		idArtista.setColumns(10);
		
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
		
		JButton btnPesquisaArtista = new JButton(" Busca Aritsta");
		btnPesquisaArtista.setToolTipText("Use o campo e clique para realizar a busca por número Material");
		btnPesquisaArtista.setBounds(376, 90, 117, 34);
		btnPesquisaArtista.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisaArtista);
		
		AlteraDelArtistaController ctrlADArtista = new AlteraDelArtistaController(idArtista, txtArtista, btnApagar, btnGravar, msgGravar, msgVazio);
		
		btnPesquisaId.addActionListener(ctrlADArtista.pesquisarArtista);
		btnPesquisaArtista.addActionListener(ctrlADArtista.pesquisarArtista);
		btnApagar.addActionListener(ctrlADArtista.apagarArtista);
		btnGravar.addActionListener(ctrlADArtista.gravarArtista);
		txtArtista.addMouseListener(ctrlADArtista.limpaCampo);
		txtArtista.addActionListener(ctrlADArtista.gravarArtista);
	}
}
       