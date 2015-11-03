package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Setor;
import persistence.SetorArquivo;

public class SetorCtrl implements ComponentListener {

//	private JLabel mensagemGravado, mensagemVazio; //SUBISTITUIDO POR JOPTION PANE
//	private JButton btnGravar;
	private JPanel frmSetor;
	private JTextField idSetor, nomeSetor;
	private List <Setor> setores;
	private static int contador = 1;
	private String validar;
	private ArquivosCtrl ctrlArquivos = new ArquivosCtrl();
	private Setor setor = new Setor();
	

	public SetorCtrl(JPanel frmSetor, JTextField idSetor, JTextField txtSetor) {  // JLabel mensagemGravado, JLabel mensagemVazio,JButton btnGravar) {
		this.idSetor = idSetor;
//		this.mensagemGravado = mensagemGravado; //SUBISTITUIDO POR JOPTION PANE
//		this.mensagemVazio = mensagemVazio;
//		this.btnGravar = btnGravar;
		this.nomeSetor = txtSetor;
		this.setores = new ArrayList<Setor>();
	
		lerSetor();
	}
	
	
	// METODOS DE SUPORTE ////////////////////////
	
	public void gerarId() {
		// Chamada deste metodo no gravaSetor e no FormRegisSetor
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idSetor.setText("SET" + id);
	}
	
	public void limpaCampos() {
		nomeSetor.setText(null);
		idSetor.setText(null);	
	}
	
	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errornull":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampo Vazio.\nPor favor, digite o ID ou nome do Setor.",
					"Registro de Setor", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "cancelsearch":
			// JOptionPane.showMessageDialog(null, "ATENÇÃO! Cancelando sua pesquisa!", "Registro de Material",
			// JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
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
					"ATENÇÃO!\nNão foi possível apagar o registro: " + idSetor.getText() + " "
							+ nomeSetor.getText() + "!\nVerifique sua digitação!",
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

	
	// CRUD //////////////////////////	
	
	public void  lerSetor(){
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "setores");
			linha = ctrlArquivos.getBuffer();
			String[] listaSetor = linha.split(";");
			for (String s : listaSetor) {
				String text = s.replaceAll(".*: ", ""); //ERRO QUE IMPEDE A EXCLUSÃO DO REGISTRO - PRECISA DE 2 ESPAÇOS DEPOIS DO *:
				list.add(text);
				if (s.contains("---")) {
					Setor setor = new Setor();
					setor.setId(list.get(0));
					setor.setNome(list.get(1));
					setores.add(setor);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public void pesquisarSetor(String pesquisa) {
		ArrayList<Setor> listSetor = new ArrayList<>();
		List<Setor> dados = setores;

		if (!nomeSetor.getText().isEmpty() || !idSetor.getText().isEmpty()) {

			for (int i = 0; i < setores.size(); i++) {
				if (pesquisa.equalsIgnoreCase(setores.get(i).getId())) {
					idSetor.setText(setores.get(i).getId());
					nomeSetor.setText(setores.get(i).getNome());
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

						s.setId(setores.get(i).getId());
						s.setNome(setores.get(i).getNome());
						listSetor.add(s);
					}
				}
				String[] filtro = new String[listSetor.size()];
				for (int i = 0; i < listSetor.size(); i++) {
					filtro[i] = listSetor.get(i).getId();
				}
				String s = (String) JOptionPane.showInputDialog(frmSetor, "Escolha o ID:\n", "Selecione o ID",
						JOptionPane.INFORMATION_MESSAGE, null, filtro, filtro[0]);
				if (s != null && s.length() > 0) {
					for (int i = 0; i < setores.size(); i++) {
						if (s.equalsIgnoreCase(setores.get(i).getId())) {
							idSetor.setText(setores.get(i).getId());
							nomeSetor.setText(setores.get(i).getNome());
						}
					}
					validar = "";
				} else {
					msg("cancelsearch", "");
				}
			} else {
				if (validar != "id") {
					msg("nosearch", pesquisa);
					validar = "";
				}
			}
		} else {
			msg("errorsearch", pesquisa);
		}
	}

	public void editarSetor(String pesquisa) {
		if (!idSetor.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (pesquisa.equalsIgnoreCase(setores.get(i).getId())) {
					setor.setId(idSetor.getText());
					setor.setNome(nomeSetor.getText());
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
	
	public void excluirSetor(String pesquisa) {
		if (!idSetor.getText().isEmpty()) {
			for (int i = 0; i < setores.size(); i++) {
				if (pesquisa.equalsIgnoreCase(setores.get(i).getId())) {
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

	public void gravarSetor(String pesquisa) {
	    new SetorArquivo();

		if (!nomeSetor.getText().isEmpty()) {
			setor.setId(idSetor.getText());
			setor.setNome(nomeSetor.getText());
			setores.add(setor);
			msg("save", pesquisa);
			atualizaDados(setores);
			nomeSetor.setText(null);
			gerarId();
		} else {
			msg("errornull", pesquisa);
		}
	}
	
	// CONTROLE BOTAO //////////////////////////////	
	
	public ActionListener pesquisarSetor = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarSetor(nomeSetor.getText());
		}
	};
	
	public ActionListener excluirSetor = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (idSetor.getText().isEmpty()) {
				excluirSetor(nomeSetor.getText());
			} else {
				excluirSetor(idSetor.getText());
			}
		}
	};
	
	public ActionListener editarSetor = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			editarSetor(idSetor.getText());
		}
	};
	
	public ActionListener gravarSetor = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gravarSetor(idSetor.getText());
		}
	};
	
	public ActionListener fecharTela = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

	public KeyListener ativaGravar = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			if (!nomeSetor.getText().isEmpty()) {
//				btnGravar.setEnabled(true);
			} else {
//				btnGravar.setEnabled(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
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
			// se for clicado pela primeira vez o campo fica limpo para
			// preencher com o nome do setor
			// para que a mensagem não fique visivel a todo momento
			if (contador == 1) {
				nomeSetor.setText(null);
				contador += 1;
			}
//			btnGravar.setEnabled(true); //SUBISTITUIDO POR JOPTION PANE
//			mensagemGravado.setVisible(false);
//			mensagemVazio.setVisible(false);
		}
	};

	@Override
	public void componentResized(ComponentEvent e) {

	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}
