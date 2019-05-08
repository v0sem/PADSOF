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

	/**
	 * Constructor de la tabla con scroll
	 * @param titulos Cabeceras de la tabla
	 */
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
	
	/**
	 * Constructor de la tabla con scroll
	 * @param titulos Cabeceras de la tabla
	 * @param ancho Anchura de la tabla
	 * @param altura Altura de la tabla
	 */
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

	/**
	 * Inicializa la interfaz dada la tabla
	 * @param table Tabla a inicializar
	 */
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
	
	/**
	 * Insertar un solo elemento
	 */
	abstract public void insertSingle(T a);
	
	/**
	 * Insertar una lista de elementos
	 */
	abstract public void insertMultiple(List<? extends T> a);
	
	public T getByIndex(int fila) {
		return objectList.get(fila);
	}
	
	/**
	 * Obtener el elemento seleccionado de la tabla
	 * @return Elemento seleccionado de la tabla
	 */
	public T getSelected() {
		int index = table.getSelectedRow();
		
		return objectList.get(index);
	}
	
	/**
	 * Eliminar los contenidos de la tabla
	 */
	public void resetTable() {
		tableModel.setRowCount(0);
	}
	
	/**
	 * Eliminar un elemento de la tabla dado su indice
	 * @param poo elemento a eliminar
	 */
	public void delByIndex(PlayableObject poo) {
		if (!objectList.contains(poo)) {
			return;
		}
		
		int fila = objectList.indexOf(poo);
		
		objectList.remove(fila);
		tableModel.removeRow(fila);
	}
}