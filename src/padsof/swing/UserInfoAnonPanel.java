package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


@SuppressWarnings("serial")
public class UserInfoAnonPanel extends JPanel {
	
	private JLabel anon;
	private JLabel info;
	
	public UserInfoAnonPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		anon  = new JLabel();
		anon.setText("Usuario anónimo");
		Font f = anon.getFont();
		// bold
		anon.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		
		info = new JLabel("Sesion no iniciada");
		info.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
		
		this.add(anon);
		this.add(info);
		
		layout.putConstraint(SpringLayout.WEST, anon, -90, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, anon, 10, SpringLayout.NORTH, this);

		
		layout.putConstraint(SpringLayout.WEST, info, -90, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, info, -15, SpringLayout.SOUTH, this);
		
		this.setPreferredSize(new Dimension(200, 55));
	}
}
