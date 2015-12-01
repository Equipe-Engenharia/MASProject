package view;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;

import controller.MenuCtrl;
import controller.SessaoCtrl;

public class FrmMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuItem salvar, imprimir, login, usuarios, sair;
	private JMenuItem acervoCadastrar, acervoEditar, artistaCadastrar, artistaEditar;
	private JMenuItem categoriaCadastrar, categoriaEditar, materialCadastrar, materialEditar;
	private JMenuItem setorCadastrar, setorEditar, emprestimo;
	private JMenuItem exposicaoCadastrar, exposicaoEditar;
	private JMenuItem ingressoCadastrar;
	private JMenuItem visitanteCadastrar, visitanteEditar;
	private JMenuItem visualizar;
	private JMenuItem documentacao;
	private JMenuBar menuBar;
	private JMenu obra, artista, categoria, material, setor;
	private JMenu arquivo, acervo, exposicao, visitante, ingresso, relatorios, ajuda; 

	private FrmLogin         frame11;
	

	

	

	public FrmMenu() throws ParseException {

		super("Sistema MASP");

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		int inset = 50;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset,
				screenSize.width  - inset*2,
				screenSize.height - inset*2);


		final JDesktopPane desktopPane = new JDesktopPane();


	


		visualizar = new JMenuItem("Tipo A");
		documentacao = new JMenuItem("Documentação");
		menuBar = new JMenuBar();
		arquivo = new JMenu("Arquivo");
		acervo = new JMenu("Acervo");
		exposicao = new JMenu("Exposição");
		visitante = new JMenu("Visitante");
		ingresso = new JMenu("Ingresso");
		relatorios = new JMenu("Relatórios");
		ajuda = new JMenu("Ajuda");

		desktopPane.setBackground(Color.gray); // Ajusta uma cor de fundo (opção caso a imagem de fundo dÍ algum problema no programa)

		setContentPane(desktopPane);
		setJMenuBar(menuBar);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		login = new JMenuItem("Fazer Login");
		arquivo.add(login);

		usuarios = new JMenuItem("Configurar Usuários");
		arquivo.add(usuarios);
		
		salvar = new JMenuItem("Salvar Como…");
		arquivo.add(salvar);
		
		imprimir = new JMenuItem("Imprimir…");
		arquivo.add(imprimir);
		
		arquivo.add(new JSeparator());

		sair = new JMenuItem("Sair");
		arquivo.add(sair);
		
		obra = new JMenu("Obra");
		acervo.add(obra);
		
		artista = new JMenu("Artista");
		acervo.add(artista);
		
		categoria = new JMenu("Categoria");
		acervo.add(categoria);
		
		material = new JMenu("Material");
		acervo.add(material);
		
		setor = new JMenu("Setor");
		acervo.add(setor);
		
		acervo.add(new JSeparator());
		
		emprestimo = new JMenuItem("Empréstimos");
		acervo.add(emprestimo);
		
		acervoCadastrar = new JMenuItem("Cadastrar");
		obra.add(acervoCadastrar);
		
		acervoEditar = new JMenuItem("Editar");
		obra.add(acervoEditar);
		
		artistaCadastrar = new JMenuItem("Cadastrar");
		artista.add(artistaCadastrar);
		
		artistaEditar = new JMenuItem("Editar");
		artista.add(artistaEditar);
		
		categoriaCadastrar = new JMenuItem("Cadastrar");
		categoria.add(categoriaCadastrar);
		
		categoriaEditar = new JMenuItem("Editar");
		categoria.add(categoriaEditar);
		
		materialCadastrar = new JMenuItem("Cadastrar");
		material.add(materialCadastrar);
		
		materialEditar = new JMenuItem("Editar");
		material.add(materialEditar);
		
		setorCadastrar = new JMenuItem("Cadastrar");
		setor.add(setorCadastrar);
		
		setorEditar = new JMenuItem("Editar");
		setor.add(setorEditar);
		
		exposicaoCadastrar = new JMenuItem("Cadastrar");
		exposicao.add(exposicaoCadastrar);
		
		exposicaoEditar = new JMenuItem("Editar");
		exposicao.add(exposicaoEditar);
		
		visitanteCadastrar = new JMenuItem("Cadastrar");
		visitante.add(visitanteCadastrar);
		
		visitanteEditar = new JMenuItem("Editar");
		visitante.add(visitanteEditar);
		
		ingressoCadastrar = new JMenuItem("Vender");
		ingresso.add(ingressoCadastrar);
		
		
		relatorios.add(visualizar);
		ajuda.add(documentacao);
		menuBar.add(arquivo);
		menuBar.add(acervo);
		menuBar.add(exposicao);
		menuBar.add(visitante);
		menuBar.add(ingresso);
		menuBar.add(relatorios);
		menuBar.add(ajuda); 
		

		MenuCtrl controle = new MenuCtrl (desktopPane);

		imprimir.addActionListener(controle.imprimir);
		sair.addActionListener(controle.sair);
		login.addActionListener(controle.login);
		usuarios.addActionListener(controle.usuarios);
		emprestimo.addActionListener(controle.emprestimo);
		acervoCadastrar.addActionListener(controle.acervoCadastrar);
		acervoEditar.addActionListener(controle.acervoEditar);
		artistaCadastrar.addActionListener(controle.artistaCadastrar);
		artistaEditar.addActionListener(controle.artistaEditar);
		categoriaCadastrar.addActionListener(controle.categoriaCadastrar);
		categoriaEditar.addActionListener(controle.categoriaEditar);
		materialCadastrar.addActionListener(controle.materialCadastrar);
		materialEditar.addActionListener(controle.materialEditar);
		setorCadastrar.addActionListener(controle.setorCadastrar);
		setorEditar.addActionListener(controle.setorEditar);
		exposicaoCadastrar.addActionListener(controle.exposicaoCadastrar);
		exposicaoEditar.addActionListener(controle.exposicaoEditar);
		visitanteCadastrar.addActionListener(controle.visitanteCadastrar);
		visitanteEditar.addActionListener(controle.visitanteEditar);
		ingressoCadastrar.addActionListener(controle.ingressoCadastrar);
	}

	public static void main(String args[]) throws ParseException{

		@SuppressWarnings("unused")
		FrmMenu menu = new FrmMenu();

	}  
}