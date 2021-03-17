package dev.negrel.fxchess.engine.piece;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Color;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	void legalMove() throws IllegalPositionException {
		List<Coord> legalMoves = knight.legalMove();
		assertEquals(2, legalMoves.size());

		knight = new Knight(new ChessBoard(), new Coord(3, 3), Color.BLACK);
		legalMoves = knight.legalMove();
		assertEquals(8, legalMoves.size());
	}
}
