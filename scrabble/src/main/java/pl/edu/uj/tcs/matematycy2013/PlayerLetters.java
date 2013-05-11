package pl.edu.uj.tcs.matematycy2013;


import javax.swing.*;
import java.awt.*;


public class PlayerLetters extends JPanel {

	private static final long serialVersionUID = 1L;
	LetterButton[] letters = new LetterButton[7];
	
	public PlayerLetters(Cell cell) {
		setLayout(new FlowLayout());
		for (int i=0; i<7; i++) {
			letters[i] = new LetterButton(cell);
			add(letters[i]);
		}
	}
	
	public void changePlayer(Letter[] newLetters) {
		for (int i=0; i<7; i++) {
			if (newLetters[i] != null) {
				letters[i].withLetter(newLetters[i]);
			} else {
				letters[i].noLetter();
			}
		}
	}

}
