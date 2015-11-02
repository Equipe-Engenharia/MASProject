package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Categoria;
import model.Material;
import persistence.MaterialArquivo;

public class MaterialCtrl implements ComponentListener {

	//private JPanel frmMaterial;
	private JTextField idMaterial, nomeMaterial;
	private JComboBox<String> cbCategoria;
	private JButton btApagar, btGravar;
	//private JLabel msgGravar, msgVazio;
	private List<Material> materiais;
	private static int contador = 1;
	private ArquivosCtrl ctrlArquivos;
	private String validar;

	public MaterialCtrl(JPanel frmMaterial, JTextField idMaterial, JComboBox<String> cbCategoria, JTextField txtMaterial,
			JButton btnApagar, JButton btnGravar, JLabel msgGravado, JLabel msgVazio) {

		//this.frmMaterial = frmMaterial;
		this.cbCategoria = cbCategoria;
		this.idMaterial = idMaterial;
		this.btApagar = btnApagar;
		this.btGravar = btnGravar;
		//this.msgGravar = msgGravado;
		//this.msgVazio = msgVazio;
		this.nomeMaterial = txtMaterial;
		this.materiais = new ArrayList<Material>();
		
		lerMaterial();
	}

	
	// METODOS DE SUPORTE ////////////////////////

	
	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		idMaterial.setText("MAT" + id);
	}
	
	public void limpaCampos() {
		nomeMaterial.setText(null);
		idMaterial.setText(null);	
		//cbCategoria.setSelectedIndex(0);
	}
	
	public void msg(String tipo){
		
		switch (tipo) {

		case "erroVazio":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nCampo Vazio", "Registro de Material",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "okPesquisa":
				if (JOptionPane.showConfirmDialog(null, "Registro " + nomeMaterial.getText() + " localizado com sucesso!\nGostaria de apagá-lo?",
					"Registro de Material", JOptionPane.OK_CANCEL_OPTION)== JOptionPane.OK_OPTION){
					apagarMaterial(idMaterial.getText(),nomeMaterial.getText());
					} else {
						editarMaterial(nomeMaterial.getText());
					}
			break;
		case "noPesquisa":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\n\nNão localizamos o registro: ' " + idMaterial.getText()
			+ " " + nomeMaterial.getText() + " ' !\nVerifique sua digitação.", "Pesquisa de Material",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;	
		case "erroPesquisa":
			JOptionPane.showMessageDialog(null, "ATENÇÃO! Por favor, use um dos campos de Pesquisa!", "Registro de Material",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "okGrava":
			JOptionPane.showMessageDialog(null, "Registro '" + nomeMaterial.getText() + "' salvo com sucesso.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "erroGrava":
			JOptionPane.showMessageDialog(null, "ATENÇÃO!\nNão foi possível apagar o registro: " + idMaterial.getText()
			+ " " + nomeMaterial.getText() + "!\nVerifique sua digitação!", "Registro de Material",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;
		case "okApaga":
			JOptionPane.showMessageDialog(null, "Registro apagado com sucesso.",
					"Registro de Material", JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/record.png"));
			break;
		case "teste":
			JOptionPane.showMessageDialog(null, "TESTE: ", "Teste do Controller Material",
			JOptionPane.PLAIN_MESSAGE, new ImageIcon("../MASProject/icons/warning.png"));
			break;

		default:
			JOptionPane.showMessageDialog(null,
					"OOPS!\n\nQue feio, Ed Stark perdeu a cabeça, e algo não deveria ter acontecido…\n\nVolte ao trabalho!!!",
					"Erro no Controller Material", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("../MASProject/icons/warning.png"));
		}
	}

	
	//PREENCHE COMBOBOX /////////////////////
	
	
	public void preencherComboBoxCategoria() {
		String linha = new String();
		ctrlArquivos = new ArquivosCtrl();
		ArrayList<String> listString = new ArrayList<>();
		ArrayList<Categoria> listCategorias = new ArrayList<>();

		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "categorias");
			linha = ctrlArquivos.getBuffer();
			String[] categorias = linha.split(";");
			for (String s : categorias) {
				String text = s.replaceAll(".*: ", ""); //ERRO NA RECUPERAÇÃO DO REGISTRO - PRECISA DE 2 ESPAÇOS DEPOIS DO *:
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
	
	
	// CRUD //////////////////////////
	
	
	public void lerMaterial() {
		String linha = new String();
		ArrayList<String> list = new ArrayList<>();

		ctrlArquivos = new ArquivosCtrl();
		try {
			ctrlArquivos.leArquivo("../MASProject/dados/", "materiais");
			linha = ctrlArquivos.getBuffer();
			String[] listaMaterial = linha.split(";");
			for (String s : listaMaterial) {
				String text = s.replaceAll(".*: ", ""); //ERRO QUE IMPEDE A EXCLUSÃO DO REGISTRO - PRECISA DE 2 ESPAÇOS DEPOIS DO *:
				list.add(text);
				if (s.contains("---")) {
					Material material = new Material();
					material.setId(list.get(0));
					material.setNome(list.get(1));
					material.setCategoria(list.get(2));
					materiais.add(material);
					list.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*for(Material m :  materiais){
			System.out.println(m.getNome());
		}*/
	}
	
	
	public void atualizaDados(List<Material> listMateriais) {
		File f = new File("../MASProject/dados/materiais");
		f.delete();
		MaterialArquivo materialImpl = new MaterialArquivo();
		for (Material material : listMateriais) {
			try {
				materialImpl.escreveArquivo("../MASProject/dados/", "materiais", "", material);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void pesquisarMaterial(String material) {

		if (!nomeMaterial.getText().isEmpty() || !idMaterial.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (material.equalsIgnoreCase(materiais.get(i).getId())) {
					validar = "ok";
				} else if (material.equalsIgnoreCase(materiais.get(i).getNome())) {
					validar = "ok";
				}
			}
			if (validar == "ok") {
				// msgGravar.setText(nomeMaterial.getText() + " localizado com
				// sucesso!");
				// msgGravar.setVisible(true);
				msg("okPesquisa");
				//nomeMaterial.setText(null);
			} else {
				// msgGravar.setVisible(false);
				// msgVazio.setText("Por favor, use um dos campos de
				// Pesquisa!");
				// msgVazio.setVisible(true);
				msg("noPesquisa");
//				btApagar.setEnabled(false);
//				btGravar.setEnabled(false);
				validar = "";
			}
		} else {
			msg("erroPesquisa");
		}
	}
	
	
	public void editarMaterial(String material){
		Material m = new Material();
		if (!nomeMaterial.getText().isEmpty() || !idMaterial.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (material.equalsIgnoreCase(materiais.get(i).getNome())) {
					idMaterial.setText(materiais.get(i).getId().toString());
					nomeMaterial.setText(materiais.get(i).getNome().toString());
					cbCategoria.setSelectedItem(materiais.get(i).getCategoria());
					
					m.setId(idMaterial.getText());
					m.setNome(nomeMaterial.getText());
					m.setCategoria((String) cbCategoria.getSelectedItem());
					
				}
//				msg(materiais.get(i).toString());
//				materiais.get(i).setId(m.getId());
//				materiais.get(i).setNome(m.getNome());
//				materiais.get(i).setCategoria(m.getCategoria());
			}
			
			//msg("teste");
			//limpaCampos();
			int delay = 2000; // DELAY 5 SEG
			int interval = 1000; // INTERVALO 1 SEG
			final Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					// msgGravar.setVisible(false);
					timer.cancel();
				}
			}, delay, interval);
			atualizaDados(materiais);
			cbCategoria.removeAllItems();
			preencherComboBoxCategoria();
		}
	}
	
	
	public void apagarMaterial(String id, String material) {
		if (!nomeMaterial.getText().isEmpty() || !idMaterial.getText().isEmpty()) {
			for (int i = 0; i < materiais.size(); i++) {
				if (id.equalsIgnoreCase(materiais.get(i).getId())) {
					materiais.remove(i);
					validar = "ok";
				} else if (material.equalsIgnoreCase(materiais.get(i).getNome())) {
					materiais.remove(i);
					validar = "ok";
				}
			}
			if (validar == "ok") {
				atualizaDados(materiais);
				// msgGravar.setText("Deletado com sucesso");
				// msgGravar.setVisible(true);
				msg("okApaga");
				limpaCampos();
				int delay = 2000; // DELAY 5 SEG
				int interval = 1000; // INTERVALO 1 SEG
				final Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						// msgGravar.setVisible(false);
						timer.cancel();
					}
				}, delay, interval);
			} else {
				validar = "";
				msg("erroApaga");
			}
		} else {
			msg("erroVazio");
		}
	}
	

	public void gravarMaterial() {
		Material material = new Material();
		MaterialArquivo materialImpl = new MaterialArquivo();

		material.setId(idMaterial.getText());
		material.setNome(nomeMaterial.getText());
		material.setCategoria(cbCategoria.getSelectedItem().toString());

		if (!nomeMaterial.getText().isEmpty()) {
			try {
				materialImpl.escreveArquivo("../MASProject/dados/", "materiais", nomeMaterial.getText(), material);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//msgGravar.setText(nomeMaterial.getText() + " salvo com sucesso!");
			//msgGravar.setVisible(true);
			msg("okGrava");
			nomeMaterial.setText(null);
			gerarId();
		} else {
			//msgGravar.setVisible(false);
			//msgVazio.setVisible(true);
			msg("erroVazio");
		}
	}
	
	
	// CONTROLE BOTAO //////////////////////////////

	public ActionListener pesquisarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisarMaterial(nomeMaterial.getText());
		}
	};
	
	public ActionListener apagarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			apagarMaterial(idMaterial.getText(),nomeMaterial.getText());
		}
	};
	
	public ActionListener gravarMaterial = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravarMaterial();
		}
	};

	
	// CONTROLE MOUSE ///////////////////////////////
	
	public MouseListener limpaCampo = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (contador == 1) {
				nomeMaterial.setText(null);
				contador += 1;
			}
			//btApagar.setEnabled(true);
			//btGravar.setEnabled(true);
			//msgGravar.setVisible(false);
			//msgVazio.setVisible(false);
		}
	};

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

}