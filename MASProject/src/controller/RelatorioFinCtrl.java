package controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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

public class RelatorioFinCtrl implements ActionListener {
	
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JComboBox<String> cbCategoria, cbSubCategoria;
	private JButton btnGerar, btnSalvarImprimir;
	private JTextField txtDataInicio, txtDataFim, txtGanho, txtDespesa;
	
	public RelatorioFinCtrl(){}
	
	//Fetch, merge, commit
	public RelatorioFinCtrl(JComboBox<String> cbCategoria, JComboBox<String> cbSubCategoria,
			JButton btnGerar, JButton btnSalvarImprimir, JTextField txtDataInicio, JTextField txtDataFim,
			JTextField txtGanho, JTextField txtDespesa, JFreeChart chart, ChartPanel chartPanel){
		
		this.chart = chart;
		this.chartPanel = chartPanel;
		this.cbCategoria = cbCategoria;
		this.cbSubCategoria = cbSubCategoria;
		this.btnGerar = btnGerar;
		this.btnSalvarImprimir = btnSalvarImprimir;
		this.txtDataInicio = txtDataInicio;
		this.txtDataFim = txtDataFim;
		this.txtGanho = txtGanho;
		this.txtDespesa = txtDespesa;
		carregaComboCategoria();
	}
	
	private void carregaComboCategoria(){
		cbCategoria.addItem("");
		cbCategoria.addItem("Visitante");
		cbCategoria.addItem("Acervo");
	}
	
	private void carregaComboSubCategoria(){
		if(cbCategoria.getSelectedItem().equals("Visitante")){
			cbSubCategoria.removeAllItems();
			cbSubCategoria.addItem("Todos");
			cbSubCategoria.addItem("Estudantes");
			cbSubCategoria.addItem("Comum");
		}else if(cbCategoria.getSelectedItem().equals("Acervo")){
			cbSubCategoria.removeAllItems();
			cbSubCategoria.addItem("Manutenção");
			cbSubCategoria.addItem("Transporte");
			cbSubCategoria.addItem("Exposição");
			cbSubCategoria.addItem("Aquisição");
		}else{
			return;
		}
	}
	
	public void criaGrafico(String titulo, List<?> dados, String nomeEixoX, String nomeEixoY){
		CategoryDataset dataset = criaDataset(dados);
		chart = criaChart(dataset, titulo, nomeEixoX, nomeEixoY);
		chartPanel = new ChartPanel(chart);
		
	}
	
	public void filtroGrafico(){
		String categoria = cbCategoria.getSelectedItem().toString();
		String subCategoria = cbSubCategoria.getSelectedItem().toString();
		
		
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

	@Override
	public void actionPerformed(ActionEvent actEvt) {
		Object source = actEvt.getSource();
		if(source == cbCategoria){
			carregaComboSubCategoria();
		}
	}

}
