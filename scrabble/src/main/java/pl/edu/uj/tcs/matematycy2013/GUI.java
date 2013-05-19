package pl.edu.uj.tcs.matematycy2013;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel panel;
	BoardGUI board;
	JButton ok = new JButton("OK");
	JButton pass = new JButton("PASS");
	JButton exchange = new JButton("EXCHANGE");
	ClockGUI clock = new ClockGUI(100);
	PointsGUI points = new PointsGUI();
	PlayersNicksGUI players;
	PlayerLettersGUI playerLetters;
	PlayerLettersGUI toExchange;

	Game game;

	GUI (String name, Game game) {
		super(name);
		this.game = game;
	}


	public void createGUI (int size, Cell[][] cells, String player1, String player2) {
		panel = new JPanel(new GridBagLayout());
        this.getContentPane().add(panel);
        ButtonMouseListener btnMseLnr=new ButtonMouseListener(game);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NORTHEAST;
		gbc.anchor = GridBagConstraints.CENTER;

		board = new BoardGUI(size, cells,btnMseLnr);
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
		players = new PlayersNicksGUI(player1, player2);
		panel.add(players, gbc);

		gbc.gridx = 11;
		gbc.gridy = 1;
		panel.add(clock, gbc);

		gbc.gridx = 11;
		gbc.gridy = 2;
		panel.add(points, gbc);

		gbc.gridx = 11;
		gbc.gridy = 3;
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				game.finaliseTurn();
				game.beginTurn();
			}
		});
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
		playerLetters=new PlayerLettersGUI(game.getCurrentPlayer().getLetters(), btnMseLnr, Position.LETTERS);
		panel.add(playerLetters, gbc);

		gbc.gridx = 5;
		gbc.gridy = 11;
		toExchange=new PlayerLettersGUI(Cell.TOEXCHANGE, btnMseLnr, Position.EXCHANGE);
		panel.add(toExchange, gbc);

		//board.randomChange(); // temporary- to check changing letters
		//board.randomChange(); // the same
		//changeActivePlayer(new Player("")); // to check changeActivePlayer method
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

	void wordState() {
		ok.setEnabled(true);
		pass.setEnabled(false);
		exchange.setEnabled(false);
	}
	void exchangeState() {
		ok.setEnabled(false);
		pass.setEnabled(false);
		exchange.setEnabled(true);
	}
	void invalidState() {
		ok.setEnabled(false);
		pass.setEnabled(false);
		exchange.setEnabled(false);
	}
	void passState() {
		ok.setEnabled(false);
		pass.setEnabled(true);
		exchange.setEnabled(false);
	}
	
	public void prepareBoard(Board trueBoard) {
		board.prepare(trueBoard);
	}


	/**
	 * temporary - main -to show GUI
	 */
	public static void main(String[] args) {
		final int n = 12;
		Game tmpG=new Game(new Config(),"Dudu","Tomek");
		final GUI temp = new GUI("Scrabble", tmpG);
		tmpG.setGUI(temp);

		final Cell[][] c = temp.game.getBoard().getBoard();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                temp.createGUI(n,c, "Dudu", "Tomek");
            }
        });

	}

}

