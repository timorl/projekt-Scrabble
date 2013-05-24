package pl.edu.uj.tcs.matematycy2013;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ClockGUI extends JPanel {

	/**
	 temporary - time format?
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel text = new JLabel("Time Left:");
	private final JLabel time = new JLabel();
	private int tme;

	public ClockGUI (int tm) {
		tme=tm;
		setLayout(new GridLayout(2,1));
		this.time.setText(formatTime(tm));
		add(text);
		add(time);
	}

	public void updateClock() {
		this.time.setText(formatTime(--tme));
	}

	public void updateClock(int tm) {
		tme=tm;
		this.time.setText(formatTime(tm));
	}

	private String formatTime(int tm) {
		String result="";
		if(tm/3600<10)
			result="0";
		result+=""+tm/3600+" : ";
		if((tm%3600)/60<10)
			result+="0";
		result+=""+(tm%3600)/60+" : ";
		if(tm%60<10)
			result+="0";
		result+=""+tm%60;

		return result;
	}

}
