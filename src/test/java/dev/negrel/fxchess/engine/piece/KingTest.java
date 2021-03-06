package dev.negrel.fxchess.engine.piece;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Color;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	void legalMove() throws IllegalPositionException {
		List<Coord> legalMoves = king.legalMove();
		assertEquals(3, legalMoves.size());

		king = new King(new ChessBoard(), new Coord(4, 4), Color.BLACK);
		legalMoves = king.legalMove();
		assertEquals(8, legalMoves.size());
	}
}
