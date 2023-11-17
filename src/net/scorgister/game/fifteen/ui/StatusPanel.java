package net.scorgister.game.fifteen.ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3633461711241260782L;
	
	private JLabel statusLab;
	
	public StatusPanel() {
		this.statusLab = new JLabel("Ready ?");
		statusLab.setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		add(statusLab);
	}
	
	public void changeStatus(String status) {
		statusLab.setText(status);
	}

}
