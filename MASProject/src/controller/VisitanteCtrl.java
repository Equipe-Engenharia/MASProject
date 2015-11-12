package controller;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Visitante;
import persistence.VisitanteArquivo;

public class VisitanteCtrl {
	private boolean validar;
	private List<Visitante> visitantes;
	private JTextField txtNome, txtDataNasc;
	//private JComboBox<String> cbContinente, cbNacionali;
	private JRadioButton rdbtnMasculino, rdbtnFeminino;
	private JCheckBox checkPT, checkING, checkESP;

	public VisitanteCtrl(JTextField txtNome, JTextField txtDataNasc, JComboBox<String> cbNacionali,
			JRadioButton rdbtnMasculino, JRadioButton rdbtnFeminino, JCheckBox checkING, JCheckBox checkPT,
			JCheckBox checkESP) {
		this.txtNome = txtNome;
		this.txtDataNasc = txtDataNasc;
		//
		this.rdbtnMasculino = rdbtnMasculino;
		this.rdbtnFeminino = rdbtnFeminino;
		this.checkING = checkING;
		this.checkPT = checkPT;
		this.checkESP = checkESP;
		
	}
	
	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, 
					"ATENÇÃO!\nCampo Vazio.\nPor favor, digite o nome do Visitante.", 
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
					"ATENÇÃO!\nNão foi possível apagar o registro: " +txtNome.getText() + "!\nVerifique sua digitação!", 
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

	public void gravar() {
		new VisitanteArquivo();
		Visitante visita = new Visitante();
		validar = false;
		if (!txtNome.getText().isEmpty()) {
			for (int i = 0; i < visitantes.size(); i++) {
				if (txtNome.getText().equalsIgnoreCase(visitantes.get(i).getNome())){
					//msg("erroredit", visitantes.get(i).getNome());
					validar = true;
				}
			}
			if(!(validar == true)){	
				visita.setNome(txtNome.getText());
				visita.setDataNasc(txtDataNasc.getText());
				//visita.setNacionalidade();
				if(rdbtnMasculino.isSelected()){
					visita.setSexo(rdbtnMasculino.getText());
				}else if(rdbtnFeminino.isSelected()){
					visita.setSexo(rdbtnFeminino.getText());
				}
				
				if(checkING.isSelected()){
					visita.setIdiomas(checkING.getText());
				}else if(checkPT.isSelected()){
					visita.setIdiomas(checkPT.getText());
				}else if(checkESP.isSelected()){
					visita.setIdiomas(checkESP.getText());
				}
				visitantes.add(visita);
				msg("save", txtNome.getText());
				//atualizaDados(visitantes);
			}
		} else {
			msg("errornull", txtNome.getText());
		}
	}
	
	public ActionListener gravarVisita = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravar();
		}
	};
}
