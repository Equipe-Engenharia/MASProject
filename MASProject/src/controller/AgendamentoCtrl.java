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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import view.FrmCalendario;
import view.FrmLogin;

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
	cbPeriodo, 
	cbTipo;
	private JTable tbAgenda;
	private boolean validar;
	private static int flag;
	//private List<EmprestimoObraMdl> obras;
	private List<ExposicaoMdl> exposicoes;
	private List<AgendamentoMdl> emprestimos;
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
			JComboBox<String> cbPeriodo, 
			JComboBox<String> cbTipo, 
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
		this.cbPeriodo = cbPeriodo;
		this.cbTipo = cbTipo;
		this.tbAgenda = tbAgenda;
		//this.obras = new ArrayList<EmprestimoObraMdl>();
		this.exposicoes = new ArrayList<ExposicaoMdl>();
		this.emprestimos = new ArrayList<AgendamentoMdl>();
		this.tabela = new ArrayList<AgendamentoMdl>();
		
		sessao();
		lerArquivo();
		//lerAcervo();
		//lerMuseu();
		atualizarId();
		//atualizarTempo();
		preencherMuseu();
		//preencherCampos();
		formataTabela();
	}
	
	
	// PREENCHE COMBOBOX /////////////////////

	
	public void preencherMuseu() {

		cbExpo.addItem("Selecione…");
		for(int i = 0; i < exposicoes.size(); i++){	
			cbExpo.addItem(exposicoes.get(i).getTitulo());
		}
		cbExpo.addItem("Adicionar novo…");
	}
	
	
	/*public void preencherCampos() {

		for(int i = 0; i < exposicoes.size(); i++)
			if(cbExpo.getSelectedItem().toString().equals(exposicoes.get(i).getTitulo())){
				txtTelefone.setText(exposicoes.get(i).getTelefone());
				txtResponsavel.setText(exposicoes.get(i).getResponsavel());
				txtResponsavelId.setText(exposicoes.get(i).getResponsavelId());
			} else if (cbExpo.getSelectedItem() == "Selecione…" || cbExpo.getSelectedItem() == "Adicionar novo…") {
				txtTelefone.setText(null);
				txtResponsavel.setText(null);
				txtResponsavelId.setText(null);
			}
	}*/


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


	/*public void limpaCampos() {

		txtPesquisa.setText(null);
		txtNome.setText(null);
		txtArtista.setText(null);
		ftxtData.setValue(null);
		ftxtDataFinal.setValue(null);
		ftxtCusto.setValue(null);
		if (tabela.isEmpty()) { 
			rdbtnEntrada.setEnabled(true);
			rdbtnSaida.setEnabled(true);
			cbExpo.setEnabled(true);
			txtResponsavel.setText(null);
			txtResponsavelId.setText(null);
			txtTelefone.setText(null);
			cbExpo.setSelectedItem("Selecione…");
			ftxtTotal.setValue(null);
		}
	}*/
	
	public AgendamentoCtrl(JCalendar calendar) {
		
		AgendamentoCtrl.calendar = calendar;
	}
	
	/*
	 * AS FLAGS FUNCIONAM PARA QUANDO SE TEM MAIS DE UMA CHAMADA DE CALENDARIO
	 * NA MESMA TELA (AJUDA NO TRATAMENTO DE RETORNO)
	 */
	/*public static int getFlag() {
		
		return flag;
	}

	public void setFlag(int n) {
		
		flag = n;
	}*/

	public static void leCalendario() {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		//LÊ DATA SELECIONADA PELO USUARIO
		String data = formato.format(calendar.getDate());
		ftxtData.setValue(null);
		ftxtData.setValue(data);
		//insereDataTextField(data);
	}
	
	/*public static void insereDataTextField(String data) {
		
		// TRATAMENTO DAS FLAGS
		switch (getFlag()) {
		
		case 1:
			ftxtData.setValue(null);
			ftxtData.setValue(data);
			break;
		case 2:
			ftxtDataFinal.setValue(null);
			ftxtDataFinal.setValue(data);
			break;
		}
	}*/

	public void chamaCalendario() throws ParseException {
		FrmCalendario frmCal = new FrmCalendario();
		frmCal.setLocationRelativeTo(null);
		frmCal.setVisible(true);
	}


	/*public void lerAcervo() {

		String linha = new String();
		ArrayList<String> list = new ArrayList<>();	
		try {
			//RECUPERA E FILTRA OS DADOS DO ARQUIVO TXT
			arquivo.leArquivo("../MASProject/dados/", "acervo");
			linha = arquivo.getBuffer();
			String[] listaAcervo = linha.split(";");
			for (String s : listaAcervo) {
				String text = s.replaceAll(".*:", "");
				list.add(text);
				if (s.contains("---")) {
					//PREENCHE O ARRAY
					EmprestimoObraMdl obra = new EmprestimoObraMdl();
					obra.setId(list.get(0));
					obra.setArtista(list.get(1));
					obra.setNome(list.get(2));
					obras.add(obra);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	
	/*public void lerMuseu() {

		String linha = new String();
		ArrayList<String> listaMuseu = new ArrayList<>();	
		try {
			//RECUPERA E FILTRA OS DADOS DO ARQUIVO TXT
			arquivo.leArquivo("../MASProject/dados/", "museus");
			linha = arquivo.getBuffer();
			String[] lista = linha.split(";");
			for (String s : lista) {
				String text = s.replaceAll(".*: ", "");
				listaMuseu.add(text);
				if (s.contains("---")) {
					//PREENCHE O ARRAY
					MuseuMdl museu = new MuseuMdl();
					museu.setId(listaMuseu.get(0));
					museu.setNome(listaMuseu.get(1));
					museu.setTelefone(listaMuseu.get(2));
					museu.setResponsavelId(listaMuseu.get(3));
					museu.setResponsavel(listaMuseu.get(4));
					exposicoes.add(museu);
					listaMuseu.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/


	/*public void atualizarTempo(){  

		if(ftxtData.getValue() != null){

			SimpleDateFormat temp = new SimpleDateFormat("ddMMyyyy");

			try {
				Date dt1 = temp.parse(ftxtData.getValue().toString().replace("/","").replace("/",""));
				Date dt2 = temp.parse(ftxtDataFinal.getValue().toString().replace("/","").replace("/",""));
				if (dt1.after(dt2)) {   
					msg("errordate", "");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}  */
	

	public void formataTabela(){
		
		//VETOR DAS LINHAS DA TABELA
		List<String[]> linhas = new ArrayList<>(); 

		//CARREGA AS LINHAS NA TABELA COM OS DADOS DA COMPRA (SOMENTE AS COLUNAS CONFIGURADAS)
		for (int i = 0; i < tabela.size(); i++) {

			String[] item = {  
					tabela.get(i).getExpo(),
					tabela.get(i).getResponsavel(), 
					tabela.get(i).getResponsavelId(), 
					tabela.get(i).getData(), 
					tabela.get(i).getInstitutoId(), 
					tabela.get(i).getCusto()			
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
		String[] nomesColunas = {"Obra", "Responsável", "Autorização", "Data Inicial", "Data Final", "Custo"};

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
		tbAgenda.getColumnModel().getColumn(5).setCellRenderer(direita);

		//CONFIGURA O TAMANHO DAS COLUNAS
		tbAgenda.getColumnModel().getColumn(0).setPreferredWidth(110);
		tbAgenda.getColumnModel().getColumn(1).setPreferredWidth(80);
		tbAgenda.getColumnModel().getColumn(2).setPreferredWidth(85);
		tbAgenda.getColumnModel().getColumn(3).setPreferredWidth(50);
		tbAgenda.getColumnModel().getColumn(4).setPreferredWidth(50);
		tbAgenda.getColumnModel().getColumn(5).setPreferredWidth(60);
	}


	public void atualizaTabela(){

		//SE FOR CADASTRADO UM NOVO REGISTRO, ATUALIZAR A BASE DE DADOS
		//obras.clear();
		//lerAcervo();

		//CARREGA O NOVO REGISTRO NA BASE DE DADOS
		//EmprestimoMdl emprestimo = new EmprestimoMdl();
		/*if (!txtNome.getText().isEmpty() 
				&& ftxtData.getValue() != null  
				&& cbExpo.getSelectedItem().toString() != "Selecione…" 
				&& ftxtCusto.getValue() != null) {

			for (int i = 0; i < obras.size(); i++) {
				if (txtNome.getText().equals(obras.get(i).getNome())) {
					emprestimo.setId(txtId.getText());
					emprestimo.setObraId(obras.get(i).getId());
					emprestimo.setObra(txtNome.getText());
					emprestimo.setArtista(txtArtista.getText());
					emprestimo.setDestino(btgDestino.getSelection().getActionCommand());
					emprestimo.setDataInicial(ftxtData.getText());
					emprestimo.setDataFinal(ftxtDataFinal.getText());
					for (int j = 0; j < exposicoes.size(); j++) { //NECESSÁRIO POIS O ID É DE OUTRA BASE
						if (cbExpo.getSelectedItem().toString().equals(exposicoes.get(j).getNome())) {
							emprestimo.setMuseuId(exposicoes.get(j).getId());
						}
					}
					emprestimo.setMuseu(cbExpo.getSelectedItem().toString());
					emprestimo.setResponsavelId(txtResponsavelId.getText());
					emprestimo.setResponsavel(txtResponsavel.getText());
					emprestimo.setCusto(ftxtCusto.getValue().toString());			
					tabela.add(emprestimo);
					//msg("save", txtObra.getText());
					validar = true;
				}
			}
			
			//ATUALIZA TOTAL DO CUSTO
			int total = 0;
			for(int t = 0; t < tabela.size(); t++){
				total = total + Integer.parseInt(tabela.get(t).getCusto().replace("R$ ","").replace(",00",""));	
			}
			ftxtTotal.setValue(total);
			limpaCampos();
			
			if (validar == false) {
				msg("errorsave", txtPesquisa.getText());
			}
		} else {
			msg("errornull", txtPesquisa.getText());
		}	
		//CARREGA O REGISTRO PARA A TABELA
		formataTabela();*/
	}
	
	
	public void removeLinha(){

	/*	if(tbAgenda.getRowCount() > 0){
			//ATUALIZA A BASE DE DADOS EXCLUINDO O REGISTRO PELO ID DA OBRA COMO FILTRO
			for(int i = 0; i < tabela.size(); i ++){
				if((tbAgenda.getValueAt(tbAgenda.getSelectedRow(), 0).toString()).equals(tabela.get(i).getObraId())){
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
		}*/
	}
	

	// CRUD //////////////////////////


	public void lerArquivo() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
		try {
			// VERIFICA SE O ARQUIVO TXT EXISTE E FAZ LEITURA (E CRIA O TXT CASO NEGATIVO)
			arquivo.leArquivo("../MASProject/dados/", "emprestimos");
			//FILTRA E CARREGA O ARRAY COM A BASE DE DADOS DO ARQUIVO TXT
			linha = arquivo.getBuffer();
			String[] listaIngresso = linha.split(";");
			for (String s : listaIngresso) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				/*if (s.contains("---")) {
					AgendamentoMdl emprestimo = new AgendamentoMdl();
					emprestimo.setId(list.get(0));
					emprestimo.setObraId(list.get(1));
					emprestimo.setObra(list.get(2));
					emprestimo.setArtista(list.get(3));
					emprestimo.setDestino(list.get(4));
					emprestimo.setDataInicial(list.get(5));
					emprestimo.setDataFinal(list.get(6));
					emprestimo.setMuseuId(list.get(7));
					emprestimo.setMuseu(list.get(8));
					emprestimo.setResponsavelId(list.get(9));
					emprestimo.setResponsavel(list.get(10));
					emprestimo.setCusto(list.get(11));
					emprestimos.add(emprestimo);
					list.clear();
				}*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void atualizaDados(List<AgendamentoMdl> listemprestimos) {

		//REALIZA A GRAVAÇÃO NO ARQUIVO TXT
		File f = new File("../MASProject/dados/emprestimos");
		f.delete();
		for (AgendamentoMdl emprestimo : listemprestimos) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "emprestimos", "", emprestimo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void pesquisar() {

		//LIMPA O ARRAY PARA RECARREGAR COM OS DADOS ATUAIS DO ARQUIVO TXT
		/*obras.clear();
		lerAcervo();
		ArrayList<EmprestimoObraMdl> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtPesquisa.getText().isEmpty()) {
			for (int i = 0; i < obras.size(); i++) {
				//VERIFICA SE O ID DIGITADO CONFERE COM ALGUM ID CADASTRADO
				if (txtPesquisa.getText().equalsIgnoreCase(obras.get(i).getId())) {
					txtNome.setText(obras.get(i).getNome());
					txtArtista.setText(obras.get(i).getArtista().toString());
					validar = true;
					//VERIFICA OS REGISTROS QUE COMEÇAM COM O TEXTO DIGITADO
				} else if (obras.get(i).getNome().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase())) {
					validar = true;
				}
			}
			if (validar == true) {
				//CARREGA O ARRAY COM OS NOMES INICIADOS COM O TEXTO DIGITADO
				for (int i = 0; i < obras.size(); i++) {
					boolean filtro = obras.get(i).getNome().toLowerCase().startsWith(txtPesquisa.getText().toLowerCase());
					if (filtro == true) {
						EmprestimoObraMdl item = new EmprestimoObraMdl();
						item.setId(obras.get(i).getId());
						item.setNome(obras.get(i).getNome());
						lista.add(item);
					}
				}
				//CARREGADO PARA O ARRAY OS IDs COM OS NOMES QUE COINCIDEM COM A DIGITAÇÃO
				String[] filtro = new String[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					filtro[i] = lista.get(i).getId() + " : " + lista.get(i).getNome();
					pesquisa = lista.get(i).getId();
				}
				if (filtro != null && filtro.length > 1) {
					//INFORMA AO USUÁRIO UMA LISTA DOS ID's DOS USUÁRIOS E PEDE RETORNO
					pesquisa = (String) JOptionPane.showInputDialog(form, "Selecione:\n", "Registros Localizados",
							JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				} 
				if (pesquisa == "0" || pesquisa != null){
					//SE FOR ESCOLHIDO UM ID, PREENCHE O CAMPO COM O NOME
					for (int i = 0; i < obras.size(); i++) {
						if (pesquisa.replaceAll(" : .*", "").equalsIgnoreCase(obras.get(i).getId())) {
							txtNome.setText(obras.get(i).getNome());
							txtArtista.setText(obras.get(i).getArtista().toString());
							txtPesquisa.setText(null);
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
		}*/
	}


	public void gravar() {

			//INICIALIZA VARIÁVEL COM O MODELO
		/*AgendamentoMdl emprestimo = new AgendamentoMdl();
			//VERIFICA SE A TABELA ESTA CARREGADA (SE POSITIVO TRANSFERE PARA O MODELO)
			if (tbAgenda.getRowCount() > 0){
				for(int i = 0; i < tabela.size(); i++){			
					emprestimos.add(tabela.get(i));
					atualizaDados(emprestimos);
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
					&& ftxtDataFinal.getValue() != null 
					&& cbExpo.getSelectedItem().toString() != "Selecione…" 
					&& ftxtCusto.getValue() != null) {
				//PRECORRE O ARRAY PARA ENCONTRAR O NOME DIGITADO NO CAMPO
				for (int i = 0; i < obras.size(); i++) {
					//CARREGA O MODELO COM OS DADOS DOS CAMPOS DA TELA
					if (txtNome.getText().equals(obras.get(i).getNome())) {
						emprestimo.setId(txtId.getText());
						emprestimo.setObraId(obras.get(i).getId());
						emprestimo.setObra(txtNome.getText());
						emprestimo.setArtista(txtArtista.getText());
						emprestimo.setDestino(btgDestino.getSelection().getActionCommand());
						emprestimo.setDataInicial(ftxtData.getText());
						emprestimo.setDataFinal(ftxtDataFinal.getText());
						for (int m = 0; m < exposicoes.size(); m++) { //NECESSÁRIO POIS O ID É DE OUTRA BASE
							if (cbExpo.getSelectedItem().toString().equals(exposicoes.get(m).getNome())) {
								emprestimo.setMuseuId(exposicoes.get(m).getId());
							}
						}
						emprestimo.setMuseu(cbExpo.getSelectedItem().toString());
						emprestimo.setResponsavelId(txtResponsavelId.getText());
						emprestimo.setResponsavel(txtResponsavel.getText());
						emprestimo.setCusto(ftxtCusto.getValue().toString()); 				
						emprestimos.add(emprestimo);
						msg("save", "O ID " + txtId.getText() + " para o " + cbExpo.getSelectedItem().toString());
						//ENVIA A ARRAY DO MODELO CARREGADO PARA ESCRITA NO ARQUIVO TXT
						atualizaDados(emprestimos);
						limpaCampos();
						atualizarId();
						//atualizarTempo();
						validar = true;
					} 
				}
				if (validar == false) {
					msg("errorsave", txtPesquisa.getText());
				}
			} else {
				msg("errornull", txtPesquisa.getText());
			}*/
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
		case "errordate":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO! Erro no campo Data.\n\nPor favor, preencha as datas respeitando o Início e o Fim do período.", 
					"Erro", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
			ftxtData.setValue(null);
			break;
		case "save":
			JOptionPane.showMessageDialog(null, 
					"Registro(s) processado(s)\n\n" + mensagem + "\n\nEmpréstimo registrado com sucesso.", 
					"Confirmação", 
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/confirm.png"));
			break;
		case "saveconfirm":
			Object[] save = { "Confirmar", "Cancelar" };  
			int confirmar = JOptionPane.showOptionDialog(null, "Confirma o processamento do empréstimo " + mensagem 
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
		case "errorsave":
			Object[] options = { "Confirmar", "Cancelar" };  
			int cadastro = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nA obra '" + mensagem 
					+ "' não existe na base de dados!\n\nDeseja realizar o cadastro desta obra no acervo?",
					"Não Localizado", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), options, options[1]);
			if (cadastro == 0) {
				/*try {
					FrmAcervoCad frmCad = new FrmAcervoCad();
					frmCad.setVisible(true);
					frmCad.setLocationRelativeTo(null);
					frmCad.txtObra.setText(txtPesquisa.getText());
				} catch (ParseException e) {
					e.printStackTrace();
				}*/
			}
			break;
		case "novomuseu":
			Object[] novo = { "Confirmar", "Cancelar" };  
			int cadastroMuseu = JOptionPane.showOptionDialog(null, "Gostaria de cadastrar um novo Museu \npara o Empréstimo\n\nID " + mensagem 
					+ " ?",
					"Novo Museu", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/question.png"), novo, novo[1]);
			if (cadastroMuseu == 0) {
				FrmLogin frmCad = new FrmLogin();
				frmCad.setVisible(true);
				frmCad.setLocationRelativeTo(null);
				//frmCad.txtObra.setText(txtPesquisa.getText());
			} else {
				//limpaCampos();
			}
			break;
		case "system":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nChamada para o " + mensagem 
					+ " do sistema!\n\nDeseja encerrar a aplicação?",
					"Fechamento do Programa!", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon("../MASProject/icons/warning.png"), exit, exit[1]);
			if (fechar == 0) {
				validar = true;
			} else {
				validar = false;
			}
			break;		
		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO! Algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nOcorreu no Controller desta Tela.", 
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
				msg("novomuseu",txtId.getText());
			}
		}
	};

	public ActionListener valor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	};

	public ActionListener incluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//atualizarTempo();
			atualizaTabela();
			/*if (tabela.size() > 0) { 
				rdbtnEntrada.setEnabled(false);
				rdbtnSaida.setEnabled(false);
				cbExpo.setEnabled(false);
			}
*/		}
	};
	
	public ActionListener apagar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			removeLinha();
		}
	};
	
	public ActionListener abreCalInicial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				chamaCalendario();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//setFlag(1);
		}
	};

	public ActionListener abreCalFinal = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				chamaCalendario();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//setFlag(2);
		}
	};
	

	public ActionListener cancelar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			tabela.clear();
			((DefaultTableModel) tbAgenda.getModel()).setNumRows(0);
			tbAgenda.updateUI();
			//limpaCampos();
		}
	};

	public ActionListener gravar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//msg("saveconfirm", txtId.getText());
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
			
			if(txtTelefone.hasFocus() || ftxtCusto.hasFocus()){
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