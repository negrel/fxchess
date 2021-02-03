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
	void occupation() {
		assertFalse(board.isOccupied(new Coord()));
		board.setOccupation(new Coord(), true);
		assertTrue(board.isOccupied(new Coord()));
		board.setOccupation(new Coord(), false);
		assertFalse(board.isOccupied(new Coord()));
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
	void smartPrintBoard() {
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
