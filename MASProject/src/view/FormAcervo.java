package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class FormAcervo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField nome_artist;
	private JTextField nome_obra;
	private JTextField data_obra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAcervo frame = new FormAcervo();
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
	public FormAcervo() {
		setResizable(false);
		setTitle("Registro de Acervo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 674);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID.Obra:");
		lblId.setBounds(69, 26, 46, 14);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(125, 23, 86, 17);
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblArtista = new JLabel("Artista:");
		lblArtista.setBounds(79, 69, 46, 14);
		contentPane.add(lblArtista);
		
		nome_artist = new JTextField();
		nome_artist.setBounds(125, 66, 145, 20);
		contentPane.add(nome_artist);
		nome_artist.setColumns(10);
		
		JButton btnPesquisaArtist = new JButton("");
		btnPesquisaArtist.setBounds(280, 66, 29, 23);
		contentPane.add(btnPesquisaArtist);
		
		JLabel lblNomeDaObra = new JLabel("Nome da obra:");
		lblNomeDaObra.setBounds(39, 114, 76, 14);
		contentPane.add(lblNomeDaObra);
		
		nome_obra = new JTextField();
		nome_obra.setBounds(125, 111, 145, 20);
		contentPane.add(nome_obra);
		nome_obra.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data de composi\u00E7\u00E3o:");
		lblNewLabel.setBounds(12, 163, 105, 14);
		contentPane.add(lblNewLabel);
		
		data_obra = new JTextField();
		data_obra.setBounds(125, 160, 86, 20);
		contentPane.add(data_obra);
		data_obra.setColumns(10);
		
		JLabel lblCategoriaDaObra = new JLabel("Categoria da obra:");
		lblCategoriaDaObra.setBounds(20, 260, 145, 14);
		contentPane.add(lblCategoriaDaObra);
		
		JComboBox comboBox_categoria = new JComboBox();
		comboBox_categoria.setBounds(143, 257, 122, 20);
		contentPane.add(comboBox_categoria);
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setBounds(57, 311, 58, 14);
		contentPane.add(lblMaterial);
		
		JComboBox comboBox_material = new JComboBox();
		comboBox_material.setBounds(143, 308, 122, 20);
		contentPane.add(comboBox_material);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o da Obra:");
		lblNewLabel_1.setBounds(12, 346, 131, 14);
		contentPane.add(lblNewLabel_1);
		
		JEditorPane editor_descricao = new JEditorPane();
		editor_descricao.setBounds(153, 346, 433, 81);
		contentPane.add(editor_descricao);
		
		JLabel lblNoEncontrouA = new JLabel("N\u00E3o encontrou a categoria?");
		lblNoEncontrouA.setBounds(275, 260, 162, 14);
		contentPane.add(lblNoEncontrouA);
		
		JButton btnNovaCategoria = new JButton("Nova Categoria");
		btnNovaCategoria.setBounds(440, 256, 122, 23);
		contentPane.add(btnNovaCategoria);
		
		JLabel lblNoEncontrouO = new JLabel("N\u00E3o encontrou o material?");
		lblNoEncontrouO.setBounds(275, 311, 162, 14);
		contentPane.add(lblNoEncontrouO);
		
		JButton btnNovoMaterial = new JButton("Novo Material");
		btnNovoMaterial.setBounds(440, 307, 122, 23);
		contentPane.add(btnNovoMaterial);
		
		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(39, 438, 547, 127);
		contentPane.add(abas);
		
		JPanel panel_proprio = new JPanel();
		abas.addTab("Obra Pr\u00F3pria", null, panel_proprio, null);
		
		JPanel panel_terceiros = new JPanel();
		abas.addTab("Obra de Terceiro", null, panel_terceiros, null);
		
		JButton btnGravar = new JButton("GRAVAR");
		btnGravar.setBounds(36, 588, 89, 23);
		contentPane.add(btnGravar);
		
		JLabel lblSelecImagem = new JLabel("Selecione uma imagem...");
		lblSelecImagem.setBackground(SystemColor.inactiveCaption);
		lblSelecImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecImagem.setBounds(397, 26, 189, 151);
		lblSelecImagem.setBorder(BorderFactory.createLineBorder(Color.black, 1));  
		contentPane.add(lblSelecImagem);
		
		JButton btnPesquisar = new JButton("+");
		btnPesquisar.setBounds(440, 188, 46, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnExcluir = new JButton("x");
		btnExcluir.setBounds(497, 188, 46, 23);
		contentPane.add(btnExcluir);
		
		
	}
}