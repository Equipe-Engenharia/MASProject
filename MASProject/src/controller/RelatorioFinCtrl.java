package controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JCalendar;

import view.FrmCalendario;

public class RelatorioFinCtrl {

	private JFreeChart chart;
	private ChartPanel chartPanel;
	private static JCalendar calendar;
	private static int flag;
	private JTextField txtDataIni, txtDataFim;
	private JPanel form;

	public RelatorioFinCtrl(JFreeChart chart, ChartPanel chartPanel, JTextField txtDataIni, JTextField txtDataFim, JPanel form) {
		this.chart = chart;
		this.chartPanel = chartPanel;
		this.txtDataFim = txtDataFim;
		this.txtDataIni = txtDataIni;
		this.form = form;
	}

	public RelatorioFinCtrl(JCalendar calendar) {
		RelatorioFinCtrl.calendar = calendar;
	}
	
	public void sessao() {

		SessaoCtrl log = SessaoCtrl.getInstance();

		if (("Operacional").equalsIgnoreCase(log.getLogon().get(0).getNivel()) ||
				("Administrativo").equalsIgnoreCase(log.getLogon().get(0).getNivel())
				){

			log.registrar(
					log.getLogon().get(0).getId(), 
					log.getLogon().get(0).getUsuario(), 
					log.getLogon().get(0).getNivel(),
					form.getName());
		} 
//			else {
//			msg("", log.getLogon().get(0).getNivel());
//		}
	}

	public static int getFlag() {

		return flag;
	}

	public void setFlag(int n) {

		flag = n;
	}

	public void leCalendario() {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		// LÊ DATA SELECIONADA PELO USUARIO
		String data = formato.format(calendar.getDate());
		insereDataTextField(data);
	}

	public void insereDataTextField(String data) {

		// TRATAMENTO DAS FLAGS
		switch (getFlag()) {

		case 1:
			txtDataIni.setText(null);
			txtDataIni.setText(data);
			break;
		case 2:
			txtDataFim.setText(null);
			txtDataFim.setText(data);
			break;
		}
	}

	public void chamaCalendario() throws ParseException {
		FrmCalendario frmCal = new FrmCalendario();
		frmCal.setLocationRelativeTo(null);
		frmCal.setVisible(true);
	}

	public void criaGrafico(String titulo, List<?> dados, String nomeEixoX, String nomeEixoY) {
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
		JFreeChart chart = ChartFactory.createBarChart(titulo, nomeEixoX, nomeEixoY, dataset, PlotOrientation.VERTICAL, // orientacao
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

	private void salvaGrafico(/* OutputStream out */) throws IOException {
		OutputStream out = new FileOutputStream("nomeDoGraficoAqui.png");
		if (chart != null) {
			ChartUtilities.writeChartAsPNG(out, chart, 500, 350);
		} else {
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\nNão há grafico para gravar.\n\nPor favor, complete todos os campos necessários e clique em Gerar gráfico.",
					"Erro", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/error.png"));
		}
	}

	public ActionListener geraGrafico = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// aqui vem o metodo de fazer coleta
		}
	};
	public ActionListener salvar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				salvaGrafico();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	public ActionListener abreCalendarioIni = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				chamaCalendario();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			setFlag(1);
		}

	};

	public ActionListener abreCalendarioFim = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				chamaCalendario();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			setFlag(2);
		}

	};
	
	// ESTE LISTNER TRATA A BUSCA DA DATA SELECIONADA AO FECHAR A TELA
	public WindowListener fechaTela = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowDeactivated(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {}

		@Override
		public void windowClosed(WindowEvent e) {
			leCalendario();
		}

		@Override
		public void windowActivated(WindowEvent e) {}
	};

}
