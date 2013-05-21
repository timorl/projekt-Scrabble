package pl.edu.uj.tcs.matematycy2013;

import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Board {

    private final int size;
    private final Cell[][] cells;
    private final Letter[][] letters;
    private final boolean isTorus;

    public Board() {
        //temporary
        size = 12;
        cells = new Cell[size][size];
        letters = new Letter[size][size];
        Random rand = new Random();
        isTorus = false;


        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                cells[i][j] = Cell.values()[rand.nextInt(7)];
                letters[i][j] = null;
            }
        }

    }

    public Board(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String line = reader.readLine();
        size = Integer.parseInt(line);
        cells = new Cell[size][size];
        letters = new Letter[size][size];
        for ( int i = 0; i < size; i++ ) {
            for( int j = 0; j < size; j++ ) {
                cells[i][j] = Cell.EMPTY;
            }
        }
        Cell[] vals = Cell.values();
        while ((line = reader.readLine()) != null) {
            String[] t = line.split(" ");
            cells[Integer.parseInt(t[0])][Integer.parseInt(t[1])] = vals[Integer.parseInt(t[2])];
        }
        reader.close();
        isTorus = false;
    }

    public Board(InputStream stream, boolean isTorus) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String line = reader.readLine();
        size = Integer.parseInt(line);
        cells = new Cell[size][size];
        letters = new Letter[size][size];
        for ( int i = 0; i < size; i++ ) {
            for( int j = 0; j < size; j++ ) {
                cells[i][j] = Cell.EMPTY;
            }
        }
        Cell[] vals = Cell.values();
        while ((line = reader.readLine()) != null) {
            String[] t = line.split(" ");
            cells[Integer.parseInt(t[0])][Integer.parseInt(t[1])] = vals[Integer.parseInt(t[2])];
        }
        reader.close();
        this.isTorus = isTorus;
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
        isTorus = false;
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
