/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.tcs.matematycy2013;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

    @Test
    public void countScoreTest() {
        Game game = new Game(new Config(), "dudu", "marcin");
        Cell[][] cells = new Cell[13][13];

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }

        cells[6][6] = Cell.START;
        cells[5][6] = Cell.TRIPLELETTER;
        cells[7][6] = Cell.TRIPLEWORD;
        cells[7][7] = Cell.TRIPLEWORD;

        Letter[] letters = new Letter[7];
        Board board = new Board(cells, 13);
        Player[] players = game.getPlayers();

        for (int i = 0; i < 7; ++i) {
            letters[i] = new Letter('A', i);
        }

        players[0].setLetters(letters);
        players[1].setLetters(letters);

        game.setBoard(board);
        Turn turn = new Turn(players[0], board);
        Turn turn2 = new Turn(players[1], board);
        
        for(int i=0; i<3; ++i){
            turn.putLetterOnTable(letters[i+1], i+5, 6);
        }

        assertEquals(48, game.countScore(turn));
        board.commit(turn);

        for(int i=0; i<3; ++i){
            turn2.putLetterOnTable(letters[i+4], i+5, 7);
        }
        
        assertEquals(84, game.countScore(turn2));
        board.commit(turn2);
    }

    public void CountScoreSimpleTest() {
        Game game = new Game(new Config(), "timorl", "michal");
        Cell[][] cells = new Cell[13][13];

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }

        cells[6][6] = Cell.START;

        Letter[] letters = new Letter[7];
        Board board = new Board(cells, 13);
        Player[] players = game.getPlayers();

        for (int i = 0; i < 7; ++i) {
            letters[i] = new Letter('A', i);
        }

        players[0].setLetters(letters);
        Turn turn = new Turn(players[0], board);
        
        for(int i=0; i<7; ++i){
            turn.putLetterOnTable(letters[i], 2, i);
        }
        
        assertEquals(92, game.countScore(turn));
        board.commit(turn);

    }
}
