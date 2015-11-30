package view;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;

import controller.jBackground;

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
    
	
	public Menu() {
		 
        super("Sistema MASP");
 
        int inset = 50;
 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                   screenSize.width  - inset*2,
                  screenSize.height - inset*2);
       
        final jBackground desktopPane = new jBackground();
        
        
        
        salvarcomo = new JMenuItem("Salvar Como...");
        
        
        
        imprimir   = new JMenuItem("Imprimir...");
        
        
        
        fazerlogin = new JMenuItem("Fazer Login");
        
        fazerlogin.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame11 == null){
                    frame11 = new FrmLogin();
                    frame11.setVisible(true);
                    desktopPane.add(frame11);
                }
                else if(!frame11.isVisible()){
                    frame11.setVisible(true);
                    desktopPane.add(frame11);
                }
            }
        });
        
        
        configurarusuarios = new JMenuItem("Configurar Usu·rios");
        
        configurarusuarios.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame16 == null){
                    frame16 = new FrmUsuario();
                    frame16.setVisible(true);
                    desktopPane.add(frame16);
                }
                else if(!frame16.isVisible()){
                    frame16.setVisible(true);
                    desktopPane.add(frame16);
                }
            }
        });
        
        
        
        
        
        
        sair = new JMenuItem("Sair");
        
        
        
       obra = new JMenu("Obra");
        
        obracadastrar = new JMenuItem("Cadastrar");
        obracadastrar.addActionListener(new ActionListener(){
        	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame1 == null){
                    try {
						frame1 = new FrmAcervoCad();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame1.setVisible(true);
                    desktopPane.add(frame1);
                }
                else if(!frame1.isVisible()){
                    frame1.setVisible(true);
                    desktopPane.add(frame1);
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame2.setVisible(true);
                    desktopPane.add(frame2);
                }
                else if(!frame2.isVisible()){
                    frame2.setVisible(true);
                    desktopPane.add(frame2);
                }
            }
        });
        
        artista = new JMenu("Artista");
        
        artistacadastrar = new JMenuItem("Cadastrar");
        
        artistacadastrar.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame3 == null){
                    frame3 = new FrmArtistaCad();
                    frame3.setVisible(true);
                    desktopPane.add(frame3);
                }
                else if(!frame3.isVisible()){
                    frame3.setVisible(true);
                    desktopPane.add(frame3);
                }
            }
        });
        
        
        
        
        artistaeditar = new JMenuItem("Editar");
        artistaeditar.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame4 == null){
                    frame4 = new FrmArtistaEdit();
                    frame4.setVisible(true);
                    desktopPane.add(frame4);
                }
                else if(!frame4.isVisible()){
                    frame4.setVisible(true);
                    desktopPane.add(frame4);
                }
            }
        });
        
        
        
        
        
        
        categoria = new JMenu("Categoria");
        
        categoriacadastrar = new JMenuItem("Cadastrar");
        
        categoriacadastrar.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame5 == null){
                    frame5 = new FrmCategoriaCad();
                    frame5.setVisible(true);
                    desktopPane.add(frame5);
                }
                else if(!frame5.isVisible()){
                    frame5.setVisible(true);
                    desktopPane.add(frame5);
                }
            }
        });
        
        
        
        
        
        
        
        categoriaeditar = new JMenuItem("Editar");
        obraeditar.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame6 == null){
                    frame6 = new FrmCategoriaEdit();
                    frame6.setVisible(true);
                    desktopPane.add(frame6);
                }
                else if(!frame6.isVisible()){
                    frame6.setVisible(true);
                    desktopPane.add(frame6);
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
                }
                else if(!frame12.isVisible()){
                    frame12.setVisible(true);
                    desktopPane.add(frame12);
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
                }
                else if(!frame13.isVisible()){
                    frame13.setVisible(true);
                    desktopPane.add(frame13);
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
                }
                else if(!frame14.isVisible()){
                    frame14.setVisible(true);
                    desktopPane.add(frame14);
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
                }
                else if(!frame15.isVisible()){
                    frame15.setVisible(true);
                    desktopPane.add(frame15);
                }
            }
        });
        
        
        
        
        
        emprestimos = new JMenuItem("EmprÈstimos");
        
        emprestimos.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame7 == null){
                    try {
						frame7 = new FrmEmprestimo();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame7.setVisible(true);
                    desktopPane.add(frame7);
                }
                else if(!frame7.isVisible()){
                    frame7.setVisible(true);
                    desktopPane.add(frame7);
                }
            }
        });
        
        
        
       
        
        exposicao = new JMenu("ExposiÁ„o");
        exposicaocadastrar = new JMenuItem("Cadastrar");
        
        exposicaocadastrar.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame8 == null){
                    try {
						frame8 = new FrmExposicaoCad();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame8.setVisible(true);
                    desktopPane.add(frame8);
                }
                else if(!frame8.isVisible()){
                    frame8.setVisible(true);
                    desktopPane.add(frame8);
                }
            }
        });
        
        
        
        
        exposicaoeditar = new JMenuItem("Editar");
        
        exposicaoeditar.addActionListener(new ActionListener(){
       	 
            public void actionPerformed(ActionEvent evt){
 
                if(frame9 == null){
                    try {
						frame9 = new FrmExposicaoEdit();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame9.setVisible(true);
                    desktopPane.add(frame9);
                }
                else if(!frame9.isVisible()){
                    frame9.setVisible(true);
                    desktopPane.add(frame9);
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
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame10.setVisible(true);
                    desktopPane.add(frame10);
                }
                else if(!frame10.isVisible()){
                    frame10.setVisible(true);
                    desktopPane.add(frame10);
                }
            }
        });
        
        
     
        visitantecadastrar = new JMenuItem("Cadastrar");
        visitantecadastrar.addActionListener(new ActionListener(){
 
            public void actionPerformed(ActionEvent evt){
 
                if(frame17 == null){
                    try {
						frame17 = new FrmVisitanteCad();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame17.setVisible(true);
                    desktopPane.add(frame17);
                }
                else if(!frame17.isVisible()){
                    frame17.setVisible(true);
                    desktopPane.add(frame17);
                }
            }
        });
        
        visitanteeditar = new JMenuItem("Editar");
        visitanteeditar.addActionListener(new ActionListener(){
 
            public void actionPerformed(ActionEvent evt){
 
                if(frame18 == null){
                    try {
						frame18 = new FrmVisitanteEdit();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    frame18.setVisible(true);
                    desktopPane.add(frame18);
                }
                else if(!frame18.isVisible()){
                    frame18.setVisible(true);
                    desktopPane.add(frame18);
                }
            }
        });
        
        
        visualizar = new JMenuItem("Tipo A");
        
        
        
        documentacao = new JMenuItem("DocumentaÁ„o");
        
        
        menuBar = new JMenuBar();
        arquivo = new JMenu("Arquivo");
        acervo = new JMenu("Acervo");
        exposicao = new JMenu("ExposiÁ„o");
        
        visitante = new JMenu("Visitante");
        ingresso = new JMenu("Ingresso");
        relatorios = new JMenu("RelatÛrios");
        ajuda = new JMenu("Ajuda");
        
        // desktopPane.setBackground(Color.gray); // Ajusta uma cor de fundo (opÁ„o caso a imagem de fundo dÍ algum problema no programa)...
        
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
		 
       Menu menu = new Menu();
    }
	
}
