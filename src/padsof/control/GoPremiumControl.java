package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import padsof.Status;
import padsof.swing.GoPremiumPanel;
import padsof.swing.MainFrame;
import padsof.system.System;

/**
 * Controlador de Go Premium
 * 
 * @author David Palomo, Pablo Sanchez, Antonio Solana
 *
 */
public class GoPremiumControl implements ActionListener {

	/**
	 * Panel Go Premium
	 */
	private GoPremiumPanel panel;

	/**
	 * Constructor de clase
	 *  
	 * @param panel Go premium panel
	 */
	public GoPremiumControl(GoPremiumPanel panel) {
		this.panel = panel;
	}

	/**
	 * Accionador de eventos de Go Premium
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String fullname = this.panel.getFullName().getText();
		String creditCard = this.panel.getCreditCard().getText();
		if (fullname.isEmpty() || creditCard.isEmpty()) {
			JOptionPane.showMessageDialog(this.panel, "Es obligatorio rellenar ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (System.getInstance().getLoggedUser().goPremium(creditCard) == Status.OK) {
			JOptionPane.showMessageDialog(this.panel, "Enhorabuena, eres premium! Disfruta el mes!", "LevelUp", JOptionPane.INFORMATION_MESSAGE);
			this.panel.getFullName().setText("");
			this.panel.getCreditCard().setText("");
			//MainFrame.getInstance().updateSideBar();
			MainFrame.getInstance().mostrarMainPanel();
		} else {
			JOptionPane.showMessageDialog(this.panel, "No hemos podido validar el pago. Vuelve a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
			this.panel.getFullName().setText("");
			this.panel.getCreditCard().setText("");
		}
	}

}
