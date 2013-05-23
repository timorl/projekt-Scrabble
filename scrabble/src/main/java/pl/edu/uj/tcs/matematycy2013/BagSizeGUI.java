package pl.edu.uj.tcs.matematycy2013;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class BagSizeGUI extends JPanel {
	
	private JLabel text = new JLabel ("Letters left:");
	private JLabel number = new JLabel();
	
	public BagSizeGUI (int i) {
		setLayout(new GridLayout(2,1));
		number.setText(new Integer(i).toString());
		text.setFont(new Font("Serif", Font.BOLD, 15));
		number.setFont(new Font ("Serif", Font.BOLD, 15));
		add(text);
		add(number);
	}
	public void actualize(int i) {
		number.setText(new Integer(i).toString());
	}
	
	
}
