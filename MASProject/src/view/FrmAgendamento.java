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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import controller.AgendamentoCtrl;


public class FrmAgendamento extends JFrame {

	static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel 
	lblId, 
	lblData, 
	lblPesquisa, 
	lblVagas, 
	lblTipo, 
	lblPeriodo, 
	lblIngresso, 
	lblInstituto, 
	lblTelefone, 
	lblResponsavel, 
	lblIdResponsavel, 
	lblExpo, 
	lblQtd, 
	lblCusto, 
	lblTotal;
	private JTextField 
	txtId, 
	txtPesquisa, 
	txtNome,  
	txtResponsavel, 
	txtResponsavelId;
	private JFormattedTextField 
	ftxtTelefone, 
	ftxtData, 
	ftxtQtd, 
	ftxtVagas, 
	ftxtCusto, 
	ftxtTotal;
	private JComboBox<String> cbExpo, cbTipo, cbPeriodo, cbIngresso;
	private JTable tbAgenda;
	private JButton btnPesquisar, btnCal, btnIncluir, btnApagar, btnCancelar, btnGravar;
	private JScrollPane spTabela;
	private MaskFormatter maskFone, maskData;
	private DecimalFormat maskQtd, maskCusto, maskTotal;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAgendamento frame = new FrmAgendamento();
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
	
	public FrmAgendamento() throws ParseException{
		setTitle("Agendamento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 480);
		contentPane = new JPanel();
		contentPane.setName("AGD");
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
		
		lblInstituto = new JLabel("Instituição");
		lblInstituto.setBounds(59, 92, 67, 16);
		contentPane.add(lblInstituto);
		
		lblQtd = new JLabel("Qtd. Pessoas");
		lblQtd.setBounds(310, 129, 88, 16);
		contentPane.add(lblQtd);
		
		lblData = new JLabel("Data");
		lblData.setBounds(541, 236, 43, 16);
		contentPane.add(lblData);
		
		lblVagas = new JLabel("Vagas Disponíveis");
		lblVagas.setBounds(506, 21, 129, 16);
		contentPane.add(lblVagas);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(539, 92, 43, 16);
		contentPane.add(lblTipo);
		
		lblPeriodo = new JLabel("Período");
		lblPeriodo.setBounds(527, 129, 55, 16);
		contentPane.add(lblPeriodo);
		
		lblIngresso = new JLabel("Ingresso");
		lblIngresso.setBounds(527, 164, 55, 16);
		contentPane.add(lblIngresso);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(71, 129, 55, 16);
		contentPane.add(lblTelefone);
		
		lblResponsavel = new JLabel("Responsável");
		lblResponsavel.setBounds(48, 166, 78, 16);
		contentPane.add(lblResponsavel);
		
		lblIdResponsavel = new JLabel("Autorização");
		lblIdResponsavel.setBounds(48, 201, 78, 16);
		contentPane.add(lblIdResponsavel);
		
		lblExpo = new JLabel("Exposição");
		lblExpo.setBounds(58, 236, 68, 16);
		contentPane.add(lblExpo);
		
		lblCusto = new JLabel("Valor Unitário");
		lblCusto.setBounds(310, 166, 88, 16);
		contentPane.add(lblCusto);
		
		lblTotal = new JLabel("Valor Total");
		lblTotal.setBounds(310, 201, 88, 16);
		contentPane.add(lblTotal);
		
		// CAMPOS ////////////////////////		
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(138, 15, 150, 28);
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setColumns(10);
		contentPane.add(txtId);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setToolTipText("Digite o nome ou ID da instituição para realizar a pesquisa");
		txtPesquisa.setBounds(138, 50, 310, 28);
		txtPesquisa.setColumns(10);
		contentPane.add(txtPesquisa);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.LEFT);
		txtNome.setEnabled(false);
		txtNome.setBounds(138, 86, 356, 28);
		txtNome.setColumns(10);
		contentPane.add(txtNome);
			
		maskFone = new MaskFormatter("(##) #####-####");
		ftxtTelefone = new JFormattedTextField(maskFone);;
		ftxtTelefone.setEnabled(false);
		ftxtTelefone.setBounds(138, 123, 160, 28);
		ftxtTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		ftxtTelefone.setColumns(10);
		contentPane.add(ftxtTelefone);
		
		txtResponsavel = new JTextField();
		txtResponsavel.setHorizontalAlignment(SwingConstants.LEFT);
		txtResponsavel.setEnabled(false);
		txtResponsavel.setBounds(138, 160, 160, 28);
		txtResponsavel.setColumns(10);
		contentPane.add(txtResponsavel);
		
		txtResponsavelId = new JTextField();
		txtResponsavelId.setHorizontalAlignment(SwingConstants.LEFT);
		txtResponsavelId.setEnabled(false);
		txtResponsavelId.setBounds(138, 195, 160, 28);
		txtResponsavelId.setColumns(10);
		contentPane.add(txtResponsavelId);
		
		maskData = new MaskFormatter("##/##/####");
		ftxtData = new JFormattedTextField(maskData);
		ftxtData.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtData.setBounds(586, 230, 98, 28);
		ftxtData.setColumns(10);
		contentPane.add(ftxtData);
		
		maskQtd = new DecimalFormat("#,###,###");
		NumberFormatter qtd = new NumberFormatter(maskQtd);
		qtd.setFormat(maskQtd);
		qtd.setAllowsInvalid(false);
		ftxtQtd = new JFormattedTextField(maskQtd);
		ftxtQtd.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtQtd.setToolTipText("");
		ftxtQtd.setBounds(395, 123, 99, 28);
		ftxtQtd.setColumns(10);
		contentPane.add(ftxtQtd);
		
		maskQtd = new DecimalFormat("#,###,###");
		NumberFormatter vaga = new NumberFormatter(maskQtd);
		vaga.setFormat(maskQtd);
		vaga.setAllowsInvalid(false);
		ftxtVagas = new JFormattedTextField(maskQtd);
		ftxtVagas.setEnabled(false);
		ftxtVagas.setHorizontalAlignment(SwingConstants.CENTER);
		ftxtVagas.setToolTipText("");
		ftxtVagas.setBounds(631, 15, 99, 28);
		ftxtVagas.setColumns(10);
		contentPane.add(ftxtVagas);
		
		maskCusto = new DecimalFormat("#,###,###.##");
		NumberFormatter custo = new NumberFormatter(maskCusto);
		custo.setFormat(maskCusto);
		custo.setAllowsInvalid(false);
		ftxtCusto = new JFormattedTextField(maskCusto);
		ftxtCusto.setEnabled(false);
		ftxtCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtCusto.setBounds(395, 160, 99, 28);
		ftxtCusto.setColumns(10);
		contentPane.add(ftxtCusto);
		
		maskTotal = new DecimalFormat("R$ #,###,##0.00");
		NumberFormatter total = new NumberFormatter(maskTotal);
		total.setFormat(maskTotal);
		total.setAllowsInvalid(false);
		ftxtTotal = new JFormattedTextField(maskTotal);
		ftxtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ftxtTotal.setEnabled(false);
		ftxtTotal.setBounds(395, 195, 100, 28);
		ftxtTotal.setColumns(10);
		contentPane.add(ftxtTotal);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setBounds(584, 86, 146, 28);
		contentPane.add(cbTipo);
		
		cbPeriodo = new JComboBox<String>();
		cbPeriodo.setBounds(584, 123, 146, 28);
		contentPane.add(cbPeriodo);
		
		cbIngresso = new JComboBox<String>();
		cbIngresso.setBounds(584, 160, 146, 28);
		contentPane.add(cbIngresso);
		
		cbExpo = new JComboBox<String>();
		cbExpo.setBounds(138, 230, 356, 28);
		contentPane.add(cbExpo);
		
		spTabela = new JScrollPane();
		spTabela.setBounds(38, 272, 691, 122);
		contentPane.add(spTabela);
		
		tbAgenda = new JTable();
		tbAgenda.setToolTipText("Clique para selecionar…");
		spTabela.setViewportView(tbAgenda);
		tbAgenda.setBorder(null);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setBounds(460, 47, 34, 34);
		btnPesquisar.setIcon(new ImageIcon("../MASProject/icons/search.png"));
		contentPane.add(btnPesquisar);
		
		btnCal = new JButton("");
		btnCal.setBounds(696, 232, 27, 25);
		btnCal.setIcon(new ImageIcon("../MASProject/jcalendar-1.4 (1)/src/com/toedter/calendar/images/JDateChooserColor32.gif"));
		contentPane.add(btnCal);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("../MASProject/icons/add.png"));
		btnIncluir.setBounds(38, 406, 109, 34);
		contentPane.add(btnIncluir);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnApagar.setBounds(154, 406, 109, 34);
		contentPane.add(btnApagar);
		
		btnCancelar = new JButton("Limpar");
		btnCancelar.setIcon(new ImageIcon("../MASProject/icons/delete.png"));
		btnCancelar.setBounds(504, 406, 109, 34);
		contentPane.add(btnCancelar);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		btnGravar.setBounds(620, 406, 109, 34);
		contentPane.add(btnGravar);
		
		AgendamentoCtrl controle = new AgendamentoCtrl(
				contentPane, 
				txtId, 
				txtPesquisa, 
				txtNome, 
				ftxtTelefone, 
				txtResponsavel, 
				txtResponsavelId, 
				ftxtVagas, 
				ftxtData, 
				ftxtQtd, 
				ftxtCusto, 
				ftxtTotal, 
				cbExpo, 
				cbTipo, 
				cbPeriodo, 
				cbIngresso, 
				tbAgenda
				);
		
		txtPesquisa.addActionListener(controle.pesquisar);
		txtPesquisa.addKeyListener(controle.tecla);
		ftxtData.addFocusListener(controle.move);
		ftxtQtd.addActionListener(controle.valor);
		//ftxtCusto.addKeyListener(controle.tecla);
		//ftxtCusto.addActionListener(controle.incluir);
		cbIngresso.addActionListener(controle.valor);
		tbAgenda.addMouseListener(controle.limpaCampo);
		tbAgenda.addKeyListener(controle.tecla);
		btnPesquisar.addActionListener(controle.pesquisar);
		btnCal.addActionListener(controle.abreCal);
		btnCancelar.addActionListener(controle.cancelar);
		btnIncluir.addActionListener(controle.incluir);
		btnApagar.addActionListener(controle.apagar);
		btnGravar.addActionListener(controle.gravar);
	}
}
