package padsof.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import padsof.playable.PlayableObject;

/**
 * Panel abstracto de tabla con scroll. Permite la 
 * insercion de elementos genericos
 *  
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public abstract class ScrollableJTable<T> extends JPanel {
	JTable table;
	DefaultTableModel tableModel;
	List<T> objectList;

	public ScrollableJTable(String[] titulos) {
		objectList = new ArrayList<T>();
		
		tableModel = new DefaultTableModel(0, titulos.length) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
			
		};
		
		tableModel.setColumnIdentifiers(titulos);

		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(550, 340));
		table.getTableHeader().setReorderingAllowed(false);
		
		initializeUI(table);
	}
	
	public ScrollableJTable(String[] titulos, int ancho, int altura) {
		objectList = new ArrayList<T>();
		
		tableModel = new DefaultTableModel(0, titulos.length) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
			
		};
		
		tableModel.setColumnIdentifiers(titulos);
		
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(ancho, altura));
		table.getTableHeader().setReorderingAllowed(false);
		
		initializeUI(table);
	}

	private void initializeUI(JTable table) {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		setPreferredSize(new Dimension(table.getPreferredSize().width + 20,
			table.getPreferredSize().height));

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setDragEnabled(false);

		JScrollPane pane = new JScrollPane(table);
		add(pane, BorderLayout.CENTER);
	}
	
	abstract public void insertSingle(T a);
	
	abstract public void insertMultiple(List<? extends T> a);
	
	public T getByIndex(int fila) {
		return objectList.get(fila);
	}
	
	public T getSelected() {
		int index = table.getSelectedRow();
		
		return objectList.get(index);
	}
	
	public void resetTable() {
		tableModel.setRowCount(0);
	}
	
	public void delByIndex(PlayableObject poo) {
		if (!objectList.contains(poo)) {
			return;
		}
		
		int fila = objectList.indexOf(poo);
		
		objectList.remove(fila);
		tableModel.removeRow(fila);
	}
}