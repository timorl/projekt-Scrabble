package pl.edu.uj.tcs.matematycy2013;


import java.util.Date;

public class Player {

	private String nick;
	private int score;
	private Date timeLeft;
	private Letter[] letters;

	
	public Player(String nick) {
		this.nick = nick;
		//temporary - we have to get random letters from bag, which doesn't exist yet
		letters = new Letter[7];
		for (int i=0; i<7; i++) {
			letters[i] = new Letter('B', 3);
		}
	}
	public Letter[] getLetters() {
		return letters;
	}
		
    public int getScore() {
        return score;
    }
		
    public String getNick() {
        return nick;
    }
		
    public Date getTimeLeft() {
        return timeLeft;
    }
		
    public void updateScore(int update) {
		 score += update;
	}
		
    public void updateTime(int update) {
        //TODO
    }
		
    public void setLetters(Letter[] newLetters) {
        //temporary - we update all letters?
    	for (int i=0; i<7; i++) {
    		letters[i] = newLetters[i];
    	}
    } 
}
