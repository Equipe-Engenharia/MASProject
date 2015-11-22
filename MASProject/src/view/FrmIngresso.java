package view;

import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.IngressoCtrl;

public class FrmIngresso extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblId, lblData, lblHora, lblPesquisa, lblBilhete, lblVisita, lblExpo, lblQtd, lblValorUnit, lblSubtotal, lblTotal, lblDinheiro, lblTroco;
	private JTextField txtId, txtData, txtHora, txtBilhete, txtPesquisa, txtQtd;
	private JFormattedTextField  ftxtValorUnit, ftxtSubtotal, ftxtDinheiro, ftxtTotal, ftxtTroco;
	private MaskFormatter maskData;
	private DecimalFormat maskValor, maskValorUnit, maskDinheiro, maskTotal, maskTroco;
	private JComboBox<String> cbIngresso, cbExpo;
	private JButton btnPesquisar, btnIncluir, btnCancelar, btnGravar;
	private JRadioButton rdbtnDinheiro, rdbtnCard;
	private JScrollPane spCompra;
	private JTable tbCompra;
	private final ButtonGroup pagamento = new ButtonGroup();
	
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
		lblQtd.setBounds(250, 330, 72, 16);
		contentPane.add(lblQtd);
		
		lblValorUnit = new JLabel("Valor Unitário");
		lblValorUnit.setBounds(38, 330, 89, 16);
		contentPane.add(lblValorUnit);
		
		lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(75, 369, 52, 16);
		contentPane.add(lblSubtotal);
		
		lblDinheiro = new JLabel("Dinheiro");
		lblDinheiro.setBounds(261, 369, 61, 16);
		contentPane.add(lblDinheiro);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(95, 406, 32, 16);
		contentPane.add(lblTotal);
		
		lblTroco = new JLabel("Troco");
		lblTroco.setBounds(279, 406, 43, 16);
		contentPane.add(lblTroco);
		
		JLabel lblPagamento = new JLabel("Tipo de Pagamento");
		lblPagamento.setBounds(461, 330, 120, 16);
		contentPane.add(lblPagamento);
		
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
		txtQtd.setBounds(325, 324, 98, 28);
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
		ftxtValorUnit.setBounds(144, 324, 98, 28);
		ftxtValorUnit.setColumns(10);
		contentPane.add(ftxtValorUnit);
		
		maskValor = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter subtotal = new NumberFormatter(maskValor);
		subtotal.setFormat(maskValor);
		subtotal.setAllowsInvalid(false);
		ftxtSubtotal = new JFormattedTextField(maskValor);
		ftxtSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtSubtotal.setEnabled(false);
		ftxtSubtotal.setEditable(false);
		ftxtSubtotal.setBounds(144, 363, 98, 28);
		ftxtSubtotal.setColumns(10);
		contentPane.add(ftxtSubtotal);
		
		maskDinheiro = new DecimalFormat("#,###,###.00");
		NumberFormatter dinheiro = new NumberFormatter(maskDinheiro);
		dinheiro.setFormat(maskDinheiro);
		dinheiro.setAllowsInvalid(false);
		ftxtDinheiro = new JFormattedTextField(maskDinheiro);
		ftxtDinheiro.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtDinheiro.setBounds(325, 363, 98, 28);
		ftxtDinheiro.setColumns(10);
		contentPane.add(ftxtDinheiro);
		
		maskTotal = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter total = new NumberFormatter(maskTotal);
		total.setFormat(maskTotal);
		total.setAllowsInvalid(false);
		ftxtTotal = new JFormattedTextField(maskTotal);
		ftxtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtTotal.setEnabled(false);
		ftxtTotal.setEditable(false);
		ftxtTotal.setBounds(144, 400, 98, 28);
		ftxtTotal.setColumns(10);
		contentPane.add(ftxtTotal);
		
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
		rdbtnDinheiro.setBounds(612, 323, 86, 23);
		contentPane.add(rdbtnDinheiro);
		
		rdbtnCard = new JRadioButton("Cartão");
		pagamento.add(rdbtnCard);
		rdbtnCard.setActionCommand("Cartão");
		rdbtnCard.setBounds(612, 348, 86, 23);
		contentPane.add(rdbtnCard);
		
		spCompra = new JScrollPane();
		spCompra.setBounds(38, 183, 691, 122);
		contentPane.add(spCompra);
		
		tbCompra = new JTable();
		tbCompra.setToolTipText("Clique para selecionar…");
		spCompra.setViewportView(tbCompra);
		tbCompra.setBorder(null);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(612, 97, 117, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnIncluir.setBounds(464, 360, 117, 34);
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
						txtQtd, ftxtValorUnit, ftxtSubtotal, ftxtTotal, ftxtDinheiro, ftxtTroco, rdbtnDinheiro, pagamento);
		
		controle.atualizaId();
		controle.atualizaTempo();
		controle.atualizaBilhete();
		controle.preencherComboBoxExpo();
		controle.preencherComboBoxIngresso();
		controle.atualizaValor();
		
		txtPesquisa.addActionListener(controle.pesquisar);
		//txtPesquisa.addKeyListener(controle.tecla);
		//txtPesquisa.addMouseListener(controle.limpaCampo);
		txtQtd.addActionListener(controle.incluir);
		txtQtd.addMouseListener(controle.limpaCampo);
		txtQtd.addKeyListener(controle.tecla);
		txtQtd.addFocusListener(controle.move);
		ftxtDinheiro.addActionListener(controle.valor);
		ftxtDinheiro.addKeyListener(controle.tecla);
		cbIngresso.addActionListener(controle.valor);
		tbCompra.addMouseListener(controle.limpaCampo);
		tbCompra.addKeyListener(controle.tecla);
		rdbtnDinheiro.addActionListener(controle.valor);
		rdbtnCard.addActionListener(controle.valor);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnCancelar.addActionListener(controle.cancelar);
		btnIncluir.addActionListener(controle.valor);
		btnIncluir.addActionListener(controle.incluir);
		btnGravar.addActionListener(controle.gravar);
	}
}
