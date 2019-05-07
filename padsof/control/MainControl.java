package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.playable.PlayableObject;
import padsof.swing.MainFrame;
import padsof.swing.MainPanel;

public class MainControl implements ActionListener {

	private MainPanel panel;

	public MainControl(MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlay())) {
			PlayableObject p = panel.getTablita().getSelected();
			p.play();
			System.out.println("Playing");
		} else {
			System.out.println("Qe carajo ases");
		}
	}

}
