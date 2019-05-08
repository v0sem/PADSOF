package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.FollowingPanel;
import padsof.swing.MainFrame;
import padsof.system.System;
import padsof.user.User;

public class FollowingControl implements ActionListener {

	private FollowingPanel panel;

	public FollowingControl(FollowingPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		User u = System.getInstance().getLoggedUser();
		if (u != null) u.unfollow(panel.getTable().getSelected());
		MainFrame.getInstance().mostrarFollowing();
	}

}
