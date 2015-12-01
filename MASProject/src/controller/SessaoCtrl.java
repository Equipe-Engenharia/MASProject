package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.UsuarioMdl;
import model.SessaoMdl;
import persistence.SessaoFile;

public class SessaoCtrl {

	// Variável estática que conterá a instancia da classe
	private static SessaoCtrl instance;
	
	private List<SessaoMdl> logon;
	private SessaoFile sessao = new SessaoFile();

	// Construtor privado (suprime o construtor público padrão).
	private SessaoCtrl() {
		
		new ArrayList<UsuarioMdl>();
		this.setLogon(new ArrayList<SessaoMdl>());
		
		lerSession();
	}

	// Método público estático de acesso único ao objeto!
	public static SessaoCtrl getInstance() {
		if (instance == null)
			instance = new SessaoCtrl();
		return instance;
	}

	
	// METODOS DE SUPORTE ////////////////////////

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errorsession":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, solicite a autorização de um administrador.", 
					"Bloqueado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;

		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO! Algo não deveria ter acontecido…\n\nSessaoCtrl - Termo: " + mensagem
					+ "\n\nOcorreu no Controller desta Tela.", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
		}
	}

public void lerSession() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
	
		try {
			sessao.leArquivo("../MASProject/dados/", "log");
			linha = sessao.getBuffer();
			String[] listaSession = linha.split(";");
			for (String s : listaSession) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					SessaoMdl log = new SessaoMdl();
					log.setId(list.get(0));
					log.setUsuario(list.get(1));
					log.setNivel(list.get(2));
					log.setHora(list.get(3));
					getLogon().add(log);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void atualizaSession(List<SessaoMdl> lista) {
		
		File f = new File("../MASProject/dados/log");
		f.delete();	
		for (SessaoMdl logon : lista) {
			try {
				sessao.escreveArquivo("../MASProject/dados/", "log", "", logon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public String registrar(String id, String usuario, String acesso, String tela) {
		
		SessaoMdl log = new SessaoMdl();
		Date date = new Date();

					getLogon().clear(); //APAGA A ULTIMA SESSAO
					log.setId(id);
					log.setUsuario(usuario);
					log.setNivel(acesso);
					log.setHora(date.toString());
					log.setTela(tela);
					getLogon().add(log);
					atualizaSession(getLogon());
					return getLogon().get(0).getNivel();	
	}
	
	
	public boolean acesso() {

			lerSession();
			boolean open;
			if (("Administrativo").equalsIgnoreCase(getLogon().get(0).getNivel())){
				open = true;
			} else {
				//msg("errorsession", logon.get(0).getNivel());
				open = false;
			}
			return open;
		}

	public List<SessaoMdl> getLogon() {
		return logon;
	}

	public void setLogon(List<SessaoMdl> logon) {
		this.logon = logon;
	} 
	
}
