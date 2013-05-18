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
			//move letter if move is valid
			if(moveLetter(X)) {
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
				if(tmpPos==Position.BOARD && XPos==Position.LETTERS) {
					LetterCoordinates co=tmp.getLetterCoordinates();
					game.removeLetterFromBoard(tmp.getLetter(), co.x, co.y);
				}

				//moving letter from exchange
				if(tmpPos==Position.EXCHANGE && XPos==Position.LETTERS) {
					game.removeLetterFromExchange(X.getLetter());
				}
				tmp=null;
			}
		}
	}

	private boolean moveLetter(LetterButton X) {

		if( tmpPos==Position.LETTERS || (tmpPos==Position.EXCHANGE && XPos!=Position.BOARD ) ||	(tmpPos==Position.BOARD  && XPos==Position.LETTERS	) ) {
				Letter tmpLetter=tmp.getLetter();
				X.withLetter(tmpLetter);
				tmp.setContentAreaFilled(true);
				tmp.noLetter();
				return true;
		}
		return false;
	}
}
