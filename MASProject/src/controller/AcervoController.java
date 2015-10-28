package controller;

import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Artista;
import model.Categoria;
import model.Material;
import model.Obra;
import model.Setor;
import persistence.ObraArquivoImpl;

public class AcervoController implements ComponentListener {

	private JLabel imagem, msgGravado, msgVazio;
	private JTextField nomeArtista, nomeObra, dataAquisicao, textField_valor;
	private JEditorPane descricaoObra;
	private JComboBox<String> cbMaterial;
	private JComboBox<String> cbCategoria;
	// Variavel caminho imagem criada para gravar e carregar na hora de procurar
	// obra

	private String caminhoImagem;
	private JComboBox<String> cbSetor;
	private JComboBox <String> comboSetorT;
	private JComboBox <String> comboStatus;
	private JComboBox <String> comboStatusT;
	
	private ArquivosController arqController;
//lblSelecImagem,comboSetor,comboSetorT,comboStatus,comboStatusT,cbCategoria, cbMaterial
	public AcervoController(JLabel imagem, JComboBox<String> comboSetor,JComboBox<String> comboSetorT,
			JComboBox<String> comboStatus,JComboBox<String> comboStatusT, JComboBox<String> cbCategoria,
			JComboBox<String> cbMaterial, JTextField nomeArtista, JTextField nomeObra, JTextField dataAquisicao,
			JEditorPane descricaoObra, JLabel msgGravado, JLabel msgVazio, JTextField textField_valor) {
		this.imagem = imagem;
		this.nomeArtista = nomeArtista;
		this.nomeObra = nomeObra;
		this.dataAquisicao = dataAquisicao;
		this.descricaoObra = descricaoObra;
		this.cbMaterial = cbMaterial;
		this.cbCategoria = cbCategoria;
		this.cbSetor = comboSetor;
		this.comboSetorT = comboSetorT;
		this.comboStatus = comboStatus;
		this.comboStatusT = comboStatusT;
		this.caminhoImagem = "../MASProject/icons/painting.png";
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.textField_valor = textField_valor;
		// lerAcervo();
		lerAcervo();
	}

	public void lerXml() {

		try {

			File fXmlFile = new File("../MASProject/dados/xmlTeste.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("item");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Item id : " + eElement.getAttribute("id"));
					System.out.println("Nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent());
					System.out.println("Preço : " + eElement.getElementsByTagName("preco").item(0).getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void lerAcervo() {
		ArrayList<Obra> obras = new ArrayList<Obra>();
		String linha = new String();
		ArrayList<String> acervo = new ArrayList<>();

		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados/", "acervo");
			linha = arqController.getBuffer();

			String[] categoria = linha.split(";");
			for (String s : categoria) {
				if (!(s.contains("-"))) {
					acervo.add(s);
				}
			}
			for (String s : categoria) {
				String text = s.replaceAll(".*:", ""); 
				acervo.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i< acervo.size(); i++){
			
		}
		
	}
	public void procuraAcervoNome(String palavraChave){
		String linha = new String();

		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados/", "acervo");
			linha = arqController.getBuffer();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] categoria = linha.split(";");
		ArrayList<String> acervo = new ArrayList<>();
		for (String s : categoria) {
			if (!(s.contains("-"))) {
				String text = s.replaceAll(".*:", "");
				acervo.add(text);
			}
		}
		for(String s : acervo){
			if(s.equalsIgnoreCase(palavraChave)){
				System.out.println("Achou");
			}else{
				System.out.println("não Achou");

			}
		}
	}

	public void procuraImagem() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de imagem (jpg, png, gif)", "jpg", "png",
				"gif");
		String diretorioBase = System.getProperty("user.home") + "/Desktop";
		File dir = new File(diretorioBase);

		JFileChooser choose = new JFileChooser();
		choose.setCurrentDirectory(dir);
		choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choose.setAcceptAllFileFilterUsed(false);
		choose.addChoosableFileFilter(filtro);
		String caminhoArquivo = "";

		int retorno = choose.showOpenDialog(null);
		if (retorno == JFileChooser.APPROVE_OPTION) {
			caminhoArquivo = choose.getSelectedFile().getAbsolutePath();
			ImageIcon img = new ImageIcon(caminhoArquivo);
			Image newImg = img.getImage().getScaledInstance(imagem.getWidth(), imagem.getHeight(), Image.SCALE_DEFAULT);
			imagem.setIcon(new ImageIcon(newImg));
			// System.out.println(caminhoArquivo);
			caminhoImagem = caminhoArquivo;
		}

	}

	public ActionListener inserir_imagem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			procuraImagem();
		}

	};

	public ActionListener gravarAcervo = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarAcervo();
		}

	};
	public ActionListener remover_imagem = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));

		}

	};
	

	public void gravarAcervo() {
		Obra obra = new Obra();
		ObraArquivoImpl obraImpl = new ObraArquivoImpl();
		// Acervo acervo = new Acervo();
		Artista artista = new Artista();
		Categoria categoria = new Categoria();
		Material material = new Material();
		Setor setor = new Setor();
		System.out.println(material.getNome());

		if (nomeArtista.getText().isEmpty()) {
			msgVazio.setVisible(true);
			msgVazio.setText("Campo Artista é obrigatório");
		} else if (nomeObra.getText().isEmpty()) {
			msgVazio.setVisible(true);
			msgVazio.setText("Campo Obra é obrigatório");
		} else if (dataAquisicao.getText().isEmpty()) {
			msgVazio.setVisible(true);
			msgVazio.setText("Campo Data é obrigatório");
		} else {
			msgGravado.setVisible(true);
			msgVazio.setVisible(false);
			setor.setNome((String) cbSetor.getSelectedItem());
			material.setNome((String) cbMaterial.getSelectedItem());
			categoria.setNome((String) cbCategoria.getSelectedItem());
			artista.setNome(nomeArtista.getText());
			obra.setNomeObra(nomeObra.getText());
			obra.setArtista(artista);
			obra.setSetor(setor);
			obra.setImagem(caminhoImagem);
			obra.setCategoria(categoria);
			obra.setDataComposicao(dataAquisicao.getText());
			obra.setDescricaoObra(descricaoObra.getText());
			obra.setMaterial(material);
			// String valor = textField_valor.getText();
			// valor = valor.replace(",", ".");
			// Double valorDouble = Double.parseDouble(valor);
			obra.setPreco(textField_valor.getText());
			try {
				obraImpl.escreveArquivo("../MASProject/dados/", "acervo", "", obra);
			} catch (IOException e) {
				e.printStackTrace();
			}
			limpaCampos();
			int delay = 3000; // delay de 5 seg.
			int interval = 1000; // intervalo de 1 seg.
			final Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					msgGravado.setVisible(false);
					timer.cancel();
				}
			}, delay, interval);

		}

	}

	public void limpaCampos() {
		imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		imagem.setBackground(SystemColor.inactiveCaption);
		imagem.setHorizontalAlignment(SwingConstants.CENTER);
		nomeArtista.setText(null);
		nomeObra.setText(null);
		textField_valor.setText(null);
		dataAquisicao.setText(null);
		descricaoObra.setText(null);
		cbMaterial.setSelectedIndex(0);
		// cbCategoria.setSelectedIndex(0);
		cbSetor.setSelectedIndex(0);

	}
	public void preencherComboBoxMaterial() {
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "materiais");
			arqController.leArquivo("../MASProject/dados/", "materiais");
			linha = arqController.getBuffer();
			String[] categoria = linha.split(";");
			for (String s : categoria) {
				cbMaterial.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void preencherComboBoxCategoria(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "categorias");
			linha = arqController.getBuffer();
			String [] categoria = linha.split(";");
			for(String s : categoria){
				cbCategoria.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preencherComboBoxSetores() {
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "setores");
			linha = arqController.getBuffer();
			String[] setor = linha.split(";");
			for (String s : setor) {
				cbSetor.addItem(s);
				comboSetorT.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void preencherComboStatusProprio(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "status_obra_propria");
			linha = arqController.getBuffer();
			String [] proprio = linha.split(";");
			for(String s : proprio){
				comboStatus.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preencherComboStatusTerceiro(){
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "status_obra_terceiros");
			linha = arqController.getBuffer();
			String [] terceiro = linha.split(";");
			for(String s : terceiro){
				comboStatusT.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		preencherComboBoxMaterial();
		preencherComboBoxSetores();

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}
}
