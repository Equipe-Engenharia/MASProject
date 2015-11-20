package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.EmprestimoCtrl;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class FrmEmp extends JFrame {
	private JTextField CodEmp;
	private JTextField textField_1;
	private JTextField IdObra;
	private JTextField artista;
	private JTextField Titulo;
	private JTable table;
	//private JTextField textField_5;
	private JTextField Museu;
	private JTextField Tel;
	private JTextField RespTec;
	private JTextField numero;
	private MaskFormatter maskData;
	private JFormattedTextField ftxtDataDev;
	private JFormattedTextField ftxtDataEmp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEmp frame = new FrmEmp();
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
	EmprestimoCtrl emprest= new EmprestimoCtrl();
	public FrmEmp() {
		setType(Type.UTILITY);
		setTitle("Empr\u00E9stimo");
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setBackground(new Color(211, 211, 211));
		getContentPane().setLayout(null);
		
		JLabel lblCdigoDoEmprstimo = new JLabel("C\u00F3digo Do Empr\u00E9stimo");
		lblCdigoDoEmprstimo.setBounds(275, 35, 112, 14);
		getContentPane().add(lblCdigoDoEmprstimo);
		
		CodEmp = new JTextField();
		CodEmp.setBounds(397, 29, 283, 20);
		getContentPane().add(CodEmp);
		CodEmp.setColumns(10);
		
		JLabel lblDataDoEmprstimo = new JLabel("Data Do Empr\u00E9stimo");
		lblDataDoEmprstimo.setBounds(768, 32, 102, 14);
		getContentPane().add(lblDataDoEmprstimo);
		
		
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ftxtDataEmp = new JFormattedTextField(maskData);
		ftxtDataEmp.setBounds(880, 29, 69, 20);
		getContentPane().add(ftxtDataEmp);
		ftxtDataEmp.setColumns(10);
		ftxtDataEmp.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel lblIdObra = new JLabel("ID. Obra");
		lblIdObra.setBounds(435, 69, 46, 14);
		getContentPane().add(lblIdObra);
		
		IdObra = new JTextField();
		IdObra.setBounds(491, 66, 170, 20);
		getContentPane().add(IdObra);
		IdObra.setColumns(10);
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.setBounds(695, 65, 80, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblArtista = new JLabel("Artista");
		lblArtista.setBounds(314, 106, 46, 14);
		getContentPane().add(lblArtista);
		
		artista = new JTextField();
		artista.setBounds(370, 103, 291, 20);
		getContentPane().add(artista);
		artista.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(705, 109, 46, 14);
		getContentPane().add(lblTtulo);
		
		Titulo = new JTextField();
		Titulo.setBounds(758, 103, 211, 20);
		getContentPane().add(Titulo);
		Titulo.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon("..MASProject\\icons\\ok.png"));
		btnIncluir.setBounds(594, 150, 86, 31);
		getContentPane().add(btnIncluir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(73, 150, 1, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(389, 150, 1, 2);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 198, 1264, 2);
		getContentPane().add(separator_2);
		
		//DefaultTableModel model = new DefaultTableModel();
		table = new JTable();
		table.setCellSelectionEnabled(true);
	
		table.setColumnSelectionAllowed(true);
		table.setBounds(224, 223, 871, 219);
		//model.addColumn("fgfgfg");
		getContentPane().add(table);
		
		JLabel lblDataDeDevoluo = new JLabel("Data de Devolu\u00E7\u00E3o");
		lblDataDeDevoluo.setBounds(594, 453, 112, 14);
		getContentPane().add(lblDataDeDevoluo);
		
	    try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ftxtDataDev = new JFormattedTextField(maskData);
		ftxtDataDev.setBounds(689, 450, 76, 20);
		getContentPane().add(ftxtDataDev);
		ftxtDataDev.setColumns(10);
		ftxtDataDev.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 493, 1264, 2);
		getContentPane().add(separator_3);
		
		JLabel lblMuseu = new JLabel("Museu");
		lblMuseu.setBounds(338, 506, 46, 14);
		getContentPane().add(lblMuseu);
		
		Museu = new JTextField();
		Museu.setBounds(370, 503, 308, 20);
		getContentPane().add(Museu);
		Museu.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(794, 506, 46, 14);
		getContentPane().add(lblTelefone);
		
		Tel = new JTextField();
		Tel.setBounds(850, 503, 99, 20);
		getContentPane().add(Tel);
		Tel.setColumns(10);
		
		JLabel lblResponsvelTcnico = new JLabel("Respons\u00E1vel T\u00E9cnico");
		lblResponsvelTcnico.setBounds(338, 537, 112, 14);
		getContentPane().add(lblResponsvelTcnico);
		
		RespTec = new JTextField();
		RespTec.setBounds(469, 534, 296, 20);
		getContentPane().add(RespTec);
		RespTec.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(338, 563, 67, 14);
		getContentPane().add(lblDocumento);
		
		JComboBox comboBoxDoc = new JComboBox();
		comboBoxDoc.setBounds(415, 562, 123, 20);
		getContentPane().add(comboBoxDoc);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(611, 563, 46, 14);
		getContentPane().add(lblNmero);
		
		numero = new JTextField();
		numero.setBounds(653, 560, 112, 20);
		getContentPane().add(numero);
		numero.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Gravar");
		btnNewButton_1.setIcon(new ImageIcon("..MASProject\\icons\\save.png"));
		btnNewButton_1.setBounds(224, 687, 89, 45);
		getContentPane().add(btnNewButton_1);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("..MASProject\\icons\\delete.png"));
		btnExcluir.setBounds(611, 687, 89, 45);
		getContentPane().add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon("..MASProject\\icons\\6169_16x16.png"));
		btnEditar.setBounds(1006, 687, 89, 45);
		getContentPane().add(btnEditar);
		
		JButton pesqObra = new JButton("");
		pesqObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emprest.preencherComboBoxObra(artista);
			}
		});
		pesqObra.setIcon(new ImageIcon("..MASProject\\icons\\search.png"));
		pesqObra.setBounds(660, 65, 20, 21);
		getContentPane().add(pesqObra);
		
		JButton pesqArt = new JButton("");
		pesqArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emprest.preencherComboBoxArtista();
				
			}
		});
		pesqArt.setIcon(new ImageIcon("..MASProject\\icons\\search.png"));
		pesqArt.setBounds(660, 102, 20, 21);
		getContentPane().add(pesqArt);
		
		JButton adicionarTel = new JButton("");
		adicionarTel.setIcon(new ImageIcon("..MASProject\\icons\\add.png"));
		adicionarTel.setBounds(949, 502, 20, 21);
		getContentPane().add(adicionarTel);
		setBounds(100, 100, 1280, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public boolean getTableCellSelectionEnabled() {
		return table.getCellSelectionEnabled();
	}
	public void setTableCellSelectionEnabled(boolean cellSelectionEnabled) {
		table.setCellSelectionEnabled(cellSelectionEnabled);
	}
}

