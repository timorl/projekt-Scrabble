package pl.edu.uj.tcs.matematycy2013;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Config {

    private InputStream board;
    private boolean boardIsTorus;
    private InputStream dictionary;
    private InputStream bag;
    private String player1;
    private String player2;
    //prolly not int, placeholder
    private int maxTime;

    public Config() {
    }

    public void setDefaultBoard() {
        board = Thread.currentThread().getContextClassLoader().getResourceAsStream("defaultBoard.txt");
        boardIsTorus = false;
    }

    public void setBoard(String pathToFile, boolean isTorus) throws FileNotFoundException {
        board = new FileInputStream(pathToFile);
        boardIsTorus = isTorus;
    }

    //if standard, then dictName is the language name (pl or en), else it's a path
    public void setDictionary(String dictName, boolean standard) throws FileNotFoundException {
        if (standard) {
            dictionary = Thread.currentThread().getContextClassLoader().getResourceAsStream("words-" + dictName + ".txt");
        } else {
            dictionary = new FileInputStream(dictName);
        }
    }

    public void setPlayerNames(String name1, String name2) {
        player1 = name1;
        player2 = name2;
    }

    //if standard, then bagName is the language name (pl or en), else it's a path
    public void setBag(String bagName, boolean standard) throws FileNotFoundException {
        if (standard) {
            bag = Thread.currentThread().getContextClassLoader().getResourceAsStream("letters-" + bagName + ".txt");
        } else {
            bag = new FileInputStream(bagName);
        }
    }

    //prolly not int, that's a placeholder
    public void setMaxTime(int time) {
        //temporary
        maxTime = time;
    }

    public InputStream getBoardStream() {
        return board;
    }

    public boolean getBoardTorus() {
        return boardIsTorus;
    }

    public InputStream getDictionaryStream() {
        return dictionary;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public InputStream getBagStream() {
        return bag;
    }

    public int getMaxTime() {
        return maxTime;
    }

}
