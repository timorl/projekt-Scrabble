package pl.edu.uj.tcs.matematycy2013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Turn {

	private final ArrayList<LetterCoordinates> onBoard = new ArrayList<LetterCoordinates>();
	private final ArrayList<Letter> toExchange = new ArrayList<Letter>();
	private final ArrayList<Letter> letters = new ArrayList<Letter>();
	private final Letter[][] putLetters;
	private final Board board;
	public State state;

	public Turn (Player player, Board board) {
		letters.addAll(Arrays.asList(player.getLetters()));
		this.board = board;
		putLetters = new Letter[board.getSize()][board.getSize()];
		for (int i=0; i<board.getSize(); i++) {
			for (int j=0; j<board.getSize(); j++) {
				putLetters[i][j] = null;
			}
		}
		state = State.PASS;
	}
    private int inc (int x, int a) {
    	int p = board.getSize();
    	return (((x+a) %p) + p) % p;
    }

    private boolean check (int X, int Y, LinkedList<LetterCoordinates> coors) {
    	int length = 1;
    	LetterCoordinates beg = coors.getFirst();
    	coors.remove(beg);
    	length = checkLine (X, Y, beg.x, beg.y, length, coors);
    	length = checkLine (-X, -Y, beg.x, beg.y, length, coors);
    	if (length >= board.getSize() || !coors.isEmpty()) return false;
    	else {
    		for (LetterCoordinates co : onBoard) {
    			if (board.isEmpty() && checkStart(co)) return true;
    			else if (checkNeighbours(co)) return true;
    		}
    		return false;
    	}
    }

    private int checkLine (int X, int Y, int x, int y, int length, LinkedList<LetterCoordinates> coors) {
    	x = inc (x, X);
    	y = inc (y, Y);
    	while (length < board.getSize()) {
			if (putLetters[x][y] != null || board.getLetters()[x][y] != null) {
				coors.remove(new LetterCoordinates(x, y));
				length++;
				x = inc (x, X);
				y = inc (y, Y);
			}
			else break;
    	}
    	return length;
    }
    private boolean checkStart(LetterCoordinates co) {
    	if (board.getBoard()[co.x][co.y] == Cell.START) return true;
    	return false;
    }
    private boolean checkNeighbours (LetterCoordinates co) {
    	int[] X = {-1, 1, 0, 0};
    	int[] Y = {0, 0, 1, -1};
    	for (int i=0; i<4; i++) {
    		if (board.getLetters()[inc (co.x, X[i])][inc (co.y, Y[i])]!=null){
    			return true;
    		}
    	}
    	return false;
    }
    private boolean isValid() {
    	LinkedList<LetterCoordinates> coors = new LinkedList<LetterCoordinates>(onBoard);
    	if (coors.size()==1) {
    		if (board.isEmpty()) return checkStart(coors.getFirst());
    		return checkNeighbours(coors.getFirst());
    	}
    	else {
    		LetterCoordinates c1 = coors.peekFirst();
    		LetterCoordinates c2 = coors.peekLast();
    		if (c1.x == c2.x) return check(0, 1, coors);
    		else if (c1.y == c2.y)return check(1, 0, coors);
    		else return false;
    	}
    }

    public void setState (State state) {
    	this.state = state;
    }
    private State setState() {
    	if (toExchange.isEmpty()) {
    		if (onBoard.isEmpty()) return State.PASS;
    		else if (isValid()) return State.WORD;
    		else return State.INVALID;
    	}
    	else {
    		if (onBoard.isEmpty()) return State.EXCHANGE;
    		else return State.INVALID;
    	}
    }

    public void putLetterOnTable(Letter toPut, int x, int y) {
        if (letters.contains(toPut)) {
        	letters.remove(toPut);
        	onBoard.add(new LetterCoordinates(x, y));
        	putLetters[x][y] = toPut;
        }
        
    	state = setState();
    	return;
        
    }

    public void addLetterToExchange(Letter toAdd) {
        if (letters.contains(toAdd)) {
        	letters.remove(toAdd);
        	toExchange.add(toAdd);
        }
        state = setState();
        return;
    }

    public void removeLetterFromExchange(Letter toRemove) {
        if (toExchange.contains(toRemove)) {
        	toExchange.remove(toRemove);
        	letters.add(toRemove);
        }
        state = setState();
        return;
    }

    public void removeLetterFromBoard(Letter toRemove, int x, int y) {
    	LetterCoordinates co = new LetterCoordinates(x, y);
        if (onBoard.contains(co)) {
        	letters.add(putLetters[x][y]);
        	putLetters[x][y] = null;
        	onBoard.remove(co);
        }
        state = setState();
        return;
    }

    public Letter[][] getPutLetters() {
    	return putLetters;
    }

}
