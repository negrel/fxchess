package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChessBoardTest {
	ChessBoard board;

	@BeforeEach
	void classInit() {
		this.board = new ChessBoard();
		this.board.clearBoard();
	}

	@Test
	void init() {
		board.init();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		board.smartPrint();

		String expectedOutput = """
			8 │ R | k | B | Q | K | B | k | R |\s
			7 │ P | P | P | P | P | P | P | P |\s
			6 │   |   |   |   |   |   |   |   |\s
			5 │   |   |   |   |   |   |   |   |\s
			4 │   |   |   |   |   |   |   |   |\s
			3 │   |   |   |   |   |   |   |   |\s
			2 │ P | P | P | P | P | P | P | P |\s
			1 │ R | k | B | Q | K | B | k | R |\s
			──┼────────────────────────────────
			0 │ 1   2   3   4   5   6   7   8

			""";

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	void occupation() throws IllegalPositionException {
		Movable m = mock(Piece.class);

		assertFalse(board.isOccupied(new Coord()));
		board.setOccupation(new Coord(), m);
		assertTrue(board.isOccupied(new Coord()));
		board.setOccupation(new Coord(), null);
		assertFalse(board.isOccupied(new Coord()));
	}

	/* These tests show us the pain that it is to test bad exception.
	 *  IllegalPositionException should be raised in the Coord constructor.
	 */
	@Test
	void occupationThrowIllegalPositionException() {
		Movable m = mock(Piece.class);

		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(1000, 1000), m));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(5, 1000), m));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(1000, 5), m));

		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(-1, -1), m));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(-1, 5), m));
		assertThrows(IllegalPositionException.class, () -> board.setOccupation(new Coord(5, -1), m));


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
			8 │   |   |   |   |   |   |   |   |\s
			7 │   |   |   |   |   |   |   |   |\s
			6 │   |   |   |   |   |   |   |   |\s
			5 │   |   |   |   |   |   |   |   |\s
			4 │   |   |   |   |   |   |   |   |\s
			3 │   |   |   |   |   |   |   |   |\s
			2 │   |   |   |   |   |   |   |   |\s
			1 │   |   |   |   |   |   |   |   |\s
			──┼────────────────────────────────
			0 │ 1   2   3   4   5   6   7   8

			""";

		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	void smartPrintBoard() throws IllegalPositionException {
		Movable mockT = mock(Piece.class);
		when(mockT.toString()).thenReturn("T");

		Movable mockZ = mock(Piece.class);
		when(mockZ.toString()).thenReturn("Z");

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));


		board.setOccupation(new Coord(0, 0), mockT);
		board.setOccupation(new Coord(2, 3), mockZ);
		board.smartPrint();

		String expectedOutput = """
			8 │   |   |   |   |   |   |   |   |\s
			7 │   |   |   |   |   |   |   |   |\s
			6 │   |   |   |   |   |   |   |   |\s
			5 │   |   |   |   |   |   |   |   |\s
			4 │   |   | Z |   |   |   |   |   |\s
			3 │   |   |   |   |   |   |   |   |\s
			2 │   |   |   |   |   |   |   |   |\s
			1 │ T |   |   |   |   |   |   |   |\s
			──┼────────────────────────────────
			0 │ 1   2   3   4   5   6   7   8

			""";


		assertEquals(expectedOutput, outContent.toString());
	}
}
