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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Setor;
import persistence.SetorArquivo;

public class SetorCtrl {
   private JPanel frmSetor;
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
		
		leSetor();
	}
	
	public SetorCtrl(JTextField txtDigiteId, JTextField txtDigitadoN, JButton btnPesqIdSet, JButton btnPesqNomSet,
			JButton btnApagar, JButton btnGravarEdit, JPanel contentPane){
		this.txtDigiteId = txtDigiteId;
		this.txtDigitadoN = txtDigitadoN;
		this.btnPesqIdSet = btnPesqIdSet;
		this.btnPesqNomSet = btnPesqNomSet;
		this.btnApagar = btnApagar;
		this.btnGravarEdit = btnGravarEdit;
		this.frmSetor = contentPane;
		
	}
	
	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Setor.",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "cancelsearch":
			// JOptionPane.showMessageDialog(null, "ATENÇÃO! Cancelando sua
			// pesquisa!", "Registro de Material",
			// JOptionPane.PLAIN_MESSAGE, new
			// ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "nosearch":
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\n\nNão localizamos o registro: '" + mensagem + "' !\nVerifique sua digitação.",
					"Pesquisa de Setor", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "errorsearch":
			JOptionPane.showMessageDialog(null, "ATENÇÃO! Por favor, digite para pesquisar!", "Registro de Setor",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "save":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' salvo com sucesso.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "errorrec":
			JOptionPane.showMessageDialog(null,
					"ATENÇÃO!\nNão foi possível apagar o registro: " + txtDigiteId.getText() + " "
							+ txtDigitadoN.getText() + "!\nVerifique sua digitação!",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "edit":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' editado com sucesso.",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "delete":
			JOptionPane.showMessageDialog(null, "Registro '" + mensagem + "' apagado com sucesso.",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		default:
			JOptionPane.showMessageDialog(null,
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\n" + mensagem
							+ "\n\nVolte ao trabalho e conserte isso!!!",
					"Erro no Controller Material", JOptionPane.PLAIN_MESSAGE,
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

	public void gravaSetor(String pesquisa) {
		Setor setor = new Setor();
	    new SetorArquivo();

		setor.setIdentificacao(idsetor.getText());
		setor.setNome(nomeset.getText());

		if (!txtDigitadoN.getText().isEmpty()) {
			setor.setIdentificacao(txtDigiteId.getText());
			setor.setNome(txtDigitadoN.getText());
			setores.add(setor);
			msg("save", pesquisa);
			atualizaDados(setores);
			txtDigitadoN.setText(null);
			gerarId();
		} else {
			msg("errornull", pesquisa);
		}
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
	
	public void excluiSetor(String pesquisa) {
		if (!txtDigiteId.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (pesquisa.equalsIgnoreCase(setores.get(i).getIdentificacao())) {
					setores.remove(i);
					validar = "ok";
				}
			}
			if (validar == "ok") {
				atualizaDados(setores);
				msg("delete", pesquisa);
				limpaCampos();
			} else {
				validar = "";
				msg("erroApaga", pesquisa);
			}
		} else {
			pesquisarSetor(pesquisa);
		}
	}
	
	
	public void pesquisarSetor(String pesquisa) {
		ArrayList<Setor> listSetor = new ArrayList<>();
		List<Setor> dados = setores;

		if (!txtDigitadoN.getText().isEmpty() || !txtDigiteId.getText().isEmpty()) {

			for (int i = 0; i < setores.size(); i++) {
				if (pesquisa.equalsIgnoreCase(setores.get(i).getIdentificacao())) {
					txtDigiteId.setText(setores.get(i).getIdentificacao());
					txtDigitadoN.setText(setores.get(i).getNome());
					validar = "id";
				} else if (pesquisa.equalsIgnoreCase(setores.get(i).getNome())) {
					validar = "nome";
				}
			}
			if (validar == "nome") {
				for (int i = 0; i < dados.size(); i++) {

					boolean filtro = pesquisa.equalsIgnoreCase(setores.get(i).getNome());

					if (filtro == true) {
                        Setor s = new Setor();

						s.setIdentificacao(setores.get(i).getIdentificacao());
						s.setNome(setores.get(i).getNome());
						listSetor.add(s);
					}
				}
				String[] filtro = new String[listSetor.size()];
				for (int i = 0; i < listSetor.size(); i++) {
					filtro[i] = listSetor.get(i).getIdentificacao();
				}
				String s = (String) JOptionPane.showInputDialog(frmSetor, "Escolha o ID:\n", "Selecione o ID",
						JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				if (s != null && s.length() > 0) {
					for (int i = 0; i < setores.size(); i++) {
						if (s.equalsIgnoreCase(setores.get(i).getIdentificacao())) {
							txtDigiteId.setText(setores.get(i).getIdentificacao());
							txtDigitadoN.setText(setores.get(i).getNome());
						}
					}
					validar = "";
				} else {
					msg("cancelsearch", "");
				}
			} else {
				validar = "";
				if (validar != "id") {
					msg("nosearch", pesquisa);
				}
			}

		} else {
			msg("errorsearch", pesquisa);
		}
	}

	public void editarSetor(String pesquisa) {
		Setor setor = new Setor();
		if (!txtDigiteId.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (pesquisa.equalsIgnoreCase(setores.get(i).getIdentificacao())) {
					setor.setIdentificacao(txtDigiteId.getText());
					setor.setNome(txtDigitadoN.getText());
					setores.set(i, setor);
					atualizaDados(setores);
					msg("edit", pesquisa);
					limpaCampos();
				}
			}
		} else {
			msg("errorsearch", pesquisa);
		}
	}
	
	
	public ActionListener gravarSetor = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gravaSetor(txtDigiteId.getText());
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
			editarSetor(txtDigiteId.getText());
		}
	};

	public ActionListener excluirSetor = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (txtDigiteId.getText().isEmpty()) {
				excluiSetor(txtDigitadoN.getText());
			} else {
				excluiSetor(txtDigiteId.getText());
			}
		}
	};
	 

}
