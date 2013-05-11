package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;
import java.util.Random;

import javax.swing.*;



public class BoardGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	Cell[][] cells;
	LetterButton[][] buttons;
	int size;

	public BoardGUI (int size, Cell[][] cells) {
		this.size = size;
		this.cells = cells;
		formatBoard();
	}
	
	private void formatBoard() {
		buttons = new LetterButton[size][size];
		GridLayout layout = new GridLayout(size, size);
		setLayout(layout);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				buttons[i][j] = new LetterButton(cells[i][j]);
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
