package controller;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class RelatorioEstCtrl {
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JInternalFrame internalFrameGrafico;

	public RelatorioEstCtrl(JFreeChart chart, ChartPanel chartPanel, JInternalFrame internalFrameGrafico) {
		this.internalFrameGrafico = internalFrameGrafico;
		this.chart = chart;
		this.chartPanel = chartPanel;
	}
	
	

}
