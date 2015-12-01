package controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class RelatorioFinCtrl {
	
	private JFreeChart chart;
	private ChartPanel chartPanel;
	
	
	public RelatorioFinCtrl(JFreeChart chart, ChartPanel chartPanel){
		this.chart = chart;
		this.chartPanel = chartPanel;
	}
	
	public void criaGrafico(String titulo, List<?> dados, String nomeEixoX, String nomeEixoY){
		CategoryDataset dataset = criaDataset(dados);
		chart = criaChart(dataset, titulo, nomeEixoX, nomeEixoY);
		chartPanel = new ChartPanel(chart);
		
	}

	public CategoryDataset criaDataset(List<?> dados) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// TODO

		return dataset;
	}

	public JFreeChart criaChart(CategoryDataset dataset, String titulo, String nomeEixoX, String nomeEixoY) {
		JFreeChart chart = ChartFactory.createBarChart(titulo, nomeEixoX , nomeEixoY, dataset,
				PlotOrientation.VERTICAL, // orientacao
				true, // se quiser por legenda
				true, // tooltips?
				false // URLs?
		);

		// CUSTOMIZACOES

		// seleciona o plano de fundo do grafico
		chart.setBackgroundPaint(Color.white);

		// pega uma referencia para customizacoes
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// seleciona o eixo para mostrar apenas inteiros
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// Desativa sublinhas da barra
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// Coloca cores gradientes nas barras
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		// Customizacao termina aqui

		return chart;

	}
	
	private void salvaGrafico() {
		// TODO Auto-generated method stub
		
	}
	
	public ActionListener geraGrafico = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//aqui vem o metodo de fazer coleta
		}
	};
	public ActionListener salvar = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			salvaGrafico();
		}
	};

}
