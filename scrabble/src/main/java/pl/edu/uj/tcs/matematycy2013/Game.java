package pl.edu.uj.tcs.matematycy2013;




public class Game {

	private final Board board;
	private Turn turn;
	private final Player player1;
	private final Player player2;
	private final Bag bag;
	private Player currentPlayer;
	private GUI gui;

	public Game (Config conf, String name1, String name2) {
		//temporary - we don't have Config yet
		//gui = new GUI("Scrabble", this);
		bag = new Bag();
		board = new Board();
		player1 = new Player(name1);
		player2 = new Player(name2);
		player1.setLetters(bag.getLetters(7));
		player2.setLetters(bag.getLetters(7));
		currentPlayer = player1;
		turn = new Turn (player1, board);
	}
	private void changeCurrentPlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else currentPlayer = player1;
	}
	private void setGUIState() {
		switch (turn.state) {
		 case WORD: gui.wordState(); break;
		 case EXCHANGE:	gui.exchangeState();break;
		 case INVALID: gui.invalidState();break;
		 case PASS: gui.passState();break;
		 }
	}

	public void putLetterOnTable (Letter toPut, int x, int y) {
		turn.putLetterOnTable(toPut, x, y);
		setGUIState();
	}
	public void addLetterToExchange (Letter toAdd) {
		turn.addLetterToExchange(toAdd);
		setGUIState();
	}
	public void removeLetterFromExchange(Letter toRemove) {
		turn.removeLetterFromExchange(toRemove);
		setGUIState();
	}
	 public void removeLetterFromBoard(Letter toRemove, int x, int y) {
		turn.removeLetterFromBoard(toRemove, x, y);
		setGUIState();
	 }


    public void beginTurn() {
    	changeCurrentPlayer();
        turn = new Turn (currentPlayer, board);
        gui.changeActivePlayer(currentPlayer);
    }

    public void finaliseTurn() {
        //TODO
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void processWord() {
        //TODO
    }

    public boolean isOver() {
        //TODO
        return false;
    }

    public Board getBoard() {
    	return board;
    }
    //temporary; we need to tide up gui and game. maybe moving main to game is better
    void setGUI(GUI gui){
    	this.gui=gui;
    }

}
