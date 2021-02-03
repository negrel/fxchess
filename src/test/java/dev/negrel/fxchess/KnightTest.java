package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class KnightTest {
	Knight knight;

	public KnightTest() throws IllegalPositionException {
		knight = new Knight(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(knight.isValidMove(new Coord(1, 2)));
		assertTrue(knight.isValidMove(new Coord(1, -2)));
		assertTrue(knight.isValidMove(new Coord(-1, 2)));
		assertTrue(knight.isValidMove(new Coord(-1, -2)));
		assertTrue(knight.isValidMove(new Coord(2, 1)));
		assertTrue(knight.isValidMove(new Coord(2, -1)));
		assertTrue(knight.isValidMove(new Coord(-2, 1)));
		assertTrue(knight.isValidMove(new Coord(-2, -1)));


		assertFalse(knight.isValidMove(new Coord(0, 2)));
		assertFalse(knight.isValidMove(new Coord(0, -2)));
		assertFalse(knight.isValidMove(new Coord(2, 0)));
		assertFalse(knight.isValidMove(new Coord(-2, 0)));
		assertFalse(knight.isValidMove(new Coord(0, 1)));
		assertFalse(knight.isValidMove(new Coord(0, -1)));
		assertFalse(knight.isValidMove(new Coord(1, 0)));
		assertFalse(knight.isValidMove(new Coord(-1, 0)));

		assertFalse(knight.isValidMove(new Coord(1, 3)));
		assertFalse(knight.isValidMove(new Coord(-1, 3)));
		assertFalse(knight.isValidMove(new Coord(1, -3)));
		assertFalse(knight.isValidMove(new Coord(-1, -3)));

		assertFalse(knight.isValidMove(new Coord(3, 1)));
		assertFalse(knight.isValidMove(new Coord(-3, 1)));
		assertFalse(knight.isValidMove(new Coord(3, -1)));
		assertFalse(knight.isValidMove(new Coord(-3, -1)));


		assertFalse(knight.isValidMove(new Coord(0, 0)));
	}
}
