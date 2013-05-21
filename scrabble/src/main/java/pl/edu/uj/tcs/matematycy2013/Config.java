package pl.edu.uj.tcs.matematycy2013;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class Config {

    private Board board;
    private Dictionary dictionary;
    private Player player1;
    private Player player2;
    private Bag bag;
    //prolly not int, placeholder
    private int maxTime;

    public Config() {
    }

    public void setDefaultBoard() {
        try {
            board = new Board( Thread.currentThread().getContextClassLoader().getResourceAsStream("defaultBoard.txt"), false );
        } catch (Exception e) {
            System.out.println("Default board not working, everything will crash now, brace for impact.");
        }
    }

    public void setBoard(String pathToFile, boolean isTorus) {
        try {
            board = new Board(new FileInputStream(pathToFile), isTorus);
        } catch (Exception e) {
            System.out.println("Provided file not found or just not working, falling back to default.");
            setDefaultBoard();
        }
    }

    //if standard, then dictName is the language name (pl or en), else it's a path
    public void setDictionary(String dictName, boolean standard) {
        if (standard) {
            try {
                dictionary = new Dictionary( Thread.currentThread().getContextClassLoader().getResourceAsStream("words-" + dictName + ".txt"), new Alphabet(Thread.currentThread().getContextClassLoader().getResourceAsStream("letters-" + dictName + ".txt") ) );
            } catch (Exception e) {
                System.out.println("Either dictionary or bag file not working, brace for failure.");
            }
        } else {
            //Not really possible, since we need an alphabet.
        }
    }

    public void setPlayerNames(String name1, String name2) {
        player1 = new Player(name1);
        player2 = new Player(name2);
    }

    //if standard, then bagName is the language name (pl or en), else it's a path
    public void setBag(String bagName, boolean standard) {
        if (standard) {
            try {
                bag = new Bag( Thread.currentThread().getContextClassLoader().getResourceAsStream("letters-" + bagName + ".txt") );
            } catch (Exception e) {
                System.out.println("Bag file not working, brace for failure.");
            }
        } else {
            try {
                bag = new Bag( new FileInputStream(bagName) );
            } catch (Exception e) {
                System.out.println("Bag file not working, brace for failure.");
            }
        }
    }

    //prolly not int, that's a placeholder
    public void setMaxTime(int time) {
        //temporary
        maxTime = time;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Bag getBag() {
        return bag;
    }

    public int getMaxTime() {
        return maxTime;
    }

}
