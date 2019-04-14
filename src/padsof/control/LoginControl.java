package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import padsof.swing.LoginPanel;
import padsof.swing.MainPanel;
import padsof.Status;
import padsof.sistem.Sistem;

public class LoginControl implements ActionListener {

	private LoginPanel panel;

	public LoginControl(LoginPanel panel, MainPanel mainPanel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nick = this.panel.getNick().getText();
		String pass = new String(this.panel.getPassword().getPassword());
		if (nick.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (Sistem.getInstance().login(nick, pass) == Status.OK) {
			JOptionPane.showMessageDialog(this.panel, "Entrando a la aplicacion como " + nick, "LOG-IN", JOptionPane.INFORMATION_MESSAGE);
			this.panel.getNick().setText("");
			this.panel.getPassword().setText("");
			// TODO: this.mainPanel.metodoParaMostrarElHome();  mostrarlo segun el tipo del usuario logueado
		} else {
			JOptionPane.showMessageDialog(this.panel, "Usuario o contrasena incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			this.panel.getNick().setText("");
			this.panel.getPassword().setText("");
		}
	}

}