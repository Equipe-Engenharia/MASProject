package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.AcervoMdl;
import model.ArtistaMdl;
import model.EmprestimoMdl;
import model.ObraMdl;
import persistence.ArtistaFile;
import persistence.EmprestimoFile;

public class EmprestimoCtrl {
	private JTextField txtCod,txtDataEmp,txtMuseu,txtRespTec,txtTel,txtDoc,txtNum,txtId;
	private List<AcervoMdl> acervo;
	private List<ObraMdl> obra;
	private JPanel form;
	private JTextField  txtNome;
	private List<ArtistaMdl> artistas;
	private List<EmprestimoMdl> emprestimos;
	private static int contador = 1;
	private boolean validar;
	private ArquivosCtrl arquivos = new ArquivosCtrl();
	EmprestimoFile arquivo = new EmprestimoFile();
	private String[] artista;
	private String[]emprestimo;
	
	
	public EmprestimoCtrl(JPanel form, JTextField Cod,JTextField txtDataEmp,JTextField txtMuseu,JTextField txtRespTec,JTextField txtTel,JTextField txtDoc,JTextField txtNum) {
		
		
        this.txtNum=txtNum;
		this.txtCod = txtCod;
		this.txtDataEmp=txtDataEmp;
		this.txtDoc=txtDoc;
		this.txtMuseu=txtMuseu;
		this.txtRespTec=txtRespTec;
		this.txtTel=txtTel;
		this.acervo = new ArrayList<AcervoMdl>();
		this.obra = new ArrayList<ObraMdl>();
		//lerArquivo();
	}
	
	public EmprestimoCtrl() {
		// TODO Auto-generated constructor stub
	}

	AcervoCtrl acerv=new AcervoCtrl();
	EmprestimoCtrl emprest= new EmprestimoCtrl();

	public void gerarId() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String NewId = (dateFormat.format(date));
		txtCod.setText("Emp" + NewId);
	}
	
	public String[] preencherComboBoxArtista
(){
		String linha = new String();
		String nArtista[] = null; 
		StringBuffer nomeArtista;
		arquivos = new ArquivosCtrl();
		try {
			arquivos.leArquivo("../MASProject/dados", "acervo");
			linha = arquivos.getBuffer();
			nArtista = linha.split(";");
			nomeArtista = new StringBuffer();
			for(String nome : nArtista){
				if(nome.contains("Nome do Artista")){
					nomeArtista.append(nome.substring(10));
					nomeArtista.append(";");
				}
			}
			linha = nomeArtista.toString();
			nArtista = linha.split(";");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return nArtista;
		
	}

	 public String[] preencherComboBoxObra(JTextField artista)
{
		/// Como associar o nome do artista escolhido no textfield"artista" as obras
		String linha = new String();
		String nObra[] = null; 
		StringBuffer nomeObra;
		arquivos = new ArquivosCtrl();
		try {
			arquivos.leArquivo("../MASProject/dados", "acervo");
			linha = arquivos.getBuffer();
			nObra = linha.split(";");
			nomeObra = new StringBuffer();
			for(String nome : nObra){
					
				if( nome.contains("Nome da Obra")){
					nomeObra.append(nome.substring(10));
					nomeObra.append(";");
				}
			}
		 
			linha = nomeObra.toString();
			nObra = linha.split(";");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nObra;
	}
	

	public void atualizaDados(List<EmprestimoMdl> listEmprestimos) {
		File f = new File("../MASProject/dados/Emprestimos");
		f.delete();	
		for (EmprestimoMdl emprestimos : listEmprestimos) {
			try {
				arquivo.escreveArquivo("../MASProject/dados/", "Emprestimos", "", emprestimos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void limpaCampos() {
		txtCod.setText(null);
		txtNum.setText(null);
		txtDataEmp.setText(null);
		txtDoc.setText(null);
		txtMuseu.setText(null);
		txtRespTec.setText(null);
		txtTel.setText(null);
	}
	
	

	public void editar() {
		
		//
		EmprestimoMdl emp=new EmprestimoMdl();
		validar = false;
		if (!txtCod.getText().isEmpty()) {
			for (int i = 0; i < emprestimos.size(); i++) {
				if (!txtCod.getText().equalsIgnoreCase(emprestimos.get(i).getCodEmp()) ) {				
					msg("erroredit",emprestimos.get(i).getCodEmp());
					validar = true;
				} 
			}
			if(!(validar == true)){
				for (int i = 0; i < emprestimos.size(); i++) {
					if (txtCod.getText().equalsIgnoreCase(emprestimos.get(i).getCodEmp())) {
						
						emp.setCodEmp(txtCod.getText());
						emp.setDataEmp(txtDataEmp.getText());
						emp.setDoc(txtDoc.getText());
						emp.setMuseu(txtMuseu.getText());
						emp.setNum(txtNum.getText());
						emp.setRespTec(txtRespTec.getText());
						emp.setTel(txtTel.getText());
						emprestimos.set(i, emp);
						atualizaDados(emprestimos);
						msg("edit", txtNome.getText());
						limpaCampos();
					}
				}
			}
			

		} else {
			msg("errorsearch", txtCod.getText());
		}
	}

	

	public void gravar() {
		new EmprestimoFile();
		EmprestimoMdl emprestimo = new EmprestimoMdl();
		validar = false;
		if (!txtCod.getText().isEmpty()) {
			for (int i = 0; i < emprestimos.size(); i++) {
				if (txtCod.getText().equalsIgnoreCase(emprestimos.get(i).getCodEmp())){
					msg("erroredit", emprestimos.get(i).getCodEmp());
					validar = true;
				}
			}
			if(!(validar == true)){	
			emprestimo.setCodEmp(txtCod.getText());
			emprestimo.setDataEmp(txtDataEmp.getText());
			emprestimo.setMuseu(txtMuseu.getText());
			emprestimo.setTel(txtTel.getText());
			emprestimo.setRespTec(txtRespTec.getText());
			emprestimo.setDoc(txtDoc.getText());
			emprestimo.setNum(txtNum.getText());
			emprestimos.add(emprestimo);
			msg("save", txtNome.getText());
			atualizaDados(emprestimos);
			limpaCampos();
			gerarId();
			}
		} else {
			msg("errornull", txtCod.getText());
		}
	}

	// CONTROLE BOTAO //////////////////////////////

	public ActionListener pesquisar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			pesquisar();
		}
	};

	public ActionListener excluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
				excluir();
		}
	};

	public ActionListener editar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			editar();
		}
	};

	public ActionListener gravar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gravar();
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
				txtNome.setText(null);
				contador += 1;
			}
		}
	};

	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

}





















