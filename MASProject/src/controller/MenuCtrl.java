package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import view.FrmAcervoCad;
import view.FrmAcervoEdit;
import view.FrmArtistaCad;
import view.FrmArtistaEdit;
import view.FrmCategoriaCad;
import view.FrmCategoriaEdit;
import view.FrmEmprestimo;
import view.FrmExposicaoCad;
import view.FrmExposicaoEdit;
import view.FrmIngresso;
import view.FrmLogin;
import view.FrmMaterialCad;
import view.FrmMaterialEdit;
import view.FrmSetorCad;
import view.FrmSetorEdit;
import view.FrmUsuario;
import view.FrmVisitanteCad;
import view.FrmVisitanteEdit;

public class MenuCtrl implements ComponentListener{

	private JDesktopPane desktop;
	private boolean validar;
	SessaoCtrl log = SessaoCtrl.getInstance();


	public MenuCtrl(JDesktopPane desktop){

		this.desktop = desktop;
		
		limpaSessao();
		login();
		
	}

	public void limpaSessao() {
		SessaoCtrl log = SessaoCtrl.getInstance();

		log.registrar("000", "000", "000", "MENU");
	}

	public void sessao() {

		if (("Operacional").equalsIgnoreCase(log.getLogon().get(0).getNivel()) ||
				("Administrativo").equalsIgnoreCase(log.getLogon().get(0).getNivel())
				){

			log.registrar(
					log.getLogon().get(0).getId(), 
					log.getLogon().get(0).getUsuario(), 
					log.getLogon().get(0).getNivel(),
					"MENU");
		} else {
			msg("errorLog", "");
		}
	}

	public void msg(String tipo, String mensagem) {

		switch (tipo) {

		case "errorLog":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, faça o login no sistema para acessar este recurso.", 
					"Acesso não Autorizado",
					JOptionPane.PLAIN_MESSAGE, 
					new ImageIcon("../MASProject/icons/error.png"));
			//System.exit(0);
			break;

		case "errorSession":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, solicite a autorização de um administrador.", 
					"Bloqueado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
			break;

		case "systemClose":
			Object[] exit = { "Confirmar", "Cancelar" };  
			int fechar = JOptionPane.showOptionDialog(null, "ATENÇÃO!\n\nDeseja encerrar a aplicação?",
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
					"ERRO! Algo não deveria ter acontecido…\n\nMenuCtrl - Termo: " + mensagem
					+ "\n\nOcorreu no Controller desta Tela.", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
		}
	}
	
	public void login(){
		
		if(desktop.getSelectedFrame() == null){
			desktop.setSelectedFrame(new FrmLogin());
			desktop.getSelectedFrame().setVisible(true);
			desktop.add(desktop.getSelectedFrame());
		}
		else if(!desktop.getSelectedFrame().isVisible()){
			desktop.getSelectedFrame().setVisible(true);
			desktop.add(desktop.getSelectedFrame());
		}
	}


	public void iForm(String iFrame) {

		sessao();

		if (("Operacional").equalsIgnoreCase(log.getLogon().get(0).getNivel()) ||
				("Administrativo").equalsIgnoreCase(log.getLogon().get(0).getNivel())){

			switch (iFrame) {

			case "usuarios":

				if (("Administrativo").equalsIgnoreCase(log.getLogon().get(0).getNivel())){

					if(desktop.getSelectedFrame() == null){
						desktop.setSelectedFrame(new FrmUsuario());
						desktop.getSelectedFrame().setVisible(true);
						desktop.add(desktop.getSelectedFrame());
					}
					else if(!desktop.getSelectedFrame().isVisible()){
						desktop.getSelectedFrame().setVisible(true);
						desktop.add(desktop.getSelectedFrame());
					}
					/*} else {
				msg("errorSession", "");*/
				}
				break;

			case "emprestimo":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmEmprestimo());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "acervoCadastrar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmAcervoCad());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "acervoEditar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmAcervoEdit());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "artistaCadastrar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmArtistaCad());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "artistaEditar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmArtistaEdit());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "categoriaCadastrar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmCategoriaCad());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "categoriaEditar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmCategoriaEdit());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "materialCadastrar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmMaterialCad());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "materialEditar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmMaterialEdit());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "setorCadastrar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmSetorCad());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "setorEditar":

				if(desktop.getSelectedFrame() == null){
					desktop.setSelectedFrame(new FrmSetorEdit());
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "exposicaoCadastrar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmExposicaoCad());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "exposicaoEditar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmExposicaoEdit());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "visitanteCadastrar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmVisitanteCad());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "visitanteEditar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmVisitanteEdit());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				break;

			case "ingressoCadastrar":

				if(desktop.getSelectedFrame() == null){
					try {
						desktop.setSelectedFrame(new FrmIngresso());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}
				else if(!desktop.getSelectedFrame().isVisible()){
					desktop.getSelectedFrame().setVisible(true);
					desktop.add(desktop.getSelectedFrame());
				}		
				break;

			default:
				msg("","Metodo iForm");
			}
		}
	}


	// CONTROLE ACTION //////////////////////////////


	public ActionListener imprimir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			sessao();
			if (("Operacional").equalsIgnoreCase(log.getLogon().get(0).getNivel()) ||
					("Administrativo").equalsIgnoreCase(log.getLogon().get(0).getNivel())){
				msg("", "Ainda a ser Implementado");
			}
		}
	};

	public ActionListener sair = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			msg("systemClose","Fechamento");
			if(validar != false){
				System.exit(0);
			}
		}
	};



	// CONTROLE IFORM //////////////////////////////	

	public ActionListener login = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			login();
		}
	};

	public ActionListener usuarios = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("usuarios");
		}
	};

	public ActionListener emprestimo = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("emprestimo");
		}
	};

	public ActionListener acervoCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("acervoCadastrar");
		}
	};

	public ActionListener acervoEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("acervoEditar");
		}
	};

	public ActionListener artistaCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("artistaCadastrar");
		}
	};

	public ActionListener artistaEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("artistaEditar");
		}
	};

	public ActionListener categoriaCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("categoriaCadastrar");
		}
	};

	public ActionListener categoriaEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("categoriaEditar");
		}
	};

	public ActionListener materialCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("materialCadastrar");
		}
	};

	public ActionListener materialEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("materialEditar");
		}
	};

	public ActionListener setorCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("setorCadastrar");
		}
	};

	public ActionListener setorEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("setorEditar");
		}
	};

	public ActionListener exposicaoCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("exposicaoCadastrar");
		}
	};

	public ActionListener exposicaoEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("exposicaoEditar");
		}
	};

	public ActionListener visitanteCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("visitanteCadastrar");
		}
	};

	public ActionListener visitanteEditar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("visitanteEditar");
		}
	};

	public ActionListener ingressoCadastrar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			iForm("ingressoCadastrar");
		}
	};


	// CONTROLE COMPONENTES //////////////////////////////


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
