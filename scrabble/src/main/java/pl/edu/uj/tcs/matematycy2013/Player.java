package pl.edu.uj.tcs.matematycy2013;



public class Player {

	private final String nick;
	private int score;
	private int timeLeft;
	private final Letter[] letters;
	private int passCounter=0;


	public Player(String nick, int initialTime) {
		this.nick = nick;
		timeLeft=initialTime;
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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void updateScore(int update) {
		 score += update;
	}

    public void updateTime() {
        --timeLeft;
    }

    public void setLetters(Letter[] newLetters) {
        //temporary - we update all letters?
    	for (int i=0; i<7; i++) {
    		letters[i] = newLetters[i];
    	}
    }
    public void pass() {
    	passCounter++;
    }
    public int getPassCounter() {
    	return passCounter;
    }
    public void clearPassCounter() {
    	passCounter=0;
    }
    public boolean hasTime() {
    	return timeLeft>0;
    }
	public boolean hasLetter() {
		for(Letter l : letters)
			if(l!=null)
				return true;
		return false;
	}
}
