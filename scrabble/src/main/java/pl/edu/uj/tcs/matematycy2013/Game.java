package pl.edu.uj.tcs.matematycy2013;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;
import javax.swing.Timer;

public class Game {

    private Board board;
    private Dictionary dictionary;
    private SwingWorker<Dictionary,Void> dictionaryLoader;
    private Alphabet alphabet;
    private Turn turn;
    private final Player player1;
    private final Player player2;
    private final Bag bag;
    private Player currentPlayer;
    private GUI gui;
    private final ChangePlayerGUI changeGui;
    private final Timer timer;
    private EndGUI endGUI;
    private Config config;
    

    public Game(Config conf) throws IOException {
    	
    	config=conf;
    	dictionaryLoader=getDictionaryLoader();
    	dictionaryLoader.execute();
        alphabet = new Alphabet(conf.getBagStream());
        bag = new Bag(conf.getBagStream());
        board = new Board(conf.getBoardStream());
        player1 = new Player(conf.getPlayer1(),conf.getMaxTime());
        player2 = new Player(conf.getPlayer2(),conf.getMaxTime());
        player1.setLetters(bag.getLetters(7));
        player2.setLetters(bag.getLetters(7));
        
        currentPlayer = player1;
        turn = new Turn(player1, board, bag.getSize());
        changeGui = new ChangePlayerGUI(this);
        timer = new Timer(1000,new ActionListener() {

        	public void actionPerformed(ActionEvent e) {
				currentPlayer.updateTime();
				gui.updateClock();
				if(currentPlayer.getTimeLeft()<=0) {
			        turn.timeLeft();
			        gui.setLook(board);
			        finaliseTurn();
				}
			}
		});
    }
    private SwingWorker<Dictionary, Void> getDictionaryLoader(){
    	return new SwingWorker<Dictionary, Void>() {

			@Override
			protected Dictionary doInBackground() throws Exception {
				return new Dictionary(config.getDictionaryStream(), alphabet);
			}
    		
			@Override
			public void done(){
				try {
					dictionary=get();
				} catch (InterruptedException e) {
					System.out.println("InterruptedException when loading dictionary");
					e.printStackTrace();
				} catch (ExecutionException e) {
					System.out.println("ExecutionException when loading dictionary");
					e.printStackTrace();
				}
			}
    		
		};
    }

    private void changeCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
    }

    private void setGUIState() {
        switch (turn.state) {
            case WORD:
                gui.wordState();
                break;
            case EXCHANGE:
                gui.exchangeState();
                break;
            case INVALID:
                gui.invalidState();
                break;
            case PASS:
                gui.passState();
                break;
        }
    }

    public void putLetterOnTable(Letter toPut, int x, int y) {
        turn.putLetterOnTable(toPut, x, y);
        setGUIState();
    }

    public void addLetterToExchange(Letter toAdd) {
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

    public void changeLettersOnBoard(Letter toChange, int fromx, int fromy, int tox, int toy) {
        turn.removeLetterFromBoard(toChange, fromx, fromy);
        turn.putLetterOnTable(toChange, tox, toy);
        setGUIState();
    }

    public void fromBoardToExchange(Letter toChange, int x, int y) {
        turn.removeLetterFromBoard(toChange, x, y);
        turn.addLetterToExchange(toChange);
        setGUIState();
    }

    public void fromExchangeToBoard(Letter toChange, int x, int y) {
        turn.removeLetterFromExchange(toChange);
        turn.putLetterOnTable(toChange, x, y);
        setGUIState();
    }

    private Letter[] addLettersToBlanks(LetterCoordinates now, int orientation, Letter[][] putLetters) {
        LetterCoordinates start, end;
        ArrayList<Letter> foundBlanks = new ArrayList<Letter>();
        start = findStart(now, putLetters, orientation);
        end = findEnd(now, putLetters, orientation);
        while ( !start.equals(end) ) {
            if ( putLetters[start.x][start.y] != null && putLetters[start.x][start.y].getChar() == '*' ) {
                char letterToSet = gui.askForLetter(alphabet.getLetters(), start);
                foundBlanks.add(putLetters[start.x][start.y]);
                putLetters[start.x][start.y].setChar(letterToSet);
            }
            start = nextField(start, orientation);
        }
        if ( putLetters[start.x][start.y] != null && putLetters[start.x][start.y].getChar() == '*' ) {
            char letterToSet = gui.askForLetter(alphabet.getLetters(), start);
            foundBlanks.add(putLetters[start.x][start.y]);
            putLetters[start.x][start.y].setChar(letterToSet);
        }
        Letter[] result = new Letter[foundBlanks.size()];
        int i = 0;
        for ( Letter l: foundBlanks ) {
            result[i++] = l;
        }
        return result;
    }

    private void clearBlanks(Letter[] toClear) {
        for (Letter l: toClear) {
            l.setChar('*');
        }
    }

    // return -1 when at least one word is incorrect
    public int countScore(Turn toCount) {
        ArrayList<LetterCoordinates> onBoard = toCount.getOnBoard();
        if (onBoard.isEmpty()) {
            return 0;
        }

        int bonus = 0;
        if (onBoard.size() == 7) {
            bonus = 50;
        }

        Letter[][] putLetters = toCount.getPutLetters();
        int orientation = getOrientation(onBoard);
        Letter[] oldBlanks = addLettersToBlanks(new LetterCoordinates(onBoard.get(0).x, onBoard.get(0).y), orientation, putLetters);

        int score = processMainWord(new LetterCoordinates(onBoard.get(0).x, onBoard.get(0).y), orientation, putLetters);
        if (score == -1) {
            clearBlanks(oldBlanks);
            return -1;
        }

        int additionalScore = processAdditionalWords(onBoard, orientation ^ 1, putLetters);
        if (additionalScore == -1) {
            clearBlanks(oldBlanks);
            return -1;
        }

        return score + additionalScore + bonus;
    }

    private int processMainWord(LetterCoordinates now, int orientation, Letter[][] putLetters) {
        LetterCoordinates start, end;
        start = findStart(now, putLetters, orientation);
        end = findEnd(now, putLetters, orientation);
        return processSingleWord(start, end, putLetters, orientation);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private int processAdditionalWords(ArrayList<LetterCoordinates> onBoard, int orientation, Letter[][] putLetters) {
        int res = 0;
        LetterCoordinates start, end;

        for (int i = 0; i < onBoard.size(); ++i) {
            LetterCoordinates now = new LetterCoordinates(onBoard.get(i).x, onBoard.get(i).y);
            start = findStart(now, putLetters, orientation);
            end = findEnd(now, putLetters, orientation);
            if (start.equals(end)) {
                continue;
            }
            int wordScore = processSingleWord(start, end, putLetters, orientation);
            if (wordScore == -1) {
                return -1;
            }
            res += wordScore;
        }
        return res;
    }

    private int processSingleWord(LetterCoordinates start, LetterCoordinates end, Letter[][] putLetters, int orientation) {
        int multiplier = 1;
        int wordScore = 0;
        String word = new String();
        Letter[][] letters = board.getLetters();
        Cell[][] cells = board.getBoard();

        while (true) {
            int value;
            if (putLetters[start.x][start.y] != null) {
                value = putLetters[start.x][start.y].getValue();
                int type = processCell(cells[start.x][start.y]);
                if (type > 1) {
                    multiplier *= type;
                }
                else {
                    if (type != -1) {
                        value *= (type + 2);
                    }
                }
                word += putLetters[start.x][start.y].getChar();
            }
            else {
                value = letters[start.x][start.y].getValue();
                word += letters[start.x][start.y].getChar();
            }

            wordScore += value;

            if (start.equals(end)) {
                break;
            }
            start = nextField(start, orientation);
        }

        if (!dictionary.checkWord(word)) {
            return -1;
        }

        //needed Dictionary class, therefore moving all of those methods to Game class sounds reasonably
        return wordScore * multiplier;
    }

    private LetterCoordinates findStart(LetterCoordinates now, Letter[][] putLetters, int orientation) {
        Letter[][] letters = board.getLetters();
        LetterCoordinates start = now;
        LetterCoordinates cur = previousField(now, orientation);

        while (letters[cur.x][cur.y] != null || putLetters[cur.x][cur.y] != null) {
            start = cur;
            cur = previousField(cur, orientation);
        }

        return start;
    }

    private LetterCoordinates findEnd(LetterCoordinates now, Letter[][] putLetters, int orientation) {
        Letter[][] letters = board.getLetters();
        LetterCoordinates end = now;
        LetterCoordinates cur = nextField(now, orientation);

        while (letters[cur.x][cur.y] != null || putLetters[cur.x][cur.y] != null) {
            end = cur;
            cur = nextField(cur, orientation);
        }
        System.out.println("end: " + end.x + " " + end.y);
        return end;
    }

    private int processCell(Cell cell) {
        if (cell.equals(Cell.DOUBLEWORD) || cell.equals(Cell.START)){
            return 2;
        }
        if (cell.equals(Cell.TRIPLEWORD)) {
            return 3;
        }
        if (cell.equals(Cell.DOUBLELETTER)) {
            return 0;
        }
        if (cell.equals(Cell.TRIPLELETTER)) {
            return 1;
        }
        return -1;
    }

    private int getOrientation(ArrayList<LetterCoordinates> list) {
        if (list.size() == 1) {
            return 0;
        }
        else {
            LetterCoordinates a = list.get(0);
            LetterCoordinates b = list.get(1);
            if (a.x == b.x) {
                return 0;
            }
            return 1;
        }
    }

    private LetterCoordinates previousField(LetterCoordinates field, int orientation) {
        int size = board.getSize();

        if (orientation == 1) {
            return new LetterCoordinates((field.x - 1 + size) % size, field.y);
        }
        else {
            return new LetterCoordinates(field.x, (field.y - 1 + size) % size);
        }
    }

    private LetterCoordinates nextField(LetterCoordinates field, int orientation) {
        int size = board.getSize();

        if (orientation == 1) {
            return new LetterCoordinates((field.x + 1) % size, field.y);
        }
        else {
            return new LetterCoordinates(field.x, (field.y + 1) % size);
        }
    }

    public void exchangeLetters(boolean backToBag) {
        ArrayList<Letter> toExch = turn.getToExchange();
        ArrayList<Letter> playerLet = new ArrayList<Letter>( Arrays.asList( currentPlayer.getLetters() ) );
        for ( Letter let : toExch ) {
            playerLet.remove(let);
        }
        playerLet.addAll( Arrays.asList( bag.getLetters( toExch.size() ) ) );
        playerLet = new ArrayList<Letter>( playerLet );
        Letter[]  pL = new Letter[7];
        for ( int i = 0; i < 7; i++ ) {
            pL[i] = playerLet.get(i);
        }
        currentPlayer.setLetters( pL );
        if (backToBag) {
	        pL = new Letter[toExch.size()];
	        for ( int i = 0; i < pL.length; i++ ) {
	            pL[i] = toExch.get(i);
	        }
	        bag.addLetters( pL );
        }
    }

    public void beginTurn() {
        turn = new Turn (currentPlayer, board, bag.getSize());
        setGUIState();
        gui.updateClock(currentPlayer.getTimeLeft());
        gui.showGamePanel(true);
        timer.start();
    }

    public void finaliseTurn() {
    	timer.stop(); 
    	//waits 0,5 sec if dictionary hasn't been loaded yet
    	while(!dictionaryLoader.isDone())
			try {
				wait(500);
			} catch (InterruptedException e) {
				System.out.println("Interrupted while sleeping when waiting for dictinary (game.finalizeTurn)");
				e.printStackTrace();
			}
        switch ( turn.state ) {
            case EXCHANGE:
                exchangeLetters(true);
                player1.clearPassCounter();
                player2.clearPassCounter();
                break;
            case WORD:
            	int score = countScore(turn);
            	if (score < 0) {
            		turn.timeLeft(); // timeLeft clears Turn?
                        gui.setLook(board);
            		currentPlayer.pass(); 
            	} else {
	                board.commit(turn);
	                currentPlayer.updateScore(score);
	                turn.usedLettersToExchange();
	                exchangeLetters(false);
	                player1.clearPassCounter();
	                player2.clearPassCounter();
            	}
                break;
            case PASS:
            	currentPlayer.pass();
            	break;
            default : break;
        }
        gui.updateBagSize(bag.getSize());

        //two passes from each player in a row or bag is empty and some player has used last letter
        if( isEnded() ){
        	finaliseGame();
        	return;
        }


        gui.prepareBoard(board);
        final Player last = currentPlayer;
        changeCurrentPlayer();

        //player has time
        if(currentPlayer.hasTime()) {
            gui.showGamePanel(false);
            gui.changeActivePlayer(last, currentPlayer);
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                	changeGui.showChangePlayerGUI(currentPlayer, last, false);
                }
            });
        }
        //player has no time
        else {
        	gui.showGamePanel(false);
        	gui.changeActivePlayer(last, currentPlayer);
        	final Player last2 = currentPlayer;
        	changeCurrentPlayer();
        	gui.changeActivePlayer(last2, currentPlayer);
        	//current player has no time or passed
        	if(!currentPlayer.hasTime() || currentPlayer.getPassCounter()==2)
        		finaliseGame();
        	else {
        		javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                    	changeGui.showChangePlayerGUI(currentPlayer, last2, true);
                    }
                });
        	}
        		
        }
    }

    private boolean isEnded() {
		return (player1.getPassCounter()==2 && player2.getPassCounter()==2) || (bag.getSize()==0 && (!player1.hasLetter() || !player2.hasLetter()));
	}

	private void finaliseGame() {
        gui.showGamePanel(false);
        if(player1.getScore()>player2.getScore())
        	endGUI=new EndGUI(player1,"The winner is: ",config);
        if(player1.getScore()<player2.getScore())
        	endGUI=new EndGUI(player2,"The winner is: ",config);
        if(player1.getScore()==player2.getScore())
        	endGUI=new EndGUI(null,"The match ended in a draw.",config);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            	endGUI.showEndGUI();
            	
            }
        });
	}

	public Player[] getPlayers() {
        Player[] players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        return players;
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
    public int getBagSize() {
    	return bag.getSize();
    }

    //temporary; we need to tide up gui and game. maybe moving main to game is better
    //timer has to start after gui reference was initialized
    void setGUI(GUI gui) {
        this.gui = gui;
        timer.start();
    }

}
