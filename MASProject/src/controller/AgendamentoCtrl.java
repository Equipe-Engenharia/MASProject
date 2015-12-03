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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import model.AgendamentoMdl;
import model.ExposicaoMdl;
import persistence.AgendamentoFile;
import persistence.IngressoTipoFile;
import view.FrmCalendario;
import view.FrmExposicaoCad;


public class AgendamentoCtrl implements ComponentListener {

	private JPanel 
	form;
	private JTextField 
	txtId, 
	txtPesquisa, 
	txtNome, 
	txtTelefone, 
	txtResponsavel,
	txtResponsavelId;
	private static JFormattedTextField 
	ftxtVagas, 
	ftxtData, 
	ftxtQtd, 
	ftxtCusto, 
	ftxtTotal;
	private static JCalendar calendar;
	private JComboBox<String> 
	cbExpo, 
	cbTipo, 
	cbPeriodo, 
	cbIngresso;
	private JTable tbAgenda;
	private String newId;
	private boolean validar;
	private List<ExposicaoMdl> expos;
	private List<AgendamentoMdl> agendamentos;
	private List<AgendamentoMdl> tabela;
	private AgendamentoFile arquivo = new AgendamentoFile();
	
	public AgendamentoCtrl(
			JPanel form, 
			JTextField txtId, 
			JTextField txtPesquisa, 
			JTextField txtNome, 
			JTextField txtTelefone, 
			JTextField txtResponsavel, 
			JTextField txtResponsavelId, 
			JFormattedTextField ftxtVagas, 
			JFormattedTextField ftxtData, 
			JFormattedTextField ftxtQtd, 
			JFormattedTextField ftxtCusto, 
			JFormattedTextField ftxtTotal, 
			JComboBox<String> cbExpo, 
			JComboBox<String> cbTipo, 
			JComboBox<String> cbPeriodo, 
			JComboBox<String> cbIngresso, 
			JTable tbAgenda
			){

		this.form = form;
		this.txtId = txtId;
		this.txtPesquisa = txtPesquisa;
		this.txtNome = txtNome;
		this.txtTelefone = txtTelefone;
		this.txtResponsavel = txtResponsavel;
		this.txtResponsavelId = txtResponsavelId;
		this.ftxtVagas = ftxtVagas;
		AgendamentoCtrl.ftxtData = ftxtData;
		this.ftxtQtd = ftxtQtd;
		this.ftxtCusto = ftxtCusto;
		this.ftxtTotal = ftxtTotal;
		this.cbExpo = cbExpo;
		this.cbTipo = cbTipo;
		this.cbPeriodo = cbPeriodo;
		this.cbIngresso = cbIngresso;
		this.tbAgenda = tbAgenda;
		this.expos = new ArrayList<ExposicaoMdl>();
		this.agendamentos = new ArrayList<AgendamentoMdl>();
		this.tabela = new ArrayList<AgendamentoMdl>();
		
		sessao();
		lerArquivo();
		atualizarId();
		atualizarTempo();
		preencherPeriodo();
		preencherTipo();
		preencherIngresso();
		preencherExpo();
		//preencherCampos();
		formataTabela();
	}
	
	
	// PREENCHE COMBOBOX /////////////////////
	
	
	public void preencherExpo() {

		cbExpo.addItem("Selecione…");
		String linha = new String();
		ArrayList<String> listString = new ArrayList<>();	
		try {
			//RECUPERA E FILTRA OS DADOS DO ARQUIVO TXT
			arquivo.leArquivo("../MASProject/dados/", "exposicoes");
			linha = arquivo.getBuffer();
			String[] listaExpo = linha.split(";");
			for (String s : listaExpo) {
				String text = s.replaceAll(".*:", "");
				listString.add(text);
				if (s.contains("---")) {
					//PREENCHE O ARRAY
					ExposicaoMdl expo = new ExposicaoMdl();
					expo.setId(listString.get(0));
					expo.setTitulo(listString.get(1));
					expo.setDataIni(listString.get(2));
					expo.setDataFim(listString.get(3));
					expo.setDescricao(listString.get(4));
					expos.add(expo);
					cbExpo.addItem(listString.get(1));
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		cbExpo.addItem("Adicionar nova…");
	}
	
	public void preencherTipo() {

		cbTipo.addItem("Selecione…");
		cbTipo.addItem("Escola");
		cbTipo.addItem("Orfanato");
		cbTipo.addItem("Associação");
		cbTipo.addItem("Outros");
	}
	
	public void preencherPeriodo() {

		cbPeriodo.addItem("Selecione…");
		cbPeriodo.addItem("Matutino");
		cbPeriodo.addItem("Vespertino");
		cbPeriodo.addItem("Noturno");
	}
	
	public void preencherIngresso() {

		cbIngresso.addItem("Selecione…");
		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS DO TIPOS DE INGRESSO
		String linha = new String();
		IngressoTipoFile arquivos = new IngressoTipoFile();
		arquivos = new IngressoTipoFile();
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


	// METODOS DE SUPORTE ////////////////////////
	
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
		} else {
			msg("", log.getLogon().get(0).getNivel());
		}
	}


	public void atualizarId() {

		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("AGD" + NewId);
	}
	
	public void responsavelId() {

		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtResponsavelId.setText("AUT" + NewId);
	}
	
	public String institutoId() {

		
		if(agendamentos.size() > 0){
			for (int i = 0; i < agendamentos.size(); i++) {

				if (txtNome.getText().equals(agendamentos.get(i).getInstituto())){
					newId = agendamentos.get(i).getInstitutoId();
				}
			}
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
			Date date = new Date();
			newId = "ITT" + (dateFormat.format(date));
		}
		return newId;
	}

	public void limpaCampos() {

		txtPesquisa.setText(null);
		txtNome.setText(null);
		ftxtData.setValue(null);
		ftxtCusto.setValue(null);
		ftxtQtd.setValue(null);
		ftxtTotal.setValue(null);
		cbTipo.setSelectedItem("Selecione…");
		cbPeriodo.setSelectedItem("Selecione…");
		cbIngresso.setSelectedItem("Selecione…");
		cbExpo.setSelectedItem("Selecione…");
		if (tabela.isEmpty()) { 
			txtResponsavel.setText(null);
			txtResponsavelId.setText(null);
			txtTelefone.setText(null);
			cbTipo.setSelectedItem("Selecione…");
			cbPeriodo.setSelectedItem("Selecione…");
			cbIngresso.setSelectedItem("Selecione…");
			cbExpo.setSelectedItem("Selecione…");
			ftxtTotal.setValue(null);
		}
		bloquearCampos();
	}
	
	public void ativarCampos(){

		txtPesquisa.setEnabled(false);
		txtNome.setEnabled(true);
		txtNome.requestFocus();
		txtTelefone.setEnabled(true);
		txtResponsavel.setEnabled(true);
		responsavelId();
	}

	public void bloquearCampos(){

		txtPesquisa.setEnabled(true);
		txtNome.setText(null);
		txtNome.setEnabled(false);
		txtTelefone.setText(null);
		txtTelefone.setEnabled(false);
		txtResponsavel.setText(null);
		txtResponsavel.setEnabled(false);
		txtResponsavelId.setText(null);
		txtResponsavelId.setEnabled(false);
	}
	
	public void atualizarTempo(){  

		if(ftxtData.getValue() != null){

			SimpleDateFormat temp = new SimpleDateFormat("ddMMyyyy");

			try {
				Date dt1 = temp.parse(ftxtData.getValue().toString().replace("/","").replace("/",""));			
				Date dt2 = temp.parse(temp.format(new Date()));
				if (dt2.after(dt1)) {
					msg("errorDate", "");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}  
	
	public void atualizarValor() {

		if(cbIngresso.getSelectedItem() != "Selecione…"){
			
		//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS DO TIPOS DE INGRESSO
		String linha = new String();
		IngressoTipoFile arquivos = new IngressoTipoFile();
		arquivos = new IngressoTipoFile();
		ArrayList<String> listString = new ArrayList<>();
		try {
			arquivos.leArquivo("../MASProject/dados/", "ingressoTipo");
			linha = arquivos.getBuffer();
			String[] ingresso = linha.split(";");
			for (String s : ingresso) {
				String text = s.replaceAll(".*: ", "");
				listString.add(text);
				if (s.contains("---")) {

					//PRECORRE O ARRAY DOS DADOS
					for (int i = 0; i < listString.size(); i++) {
						//CARREGA O VALOR DO INGRESSO ESCOLHIDO PARA O PREÇO UNITÁRIO
						if (cbIngresso.getSelectedItem().toString().equals(listString.get(1))) {
							ftxtCusto.setValue(Integer.parseInt(listString.get(2)));
						}
						listString.clear();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//ATUALIZA O VALOR CONFORME A QUANTIDADE
		if(!ftxtQtd.getText().isEmpty()){	
			int qtd = Integer.parseInt(ftxtQtd.getText());
			Object unit = ftxtCusto.getValue();
			int total = qtd * (int) unit;
			ftxtTotal.setValue(total);
			if(tabela.size() == 0){
				ftxtTotal.setValue(total);
			}
		} else {
			//POR PADRÃO A QUANTIDADE É "1"
			ftxtQtd.setText("1");
			atualizarValor();
		}
		} else {
			ftxtCusto.setValue(null);
			ftxtTotal.setValue(null);
			ftxtQtd.setValue(null);
		}
	}
	
	
	public AgendamentoCtrl(JCalendar calendar) {
		
		AgendamentoCtrl.calendar = calendar;
	}
	
	
	public static void leCalendario() {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		//LÊ DATA SELECIONADA PELO USUARIO
		String data = formato.format(calendar.getDate());
		ftxtData.setValue(null);
		ftxtData.setValue(data);
	}
	

	public void chamaCalendario() throws ParseException {
		FrmCalendario frmCal = new FrmCalendario();
		frmCal.setLocationRelativeTo(null);
		frmCal.setVisible(true);
	}

	public void formataTabela(){
		
		//VETOR DAS LINHAS DA TABELA
		List<String[]> linhas = new ArrayList<>(); 

		//CARREGA AS LINHAS NA TABELA COM OS DADOS (SOMENTE AS COLUNAS CONFIGURADAS)
		for (int i = 0; i < tabela.size(); i++) {

			String[] item = {  
					tabela.get(i).getExpoNome(),
					tabela.get(i).getPeriodo(),
					tabela.get(i).getData(),  
					tabela.get(i).getResponsavel(), 
					tabela.get(i).getQtd(), 
					tabela.get(i).getVlrUnitario()			
			};
			linhas.add(item);
		}
		
		//CONFIGURA O ALINHAMENTO DOS TÍTULOS DAS COLUNAS DA TABELA
		((DefaultTableCellRenderer) tbAgenda.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		//CONFIGURA O ALINHAMENTO PARA AS COLUNAS DA TABELA
		DefaultTableCellRenderer esquerda = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();  
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();  

		esquerda.setHorizontalAlignment(SwingConstants.LEFT);  
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);  
		direita.setHorizontalAlignment(SwingConstants.RIGHT);

		//NOMES DAS COLUNAS DA TABELA
		String[] nomesColunas = {"Exposição", "Período", "Data", "Responsável", "Qtd. Pessoas", "Valor"};

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

		//DEFINE COMO SELEÇÃO TODA A LINHA
		tbAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//DEFINE O MODEL DA TABELA
		tbAgenda.setModel(model);

		//DEFINE O ALINHAMENTO DAS COLUNAS
		tbAgenda.getColumnModel().getColumn(0).setCellRenderer(esquerda);
		tbAgenda.getColumnModel().getColumn(1).setCellRenderer(esquerda); 
		tbAgenda.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		tbAgenda.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		tbAgenda.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		tbAgenda.getColumnModel().getColumn(5).setCellRenderer(centralizado);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbAgenda.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbAgenda.getColumnModel().getColumn(1).setPreferredWidth(30);
		tbAgenda.getColumnModel().getColumn(2).setPreferredWidth(30);
		tbAgenda.getColumnModel().getColumn(3).setPreferredWidth(80);
		tbAgenda.getColumnModel().getColumn(4).setPreferredWidth(30);
		tbAgenda.getColumnModel().getColumn(5).setPreferredWidth(50);
	}


	public void atualizaTabela(){

		//SE FOR CADASTRADO UM NOVO REGISTRO, ATUALIZAR A BASE DE DADOS
		agendamentos.clear();
		lerArquivo();

		//CARREGA O NOVO REGISTRO NA BASE DE DADOS
		AgendamentoMdl agendamento = new AgendamentoMdl();
		if (!txtNome.getText().isEmpty() 
				&& ftxtData.getValue() != null 
				&& cbTipo.getSelectedItem().toString() != "Selecione…" 
				&& cbPeriodo.getSelectedItem().toString() != "Selecione…" 
				&& cbExpo.getSelectedItem().toString() != "Selecione…" 
				&& ftxtCusto.getValue() != null) {

			for (int i = 0; i <= agendamentos.size(); i++) {
				//msg("",txtNome.getText() + " = " + agendamentos.get(i).getInstituto());
				if (agendamentos.size() == 0 || 
						txtNome.getText().equals(agendamentos.get(i).getInstituto())) {
					
					agendamento.setId(txtId.getText());
					agendamento.setInstitutoId(institutoId());
					agendamento.setInstituto(txtNome.getText());
					agendamento.setFone(txtTelefone.getText());
					agendamento.setResponsavelId(txtResponsavelId.getText());
					agendamento.setResponsavel(txtResponsavel.getText());
					agendamento.setTipo(cbTipo.getSelectedItem().toString());
					agendamento.setData(ftxtData.getText());
					agendamento.setPeriodo(cbPeriodo.getSelectedItem().toString());
					agendamento.setQtd(ftxtQtd.getText());
					
					for (int j = 0; j < expos.size(); j++) { //NECESSÁRIO POIS O ID É DE OUTRA BASE
						if (cbExpo.getSelectedItem().toString().equals(expos.get(j).getTitulo())) {
							agendamento.setExpoId(expos.get(j).getId());
						}
					}
					
					agendamento.setExpoNome(cbExpo.getSelectedItem().toString());
					agendamento.setIngresso(cbIngresso.getSelectedItem().toString());
					agendamento.setVlrUnitario(ftxtCusto.getValue().toString());
					agendamento.setVlrTotal(ftxtTotal.getValue().toString()); 			
					tabela.add(agendamento);
					validar = true;
				}
			}		
			//ATUALIZA TOTAL DO CUSTO
			int total = 0;
			for(int t = 0; t < tabela.size(); t++){
				total = total + Integer.parseInt(tabela.get(t).getVlrUnitario().replace("R$ ","").replace(",00",""));	
			}
			ftxtTotal.setValue(total);
			limpaCampos();
			
			if (validar == false) {
				msg("errorSave", txtPesquisa.getText());
			}
		} else {
			msg("errorNull", txtPesquisa.getText());
		}	
		//CARREGA O REGISTRO PARA A TABELA
		formataTabela();
	}
	
	
	public void removeLinha(){

		if(tbAgenda.getRowCount() > 0){
			//ATUALIZA A BASE DE DADOS EXCLUINDO O REGISTRO PELO ID DA OBRA COMO FILTRO
			for(int i = 0; i < tabela.size(); i ++){
				if((tbAgenda.getValueAt(tbAgenda.getSelectedRow(), 0).toString()).equals(tabela.get(i).getExpoId())){
					tabela.remove(i);
				}
			}

			//ATUALIZA A TABELA, REMOVENDO O DADO
			((DefaultTableModel) tbAgenda.getModel()).removeRow(tbAgenda.getSelectedRow());
			tbAgenda.updateUI();
			
			if (tbAgenda.getRowCount() == 0) {
				tabela.clear();
				limpaCampos();
			}
		}
	}
	

	// CRUD //////////////////////////


	public void lerArquivo() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			// VERIFICA SE O ARQUIVO TXT EXISTE E FAZ LEITURA (E CRIA O TXT CASO NEGATIVO)
			arquivo.leArquivo("../MASProject/dados/", "agendamentos");
			//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS DO ARQUIVO TXT
			linha = arquivo.getBuffer();
			String[] listaAgd = linha.split(";");
			for (String s : listaAgd) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					AgendamentoMdl agendamento = new AgendamentoMdl();
					
					
					agendamento.setId(list.get(0));
					agendamento.setInstitutoId(list.get(1));
					agendamento.setInstituto(list.get(2));
					agendamento.setFone(list.get(3));
					agendamento.setResponsavelId(list.get(4));
					agendamento.setResponsavel(list.get(5));
					agendamento.setTipo(list.get(6));
					agendamento.setData(list.get(7));
					agendamento.setPeriodo(list.get(8));
					agendamento.setQtd(list.get(9));
					agendamento.setExpoId(list.get(10));
					agendamento.setExpoNome(list.get(11));
					agendamento.setIngresso(list.get(12));
					agendamento.setVlrUnitario(list.get(13));
					agendamento.setVlrTotal(list.get(14));
					agendamentos.add(agendamento);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizaDados(List<AgendamentoMdl> listagendamentos) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File("../MASProject/dados/agendamentos");
		f.delete();
		for (AgendamentoMdl agendamento : listagendamentos) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "agendamentos", "", agendamento);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void pesquisar() {

		ArrayList<AgendamentoMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtPesquisa.getText().isEmpty()) {
			for (int i = 0; i < agendamentos.size(); i++) {
				
				//VERIFICA SE O ID DIGITADO CONFERE COM ALGUM ID CADASTRADO
				if (txtPesquisa.getText().equalsIgnoreCase(agendamentos.get(i).getId())) {
					txtNome.setText(agendamentos.get(i).getInstituto());
					txtTelefone.setText(agendamentos.get(i).getFone());
					txtResponsavel.setText(agendamentos.get(i).getResponsavel());
					txtResponsavelId.setText(agendamentos.get(i).getResponsavelId());
					cbTipo.setSelectedItem(agendamentos.get(i).getTipo());
					validar = true;
					
					//VERIFICA OS REGISTROS QUE COMEÇAM COM O TEXTO DIGITADO
				} else if (agendamentos.get(i).getInstituto().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase())) {
					validar = true;
				}
			}
			if (validar == true) {
				//CARREGA O ARRAY COM OS NOMES INICIADOS COM O TEXTO DIGITADO
				for (int i = 0; i < agendamentos.size(); i++) {
					boolean filtro = agendamentos.get(i).getInstituto().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase());
					if (filtro == true) {
						AgendamentoMdl item = new AgendamentoMdl();
						item.setInstitutoId(agendamentos.get(i).getInstitutoId());
						item.setInstituto(agendamentos.get(i).getInstituto());
						lista.add(item);
					}
				}
				//CARREGADO PARA O ARRAY OS IDs COM OS NOMES QUE COINCIDEM COM A DIGITAÇÃO
				String[] filtro = new String[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					filtro[i] = lista.get(i).getInstitutoId() + " : " + lista.get(i).getInstituto();
					pesquisa = lista.get(i).getInstitutoId();
				}
				if (filtro != null && filtro.length > 1) {
				
					//INFORMA AO USUÁRIO UMA LISTA DOS ID's DOS USUÁRIOS E PEDE RETORNO
					pesquisa = (String) JOptionPane.showInputDialog(form, "Selecione:\n", "Registros Localizados",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				} 
				if (pesquisa == "0" || pesquisa != null){
				
					//SE FOR ESCOLHIDO UM ID, PREENCHE O CAMPO COM O NOME
					for (int i = 0; i < agendamentos.size(); i++) {
						if (pesquisa.replaceAll(" : .*", "").equalsIgnoreCase(agendamentos.get(i).getInstitutoId())) {
							txtNome.setText(agendamentos.get(i).getInstituto());
							txtTelefone.setText(agendamentos.get(i).getFone());
							txtResponsavel.setText(agendamentos.get(i).getResponsavel());
							txtResponsavelId.setText(agendamentos.get(i).getResponsavelId());
							cbTipo.setSelectedItem(agendamentos.get(i).getTipo());
							txtPesquisa.setText(null);
						}
					}
				}
				validar = false;
			} else {
				if (pesquisa == "") {
					msg("errorSave", txtPesquisa.getText());
					txtNome.setText(txtPesquisa.getText().toString());
				}
				validar = false;
			}
		} else {
			msg("errorSearch", txtPesquisa.getText());
		}
	}


	public void gravar() {

		//INICIALIZA VARIÁVEL COM O MODELO
		AgendamentoMdl agendamento = new AgendamentoMdl();
		//VERIFICA SE A TABELA ESTA CARREGADA (SE POSITIVO TRANSFERE PARA O MODELO)
		if (tbAgenda.getRowCount() > 0){
			for(int i = 0; i < tabela.size(); i++){			
				agendamentos.add(tabela.get(i));
				atualizaDados(agendamentos);
			}
			tabela.clear();					
			msg("save", txtId.getText());
			
			//LIMPA AS LINHAS E ATUALIZA A TABELA
			((DefaultTableModel) tbAgenda.getModel()).setNumRows(0); 
			tbAgenda.updateUI();
			limpaCampos();
			atualizarId();
			validar = true;
			
			//CASO A TABELA ESTEJA VAZIA, VERIFICA SE OS CAMPOS ESTÃO PREENCHIDOS
		} else if (!txtNome.getText().isEmpty() 
				&& ftxtData.getValue() != null 
				&& cbTipo.getSelectedItem().toString() != "Selecione…" 
				&& cbPeriodo.getSelectedItem().toString() != "Selecione…" 
				&& cbExpo.getSelectedItem().toString() != "Selecione…" 
				&& ftxtCusto.getValue() != null) {
			
			//PRECORRE O ARRAY PARA ENCONTRAR O NOME DIGITADO NO CAMPO
			for (int i = 0; i < agendamentos.size(); i++) {

				//CARREGA O MODELO COM OS DADOS DOS CAMPOS DA TELA
				if (txtNome.getText().equals(agendamentos.get(i).getInstituto())) {
					agendamento.setId(txtId.getText());
					agendamento.setInstitutoId(institutoId());
					agendamento.setInstituto(txtNome.getText());
					agendamento.setFone(txtTelefone.getText());
					agendamento.setResponsavelId(txtResponsavelId.getText());
					agendamento.setResponsavel(txtResponsavel.getText());
					agendamento.setTipo(cbTipo.getSelectedItem().toString());
					agendamento.setData(ftxtData.getText());
					agendamento.setPeriodo(cbPeriodo.getSelectedItem().toString());
					agendamento.setQtd(ftxtQtd.getText());
					
					for (int j = 0; j < expos.size(); j++) { //NECESSÁRIO POIS O ID É DE OUTRA BASE
						if (cbExpo.getSelectedItem().toString().equals(expos.get(j).getTitulo())) {
							agendamento.setExpoId(expos.get(j).getId());
						}
					}
					
					agendamento.setExpoNome(cbExpo.getSelectedItem().toString());
					agendamento.setIngresso(cbIngresso.getSelectedItem().toString());
					agendamento.setVlrUnitario(ftxtCusto.getValue().toString());
					agendamento.setVlrTotal(ftxtTotal.getValue().toString());			
					agendamentos.add(agendamento);
					msg("save", "A Insttuição " + txtNome.getText() + " para a " + cbExpo.getSelectedItem().toString());
					
					//ENVIA A ARRAY DO MODELO CARREGADO PARA ESCRITA NO ARQUIVO TXT
					atualizaDados(agendamentos);
					limpaCampos();
					atualizarId();
					validar = true;
				} 
			}
			if (validar == false) {
				msg("errorSave", txtPesquisa.getText());
			}
		} else {
			msg("errorNull", txtPesquisa.getText());
		}
	}



	// MENSAGENS //////////////////////////////


	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errorNull":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\n\nPor favor, complete todos os campos necessários.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
			break;
			
		case "errorSearch":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Por favor, digite para pesquisar!", 
					"Erro",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/error.png"));
			break;
			
		case "errorDate":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Erro no campo Data.\n\nPor favor, preencha a data respeitando que deve ser superior a data de ontem.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
			ftxtData.setValue(null);
			break;
			
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Registro(s) processado(s)\n\n" + mensagem + "\n\nAgendamento registrado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/confirm.png"));
			break;
			
		case "saveConfirm":
			Object[] save = { "Confirmar", "Cancelar" };  
			int confirmar = JOptionPane.showOptionDialog(null, "Confirma o processamento do agendamento " + mensagem 
					+ " ?",
					"Aguardando Confirmação", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), save, save[1]);
			if (confirmar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;
			
		case "errorSave":
			Object[] options = { "Confirmar", "Cancelar" };  
			int cadastro = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nA Instituição '" + mensagem 
					+ "' não existe na base de dados!\n\nDeseja realizar o cadastro desta Instituição?",
					"Não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), options, options[0]);
			if (cadastro == 0) {
				ativarCampos();
			}
			break;
			
		case "novoEpo":
			Object[] novoExpo = { "Confirmar", "Cancelar" };  
			int cadastroExpo = JOptionPane.showOptionDialog(null, "Gostaria de cadastrar uma nova Exposição \npara o agendamento\n\nID " + mensagem 
					+ " ?",
					"Nova Exposição", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/question.png"), novoExpo, novoExpo[1]);
			if (cadastroExpo == 0) {
				if (cadastroExpo == 0) {
					FrmExposicaoCad frmCad;
					try {
						frmCad = new FrmExposicaoCad();
						frmCad.setVisible(true);
						frmCad.setLocation(0,0);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					limpaCampos();
				}
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO! Algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nOcorreu no Controller desta Tela.\nPor favor, avise a um admnistrador", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
		}
	}


	// CONTROLE ACTION //////////////////////////////


	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			pesquisar();
		}
	};
	
	public ActionListener atualizar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//preencherCampos();
			if(cbExpo.getSelectedItem().toString().equals("Adicionar novo…")){
				msg("novoExpo",txtId.getText());
			}
		}
	};

	public ActionListener valor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			atualizarValor();
		}
	};

	public ActionListener incluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			atualizarTempo();
			atualizaTabela();
		}
	};
	
	public ActionListener apagar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			removeLinha();
		}
	};
	
	public ActionListener abreCal = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				chamaCalendario();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	};
	

	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			tabela.clear();
			((DefaultTableModel) tbAgenda.getModel()).setNumRows(0);
			tbAgenda.updateUI();
			limpaCampos();
		}
	};

	public ActionListener gravar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//msg("saveConfirm", txtId.getText());
			//if(validar == true){
			gravar();
			//}
		}
	};


	// CONTROLE TECLA ///////////////////////////////


	public KeyListener tecla = new KeyListener() {  

		@Override  
		public void keyTyped(KeyEvent e) {
			
			//SELECIONA O CAMPO COM FOCO E BLOQUEIA CARACTERES NÃO AUTORIZADOS
			/*if(txtPesquisa.hasFocus()){
				String caracteres="0987654321";
				if(caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
			}*/
			
			if(txtTelefone.hasFocus() || ftxtQtd.hasFocus()){
				String caracteres="0987654321";
				if(!caracteres.contains(e.getKeyChar()+"")){
					e.consume();
				}
			}
			
			//txtTelefone.setText(null);
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
				/*msg("system","Fechamento");
				if(validar != false){
				System.exit(0);
				}*/
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
		}
	};


	// CONTROLE FOCO ///////////////////////////////

	public FocusListener move = new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			
			if (ftxtData.hasFocus()) { 
				ftxtData.setText(null);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
		
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
}