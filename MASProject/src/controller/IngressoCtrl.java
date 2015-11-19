package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.ExposicaoMdl;
import model.IngressoMdl;
import model.IngressoTipoMdl;
import persistence.IngressoFile;

public class IngressoCtrl implements ComponentListener {

	private JPanel form;
	private JTextField txtId, txtData, txtHora, txtBilhete, txtPesquisa, txtQtd;
	private JFormattedTextField ftxtValorUnit, ftxtValor; 
	private JComboBox<String> cbExpo, cbIngresso;
	private JTable tbCompra;
	private List<IngressoMdl> ingressos;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivos = new ArquivosCtrl();
	private IngressoFile arquivo = new IngressoFile();

	public IngressoCtrl(JPanel form, JTextField txtId, JTextField txtData, JTextField txtHora, 
			JTextField txtBilhete, JTextField txtPesquisa, JComboBox<String> cbExpo, JComboBox<String> cbIngresso, 
			JTable tbCompra, JTextField txtQtd, JFormattedTextField ftxtValorUnit, JFormattedTextField ftxtValor) {

		this.form = form;
		this.txtId = txtId;
		this.txtData = txtData;
		this.txtHora = txtHora;
		this.txtBilhete= txtBilhete;
		this.txtPesquisa = txtPesquisa;
		this.txtQtd = txtQtd;
		this.ftxtValorUnit = ftxtValorUnit;
		this.ftxtValor = ftxtValor;
		this.cbExpo = cbExpo;
		this.cbIngresso = cbIngresso;
		this.tbCompra = tbCompra;
		this.ingressos = new ArrayList<IngressoMdl>();

		lerArquivo();
	}

	// METODOS DE SUPORTE ////////////////////////

	public void gerarId() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("VND" + NewId);
	}

	
	public void limpaCampos() {
		
		//txtId.setText(null);
		txtPesquisa.setText(null);
		cbExpo.setSelectedIndex(0);
		cbIngresso.setSelectedIndex(0);
		txtQtd.setText(null);
		ftxtValorUnit.setText(null);
		ftxtValor.setText(null);
	}
	
	
	public void preencheData(){

		txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		txtHora.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
	}
	
	
	public void preencheBilhete() {

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
		
		String opt = cbIngresso.getSelectedItem().toString();
		
		switch (opt){
		
		case "Inteira":
			ftxtValorUnit.setValue(20);
			break;
		case "Meia":
			ftxtValorUnit.setValue(10);
			break;
		case "Estudante":
			ftxtValorUnit.setValue(10);
			break;
		case "Franca":
			ftxtValorUnit.setValue(0);
			break;
		}
		
		if(!txtQtd.getText().isEmpty()){	
			int qtd = Integer.parseInt(txtQtd.getText());
			Object unit = ftxtValorUnit.getValue();
			int total = qtd * (int) unit;
			ftxtValor.setValue(total);
		}
	}
	

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Setor.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.", 
					"Não Localizado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' salvo com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nNão foi possível apagar o registro: " + txtId.getText() + " "
					+ txtPesquisa.getText() + "!\nVerifique sua digitação!", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' editado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroredit":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' já existe!",
					"Já Cadastrado", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "deleteconfirm":
			Object[] options = { "Confirmar", "Cancelar" };  
			int r = JOptionPane.showOptionDialog(null, "Você confirma a exclusão do registro '" + mensagem + "'?",
					"Exclusão de Registro", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), options, options[0]);
			if (r == 0) {
				validar = false;
			}
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' excluído com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errordelete":
			JOptionPane.showMessageDialog(null, 
					"Registro '" + mensagem + "' não pode ser alterado para a exclusão.",
					"Erro", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null, 
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nVolte ao trabalho duro e conserte isso!!!", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}


	// PREENCHE COMBOBOX /////////////////////

	
	public void preencherComboBoxExpo() {
		
		String linha = new String();
		arquivos = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<ExposicaoMdl> listExpo = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "exposicao");
			linha = arquivos.getBuffer();
			String[] expo = linha.split(";");
			for (String s : expo) {
				String text = s.replaceAll(".*: ", "");
				listString.add(text);
				if (s.contains("---")) {
					ExposicaoMdl e = new ExposicaoMdl();
					e.setNome(listString.get(1));
					listExpo.add(e);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (ExposicaoMdl c : listExpo) {
			cbExpo.addItem(c.getNome());
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
					ingresso.setValor(list.get(7));
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

		ArrayList<IngressoMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtPesquisa.getText().isEmpty() || !txtId.getText().isEmpty()) {

			for (int i = 0; i < ingressos.size(); i++) {
				if (txtPesquisa.getText().equalsIgnoreCase(ingressos.get(i).getId())) {
					txtId.setText(ingressos.get(i).getId());
					txtPesquisa.setText(ingressos.get(i).getVisitante());
//					cbCategoria.getModel().setSelectedItem(ingressos.get(i).getCategoria());
					validar = true;
				} else if (ingressos.get(i).getVisitante().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase())) {
					validar = true;
//					cbCategoria.getModel().setSelectedItem(ingressos.get(i).getCategoria());
				}
			}
			if (validar == true) {
				for (int i = 0; i < ingressos.size(); i++) {
					boolean filtro = ingressos.get(i).getVisitante().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase());
					if (filtro == true) {
						IngressoMdl item = new IngressoMdl();
						item.setId(ingressos.get(i).getId());
						item.setBilhete(ingressos.get(i).getBilhete());
						item.setData(ingressos.get(i).getData());
						item.setHora(ingressos.get(i).getHora());
						item.setVisitante(ingressos.get(i).getVisitante());
						item.setValor(ingressos.get(i).getValor());	
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
				for (int i = 0; i < ingressos.size(); i++) {
					if (pesquisa.equalsIgnoreCase(ingressos.get(i).getId())) {
						txtId.setText(ingressos.get(i).getId());
						txtPesquisa.setText(ingressos.get(i).getVisitante());
//						cbCategoria.getModel().setSelectedItem(ingressos.get(i).getCategoria());
					}
				}
				}
				validar = false;
			} else {
				if (pesquisa == "") {
					msg("nosearch", txtPesquisa.getText());
					limpaCampos();
				}
				validar = false;
			}
		} else {
			msg("errorsearch", txtPesquisa.getText());
		}
	}
	

	public void editar() {
		
		IngressoMdl ingresso = new IngressoMdl();
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < ingressos.size(); i++) {
				if (!txtId.getText().equalsIgnoreCase(ingressos.get(i).getId()) 
						&& txtPesquisa.getText().equalsIgnoreCase(ingressos.get(i).getVisitante())
						&& txtBilhete.getText().equalsIgnoreCase(ingressos.get(i).getBilhete())) {
					msg("erroredit", ingressos.get(i).getVisitante());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < ingressos.size(); i++) {
					if (txtId.getText().equalsIgnoreCase(ingressos.get(i).getId())) {
						ingresso.setId(txtId.getText());
						ingresso.setVisitante(txtPesquisa.getText());
//						ingresso.setCategoria((String) cbCategoria.getSelectedItem());
						ingressos.set(i, ingresso);
						atualizaDados(ingressos);
						msg("edit", txtPesquisa.getText());
						limpaCampos();	
					}
				}
			}
		} else {
			msg("errorsearch", txtPesquisa.getText());
		}
	}

	
	public void excluir() {
		
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < ingressos.size(); i++) {
				if (txtId.getText().equalsIgnoreCase(ingressos.get(i).getId()) 
						&& txtPesquisa.getText().equalsIgnoreCase(ingressos.get(i).getVisitante())
//						&& cbCategoria.getSelectedItem().toString().equalsIgnoreCase(ingressos.get(i).getCategoria())
						) {
					ingressos.remove(i);
					validar = true;
				} else {
					if(i == ingressos.size()){
						msg("errordelete", ingressos.get(i).getVisitante());
					}
				}
			}
			if (validar == true) {
				msg("deleteconfirm", txtPesquisa.getText());
				if (validar == false){
					atualizaDados(ingressos);
					msg("delete", txtPesquisa.getText());
					limpaCampos();
				} else {
					ingressos.clear();
					lerArquivo();	
				}
			} else {
				validar = false;
				msg("errordelete", txtId.getText());
			}
		} else {
			pesquisar();
		}
	}
	

	public void gravar() {
		
		new IngressoFile();
		IngressoMdl ingresso = new IngressoMdl();
		validar = false;
		if (!txtPesquisa.getText().isEmpty()) {
			for (int i = 0; i < ingressos.size(); i++) {	
				if (txtPesquisa.getText().equalsIgnoreCase(ingressos.get(i).getVisitante()) 
						&& cbIngresso.getSelectedItem().toString().equalsIgnoreCase(ingressos.get(i).getIngresso())
						) {
					msg("erroredit", ingressos.get(i).getVisitante());
					validar = true;
				}
			}
			if(!(validar == true)){
				ingresso.setId(txtId.getText());
				ingresso.setData(txtData.getText());
				ingresso.setHora(txtHora.getText());
				ingresso.setBilhete(txtBilhete.getText().replace("0",""));
				ingresso.setExpo(cbExpo.getSelectedItem().toString());
				ingresso.setVisitante(txtPesquisa.getText());
				ingresso.setIngresso(cbIngresso.getSelectedItem().toString());
				ingresso.setValor(ftxtValor.getText().replace("R$ ","").replace(",00","")); 
				ingressos.add(ingresso);
				msg("save", txtPesquisa.getText());
				atualizaDados(ingressos);
				limpaCampos();
				gerarId();
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

	public ActionListener excluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
				excluir();
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			editar();
		}
	};
	
	public ActionListener valor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			calculaValor();
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
				txtPesquisa.setText(null);
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