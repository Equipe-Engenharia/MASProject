package view;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import controller.RelatorioEstCtrl;

import java.awt.Color;

public class FrmRelatorioEstatistico extends JFrame {

	private JPanel contentPane;
	private JTextField txtDataIni;
	private JTextField txtDataFim;
	private JLabel lblDataInicial, lblDataFinal, lblPerodo, lblFiltrarPor;
	private JSeparator separator, separator_1, separator_2;
	private JButton btnGerar, btnSalvarimprimir, btnLimparCampos;
	private JComboBox<String> cbFiltro;
	private JInternalFrame internalFrameGrafico;
	private PieDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private MaskFormatter maskData;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatorioEstatistico frame = new FrmRelatorioEstatistico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public FrmRelatorioEstatistico() throws ParseException {
		setTitle("Relatorio Estatístico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chartPanel = new ChartPanel(chart);

		lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(54, 44, 62, 14);
		contentPane.add(lblDataInicial);
		
		maskData = new MaskFormatter("##/##/####");

		txtDataIni = new JFormattedTextField(maskData);
		txtDataIni.setBounds(126, 41, 86, 20);
		contentPane.add(txtDataIni);
		txtDataIni.setColumns(10);

		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(267, 47, 62, 14);
		contentPane.add(lblDataFinal);

		txtDataFim = new JFormattedTextField(maskData);
		txtDataFim.setBounds(340, 44, 86, 20);
		contentPane.add(txtDataFim);
		txtDataFim.setColumns(10);

		lblPerodo = new JLabel("Período:");
		lblPerodo.setBounds(26, 16, 46, 14);
		contentPane.add(lblPerodo);

		separator = new JSeparator();
		separator.setBounds(10, 78, 655, 2);
		contentPane.add(separator);

		lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setBounds(26, 91, 72, 14);
		contentPane.add(lblFiltrarPor);

		cbFiltro = new JComboBox();
		cbFiltro.setBounds(108, 91, 104, 20);
		contentPane.add(cbFiltro);

		internalFrameGrafico = new JInternalFrame("Gráfico");
		internalFrameGrafico.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		internalFrameGrafico.setFrameIcon(null);
		internalFrameGrafico.setBounds(124, 154, 434, 330);
		internalFrameGrafico.setContentPane(chartPanel);
		contentPane.add(internalFrameGrafico);

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 136, 655, 2);
		contentPane.add(separator_1);

		btnGerar = new JButton("Gerar Gráfico");
		btnGerar.setIcon(new ImageIcon("../MASProject/icons/ok.png"));
		btnGerar.setBounds(500, 94, 147, 31);
		contentPane.add(btnGerar);

		separator_2 = new JSeparator();
		separator_2.setBounds(10, 495, 655, 2);
		contentPane.add(separator_2);

		btnSalvarimprimir = new JButton("Salvar/Imprimir");
		btnSalvarimprimir.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnSalvarimprimir.setBounds(500, 508, 147, 31);
		contentPane.add(btnSalvarimprimir);
		internalFrameGrafico.setVisible(true);
		
		
		
		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setIcon(new ImageIcon("../MASProject/icons/clear.png"));
		btnLimparCampos.setBounds(324, 508, 153, 31);
		contentPane.add(btnLimparCampos);
		
		RelatorioEstCtrl rEst = new RelatorioEstCtrl(chart, chartPanel, internalFrameGrafico, txtDataIni, txtDataFim,
				cbFiltro, btnGerar, btnSalvarimprimir, btnLimparCampos);
		
		btnGerar.addActionListener(rEst);
		btnSalvarimprimir.addActionListener(rEst);
		btnLimparCampos.addActionListener(rEst);
	}
	

}
