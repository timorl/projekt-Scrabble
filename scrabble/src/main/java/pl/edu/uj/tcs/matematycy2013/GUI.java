package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel rightPanel;
	JPanel downPanel;
	BoardGUI board;
	JButton ok = new JButton("OK");
	JButton pass = new JButton("PASS");
	JButton exchange = new JButton("EXCHANGE");
	ClockGUI clock ;
	BagSizeGUI bag;
	PlayersNicksGUI players;
	PlayerLettersGUI playerLetters;
	PlayerLettersGUI toExchange;

	Game game;

	GUI ( Game game) {
		super("Scrabble");
		this.game = game;
		clock=new ClockGUI(game.getCurrentPlayer().getTimeLeft());
	}


	public void createGUI (int size, Cell[][] cells, String player1, String player2) {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("icon.jpe");
		try {
			Image im = ImageIO.read(input);
			setIconImage(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        ButtonMouseListener btnMseLnr=new ButtonMouseListener(game);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;

		board = new BoardGUI(game.getBoard(),btnMseLnr);
		gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 0.5;
		gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;

		add(board, gbc);
		
		downPanel = new JPanel (new GridBagLayout());
		//downPanel.setBackground(Color.BLUE);
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		playerLetters=new PlayerLettersGUI(game.getCurrentPlayer().getLetters(), btnMseLnr, Position.LETTERS);
		downPanel.add(playerLetters, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		toExchange=new PlayerLettersGUI(Cell.TOEXCHANGE, btnMseLnr, Position.EXCHANGE);
		downPanel.add(toExchange, gbc);
		
		//gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 1;
		gbc.gridy = 0;
		exchange.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				game.finaliseTurn();
			}
		});
		// ?
		downPanel.setPreferredSize(new Dimension(500, 500));
		downPanel.add(exchange, gbc);
		exchange.setEnabled(false);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0; //0.7;
		gbc.weighty = 0; //0.3;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		
		add(downPanel, gbc);
		
		rightPanel = new JPanel (new GridBagLayout());
		//rightPanel.setBackground(Color.GREEN);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		players = new PlayersNicksGUI(player1, player2);
		rightPanel.add(players, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		rightPanel.add(clock, gbc);

		bag = new BagSizeGUI(game.getBagSize());
		gbc.gridx = 0;
		gbc.gridy = 2;
		rightPanel.add(bag, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				game.finaliseTurn();
			}
		});
		rightPanel.add(ok,gbc);
		ok.setEnabled(false);


		gbc.gridx = 0;
		gbc.gridy = 4;
		pass.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				game.finaliseTurn();
			}
		});
		rightPanel.add(pass, gbc);

		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = 0; //0.3;
		gbc.weighty = 0; //1;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		gbc.gridx = 2;
		gbc.gridy = 0;
		
		add(rightPanel, gbc);

		//setMinimumSize(new Dimension(700,600));
		pack();
		setVisible(true);
	}


	public void changeActivePlayer (Player last, Player player) {
		playerLetters.changePlayer(player.getLetters());
		toExchange.changePlayer(new Letter[7]);
		players.changeActivePlayer(last.getScore(), player.getScore());
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
	public void setLook(Board trueBoard) {
		board.setLook(trueBoard);
	}
	public void showGamePanel(boolean flag) {
		board.setVisible(flag);
		rightPanel.setVisible(flag);
		downPanel.setVisible(flag);
//		setVisible(flag);
	}
	public void showGameWindow(boolean flag) {
		setVisible(flag);
	}

	public void updateClock() {
		clock.updateClock();
	}

	public void updateClock(int time) {
		clock.updateClock(time);
	}
	public void updateBagSize(int i) {
		bag.actualize(i);
	}

    public char askForLetter(Character[] alph, LetterCoordinates coords) {
        String message = "What letter should the blank in the column " + coords.x + " and row " + coords.y + "represent?";
        String name = "Fill the blank";
        Character res = null;
        do {
            res = (Character)JOptionPane.showInputDialog(
                this,
                message,
                name,
                JOptionPane.PLAIN_MESSAGE,
                null, //No icon.
                alph,
                alph[0]
            );
        } while (res == null);
        return res.charValue();
    }
}

