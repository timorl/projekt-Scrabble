package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class EndGUI extends JFrame{
	
	private JLabel message = new JLabel("The winner is: ");
	private JLabel winner = new JLabel();
	private JLabel score = new JLabel();
	private JPanel panel = new JPanel();
	private JButton end = new JButton ("EXIT");
	private JButton restart = new JButton ("RESTART");
	private JButton newGame = new JButton ("NEW GAME");
	
	public EndGUI (Player player) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension (500, 500));
		message.setFont(new Font ("Serif", Font.BOLD, 30));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		
		winner.setText(player.getNick());
		winner.setFont(new Font ("Serif", Font.BOLD, 40));
		winner.setHorizontalAlignment(SwingConstants.CENTER);
		
		String text = "Scored: " + player.getScore() + " points!";
		score.setText(text);
		score.setFont(new Font ("Serif", Font.BOLD, 30));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		
		restart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//TODO;
			}
		});
		
		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//TODO;
			}
		});
		
		end.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(restart);
		panel.add(newGame);
		panel.add(end);
		
		setLayout(new GridLayout (4,1));
		add(message);
		add(winner);
		add(score);
		add(panel);
	}
	public void showEndGUI() {
		pack();
		setVisible(true);
	}
	
	// main to tests only
	/*public static void main(String[] args) {
		
		final Game tmpG=new Game(new Config(),"Dudu","Tomek");
		final GUI temp = new GUI("Scrabble", tmpG);
		tmpG.setGUI(temp);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new EndGUI(tmpG.getCurrentPlayer()).showEndGUI();
            }
        });

	}*/

}
