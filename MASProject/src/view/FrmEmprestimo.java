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

import controller.EmprestimoCtrl;

public class FrmEmprestimo extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel 
	lblId, 
	lblDataInicial, 
	lblDataFinal, 
	lblPesquisa, 
	lblObra, 
	lblArtista, 
	lblMuseu, 
	lblTelefone, 
	lblResponsavel, 
	lblIdResponsavel, 
	lblCusto, 
	lblTotal;
	private JTextField 
	txtId, 
	txtPesquisa, 
	txtObra, 
	txtArtista,  
	txtTelefone, 
	txtResponsavel, 
	txtIdResponsavel;
	private JFormattedTextField 
	ftxtDataInicial, 
	ftxtDataFinal, 
	ftxtCusto, 
	ftxtTotal;
	private JComboBox<String> cbMuseu;
	private JTable tbEmprestimo;
	private JRadioButton rdbtnSaida, rdbtnEntrada;
	private JButton btnPesquisar, btnCalInicial, btnCalFinal, btnIncluir, btnApagar, btnCancelar, btnGravar;
	private JScrollPane spEmprestimo;
	private final ButtonGroup btgDestino = new ButtonGroup();
	private MaskFormatter maskData;
	private DecimalFormat maskCusto, maskTotal;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEmprestimo frame = new FrmEmprestimo();
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
	
	public FrmEmprestimo() throws ParseException{
		setTitle("Empréstimo de Obras");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		contentPane = new JPanel();
		contentPane.setName("EPT");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
			
		// LABEL ////////////////////////	
		
		lblId = new JLabel("ID");
		lblId.setBounds(112, 15, 14, 28);
		contentPane.add(lblId);
		
		lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setBounds(71, 56, 55, 16);
		contentPane.add(lblPesquisa);
		
		lblObra = new JLabel("Título da Obra");
		lblObra.setBounds(36, 91, 90, 16);
		contentPane.add(lblObra);
		
		lblArtista = new JLabel("Artista");
		lblArtista.setBounds(83, 126, 43, 16);
		contentPane.add(lblArtista);
		
		JLabel lblDestino = new JLabel("Tipo de Destino");
		lblDestino.setBounds(529, 19, 100, 16);
		contentPane.add(lblDestino);
		
		lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(504, 91, 78, 16);
		contentPane.add(lblDataInicial);
		
		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(514, 126, 68, 16);
		contentPane.add(lblDataFinal);
		
		lblMuseu = new JLabel("Museu");
		lblMuseu.setBounds(83, 310, 43, 16);
		contentPane.add(lblMuseu);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(71, 345, 55, 16);
		contentPane.add(lblTelefone);
		
		lblResponsavel = new JLabel("Responsável");
		lblResponsavel.setBounds(48, 380, 78, 16);
		contentPane.add(lblResponsavel);
		
		lblIdResponsavel = new JLabel("Autorização");
		lblIdResponsavel.setBounds(48, 415, 78, 16);
		contentPane.add(lblIdResponsavel);
		
		lblCusto = new JLabel("Custo");
		lblCusto.setBounds(310, 310, 43, 16);
		contentPane.add(lblCusto);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(321, 345, 32, 16);
		contentPane.add(lblTotal);
		
		// CAMPOS ////////////////////////		
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(138, 15, 150, 28);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setToolTipText("Digite o nome do visitante ou seu ID para realizar a pesquisa");
		txtPesquisa.setBounds(138, 50, 294, 28);
		txtPesquisa.setColumns(10);
		contentPane.add(txtPesquisa);
		
		txtObra = new JTextField();
		txtObra.setHorizontalAlignment(SwingConstants.CENTER);
		txtObra.setEnabled(false);
		txtObra.setEditable(false);
		txtObra.setBounds(138, 85, 294, 28);
		txtObra.setColumns(10);
		contentPane.add(txtObra);
		
		txtArtista = new JTextField();
		txtArtista.setHorizontalAlignment(SwingConstants.CENTER);
		txtArtista.setEnabled(false);
		txtArtista.setEditable(false);
		txtArtista.setToolTipText("");
		txtArtista.setBounds(138, 120, 294, 28);
		txtArtista.setColumns(10);
		contentPane.add(txtArtista);
			
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setEditable(false);
		txtTelefone.setToolTipText("");
		txtTelefone.setBounds(138, 339, 160, 28);
		txtTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefone.setColumns(10);
		contentPane.add(txtTelefone);
		
		txtResponsavel = new JTextField();
		txtResponsavel.setHorizontalAlignment(SwingConstants.CENTER);
		txtResponsavel.setEnabled(false);
		txtResponsavel.setEditable(false);
		txtResponsavel.setBounds(138, 374, 160, 28);
		txtResponsavel.setColumns(10);
		contentPane.add(txtResponsavel);
		
		txtIdResponsavel = new JTextField();
		txtIdResponsavel.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdResponsavel.setEnabled(false);
		txtIdResponsavel.setEditable(false);
		txtIdResponsavel.setBounds(138, 409, 160, 28);
		txtIdResponsavel.setColumns(10);
		contentPane.add(txtIdResponsavel);
		
		maskData = new MaskFormatter("##/##/####");
		ftxtDataInicial = new JFormattedTextField(maskData);
		ftxtDataInicial.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDataInicial.setBounds(584, 85, 98, 28);
		ftxtDataInicial.setColumns(10);
		contentPane.add(ftxtDataInicial);
		
		maskData = new MaskFormatter("##/##/####");
		ftxtDataFinal = new JFormattedTextField(maskData);
		ftxtDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtDataFinal.setBounds(584, 120, 98, 28);
		ftxtDataFinal.setColumns(10);
		contentPane.add(ftxtDataFinal);
		
		maskCusto = new DecimalFormat("#,###,###.##");
		NumberFormatter custo = new NumberFormatter(maskCusto);
		custo.setFormat(maskCusto);
		custo.setAllowsInvalid(false);
		ftxtCusto = new JFormattedTextField(maskCusto);
		ftxtCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtCusto.setBounds(361, 304, 123, 28);
		ftxtCusto.setColumns(10);
		contentPane.add(ftxtCusto);
		
		maskTotal = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter total = new NumberFormatter(maskTotal);
		total.setFormat(maskTotal);
		total.setAllowsInvalid(false);
		ftxtTotal = new JFormattedTextField(maskTotal);
		ftxtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtTotal.setEnabled(false);
		ftxtTotal.setEditable(false);
		ftxtTotal.setBounds(361, 339, 123, 28);
		ftxtTotal.setColumns(10);
		contentPane.add(ftxtTotal);
		
		cbMuseu = new JComboBox<String>();
		cbMuseu.setBounds(138, 304, 160, 28);
		contentPane.add(cbMuseu);
		
		
		rdbtnSaida = new JRadioButton("Saída");
		rdbtnSaida.setSelected(true);
		btgDestino.add(rdbtnSaida);
		rdbtnSaida.setActionCommand("saida");
		rdbtnSaida.setBounds(643, 15, 86, 23);
		contentPane.add(rdbtnSaida);
		
		rdbtnEntrada = new JRadioButton("Entrada");
		btgDestino.add(rdbtnEntrada);
		rdbtnEntrada.setActionCommand("entrada");
		rdbtnEntrada.setBounds(643, 46, 86, 23);
		contentPane.add(rdbtnEntrada);
		
		spEmprestimo = new JScrollPane();
		spEmprestimo.setBounds(38, 165, 691, 122);
		contentPane.add(spEmprestimo);
		
		tbEmprestimo = new JTable();
		tbEmprestimo.setToolTipText("Clique para selecionar…");
		spEmprestimo.setViewportView(tbEmprestimo);
		tbEmprestimo.setBorder(null);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setBounds(441, 47, 34, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);
		
		btnCalInicial = new JButton("");
		btnCalInicial.setBounds(694, 88, 29, 23);
		btnCalInicial.setIcon(new ImageIcon("../MASProject/jcalendar-1.4 (1)/src/com/toedter/calendar/images/JDateChooserColor32.gif"));
		contentPane.add(btnCalInicial);
		
		btnCalFinal = new JButton("");
		btnCalFinal.setBounds(694, 123, 29, 23);
		btnCalFinal.setIcon(new ImageIcon("../MASProject/jcalendar-1.4 (1)/src/com/toedter/calendar/images/JDateChooserColor32.gif"));
		contentPane.add(btnCalFinal);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnIncluir.setBounds(504, 301, 109, 34);
		contentPane.add(btnIncluir);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnApagar.setBounds(620, 301, 109, 34);
		contentPane.add(btnApagar);
		
		btnCancelar = new JButton("Limpar");
		btnCancelar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnCancelar.setBounds(504, 406, 109, 34);
		contentPane.add(btnCancelar);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		btnGravar.setBounds(620, 406, 109, 34);
		contentPane.add(btnGravar);
		
		
		EmprestimoCtrl controle = new EmprestimoCtrl(
				contentPane, 
				txtId, 
				txtPesquisa, 
				txtObra, 
				txtArtista, 
				rdbtnEntrada, 
				rdbtnSaida, 
				btgDestino, 
				ftxtDataInicial, 
				ftxtDataFinal, 
				tbEmprestimo, 
				cbMuseu, 
				txtTelefone, 
				txtResponsavel, 
				txtIdResponsavel, 
				ftxtCusto, 
				ftxtTotal
				);
		
		txtPesquisa.addActionListener(controle.pesquisar);
		txtPesquisa.addKeyListener(controle.tecla);
		ftxtDataInicial.addFocusListener(controle.move);
		ftxtDataFinal.addFocusListener(controle.move);
		ftxtCusto.addActionListener(controle.valor);
		ftxtCusto.addKeyListener(controle.tecla);
		ftxtCusto.addActionListener(controle.incluir);
		cbMuseu.addActionListener(controle.atualizar);
		tbEmprestimo.addMouseListener(controle.limpaCampo);
		tbEmprestimo.addKeyListener(controle.tecla);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnCalInicial.addActionListener(controle.abreCalInicial);
		btnCalFinal.addActionListener(controle.abreCalFinal);
		btnCancelar.addActionListener(controle.cancelar);
		btnIncluir.addActionListener(controle.incluir);
		btnApagar.addActionListener(controle.apagar);
		btnGravar.addActionListener(controle.gravar);
		
		
	}
}
