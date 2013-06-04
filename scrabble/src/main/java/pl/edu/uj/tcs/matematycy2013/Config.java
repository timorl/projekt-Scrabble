package pl.edu.uj.tcs.matematycy2013;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Config {

    private String board;
    private boolean standardBoard;
    private boolean boardIsTorus;

    private String dictionary;
    private boolean standardDictionary;

    private String bag;
    private boolean standardBag;

    private String player1;
    private String player2;
    private int maxTime;

    public Config() {
        board = "defaultBoard.txt";
        standardBoard = true;
        boardIsTorus = false;
        dictionary = "pl";
        standardDictionary = true;
        bag = "pl";
        standardBag = true;
        player1 = "Player 1";
        player2 = "Player 2";
        maxTime = 60;
    }

    public void setDefaultBoard(boolean isTorus) {
        standardBoard = true;
        if(isTorus){
        	board = "defaultBoardTorus.txt";
        }
        else{
        	board = "defaultBoard.txt";
        }
        
        boardIsTorus = isTorus;
    }

    public void setBoard(String pathToFile, boolean isTorus) {
        standardBoard = false;
        board = pathToFile;
        boardIsTorus = isTorus;
    }

    //if standard, then dictName is the language name (pl or en), else it's a path
    public void setDictionary(String dictName, boolean standard) throws FileNotFoundException {
        dictionary = dictName;
        standardDictionary = standard;
        System.out.print(dictName);
    }

    public void setPlayerNames(String name1, String name2) {
        player1 = name1;
        player2 = name2;
    }

    //if standard, then bagName is the language name (pl or en), else it's a path
    public void setBag(String bagName, boolean standard) throws FileNotFoundException {
        bag = bagName;
        standardBag = standard;
        System.out.print(bagName);
    }

    public void setMaxTime(int time) {
        maxTime = time;
    }

    public InputStream getBoardStream() throws FileNotFoundException {
        InputStream boardIS;
        if (standardBoard) {
            boardIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(board);
        } else {
            boardIS = new FileInputStream(board);
        }
        return boardIS;
    }

    public boolean getBoardTorus() {
        return boardIsTorus;
    }

    public InputStream getDictionaryStream() throws FileNotFoundException {
        InputStream dictionaryIS;
        if (standardDictionary) {
            dictionaryIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("words-" + dictionary + ".txt");
        } else {
            dictionaryIS = new FileInputStream(dictionary);
        }
        return dictionaryIS;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public InputStream getBagStream() throws FileNotFoundException {
        InputStream bagIS;
        if (standardBag) {
            bagIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("letters-" + bag + ".txt");
        } else {
            bagIS = new FileInputStream(bag);
        }
        return bagIS;
    }

    public int getMaxTime() {
        return maxTime;
    }

	public String getDictionaryType(){
		return dictionary;
	}

}
