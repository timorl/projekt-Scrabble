package pl.edu.uj.tcs.matematycy2013;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ButtonMouseListener extends MouseAdapter {
	private LetterButton tmp; 	// last time clicked button
	private Position tmpPos,XPos;
	private final Game game;


	public ButtonMouseListener(Game game) {
		tmp=null;
		this.game=game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//button which was just clicked
		LetterButton X=((LetterButton)e.getComponent());

		//the same button was clicked
		if(tmp==X) {
			X.setContentAreaFilled(true);
			tmp=null;
			return;
		}

		//nth is marked and button X has letter
		if(tmp==null && X.hasLetter() && !X.isBlack()) {
			tmp=X;
			X.setContentAreaFilled(false);
			return;
		}

		//some button with letter is marked (tmp!=null) and
		//button without letter was clicked
		if(tmp!=null && !X.hasLetter() && !X.isBlack()){
			tmpPos=tmp.getPosition();
			XPos=X.getPosition();
			moveLetter(X);
			//moving letter from player letters
			if(tmpPos==Position.LETTERS) {
				if(XPos==Position.BOARD) {
					LetterCoordinates co=X.getLetterCoordinates();
					game.putLetterOnTable(X.getLetter(), co.x, co.y);
				}
				if(XPos==Position.EXCHANGE) {
					game.addLetterToExchange(X.getLetter());
				}
			}
			//moving letter from board
			if(tmpPos==Position.BOARD) {
				if (XPos == Position.LETTERS) {
					LetterCoordinates co=tmp.getLetterCoordinates();
					game.removeLetterFromBoard(tmp.getLetter(), co.x, co.y);
				}
				if (XPos == Position.BOARD) {
					LetterCoordinates from=tmp.getLetterCoordinates();
					LetterCoordinates to = X.getLetterCoordinates();
					game.changeLettersOnBoard(X.getLetter(), from.x, from.y, to.x, to.y);
				}
				if (XPos == Position.EXCHANGE) {
					LetterCoordinates from = tmp.getLetterCoordinates();
					game.fromBoardToExchange(X.getLetter(), from.x, from.y);
				}
			}

			//moving letter from exchange
			if(tmpPos==Position.EXCHANGE) {
				if (XPos == Position.LETTERS) {
					game.removeLetterFromExchange(X.getLetter());
				}
				if (XPos == Position.BOARD) {
					LetterCoordinates to = X.getLetterCoordinates();
					game.fromExchangeToBoard(X.getLetter(), to.x, to.y);
				}
			}
			tmp = null;

		}
	}

	private void moveLetter(LetterButton X) {
		Letter tmpLetter=tmp.getLetter();
		X.withLetter(tmpLetter);
		tmp.setContentAreaFilled(true);
		tmp.noLetter();

	}

	public void unclickButtons() {
		tmp.setContentAreaFilled(true);
		tmp=null;
	}
}
