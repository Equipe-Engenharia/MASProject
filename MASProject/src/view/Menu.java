package view;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;

import controller.SessaoCtrl;

public class Menu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuItem salvarcomo, imprimir, fazerlogin, configurarusuarios, sair;
	private JMenuItem obracadastrar, obraeditar, artistacadastrar, artistaeditar;
	private JMenuItem categoriacadastrar, categoriaeditar, materialcadastrar, materialeditar;
	private JMenuItem setorcadastrar, setoreditar, emprestimos;
	private JMenuItem exposicaocadastrar, exposicaoeditar;
	private JMenuItem vendaformulario;
	private JMenuItem visitantecadastrar, visitanteeditar;
	private JMenuItem visualizar;
	private JMenuItem documentacao;
	private JMenuBar menuBar;
	private JMenu obra, artista, categoria, material, setor;
	private JMenu arquivo, acervo, exposicao, visitante, ingresso, relatorios, ajuda; 

	private FrmAcervoCad     frame1;
	private FrmAcervoEdit    frame2;
	private FrmArtistaCad    frame3;
	private FrmArtistaEdit   frame4;
	private FrmCategoriaCad  frame5;
	private FrmCategoriaEdit frame6;
	private FrmEmprestimo    frame7;
	private FrmExposicaoCad  frame8;
	private FrmExposicaoEdit frame9;
	private FrmIngresso      frame10;
	private FrmLogin         frame11;
	private FrmMaterialCad   frame12;
	private FrmMaterialEdit  frame13;
	private FrmSetorCad      frame14;
	private FrmSetorEdit     frame15;
	private FrmUsuario       frame16;
	private FrmVisitanteCad  frame17;
	private FrmVisitanteEdit frame18;
	
	
	public void sessao() {

		SessaoCtrl log = SessaoCtrl.getInstance();

			log.registrar("000", "000", "000", "MENU");
	}
	
	public Menu() {
		
		super("Sistema MASP");
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		int inset = 50;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset,
				screenSize.width  - inset*2,
				screenSize.height - inset*2);

		
		final JDesktopPane desktopPane = new JDesktopPane();

		salvarcomo = new JMenuItem("Salvar Como...");
		imprimir   = new JMenuItem("Imprimir...");
		sair = new JMenuItem("Sair");

		frame11 = new FrmLogin();
		frame11.setVisible(true);
		desktopPane.add(frame11);
		if(!frame11.isVisible()){
			frame11.setVisible(true);
			desktopPane.add(frame11);
		}
		
		sessao();
			
		obra = new JMenu("Obra");	
			
			obracadastrar = new JMenuItem("Cadastrar");
			obracadastrar.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent evt){
					if(frame1 == null){
						try {
							frame1 = new FrmAcervoCad();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						desktopPane.add(frame1);
						frame1.setVisible(true);
						try {
							frame1.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						} 
					}
					else if(!frame1.isVisible()){
						frame1.setVisible(true);
						desktopPane.add(frame1);
						try {
							frame1.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						} 
					}
				}
			});

		obraeditar = new JMenuItem("Editar");
		obraeditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame2 == null){
					try {
						frame2 = new FrmAcervoEdit();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					desktopPane.add(frame2);
					frame2.setVisible(true);
					try {
						frame2.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame2.isVisible()){
					frame2.setVisible(true);
					desktopPane.add(frame2);
					try {
						frame2.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});

		artista = new JMenu("Artista");
		artistacadastrar = new JMenuItem("Cadastrar");
		
		artistacadastrar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame3 == null){
					frame3 = new FrmArtistaCad();
					desktopPane.add(frame3);
					frame3.setVisible(true);
					try {
						frame3.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame3.isVisible()){
					frame3.setVisible(true);
					desktopPane.add(frame3);
					try {
						frame3.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});

		artistaeditar = new JMenuItem("Editar");
		artistaeditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame4 == null){
					frame4 = new FrmArtistaEdit();
					desktopPane.add(frame4);
					frame4.setVisible(true);
					try {
						frame4.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame4.isVisible()){
					frame4.setVisible(true);
					desktopPane.add(frame4);
					try {
						frame4.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});


		categoria = new JMenu("Categoria");
		categoriacadastrar = new JMenuItem("Cadastrar");
		
		categoriacadastrar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame5 == null){
					frame5 = new FrmCategoriaCad();
					desktopPane.add(frame5);
					frame5.setVisible(true);
					try {
						frame5.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame5.isVisible()){
					frame5.setVisible(true);
					desktopPane.add(frame5);
					try {
						frame5.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});

		categoriaeditar = new JMenuItem("Editar");
		categoriaeditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame6 == null){
					frame6 = new FrmCategoriaEdit();
					desktopPane.add(frame6);
					frame6.setVisible(true);
					try {
						frame6.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame6.isVisible()){
					frame6.setVisible(true);
					desktopPane.add(frame6);
					try {
						frame6.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});

		emprestimos = new JMenuItem("Empréstimos");
		emprestimos.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame7 == null){
						try {
							frame7 = new FrmEmprestimo();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						desktopPane.add(frame7);
						frame7.setVisible(true);
						try {
							frame7.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						} 
					}
					else if(!frame7.isVisible()){
						frame7.setVisible(true);
						desktopPane.add(frame7);
						try {
							frame7.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						} 
					}
				}
			});

		exposicao = new JMenu("Exposição");
		exposicaocadastrar = new JMenuItem("Cadastrar");

		exposicaocadastrar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame8 == null){
					try {
						frame8 = new FrmExposicaoCad();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					desktopPane.add(frame8);
					frame8.setVisible(true);
					try {
						frame8.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame8.isVisible()){
					frame8.setVisible(true);
					desktopPane.add(frame8);
					try {
						frame8.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});

		exposicaoeditar = new JMenuItem("Editar");
		exposicaoeditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame9 == null){
					try {
						frame9 = new FrmExposicaoEdit();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					desktopPane.add(frame9);
					frame9.setVisible(true);
					try {
						frame9.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame9.isVisible()){
					frame9.setVisible(true);
					desktopPane.add(frame9);
					try {
						frame9.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});


		ingresso = new JMenu("Ingresso");
		vendaformulario = new JMenuItem("Vender");

		vendaformulario.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame10 == null){
					try {
						frame10 = new FrmIngresso();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					desktopPane.add(frame10);
					frame10.setVisible(true);
					try {
						frame10.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
				else if(!frame10.isVisible()){
					frame10.setVisible(true);
					desktopPane.add(frame10);
					try {
						frame10.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					} 
				}
			}
		});

		fazerlogin = new JMenuItem("Fazer Login");
		fazerlogin.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame11 == null){
					frame11 = new FrmLogin();
					frame11.setVisible(true);
					desktopPane.add(frame11);
					try {
						frame11.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if(!frame11.isVisible()){
					frame11.setVisible(true);
					desktopPane.add(frame11);
					try {
						frame11.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});

		material = new JMenu("Material");
		materialcadastrar = new JMenuItem("Cadastrar");
		
		materialcadastrar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame12 == null){
					frame12 = new FrmMaterialCad();
					frame12.setVisible(true);
					desktopPane.add(frame12);
					try {
						frame12.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if(!frame12.isVisible()){
					frame12.setVisible(true);
					desktopPane.add(frame12);
					try {
						frame12.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});


		materialeditar = new JMenuItem("Editar");
		materialeditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame13 == null){
					frame13 = new FrmMaterialEdit();
					frame13.setVisible(true);
					desktopPane.add(frame13);
					try {
						frame13.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if(!frame13.isVisible()){
					frame13.setVisible(true);
					desktopPane.add(frame13);
					try {
						frame13.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});

		setor = new JMenu("Setor");
		setorcadastrar = new JMenuItem("Cadastrar");

		setorcadastrar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame14 == null){
					frame14 = new FrmSetorCad();
					frame14.setVisible(true);
					desktopPane.add(frame14);
					try {
						frame14.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if(!frame14.isVisible()){
					frame14.setVisible(true);
					desktopPane.add(frame14);
					try {
						frame14.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});

		setoreditar = new JMenuItem("Editar");

		setoreditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame15 == null){
					frame15 = new FrmSetorEdit();
					frame15.setVisible(true);
					desktopPane.add(frame15);
					try {
						frame15.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if(!frame15.isVisible()){
					frame15.setVisible(true);
					desktopPane.add(frame15);
					try {
						frame15.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});

		configurarusuarios = new JMenuItem("Configurar Usuários");
		configurarusuarios.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame16 == null){
					frame16 = new FrmUsuario();
					frame16.setVisible(true);
					desktopPane.add(frame16);
					try {
						frame16.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if(!frame16.isVisible()){
					frame16.setVisible(true);
					desktopPane.add(frame16);
					try {
						frame16.setPosicao();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		});


		visitantecadastrar = new JMenuItem("Cadastrar");
		visitantecadastrar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame17 == null){
						try {
							frame17 = new FrmVisitanteCad();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						frame17.setVisible(true);
						desktopPane.add(frame17);
						try {
							frame17.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					else if(!frame17.isVisible()){
						frame17.setVisible(true);
						desktopPane.add(frame17);
						try {
							frame17.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			});

		visitanteeditar = new JMenuItem("Editar");
		visitanteeditar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent evt){

				if(frame18 == null){
				
						try {
							frame18 = new FrmVisitanteEdit();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						frame18.setVisible(true);
						desktopPane.add(frame18);
						try {
							frame18.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					else if(!frame18.isVisible()){
						frame18.setVisible(true);
						desktopPane.add(frame18);
						try {
							frame18.setPosicao();
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			});

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

		desktopPane.setBackground(Color.gray); // Ajusta uma cor de fundo (opção caso a imagem de fundo dÍ algum problema no programa)...

		setContentPane(desktopPane);

		arquivo.add(salvarcomo);
		arquivo.add(imprimir);
		arquivo.add(new JSeparator());
		arquivo.add(fazerlogin);
		arquivo.add(configurarusuarios);
		arquivo.add(new JSeparator());
		arquivo.add(sair);

		acervo.add(obra);
		acervo.add(artista);
		acervo.add(categoria);
		acervo.add(material);
		acervo.add(setor);
		acervo.add(new JSeparator());
		acervo.add(emprestimos);
		obra.add(obracadastrar);
		obra.add(obraeditar); 
		artista.add(artistacadastrar);
		artista.add(artistaeditar);
		categoria.add(categoriacadastrar);
		categoria.add(categoriaeditar);
		material.add(materialcadastrar);
		material.add(materialeditar);
		setor.add(setorcadastrar);
		setor.add(setoreditar);
		exposicao.add(exposicaocadastrar);
		exposicao.add(exposicaoeditar);
		visitante.add(visitantecadastrar);
		visitante.add(visitanteeditar);
		ingresso.add(vendaformulario);
		relatorios.add(visualizar);
		ajuda.add(documentacao);
		menuBar.add(arquivo);
		menuBar.add(acervo);
		menuBar.add(exposicao);
		menuBar.add(visitante);
		menuBar.add(ingresso);
		menuBar.add(relatorios);
		menuBar.add(ajuda);

		setJMenuBar(menuBar);

		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]){

		@SuppressWarnings("unused")
		Menu menu = new Menu();

	}  
	
	public void msg(String tipo, String mensagem) {

		switch (tipo) {
		case "errorlogin":
			JOptionPane.showMessageDialog(null, 
					"ACESSO NEGADO!\n\nPor favor, faça o login no sistema para acessar este recurso.", 
					"Acesso não Autorizado", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
			 break;
		
		default:
			JOptionPane.showMessageDialog(null, 
					"ERRO! Algo não deveria ter acontecido…\n\nTermo: " + mensagem
					+ "\n\nOcorreu no Controller desta Tela.", 
					"Erro no Controller", 
					JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/error.png"));
		}
	}

}