package padsof.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import padsof.playable.Song;
import padsof.playable.SongState;
import padsof.system.System;

@SuppressWarnings("serial")
public class MySongsPanel extends JPanel {

	private SideBarPanel sideBar;
	
	ScrollableJTable acceptedTable;
	
	ScrollableJTable pendingTable;
	
	public MySongsPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		acceptedTable = new ScrollableJTable(new String[]{"a", "b", "c"}, 550, 340);
		this.add(acceptedTable);
		pendingTable = new ScrollableJTable(new String[]{"a", "b", "c"}, 550, 340);
		this.add(pendingTable);
		
		List<Song> acceptedList = new ArrayList<>();
		List<Song> pendingList = new ArrayList<>();
		for (Song s : System.getInstance().getSongList()) {
			if (s.getAuthor() == System.getInstance().getLoggedUser()) {
				if (s.getState() == SongState.ACCEPTED) acceptedList.add(s);
				else pendingList.add(s);
			}
		}
		acceptedTable.insertMultiple(acceptedList);
		pendingTable.insertMultiple(pendingList);
		
		layout.putConstraint(SpringLayout.WEST, acceptedTable, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, acceptedTable, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, acceptedTable, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, acceptedTable, 370, SpringLayout.NORTH, this);
	}
	
	public SideBarPanel getSideBar() {
		return this.sideBar;
	}

	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
