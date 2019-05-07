package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import padsof.Status;
import padsof.swing.AdminPanel;
import padsof.swing.LoginPanel;
import padsof.swing.MainFrame;
import padsof.system.System;

public class AdminControl implements ActionListener {

	private AdminPanel panel;

	public AdminControl(AdminPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		Double prePrice;
		Long preRepro, reproReg, reproAnon;
		
		if (event.getSource().equals(panel.getPendientes())) {
			MainFrame.getInstance().mostrarMainPanel(); //Panel Pendientes
		} else if (event.getSource().equals(panel.getGuardar())) {
			System sys = System.getInstance();
			
			try {
				prePrice = Double.parseDouble(panel.getPremiumEurosBox().getText());
				preRepro = Long.parseLong(panel.getPremiumReproBox().getText());
				reproReg = Long.parseLong(panel.getReproRegisBox().getText());
				reproAnon = Long.parseLong(panel.getReproAnonBox().getText());
			} catch (NumberFormatException numberE) {
				JOptionPane.showMessageDialog(this.panel, "Error en el input");
				return;
			}
			
			sys.setPremiumPrice(prePrice);
			sys.setPlaysToPremium(preRepro);
			sys.setMaxRegisteredSong(reproReg);
			sys.setMaxAnonSong(reproAnon);
			
			try {
				sys.saveData();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.panel, "Ha habido algun error :/");
				return;
			}
			
			JOptionPane.showMessageDialog(this.panel, "Datos cambiados");
		}
	}
}
