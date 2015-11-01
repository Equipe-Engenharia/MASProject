package view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.AcervoCtrl;

public class FrmAcervoEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idObra, txtArtist, txtObra, txtValor;
	private JFormattedTextField ftxtData;
	private MaskFormatter maskData;
	private DecimalFormat maskValor;
	private JComboBox<String> cbCategoria, cbMaterial, cbSetor, cbSetorT, cbStatus, cbStatusT;
	private JButton btnPesquisarImagem, btnExcluirImagem, btnPesquisaArtist, btnNovoArtista, btnEditarArtista, 
	btnNovaCategoria, btnEditarCategoria, btnNovoMaterial, btnEditarMaterial, 
	btnNovoSetor, btnEditarSetor, btnNovoSetorT, btnEditarSetorT, btnGravar; // deixar os demais botoes private

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAcervoEdit frame = new FrmAcervoEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmAcervoEdit() throws ParseException {
		setResizable(false);
		setTitle("Excluir/Alterar Acervo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 674);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// JLabel lblId = new JLabel("ID.Obra");
		// lblId.setBounds(103, 27, 62, 14);
		// contentPane.add(lblId);

		// textField = new JTextField();
		// textField.setEnabled(false);
		// textField.setBounds(166, 27, 86, 17);
		// textField.setEditable(false);
		// contentPane.add(textField);
		// textField.setColumns(10);

		JLabel lblArtista = new JLabel("Artista");
		lblArtista.setBounds(113, 26, 52, 14);
		contentPane.add(lblArtista);

		JLabel msgGravado = new JLabel("Dados Alterados com sucesso");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(28, 500, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(43, 500, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		txtArtist = new JTextField();
		txtArtist.setBounds(166, 26, 352, 20);
		txtArtist.setEditable(false);
		contentPane.add(txtArtist);
		txtArtist.setColumns(10);

		btnPesquisaArtist = new JButton("");
		btnPesquisaArtist.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaArtist.setBounds(522, 22, 29, 28);
		contentPane.add(btnPesquisaArtist);

		// JButton btnPesquisaId = new JButton("");
		// btnPesquisaId.setIcon(new
		// ImageIcon("../MASProject/icons/search.png"));
		// btnPesquisaId.setBounds(255, 18, 29, 28);
		// contentPane.add(btnPesquisaId);

		JButton btnPesquisaObra = new JButton("");
		btnPesquisaObra.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaObra.setBounds(522, 55, 29, 28);
		contentPane.add(btnPesquisaObra);

		JLabel lblNomeDaObra = new JLabel("Nome da Obra");
		lblNomeDaObra.setBounds(67, 58, 98, 14);
		contentPane.add(lblNomeDaObra);

		JTextField txtNovaObra = new JTextField("Nome da Obra Atualizada");
		txtNovaObra.setBounds(166, 88, 352, 20);
		contentPane.add(txtNovaObra);

		// nome_obra = new JTextField();
		// nome_obra.setBounds(206, 110, 352, 20);
		// contentPane.add(nome_obra);
		// nome_obra.setColumns(10);

		JComboBox<String> cbObras = new JComboBox<String>();
		
		cbObras.setBounds(166, 58, 352, 20);
		contentPane.add(cbObras);

		JLabel lblNewLabel = new JLabel("Data de Composição");
		lblNewLabel.setBounds(28, 120, 139, 14);
		contentPane.add(lblNewLabel);

		maskData = new MaskFormatter("##/##/####");
		ftxtData = new JFormattedTextField(maskData);
		ftxtData.setBounds(168, 120, 86, 20);
		contentPane.add(ftxtData);
		ftxtData.setColumns(10);
		ftxtData.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCategoriaDaObra = new JLabel("Categoria da Obra");
		lblCategoriaDaObra.setBounds(44, 152, 122, 14);
		contentPane.add(lblCategoriaDaObra);

		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(167, 152, 133, 20);
		contentPane.add(cbCategoria);

		JLabel lblMaterial = new JLabel("Material da Obra");
		lblMaterial.setBounds(49, 219, 122, 14);
		contentPane.add(lblMaterial);

		cbMaterial = new JComboBox<String>();
		cbMaterial.setBounds(166, 219, 133, 20);
		contentPane.add(cbMaterial);

		JLabel lblNewLabel_1 = new JLabel("Descrição da Obra");
		lblNewLabel_1.setBounds(28, 330, 139, 14);
		contentPane.add(lblNewLabel_1);

		JEditorPane edtDescricao = new JEditorPane();
		edtDescricao.setBounds(28, 346, 558, 81);
		contentPane.add(edtDescricao);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(125, 261, 46, 14);
		contentPane.add(lblStatus);

		cbStatus = new JComboBox<String>();
		cbStatus.setBounds(167, 258, 133, 20);
		contentPane.add(cbStatus);

		JLabel lblValor = new JLabel("Valor");

		lblValor.setBounds(28, 446, 46, 23);
		contentPane.add(lblValor);

		maskValor = new DecimalFormat("#,###,###.00");
		NumberFormatter formatter = new NumberFormatter(maskValor);
		formatter.setFormat(maskValor);
		formatter.setAllowsInvalid(false);
		txtValor = new JFormattedTextField(maskValor);
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setBounds(68, 448, 110, 20);
		txtValor.setColumns(10);
		
		JLabel Setor = new JLabel("Setor");
		Setor.setBounds(127, 188, 46, 14);
		contentPane.add(Setor);

		cbSetor = new JComboBox<String>();
		cbSetor.setBounds(167, 185, 133, 20);
		contentPane.add(cbSetor);

		JLabel lblSelecImagem = new JLabel("");
		lblSelecImagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		lblSelecImagem.setBackground(SystemColor.inactiveCaption);
		lblSelecImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecImagem.setBounds(397, 137, 189, 147);
		lblSelecImagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(lblSelecImagem);

		btnPesquisarImagem = new JButton("");
		btnPesquisarImagem.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnPesquisarImagem.setBounds(440, 299, 46, 30);
		contentPane.add(btnPesquisarImagem);

		btnExcluirImagem = new JButton("");
		btnExcluirImagem.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluirImagem.setBounds(497, 299, 46, 30);
		contentPane.add(btnExcluirImagem);
		
		JButton btnExcluir = new JButton("Excluir Obra");
		btnExcluir.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluir.setBounds(457, 594, 122, 34);
		
		btnGravar = new JButton("Gravar Alterações");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(298, 594, 122, 34);
		contentPane.add(btnGravar);
		
		idObra = new JTextField();
		
		AcervoCtrl ctrlAcervo = new AcervoCtrl(contentPane,idObra, lblSelecImagem, cbSetor, cbSetorT,
				cbStatus, cbStatusT, cbCategoria, cbMaterial, txtArtist, txtObra, ftxtData, edtDescricao,
				msgGravado, msgVazio, txtValor, btnPesquisaArtist,  btnNovoArtista,
				btnEditarArtista, btnNovaCategoria, btnEditarCategoria, btnNovoMaterial, btnEditarMaterial, 
				btnNovoSetor, btnEditarSetor, btnNovoSetorT, btnEditarSetorT);
		
		contentPane.add(btnExcluir);
		ctrlAcervo.preencherComboBoxCategoria();
		ctrlAcervo.preencherComboBoxMaterial();
		ctrlAcervo.preencherComboBoxSetoresAlteraDel();
		ctrlAcervo.preencherComboStatusProprio();
		ctrlAcervo.preencherComboBoxObras();
		//txtNovaObra.addMouseListener(ctrlAcervo.limpaCampos()); //PRECISA CONSERTO
		btnPesquisarImagem.addActionListener(ctrlAcervo.inserir_imagem);
		btnPesquisaArtist.addActionListener(ctrlAcervo.pesquisaArtistaEditar);
		btnPesquisaObra.addActionListener(ctrlAcervo.pesquisarObra);
		btnExcluirImagem.addActionListener(ctrlAcervo.remover_imagem);
		btnExcluir.addActionListener(ctrlAcervo.excluir_obraAcervo);	
		btnGravar.addActionListener(ctrlAcervo.editar_acervo);
		
		
		
	}
}