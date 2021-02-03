package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
	ChessBoard board;

	public ChessBoardTest() {
		board = new ChessBoard();
	}

	@Test
	void occupation() throws IllegalPositionException {
		assertFalse(board.isOccupied(new Coord()));
		board.setOccupation(new Coord(), true);
		assertTrue(board.isOccupied(new Coord()));
		board.setOccupation(new Coord(), false);
		assertFalse(board.isOccupied(new Coord()));
	}

	/* These tests show us the pain that it is to test bad exception.
	 *  IllegalPositionException should be raised in the Coord constructor.
	 */
	@Test
	void occupationThrowIllegalPositionException() {
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(1000, 1000), true));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(5, 1000), true));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(1000, 5), true));

		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(-1, -1), true));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(-1, 5), true));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(5, -1), true));


		assertThrows(IllegalPositionException.class, () -> board.isOccupied(new Coord(1000, 1000)));
		assertThrows(IllegalPositionException.class, () -> board.isOccupied(new Coord(1000, 5)));
		assertThrows(IllegalPositionException.class, () -> board.isOccupied(new Coord(5, 1000)));

		assertThrows(IllegalPositionException.class, () -> board.isOccupied(new Coord(-1, -1)));
		assertThrows(IllegalPositionException.class, () -> board.isOccupied(new Coord(-1, 5)));
		assertThrows(IllegalPositionException.class, () -> board.isOccupied(new Coord(5, -1)));
	}

	@Test
	void smartPrintEmptyBoard() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		board.smartPrint();

		String expectedOutput = """
			8 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			7 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			6 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			5 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			4 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			3 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			2 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			1 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			──┼────────────────────────────────
			0 │ 1   2   3   4   5   6   7   8

			""";

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	void smartPrintBoard() throws IllegalPositionException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));


		board.setOccupation(new Coord(0, 0), true);
		board.setOccupation(new Coord(2, 3), true);
		board.smartPrint();

		String expectedOutput = """
			8 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			7 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			6 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			5 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			4 │ 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 |\s
			3 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			2 │ 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			1 │ 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |\s
			──┼────────────────────────────────
			0 │ 1   2   3   4   5   6   7   8

			""";


		assertEquals(expectedOutput, outContent.toString());
	}
}
