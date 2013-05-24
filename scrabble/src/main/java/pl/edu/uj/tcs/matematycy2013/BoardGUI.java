package pl.edu.uj.tcs.matematycy2013;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;



public class BoardGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	Cell[][] cells;
	LetterButton[][] buttons;
	int size;
	ButtonMouseListener btnMseLnr;
	boolean isTorus;

	public BoardGUI (Board board, ButtonMouseListener btnMseLnr) {
		this.size = board.getSize();
		this.cells = board.getBoard();
		this.btnMseLnr=btnMseLnr;
		isTorus = board.isTorus();
		formatBoard();
	}

	private void formatBoard() {
		buttons = new LetterButton[size][size];
		GridLayout layout = new GridLayout(size, size);
		setLayout(layout);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				buttons[i][j] = new LetterButton(cells[i][j], Position.BOARD, new LetterCoordinates(i, j));
				buttons[i][j].addMouseListener(btnMseLnr);
				add(buttons[i][j]);
			}
		}
		if (!isTorus) {
			for (int i=0; i<size; i+=size-1) {
				for (int j=0; j<size; j++) {
					buttons[i][j].setVisible(false);
				}
			}
			for (int j=0; j<size; j+=size-1) {
				for (int i=0; i<size; i++) {
					buttons[i][j].setVisible(false);
				}
			}
		}
		setVisible(true);

	}

	public void prepare (Board board) {
		Letter[][] lets = board.getLetters();
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (lets[i][j] != null) {
					buttons[i][j].setEnabled(false);
					buttons[i][j].setBlack(true);
				}
			}
		}
	}

	public void setLook(Board board) {
		Letter[][] lets = board.getLetters();
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (lets[i][j] != null) {
					buttons[i][j].setEnabled(false);
					buttons[i][j].setBlack(true);
				}
				else  {
					buttons[i][j].setEnabled(true);
					buttons[i][j].setBlack(false);
					buttons[i][j].noLetter();
				}
			}
		}
	}

	public void randomChange() {
		Random rg = new Random();
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if (rg.nextBoolean()) {
					buttons[i][j].withLetter(new Letter('A', 2));
				}
				else {
					buttons[i][j].noLetter();
				}
			}
		}
	}

}

