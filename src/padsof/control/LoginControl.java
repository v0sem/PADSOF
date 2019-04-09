package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import padsof.swing.LoginPanel;
import padsof.swing.MainPanel;

public class LoginControl implements ActionListener {

	private LoginPanel panel;

	public LoginControl(LoginPanel panel, MainPanel mainPanel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this.panel, "Transicion de ejemplo", "LOG-IN",
				JOptionPane.INFORMATION_MESSAGE);
		
	}

}
