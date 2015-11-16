package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.LoginMdl;
import model.SessionMdl;
import persistence.SessionFile;

public class SessionCtrl {

	private List<SessionMdl> logon;
	private SessionFile session = new SessionFile();

	public SessionCtrl () {

		new ArrayList<LoginMdl>();
		this.logon = new ArrayList<SessionMdl>();

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
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nVolte ao trabalho duro e conserte isso!!!", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}
	

	// CRUD //////////////////////////
	
	
public void lerSession() {
		
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();
	
		try {
			session.leArquivo("../MASProject/dados/", "session");
			linha = session.getBuffer();
			String[] listaSession = linha.split(";");
			for (String s : listaSession) {
				String text = s.replaceAll(".*: ", "");
				list.add(text);
				if (s.contains("---")) {
					SessionMdl log = new SessionMdl();
					log.setId(list.get(0));
					log.setUsuario(list.get(1));
					log.setNivel(list.get(2));
					log.setHora(list.get(3));
					logon.add(log);
					list.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void atualizaSession(List<SessionMdl> lista) {
		
		File f = new File("../MASProject/dados/session");
		f.delete();	
		for (SessionMdl logon : lista) {
			try {
				session.escreveArquivo("../MASProject/dados/", "session", "", logon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	public String registrar(String id, String usuario, String acesso) {
		
		SessionMdl log = new SessionMdl();
		Date date = new Date();

					logon.clear(); //APAGA A ULTIMA SESSAO
					log.setId(id);
					log.setUsuario(usuario);
					log.setNivel(acesso);
					log.setHora(date.toString());
					logon.add(log);
					atualizaSession(logon);
					return logon.get(0).getNivel();	
	}
	
	
	public boolean acesso() {

			lerSession();
			boolean open;
			if (("Administrativo").equalsIgnoreCase(logon.get(0).getNivel())){
				open = true;
			} else {
				msg("errorsession", logon.get(0).getNivel());
				open = false;
			}
			return open;
		} 
}
