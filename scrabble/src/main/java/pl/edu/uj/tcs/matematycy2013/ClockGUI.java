package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;
import java.util.Date;

import javax.swing.*;


public class ClockGUI extends JPanel {
	 
	/**
	 temporary - time format? 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel text = new JLabel("Time Left:");
	private JLabel time = new JLabel();
	
	public ClockGUI (Integer tm) {
		setLayout(new GridLayout(2,1));
		this.time.setText(tm.toString());
		add(text);
		add(time);
	}
	public void updateClock(Date time) {
		//TODO
	}

}
