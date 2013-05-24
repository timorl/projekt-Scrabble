package pl.edu.uj.tcs.matematycy2013;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EndGUI extends JFrame{

	private final JLabel message = new JLabel("The winner is: ");
	private final JLabel winner = new JLabel();
	private final JLabel score = new JLabel();
	private final JPanel panel = new JPanel();
	private final JButton end = new JButton ("EXIT");
	private final JButton restart = new JButton ("RESTART");
	private final JButton newGame = new JButton ("NEW GAME");

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
	public static void main(String[] args) {

		final Game tmpG=new Game(new Config(20*60),"Dudu","Tomek");
		final GUI temp = new GUI("Scrabble", tmpG);
		tmpG.setGUI(temp);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new EndGUI(tmpG.getCurrentPlayer()).showEndGUI();
            }
        });

	}

}