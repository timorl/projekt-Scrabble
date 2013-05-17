package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;
import java.util.Random;

import javax.swing.*;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel panel;
	BoardGUI board;
	JButton ok = new JButton("OK");
	JButton pass = new JButton("PASS");
	JButton exchange = new JButton("EXCHANGE");
	ClockGUI clock = new ClockGUI(100);
	PointsGUI points = new PointsGUI();
	PlayersNicks players;
	PlayerLetters playerLetters = new PlayerLetters(Cell.PLAYERLETTER);
	PlayerLetters toExchange = new PlayerLetters(Cell.TOEXCHANGE);
	
	Game game;
	
	GUI (String name, Game game) {
		super(name);
		this.game = game;
	}
	
	
	public void createGUI (int size, Cell[][] cells, String player1, String player2) {
		panel = new JPanel(new GridBagLayout());
        this.getContentPane().add(panel);
        
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NORTHEAST;
		gbc.anchor = GridBagConstraints.CENTER;
		
		board = new BoardGUI(size, cells);
		gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1.0;
		gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 10;
        gbc.gridwidth = 10;
	
		panel.add(board, gbc);
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 11;
		gbc.gridy = 0;
		players = new PlayersNicks(player1, player2);
		panel.add(players, gbc);
		
		gbc.gridx = 11;
		gbc.gridy = 1;
		panel.add(clock, gbc);
		
		gbc.gridx = 11;
		gbc.gridy = 2;
		panel.add(points, gbc);
		
		gbc.gridx = 11;
		gbc.gridy = 3;
		panel.add(ok,gbc);
		
		gbc.gridx = 11;
		gbc.gridy = 4;
		panel.add(pass, gbc);
		
		gbc.gridx = 7;
		gbc.gridy = 10;
		panel.add(exchange, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 3;
		panel.add(playerLetters, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 11;
		panel.add(toExchange, gbc);
		
		board.randomChange(); // temporary- to check changing letters
		board.randomChange(); // the same
		changeActivePlayer(new Player("")); // to check changeActivePlayer method
		setMinimumSize(new Dimension(700,600));
		pack();
		setVisible(true);
	}
	
	public void changeActivePlayer (Player player) {
		playerLetters.changePlayer(player.getLetters());
		toExchange.changePlayer(new Letter[7]);
		players.changeActivePlayer();
		points.updateScore(player.getScore());
		clock.updateClock(player.getTimeLeft());
	}
	
	
	/**
	 * temporary - main -to show GUI
	 */
	public static void main(String[] args) {
		final int n = 12;
		final GUI temp = new GUI("Scrabble", null);
		Random rg = new Random();
		Cell[][] cel = new Cell[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				int h = rg.nextInt(7);
				cel[i][j] = Cell.values()[h];
			}
		}
		final Cell[][] c = cel; 
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                temp.createGUI(n,c, "Dudu", "Tomek");
            }
        });

	}

}

