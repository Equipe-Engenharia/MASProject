package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class SetorEditCtrl {
	private JTextField txtDigiteOId, txtDigitadoN;
	private JButton btnPesqIdSet, btnPesqNomSet, btnGravar, btnExcluir;

	public SetorEditCtrl(JTextField txtDigiteOId, JTextField txtDigitadoN, JButton btnPesqIdSet,
			JButton btnPesqNomSet, JButton btnGravar, JButton btnExcluir) {
		this.txtDigiteOId = txtDigiteOId;
		this.txtDigitadoN = txtDigitadoN;
		this.btnPesqIdSet = btnPesqIdSet;
		this.btnPesqNomSet = btnPesqNomSet;
		this.btnGravar = btnGravar;
		this.btnExcluir = btnExcluir;

	}
	
 public void pesquisaIDSetor(){
	 
 }
 
 public void pesquisaNomeSetor(){
	 
 }
 
 public void gravarAlteracoesSetor(){
	 
 }
 
 public void excluirSetor(){
	 
 }
 
 public ActionListener pesquisaIDSetor = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pesquisaIDSetor();
	}
};

public ActionListener pesquisaNomeSetor = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pesquisaNomeSetor();
	}
};

public ActionListener gravarAlteracoesSetor = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gravarAlteracoesSetor();
	}
};

public ActionListener excluirSetor = new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		excluirSetor();
	}
};
 
 
	
	

}
