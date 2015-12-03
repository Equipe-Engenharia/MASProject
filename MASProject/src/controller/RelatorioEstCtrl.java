package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import model.IngressoMdl;
import model.VisitanteMdl;

public class RelatorioEstCtrl implements ActionListener {
	private JPanel form;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private JInternalFrame internalFrameGrafico;
	private final String[] categorias = { "", "Idade", "Sexo", "Ingresso", "Grupos", "Idioma", "Nacionalidade" };
	private JComboBox<String> cbFiltro;
	private JTextField txtDataIni, txtDataFim;
	private JButton btnGerar, btnSalvar, btnLimparCampos;
	private ArquivosCtrl arquivos;
	private String categoria = "";
	private Date dataIni;
	private Date dataFinal;
	private List<VisitanteMdl> visitas = new ArrayList<VisitanteMdl>();
	private List<IngressoMdl> ingressos = new ArrayList<IngressoMdl>();

	public RelatorioEstCtrl(JFreeChart chart, ChartPanel chartPanel, JInternalFrame internalFrameGrafico,
			JTextField txtDataIni, JTextField txtDataFim, JComboBox<String> cbFiltro, JButton btnGerar,
			JButton btnSalvarimprimir, JButton btnLimparCampos) {

		this.internalFrameGrafico = internalFrameGrafico;
		this.chart = chart;
		this.chartPanel = chartPanel;
		this.btnGerar = btnGerar;
		this.btnSalvar = btnSalvarimprimir;
		this.txtDataFim = txtDataFim;
		this.txtDataIni = txtDataIni;
		this.cbFiltro = cbFiltro;
		this.btnLimparCampos = btnLimparCampos;

		carregaComboCategoria();

	}

	// O Metodo de coleta deverá chamar esse método passando um titulo, e um
	// array de objeto carregado
	public void criaGrafico(String titulo, List<?> dados) {
		PieDataset dataset = criaDataset(dados, titulo);
		chart = criaChart(dataset, titulo);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(internalFrameGrafico.getContentPane().getWidth(),
				internalFrameGrafico.getContentPane().getHeight()));
		internalFrameGrafico.setContentPane(chartPanel);
	}

	public PieDataset criaDataset(List<?> dados,String titulo) {
		DefaultPieDataset result = new DefaultPieDataset();
		if (dados.getClass().isInstance(visitas)){
			if(titulo.contains("idade")){
				for (int i = 0; i < dados.size(); i++) {
					//result.setValue(((VisitanteMdl) dados.get(i)).getDataNasc(), value);
				}
			}else if (titulo.contains("genero")){
				for (int i = 0; i < dados.size(); i++) {
					result.setValue(((VisitanteMdl) dados.get(i)).getSexo(),1);
				}
				
			}else if (titulo.contains("idioma")){
				for (int i = 0; i< dados.size(); i++){
					result.setValue(((VisitanteMdl) dados.get(i)).getIdioma(), 1);
				}
			}else if (titulo.contains("nascionalidade")){
				for(int i = 0; i < dados.size();i++){
					result.setValue(((VisitanteMdl)dados.get(i)).getNacionalidade(), 1);
				}
			}
		
		}else if(dados.getClass().isInstance(ingressos)){
			for (int i = 0; i < dados.size(); i++) {
				// TODO
				result.setValue(((IngressoMdl)dados.get(i)).getIngresso(), i);
			}
		}
		return result;

	}

	public JFreeChart criaChart(PieDataset dataset, String titulo) {
		JFreeChart chart = ChartFactory.createPieChart3D(titulo, // titulo do
																	// grafico
				dataset, // dados
				true, // incluir legenda
				true, false);

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

	private boolean filtroGrafico() {
		String dataInicio = txtDataIni.getText();
		String dataFim = txtDataFim.getText();
		String categoria = cbFiltro.getSelectedItem().toString();

		String titulo = "";
		if (!dataInicio.trim().equals("/  /") || !dataFim.trim().equals("/  /")) {
			if (!categoria.equals("")) {
				SimpleDateFormat mascara = new SimpleDateFormat("ddMMyyyy");
				try {
					dataIni = (Date) mascara.parse(dataInicio.replace("/", "").replace("/", ""));
					dataFinal = (Date) mascara.parse(dataFim.replace("/", "").replace("/", ""));
				} catch (ParseException e) {
					 JOptionPane.showMessageDialog(form, e.getMessage());
				}
				if (validaData(dataInicio)) {
					if (categoria.contains("Ida")) {
						lerArquivoVisitante();
						if (visitas.size() > 0) {
							titulo = "Estatística de idades dos visitantes " + categoria + " - Período: " + dataInicio
									+ " a " + dataFim;
							criaGrafico(titulo, visitas);
							return true;
						}else{
							JOptionPane.showMessageDialog(form, "Não há dados de acordo com o filtro");
						}
					} else if (categoria.contains("Sex")) {
						lerArquivoVisitante();
						if (visitas.size() > 0) {
							titulo = "Estatística de gênero dos visitantes " + categoria + " - Período: " + dataInicio
									+ " a " + dataFim;
							criaGrafico(titulo, visitas);
							return true;
						}
					} else if (categoria.contains("Ing")) {
						lerArquivoIngresso();
						if (ingressos.size() > 0){
							titulo = "Estatistica dos tipos de ingresso utilizados" + categoria + " - Período: " + dataInicio
									+ " a " + dataFim;
							criaGrafico(titulo, ingressos);
							return true;
						}

					} else if (categoria.contains("Grup")) {

					} else if (categoria.contains("Idio")) {
						lerArquivoVisitante();
						if (visitas.size() > 0) {
							titulo = "Estatística de idioma dos visitantes " + categoria + " - Período: " + dataInicio
									+ " a " + dataFim;
							criaGrafico(titulo, visitas);
							return true;
						}
					} else if (categoria.contains("Nasc")) {
						lerArquivoVisitante();
						if (visitas.size() > 0) {
							titulo = "Estatística de nascionalidade dos visitantes " + categoria + " - Período: " + dataInicio
									+ " a " + dataFim;
							criaGrafico(titulo, visitas);
							return true;
						}
					}
				} else {
					 JOptionPane.showMessageDialog(form, "Selecione a categoria");
				}
			} else {
				 JOptionPane.showMessageDialog(form, "Insira data de inicio e/ou final para continuar!");
			}
		}
		return false;
	}

	private void lerArquivoVisitante() {
		arquivos = new ArquivosCtrl();
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "visitante");
			linha = arquivos.getBuffer();
			String[] listaVisita = linha.split(";");
			for (String s : listaVisita) {
				String text = s.replace(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					String data = list.get(2).toString();
//					if (validaData(data)) {
						VisitanteMdl visita = new VisitanteMdl();
						visita.setId(list.get(0));
						visita.setNome(list.get(1));
						visita.setDataNasc(list.get(2));
						visita.setNacionalidade(list.get(3));
						visita.setSexo(list.get(4));
						visitas.add(visita);
						list.clear();
//					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private void lerArquivoIngresso() {
		try {
			arquivos.leArquivo("../MASProject/dados/", "ingresso");
			String linha = new String();
			ArrayList<String> list = new ArrayList<>();
			linha = arquivos.getBuffer();
			String[] listaIngresso = linha.split(";");
			for (String s : listaIngresso) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					String data = list.get(1).toString();
					if (validaData(data)) {
						IngressoMdl ingresso = new IngressoMdl();
						ingresso.setId(null);
						ingresso.setData(null);
						ingresso.setHora(null);
						ingresso.setBilhete(null);
						ingresso.setExpo(null);
						ingresso.setVisitaId(null);
						ingresso.setVisitante(null);
						ingresso.setIngresso(list.get(7));
						System.out.println(list.get(7));
						ingresso.setQtd(null);
						ingresso.setValor(null);
						ingresso.setPagamento(null);
						
						
						ingressos.add(ingresso);
						list.clear();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validaData(String dataDoArquivo) {
		SimpleDateFormat mascara = new SimpleDateFormat("ddMMyyyy");
		try {
			Date dataAtual = mascara.parse(dataDoArquivo.replace("/", "").replace("/", ""));
			if (dataIni.before(dataFinal) || dataIni.compareTo(dataFinal) == 0) {
				if (dataAtual.after(dataIni) && dataAtual.before(dataFinal) || dataAtual.compareTo(dataIni) == 0
						|| dataAtual.compareTo(dataFinal) == 0) {
					return true;
				}
			} else {
				 JOptionPane.showMessageDialog(form, "Data inicial é maior do que a data final");
				return false;
			}
		} catch (ParseException e) {
			 JOptionPane.showMessageDialog(form, "Não foi possível converter a data do arquivo");
		}
		return false;
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

		if (source == btnGerar) {
			if(filtroGrafico())
				internalFrameGrafico.pack();
		}
		if (source == btnSalvar) {
			try {
				salvaGrafico();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			limpaCampos();
		}
		if (source == btnLimparCampos) {
			limpaCampos();
		}

	}

}
