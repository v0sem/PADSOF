package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.swing.MainFrame;
import padsof.system.System;

public class UserInfoControl implements ActionListener {

	public UserInfoControl() {}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.getInstance().stop();
		System.getInstance().logout();
		MainFrame.getInstance().updateSideBar();
		MainFrame.getInstance().mostrarLogin();
	}

}
