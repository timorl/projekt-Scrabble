package pl.edu.uj.tcs.matematycy2013;

import java.util.ArrayList;

public class Game {

    private Board board;
    private final Dictionary dictionary;
    private Turn turn;
    private final Player player1;
    private final Player player2;
    private final Bag bag;
    private Player currentPlayer;
    private GUI gui;

    public Game(Config conf, String name1, String name2) {
        //temporary - we don't have Config yet
        //gui = new GUI("Scrabble", this);
        dictionary = new Dictionary();
        bag = new Bag();
        board = new Board();
        player1 = new Player(name1);
        player2 = new Player(name2);
        player1.setLetters(bag.getLetters(7));
        player2.setLetters(bag.getLetters(7));
        currentPlayer = player1;
        turn = new Turn(player1, board);
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

        int score = processMainWord(new LetterCoordinates(onBoard.get(0).x, onBoard.get(0).y), orientation, putLetters);
        if (score == -1) {
            return -1;
        }

        int additionalScore = processAdditionalWords(onBoard, orientation ^ 1, putLetters);
        if (additionalScore == -1) {
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

    public void beginTurn() {
        changeCurrentPlayer();
        turn = new Turn(currentPlayer, board);
        gui.changeActivePlayer(currentPlayer);
    }

    public void finaliseTurn() {
        //temporary - counting points to add
        board.commit(turn);
        gui.prepareBoard(board);
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
    //temporary; we need to tide up gui and game. maybe moving main to game is better

    void setGUI(GUI gui) {
        this.gui = gui;
    }
}
