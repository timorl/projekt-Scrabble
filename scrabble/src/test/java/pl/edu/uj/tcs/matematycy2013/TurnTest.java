package pl.edu.uj.tcs.matematycy2013;

import org.junit.*;
import static org.junit.Assert.*;

public class TurnTest {

	@Test
	public void testSimpleTurn() {
		Player player = new Player("dudu",60*20);
		Cell[][] cells = new Cell[13][13];
		for (int i=0; i<13; i++) {
			for (int j=0; j< 13; j++) {
				cells[i][j] = Cell.EMPTY;
			}
		}
		cells[6][6] = Cell.START;
		Board board = new Board(cells, 13);
		Turn turn = new Turn (player, board, 17);
		Letter[] lets = player.getLetters();

		turn.putLetterOnTable(lets[0], 5, 6);
		assertEquals("INVALID", turn.state.toString());

		turn.putLetterOnTable(lets[1], 6, 6);
		assertEquals("WORD", turn.state.toString());

		turn.putLetterOnTable(lets[2], 1, 12);
		assertEquals("INVALID", turn.state.toString());

		Letter[][] put = turn.getPutLetters();
		turn.removeLetterFromBoard(put[1][12], 1, 12);
		assertEquals("WORD", turn.state.toString());

		turn.addLetterToExchange(lets[2]);
		assertEquals("INVALID", turn.state.toString());

		turn.removeLetterFromBoard(put[6][6], 6, 6);
		turn.removeLetterFromBoard(put[5][6], 5, 6);
		assertEquals("EXCHANGE", turn.state.toString());

		turn.removeLetterFromExchange(lets[2]);
		assertEquals("PASS", turn.state.toString());

		for (int i=0; i<7; i++) {
			turn.putLetterOnTable(lets[i], i, 6);
		}
		assertEquals("WORD", turn.state.toString());
		board.commit(turn);

		player = new Player("t",60*20);
		turn = new Turn (player, board, 17);
		lets = player.getLetters();
		turn.putLetterOnTable(lets[0], 3, 5);
		assertEquals("WORD", turn.state.toString());

		turn.removeLetterFromBoard(lets[0], 3, 5);
		assertEquals("PASS", turn.state.toString());

		turn.putLetterOnTable(lets[0], 12, 6);
		assertEquals("WORD", turn.state.toString());

		turn.putLetterOnTable(lets[1], 1, 1);
		assertEquals("INVALID", turn.state.toString());
	}

}
