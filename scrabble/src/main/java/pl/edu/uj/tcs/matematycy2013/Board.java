package pl.edu.uj.tcs.matematycy2013;

import java.util.Random;

public class Board {

    private final int size;
    private final Cell[][] cells;
    private final Letter[][] letters;

    public Board() {
        //temporary
        size = 12;
        cells = new Cell[size][size];
        letters = new Letter[size][size];
        Random rand = new Random();


        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                cells[i][j] = Cell.values()[rand.nextInt(7)];
                letters[i][j] = null;
            }
        }

    }

    public Board(Cell[][] cells, int size) {
        this.size = size;
        this.cells = cells;
        letters = new Letter[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                letters[i][j] = null;
            }
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (letters[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void commit(Turn toCommit) {
        Letter[][] put = toCommit.getPutLetters();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (put[i][j] != null) {
                    letters[i][j] = put[i][j];
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return cells;
    }

    public Letter[][] getLetters() {
        return letters;
    }
}
