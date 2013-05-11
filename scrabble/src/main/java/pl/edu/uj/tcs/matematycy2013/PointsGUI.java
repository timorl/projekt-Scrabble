package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;

import javax.swing.*;

public class PointsGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel text = new JLabel("Points:");
	JLabel points = new JLabel();
	
	public PointsGUI () {
		setLayout(new GridLayout(2,1));
		points.setText("0");
		add(text);
		add(points);
	}
	public void updateScore(int i) {
		points.setText(String.valueOf(i));
	}


}
