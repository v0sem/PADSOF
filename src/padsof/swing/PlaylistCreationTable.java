package padsof.swing;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class PlaylistCreationTable extends AbstractTableModel {
	private String[] titulos;
	private Object[][] filas;
	
	public PlaylistCreationTable(String[] titulos, Object[][] filas) {
		this.titulos = titulos;
		this.filas = filas;
	}
		
	@Override
	public int getRowCount() {
		return filas.length;
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return filas[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 0;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		filas[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	public Class getColumnClass (int col) {
		return getValueAt(0, col).getClass();
	}
}
