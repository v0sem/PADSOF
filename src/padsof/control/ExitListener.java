package padsof.control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ExitListener extends WindowAdapter {


	@Override
	public void windowClosed(WindowEvent e) {
			try {
				padsof.system.System.getInstance().saveData();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		System.exit(0);
	}

}
