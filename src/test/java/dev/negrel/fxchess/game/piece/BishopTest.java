package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BishopTest {
	Bishop bishop;

	public BishopTest() throws IllegalPositionException {
		bishop = new Bishop(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(bishop.isValidMove(new Coord(5, 0)));
		assertTrue(bishop.isValidMove(new Coord(-5, 0)));
		assertTrue(bishop.isValidMove(new Coord(0, 5)));
		assertTrue(bishop.isValidMove(new Coord(0, -5)));

		assertFalse(bishop.isValidMove(new Coord(1, 1)));
		assertFalse(bishop.isValidMove(new Coord(1, 5)));
		assertFalse(bishop.isValidMove(new Coord(-1, -1)));
		assertFalse(bishop.isValidMove(new Coord(-1, 5)));
		assertFalse(bishop.isValidMove(new Coord(0, 0)));
	}
}
