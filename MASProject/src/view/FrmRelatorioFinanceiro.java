package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import controller.RelatorioFinCtrl;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class FrmRelatorioFinanceiro extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	private JInternalFrame internalFrameGrafico;
	private CategoryDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel = new ChartPanel(chart);
	private JTextField txtDataIni;
	private JTextField txtDataFim;
	private JTextField txtGanho;
	private JTextField textField;
	private JLabel lblPeriodo, lblDataInicial, lblDataFinal, lblSelecioneOQue, lblCategoria, lblSubcategoria,
			lblInformaesGerais, lblGanhor, lblDespesas;
	private JButton btnSalvarimprimir, btnDataIni, btnDataFim;
	private JSeparator separator, separator_1, separator_2;
	// arrumar argumento
	private JComboBox<Object> cbCategoria, cbSubCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatorioFinanceiro frame = new FrmRelatorioFinanceiro();
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
	public FrmRelatorioFinanceiro() {
		setTitle("Relatório Financeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 546);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblPeriodo = new JLabel("Período:");
		lblPeriodo.setBounds(41, 12, 60, 14);
		contentPane.add(lblPeriodo);

		lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(41, 47, 72, 14);
		contentPane.add(lblDataInicial);

		txtDataIni = new JTextField();
		txtDataIni.setBounds(123, 44, 86, 20);
		contentPane.add(txtDataIni);
		txtDataIni.setColumns(10);

		lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(381, 47, 72, 14);
		contentPane.add(lblDataFinal);

		txtDataFim = new JTextField();
		txtDataFim.setBounds(463, 44, 86, 20);
		contentPane.add(txtDataFim);
		txtDataFim.setColumns(10);

		btnDataIni = new JButton("");
		btnDataIni.setIcon(new ImageIcon(
				FrmRelatorioFinanceiro.class.getResource("/com/toedter/calendar/images/JDateChooserColor32.gif")));
		btnDataIni.setBounds(219, 43, 29, 23);
		contentPane.add(btnDataIni);

		btnDataFim = new JButton("");
		btnDataFim.setIcon(new ImageIcon(
				FrmRelatorioFinanceiro.class.getResource("/com/toedter/calendar/images/JDateChooserColor32.gif")));
		btnDataFim.setBounds(559, 43, 29, 23);
		contentPane.add(btnDataFim);

		lblSelecioneOQue = new JLabel("Selecione o que você deseja visualizar:");
		lblSelecioneOQue.setBounds(41, 86, 207, 14);
		contentPane.add(lblSelecioneOQue);

		separator = new JSeparator();
		separator.setBounds(0, 71, 773, 4);
		contentPane.add(separator);

		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(41, 119, 60, 14);
		contentPane.add(lblCategoria);

		// arrumar argumentos das combos
		cbCategoria = new JComboBox<Object>();
		cbCategoria.setBounds(123, 116, 125, 20);
		contentPane.add(cbCategoria);

		lblSubcategoria = new JLabel("Sub-Categoria");
		lblSubcategoria.setBounds(356, 116, 86, 14);
		contentPane.add(lblSubcategoria);

		// Arrumar argumentos das combos
		cbSubCategoria = new JComboBox<Object>();
		cbSubCategoria.setBounds(463, 113, 125, 20);
		contentPane.add(cbSubCategoria);

		separator_1 = new JSeparator();
		separator_1.setBounds(0, 155, 773, 4);
		contentPane.add(separator_1);

		internalFrameGrafico = new JInternalFrame("Gráfico");
		internalFrameGrafico.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		internalFrameGrafico.setFrameIcon(null);
		internalFrameGrafico.setBounds(10, 170, 438, 267);
		contentPane.add(internalFrameGrafico);
		internalFrameGrafico.setVisible(true);

		// chartPanel.setPreferredSize(new Dimension(500, 270));
		internalFrameGrafico.setContentPane(chartPanel);
		// setContentPane(chartPanel);
		chartPanel.setLayout(null);

		lblInformaesGerais = new JLabel("Informações Gerais:");
		lblInformaesGerais.setBounds(477, 171, 130, 14);
		contentPane.add(lblInformaesGerais);

		lblGanhor = new JLabel("Ganho(R$)");
		lblGanhor.setBounds(531, 209, 72, 14);
		contentPane.add(lblGanhor);

		txtGanho = new JTextField();
		txtGanho.setBounds(613, 206, 86, 20);
		contentPane.add(txtGanho);
		txtGanho.setColumns(10);

		lblDespesas = new JLabel("Despesas(R$)");
		lblDespesas.setBounds(531, 250, 72, 14);
		contentPane.add(lblDespesas);

		textField = new JTextField();
		textField.setBounds(613, 247, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSalvarimprimir = new JButton("Salvar/Imprimir");
		btnSalvarimprimir.setIcon(new ImageIcon("../MASProject/icons/save.png"));
		btnSalvarimprimir.setBounds(608, 468, 155, 29);
		contentPane.add(btnSalvarimprimir);

		separator_2 = new JSeparator();
		separator_2.setBounds(0, 453, 773, 4);
		contentPane.add(separator_2);

		RelatorioFinCtrl relaFinCtrl = new RelatorioFinCtrl();
		dataset = relaFinCtrl.criaDataset();
		chart = relaFinCtrl.criaChart(dataset);

	}
}
