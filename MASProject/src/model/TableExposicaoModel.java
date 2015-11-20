package model;

import javax.swing.table.AbstractTableModel;

import controller.ObraArtistaCtrl;

public class TableExposicaoModel extends AbstractTableModel{

	/**
	 * 
	 */
	private ObraArtistaCtrl oAController = new ObraArtistaCtrl();
	private static final long serialVersionUID = 1L;
	private String [] columnNames = {"Obra", "Artista"};
	private Object[][] data = oAController.getObras();
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public void setData(Object [][] data){
		this.data = data;
	}
	
	public String getColumnName(int col){
		return columnNames[col];
	}
	
	public void setValueAt(Object value, int row, int col){
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

}
