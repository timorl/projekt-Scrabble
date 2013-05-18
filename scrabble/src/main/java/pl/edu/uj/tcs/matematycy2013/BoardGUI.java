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

	public BoardGUI (int size, Cell[][] cells, ButtonMouseListener btnMseLnr) {
		this.size = size;
		this.cells = cells;
		this.btnMseLnr=btnMseLnr;
		formatBoard();
	}

	private void formatBoard() {
		buttons = new LetterButton[size][size];
		GridLayout layout = new GridLayout(size, size);
		setLayout(layout);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				buttons[i][j] = new LetterButton(cells[i][j]);
				buttons[i][j].addMouseListener(btnMseLnr);
				add(buttons[i][j]);
			}
		}
		setVisible(true);

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

