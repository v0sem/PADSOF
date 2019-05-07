package padsof.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import padsof.playable.PlayableObject;

import java.awt.*;

@SuppressWarnings("serial")
public class ScrollableJTable extends JPanel {
	JTable table;
	DefaultTableModel tableModel;
	List<PlayableObject> songList;
	
	public ScrollableJTable(String[] titulos) {
		songList = new ArrayList<PlayableObject>();
		
		tableModel = new DefaultTableModel(0, titulos.length);
		tableModel.setColumnIdentifiers(titulos);
		
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(550, 340));
		
		initializeUI(table);
	}
	
	public ScrollableJTable(String[] titulos, int ancho, int altura) {
		songList = new ArrayList<PlayableObject>();
		
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
	
	public void insertSingle(PlayableObject po) {
		songList.add(po);
		tableModel.addRow(new Object[]{
				po.getTitle(),
				po.getAuthor().getName(),
				po.getLength()
		});
	}
	
	public void insertMultiple(List<? extends PlayableObject> pos) {
		for (PlayableObject po : pos) {
			insertSingle(po);
		}
	}
	
	public PlayableObject getSongByIndex(int fila) {
		return songList.get(fila);
	}
	
	public void resetTable() {
		tableModel.setRowCount(0);
	}
	
	public void delSongByIndex(PlayableObject poo) {
		if (!songList.contains(poo)) {
			return;
		}
		
		int fila = songList.indexOf(poo);
		
		songList.remove(fila);
		tableModel.removeRow(fila);
	}
}