package padsof.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import padsof.playable.PlayableObject;
import padsof.playable.Song;
import padsof.swing.MainFrame;
import padsof.swing.MainPanel;

public class MainControl implements ActionListener {

	private MainPanel panel;

	public MainControl(MainPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(panel.getPlay()))
		{ // PLAY
			PlayableObject p = panel.getTablita().getSelected();
			p.play();
			System.out.println("Playing...");
		}
		else if (event.getSource().equals(panel.getReport()))
		{ // REPORT
			Song p = (Song) panel.getTablita().getSelected();
			p.report();
			System.out.println("Reported...");
		}
		else if (event.getSource().equals(panel.getComment()))
		{ // COMMENT
			Song p = (Song) panel.getTablita().getSelected();
			p.addComment("testing");
			System.out.println("Commented...");
		}
		else if (event.getSource().equals(panel.getFollow()))
		{ // FOLLOW
			PlayableObject p = panel.getTablita().getSelected();
			p.getAuthor().follow(padsof.system.System.getInstance().getLoggedUser());
			System.out.println("Following...");
		}
		else
		{
			System.out.println("Qe carajo ases");
		}
	}

}
