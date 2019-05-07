package padsof.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import padsof.playable.PlayableObject;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class ScrollableJTable<T> extends JPanel {
	JTable table;
	DefaultTableModel tableModel;
	List<T> objectList;
	
	public ScrollableJTable(String[] titulos) {
		objectList = new ArrayList<T>();
		
		tableModel = new DefaultTableModel(0, titulos.length);
		tableModel.setColumnIdentifiers(titulos);
		
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(550, 340));
		
		initializeUI(table);
	}
	
	public ScrollableJTable(String[] titulos, int ancho, int altura) {
		objectList = new ArrayList<T>();
		
		tableModel = new DefaultTableModel(0, titulos.length);
		tableModel.setColumnIdentifiers(titulos);
		
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(ancho, altura));
		
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
	
	public T getSongByIndex(int fila) {
		return objectList.get(fila);
	}
	
	public void resetTable() {
		tableModel.setRowCount(0);
	}
	
	public void delSongByIndex(PlayableObject poo) {
		if (!objectList.contains(poo)) {
			return;
		}
		
		int fila = objectList.indexOf(poo);
		
		objectList.remove(fila);
		tableModel.removeRow(fila);
	}
}