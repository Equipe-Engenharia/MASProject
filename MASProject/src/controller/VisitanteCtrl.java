package controller;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Visitante;
import persistence.VisitanteArquivo;

public class VisitanteCtrl implements ComponentListener {
	
	private JPanel form;
	private JTextField txtId, txtNome, txtDataNasc;
	private JComboBox<String> cbNacional;
	private JRadioButton rdbtnMasculino, rdbtnFeminino;
	private JCheckBox checkPT, checkING, checkESP;
	private List<Visitante> visitantes;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivo = new ArquivosCtrl();
	private VisitanteArquivo formatar = new VisitanteArquivo();

	public VisitanteCtrl(JPanel form, JTextField txtId, JTextField txtNome, JTextField txtDataNasc, JComboBox<String> cbNacional,
			JRadioButton rdbtnMasculino, JRadioButton rdbtnFeminino, JCheckBox checkING, JCheckBox checkPT,
			JCheckBox checkESP) {
		
		this.txtId = txtId;
		this.txtNome = txtNome;
		this.txtDataNasc = txtDataNasc;
		this.cbNacional = cbNacional;
		this.rdbtnMasculino = rdbtnMasculino;
		this.rdbtnFeminino = rdbtnFeminino;
		this.checkING = checkING;
		this.checkPT = checkPT;
		this.checkESP = checkESP;
		this.visitantes = new ArrayList<Visitante>();
		
		lerArquivo();
	}

	// METODOS DE SUPORTE ////////////////////////

	public void gerarId() { // USO DESTE METODO NO GRAVARMATERIAL E FRMMATERIAL (CAD E EDIT)
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtId.setText("VST" + NewId);
	}

	public void limpaCampos() {
		txtId.setText(null);
		txtNome.setText(null);
		txtDataNasc.setText(null);
		cbNacional.setSelectedIndex(0);
		rdbtnMasculino.setSelected(false);
		rdbtnFeminino.setSelected(false);
		checkING.setSelected(false);
		checkPT.setSelected(false);
		checkESP.setSelected(false);	
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
					+ txtNome.getText() + "!\nVerifique sua digitação!", 
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

	public void preencherComboBoxNacional() {
		String linha = new String();
		arquivo = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		try {
			arquivo.leArquivo("../MASProject/dados/", "nacionalidades");
			linha = arquivo.getBuffer();
			String[] nacionalidades = linha.split(";");
			for (String s : nacionalidades) {
				cbNacional.addItem(s);
				listString.clear();
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
			arquivo.leArquivo("../MASProject/dados/", "visitante");
			linha = arquivo.getBuffer();
			String[] listaVisitante = linha.split(";");
			for (String s : listaVisitante) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					Visitante visitante = new Visitante();
					visitante.setId(list.get(0));
					visitante.setNome(list.get(1));
					visitante.setDataNasc(list.get(2));
					visitante.setNacionalidade(list.get(3));
					visitante.setSexo(list.get(4));
					visitante.setIdiomas(list.get(5));
					visitantes.add(visitante);				//PROBLEMA AO ADCIONAR NA LISTA - IMPEDE O FUNCIONAMENTO DO CRUD
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizaDados(List<Visitante> listVisitantes) {
		File f = new File("../MASProject/dados/visitante");
		f.delete();
		for (Visitante visitante : listVisitantes) {
			try {
				formatar.escreveArquivo("../MASProject/dados/", "visitante", "", visitante);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisar() {

		ArrayList<Visitante> lista = new ArrayList<>();
		String pesquisa ="";
		if (!txtNome.getText().isEmpty() || !txtId.getText().isEmpty()) {

			for (int i = 0; i < visitantes.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(visitantes.get(i).getId())) {
					txtId.setText(visitantes.get(i).getId());
					txtNome.setText(visitantes.get(i).getNome());
					cbNacional.getModel().setSelectedItem(visitantes.get(i).getNacionalidade());
					validar = true;
				} else if (txtNome.getText().equalsIgnoreCase(visitantes.get(i).getNome())) {
					validar = true;
					cbNacional.getModel().setSelectedItem(visitantes.get(i).getNacionalidade());
				}
			}
			if (validar == true) {
				for (int i = 0; i < visitantes.size(); i++) {
					boolean filtro = txtNome.getText().equalsIgnoreCase(visitantes.get(i).getNome());
					if (filtro == true) {
						Visitante item = new Visitante();
						item.setId(visitantes.get(i).getId());
						item.setNome(visitantes.get(i).getNome());
						item.setNacionalidade(visitantes.get(i).getNacionalidade());
						if(visitantes.get(i).getSexo() == "Masculino"){
							item.setSexo(visitantes.get(i).getSexo());
						}else if(visitantes.get(i).getSexo() == "Feminino"){
							item.setSexo(visitantes.get(i).getSexo());
						}
						if(visitantes.get(i).getIdiomas() == "Ingles"){
							item.setIdiomas(visitantes.get(i).getIdiomas());
						}else if(visitantes.get(i).getIdiomas() == "Portugues"){
							item.setIdiomas(visitantes.get(i).getIdiomas());
						}else if(visitantes.get(i).getIdiomas() == "Espanhol"){
							item.setIdiomas(visitantes.get(i).getIdiomas());
						}
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
						txtId.setText(visitantes.get(i).getId());
						txtNome.setText(visitantes.get(i).getNome());
						cbNacional.getModel().setSelectedItem(visitantes.get(i).getNacionalidade());;
						rdbtnMasculino.isSelected();
						rdbtnFeminino.isSelected();
						checkING.isSelected();
						checkPT.isSelected();
						checkESP.isSelected();
					}
				}
				}
				validar = false;
			} else {
				if (pesquisa == "") {
					msg("nosearch", txtNome.getText());
					limpaCampos();
				}
				validar = false;
			}
		} else {
			msg("errorsearch", txtNome.getText());
		}
	}

	public void editar() {
		Visitante visitante = new Visitante();
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (!txtId.getText().equalsIgnoreCase(visitantes.get(i).getId()) 
						&& txtNome.getText().equalsIgnoreCase(visitantes.get(i).getNome())
						&& cbNacional.getSelectedItem().toString().equalsIgnoreCase(visitantes.get(i).getNacionalidade())) {
					msg("erroredit", visitantes.get(i).getNome());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < visitantes.size(); i++) {
					if (txtId.getText().equalsIgnoreCase(visitantes.get(i).getId())) {
						visitante.setId(txtId.getText());
						visitante.setNome(txtNome.getText());
						visitante.setDataNasc(txtDataNasc.getText());
						visitante.setNacionalidade(cbNacional.getSelectedItem().toString());
						if(rdbtnMasculino.isSelected()){
							visitante.setSexo(rdbtnMasculino.getText());
						}else if(rdbtnFeminino.isSelected()){
							visitante.setSexo(rdbtnFeminino.getText());
						}
						if(checkING.isSelected()){
							visitante.setIdiomas(checkING.getText());
						}else if(checkPT.isSelected()){
							visitante.setIdiomas(checkPT.getText());
						}else if(checkESP.isSelected()){
							visitante.setIdiomas(checkESP.getText());
						}
						visitantes.set(i, visitante);
						atualizaDados(visitantes);
						msg("edit", txtNome.getText());
						limpaCampos();	
					}
				}
			}
		} else {
			msg("errorsearch", txtNome.getText());
		}
	}

	public void excluir() {
		validar = false;
		if (!txtId.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (txtId.getText().equalsIgnoreCase(visitantes.get(i).getId()) 
						&& txtNome.getText().equalsIgnoreCase(visitantes.get(i).getNome())
						&& cbNacional.getSelectedItem().toString().equalsIgnoreCase(visitantes.get(i).getNacionalidade())) {
					visitantes.remove(i);
					validar = true;
				} else {
					if(i == visitantes.size()){
						msg("errordelete", visitantes.get(i).getNome());
					}
				}
			}
			if (validar == true) {
				msg("deleteconfirm", txtNome.getText());
				if (validar == false){
					atualizaDados(visitantes);
					msg("delete", txtNome.getText());
					limpaCampos();
				} else {
					visitantes.clear();
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
		new VisitanteArquivo();
		Visitante visitante = new Visitante();
		validar = false;
		if (!txtNome.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {	
				if (txtNome.getText().equalsIgnoreCase(visitantes.get(i).getNome()) && cbNacional.getSelectedItem().toString().equalsIgnoreCase(visitantes.get(i).getNacionalidade())) {
					msg("erroredit", visitantes.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){
				visitante.setId(txtId.getText());
				visitante.setNome(txtNome.getText());
				visitante.setDataNasc(txtDataNasc.getText());
				visitante.setNacionalidade(cbNacional.getSelectedItem().toString());
				if(rdbtnMasculino.isSelected()){
					visitante.setSexo(rdbtnMasculino.getText());
				}else if(rdbtnFeminino.isSelected()){
					visitante.setSexo(rdbtnFeminino.getText());
				}
				if(checkING.isSelected()){
					visitante.setIdiomas(checkING.getText());
				}else if(checkPT.isSelected()){
					visitante.setIdiomas(checkPT.getText());
				}else if(checkESP.isSelected()){
					visitante.setIdiomas(checkESP.getText());
				}
				visitantes.add(visitante);
				msg("save", txtNome.getText());
				atualizaDados(visitantes);
				limpaCampos();
				gerarId();
			}
		} else {
			msg("errornull", txtNome.getText());
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

	public ActionListener gravar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravar();
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
				txtNome.setText(null);
				contador += 1;
			}
		}
	};

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
