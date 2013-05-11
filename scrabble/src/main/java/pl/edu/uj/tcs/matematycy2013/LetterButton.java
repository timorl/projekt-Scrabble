package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;

import javax.swing.*;

public class LetterButton extends JButton {

	private static final long serialVersionUID = 1L;
	boolean hasLetter = false;
	private JLabel field;
	private JLabel letter;
	private JLabel points;
	private Color fieldColor;
	
	public LetterButton(Cell c) {
		format(c);
		noLetter();
	}
	
	private void format(Cell cell) {
		setPreferredSize(new Dimension(60, 60));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		field = new JLabel();
		String lbl;
		switch (cell) {
		case EMPTY: 
			fieldColor = Color.YELLOW;
			break;
		case INVALID:
			fieldColor = Color.BLACK;
			setEnabled(false);
			break;
		case DOUBLEWORD:
			lbl = "<html>" + "Double" + "<br>" + "word" + "<br>" + "score"
					 + "</html>";
			field.setText(lbl);
			fieldColor = Color.ORANGE;
			break;
		case TRIPLEWORD:
			 lbl = "<html>" + "Triple" + "<br>" + "word" + "<br>" + "score"
					 + "</html>";
			field.setText(lbl);
			fieldColor = Color.RED;
			break;
		case DOUBLELETTER:
			lbl = "<html>" + "Double" + "<br>" + "letter" + "<br>" + "score"
					 + "</html>";
			field.setText(lbl);
			fieldColor = Color.CYAN;
			break;
		case TRIPLELETTER:
			lbl = "<html>" + "Triple" + "<br>" + "letter" + "<br>" + "score"
					 + "</html>";
			field.setText(lbl);
			fieldColor = Color.BLUE;
			break;
		case START:
			field.setText("Start");
			fieldColor = Color.GREEN;
			break;
		case PLAYERLETTER:
			fieldColor = Color.MAGENTA;
			break;
		case TOEXCHANGE:
			fieldColor = Color.PINK;
			break;
		default:
			break;
		}
		field.setFont(new Font("Serif", Font.PLAIN, 10));
		field.setHorizontalTextPosition(SwingConstants.CENTER);
		field.setPreferredSize(new Dimension(60, 60));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		add(field, gbc);
		
		gbc.fill = GridBagConstraints.NORTHEAST;
		gbc.anchor = GridBagConstraints.CENTER;
		
		letter = new JLabel();
		letter.setFont(new Font("Serif", Font.PLAIN, 40));
		letter.setPreferredSize(new Dimension(50,50));
		letter.setBackground(Color.LIGHT_GRAY);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(letter, gbc);
		
		points = new JLabel();
		//points.setText(String.valueOf(pts));
		points.setFont(new Font("Serif", Font.PLAIN, 10));
		points.setBackground(Color.LIGHT_GRAY);
		points.setPreferredSize(new Dimension(10,10));
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(points, gbc);
	}
	
	public void withLetter (Letter theChar) {
		char let = theChar.getChar();
		int pts = theChar.getValue();
		if (fieldColor == Color.BLACK) return;
		field.setVisible(false);
		letter.setVisible(true);
		letter.setText(String.valueOf(Character.toUpperCase(let)));
		points.setVisible(true);
		points.setText(String.valueOf(pts));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void noLetter() {
		field.setVisible(true);
		letter.setVisible(false);
		points.setVisible(false);
		setBackground(fieldColor);
	}
	

}
