package pl.edu.uj.tcs.matematycy2013;

public class Letter {

	private char letter;
	private final int points;

    public Letter(char theChar, int points) {
        letter = Character.toUpperCase(theChar);
        this.points = points;
    }

    public void setChar(char newChar) {
        letter = Character.toUpperCase(newChar);
    }

    public char getChar() {
        return letter;
    }

    public int getValue() {
        return points;
    }

    @Override
	public boolean equals(Object o) {
    	if(o.getClass()==Letter.class && ((Letter)o).letter==letter)
    		return true;

    	return false;
    }

    @Override
    public String toString() {
        return String.valueOf(letter) + String.valueOf(points);
    }

}
