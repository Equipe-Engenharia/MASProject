package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.IngressoCtrl;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;

public class FrmIngresso extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblId, lblData, lblHora, lblPesquisa, lblBilhete, lblVisita, lblExpo, lblQtd, lblValorUnit, lblValor, lblDinheiro, lblTroco;
	private JTextField txtId, txtData, txtHora, txtBilhete, txtPesquisa, txtQtd;
	private JFormattedTextField  ftxtValorUnit, ftxtValor, ftxtDinheiro, ftxtTroco;
	private MaskFormatter maskData;
	private DecimalFormat maskValor, maskValorUnit, maskDinheiro, maskTroco;
	private JComboBox<String> cbIngresso, cbExpo;
	private JButton btnPesquisar, btnIncluir, btnCancelar, btnGravar;
	private JRadioButton rdbtnDinheiro, rdbtnCard;
	private JScrollPane spCompra;
	private JTable tbCompra;
	private final ButtonGroup pagamento = new ButtonGroup();
	private String[] colunas = { "Exposição", "Visitante", "Entrada", "Qtd", "Valor"};
	private Object[][] dados = { { "Noite Van Gogh", "Fernando M. Oliveira", "Inteira", "1", "R$ 20,00" } };
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmIngresso frame = new FrmIngresso();
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
	
	public FrmIngresso() throws ParseException{
		setTitle("Venda de Ingressos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(59, 28, 27, 28);
		contentPane.add(lblId);
		
		lblData = new JLabel("Data");
		lblData.setBounds(43, 65, 29, 28);
		contentPane.add(lblData);
		
		lblHora = new JLabel("Hora");
		lblHora.setBounds(43, 106, 43, 16);
		contentPane.add(lblHora);

		lblBilhete = new JLabel("Número do Bilhete");
		lblBilhete.setBounds(275, 30, 117, 16);
		contentPane.add(lblBilhete);
		
		lblExpo = new JLabel("Exposição");
		lblExpo.setBounds(324, 69, 68, 16);
		contentPane.add(lblExpo);
		
		lblPesquisa = new JLabel("ID/Nome Visitante");
		lblPesquisa.setBounds(275, 106, 117, 16);
		contentPane.add(lblPesquisa);
		
		lblVisita = new JLabel("Entrada");
		lblVisita.setBounds(345, 145, 47, 16);
		contentPane.add(lblVisita);
		
		lblQtd = new JLabel("Quantidade");
		lblQtd.setBounds(59, 330, 72, 16);
		contentPane.add(lblQtd);
		
		lblValorUnit = new JLabel("Valor Unitário");
		lblValorUnit.setBounds(38, 369, 89, 16);
		contentPane.add(lblValorUnit);
		
		lblValor = new JLabel("Valor Total");
		lblValor.setBounds(59, 406, 68, 16);
		contentPane.add(lblValor);
		
		lblDinheiro = new JLabel("Dinheiro");
		lblDinheiro.setBounds(261, 369, 61, 16);
		contentPane.add(lblDinheiro);
		
		lblTroco = new JLabel("Troco");
		lblTroco.setBounds(279, 406, 43, 16);
		contentPane.add(lblTroco);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(88, 28, 150, 28);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
			
		maskData = new MaskFormatter("##/##/####");
		txtData = new JFormattedTextField(maskData);
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setBounds(88, 65, 98, 28);
		txtData.setEnabled(false);
		txtData.setEditable(false);
		txtData.setColumns(10);
		contentPane.add(txtData);
		
		maskData = new MaskFormatter("##:##");
		txtHora = new JFormattedTextField(maskData);
		txtHora.setEnabled(false);
		txtHora.setEditable(false);
		txtHora.setBounds(88, 104, 98, 28);
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setColumns(10);
		contentPane.add(txtHora);
		
		maskValor = new DecimalFormat("R$ "+"#,###,###.00");
		NumberFormatter bilhete = new NumberFormatter(maskValor);
		bilhete.setFormat(maskValor);
		bilhete.setAllowsInvalid(false);
		txtBilhete = new JFormattedTextField(maskValor);
		txtBilhete.setHorizontalAlignment(SwingConstants.CENTER);
		txtBilhete.setEnabled(false);
		txtBilhete.setEditable(false);
		txtBilhete.setToolTipText("");
		txtBilhete.setBounds(403, 24, 178, 28);
		txtBilhete.setColumns(10);
		contentPane.add(txtBilhete);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setToolTipText("Digite o nome do visitante ou seu ID para realizar a pesquisa");
		txtPesquisa.setBounds(403, 100, 178, 28);
		txtPesquisa.setColumns(10);
		contentPane.add(txtPesquisa);
		
		txtQtd = new JTextField();
		txtQtd.setToolTipText("Digite a quantidade dos ingressos");
		txtQtd.setBounds(144, 324, 98, 28);
		txtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		txtQtd.setColumns(10);
		contentPane.add(txtQtd);
		
		maskValorUnit = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter unitario = new NumberFormatter(maskValorUnit);
		unitario.setFormat(maskValorUnit);
		unitario.setAllowsInvalid(false);
		ftxtValorUnit = new JFormattedTextField(maskValorUnit);
		ftxtValorUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtValorUnit.setEnabled(false);
		ftxtValorUnit.setEditable(false);
		ftxtValorUnit.setBounds(144, 363, 98, 28);
		ftxtValorUnit.setColumns(10);
		contentPane.add(ftxtValorUnit);
		
		maskValor = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter total = new NumberFormatter(maskValor);
		total.setFormat(maskValor);
		total.setAllowsInvalid(false);
		ftxtValor = new JFormattedTextField(maskValor);
		ftxtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtValor.setEnabled(false);
		ftxtValor.setEditable(false);
		ftxtValor.setBounds(144, 400, 98, 28);
		ftxtValor.setColumns(10);
		contentPane.add(ftxtValor);
		
		maskDinheiro = new DecimalFormat("#,###,###.00");
		NumberFormatter dinheiro = new NumberFormatter(maskDinheiro);
		dinheiro.setFormat(maskDinheiro);
		dinheiro.setAllowsInvalid(false);
		ftxtDinheiro = new JFormattedTextField(maskDinheiro);
		ftxtDinheiro.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtDinheiro.setBounds(325, 363, 98, 28);
		ftxtDinheiro.setColumns(10);
		contentPane.add(ftxtDinheiro);
		
		maskTroco = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter troco = new NumberFormatter(maskTroco);
		troco.setFormat(maskTroco);
		troco.setAllowsInvalid(false);
		ftxtTroco = new JFormattedTextField(maskTroco);
		ftxtTroco.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtTroco.setEnabled(false);
		ftxtTroco.setEditable(false);
		ftxtTroco.setBounds(325, 400, 98, 28);
		ftxtTroco.setColumns(10);
		contentPane.add(ftxtTroco);
		
		cbIngresso = new JComboBox<String>();
		cbIngresso.setBounds(403, 139, 178, 28);
		contentPane.add(cbIngresso);
		
		cbExpo = new JComboBox<String>();
		cbExpo.setBounds(403, 63, 326, 28);
		contentPane.add(cbExpo);
		
		rdbtnDinheiro = new JRadioButton("Dinheiro");
		rdbtnDinheiro.setSelected(true);
		pagamento.add(rdbtnDinheiro);
		rdbtnDinheiro.setActionCommand("Dinheiro");
		rdbtnDinheiro.setBounds(261, 327, 86, 23);
		contentPane.add(rdbtnDinheiro);
		
		rdbtnCard = new JRadioButton("Cartão");
		pagamento.add(rdbtnCard);
		rdbtnCard.setActionCommand("Cartão");
		rdbtnCard.setBounds(345, 326, 86, 23);
		contentPane.add(rdbtnCard);
		
		spCompra = new JScrollPane();
		spCompra.setBounds(38, 183, 691, 122);
		contentPane.add(spCompra);
		
		tbCompra = new JTable(dados, colunas);
		tbCompra.setToolTipText("Clique para selecionar…");
		tbCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spCompra.setViewportView(tbCompra);
		tbCompra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(612, 97, 117, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnIncluir.setBounds(464, 321, 117, 34);
		contentPane.add(btnIncluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnCancelar.setBounds(464, 397, 117, 34);
		contentPane.add(btnCancelar);
		
		btnGravar = new JButton("Comprar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		btnGravar.setBounds(612, 397, 117, 34);
		contentPane.add(btnGravar);
		
		IngressoCtrl controle = new IngressoCtrl
				(contentPane, lblDinheiro, lblTroco, txtId, txtData, txtHora, txtBilhete, txtPesquisa, cbExpo, cbIngresso, tbCompra, 
						txtQtd, ftxtValorUnit, ftxtValor, ftxtDinheiro, ftxtTroco, rdbtnDinheiro, pagamento);
		
		controle.gerarId();
		controle.preencheData();
		controle.preencheBilhete();
		controle.preencherComboBoxExpo();
		controle.preencherComboBoxIngresso();
		controle.calculaValor();
		txtPesquisa.addMouseListener(controle.limpaCampo);
		txtQtd.addMouseListener(controle.limpaCampo);
		ftxtDinheiro.addActionListener(controle.valor);
		txtPesquisa.addActionListener(controle.pesquisar);
		cbIngresso.addActionListener(controle.valor);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnCancelar.addActionListener(controle.cancelar);
		btnGravar.addActionListener(controle.gravar);
		txtQtd.addKeyListener(controle.tecla);
		txtQtd.addFocusListener(controle.move);
		ftxtDinheiro.addKeyListener(controle.tecla);
		rdbtnDinheiro.addActionListener(controle.valor);
		rdbtnCard.addActionListener(controle.valor);
	}
}
