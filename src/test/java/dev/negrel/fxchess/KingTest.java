package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {
	King king;

	public KingTest() throws IllegalPositionException {
		king = new King(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(king.isValidMove(new Coord(1, 1)));
		assertTrue(king.isValidMove(new Coord(-1, -1)));
		assertTrue(king.isValidMove(new Coord(1, 0)));
		assertTrue(king.isValidMove(new Coord(-1, 0)));
		assertTrue(king.isValidMove(new Coord(0, 1)));
		assertTrue(king.isValidMove(new Coord(0, -1)));

		assertFalse(king.isValidMove(new Coord(0, 0)));
		assertFalse(king.isValidMove(new Coord(2, 2)));
		assertFalse(king.isValidMove(new Coord(-2, -2)));
		assertFalse(king.isValidMove(new Coord(2, 0)));
		assertFalse(king.isValidMove(new Coord(-2, 0)));
		assertFalse(king.isValidMove(new Coord(0, 2)));
		assertFalse(king.isValidMove(new Coord(0, -2)));
	}
}
