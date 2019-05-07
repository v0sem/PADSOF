package padsof.swing;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import padsof.swing.items.StandardButton;
import padsof.system.System;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel {
	
	private SideBarPanel sideBar;
	
	private StandardButton pendientes;
	
	private JLabel premiumEuros;
	private JLabel premiumRepro;
	private JLabel reproRegis;
	private JLabel reproAnon;
	
	private JTextField premiumEurosBox;
	private JTextField premiumReproBox;
	private JTextField reproRegisBox;
	private JTextField reproAnonBox;
	
	public AdminPanel() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		pendientes = new StandardButton("Canciones pendientes", 250, 70);
		this.add(pendientes);
		
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
		
		
		premiumEuros = new JLabel("Precio de premium: €");
		premiumRepro = new JLabel("Numero de reproducciones para premium: ");
		reproRegis = new JLabel("Reproducciones al mes por registrado: ");
		reproAnon = new JLabel("Reproducciones al mes para usuario anonimo: ");
		
		premiumEuros.setFont(new Font("Roboto", Font.BOLD, 14));
		premiumRepro.setFont(new Font("Roboto", Font.BOLD, 14));
		reproRegis.setFont(new Font("Roboto", Font.BOLD, 14));
		reproAnon.setFont(new Font("Roboto", Font.BOLD, 14));
		
		this.add(premiumEuros);
		this.add(premiumRepro);
		this.add(reproRegis);
		this.add(reproAnon);
		
		
		String premiumEurosBoxText = Double.toString(System.getInstance().getPremiumPrice());
		String premiumReproBoxText = Double.toString(System.getInstance().getPlaysToPremium());
		String reproRegisBoxText = Double.toString(System.getInstance().getMaxRegisteredSong());
		String reproAnonBoxText = Double.toString(System.getInstance().getMaxAnonSong());
		
		
		premiumEurosBox = new JTextField(premiumEurosBoxText, 7);
		premiumReproBox = new JTextField(premiumReproBoxText, 7);
		reproRegisBox = new JTextField(reproRegisBoxText, 7);
		reproAnonBox = new JTextField(reproAnonBoxText, 7);
		
		this.add(premiumEurosBox);
		this.add(premiumReproBox);
		this.add(reproRegisBox);
		this.add(reproAnonBox);
		
		layout.putConstraint(SpringLayout.WEST, pendientes, 350, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, pendientes, 50, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, premiumEuros, 210, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, premiumEuros, -150, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.WEST, premiumRepro, 210, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, premiumRepro, -120, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.WEST, reproRegis, 210, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, reproRegis, -90, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.WEST, reproAnon, 210, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, reproAnon, -60, SpringLayout.SOUTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, premiumEurosBox, 0, SpringLayout.NORTH, premiumEuros);
		layout.putConstraint(SpringLayout.WEST, premiumEurosBox, 6, SpringLayout.EAST, premiumEuros);
		
		layout.putConstraint(SpringLayout.NORTH, premiumReproBox, 0, SpringLayout.NORTH, premiumRepro);
		layout.putConstraint(SpringLayout.WEST, premiumReproBox, 6, SpringLayout.EAST, premiumRepro);
		
		layout.putConstraint(SpringLayout.NORTH, reproRegisBox, 0, SpringLayout.NORTH, reproRegis);
		layout.putConstraint(SpringLayout.WEST, reproRegisBox, 6, SpringLayout.EAST, reproRegis);
		
		layout.putConstraint(SpringLayout.NORTH, reproAnonBox, 0, SpringLayout.NORTH, reproAnon);
		layout.putConstraint(SpringLayout.WEST, reproAnonBox, 6, SpringLayout.EAST, reproAnon);
		
		this.setPreferredSize(new Dimension(800, 450));
	}
	
	public void updateSideBar() {
		this.remove(sideBar);
		this.sideBar = new SideBarPanel();
		this.add(sideBar);
	}
}
