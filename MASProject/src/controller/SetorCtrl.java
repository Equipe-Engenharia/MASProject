package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Setor;
import persistence.SetorArquivo;

public class SetorCtrl {

	private JLabel mensagemGravado, mensagemVazio;
	private JTextField nomeset, idsetor, txtDigiteId, txtDigitadoN;
	private JButton btnGravar,btnGravarEdit, btnPesqIdSet, btnPesqNomSet, btnApagar;
	private Setor setor = new Setor();
	private List <Setor> setores;
	private String validar;
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
	
public void msg(String tipo){
		
		switch (tipo) {

		case "erroVazio":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampo Vazio", "Registro de Setor",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "okPesquisa":
				if (JOptionPane.showConfirmDialog(null, "Registro " + txtDigitadoN.getText() + " localizado com sucesso!\nGostaria de apagá-lo?",
					"Registro de Setor", JOptionPane.OK_CANCEL_OPTION)== JOptionPane.OK_OPTION){
					excluiSetor(txtDigiteId.getText(),txtDigitadoN.getText());
					} else {
						editarSetor(txtDigitadoN.getText());
					}
			break;
		case "noPesquisa":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\n\nNão localizamos o registro: ' " + txtDigiteId.getText()
			+ " " + txtDigitadoN.getText() + " ' !\nVerifique sua digitação.", "Pesquisa de Setores",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;	
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, "ATENÇÃO! Por favor, use um dos campos de Pesquisa!", "Registro de Setore",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "okGrava":
			JOptionPane.showMessageDialog(null, "Registro '" + txtDigitadoN.getText() + "' salvo com sucesso.",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroGrava":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nNão foi possível apagar o registro: " + txtDigiteId.getText()
			+ " " + txtDigitadoN.getText() + "!\nVerifique sua digitação!", "Registro de Setor",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "okApaga":
			JOptionPane.showMessageDialog(null, "Registro apagado com sucesso.",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "teste":
			////////////////////////////////
			JOptionPane.showMessageDialog(null, "TESTE: ", "Teste do Controller Material",
			JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;

		default:
			JOptionPane.showMessageDialog(null,
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nVolte ao trabalho!!!",
					"Erro no Controller Setor", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}

	public void gerarId() {
		// Chamada deste metodo no gravaSetor e no FormRegisSetor
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idsetor.setText("SET" + id);
	}
	
	public void  leSetor(){
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		try {
			arqController.leArquivo("../MASProject/dados/", "setores");
			linha = arqController.getBuffer();
			String[] listaSetor = linha.split(";");
			for (String s : listaSetor) {
				String text = s.replaceAll(".*: ", ""); //ERRO QUE IMPEDE A EXCLUSÃO DO REGISTRO - PRECISA DE 2 ESPAÇOS DEPOIS DO *:
				list.add(text);
				if (s.contains("---")) {
				Setor setor = new Setor();
				    setor.setIdentificacao(list.get(0));
				    setor.setNome(list.get(1));
					setores.add(setor);
					list.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
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
			msg("okGrava");
			txtDigitadoN.setText(null);
			gerarId();
		} else {
			mensagemGravado.setVisible(false);
			mensagemVazio.setVisible(true);
		}
		// implementar a acao de apagar o campo de nome e criar uma nova id
		// quando clicar em gravar
		msg("erroVazio");
	}
	
	public void atualizaDados(List<Setor> listSetor) {
		File f = new File("../MASProject/dados/setores");
		f.delete();
		SetorArquivo setImpl = new SetorArquivo();
		for (Setor setor : listSetor) {
			try {
				setImpl.escreveArquivo("../MASProject/dados/", "setores", "", setor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void limpaCampos() {
		txtDigitadoN.setText(null);
		txtDigiteId.setText(null);	
		//cbCategoria.setSelectedIndex(0);
	}
	public void excluiSetor(String idSet, String nSetor) {
		if (!nomeset.getText().isEmpty() || !idsetor.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (idSet.equalsIgnoreCase(setores.get(i).getIdentificacao())) {
					setores.remove(i);
					validar = "ok";
				} else if ((nSetor.equalsIgnoreCase(setores.get(i).getNome()))) {
					setores.remove(i);
					validar = "ok";
				}
			}
			if (validar == "ok") {
				atualizaDados(setores);
				// msgGravar.setText("Deletado com sucesso");
				// msgGravar.setVisible(true);
				msg("okApaga");
				limpaCampos();
				int delay = 2000; // DELAY 5 SEG
				int interval = 1000; // INTERVALO 1 SEG
				final Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						// msgGravar.setVisible(false);
						timer.cancel();
					}
				}, delay, interval);
			} else {
				validar = "";
				msg("erroApaga");
			}
		} else {
		 msg("erroVazio");
		}
	}
	
	
	public void pesquisarSetor(String nomeSet) {
		if (!txtDigitadoN.getText().isEmpty() || !txtDigiteId.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (nomeSet.equalsIgnoreCase(setores.get(i).getIdentificacao())) {
					validar = "ok";
				} else if (nomeSet.equalsIgnoreCase(setores.get(i).getNome())) {
					validar = "ok";
				}
			}
			if (validar == "ok") {
				// msgGravar.setText(nomeMaterial.getText() + " localizado com
				// sucesso!");
				// msgGravar.setVisible(true);
				msg("okPesquisa");
				//nomeMaterial.setText(null);
			} else {
				// msgGravar.setVisible(false);
				// msgVazio.setText("Por favor, use um dos campos de
				// Pesquisa!");
				// msgVazio.setVisible(true);
				msg("noPesquisa");
//				btApagar.setEnabled(false);
//				btGravar.setEnabled(false);
				validar = "";
			}
		} else {
			msg("erroPesquisa");
		}
	}

	public void editarSetor(String setor){
		Setor s = new Setor();
		if (!txtDigitadoN.getText().isEmpty() || !txtDigiteId.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (setor.equalsIgnoreCase(setores.get(i).getNome())) {
					txtDigiteId.setText(setores.get(i).getIdentificacao().toString());
					txtDigitadoN.setText(setores.get(i).getNome().toString());
					
					s.setIdentificacao(txtDigiteId.getText());
					s.setNome(txtDigitadoN.getText());
					
				}
//				msg(materiais.get(i).toString());
//				materiais.get(i).setId(m.getId());
//				materiais.get(i).setNome(m.getNome());
//				materiais.get(i).setCategoria(m.getCategoria());
			}
			
			//msg("teste");
			//limpaCampos();
			int delay = 2000; // DELAY 5 SEG
			int interval = 1000; // INTERVALO 1 SEG
			final Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					// msgGravar.setVisible(false);
					timer.cancel();
				}
			}, delay, interval);
			atualizaDados(setores);
			
		}
	}
	
	
	public ActionListener gravarSetor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravaSetor();
			nomeset.setText(null);
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

			// para que a mensagem n�o fique visivel a todo momento
			btnGravar.setEnabled(true);
			mensagemGravado.setVisible(false);
			mensagemVazio.setVisible(false);
		}
	};
	
	
	//Tela de edi��o
	
	
	 
	 public ActionListener pesquisaSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			pesquisarSetor(txtDigitadoN.getText());
		}
	};

	

	public ActionListener gravarAlteracoesSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravaSetor();
		}
	};

	public ActionListener excluirSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			excluiSetor(txtDigiteId.getText(), txtDigitadoN.getText());
		}
	};
	 

}
