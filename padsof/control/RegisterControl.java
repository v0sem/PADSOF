package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import padsof.swing.RegisterPanel;
import padsof.swing.MainFrame;
import padsof.Status;
import padsof.system.System;

public class RegisterControl implements ActionListener {

	private RegisterPanel panel;

	public RegisterControl(RegisterPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String nick = this.panel.getNick().getText();
		String name = this.panel.getAuthorName().getText();
		String pass = new String(this.panel.getPassword().getPassword());
		String dateText = this.panel.getDate().getText();
		if (nick.isEmpty() || pass.isEmpty() || name.isEmpty() || dateText.isEmpty()) {
			JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				if (System.getInstance().register(name, nick, date, pass) == Status.OK) {
					JOptionPane.showMessageDialog(this.panel, "Registrado el usuario " + nick, "Register", JOptionPane.INFORMATION_MESSAGE);
					MainFrame.getInstance().mostrarMainPanel();
				} else {
					JOptionPane.showMessageDialog(this.panel, "Nick o nombre de autor invalidos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(this.panel, "Introduce una fecha en formato dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
			}
			this.panel.getNick().setText("");
			this.panel.getAuthorName().setText("");
			this.panel.getPassword().setText("");
			this.panel.getDate().setText("");
		}
	}
}
