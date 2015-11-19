package view;

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
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.IngressoCtrl;

public class FrmIngresso extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblId, lblData, lblHora, lblPesquisa, lblBilhete, lblVisita, lblExpo, lblQtd, lblValorUnit, lblValor;
	private JTextField txtId, txtData, txtHora, txtBilhete, txtPesquisa, txtQtd;
	private JFormattedTextField  ftxtValorUnit, ftxtValor;
	private MaskFormatter maskData;
	private DecimalFormat maskValor;
	private JComboBox<String> cbIngresso, cbExpo;
	private JButton btnPesquisar, btnIncluir, btnCancelar, btnGravar;
	private JTable tbCompra;
	
	

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
		lblData.setBounds(43, 67, 29, 28);
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
		txtData.setBounds(88, 67, 98, 28);
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
		
		maskValor = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter unitario = new NumberFormatter(maskValor);
		unitario.setFormat(maskValor);
		unitario.setAllowsInvalid(false);
		ftxtValorUnit = new JFormattedTextField(maskValor);
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
		
		cbIngresso = new JComboBox<String>();
		cbIngresso.setBounds(403, 139, 178, 28);
		contentPane.add(cbIngresso);
		
		cbExpo = new JComboBox<String>();
		cbExpo.setBounds(403, 63, 326, 28);
		contentPane.add(cbExpo);
		
		tbCompra = new JTable();
		tbCompra.setBounds(38, 190, 691, 115);
		contentPane.add(tbCompra);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(612, 97, 117, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnIncluir.setBounds(275, 321, 117, 34);
		contentPane.add(btnIncluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnCancelar.setBounds(464, 394, 117, 34);
		contentPane.add(btnCancelar);
		
		btnGravar = new JButton("Comprar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		btnGravar.setBounds(612, 394, 117, 34);
		contentPane.add(btnGravar);
		
		IngressoCtrl controle = new IngressoCtrl(contentPane, txtId, txtData, txtHora, txtBilhete, txtPesquisa, cbExpo, cbIngresso, tbCompra, txtQtd, ftxtValorUnit, ftxtValor);
		
		controle.gerarId();
		controle.preencheData();
		controle.preencheBilhete();
		controle.preencherComboBoxExpo();
		controle.preencherComboBoxIngresso();
		controle.calculaValor();
		txtPesquisa.addMouseListener(controle.limpaCampo);
		txtQtd.addKeyListener(controle.tecla);
		cbIngresso.addActionListener(controle.valor);
		btnCancelar.addActionListener(controle.cancelar);
		btnGravar.addActionListener(controle.gravar);
	}
}
