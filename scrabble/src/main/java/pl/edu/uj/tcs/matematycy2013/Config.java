package pl.edu.uj.tcs.matematycy2013;

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
        //TODO
    }

    public void setBoard(String pathToFile, boolean torus) {
        //TODO
    }

    //if standard, then dictName is the language name, else it's a path
    public void setDictionary(String dictName, boolean standard) {
        //TODO
    }

    public void setPlayerNames(String name1, String name2) {
        //TODO
    }

    //if standard, then bagName is the language name, else it's a path
    public void setBag(String bagName, boolean standard) {
        //TODO
    }

    //prolly not int, that's a placeholder
    public void setMaxTime(int time) {
        //TODO
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
