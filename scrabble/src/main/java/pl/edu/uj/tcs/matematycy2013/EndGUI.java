package pl.edu.uj.tcs.matematycy2013;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EndGUI extends JFrame{

	private final JLabel message;
	private final JLabel winner = new JLabel();
	private final JLabel score = new JLabel();
	private final JPanel panel = new JPanel();
	private final JButton end = new JButton ("EXIT");
	private final JButton restart = new JButton ("RESTART");
	private final JButton newGame = new JButton ("NEW GAME");
	private Config config;


	public EndGUI (Player player, String gameResult, Config conf) {
		super("Scrabble - the end");
		config=conf;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("icon.jpe");
		try {
			Image im = ImageIO.read(input);
			setIconImage(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setPreferredSize(new Dimension (500, 500));
		message = new JLabel(gameResult);
		message.setFont(new Font ("Serif", Font.BOLD, 30));
		message.setHorizontalAlignment(SwingConstants.CENTER);

		if(gameResult.equals("The winner is: ")) {
			winner.setText(player.getNick());
			winner.setFont(new Font ("Serif", Font.BOLD, 40));
			winner.setHorizontalAlignment(SwingConstants.CENTER);

			String text = "Scored: " + player.getScore() + " points!";
			score.setText(text);
			score.setFont(new Font ("Serif", Font.BOLD, 30));
			score.setHorizontalAlignment(SwingConstants.CENTER);
		}

		restart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Scrabble.startGame(config);
				} catch (FileNotFoundException e1) {
					System.out.println("file not found when restarting game");
					e1.printStackTrace();
				} catch (IOException e1) {
					System.out.println("IOexception when restarting game");
					e1.printStackTrace();
				}
			}
		});

		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					Scrabble.main(null);
				} catch (IOException e1) {
					System.out.println("IOexception when firing new game");
					e1.printStackTrace();
				}
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

/*	// main to tests only
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
*/
}
