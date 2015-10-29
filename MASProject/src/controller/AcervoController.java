package controller;

import java.awt.Image;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Artista;
import model.Categoria;
import model.Material;
import model.Obra;
import model.Setor;
import persistence.ObraArquivoImpl;
import view.FormAlteraDelArtista;
import view.FormAlteraDelCategoria;
import view.FormAlteraDelMaterial;
import view.FormRegisArtista;
import view.FormRegisCategoria;
import view.FormRegisMaterial;

public class AcervoController implements ComponentListener, ActionListener {

	private JLabel imagem, msgGravado, msgVazio, lblStatus;
	private JTextField idObra, tfNomeArtista, nomeObra, dataAquisicao, textField_valor, txtNovaObra;
	private JEditorPane descricaoObra;
	private JComboBox<String> cbMaterial, cbObras;
	private JComboBox<String> cbCategoria;
	private String nomeArtista;

	// Variavel caminho imagem criada para gravar e carregar na hora de procurar
	// obra
	private List<Obra> obras;

	private String caminhoImagem;
	private JComboBox<String> cbSetor;
	private static int contador = 1;

	private JComboBox<String> comboSetorT;
	private JComboBox<String> comboStatus;
	private JComboBox<String> comboStatusT;

	private JButton btnPesqArtist, btnNovoArtista, btnEditarArtista; // passar o
																		// resto
																		// dos
	// botoes
	private JPanel frmAcervo;
	private ArquivosController arqController;
	private PesqArtistaController pAController;

	public AcervoController(JTextField idObra, JLabel imagem, JComboBox<String> comboSetor,
			JComboBox<String> comboSetorT, JComboBox<String> comboStatus, JComboBox<String> comboStatusT,
			JComboBox<String> cbCategoria, JComboBox<String> cbMaterial, JTextField nomeArtista, JTextField nomeObra,
			JTextField dataAquisicao, JEditorPane descricaoObra, JLabel msgGravado, JLabel msgVazio,
			JTextField textField_valor, JButton btnPesqArtist, JPanel frmAcervo, JButton btnNovoArtista,
			JButton btnEditarArtista) {
		this.idObra = idObra;
		this.imagem = imagem;
		this.tfNomeArtista = nomeArtista;
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
		this.btnPesqArtist = btnPesqArtist;
		this.frmAcervo = frmAcervo;
		this.btnNovoArtista = btnNovoArtista;
		this.btnEditarArtista = btnEditarArtista;
		lerAcervo();
	}

	public AcervoController(JTextField nomeArtista, JLabel imagem, JComboBox cbObras, JTextField txtNovaObra,
			JComboBox<String> cbSetor2, JComboBox<String> cbMaterial2, JComboBox<String> cbCategoria2,
			JComboBox<String> comboStatus2, JTextField data_obra, JEditorPane editor_descricao, JLabel msgGravado2,
			JLabel msgVazio2, JTextField textField_valor2, JButton btnPesqArtist, JLabel status) {

		this.imagem = imagem;
		this.tfNomeArtista = nomeArtista;
		this.cbObras = cbObras;
		this.txtNovaObra = txtNovaObra;
		this.cbSetor = cbSetor2;
		this.cbMaterial = cbMaterial2;
		this.cbCategoria = cbCategoria2;
		this.comboStatus = comboStatus2;
		this.dataAquisicao = data_obra;
		this.descricaoObra = editor_descricao;
		this.msgGravado = msgGravado2;
		this.msgVazio = msgVazio2;
		this.btnPesqArtist = btnPesqArtist;

		this.textField_valor = textField_valor2;
		this.obras = new ArrayList<Obra>();
		this.lblStatus = status;

	}

	public void gerarIdSetor() {
		GeradordeID geraID = new GeradordeID();
		idObra.setText("ACV" + geraID.getIndice());
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
				if (s.contains("---")) {
					Artista artista = new Artista();
					artista.setNome(acervo.get(1));
					Obra obra = new Obra();
					obra.setIdObra(acervo.get(0));
					obra.setNomeObra(acervo.get(2));
					obra.setDescricaoObra(acervo.get(3));
					Categoria c = new Categoria();
					c.setNome(acervo.get(4));
					obra.setDataComposicao(acervo.get(5));
					obra.setImagem(acervo.get(6));
					Material material = new Material();
					material.setNome(acervo.get(7));
					Setor setor = new Setor();
					setor.setNome(acervo.get(8));
					obra.setPreco(acervo.get(9));
					obra.setProprietario(Boolean.parseBoolean(acervo.get(10)));
					obra.setStatus(acervo.get(11));
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

		/*
		 * for (Obra o : obras) { System.out.println(o.getNomeObra()); }
		 */

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
		if (tfNomeArtista.getText().isEmpty()) {
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
			} else {
				obra.setStatus((String) comboStatusT.getSelectedItem());
				setor.setNome((String) comboSetorT.getSelectedItem());
			}
			msgGravado.setVisible(true);
			msgVazio.setVisible(false);
			material.setNome((String) cbMaterial.getSelectedItem());
			categoria.setNome((String) cbCategoria.getSelectedItem());
			artista.setNome(tfNomeArtista.getText());
			obra.setNomeObra(nomeObra.getText());
			obra.setIdObra(idObra.getText());
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
		tfNomeArtista.setText(null);
		nomeObra.setText(null);
		textField_valor.setText(null);
		dataAquisicao.setText(null);
		descricaoObra.setText(null);
		cbMaterial.setSelectedIndex(0);
		cbCategoria.setSelectedIndex(0);
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
					m.setId(listString.get(0));
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
				if (s.contains("------")) {
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

	public void preencherComboBoxSetoresAlteraDel() {
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
			cbSetor.addItem(s.getNome());
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

	// Abre um JOptionPane com uma comboBox - Vitor
	public void pesquisarArtista() {
		pAController = new PesqArtistaController();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Artista> listArtista = new ArrayList<>();

		String[] possibilities = pAController.getArtista();

		for (String s : possibilities) {
			String text = s.replaceAll(".*:", "");
			listString.add(text);
			if (s.contains("---")) {
				Artista artista = new Artista();
				artista.setNome(listString.get(1));
				listArtista.add(artista);
				listString.clear();
			}
		}
		String[] possibilities2 = new String[listArtista.size()];

		for (int i = 0; i < listArtista.size(); i++) {
			possibilities2[i] = listArtista.get(i).getNome();
		}

		String s = (String) JOptionPane.showInputDialog(frmAcervo, "Escolha o artista:\n", "Pesquisar o Artista",
				JOptionPane.INFORMATION_MESSAGE, null, possibilities2, possibilities2[0]);
		if (s != null && s.length() > 0) {
			tfNomeArtista.setText(s);
			nomeArtista = s;
			return;
		}
	}


	private void abrirTelaNovoArtista() {
		FormRegisArtista newArtista = new FormRegisArtista(null, true);
		newArtista.setVisible(true);
		newArtista.setDefaultCloseOperation(newArtista.DISPOSE_ON_CLOSE);
		newArtista.setResizable(false);
	}

	private void abrirTelaEditarArtista() {
		FormAlteraDelArtista editArtista = new FormAlteraDelArtista(null, true);
		editArtista.setVisible(true);
		editArtista.setDefaultCloseOperation(editArtista.DISPOSE_ON_CLOSE);
		editArtista.setResizable(false);
	}

	// Controle de bot�es
	
	public ActionListener novaCategoria = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FormRegisCategoria formCate = new FormRegisCategoria();
			formCate.setVisible(true);
			formCate.setLocationRelativeTo(null);
		}
	};
	
	
	
	public ActionListener novoMaterial = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FormRegisMaterial frame = new FormRegisMaterial();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
	};
	
	
	
	public ActionListener editarMaterial = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FormAlteraDelMaterial frame = new FormAlteraDelMaterial();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
	};
	
	
	
	public ActionListener fecharTela = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	};
	
	
	
	public ActionListener editarCategoria = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FormAlteraDelCategoria frame = new FormAlteraDelCategoria();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
	};

	


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

	// Eventos de comboBox
	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		preencherComboBoxMaterial();
		preencherComboBoxSetores();
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand(); // verifica qual botao foi
												// pressionado na tela
		if (action.equals(btnPesqArtist.getText())) { // compara com o texto do
														// botao
			pesquisarArtista();
		} else if (action.equals(btnNovoArtista.getText())) {
			abrirTelaNovoArtista();
		} else if (action.equals(btnEditarArtista.getText())) {
			abrirTelaEditarArtista();
		}
	}
	
}