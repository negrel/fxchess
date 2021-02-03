package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QueenTest {
	Queen queen;

	public QueenTest() throws IllegalPositionException {
		queen = new Queen(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(queen.isValidMove(new Coord(5, 5)));
		assertTrue(queen.isValidMove(new Coord(5, 0)));
		assertTrue(queen.isValidMove(new Coord(-5, 0)));
		assertTrue(queen.isValidMove(new Coord(0, 5)));
		assertTrue(queen.isValidMove(new Coord(0, -5)));
		assertTrue(queen.isValidMove(new Coord(-5, -5)));

		assertFalse(queen.isValidMove(new Coord(1, 5)));
		assertFalse(queen.isValidMove(new Coord(1, -5)));
		assertFalse(queen.isValidMove(new Coord(-1, 5)));
		assertFalse(queen.isValidMove(new Coord(-1, -5)));
		assertFalse(queen.isValidMove(new Coord(0, 0)));
	}
}
