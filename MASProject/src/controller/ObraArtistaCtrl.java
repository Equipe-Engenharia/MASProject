package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

//Author: Vitor Fagundes Arantes
public class ObraArtistaCtrl extends DefaultTableModel implements ActionListener, ListSelectionListener{
	private JList<String> listObras;
	private JList<String> listObrasSelecionadas;
	private DefaultListModel<String> listModelObras;
	private DefaultListModel<String> listModelObrasSelecionadas;
	private JButton btnEnviarObras;
	private JButton btnMoveObra, btnUndoMoveObra;
	private JButton btnAddAll, btnRemoveAll;
	private String nomeArtista;
	private JLabel lblStatus;
	private JPanel contentPanel;
	private Object obra[];
	private String obras[];
//	private TableExposicaoModel tableModel;
	private DefaultTableModel tableModel;
	private JTable tableObras;
	private StringBuffer bufferObras;
	
	public ObraArtistaCtrl(JList<String> listObras, JList<String> listObrasSelecionadas,
			JButton btnEnviarObras, JButton btnMoveObra, JButton btnUndoMoveObra,
			String nomeArtista, JButton btnAddAll, JButton btnRemoveAll,
			DefaultListModel<String> listModelObras, 
			DefaultListModel<String> listModelObrasSelecionadas,
			JLabel lblStatus, JPanel contentPanel, DefaultTableModel tableModel,
			JTable tableObras, StringBuffer obras) {
		
		this.listObras = listObras;
		this.listObrasSelecionadas = listObrasSelecionadas;
		this.btnEnviarObras = btnEnviarObras;
		this.btnMoveObra = btnMoveObra;
		this.btnUndoMoveObra = btnUndoMoveObra;
		this.nomeArtista = nomeArtista;
		this.btnAddAll = btnAddAll;
		this.btnRemoveAll = btnRemoveAll;
		this.listModelObras = listModelObras;
		this.listModelObrasSelecionadas = listModelObrasSelecionadas;
		this.lblStatus = lblStatus;
		this.contentPanel = contentPanel;
		this.tableModel = tableModel;
		this.tableObras = tableObras;
		this.bufferObras = obras;
		initObrasModel();
	}
	
	public ObraArtistaCtrl(){}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); //verifica qual botão está solicitando a ação
		if(source == btnMoveObra){
			addItem();
			return;
		}
		if(source == btnUndoMoveObra){
			removeItem();
			return;
		}
		if(source == btnAddAll){
			addAllItems();
			return;
		}
		if(source == btnRemoveAll){
			removeAllItems();
			return;
		}
		if(source == btnEnviarObras){
			sendItems();
			return;
		}
	}

	private void sendItems() {
		int tamanho = listModelObrasSelecionadas.getSize();
		//Se a lista estiver vazia exibe mensagem
		if(tamanho == 0){
			JOptionPane.showMessageDialog(contentPanel, "Feche a janela ou adicione obras a lista", 
					"MASProject", JOptionPane.INFORMATION_MESSAGE, null);
			lblStatus.setText("Caro usuário adicione obras a lista");
			return;
		}
		//senão cria um novo vetor de objetos e seta os nomes das obras da JList direita
		obra = new Object[tamanho];
		Object nomeArtista[] = new Object[tamanho];
		//captura todos os itens da JList direita
		for(int i = 0; i < tamanho; i++){
			obra[i] = (listModelObrasSelecionadas.getElementAt(i));
			nomeArtista[i] = this.nomeArtista;
			System.out.println(obra[i]);
		}
		
		//verifica a situação da JTable
		//se não houver coluna, adiciona uma nova e o conteudo
		if(tableObras.getColumnCount() <= 0){
			tableModel.addColumn("Obra", obra);
			tableModel.addColumn("Artista", nomeArtista);
			//se já existe uma coluna com linhas, apenas adiciona as novas abaixo
		}else{
			int linhasOld = tableModel.getRowCount();
//			System.out.println("Old: " + linhasOld);
			tableModel.setRowCount(linhasOld + obra.length);
			for(int i = 0; i < obra.length; i++){
				tableModel.setValueAt(obra[i], linhasOld + i, 0);
				tableModel.setValueAt(nomeArtista, linhasOld + i, 1);
				tableModel.isCellEditable(i, 0);
				tableModel.isCellEditable(i, 1);
			}
		}
		tableObras.setModel(tableModel);
	}
	
	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
	public Object[] getObras(){
		return obra;
	}

	private void removeAllItems() {
		listModelObras.clear();
		initObrasModel();
		listModelObrasSelecionadas.clear();
		lblStatus.setText("Status: obras removidas com sucesso!");		
	}

	private void initObrasModel() {
		StringBuffer buffer = new StringBuffer();
		String recebeLinha = bufferObras.toString();
		String linhas[] = recebeLinha.split(";");
		for(int i = 0; i < linhas.length; i++){
			if(linhas[i].equals(nomeArtista)){
				buffer.append(linhas[i + 1] + ";");
			}
		}
		recebeLinha = buffer.toString();
		obras = recebeLinha.split(";");
		for(String s : obras){
			listModelObras.addElement(s);
		}
	}
	
	private void addAllItems() {
		listModelObrasSelecionadas.clear();
		for(String s : obras){
			listModelObrasSelecionadas.addElement(s);
		}
		listModelObras.clear();
		lblStatus.setText("Status: obras adicionadas com sucesso!");
	}

	private void removeItem() {
		int indiceLista = listObrasSelecionadas.getSelectedIndex();
		if(indiceLista == -1){
			lblStatus.setText("Status: sem obras para remover!");
			return;
		}
		
		String itemRemovido = listObrasSelecionadas.getSelectedValue();
		
		//Remove da JList direita
		listModelObrasSelecionadas.remove(indiceLista);
		
		//Adiciona na JList esquerda
		int tamanho = listModelObras.getSize();
		if(tamanho == 0){
			listModelObras.addElement(itemRemovido);
			lblStatus.setText("Status: obra " + itemRemovido + " removida com sucesso!");
			return;
		}
		
		//Encontra o maior item da lista e adicionar se possível
		for(int i = 0; i < tamanho; i++){
			String item = listModelObras.elementAt(i);
			int compara = itemRemovido.compareToIgnoreCase(item);
			if(compara < 0){
				listModelObras.add(i, itemRemovido);
				lblStatus.setText("Status: obra " + itemRemovido + " removida com sucesso!");
				return;
			}
		}
		
		//Adiciona no final da lista se o loop acima não funcionar
		listModelObras.addElement(itemRemovido);
		lblStatus.setText("Status: obra " + itemRemovido + " removida com sucesso!");

	}

	private void addItem() {
		int indiceLista = listObras.getSelectedIndex();
		//Se a lista for vazia, não acontece nada
		if(indiceLista == -1){
			lblStatus.setText("Status: sem obras para adicionar!");
			return;
		}
		//Se a lista não está vazia, a string recebe o item selecionado
		String itemSelecionado = (String) listObras.getSelectedValue();
		
		//Remover item da JList esquerda
		listModelObras.remove(indiceLista);
		//displaySelectedItems();
		
		//Adicionar item na JList direita
		int tamanho = listModelObrasSelecionadas.getSize();
		if(tamanho == 0){ //JList direita vazia
			listModelObrasSelecionadas.addElement(itemSelecionado);
			lblStatus.setText("Status: obra " + itemSelecionado + " adicionada com sucesso!");
			return;
		}
		
		//Encontrar o maior indice da JList direita para então conseguir adicionar um item
		for(int i = 0; i < tamanho; i++){
			String item = listModelObrasSelecionadas.elementAt(i);
			int compara = itemSelecionado.compareToIgnoreCase(item);
			if(compara < 0){
				listModelObrasSelecionadas.add(i, itemSelecionado);
				lblStatus.setText("Status: obra " + itemSelecionado + " adicionada com sucesso!");
				return;
			}
		}
		
		//Adiciona no fim se o loop acima não der certo
		listModelObrasSelecionadas.addElement(itemSelecionado);
		lblStatus.setText("Status: obra " + itemSelecionado + " adicionada com sucesso!");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object source = e.getSource(); //verifica qual JList está requisitando a ação
		if(source == listObras){
			btnUndoMoveObra.setEnabled(false);
			btnRemoveAll.setEnabled(false);
			btnMoveObra.setEnabled(true);
			btnAddAll.setEnabled(true);
			return;
		}
		if(source == listObrasSelecionadas){
			btnUndoMoveObra.setEnabled(true);
			btnRemoveAll.setEnabled(true);
			btnMoveObra.setEnabled(false);
			btnAddAll.setEnabled(false);
			return;
		}
	}
}