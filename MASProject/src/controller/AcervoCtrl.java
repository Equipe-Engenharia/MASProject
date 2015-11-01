package controller;

import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import persistence.ObraArquivo;
import view.FrmArtistaCad;
import view.FrmArtistaEdit;
import view.FrmCategoriaCad;
import view.FrmCategoriaEdit;
import view.FrmMaterialCad;
import view.FrmMaterialEdit;
import view.FrmSetorCad;
import view.FrmSetorEdit;

public class AcervoCtrl implements ComponentListener, ActionListener {
	
	private JPanel frmAcervo;
	private JLabel imagem, msgGravar, msgVazio, lblStatus;
	private JTextField idObra, nomeArtista, nomeObra, dataAquisicao, txtValor, txtNovaObra;
	private JEditorPane edtDescricao;
	
	private JComboBox<String> cbMaterial, cbObras;
	private JComboBox<String> cbCategoria;
	private JComboBox<String> cbSetorT;
	private JComboBox<String> cbStatus;
	private JComboBox<String> cbStatusT;
	private JComboBox<String> cbSetor;
	
	private JButton btnPesqArtist, btnNovoArtista, btnEditarArtista, 
	btnNovaCategoria, btnNovoMaterial, btnEditarMaterial,
	btnNovoSetor, btnEditarCategoria, btnEditarSetor, 
	btnNovoSetorT, btnEditarSetorT; // passar o resto dos botoes
	
	private String artistaNome;
	private String caminhoImagem;
	@SuppressWarnings("unused")
	private static int contador = 1;
	
	private ArquivosCtrl arqController;
	private ArtistaPesqCtrl pAController;
	private List<Obra> obras;// Variavel caminho imagem criada para gravar e carregar na hora de procurar obra
	

	public AcervoCtrl(JPanel frmAcervo, JTextField idObra, JLabel imagem, JComboBox<String> cbSetor,
			JComboBox<String> cbSetorT, JComboBox<String> cbStatus, JComboBox<String> cbStatusT,
			JComboBox<String> cbCategoria, JComboBox<String> cbMaterial, JTextField nomeArtista, JTextField nomeObra,
			JTextField dataAquisicao, JEditorPane descricaoObra, JLabel msgGravar, JLabel msgVazio,
			JTextField txtValor, JButton btnPesqArtist, JButton btnNovoArtista, JButton btnEditarArtista,
			JButton btnNovaCategoria, JButton btnEditarCategoria, JButton btnNovoMaterial,JButton btnEditarMaterial, JButton btnNovoSetor, 
			JButton btnEditarSetor, JButton btnNovoSetorT, JButton btnEditarSetorT) {
		
		this.frmAcervo = frmAcervo;
		this.idObra = idObra;
		this.obras = new ArrayList<Obra>();
		this.imagem = imagem;
		this.nomeArtista = nomeArtista;
		this.nomeObra = nomeObra;
		this.dataAquisicao = dataAquisicao;
		this.edtDescricao = descricaoObra;
		this.cbMaterial = cbMaterial;
		this.cbCategoria = cbCategoria;
		this.cbSetor = cbSetor;
		this.cbSetorT = cbSetorT;
		this.cbStatus = cbStatus;
		this.cbStatusT = cbStatusT;
		this.btnPesqArtist = btnPesqArtist;
		this.btnNovoArtista = btnNovoArtista;
		this.btnEditarArtista = btnEditarArtista;
		this.btnNovaCategoria = btnNovaCategoria;
		this.btnEditarCategoria = btnEditarCategoria;
		this.btnNovoMaterial = btnNovoMaterial;
		this.btnEditarMaterial = btnEditarMaterial;
		this.btnNovoSetor = btnNovoSetor;
		this.btnEditarSetor = btnEditarSetor;
		this.btnNovoSetorT = btnNovoSetorT;
		this.btnEditarSetorT = btnEditarSetorT;
		this.txtValor = txtValor;
		this.msgGravar = msgGravar;
		this.msgVazio = msgVazio;
		this.caminhoImagem = "../MASProject/icons/painting.png";
		
		lerAcervo();
	}


	// MANIPULA CRUD ///////////////////////////////////////////////
	
	
	public void lerAcervo() {
		String linha = new String();
		ArrayList<String> acervo = new ArrayList<>();

		arqController = new ArquivosCtrl();
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
					obra.setId(acervo.get(0));
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
	}
	
	
	public void atualizaDados(List<Obra> listObras) {
		File f = new File("../MASProject/dados/acervo");
		f.delete();
		ObraArquivo obraImpl = new ObraArquivo();
		for (Obra obra : listObras) {
			try {
				obraImpl.escreveArquivo("../MASProject/dados/", "acervo", "", obra);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void editarAcervo(String nomeObra) {
		Artista artista = new Artista();
		Obra o = new Obra();
		Categoria categoria = new Categoria();
		Material material = new Material();
		Setor setor = new Setor();
		Obra obra = new Obra();
		for (Obra ob : obras) {
			if (nomeObra.equalsIgnoreCase(ob.getNome())) {
				obra = ob;
			}
		}
		for (int i = 0; i < obras.size(); i++) {
			if (obras.get(i).getNome().equalsIgnoreCase(obra.getNome())) {
				if (txtNovaObra.getText().isEmpty()
						|| txtNovaObra.getText().equalsIgnoreCase("Nome da Obra Atualizada")) {
					artista.setNome(nomeArtista.getText());
					o.setNomeObra((String) cbObras.getSelectedItem());
					o.setDataComposicao(dataAquisicao.getText());
					categoria.setNome((String) cbObras.getSelectedItem());
					setor.setNome((String) cbSetor.getSelectedItem());
					material.setNome((String) cbMaterial.getSelectedItem());
					o.setStatus((String) cbStatus.getSelectedItem());
					o.setDescricaoObra(edtDescricao.getText());
					o.setPreco(txtValor.getText());
					o.setImagem(imagem.getText());
				} else {
					artista.setNome(nomeArtista.getText());
					o.setNomeObra(txtNovaObra.getText());
					o.setDataComposicao(dataAquisicao.getText());
					categoria.setNome((String) cbObras.getSelectedItem());
					setor.setNome((String) cbSetor.getSelectedItem());
					material.setNome((String) cbMaterial.getSelectedItem());
					o.setStatus((String) cbStatus.getSelectedItem());
					o.setDescricaoObra(edtDescricao.getText());
					o.setPreco(txtValor.getText());
					o.setImagem(imagem.getText());
				}
				obras.get(i).setArtista(artista);
				obras.get(i).setCategoria(categoria);
				obras.get(i).setSetor(setor);
				obras.get(i).setMaterial(material);
				obras.get(i).setNomeObra(o.getNome());
				if (!(txtValor.getText().isEmpty())) {
					obras.get(i).setPreco(o.getPreco());
				}
				if (!(caminhoImagem.isEmpty()||caminhoImagem == null)) {
					obras.get(i).setImagem(caminhoImagem);
				}
				obras.get(i).setDescricaoObra(o.getDescricao());
				obras.get(i).setStatus(o.getStatus());
				obras.get(i).setDataComposicao(o.getDataComposicao());
			}
		}
		msgGravar.setVisible(true);
		limpaCampos();
		int delay = 2000; // delay de 5 seg.
		int interval = 1000; // intervalo de 1 seg.
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				msgGravar.setVisible(false);
				timer.cancel();
			}
		}, delay, interval);
		atualizaDados(obras);
		cbObras.removeAllItems();
		preencherComboBoxObrasNovo();
	}
	
	
	public void excluirObraAcervo(String nomeObra){
		for(int i = 0; i<obras.size(); i++){
			if (nomeObra.equalsIgnoreCase(obras.get(i).getNome())) {
				obras.remove(i);
			}
		}
		atualizaDados(obras);
		cbObras.removeAllItems();
		preencherComboBoxObrasNovo();
		msgGravar.setText("Deletado com sucesso");
		msgGravar.setVisible(true);
		limpaCampos();
		int delay = 2000; // delay de 5 seg.
		int interval = 1000; // intervalo de 1 seg.
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				msgGravar.setVisible(false);
				timer.cancel();
			}
		}, delay, interval);
	}
	
	
	public void gravarAcervo() {
		Obra obra = new Obra();
		ObraArquivo obraImpl = new ObraArquivo();
		Artista artista = new Artista();
		Categoria categoria = new Categoria();
		Material material = new Material();
		Setor setor = new Setor();
		obra.setProprietario(false);
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
			if (!(txtValor.getText().isEmpty())) {
				obra.setProprietario(true);
				obra.setStatus((String) cbStatus.getSelectedItem());
				setor.setNome((String) cbSetor.getSelectedItem());
			} else {
				obra.setStatus((String) cbStatusT.getSelectedItem());
				setor.setNome((String) cbSetorT.getSelectedItem());
			}
			msgGravar.setVisible(true);
			msgVazio.setVisible(false);
			material.setNome((String) cbMaterial.getSelectedItem());
			categoria.setNome((String) cbCategoria.getSelectedItem());
			artista.setNome(nomeArtista.getText());
			obra.setNomeObra(nomeObra.getText());
			obra.setId(idObra.getText());
			obra.setArtista(artista);
			obra.setSetor(setor);
			obra.setImagem(caminhoImagem);
			obra.setCategoria(categoria);
			obra.setDataComposicao(dataAquisicao.getText());
			obra.setDescricaoObra(edtDescricao.getText());
			obra.setMaterial(material);
			obra.setPreco(txtValor.getText());
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
					msgGravar.setVisible(false);
					timer.cancel();
				}
			}, delay, interval);
		}
	}
	
	
	
	// METODOS DE SUPORTE ///////////////////////////////////////////
	
	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idObra.setText("ACV" + id);
	}

	public void limpaCampos() {
		imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		imagem.setBackground(SystemColor.inactiveCaption);
		imagem.setHorizontalAlignment(SwingConstants.CENTER);
		nomeArtista.setText(null);
		nomeObra.setText(null);
		txtValor.setText(null);
		dataAquisicao.setText(null);
		edtDescricao.setText(null);
		cbMaterial.setSelectedIndex(0);
		cbCategoria.setSelectedIndex(0);
		cbSetor.setSelectedIndex(0);
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
	
	public String getNomeArtista() {
		return artistaNome;
	}
	
	public void setNomeArtista(String nomeArtista) {
		this.artistaNome = nomeArtista;
		recarregarCbObras(nomeArtista);
	}
	
	public void pesquisarArtistaEditar() {
		pAController = new ArtistaPesqCtrl();
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
			nomeArtista.setText(s);
			setNomeArtista(s);
			return;
		}
	}
	
	
	
	// PREENCHIMENTO COMBOBOX ////////////////////////////////
	
	public void preencherComboBoxObras() {
		obras.clear();
		lerAcervo();
		for (Obra o : obras) {
			cbObras.addItem(o.getNome());
		}
	}
	
	public void preencherComboBoxObrasNovo() {
		obras.clear();
		lerAcervo();
		for (Obra o : obras) {
			cbObras.addItem(o.getNome());
		}
	}
	
	public void pesquisarArtista() { // Abre um JOptionPane com uma comboBox - Vitor
			pAController = new ArtistaPesqCtrl();
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
				nomeArtista.setText(s);
				artistaNome = s;
				return;
			}
		}

	public void preencherComboBoxCategoria() {
		String linha = new String();
		arqController = new ArquivosCtrl();
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
	
	public void preencherComboBoxMaterial() {
		String linha = new String();
		arqController = new ArquivosCtrl();
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

	public void preencherComboBoxSetores() {
		String linha = new String();
		arqController = new ArquivosCtrl();
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
			cbSetorT.addItem(s.getNome());
		}
	}

	public void preencherComboBoxSetoresAlteraDel() {
		String linha = new String();
		arqController = new ArquivosCtrl();
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
		arqController = new ArquivosCtrl();
		try {
			arqController.leArquivo("../MASProject/dados", "status_obra_propria");
			linha = arqController.getBuffer();
			String[] proprio = linha.split(";");
			for (String s : proprio) {
				cbStatus.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preencherComboStatusTerceiro() {
		String linha = new String();
		arqController = new ArquivosCtrl();
		try {
			arqController.leArquivo("../MASProject/dados", "status_obra_terceiros");
			linha = arqController.getBuffer();
			String[] terceiro = linha.split(";");
			for (String s : terceiro) {
				cbStatusT.addItem(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

// CHAMADA DE TELA ////////////////////////////////////////////
	
	private void abrirTelaNovoArtista() {
		FrmArtistaCad newArtista = new FrmArtistaCad(null, true);
		newArtista.setVisible(true);
		newArtista.setDefaultCloseOperation(newArtista.DISPOSE_ON_CLOSE);
		newArtista.setResizable(false);
	}

	private void abrirTelaEditarArtista() {
		FrmArtistaEdit editArtista = new FrmArtistaEdit(null, true);
		editArtista.setVisible(true);
		editArtista.setDefaultCloseOperation(editArtista.DISPOSE_ON_CLOSE);
		editArtista.setResizable(false);
	}
	
	private void abrirTelaNovaCategoria() {
		FrmCategoriaCad newCategoria = new FrmCategoriaCad();
		newCategoria.setVisible(true);
		newCategoria.setDefaultCloseOperation(newCategoria.DISPOSE_ON_CLOSE);
		newCategoria.setResizable(false);
	}
	
	private void abrirTelaEditarCategoria() {
		FrmCategoriaEdit editCategoria = new FrmCategoriaEdit();
		editCategoria.setVisible(true);
		editCategoria.setDefaultCloseOperation(editCategoria.DISPOSE_ON_CLOSE);
		editCategoria.setResizable(false);
	}
	
	private void abrirTelaNovoMaterial() {
		FrmMaterialCad newMaterial = new FrmMaterialCad();
		newMaterial.setVisible(true);
		newMaterial.setDefaultCloseOperation(newMaterial.DISPOSE_ON_CLOSE);
		newMaterial.setResizable(false);
	}
	
	private void abrirTelaEditarMaterial() {
		FrmMaterialEdit editMaterial = new FrmMaterialEdit();
		editMaterial.setVisible(true);
		editMaterial.setDefaultCloseOperation(editMaterial.DISPOSE_ON_CLOSE);
		editMaterial.setResizable(false);
	}
	
	private void abrirTelaNovoSetor() {
		FrmSetorCad newSetor = new FrmSetorCad();
		newSetor.setVisible(true);
		newSetor.setDefaultCloseOperation(newSetor.DISPOSE_ON_CLOSE);
		newSetor.setResizable(false);
	}
	
	private void abrirTelaEditarSetor() {
		FrmSetorEdit editSetor = new FrmSetorEdit();
		editSetor.setVisible(true);
		editSetor.setDefaultCloseOperation(editSetor.DISPOSE_ON_CLOSE);
		editSetor.setResizable(false);
	}

	
	
	// CONTROLE BOTAO /////////////////////////////////////////
	
	public ActionListener novaCategoria = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FrmCategoriaCad formCate = new FrmCategoriaCad();
			formCate.setVisible(true);
			formCate.setLocationRelativeTo(null);
		}
	};
	
	public ActionListener novoMaterial = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FrmMaterialCad frame = new FrmMaterialCad();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
	};
	
	public ActionListener editarMaterial = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FrmMaterialEdit frame = new FrmMaterialEdit();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		}
	};
	
	public ActionListener pesquisaArtistaEditar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarArtistaEditar();
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
			FrmCategoriaEdit frame = new FrmCategoriaEdit();
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
	
	public ActionListener excluir_obraAcervo = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			excluirObraAcervo((String) cbObras.getSelectedItem());
		}
	};
	
	public ActionListener editar_acervo = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			editarAcervo((String) cbObras.getSelectedItem());
		}
	};

	public ActionListener pesquisarObra = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			procurarObra((String) cbObras.getSelectedItem());
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
	

	
	//EVENTOS COMBOBOX
	
	private void recarregarCbObras(String nomeArtista) {
		obras.clear();
		lerAcervo();
		cbObras.removeAllItems();
		for (Obra o : obras) {
			System.out.println(o.getNome());
			if (nomeArtista.equalsIgnoreCase(o.getArtista().getNome())) {
				cbObras.addItem(o.getNome());
			}
		}
	}

	public void procurarObra(String nomeObra) {
		Obra obra = new Obra();
		for (Obra o : obras) {
			if (o.getNome().equalsIgnoreCase(nomeObra)) {
				obra = o;
			}
		}
		System.out.println(obra.getArtista().getNome());
		preencheCampos(obra);
	}
	
	public void preencheCampos(Obra obra) {
		dataAquisicao.setText(obra.getDataComposicao());
		nomeArtista.setText(obra.getArtista().getNome());

		edtDescricao.setText(obra.getDescricao());
		ImageIcon img = new ImageIcon(obra.getImagem());
		Image newImg = img.getImage().getScaledInstance(imagem.getWidth(), imagem.getHeight(), Image.SCALE_DEFAULT);
		imagem.setIcon(new ImageIcon(newImg));
		for (int i = 0; i < cbCategoria.getItemCount(); i++) {
			if (obra.getCategoria().getNome().equalsIgnoreCase(cbCategoria.getItemAt(i))) {
				cbCategoria.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < cbSetor.getItemCount(); i++) {
			if (obra.getSetor().getNome().equalsIgnoreCase(cbSetor.getItemAt(i))) {
				cbSetor.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < cbMaterial.getItemCount(); i++) {
			if (obra.getMaterial().getNome().equalsIgnoreCase(cbMaterial.getItemAt(i))) {
				cbMaterial.setSelectedIndex(i);
			}
		}
		for (int i = 0; i < cbStatus.getItemCount(); i++) {
			if (obra.getStatus().equalsIgnoreCase(cbStatus.getItemAt(i))) {
				cbStatus.setSelectedIndex(i);
			}
		}
		if (obra.isProprietario()) {
			txtValor.setText(obra.getPreco());
		} else {
			txtValor.setVisible(false);
			lblStatus.setText("");
		}
	}
	
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
		String action = e.getActionCommand(); // verifica qual botao foi pressionado na tela
		if (action.equals(btnPesqArtist.getText())) { // compara com o texto do botao
			pesquisarArtista();
		} else if (action.equals(btnNovoArtista.getText())) {
			abrirTelaNovoArtista();
		} else if (action.equals(btnEditarArtista.getText())) {
			abrirTelaEditarArtista();
		} else if (action.equals(btnNovaCategoria.getText())) {
			abrirTelaNovaCategoria();
		} else if (action.equals(btnEditarCategoria.getText())) {
			abrirTelaEditarCategoria();
		} else if (action.equals(btnNovoMaterial.getText())) {
			abrirTelaNovoMaterial();
		} else if (action.equals(btnEditarMaterial.getText())) {
			abrirTelaEditarMaterial();
		} else if (action.equals(btnNovoSetor.getText())) {
			abrirTelaNovoSetor();
		} else if (action.equals(btnEditarSetor.getText())) {
			abrirTelaEditarSetor();
		} else if (action.equals(btnNovoSetorT.getText())) {
			abrirTelaNovoSetor();
		} else if (action.equals(btnEditarSetorT.getText())) {
			abrirTelaEditarSetor();
		}
	}
	
}