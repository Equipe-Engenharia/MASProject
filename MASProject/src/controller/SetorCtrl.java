package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Setor;
import persistence.SetorArquivo;

public class SetorCtrl {

	private JLabel mensagemGravado, mensagemVazio;
	private JTextField nomeset, idsetor, txtDigiteId, txtDigitadoN;
	private JButton btnGravar,btnGravarEdit, btnPesqIdSet, btnPesqNomSet, btnApagar;
	private Setor setor = new Setor();
	private ArquivosCtrl arqController = new ArquivosCtrl();

	public SetorCtrl(JTextField id_setor, JTextField nomeDigit, JLabel mensagemGravado, JLabel mensagemVazio,
			JButton btnGravar) {
		this.idsetor = id_setor;
		this.mensagemGravado = mensagemGravado;
		this.mensagemVazio = mensagemVazio;
		this.nomeset = nomeDigit;
		this.btnGravar = btnGravar;
	}
	
	public SetorCtrl(JTextField txtDigiteId, JTextField txtDigitadoN, JButton btnPesqIdSet, JButton btnPesqNomSet,
			JButton btnApagar, JButton btnGravarEdit){
		this.txtDigiteId = txtDigiteId;
		this.txtDigitadoN = txtDigitadoN;
		this.btnPesqIdSet = btnPesqIdSet;
		this.btnPesqNomSet = btnPesqNomSet;
		this.btnApagar = btnApagar;
		this.btnGravarEdit = btnGravarEdit;
	}

	public void gerarIdSetor() {
		// Chamada deste metodo no gravaSetor e no FormRegisSetor
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idsetor.setText("SET" + id);
	}

	public void gravaSetor() {
		Setor setor = new Setor();
		SetorArquivo setorImpl = new SetorArquivo();

		setor.setIdentificacao(idsetor.getText());
		setor.setNome(nomeset.getText());

		if (!nomeset.getText().isEmpty()) {
			try {
				setorImpl.escreveArquivo("../MASProject/dados/", "setores", nomeset.getText(), setor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mensagemGravado.setText(nomeset.getText() + " salvo com sucesso!!!");
			mensagemGravado.setVisible(true);
			gerarIdSetor();
		} else {
			mensagemGravado.setVisible(false);
			mensagemVazio.setVisible(true);
		}
		// implementar a acao de apagar o campo de nome e criar uma nova id
		// quando clicar em gravar
	}

	public void excluiSetor() {

		// try {
		// arqController.excluiDadosArquivo("../MASProject/dados", "setores",
		// registro_set);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public ActionListener gravarSetor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			gravaSetor();
			nomeset.setText(null);
		}
	};

	public ActionListener excluiSetor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			excluiSetor();
		}
	};

	public ActionListener fecharTela = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	};

	public KeyListener ativaGravar = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if (!nomeset.getText().isEmpty()) {
				btnGravar.setEnabled(true);
			} else {
				btnGravar.setEnabled(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

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
			// se for clicado pela primeira vez o campo fica limpo para
			// preencher com o nome do setor

			// para que a mensagem nï¿½o fique visivel a todo momento
			btnGravar.setEnabled(true);
			mensagemGravado.setVisible(false);
			mensagemVazio.setVisible(false);
		}
	};
	
	
	//Tela de edição
	
	
	public void pesquisaIDSetor(){
		 
	 }
	 
	 public void pesquisaNomeSetor(){
		 
	 }
	 
	 public void gravarAlteracoesSetor(){
		 
	 }
	 
	 public void excluirSetor(){
		 
	 }
	 
	 public ActionListener pesquisaIDSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pesquisaIDSetor();
		}
	};

	public ActionListener pesquisaNomeSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pesquisaNomeSetor();
		}
	};

	public ActionListener gravarAlteracoesSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravarAlteracoesSetor();
		}
	};

	public ActionListener excluirSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			excluirSetor();
		}
	};
	 

}
