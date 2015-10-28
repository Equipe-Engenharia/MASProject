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
import java.util.List;
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
	private List<Obra> obras;

	private String caminhoImagem;
	private JComboBox<String> cbSetor;

	private JComboBox<String> comboSetorT;
	private JComboBox<String> comboStatus;
	private JComboBox<String> comboStatusT;

	private ArquivosController arqController;

	public AcervoController(JLabel imagem, JComboBox<String> comboSetor, JComboBox<String> comboSetorT,
			JComboBox<String> comboStatus, JComboBox<String> comboStatusT, JComboBox<String> cbCategoria,
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
		this.obras = new ArrayList<Obra>();
		this.comboSetorT = comboSetorT;
		this.comboStatus = comboStatus;
		this.comboStatusT = comboStatusT;
		this.caminhoImagem = "../MASProject/icons/painting.png";
		this.msgGravado = msgGravado;
		this.msgVazio = msgVazio;
		this.textField_valor = textField_valor;

		lerAcervo();
	}

	public void lerAcervo() {
		String linha = new String();
		ArrayList<String> acervo = new ArrayList<>();

		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados/", "acervo");
			linha = arqController.getBuffer();
			String[] categoria = linha.split(";");
			for (String s : categoria) {
				String text = s.replaceAll(".*:", "");
				acervo.add(text);
				if (s.contains("-")) {
					Artista artista = new Artista();
					artista.setNome(acervo.get(0));
					Obra obra = new Obra();
					obra.setNomeObra(acervo.get(1));
					obra.setDescricaoObra(acervo.get(2));
					Categoria c = new Categoria();
					c.setNome(acervo.get(3));
					obra.setDataComposicao(acervo.get(4));
					obra.setImagem(acervo.get(5));
					Material material = new Material();
					material.setNome(acervo.get(6));
					Setor setor = new Setor();
					setor.setNome(acervo.get(7));
					obra.setPreco(acervo.get(8));
					obra.setProprietario(Boolean.parseBoolean(acervo.get(9)));
					obra.setStatus(acervo.get(10));

					obra.setArtista(artista);
					obra.setMaterial(material);
					obra.setCategoria(c);
					obra.setSetor(setor);
					obras.add(obra);
					acervo.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Obra o : obras) {
			System.out.println(o.getDataComposicao());
		}
	}

	public void procuraAcervoNome(String palavraChave) {
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
		for (String s : acervo) {
			if (s.equalsIgnoreCase(palavraChave)) {
				System.out.println("Achou");
			} else {
				System.out.println("n�o Achou");
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
			caminhoImagem = caminhoArquivo;
		}

	}

	public void gravarAcervo() {
		Obra obra = new Obra();
		ObraArquivoImpl obraImpl = new ObraArquivoImpl();
		Artista artista = new Artista();
		Categoria categoria = new Categoria();
		Material material = new Material();
		Setor setor = new Setor();
		obra.setProprietario(false);
		if (nomeArtista.getText().isEmpty()) {
			msgVazio.setVisible(true);
			msgVazio.setText("Campo Artista � obrigat�rio");

		} else if (nomeObra.getText().isEmpty()) {
			msgVazio.setVisible(true);
			msgVazio.setText("Campo Obra � obrigat�rio");
		} else if (dataAquisicao.getText().isEmpty()) {
			msgVazio.setVisible(true);
			msgVazio.setText("Campo Data � obrigat�rio");
		} else {
			if (!(textField_valor.getText().isEmpty())) {
				obra.setProprietario(true);
				obra.setStatus((String) comboStatus.getSelectedItem());
				setor.setNome((String) cbSetor.getSelectedItem());
			}else{
				obra.setStatus((String) comboStatusT.getSelectedItem());
				setor.setNome((String) comboSetorT.getSelectedItem());
			}
			msgGravado.setVisible(true);
			msgVazio.setVisible(false);
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
			obra.setPreco(textField_valor.getText());
			try {
				obraImpl.escreveArquivo("../MASProject/dados/", "acervo", "", obra);
			} catch (IOException e) {
				e.printStackTrace();
			}
			limpaCampos();
			int delay = 2000; // delay de 5 seg.
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
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Material> listMateriais = new ArrayList<>();

		try {
			arqController.leArquivo("../MASProject/dados/", "materiais");
			linha = arqController.getBuffer();
			String[] materias = linha.split(";");
			for (String s : materias) {
				String text = s.replaceAll(".*:", "");
				listString.add(text);
				if (s.contains("---")) {
					Material m = new Material();
					m.setID(listString.get(0));
					m.setNome(listString.get(1));
					m.setCategoria(listString.get(2));
					listMateriais.add(m);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Material m : listMateriais) {
			cbMaterial.addItem(m.getNome());
		}
	}

	public void preencherComboBoxCategoria() {
		String linha = new String();
		arqController = new ArquivosController();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategorias = new ArrayList<>();

		try {
			arqController.leArquivo("../MASProject/dados/", "categorias");
			linha = arqController.getBuffer();
			String[] materias = linha.split(";");
			for (String s : materias) {
				String text = s.replaceAll(".*:", "");
				listString.add(text);
				if (s.contains("---")) {
					Categoria c = new Categoria();
					c.setNome(listString.get(1));
					listCategorias.add(c);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Categoria c : listCategorias) {
			cbCategoria.addItem(c.getNome());
		}
	}

	public void preencherComboBoxSetores() {
		String linha = new String();
		arqController = new ArquivosController();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Setor> listSetores = new ArrayList<>();

		try {
			arqController.leArquivo("../MASProject/dados/", "setores");
			linha = arqController.getBuffer();
			String[] setores = linha.split(";");
			for (String s : setores) {
				String text = s.replaceAll(".*:", "");
				listString.add(text);
				if (s.contains("---")) {
					Setor setor = new Setor();
					setor.setNome(listString.get(1));
					listSetores.add(setor);
					listString.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Setor s : listSetores) {
			cbSetor.addItem(s.getNome());
			comboSetorT.addItem(s.getNome());
		}
	}

	public void preencherComboStatusProprio() {
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "status_obra_propria");
			linha = arqController.getBuffer();
			String[] proprio = linha.split(";");
			for (String s : proprio) {
				comboStatus.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preencherComboStatusTerceiro() {
		String linha = new String();
		arqController = new ArquivosController();
		try {
			arqController.leArquivo("../MASProject/dados", "status_obra_terceiros");
			linha = arqController.getBuffer();
			String[] terceiro = linha.split(";");
			for (String s : terceiro) {
				comboStatusT.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Controle de bot�es
	
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
	
	//Eventos de comboBox

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
