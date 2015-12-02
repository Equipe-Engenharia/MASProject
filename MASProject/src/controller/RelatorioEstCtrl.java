package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class RelatorioEstCtrl implements ActionListener{
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JInternalFrame internalFrameGrafico;
	private final String[] categorias = {"", "Todos", "Idade", "Sexo", "Ingresso", "Grupos", "Idioma", "Nacionalidade"}; 
	private JComboBox<String> cbFiltro;
	private JTextField txtDataIni, txtDataFim;
	private JButton btnGerar, btnSalvar, btnLimparCampos;

	public RelatorioEstCtrl(JFreeChart chart, ChartPanel chartPanel, JInternalFrame internalFrameGrafico,
			JTextField txtDataIni, JTextField txtDataFim, JComboBox<String> cbFiltro, 
			JButton btnGerar, JButton btnSalvarimprimir, JButton btnLimparCampos) {
		
		this.internalFrameGrafico = internalFrameGrafico;
		this.chart = chart;
		this.chartPanel = chartPanel;
		this.btnGerar= btnGerar;
		this.btnSalvar= btnSalvarimprimir;
		this.txtDataFim = txtDataFim;
		this.txtDataIni= txtDataIni;
		this.cbFiltro= cbFiltro;
		this.btnLimparCampos = btnLimparCampos;
		
		carregaComboCategoria();
	
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
			cbFiltro.addItem(category);
		}
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
	
	public void limpaCampos() {
		txtDataIni.setText(null);
		txtDataFim.setText(null);
		chartPanel.setChart(null);

	}
	
	public void actionPerformed(ActionEvent actEvt) {
		Object source = actEvt.getSource();
		
		if (source == btnGerar){
			//TODO
		}
		if(source == btnSalvar){
			try {
				salvaGrafico();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			limpaCampos();
		}
		if(source == btnLimparCampos){
			limpaCampos();
		}
		
	}
	

}
