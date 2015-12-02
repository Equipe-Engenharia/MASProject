package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class RelatorioEstCtrl {
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JInternalFrame internalFrameGrafico;
	private final String[] categorias = {"", "Todos", "Idade", "Sexo", "Ingresso", "Grupos", "Idioma", "Nacionalidade"}; 

	public RelatorioEstCtrl(JFreeChart chart, ChartPanel chartPanel, JInternalFrame internalFrameGrafico) {
		this.internalFrameGrafico = internalFrameGrafico;
		this.chart = chart;
		this.chartPanel = chartPanel;
	}
	
	//O Metodo de coleta deverá chamar esse método passando um titulo, e um array de objeto carregado
	public void criaGrafico(String titulo, List<?> dados) {
		PieDataset dataset = criaDataset(dados);
		chart = criaChart(dataset, titulo);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(internalFrameGrafico.getContentPane().getWidth(),
				internalFrameGrafico.getContentPane().getHeight()));
		internalFrameGrafico.setContentPane(chartPanel);
	}
	
	public PieDataset criaDataset(List<?>dados){
		DefaultPieDataset result = new DefaultPieDataset();
		for(int i = 0; i < dados.size();i++){
			//TODO
		}
		return result;
		
	}
	
	public JFreeChart criaChart(PieDataset dataset, String titulo){
		JFreeChart chart = ChartFactory.createPieChart3D(titulo,          // titulo do grafico
	            dataset,                // dados
	            true,                   // incluir legenda
	            true,
	            false);

	        PiePlot3D plot = (PiePlot3D) chart.getPlot();
	        plot.setStartAngle(290);
	        plot.setDirection(Rotation.CLOCKWISE);
	        plot.setForegroundAlpha(0.5f);
	        return chart;
	}
	
	private void carregaComboCategoria() {
		for (String category : categorias) {
			//cbCategoria.addItem(category);
		}
	}
	
	public void actionPerformed(ActionEvent actEvt) {
		Object source = actEvt.getSource();
		//TODO
	}
	

}
