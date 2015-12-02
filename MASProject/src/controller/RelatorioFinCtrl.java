package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.IngressoMdl;

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

public class RelatorioFinCtrl implements ActionListener {

	private JFreeChart chart;
	private ChartPanel chartPanel;
	private static JCalendar calendar;
	private static int flag;
	private JTextField txtDataInicio, txtDataFim, txtDespesa, txtGanho;
	private JPanel form;
	private JInternalFrame internalFrameGrafico;

	// Fetch, merge, commit

	private JComboBox<String> cbCategoria, cbSubCategoria;
	private JButton btnGerar, btnSalvarImprimir, btnLimpar;

	private String subCategoria = "";
	private Date dataIni;
	private Date dataFinal;

	private final String[] categorias = { "", "Visitantes", "Acervo" };
	private final String[] subCategoriaVisitantes = { "Todos", "Estudantes", "Comum", "Especial", "Meia" };
	private final String[] subCategoriaAcervo = { "Manutenção", "Transporte", "Aquisição" };

	private ArquivosCtrl arquivos;
	private List<IngressoMdl> ingressos = new ArrayList<IngressoMdl>();

	// Fetch, merge, commit
	public RelatorioFinCtrl(JComboBox<String> cbCategoria, JComboBox<String> cbSubCategoria, JTextField txtDataInicio,
			JTextField txtDataFim, JTextField txtGanho, JTextField txtDespesa, JFreeChart chart, ChartPanel chartPanel,
			JButton btnGerar, JButton btnSalvarImprimir, JInternalFrame internalFrameGrafico, JButton btnLimpar) {

		this.chart = chart;
		this.chartPanel = chartPanel;
		this.txtDataFim = txtDataFim;
		this.txtDataInicio = txtDataInicio;
		this.form = form;
		this.cbCategoria = cbCategoria;
		this.cbSubCategoria = cbSubCategoria;
		this.txtDataInicio = txtDataInicio;
		this.txtDataFim = txtDataFim;
		this.txtGanho = txtGanho;
		this.txtDespesa = txtDespesa;
		this.btnGerar = btnGerar;
		this.btnSalvarImprimir = btnSalvarImprimir;
		this.internalFrameGrafico = internalFrameGrafico;
		this.btnLimpar = btnLimpar;
		carregaComboCategoria();
	}

	public RelatorioFinCtrl(JCalendar calendar) {
		RelatorioFinCtrl.calendar = calendar;

	}

	private void carregaComboCategoria() {
		for (String category : categorias) {
			cbCategoria.addItem(category);
		}
	}

	private void carregaComboSubCategoria() {
		if (cbCategoria.getSelectedItem().toString().contains("Visi")) {
			cbSubCategoria.removeAllItems();
			for (String subCategory : subCategoriaVisitantes) {
				cbSubCategoria.addItem(subCategory);
			}
		} else if (cbCategoria.getSelectedItem().toString().contains("Ace")) {

			for (String subCategory : subCategoriaAcervo) {
				cbSubCategoria.addItem(subCategory);
			}
		} else {
			return;
		}
	}

	public void sessao() {

		SessaoCtrl log = SessaoCtrl.getInstance();

		if (("Operacional").equalsIgnoreCase(log.getLogon().get(0).getNivel())
				|| ("Administrativo").equalsIgnoreCase(log.getLogon().get(0).getNivel())) {

			log.registrar(log.getLogon().get(0).getId(), log.getLogon().get(0).getUsuario(),
					log.getLogon().get(0).getNivel(), form.getName());
		}
		// else {
		// msg("", log.getLogon().get(0).getNivel());
		// }
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
			txtDataInicio.setText(null);
			txtDataInicio.setText(data);
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
		chartPanel.setPreferredSize(new Dimension(internalFrameGrafico.getContentPane().getWidth(),
				internalFrameGrafico.getContentPane().getHeight()));
		internalFrameGrafico.setContentPane(chartPanel);
	}

	private void filtroGrafico() {
		String dataInicio = txtDataInicio.getText();
		String dataFim = txtDataFim.getText();
		String categoria = cbCategoria.getSelectedItem().toString();
		subCategoria = cbSubCategoria.getSelectedItem().toString();
		String titulo = "Finanças " + categoria + " - Periodo: " + dataInicio + " a " + dataFim;
		
		SimpleDateFormat mascara = new SimpleDateFormat("ddMMyyyy");

		try{
			dataIni = (Date) mascara.parse(dataInicio.replace("/", "").replace("/", ""));
			dataFinal = (Date) mascara.parse(dataFim.replace("/", "").replace("/", ""));
		}catch(ParseException e){
			JOptionPane.showMessageDialog(form, "Não foi possível converter a data do arquivo");
		if (categoria.contains("Visi")) {
			lerArquivoIngresso();
			if(ingressos.size() > 0)
				criaGrafico(titulo, ingressos, "Tipo de ingresso", "Valor do ingresso");
			else{
				JOptionPane.showMessageDialog(form, "Não há dados de acordo com o filtro!");
			}
		}
		else{
			if(subCategoria.contains("Manu")){
				
			}
		}
		}
	}


	private void lerArquivoIngresso() { //modificar esse metodo de acordo com a subcategoria
		  arquivos = new ArquivosCtrl();
		  String linha = new String();
		  ArrayList<String> list = new ArrayList<>();
		  try {
		   arquivos.leArquivo("../MASProject/dados/", "ingresso");
		   linha = arquivos.getBuffer();
		   String[] listaIngresso = linha.split(";");
		   if(subCategoria.contains("Tod")){
		    double ganhos = 0.0;
		    for(String s : listaIngresso){
		     String text = s.replaceAll(".*: ", "");
		     list.add(text);
		     if (s.contains("---")) {
		      String data = list.get(1).toString();
		      if(validaData(data)){
		       IngressoMdl ingresso = new IngressoMdl();
		       ingresso.setId(list.get(0));
		       ingresso.setData(list.get(1));
		       ingresso.setHora(null);
		       ingresso.setBilhete(null);
		       ingresso.setExpo(null);
		       ingresso.setVisitaId(null);
		       ingresso.setVisitante(null);
		       ingresso.setIngresso(list.get(7));
		       ingresso.setQtd(null);
		       ingresso.setValor(list.get(9).substring(3));
		       ganhos += Double.parseDouble(list.get(9).substring(3));
		       ingresso.setPagamento(null);
		       ingressos.add(ingresso);
		       list.clear();
		      }
		     }
		    }
		    txtGanho.setText(String.valueOf(ganhos));   
		    //se a subcategoria for de estudante
		   }else if(subCategoria.contains("Est")){
		    double ganhos = 0.0;
		    for(int i = 0; i < listaIngresso.length; i++){
		     if(listaIngresso[i].contains("Est")){
		      String data = listaIngresso[i - 6].toString().substring(12);
		      if(validaData(data)){
		       IngressoMdl ingresso = new IngressoMdl();
		       ingresso.setId(null);
		       ingresso.setData(null);
		       ingresso.setHora(null);
		       ingresso.setBilhete(null);
		       ingresso.setExpo(null);
		       ingresso.setVisitaId(null);
		       ingresso.setVisitante(null);
		       ingresso.setIngresso(listaIngresso[i].substring(11));
		       ingresso.setQtd(null);
		       ingresso.setValor(listaIngresso[i+2].substring(15));
		       ganhos += Double.parseDouble(listaIngresso[i+2].substring(15));
		       ingresso.setPagamento(null);
		       ingressos.add(ingresso);
		       list.clear();
		      }
		     }
		    }
		    txtGanho.setText(String.valueOf(ganhos));
		   }else{
		    
		   }
		  } catch (IOException e) {
		   e.printStackTrace();
		  }

		 }
	
	private boolean validaData(String dataDoArquivo){
		  SimpleDateFormat mascara = new SimpleDateFormat("ddMMyyyy");
		  try {
		   Date dataAtual = mascara.parse(dataDoArquivo.replace("/", "").replace("/", ""));
		   if(dataAtual.after(dataIni) && dataAtual.before(dataFinal)){
		    return true;
		   }
		  } catch (ParseException e) {
		   JOptionPane.showMessageDialog(form, "Não foi possível converter a data do arquivo");
		  }
		  return false;
		 }

	public CategoryDataset criaDataset(List<?> dados) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < dados.size(); i++) {
			dataset.addValue(Double.parseDouble(((IngressoMdl) dados.get(i)).getValor()),
					((IngressoMdl) dados.get(i)).getIngresso(), ((IngressoMdl) dados.get(i)).getIngresso()); // sis[i].getCategoria());
		}

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

	public void limpaCampos() {
		txtDataInicio.setText(null);
		txtDataFim.setText(null);
		chartPanel.setChart(null);

	}

	private void salvaGrafico(/* OutputStream out */) throws IOException {

		if (chart != null) {
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Pasta de Arquivos", "dir");

			String diretorioBase = System.getProperty("user.home") + "/Desktop";
			File dir = new File(diretorioBase);

			JFileChooser choose = new JFileChooser();
			choose.setCurrentDirectory(dir);
			choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			choose.setAcceptAllFileFilterUsed(false);
			choose.addChoosableFileFilter(filtro);
			String caminhoArquivo = "";

			int retorno = choose.showOpenDialog(null);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				caminhoArquivo = choose.getSelectedFile().getAbsolutePath();
				OutputStream out = new FileOutputStream(caminhoArquivo + "/" + "novoGrafico.png");
				ChartUtilities.writeChartAsPNG(out, chart, 500, 350);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\nNão há grafico para gravar.\n\nPor favor, complete todos os campos necessários e clique em Gerar gráfico.",
					"Erro", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/error.png"));
		}
	}

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
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
		}

		@Override
		public void windowClosed(WindowEvent e) {
			leCalendario();
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}
	};

	@Override
	public void actionPerformed(ActionEvent actEvt) {
		Object source = actEvt.getSource();
		if (source == cbCategoria) {
			carregaComboSubCategoria();
		}
		if (source == btnGerar) {
			filtroGrafico();
			internalFrameGrafico.pack();

		}
		if (source == btnLimpar) {
			limpaCampos();
		}

		if (source == btnSalvarImprimir) {
			try {
				salvaGrafico();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			limpaCampos();
		}
	}

}
