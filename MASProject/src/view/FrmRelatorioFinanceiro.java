package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class FrmRelatorioFinanceiro extends JFrame {

	private JPanel contentPane;
	private CategoryDataset dataset;
	//= createDataset();
	private  JFreeChart chart;
	//createChart(dataset);
	private ChartPanel chartPanel = new ChartPanel(chart);
	private JTextField txtDataIni;
	private JTextField txtDataFim;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPerodo = new JLabel("Período:");
		lblPerodo.setBounds(10, 11, 60, 14);
		contentPane.add(lblPerodo);
		
		JLabel lblDataInicial = new JLabel("Data Inicial:");
		lblDataInicial.setBounds(10, 46, 72, 14);
		contentPane.add(lblDataInicial);
		
		txtDataIni = new JTextField();
		txtDataIni.setBounds(92, 43, 86, 20);
		contentPane.add(txtDataIni);
		txtDataIni.setColumns(10);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setBounds(246, 46, 72, 14);
		contentPane.add(lblDataFinal);
		
		txtDataFim = new JTextField();
		txtDataFim.setBounds(328, 43, 86, 20);
		contentPane.add(txtDataFim);
		txtDataFim.setColumns(10);
		
		JButton btnDataIni = new JButton("");
		btnDataIni.setIcon(new ImageIcon(FrmRelatorioFinanceiro.class.getResource("/com/toedter/calendar/images/JDateChooserColor32.gif")));
		btnDataIni.setBounds(188, 42, 29, 23);
		contentPane.add(btnDataIni);
		
		JButton btnDataFim = new JButton("");
		btnDataFim.setIcon(new ImageIcon(FrmRelatorioFinanceiro.class.getResource("/com/toedter/calendar/images/JDateChooserColor32.gif")));
		btnDataFim.setBounds(424, 42, 29, 23);
		contentPane.add(btnDataFim);
		
		JLabel lblSelecioneOQue = new JLabel("Selecione o que você deseja visualizar:");
		lblSelecioneOQue.setBounds(10, 86, 207, 14);
		contentPane.add(lblSelecioneOQue);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 71, 597, 4);
		contentPane.add(separator);
//		chartPanel.setPreferredSize(new Dimension(500, 270));
//		setContentPane(chartPanel);
//		chartPanel.setLayout(null);
	}
}
