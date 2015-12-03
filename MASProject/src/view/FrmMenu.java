package view;

import java.awt.*;
import javax.swing.*;

import controller.MenuCtrl;

public class FrmMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuItem salvar, imprimir, login, usuarios, sair, acervoCadastrar, 
	acervoEditar, artistaCadastrar, artistaEditar, categoriaCadastrar, 
	categoriaEditar, materialCadastrar, materialEditar, setorCadastrar, 
	setorEditar, emprestimo, exposicaoCadastrar, exposicaoEditar, ingressoCadastrar, 
	visitanteCadastrar, visitanteEditar, agendaCadastrar, reFinanceiro, reEstatistico, documentacao;
	private JMenuBar menuBar;
	private JMenu obra, artista, categoria, material, setor, 
	arquivo, acervo, exposicao, visitante, agenda, ingresso, relatorios, ajuda;
	

	public FrmMenu() {

		super("Sistema MASP");

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		int inset = 50;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset,
				screenSize.width  - inset*2,
				screenSize.height - inset*2);

		final JDesktopPane desktopPane = new JDesktopPane();

		desktopPane.setBackground(Color.gray); // Ajusta uma cor de fundo

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setContentPane(desktopPane);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		arquivo = new JMenu("Arquivo");
		menuBar.add(arquivo);
		
		acervo = new JMenu("Acervo");
		menuBar.add(acervo);
		
		exposicao = new JMenu("Exposição");
		menuBar.add(exposicao);
		
		visitante = new JMenu("Visitante");
		menuBar.add(visitante);
		
		agenda = new JMenu("Agendamento");
		menuBar.add(agenda);
		
		ingresso = new JMenu("Ingresso");
		menuBar.add(ingresso);
		
		relatorios = new JMenu("Relatórios");
		menuBar.add(relatorios);
		
		ajuda = new JMenu("Ajuda");
		menuBar.add(ajuda);
		
		reFinanceiro = new JMenuItem("Relatório Financeiro");
		relatorios.add(reFinanceiro);
		
		reEstatistico = new JMenuItem("Relatório Financeiro");
		relatorios.add(reEstatistico);
		
		documentacao = new JMenuItem("Documentação");
		ajuda.add(documentacao);

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
		
		agendaCadastrar = new JMenuItem("Cadastrar");
		agenda.add(agendaCadastrar);
		
		ingressoCadastrar = new JMenuItem("Vender");
		ingresso.add(ingressoCadastrar);
		

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
		agendaCadastrar.addActionListener(controle.agendaCadastrar);
		ingressoCadastrar.addActionListener(controle.ingressoCadastrar);
		reFinanceiro.addActionListener(controle.reFinanceiro);
		reEstatistico.addActionListener(controle.reEstatistico);
	}

	public static void main(String args[]) {

		@SuppressWarnings("unused")
		FrmMenu menu = new FrmMenu();
	}  
}