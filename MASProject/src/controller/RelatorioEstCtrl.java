package controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class RelatorioEstCtrl {
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JInternalFrame internalFrameGrafico;
	private JComboBox<String> cbFiltro;
	private JTextField txtDataIni, txtDataFim;
	private JButton btnGerar, btnSalvar;

	public RelatorioEstCtrl(JFreeChart chart, ChartPanel chartPanel, JInternalFrame internalFrameGrafico,
			JTextField txtDataIni, JTextField txtDataFim, JComboBox<String> cbFiltro, 
			JButton btnGerar, JButton btnSalvarimprimir) {
		
		this.internalFrameGrafico = internalFrameGrafico;
		this.chart = chart;
		this.chartPanel = chartPanel;
		this.btnGerar= btnGerar;
		this.btnSalvar= btnSalvarimprimir;
		this.txtDataFim = txtDataFim;
		this.txtDataIni= txtDataIni;
		this.cbFiltro= cbFiltro;
	
	}
	
	

}
