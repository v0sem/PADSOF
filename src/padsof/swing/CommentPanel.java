package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import padsof.playable.CommentableObject;
import padsof.swing.items.StandardButton;

/**
 * Panel para agregar comentarios
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
@SuppressWarnings("serial")
public class CommentPanel extends JPanel {

	private JLabel title;
	
	private ScrollableJTableComment table;
	
	private JTextField newComment;
	
	private StandardButton add;
	
	private SideBarPanel sideBar;
	
	private SpringLayout layout;

	private CommentableObject s;
	
	public CommentPanel() {
		
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		// PANEL DESCRIPTION / TITLE
		title = new JLabel("Comments");
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 22));
		this.add(title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 100, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		
		newComment = new JTextField(20);
		this.add(newComment);
		
		add = new StandardButton("Comentar", 150, 20);
		this.add(add);
		
		table = new ScrollableJTableComment(500, 250);
		this.add(table);
		
		layout.putConstraint(SpringLayout.WEST, table, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, table, 80, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, newComment, 0, SpringLayout.WEST, table);
		layout.putConstraint(SpringLayout.NORTH, newComment, 5, SpringLayout.SOUTH, table);
		layout.putConstraint(SpringLayout.WEST, add, 6, SpringLayout.EAST, newComment);
		layout.putConstraint(SpringLayout.NORTH, add, 5, SpringLayout.SOUTH, table);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
	
	public SideBarPanel getSideBar() {
		return this.sideBar;
	}

	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
	
	public void setControlador(ActionListener controlador){
		this.add.addActionListener(controlador);
	}
	
	public void updateTables(CommentableObject song){
		this.s = song;
		this.remove(table);
		table.resetTable();
		table.insertMultiple(song.getCommentList());
		this.add(table);
		
		layout.putConstraint(SpringLayout.WEST, table, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, table, 80, SpringLayout.NORTH, this);
	}
	
	public JTextField getNewComment() {
		return newComment;
	}
	
	public CommentableObject getSong() {
		return s;
	}
	
}
