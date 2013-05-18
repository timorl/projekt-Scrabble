package pl.edu.uj.tcs.matematycy2013;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ButtonMouseListener extends MouseAdapter {
	private LetterButton tmp; 	// last time clicked button

	public ButtonMouseListener() {
		tmp=null;
	}
	public ButtonMouseListener(LetterButton tmp ) {
		this.tmp=tmp;
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

		//some button with letter is marked and button without letter was clicked
		if(tmp!=null && !X.hasLetter() && !X.isBlack()){
			Letter tmpLetter=tmp.getLetter();
			X.withLetter(tmpLetter);
			tmp.setContentAreaFilled(true);
			tmp.noLetter();
			tmp=null;

		}

	}
}

