package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Setor;

public class RegisSetorController {

	private JLabel mensagemGravado, mensagemVazio;
	private JTextField nomeset, idsetor;
	private JButton btnGravar;
	private Setor setor = new Setor();
	private static int contador = 1;
	private String registro_set[] = new String[2];
	private ArquivosController arqController = new ArquivosController();

	public RegisSetorController(JTextField id_setor, JTextField nomeDigit, JLabel mensagemGravado, JLabel mensagemVazio, JButton btnGravar) {
		this.idsetor = id_setor;
		this.mensagemGravado = mensagemGravado;
		this.mensagemVazio = mensagemVazio;
		this.nomeset = nomeDigit;
		this.btnGravar = btnGravar;
	}

	public void gravaSetor() {
		
		// Falta implementar
		setor.setIdentificacao(idsetor.getText());
		setor.setNome(nomeset.getText());
		
		
		//se o campo não estiver vazio
		//se o campo nï¿½o estiver vazio
		if (!nomeset.getText().isEmpty()) {
			try {
				arqController.escreveArquivo("../MASProject/dados/", "setores", nomeset.getText(), setor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mensagemGravado.setText(nomeset.getText()+" salvo com sucesso!!!");
			mensagemGravado.setVisible(true);
		}else{
			mensagemGravado.setVisible(false);
			mensagemVazio.setVisible(true);
		}
		// implementar a acao de apagar o campo de nome e criar uma nova id
		// quando clicar em gravar
	}
	
	public void excluiSetor(){
		//teste
		registro_set[1] = setor.getNome();
		registro_set[0] = setor.getIdentificacao();
		
		try {
			arqController.excluiDadosArquivo("../MASProject/dados", "setores", registro_set);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ActionListener gravarSetor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravaSetor();
			nomeset.setText("");
		}
	};
	
public ActionListener excluiSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			excluiSetor();
		}
	};

	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			//se for clicado pela primeira vez o campo fica limpo para preencher com o nome do setor
			if (contador == 1) {
				nomeset.setText("");
				contador += 1;
			}
			//para que a mensagem nï¿½o fique visivel a todo momento
			btnGravar.setEnabled(true);
			mensagemGravado.setVisible(false);
            mensagemVazio.setVisible(false);
		}
	};
	
	

}
