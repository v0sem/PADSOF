package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.swing.CommentPanel;
import padsof.system.System;
import padsof.user.User;

public class CommentControl implements ActionListener {

	private CommentPanel panel;

	public CommentControl(CommentPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = panel.getNewComment().getText();
		
		User u = System.getInstance().getLoggedUser();
		if(u == null) {
			JOptionPane.showMessageDialog(this.panel, "No estas loggeado");
			return;
		}
		
		if(text.isEmpty()) {
			JOptionPane.showMessageDialog(this.panel, "Hay que introducir texto");
			return;
		}
		
		panel.getSong().addComment(text);
		
		panel.getNewComment().setText("");
		
		panel.updateTables(panel.getSong());
	}

}
