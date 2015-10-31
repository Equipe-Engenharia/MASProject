package controller;

import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import persistence.ObraArquivo;

public class AcervoEditCtrl {
	private JLabel imagem, msgGravado, msgVazio, lblStatus;
	private JTextField idObra, tfNomeArtista, nomeObra, dataAquisicao, textField_valor, txtNovaObra;
	private JEditorPane descricaoObra;
	private JComboBox<String> cbMaterial, cbObras;
	private JComboBox<String> cbCategoria;
	private String nomeArtista;
	private List<Obra> obras;
	private String caminhoImagem = "../MASProject/icons/painting.png";
	private JComboBox<String> cbSetor;
	private static int contador = 1;
	private JComboBox<String> comboStatus;
	private JButton btnPesqArtist;
	private JPanel frmAcervo;
	private ArquivosCtrl arqController;
	private ArtistaPesqCtrl pAController;
	
	
	public AcervoEditCtrl(JTextField nomeArtista, JLabel imagem, JComboBox cbObras, JTextField txtNovaObra,
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
					artista.setNome(tfNomeArtista.getText());
					o.setNomeObra((String) cbObras.getSelectedItem());
					o.setDataComposicao(dataAquisicao.getText());
					categoria.setNome((String) cbObras.getSelectedItem());
					setor.setNome((String) cbSetor.getSelectedItem());
					material.setNome((String) cbMaterial.getSelectedItem());
					o.setStatus((String) comboStatus.getSelectedItem());
					o.setDescricaoObra(descricaoObra.getText());
					o.setPreco(textField_valor.getText());
					o.setImagem(imagem.getText());

				} else {
					artista.setNome(tfNomeArtista.getText());
					o.setNomeObra(txtNovaObra.getText());
					o.setDataComposicao(dataAquisicao.getText());
					categoria.setNome((String) cbObras.getSelectedItem());
					setor.setNome((String) cbSetor.getSelectedItem());
					material.setNome((String) cbMaterial.getSelectedItem());
					o.setStatus((String) comboStatus.getSelectedItem());
					o.setDescricaoObra(descricaoObra.getText());
					o.setPreco(textField_valor.getText());
					o.setImagem(imagem.getText());
				}
				obras.get(i).setArtista(artista);
				obras.get(i).setCategoria(categoria);
				obras.get(i).setSetor(setor);
				obras.get(i).setMaterial(material);
				obras.get(i).setNomeObra(o.getNome());
				if (!(textField_valor.getText().isEmpty())) {
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
		msgGravado.setVisible(true);
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
		msgGravado.setText("Deletado com sucesso");
		msgGravado.setVisible(true);
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
	private void limpaCampos() {
		tfNomeArtista.setText(null);
		textField_valor.setText(null);
		dataAquisicao.setText(null);
		descricaoObra.setText(null);
		txtNovaObra.setText("Nome da Obra Atualizada");
		imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		imagem.setBackground(SystemColor.inactiveCaption);
		imagem.setHorizontalAlignment(SwingConstants.CENTER);
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
			tfNomeArtista.setText(s);
			setNomeArtista(s);
			return;
		}
	}
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
	public ActionListener remover_imagem = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			imagem.setIcon(new ImageIcon("../MASProject/icons/painting.png"));
		}
	};
	public String getNomeArtista() {
		return nomeArtista;
	}

	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
		recarregarCbObras(nomeArtista);
	}

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
		tfNomeArtista.setText(obra.getArtista().getNome());

		descricaoObra.setText(obra.getDescricao());
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
		for (int i = 0; i < comboStatus.getItemCount(); i++) {
			if (obra.getStatus().equalsIgnoreCase(comboStatus.getItemAt(i))) {
				comboStatus.setSelectedIndex(i);
			}
		}
		if (obra.isProprietario()) {
			textField_valor.setText(obra.getPreco());
		} else {
			textField_valor.setVisible(false);
			lblStatus.setText("");
		}

	}
	public ActionListener pesquisaArtistaEditar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarArtistaEditar();
		}
	};
	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (contador == 1) {
				txtNovaObra.setText(null);
				contador += 1;
			}
		}
	};
}
