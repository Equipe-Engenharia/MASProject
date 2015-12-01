package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.AcervoCtrl;

public class FrmAcervoCad extends JInternalFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtObra;
	private JTextField idObra, txtArtist;
	private JFormattedTextField ftxtData, txtValor;
	private MaskFormatter maskData;
	private DecimalFormat maskValor;
	private JComboBox<String> cbMaterial;
	private JComboBox<String> cbCategoria, cbSetor, cbSetorT, cbStatus, cbStatusT;
	private JButton btnPesquisarImagem, btnExcluirImagem, btnPesquisaArtist, btnNovoArtista, btnEditarArtista,
			btnNovaCategoria, btnEditarCategoria, btnNovoMaterial, btnEditarMaterial, btnNovoSetor, btnEditarSetor,
			btnNovoSetorT, btnEditarSetorT, btnGravar; // DEIXAR OS
																	// BOTOES EM
																	// PRIVATE
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 * 
	 * @return
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAcervoCad frame = new FrmAcervoCad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setPosicao() throws ParseException {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

	/**
	 * Create the frame.
	 */

	public FrmAcervoCad() throws ParseException {
		setClosable(true);
		setIconifiable(true);
		setResizable(false);
		setTitle("Registro de Acervo");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1088, 680); //
		contentPane = new JPanel();
		setLocation(0,0);
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		JLabel lblId = new JLabel("ID Obra");
		lblId.setBounds(97, 34, 62, 14);
		contentPane.add(lblId);

		idObra = new JTextField();
		idObra.setEnabled(false);
		idObra.setBounds(160, 34, 150, 20);
		idObra.setEditable(false);
		idObra.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(idObra);
		idObra.setColumns(10);

		JLabel lblArtista = new JLabel("Artista");
		lblArtista.setBounds(109, 106, 52, 14);
		contentPane.add(lblArtista);

		JLabel msgGravado = new JLabel("Dados Salvos com sucesso");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(810, 504, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(50, 669, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		txtArtist = new JTextField("");
		txtArtist.setEditable(false);
		txtArtist.setBounds(162, 103, 342, 20);
		contentPane.add(txtArtist);
		txtArtist.setColumns(10);

		// Modificado a visibilidade do botão para privado - Vitor
		// Ações transferido para o acervoController - Vitor
		btnPesquisaArtist = new JButton("");
		btnPesquisaArtist.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaArtist.setBounds(505, 99, 29, 28);
		contentPane.add(btnPesquisaArtist);

		btnNovoArtista = new JButton("Novo Artista");
		btnNovoArtista.setBounds(541, 99, 107, 29);
		contentPane.add(btnNovoArtista);

		btnEditarArtista = new JButton("Editar Artista");
		btnEditarArtista.setBounds(650, 99, 107, 29);
		contentPane.add(btnEditarArtista);

		JLabel lblNomeDaObra = new JLabel("Nome da Obra");
		lblNomeDaObra.setBounds(63, 145, 98, 14);
		contentPane.add(lblNomeDaObra);

		txtObra = new JTextField();
		txtObra.setBounds(162, 142, 342, 20);
		contentPane.add(txtObra);
		txtObra.setColumns(10);

		JLabel lblNewLabel = new JLabel("Data de Composição");
		lblNewLabel.setBounds(522, 145, 139, 14);
		contentPane.add(lblNewLabel);

		maskData = new MaskFormatter("##/##/####");
		ftxtData = new JFormattedTextField(maskData);
		ftxtData.setBounds(662, 142, 98, 20);
		contentPane.add(ftxtData);
		ftxtData.setColumns(10);
		ftxtData.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCategoriaDaObra = new JLabel("Categoria");
		lblCategoriaDaObra.setBounds(88, 185, 73, 14);
		contentPane.add(lblCategoriaDaObra);

		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(162, 182, 133, 20);
		contentPane.add(cbCategoria);

		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(99, 223, 62, 14);
		contentPane.add(lblMaterial);

		cbMaterial = new JComboBox<String>();
		cbMaterial.setBounds(162, 220, 133, 20);
		contentPane.add(cbMaterial);

		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o da Obra");
		lblNewLabel_1.setBounds(63, 299, 139, 14);
		contentPane.add(lblNewLabel_1);

		btnNovaCategoria = new JButton("Nova Categoria");
		btnNovaCategoria.setToolTipText("Não encontrou a categoria?");
		btnNovaCategoria.setBounds(317, 178, 126, 29);
		contentPane.add(btnNovaCategoria);

		btnEditarCategoria = new JButton("Editar Categoria");
		btnEditarCategoria.setBounds(449, 178, 126, 29);
		contentPane.add(btnEditarCategoria);

		btnNovoMaterial = new JButton("Novo Material");
		btnNovoMaterial.setToolTipText("Não encontrou o material?");
		btnNovoMaterial.setBounds(317, 216, 126, 29);
		contentPane.add(btnNovoMaterial);

		btnEditarMaterial = new JButton("Editar Material");
		btnEditarMaterial.setBounds(449, 216, 126, 29);
		contentPane.add(btnEditarMaterial);

		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(63, 450, 558, 179);
		contentPane.add(abas);

		JPanel panel_proprio = new JPanel();
		abas.addTab("Obra Pr\u00F3pria", null, panel_proprio, null);
		panel_proprio.setLayout(null);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(29, 86, 46, 14);
		panel_proprio.add(lblStatus);

		cbStatus = new JComboBox<String>();
		cbStatus.setBounds(71, 83, 179, 20);
		panel_proprio.add(cbStatus);

		JLabel Setor = new JLabel("Setor");
		Setor.setBounds(29, 46, 46, 14);
		panel_proprio.add(Setor);

		cbSetor = new JComboBox<String>();
		cbSetor.setBounds(71, 43, 179, 20);
		panel_proprio.add(cbSetor);

		JLabel lblValorDaAquisio = new JLabel("Valor da aquisi\u00E7\u00E3o (R$)");
		lblValorDaAquisio.setBounds(265, 83, 143, 14);
		panel_proprio.add(lblValorDaAquisio);

		maskValor = new DecimalFormat("#,###,###.00");
		NumberFormatter formatter = new NumberFormatter(maskValor);
		formatter.setFormat(maskValor);
		formatter.setAllowsInvalid(false);
		txtValor = new JFormattedTextField(maskValor);
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setBounds(416, 80, 110, 20);
		panel_proprio.add(txtValor);
		txtValor.setColumns(10);

		JPanel panel_terceiros = new JPanel();
		abas.addTab("Obra de Terceiro", null, panel_terceiros, null);
		panel_terceiros.setLayout(null);

		JLabel lblStatus_1 = new JLabel("Status");
		lblStatus_1.setBounds(29, 86, 46, 14);
		panel_terceiros.add(lblStatus_1);

		cbStatusT = new JComboBox<String>();
		cbStatusT.setBounds(71, 83, 179, 20);
		panel_terceiros.add(cbStatusT);

		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setBounds(29, 46, 46, 14);
		panel_terceiros.add(lblSetor);

		cbSetorT = new JComboBox<String>();
		cbSetorT.setBounds(71, 43, 179, 20);
		panel_terceiros.add(cbSetorT);

		JLabel lblSelecImagem = new JLabel("");
		lblSelecImagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		lblSelecImagem.setBackground(SystemColor.inactiveCaption);
		lblSelecImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecImagem.setBounds(775, 91, 250, 155);
		lblSelecImagem.setBorder(new LineBorder(Color.GRAY));
		contentPane.add(lblSelecImagem);

		btnPesquisarImagem = new JButton("");
		btnPesquisarImagem.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnPesquisarImagem.setBounds(846, 257, 46, 30);
		contentPane.add(btnPesquisarImagem);

		btnExcluirImagem = new JButton("");
		btnExcluirImagem.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluirImagem.setBounds(902, 257, 46, 30);
		contentPane.add(btnExcluirImagem);

		btnNovoSetorT = new JButton("Novo Setor");
		btnNovoSetorT.setBounds(409, 39, 117, 29);
		panel_terceiros.add(btnNovoSetorT);

		btnEditarSetorT = new JButton("Editar Setor");
		btnEditarSetorT.setBounds(293, 39, 117, 29);
		panel_terceiros.add(btnEditarSetorT);

		btnEditarSetor = new JButton("Editar Setor");
		btnEditarSetor.setBounds(293, 39, 117, 29);
		panel_proprio.add(btnEditarSetor);

		btnNovoSetor = new JButton("Novo Setor");
		btnNovoSetor.setBounds(409, 39, 117, 29);
		panel_proprio.add(btnNovoSetor);

		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(918, 583, 107, 34);
		contentPane.add(btnGravar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 316, 962, 106);
		contentPane.add(scrollPane);
		
				JEditorPane editDescricao = new JEditorPane();
				scrollPane.setViewportView(editDescricao);
				editDescricao.setForeground(UIManager.getColor("TableHeader.foreground"));
				
						// botão de pesquisar artista e o Jpanel passados como parametro -
						// Vitor
						final AcervoCtrl ctrlAcervo = new AcervoCtrl(contentPane, idObra, lblSelecImagem, cbSetor, cbSetorT, cbStatus,
								cbStatusT, cbCategoria, cbMaterial, txtArtist, txtObra, ftxtData, editDescricao, msgGravado, msgVazio,
								txtValor, btnPesquisaArtist, btnNovoArtista, btnEditarArtista, btnNovaCategoria, btnEditarCategoria,
								btnNovoMaterial, btnEditarMaterial, btnNovoSetor, btnEditarSetor, btnNovoSetorT, btnEditarSetorT);

		btnPesquisarImagem.addActionListener(ctrlAcervo.inserir_imagem);
		btnExcluirImagem.addActionListener(ctrlAcervo.remover_imagem);
		btnPesquisaArtist.addActionListener(ctrlAcervo);
		btnNovoArtista.addActionListener(ctrlAcervo);
		btnEditarArtista.addActionListener(ctrlAcervo);
		btnNovaCategoria.addActionListener(ctrlAcervo.novaCategoria);
		btnEditarCategoria.addActionListener(ctrlAcervo.editarCategoria);
		btnNovoMaterial.addActionListener(ctrlAcervo);
		btnEditarMaterial.addActionListener(ctrlAcervo);
		btnNovoSetor.addActionListener(ctrlAcervo);
		btnEditarSetor.addActionListener(ctrlAcervo);
		btnNovoSetorT.addActionListener(ctrlAcervo);
		btnEditarSetorT.addActionListener(ctrlAcervo);
		btnGravar.addActionListener(ctrlAcervo.gravarAcervo);

		// Colocar esses Listeners no
		// Controller*****************************************************
		cbMaterial.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ctrlAcervo.preencherComboBoxMaterial();

			}
		});
		cbCategoria.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ctrlAcervo.preencherComboBoxCategoria();

			}
		});
		cbSetor.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ctrlAcervo.preencherComboBoxSetores();

			}
		});
		cbSetorT.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ctrlAcervo.preencherComboBoxSetores();

			}
		});
		cbStatus.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ctrlAcervo.preencherComboStatusProprio();

			}
		});
		cbStatusT.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ctrlAcervo.preencherComboStatusTerceiro();

			}
		});
		ctrlAcervo.gerarId();
		ctrlAcervo.preencherComboBoxCategoria();
		ctrlAcervo.preencherComboBoxMaterial();
		ctrlAcervo.preencherComboBoxSetores();
		ctrlAcervo.preencherComboStatusProprio();
		ctrlAcervo.preencherComboStatusTerceiro();

	}
}
