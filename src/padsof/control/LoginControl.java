package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.Status;
import padsof.swing.LoginPanel;
import padsof.swing.MainFrame;
import padsof.system.System;

/**
 * Controlador de eventos de Login
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class LoginControl implements ActionListener {

	/**
	 * Panel de Login
	 */
	private LoginPanel panel;

	/**
	 * Constructor de Clase
	 * 
	 * @param panel Login
	 */
	public LoginControl(LoginPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de Login
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String nick = this.panel.getNick().getText();
		String pass = new String(this.panel.getPassword().getPassword());
		if (nick.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (System.getInstance().login(nick, pass) == Status.OK) {
			JOptionPane.showMessageDialog(this.panel, "Entrando a la aplicacion como " + nick, "LOG-IN", JOptionPane.INFORMATION_MESSAGE);
			this.panel.getNick().setText("");
			this.panel.getPassword().setText("");
			MainFrame.getInstance().updateSideBar();
			MainFrame.getInstance().mostrarMainPanel(); //Actualiza sidebar por inicio de sesion
			panel.updateSideBar();
		} else {
			JOptionPane.showMessageDialog(this.panel, "Usuario o contrasena incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			this.panel.getNick().setText("");
			this.panel.getPassword().setText("");
		}
	}

}
