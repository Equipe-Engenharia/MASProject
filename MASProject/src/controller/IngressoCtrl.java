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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.ExposicaoMdl;
import model.IngressoMdl;
import model.IngressoTipoMdl;
import model.VisitanteMdl;
import persistence.IngressoFile;
import view.FrmVisitanteCad;

public class IngressoCtrl implements ComponentListener {

	private JPanel form;
	private JLabel lblDinheiro, lblTroco;
	private JTextField txtId, txtData, txtHora, txtBilhete, txtPesquisa, txtQtd;
	private JFormattedTextField ftxtValorUnit, ftxtSubtotal, ftxtTotal, ftxtDinheiro, ftxtTroco; 
	private JComboBox<String> cbExpo, cbIngresso;
	private JRadioButton rdbtnDinheiro;
	private ButtonGroup pagamento;
	private JTable tbCompra;
	private String altera;
	private boolean validar;
	private List<IngressoMdl> ingressos;
	private List<IngressoMdl> compra;
	private List<IngressoTipoMdl> tipo;
	private List<VisitanteMdl> visitantes;
	
	private ArquivosCtrl arquivos = new ArquivosCtrl();
	private IngressoFile arquivo = new IngressoFile();

	public IngressoCtrl(JPanel form, JLabel lblDinheiro, JLabel lblTroco, JTextField txtId, JTextField txtData, JTextField txtHora, 
			JTextField txtBilhete, JTextField txtPesquisa, JComboBox<String> cbExpo, JComboBox<String> cbIngresso, 
			JTable tbCompra, JTextField txtQtd, JFormattedTextField ftxtValorUnit, JFormattedTextField ftxtSubtotal, JFormattedTextField ftxtTotal, 
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
		this.ftxtSubtotal = ftxtSubtotal;
		this.ftxtTotal = ftxtTotal;
		this.ftxtDinheiro = ftxtDinheiro;
		this.ftxtTroco = ftxtTroco;
		this.cbExpo = cbExpo;
		this.cbIngresso = cbIngresso;
		this.rdbtnDinheiro = rdbtnDinheiro;
		this.pagamento = pagamento;
		this.tbCompra = tbCompra;
		this.ingressos = new ArrayList<IngressoMdl>();
		this.compra = new ArrayList<IngressoMdl>();
		this.tipo = new ArrayList<IngressoTipoMdl>();
		this.visitantes = new ArrayList<VisitanteMdl>();

		lerArquivo();
		lerVisitante();
		formataTabela();
	}


	// METODOS DE SUPORTE ////////////////////////


	public void atualizaId() {

		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("PDV" + NewId);
	}


	public void limpaCampos() {

		txtPesquisa.setText(null);
		cbExpo.setSelectedIndex(0);
		cbIngresso.setSelectedIndex(0);
		txtQtd.setText("1");
		ftxtDinheiro.setValue(null);
		ftxtTroco.setValue(null);
	}


	public void lerVisitante() {

		String linha = new String();
		ArrayList<String> list = new ArrayList<>();	
		try {
			//RECUPERA E FILTRA OS DADOS DO ARQUIVO TXT
			arquivos.leArquivo("../MASProject/dados/", "visitante");
			linha = arquivos.getBuffer();
			String[] listaVisitante = linha.split(";");
			for (String s : listaVisitante) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					//PREENCHE O ARRAY
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


	public void atualizaTempo(){  

		txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		txtHora.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()))); 
	}  

	
	public void atualizaBilhete() {

		//VALOR INICIAL CASO O ARQUIVO TXT ESTEJA VAZIO
		txtBilhete.setText(String.format("%09d",1));
		
		//VERIFICA SE A TABELA ESTÁ CARREGADA
		if(tbCompra.getRowCount() > 0){
			//VERIFICA SE HOUVE EXCLUSÃO NA TABELA E RETORNA O BILHETE ANTERIOR À EXCLUSÃO
			if(altera != null){
				txtBilhete.setText(altera);
				altera = null;
			} else {
				//ATUALIZA O BILHETE A PARTIR DA TABELA
				for (int i = 0; i < compra.size(); i++) {
					int num = Integer.parseInt(compra.get(i).getBilhete()) + 1;
					txtBilhete.setText(String.format("%09d", num));
				}
			}
		} else {
			//ATUALIZA O BILHETE A PARTIR DO ARQUIVO TXT
			ArrayList<String> bilhete = new ArrayList<>();
			File arquivo = new File("../MASProject/dados/", "ingresso");
			//SE O ARQUIVO TXT EXISTE FILTRA O CONTEUDO
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
							//SE ENCONTRAR DADOS NO ARQUIVO TXT INCREMENTA O ULTIMO BILHETE
							int num = Integer.parseInt(bilhete.get(3).toString()) + 1;
							txtBilhete.setText(String.format("%09d", num));
							bilhete.clear();
						} else {
							//SE NÀO RETORNAR DADOS DO ARQUIVO TXT INICIA O BILHETE
							txtBilhete.setText(String.format("%09d",1));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				//SE NÃO EXISTE O ARQUIVO TXT INICIA O BILHETE
				txtBilhete.setText(String.format("%09d", 1));
			}
		}
	}

	public void formataTabela(){

		List<String[]> linhas = new ArrayList<>(); //VETOR DAS LINHAS DA TABELA

		//CONFIGURA AS LINHAS DA TABELA CONFORME OS DADOS DA BASE, SELECIONANDO SOMENTE AS COLUNAS CONFIGURADAS
		for (int i = 0; i < compra.size(); i++) {

			String[] item = { 
					compra.get(i).getExpo(), 
					compra.get(i).getVisitante(),
					compra.get(i).getBilhete(),  
					compra.get(i).getIngresso(), 
					compra.get(i).getQtd(), 
					compra.get(i).getValor()			
			};
			linhas.add(item);
		}

		//CONFIGURA O ALINHAMENTO PARA AS LINHAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = { "Exposição", "Visitante", "Bilhete", "Entrada", "Quantidade", "Valor"};

		//CRIA UM DefaulTableModel COM OS DADOS (LINHAS E COLUNAS)
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel(
				linhas.toArray(new String[linhas.size()][]), nomesColunas)
		//TRAVA A EDIÇÃO DAS CELULAS
		{  		  
			boolean[] canEdit = new boolean []{    
					false, false, false, false  
			};
			@Override    
			public boolean isCellEditable(int rowIndex, int columnIndex) {    
				return canEdit [columnIndex];    
			}  
		};

		//DEFINE COMO SELEÇÃO A LINHA INTEIRO
		tbCompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbCompra.setModel(model);

		//APLICA O ALINHAMENTO NAS LINHAS
		tbCompra.getColumnModel().getColumn(0).setCellRenderer(esquerda);  
		tbCompra.getColumnModel().getColumn(1).setCellRenderer(esquerda);
		tbCompra.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		tbCompra.getColumnModel().getColumn(3).setCellRenderer(esquerda);
		tbCompra.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		tbCompra.getColumnModel().getColumn(5).setCellRenderer(direita);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbCompra.getColumnModel().getColumn(0).setPreferredWidth(120);
		tbCompra.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbCompra.getColumnModel().getColumn(2).setPreferredWidth(30);
		tbCompra.getColumnModel().getColumn(3).setPreferredWidth(20);
		tbCompra.getColumnModel().getColumn(4).setPreferredWidth(20);
		tbCompra.getColumnModel().getColumn(5).setPreferredWidth(50);
	}


	public void atualizaTabela(){

		//SE FOR CADASTRADO UM NOVO VISITANTE, ATUALIZAR A BASE DE DADOS
		visitantes.clear();
		lerVisitante();

		//CARREGA A COMPRA NA BASE DE DADOS
		IngressoMdl ingresso = new IngressoMdl();
		if (!txtPesquisa.getText().isEmpty() && !txtQtd.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (txtPesquisa.getText().equals(visitantes.get(i).getNome())) {
					ingresso.setId(txtId.getText());
					ingresso.setData(txtData.getText());
					ingresso.setHora(txtHora.getText());
					ingresso.setBilhete(txtBilhete.getText());
					ingresso.setExpo(cbExpo.getSelectedItem().toString());
					ingresso.setVisitante(txtPesquisa.getText());
					ingresso.setIngresso(cbIngresso.getSelectedItem().toString());
					ingresso.setQtd(txtQtd.getText());
					ingresso.setValor(ftxtSubtotal.getText());
					ingresso.setPagamento(pagamento.getSelection().getActionCommand());
					compra.add(ingresso);
					//msg("save", cbExpo.getSelectedItem().toString());
					limpaCampos();
					validar = true;
				} 
			}
			if (validar == false) {
				msg("errorsave", txtPesquisa.getText());
			}
		} else {
			msg("errornull", txtPesquisa.getText());
		}

		//CARREGA A COMPRA PARA A TABELA
		formataTabela();

		//ATUALIZA TOTAL DA COMPRA
		int total = 0;
		for(int i = 0; i < compra.size(); i++){
			total = total + Integer.parseInt(compra.get(i).getValor().replace("R$ ","").replace(",00",""));	
		}
		ftxtTotal.setValue(total);

		//ATUALIZA O BILHETE
		atualizaBilhete();
	}
	
	
	public void removeLinha(){

		if(tbCompra.getRowCount() > 0){
			//ATUALIZA A BASE DE DADOS EXCLUINDO O REGISTRO PELO BILHETE COMO FILTRO
			for(int i = 0; i < compra.size(); i ++){
				if((tbCompra.getValueAt(tbCompra.getSelectedRow(), 2).toString()).equals(compra.get(i).getBilhete())){
					compra.remove(i);
				}
				//RECUPERA O BILHETE ATUAL PARA RETORNAR DEPOIS QUE O BILHETE EXCLUIDO FOR REUSADO
				altera = txtBilhete.getText();
			}
			//RETORNA O BILHETE PARA USO NO SISTEMA
			txtBilhete.setText(tbCompra.getValueAt(tbCompra.getSelectedRow(), 2).toString());

			//ATUALIZA A TABELA, REMOVENDO O DADO
			((DefaultTableModel) tbCompra.getModel()).removeRow(tbCompra.getSelectedRow());
			tbCompra.updateUI(); 
		}
	}


	public void atualizaValor(){

		//FILTRA CARREGA ARRAY COM A BASE DE DADOS DO VALOR DOS INGRESSOS POR TIPO
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
		//PRECORRE O ARRAY DOS DADOS
		for (int i = 0; i < tipo.size(); i++) {
			//CARREGA O VALOR DO INGRESSO ESCOLHIDO PARA O PREÇO UNITÁRIO
			if (cbIngresso.getSelectedItem().toString().equals(tipo.get(i).getTipo())) {
				ftxtValorUnit.setValue(Integer.parseInt(tipo.get(i).getValor()));
			}
		}
		//ATUALIZA O VALOR CONFORME A QUANTIDADE
		if(!txtQtd.getText().isEmpty()){	
			int qtd = Integer.parseInt(txtQtd.getText());
			Object unit = ftxtValorUnit.getValue();
			int total = qtd * (int) unit;
			ftxtSubtotal.setValue(total);
			if(compra.size() == 0){
				ftxtTotal.setValue(total);
			}
		} else {
			//POR PADRÃO A QUANTIDADE É "1"
			txtQtd.setText("1");
			atualizaValor();
		}
	}


	public void atualizaTroco(){

		if(!ftxtDinheiro.getText().isEmpty()){
			int vlr =  Integer.parseInt(ftxtSubtotal.getValue().toString());
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
			ftxtDinheiro.setValue(null);
			ftxtTroco.setValue(null);
		}
	}


	// PREENCHE COMBOBOX /////////////////////


	public void preencherComboBoxExpo() {

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS EXPOSIÇÃO
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

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS DO TIPOS DE INGRESSO
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

		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS DOS INGRESSOS COMPRADOS
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
					ingresso.setQtd(list.get(7));
					ingresso.setValor(list.get(8));
					ingresso.setPagamento(list.get(9));
					ingressos.add(ingresso);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizaDados(List<IngressoMdl> listingressos) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
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

		//LIMPA O ARRAY DOS VISITANTES PARA RECARREGAR COM OS DADOS ATUAIS DO ARQUIVO TXT
		visitantes.clear();
		lerVisitante();
		ArrayList<VisitanteMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtPesquisa.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (txtPesquisa.getText().equalsIgnoreCase(visitantes.get(i).getId())) {
					txtId.setText(visitantes.get(i).getId());
					txtPesquisa.setText(visitantes.get(i).getNome());
					validar = true;
					//VERIFICA OS REGISTROS QUE COMEÇAM COM O TEXTO DIGITADO
				} else if (visitantes.get(i).getNome().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase())) {
					validar = true;
				}
			}
			if (validar == true) {
				//CARREGA O ARRAY DOS VISITANTES OS NOMES INICIADOS COM O TEXTO DIGITADO
				for (int i = 0; i < visitantes.size(); i++) {
					boolean filtro = visitantes.get(i).getNome().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase());
					if (filtro == true) {
						VisitanteMdl item = new VisitanteMdl();
						item.setId(visitantes.get(i).getId());
						item.setNome(visitantes.get(i).getNome());
						lista.add(item);
					}
				}
				//CARREGADO PARA O ARRAY OS IDs DOS VISITANTES COM OS NOMES QUE COINCIDEM COM A DIGITAÇÃO
				String[] filtro = new String[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					filtro[i] = lista.get(i).getId();
					pesquisa = lista.get(i).getId();					
				}
				if (filtro != null && filtro.length > 1) {
					//INFORMA AO USUÁRIO UMA LISTA DOS ID's DOS USUÁRIOS E PEDE RETORNO
					pesquisa = (String) JOptionPane.showInputDialog(form, "Escolha o ID:\n", "Selecione o ID",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				} 
				if (pesquisa == "0" || pesquisa != null){
					//SE FOR ESCOLHIDO UM ID, PREENCHE O CAMPO COM O NOME
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
		
		// VERIFICA SE O ARQUIVO TXT EXISTE (E CRIA UM CASO NEGATIVO)
		new IngressoFile();
		//INICIALIZA VARIÁVEL COM O MODELO
		IngressoMdl ingresso = new IngressoMdl();
		//VERIFICA SE A TABELA ESTA CARREGADA (SE POSITIVO TRANSFERE PARA O MODELO)
		if (tbCompra.getRowCount() > 0){
			for(int i = 0; i < compra.size(); i++){			
				ingressos.add(compra.get(i));
				atualizaDados(ingressos);
			}
			compra.clear();		
			msg("save", "");
			//LIMPA AS LINHAS E ATUALIZA A TABELA
			((DefaultTableModel) tbCompra.getModel()).setNumRows(0); 
			tbCompra.updateUI();
			validar = true;
			//CASO A TABELA ESTEJA VAZIA, VERIFICA SE O CAMPO VISITANTE E QTD ESTÃO PREENCHIDOS
		} else if (!txtPesquisa.getText().isEmpty() && !txtQtd.getText().isEmpty()) {
			//PRECORRE O ARRAY VISITANTES PARA ENCONTRAR O NOME DIGITADO NO CAMPO
			for (int i = 0; i < visitantes.size(); i++) {
				//CARREGA O MODELO COM OS DADOS DOS CAMPOS DA TELA
				if (txtPesquisa.getText().equals(visitantes.get(i).getNome())) {
					ingresso.setId(txtId.getText());
					ingresso.setData(txtData.getText());
					ingresso.setHora(txtHora.getText());
					ingresso.setBilhete(txtBilhete.getText());
					ingresso.setExpo(cbExpo.getSelectedItem().toString());
					ingresso.setVisitante(txtPesquisa.getText());
					ingresso.setIngresso(cbIngresso.getSelectedItem().toString());
					ingresso.setQtd(txtQtd.getText());
					ingresso.setValor(ftxtSubtotal.getText()); 
					ingresso.setPagamento(pagamento.getSelection().getActionCommand());				
					ingressos.add(ingresso);
					msg("save", cbExpo.getSelectedItem().toString());
					//ENVIA A ARRAY DO MODELO CARREGADO PARA ESCRITA NO ARQUIVO TXT
					atualizaDados(ingressos);
					validar = true;
				} 
			}
			if (validar == false) {
				msg("errorsave", txtPesquisa.getText());
			}
		} else {
			msg("errornull", txtPesquisa.getText());
		}
		limpaCampos();
		atualizaId();
		atualizaTempo();
		atualizaBilhete();
	}


	// MENSAGENS //////////////////////////////


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
					"Ingresso(s) processado(s)\n" + mensagem + "\n\nCompra realizada com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/confirm.png"));
			break;
		case "errorsave":
			Object[] options = { "Confirmar", "Cancelar" };  
			int cadastro = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nO visitante '" + mensagem 
					+ "' não existe na base de dados!\n\nDeseja realizar o cadastro do visitante?",
					"Não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), options, options[1]);
			if (cadastro == 0) {
				try {
					FrmVisitanteCad frmCad = new FrmVisitanteCad();
					frmCad.setVisible(true);
					frmCad.setLocationRelativeTo(null);
					frmCad.txtNome.setText(txtPesquisa.getText());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			break;
		case "system":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), exit, exit[1]);
			if (fechar == 0) {
				validar = true;
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

			atualizaValor();
			atualizaTroco();
			pagamento();
		}
	};

	public ActionListener incluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			atualizaTabela();
		}
	};

	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			limpaCampos();
			compra.clear();
			((DefaultTableModel) tbCompra.getModel()).setNumRows(0);
			tbCompra.updateUI();
			atualizaBilhete();
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
			
			//SELECIONA O CAMPO COM FOCO E BLOQUEIA CARACTERES NÃO AUTORIZADOS
			if(txtPesquisa.hasFocus()){
				String caracteres="0987654321";
				if(caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
			}
			
			if(txtQtd.hasFocus() || ftxtDinheiro.hasFocus()){
				String caracteres="0987654321";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
			}
			
			txtQtd.setText(null);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode=e.getKeyCode();

			switch (keyCode) {

			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_DOWN:
				break;
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_ESCAPE:
				msg("system","Fechamento");
				if(validar != false){
				System.exit(0);
				}
				break;
			case KeyEvent.VK_DELETE:
				removeLinha();
				break;
			case 8: //MAC OSX: DELETE
				removeLinha();
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			atualizaValor();
		}
	};


	// CONTROLE FOCO ///////////////////////////////

	public FocusListener move = new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
		}

		@Override
		public void focusLost(FocusEvent e) {

			atualizaValor();
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

			if(e.getClickCount() == 2){  
				removeLinha();
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