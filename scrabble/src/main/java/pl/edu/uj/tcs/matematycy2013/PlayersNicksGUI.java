package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;

import javax.swing.*;


public class PlayersNicksGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel[] players = new JLabel[2];
	JLabel[] points = new JLabel[2];
	int active = 0;

	 public PlayersNicksGUI(String nick1, String nick2) {
		 //setPreferredSize(new Dimension(150,100));
		players[0] = new JLabel(nick1);
		players[1] = new JLabel(nick2);
		for (int i=0; i<2; i++) {
			//players[i].setPreferredSize(new Dimension(100, 50));
			points[i] = new JLabel();
			points[i].setFont(new Font("Serif", Font.PLAIN, 20));
			players[i].setFont(new Font("Serif", Font.PLAIN, 25));
			players[i].setHorizontalAlignment(JTextField.CENTER);
			//players[i].setEditable(false);
		}
		format();
	 }

	 private void format() {
		 setLayout(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.gridx = 0; gbc.gridy = 0;
		 add(players[0], gbc);
		 gbc.gridy = 1;
		 add(players[1], gbc);
		 gbc.gridx = 1; gbc.gridy = 0;
		 add(points[0], gbc);
		 gbc.gridy = 1;
		 add(points[1], gbc);
		 updateActive(0,0);
	}

	 private void updateActive(int p1, int p2) {

		players[active].setFont(new Font("Serif", Font.BOLD, 25 ));
		players[active].setOpaque(true);
		players[active].setBackground(Color.YELLOW);
		players[(active+1)%2].setFont(new Font("Serif", Font.PLAIN, 20));
		players[(active+1)%2].setOpaque(false);
		
		points[active].setText("   " + new Integer(p2).toString() + "   ");
		points[(active+1)%2].setText("   " + new Integer(p1).toString() + "   ");
		
	 }

	 public void changeActivePlayer(int p1, int p2) {
		 active = (active +1) % 2;
		 updateActive(p1, p2);
	 }


}
