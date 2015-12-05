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
	visitanteCadastrar, visitanteEditar, reFinanceiro, reEstatistico, documentacao;
	private JMenuBar menuBar;
	private JMenu obra, artista, categoria, material, setor, 
	arquivo, acervo, exposicao, visitante, ingresso, relatorios, ajuda;
	private JLabel lblImage;
	

	public FrmMenu() {

		super("Sistema MASP");

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		int inset = 50;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset,
				screenSize.width  - inset*2,
				screenSize.height - inset*2);

		final JDesktopPane desktopPane = new JDesktopPane();

		desktopPane.setBackground(Color.gray); // Ajusta uma cor de fundo (opÃ§Ã£o caso a imagem de fundo dÃƒï¿½ algum problema no programa)
      
		
		ImageIcon img = new ImageIcon("../MASProject/jcalendar-1.4 (1)/masp.jpg");
		Image newImg = img.getImage();
		//.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
	//	setImage(new ImageIcon(newImg));
		
		//setIconImage(newImg);
		
		
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
		
		exposicao = new JMenu("ExposiÃ§Ã£o");
		menuBar.add(exposicao);
		
		visitante = new JMenu("Visitante");
		menuBar.add(visitante);
		
		ingresso = new JMenu("Ingresso");
		menuBar.add(ingresso);
		
		relatorios = new JMenu("RelatÃ³rios");
		menuBar.add(relatorios);
		
		ajuda = new JMenu("Ajuda");
		menuBar.add(ajuda);
		
		reFinanceiro = new JMenuItem("RelatÃ³rio Financeiro");
		relatorios.add(reFinanceiro);
		
		reEstatistico = new JMenuItem("RelatÃ³rio EstatÃ­stico");
		relatorios.add(reEstatistico);
		
		documentacao = new JMenuItem("DocumentaÃ§Ã£o");
		ajuda.add(documentacao);

		login = new JMenuItem("Fazer Login");
		arquivo.add(login);

		usuarios = new JMenuItem("Configurar UsuÃ¡rios");
		arquivo.add(usuarios);
		
		salvar = new JMenuItem("Salvar Comoâ€¦");
		arquivo.add(salvar);
		
		imprimir = new JMenuItem("Imprimirâ€¦");
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
		
		emprestimo = new JMenuItem("EmprÃ©stimos");
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
		

		MenuCtrl controle = new MenuCtrl (desktopPane);
		
		lblImage = new JLabel();
		desktopPane.setLayer(lblImage, -1);
		lblImage.setIcon(new ImageIcon(newImg));
		lblImage.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		desktopPane.add(lblImage);

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
		reFinanceiro.addActionListener(controle.reFinanceiro);
		reEstatistico.addActionListener(controle.reEstatistico);
	}


	public static void main(String args[]) {

		@SuppressWarnings("unused")
		FrmMenu menu = new FrmMenu();
	}  
}