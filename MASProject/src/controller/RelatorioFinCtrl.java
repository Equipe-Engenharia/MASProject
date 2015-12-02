package controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.IngressoMdl;

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

import persistence.IngressoFile;

public class RelatorioFinCtrl implements ActionListener {
	
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JComboBox<String> cbCategoria, cbSubCategoria;
	private JButton btnGerar, btnSalvarImprimir;
	private JTextField txtDataInicio, txtDataFim, txtGanho, txtDespesa;
	
	private String subCategoria;
	
	
	private final String[] categorias = {"", "Visitantes", "Acervo"};
	private final String[] subCategoriaVisitantes = {"Todos", "Estudantes", "Comum"};
	private final String[] subCategoriaAcervo = {"Manutenção", "Transporte", "Aquisição"};
	
	private ArquivosCtrl arquivos;
	private List<IngressoMdl> ingressos;
	
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
		for(String category : categorias){
			cbCategoria.addItem(category);
		}
	}
	
	private void carregaComboSubCategoria(){
		if(cbCategoria.getSelectedItem().toString().contains("Visi")){
			cbSubCategoria.removeAllItems();
			for(String subCategory : subCategoriaVisitantes){
				cbSubCategoria.addItem(subCategory);
			}
		}else if(cbCategoria.getSelectedItem().toString().contains("Ace")){
			cbSubCategoria.removeAllItems();
			for(String subCategory : subCategoriaAcervo){
				cbSubCategoria.addItem(subCategory);
			}
		}else{
			return;
		}
	}
	
	public void criaGrafico(String titulo, List<?> dados, String nomeEixoX, String nomeEixoY){
		CategoryDataset dataset = criaDataset(dados);
		chart = criaChart(dataset, titulo, nomeEixoX, nomeEixoY);
		chartPanel = new ChartPanel(chart);
		
	}
	
	private void filtroGrafico(){
		String categoria = cbCategoria.getSelectedItem().toString();
		subCategoria = cbSubCategoria.getSelectedItem().toString();
		String titulo = "Finanças " + categoria + " - Periodo: " + txtDataInicio.getText() + " a " + txtDataFim.getText();
		if(categoria.contains("Visi")){
			lerArquivoIngresso();
			criaGrafico(titulo, ingressos, "Não sei ainda", "Não sei ainda");
		}
		else{
			if(subCategoria.contains("Manu")){
				
			}else if(subCategoria.contains("Exp")){
				
			}else{
				
			}
		}
		
	}
	
	private void lerArquivoIngresso() { //modificar esse metodo de acordo com a subcategoria
		arquivos = new ArquivosCtrl();
		ingressos = new ArrayList<IngressoMdl>();
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "ingressoTipo");
			linha = arquivos.getBuffer();
			String[] listaIngresso = linha.split(";");
			if(subCategoria.contains("Tod")){
				double ganhos = 0.0;
				for(String s : listaIngresso){
					String text = s.replaceAll(".*: ", "");
					list.add(text);
					if (s.contains("---")) {
						IngressoMdl ingresso = new IngressoMdl();
						ingresso.setId(list.get(0));
						ingresso.setData(null);
						ingresso.setHora(null);
						ingresso.setBilhete(null);
						ingresso.setExpo(null);
						ingresso.setVisitaId(null);
						ingresso.setVisitante(null);
						ingresso.setIngresso(list.get(1));
						ingresso.setQtd(null);
						ingresso.setValor(list.get(2));
						ganhos += Double.parseDouble(list.get(2));
						ingresso.setPagamento(null);
						ingressos.add(ingresso);
						list.clear();
					}
				}
				txtGanho.setText(String.valueOf(ganhos));
			}else if(subCategoria.contains("Est")){
				
			}else{
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if(source == btnGerar){
			filtroGrafico();
		}
	}

}
