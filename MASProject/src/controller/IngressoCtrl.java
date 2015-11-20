package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.ExposicaoMdl;
import model.IngressoMdl;
import model.IngressoTipoMdl;
import model.VisitanteMdl;
import persistence.IngressoFile;
import view.FrmLoginCad;
import view.FrmVisitanteCad;

public class IngressoCtrl implements ComponentListener {

	private JPanel form;
	private JLabel lblDinheiro, lblTroco;
	private JTextField txtId, txtData, txtHora, txtBilhete, txtPesquisa, txtQtd;
	private JFormattedTextField ftxtValorUnit, ftxtValor, ftxtDinheiro, ftxtTroco; 
	private JComboBox<String> cbExpo, cbIngresso;
	private JRadioButton rdbtnDinheiro;
	private ButtonGroup pagamento;
	private JTable tbCompra;
	private List<IngressoMdl> ingressos;
	private List<IngressoTipoMdl> tipo;
	private List<VisitanteMdl> visitantes;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivos = new ArquivosCtrl();
	private IngressoFile arquivo = new IngressoFile();

	public IngressoCtrl(JPanel form, JLabel lblDinheiro, JLabel lblTroco, JTextField txtId, JTextField txtData, JTextField txtHora, 
			JTextField txtBilhete, JTextField txtPesquisa, JComboBox<String> cbExpo, JComboBox<String> cbIngresso, 
			JTable tbCompra, JTextField txtQtd, JFormattedTextField ftxtValorUnit, JFormattedTextField ftxtValor, 
			JFormattedTextField ftxtDinheiro, JFormattedTextField ftxtTroco, JRadioButton rdbtnDinheiro, ButtonGroup pagamento) {

		this.form = form;
		this.lblDinheiro = lblDinheiro;
		this.lblTroco = lblTroco;
		this.txtId = txtId;
		this.txtData = txtData;
		this.txtHora = txtHora;
		this.txtBilhete= txtBilhete;
		this.txtPesquisa = txtPesquisa;
		this.txtQtd = txtQtd;
		this.ftxtValorUnit = ftxtValorUnit;
		this.ftxtValor = ftxtValor;
		this.ftxtDinheiro = ftxtDinheiro;
		this.ftxtTroco = ftxtTroco;
		this.cbExpo = cbExpo;
		this.cbIngresso = cbIngresso;
		this.rdbtnDinheiro = rdbtnDinheiro;
		this.pagamento = pagamento;
		this.tbCompra = tbCompra;
		this.ingressos = new ArrayList<IngressoMdl>();
		this.tipo = new ArrayList<IngressoTipoMdl>();
		this.visitantes = new ArrayList<VisitanteMdl>();

		lerArquivo();
		lerVisitante();
	}

	// METODOS DE SUPORTE ////////////////////////

	public void gerarId() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("VND" + NewId);
	}

	
	public void limpaCampos() {
		
		txtPesquisa.setText(null);
		cbExpo.setSelectedIndex(0);
		cbIngresso.setSelectedIndex(0);
		txtQtd.setText("1");
	}
	
	
public void lerVisitante() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();	
		try {
			arquivos.leArquivo("../MASProject/dados/", "visitante");
			linha = arquivos.getBuffer();
			String[] listaVisitante = linha.split(";");
			for (String s : listaVisitante) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					VisitanteMdl visitante = new VisitanteMdl();
					visitante.setId(list.get(0));
					visitante.setNome(list.get(1));
					visitante.setDataNasc(list.get(2));
					visitante.setNacionalidade(list.get(3));
					visitante.setSexo(list.get(4));
					visitante.setIdioma(list.get(5));
					visitantes.add(visitante);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void preencheData(){

		txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		txtHora.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}
	
	
	public void preencheBilhete() {
		
		txtBilhete.setText(String.format("%09d",1));
		ArrayList<String> bilhete = new ArrayList<>();
		File arquivo = new File("../MASProject/dados/", "ingresso");
		if (arquivo.exists()) {
			String linha = new String();
			try {
				arquivos.leArquivo("../MASProject/dados/", "ingresso");
				linha = arquivos.getBuffer();
				String[] listaIngresso = linha.split(";");
				for (String s : listaIngresso) {
					String text = s.replaceAll(".*: ", "");
					bilhete.add(text);
					if (s.contains("---")) {
						int num = Integer.parseInt(bilhete.get(3).toString()) + 1;
						txtBilhete.setText(String.format("%09d", num));
						bilhete.clear();
					} else {
					txtBilhete.setText(String.format("%09d",1));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			txtBilhete.setText(String.format("%09d", 1));
		}
	}
	
	
	public void calculaValor(){

		String linha = new String();
		arquivos = new ArquivosCtrl();		
		ArrayList<String> listString = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "ingressoTipo");
			linha = arquivos.getBuffer();
			String[] list = linha.split(";");
			for (String s : list) {
				String text = s.replaceAll(".*: ", "");
				listString.add(text);
				if (s.contains("---")) {
					IngressoTipoMdl item = new IngressoTipoMdl();
					item.setId(listString.get(0));
					item.setTipo(listString.get(1));
					item.setValor(listString.get(2));
					tipo.add(item);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < tipo.size(); i++) {
			if (cbIngresso.getSelectedItem().toString().equals(tipo.get(i).getTipo())) {
				ftxtValorUnit.setValue(Integer.parseInt(tipo.get(i).getValor()));
			}
		}
		if(!txtQtd.getText().isEmpty()){	
			int qtd = Integer.parseInt(txtQtd.getText());
			Object unit = ftxtValorUnit.getValue();
			int total = qtd * (int) unit;
			ftxtValor.setValue(total);
		} else {
				txtQtd.setText("1");
				calculaValor();
		}
	}
	
	
	public void calculaTroco(){

		if(!ftxtDinheiro.getText().isEmpty()){
			int vlr =  Integer.parseInt(ftxtValor.getValue().toString());
			int dinheiro =  Integer.parseInt(ftxtDinheiro.getValue().toString());
			int troco = dinheiro -  vlr;
			ftxtTroco.setValue(troco);
		}
	}
	
	
	public void pagamento(){
		
		if(rdbtnDinheiro.isSelected()){
			lblDinheiro.setVisible(true);
			lblTroco.setVisible(true);
			ftxtDinheiro.setVisible(true);
			ftxtTroco.setVisible(true);
		} else {
			lblDinheiro.setVisible(false);
			lblTroco.setVisible(false);
			ftxtDinheiro.setVisible(false);
			ftxtTroco.setVisible(false);
		}
	}
	

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\n\nPor favor, complete todos os campos necessários.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/error.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Exposição '" + mensagem + "'\n\nCompra realizada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/confirm.png"));
			break;
		case "errorsave":
			Object[] options = { "Confirmar", "Cancelar" };  
			int r = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nO visitante '" + mensagem 
					+ "' não existe na base de dados!\n\nDeseja realizar o cadastro do visitante?",
					"Não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), options, options[0]);
			if (r == 0) {
				try {
					FrmVisitanteCad frmCad = new FrmVisitanteCad();
					frmCad.setVisible(true);
					frmCad.setLocationRelativeTo(null);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			break;		
		default:
			JOptionPane.showMessageDialog(null, 
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nVolte ao trabalho duro e conserte isso!!!", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
		}
	}


	// PREENCHE COMBOBOX /////////////////////

	
	public void preencherComboBoxExpo() {
		
		String linha = new String();
		arquivos = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<ExposicaoMdl> listExpo = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "exposicoes");
			linha = arquivos.getBuffer();
			String[] expo = linha.split(";");
			for (String s : expo) {
				String text = s.replaceAll(".*: ", "");
				listString.add(text);
				if (s.contains("---")) {
					ExposicaoMdl e = new ExposicaoMdl();
					e.setTitulo(listString.get(1));
					listExpo.add(e);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (ExposicaoMdl c : listExpo) {
		cbExpo.addItem(c.getTitulo());
		}
	}
	
	
	public void preencherComboBoxIngresso() {

		String linha = new String();
		arquivos = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "ingressoTipo");
			linha = arquivos.getBuffer();
			String[] ingresso = linha.split(";");
			for (String s : ingresso) {
				String text = s.replaceAll(".*: ", "");
				listString.add(text);
				if (s.contains("---")) {
				cbIngresso.addItem(listString.get(1));
				listString.clear();
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	// CRUD //////////////////////////

	
	public void lerArquivo() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "ingresso");
			linha = arquivos.getBuffer();
			String[] listaIngresso = linha.split(";");
			for (String s : listaIngresso) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					IngressoMdl ingresso = new IngressoMdl();
					ingresso.setId(list.get(0));
					ingresso.setData(list.get(1));
					ingresso.setHora(list.get(2));
					ingresso.setBilhete(list.get(3));
					ingresso.setExpo(list.get(4));
					ingresso.setVisitante(list.get(5));
					ingresso.setIngresso(list.get(6));
					ingresso.setPagamento(list.get(7));
					ingresso.setValor(list.get(8));
					ingressos.add(ingresso);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void atualizaDados(List<IngressoMdl> listingressos) {
		
		File f = new File("../MASProject/dados/ingresso");
		f.delete();
		for (IngressoMdl ingresso : listingressos) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "ingresso", "", ingresso);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void pesquisar() {
		
		visitantes.clear();
		lerVisitante();
		ArrayList<VisitanteMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtPesquisa.getText().isEmpty() || !txtId.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (txtPesquisa.getText().equalsIgnoreCase(visitantes.get(i).getId())) {
					txtId.setText(visitantes.get(i).getId());
					txtPesquisa.setText(visitantes.get(i).getNome());
					validar = true;
				} else if (visitantes.get(i).getNome().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase())) {
					validar = true;
				}
			}
			if (validar == true) {
				for (int i = 0; i < visitantes.size(); i++) {
					boolean filtro = visitantes.get(i).getNome().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase());
					if (filtro == true) {
						VisitanteMdl item = new VisitanteMdl();
						item.setId(visitantes.get(i).getId());
						item.setNome(visitantes.get(i).getNome());
						lista.add(item);
					}
				}
				String[] filtro = new String[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					filtro[i] = lista.get(i).getId();
					pesquisa = lista.get(i).getId();					
				}
				if (filtro != null && filtro.length > 1) {
					pesquisa = (String) JOptionPane.showInputDialog(form, "Escolha o ID:\n", "Selecione o ID",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				} 
				if (pesquisa == "0" || pesquisa != null){
					for (int i = 0; i < visitantes.size(); i++) {
						if (pesquisa.equalsIgnoreCase(visitantes.get(i).getId())) {
							txtPesquisa.setText(visitantes.get(i).getNome());
						}
					}
				}
				validar = false;
			} else {
				if (pesquisa == "") {
					msg("errorsave", txtPesquisa.getText());
					limpaCampos();
				}
				validar = false;
			}
		} else {
			msg("errorsearch", txtPesquisa.getText());
		}
	}
	

	public void gravar() {

		new IngressoFile();
		IngressoMdl ingresso = new IngressoMdl();
		if (!txtPesquisa.getText().isEmpty() && !txtQtd.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (txtPesquisa.getText().equals(visitantes.get(i).getNome())) {
					ingresso.setId(txtId.getText());
					ingresso.setData(txtData.getText());
					ingresso.setHora(txtHora.getText());
					ingresso.setBilhete(txtBilhete.getText().replace("0",""));
					ingresso.setExpo(cbExpo.getSelectedItem().toString());
					ingresso.setVisitante(txtPesquisa.getText());
					ingresso.setIngresso(cbIngresso.getSelectedItem().toString());
					ingresso.setPagamento(pagamento.getSelection().getActionCommand());
					ingresso.setValor(ftxtValor.getText().replace("R$ ","").replace(",00","")); 
					ingressos.add(ingresso);
					msg("save", cbExpo.getSelectedItem().toString());
					atualizaDados(ingressos);
					limpaCampos();
					gerarId();
					preencheBilhete();
					validar = true;
				} 
			}
			if (validar == false) {
				msg("errorsave", txtPesquisa.getText());
			}
		} else {
			msg("errornull", txtPesquisa.getText());
		}
	}
	

	// CONTROLE BOTAO //////////////////////////////
	

	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			pesquisar();
		}
	};
	
	public ActionListener valor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			calculaValor();
			calculaTroco();
			pagamento();
		}
	};
	
	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			limpaCampos();
		}
	};

	public ActionListener gravar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			gravar();
		}
	};
	
	
	// CONTROLE TECLA ///////////////////////////////
	
	
	public KeyListener tecla = new KeyListener() {  
		
		@Override  
		public void keyTyped(KeyEvent e) {
			
			String caracteres="0987654321";
			if(!caracteres.contains(e.getKeyChar()+"")){
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {	 
		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			calculaValor();
		}
	};
	

	// CONTROLE FOCO ///////////////////////////////
	
	public FocusListener move = new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			calculaValor();
		}
		
	};
 	

	// CONTROLE MOUSE ///////////////////////////////

	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (contador == 1) {
				limpaCampos();
				contador += 1;
			}
		}
	};
	

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}