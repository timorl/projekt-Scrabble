package pl.edu.uj.tcs.matematycy2013;

public class LetterCoordinates  {

	public int x;
	public int y;
	
	public LetterCoordinates (int x,int y) {
		this.x = x;
		this.y = y;
	}
	@Override 
	public boolean equals (Object o) {
		if (o.getClass() != LetterCoordinates.class) return false;
		else return (((LetterCoordinates)o).x == x && ((LetterCoordinates)o).y == y);
	}

}
