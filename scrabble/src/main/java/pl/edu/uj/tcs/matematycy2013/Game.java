package pl.edu.uj.tcs.matematycy2013;

public class Game {

	private final Board board;
	private Turn turn;
	private final Player player1;
	private final Player player2;
	//private Bag bag;
	private Player currentPlayer;
	private final GUI gui;

	public Game (Config conf, String name1, String name2) {
		//temporary - we don't have Config yet
		gui = new GUI("Scrabble", this);
		board = new Board();
		player1 = new Player(name1);
		player2 = new Player(name2);
		currentPlayer = player1;
		//bag = new Bag();
		turn = new Turn (player1, board);
	}
	private void changeCurrentPlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else currentPlayer = player1;
	}
	private void setGUIState() {
		// switch (turn.state) -GUI method to activate/deactivate proper button
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

}
