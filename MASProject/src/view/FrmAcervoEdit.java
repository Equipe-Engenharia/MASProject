package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.AcervoCtrl;

public class FrmAcervoEdit extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtArtist, txtObra;
	private JFormattedTextField txtValor;
	private JFormattedTextField ftxtData;
	private MaskFormatter maskData;
	private DecimalFormat maskValor;
	private JComboBox<String> cbCategoria, cbMaterial, cbSetor, cbSetorT, cbStatus, cbStatusT;
	private JButton btnPesquisarImagem, btnExcluirImagem, btnPesquisaArtist, btnNovoArtista, btnEditarArtista,
			btnNovaCategoria, btnEditarCategoria, btnNovoMaterial, btnEditarMaterial, btnNovoSetor, btnEditarSetor,
			btnNovoSetorT, btnEditarSetorT; // deixar os demais
														// botoes private

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
	
	public void setPosicao() throws ParseException {  
	    Dimension d = this.getDesktopPane().getSize();  
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}

	public FrmAcervoEdit() throws ParseException {
		setClosable(true);
		setIconifiable(true);
		setResizable(false);
		setTitle("Excluir/Alterar Acervo");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1088, 680);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocation(0,0);

		JLabel lblArtista = new JLabel("Artista");
		lblArtista.setBounds(106, 91, 52, 14);
		contentPane.add(lblArtista);

		txtArtist = new JTextField();
		txtArtist.setBounds(159, 88, 342, 20);
		txtArtist.setEditable(false);
		contentPane.add(txtArtist);
		txtArtist.setColumns(10);

		btnPesquisaArtist = new JButton("");
		btnPesquisaArtist.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaArtist.setBounds(502, 84, 29, 28);
		contentPane.add(btnPesquisaArtist);

		JLabel lblId = new JLabel("Procurar pelo ID");
		lblId.setBounds(54, 36, 107, 14);
		contentPane.add(lblId);

		JTextField idObra2 = new JTextField();
		idObra2.setBounds(162, 33, 150, 20);
		idObra2.setEditable(false);
		idObra2.setColumns(10);
		contentPane.add(idObra2);


		JButton btnPesquisaId = new JButton("");
		btnPesquisaId.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaId.setBounds(315, 29, 29, 28);
		contentPane.add(btnPesquisaId);

		JLabel msgGravado = new JLabel("Dados Alterados com sucesso");
		msgGravado.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		msgGravado.setBounds(810, 504, 230, 23);
		msgGravado.setVisible(false);
		contentPane.add(msgGravado);

		JLabel msgVazio = new JLabel("");
		msgVazio.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		msgVazio.setBounds(50, 669, 192, 23);
		msgVazio.setVisible(false);
		contentPane.add(msgVazio);

		JButton btnPesquisaObra = new JButton("");
		btnPesquisaObra.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		btnPesquisaObra.setBounds(502, 122, 29, 28);
		contentPane.add(btnPesquisaObra);

		JLabel lblNomeDaObra = new JLabel("Nome da Obra");
		lblNomeDaObra.setBounds(60, 168, 98, 14);
		contentPane.add(lblNomeDaObra);

		final JTextField txtNovaObra = new JTextField("Editar nome da Obra");
		txtNovaObra.setBounds(159, 165, 342, 20);
		txtNovaObra.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				txtNovaObra.setText("");

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				txtNovaObra.setText("");
			}
		});
		contentPane.add(txtNovaObra);
		
		JLabel lblPesqObra = new JLabel("Procurar por Obra");
		lblPesqObra.setBounds(41, 129, 117, 14);
		contentPane.add(lblPesqObra);

		JComboBox<String> cbObras = new JComboBox<String>();
		cbObras.setBounds(159, 126, 342, 20);
		contentPane.add(cbObras);

		JLabel lblNewLabel = new JLabel("Data de Composição");
		lblNewLabel.setBounds(519, 168, 139, 14);
		contentPane.add(lblNewLabel);

		maskData = new MaskFormatter("##/##/####");
		ftxtData = new JFormattedTextField(maskData);
		ftxtData.setBounds(659, 165, 98, 20);
		contentPane.add(ftxtData);
		ftxtData.setColumns(10);
		ftxtData.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCategoriaDaObra = new JLabel("Categoria");
		lblCategoriaDaObra.setBounds(85, 208, 73, 14);
		contentPane.add(lblCategoriaDaObra);

		cbCategoria = new JComboBox<String>();
		cbCategoria.setBounds(159, 205, 133, 20);
		contentPane.add(cbCategoria);

		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(96, 246, 62, 14);
		contentPane.add(lblMaterial);

		cbMaterial = new JComboBox<String>();
		cbMaterial.setBounds(159, 243, 133, 20);
		contentPane.add(cbMaterial);

		JLabel lblNewLabel_1 = new JLabel("Descrição da Obra");
		lblNewLabel_1.setBounds(60, 322, 139, 14);
		contentPane.add(lblNewLabel_1);

		JEditorPane edtDescricao = new JEditorPane();
		edtDescricao.setBounds(60, 338, 962, 106);
		contentPane.add(edtDescricao);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(318, 246, 54, 14);
		contentPane.add(lblStatus);

		cbStatus = new JComboBox<String>();
		cbStatus.setBounds(368, 243, 133, 20);
		contentPane.add(cbStatus);

		JLabel lblValor = new JLabel("Valor da Aquisição");
		lblValor.setBounds(60, 469, 150, 23);
		contentPane.add(lblValor);

		maskValor = new DecimalFormat("#,###,###.00");
		NumberFormatter formatter = new NumberFormatter(maskValor);
		formatter.setFormat(maskValor);
		formatter.setAllowsInvalid(false);
		txtValor = new JFormattedTextField(maskValor);
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValor.setBounds(182, 471, 110, 20);
		txtValor.setColumns(10);
		contentPane.add(txtValor);

		JLabel Setor = new JLabel("Setor");
		Setor.setBounds(318, 208, 46, 14);
		contentPane.add(Setor);

		cbSetor = new JComboBox<String>();
		cbSetor.setBounds(368, 205, 133, 20);
		contentPane.add(cbSetor);

		JLabel lblSelecImagem = new JLabel("");
		lblSelecImagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		lblSelecImagem.setBackground(SystemColor.inactiveCaption);
		lblSelecImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecImagem.setBounds(772, 114, 250, 155);
		lblSelecImagem.setBorder(new LineBorder(Color.GRAY));
		contentPane.add(lblSelecImagem);

		btnPesquisarImagem = new JButton("");
		btnPesquisarImagem.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnPesquisarImagem.setBounds(843, 280, 46, 30);
		contentPane.add(btnPesquisarImagem);

		btnExcluirImagem = new JButton("");
		btnExcluirImagem.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluirImagem.setBounds(899, 280, 46, 30);
		contentPane.add(btnExcluirImagem);

		JButton btnExcluir = new JButton("Excluir Obra");
		btnExcluir.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnExcluir.setBounds(905, 582, 117, 34);

		JButton btnGravar = new JButton("Gravar Alterações");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnGravar.setBounds(772, 583, 107, 34);
		btnGravar.setEnabled(false);
		btnGravar.setToolTipText("Carregue Uma Obra Para Editar");
		contentPane.add(btnGravar);



		AcervoCtrl ctrlAcervo = new AcervoCtrl(contentPane, lblSelecImagem, lblStatus, lblValor, cbSetor,
				cbSetorT, cbStatus, cbStatusT, cbCategoria, cbObras, cbMaterial, txtArtist, txtObra, txtNovaObra,
				ftxtData, edtDescricao, msgGravado, msgVazio, txtValor, btnPesquisaArtist, btnNovoArtista,
				btnEditarArtista, btnNovaCategoria, btnEditarCategoria, btnNovoMaterial, btnEditarMaterial,
				btnNovoSetor, btnEditarSetor, btnNovoSetorT, btnEditarSetorT ,idObra2,btnGravar);

		contentPane.add(btnExcluir);
		ctrlAcervo.preencherComboBoxCategoria();
		ctrlAcervo.preencherComboBoxMaterial();
		ctrlAcervo.preencherComboBoxSetoresAlteraDel();
		ctrlAcervo.preencherComboStatusProprio();
		ctrlAcervo.preencherComboBoxObras();
		btnPesquisarImagem.addActionListener(ctrlAcervo.inserir_imagem);
		btnPesquisaId.addActionListener(ctrlAcervo.pesquisarPorId);
		btnPesquisaArtist.addActionListener(ctrlAcervo.pesquisaArtistaEditar);
		btnPesquisaObra.addActionListener(ctrlAcervo.pesquisarObra);
		btnExcluirImagem.addActionListener(ctrlAcervo.remover_imagem);
		btnExcluir.addActionListener(ctrlAcervo.excluir_obraAcervo);
		btnGravar.addActionListener(ctrlAcervo.editar_acervo);

	}
}