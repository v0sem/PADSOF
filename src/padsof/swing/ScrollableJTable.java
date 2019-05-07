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
	List<PlayableObject> mapita;
	
	public ScrollableJTable(String[] titulos) {
		mapita = new ArrayList<PlayableObject>();
		
		tableModel = new DefaultTableModel(0, titulos.length);
		tableModel.setColumnIdentifiers(titulos);
		
		table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(550, 340));
		
		initializeUI(table);
	}
	
	public ScrollableJTable(String[] titulos, int altura, int ancho) {
		mapita = new ArrayList<PlayableObject>();
		
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
	
	public void meteleCiervo(PlayableObject po) {
		mapita.add(po);
		tableModel.addRow(new Object[]{
				po.getTitle(),
				po.getAuthor().getName(),
				po.getLength()
		});
	}
	
	public void meteleCiervos(List<? extends PlayableObject> pos) {
		for (PlayableObject po : pos) {
			meteleCiervo(po);
		}
	}
	
	public PlayableObject sacaleBrillo(int fila) {
		return mapita.get(fila);
	}
	
	public void borrateLa(PlayableObject poo) {
		if (!mapita.contains(poo)) {
			return;
		}
		
		int fila = mapita.indexOf(poo);
		
		mapita.remove(fila);
		tableModel.removeRow(fila);
	}
}