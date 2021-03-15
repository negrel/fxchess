package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BishopTest {
	Bishop bishop;

	public BishopTest() throws IllegalPositionException {
		bishop = new Bishop(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(bishop.isValidMove(new Coord(5, 5)));
		assertTrue(bishop.isValidMove(new Coord(-5, 5)));
		assertTrue(bishop.isValidMove(new Coord(5, -5)));
		assertTrue(bishop.isValidMove(new Coord(-5, -5)));

		assertFalse(bishop.isValidMove(new Coord(1, 0)));
		assertFalse(bishop.isValidMove(new Coord(1, 5)));
		assertFalse(bishop.isValidMove(new Coord(0, 1)));
		assertFalse(bishop.isValidMove(new Coord(-1, 5)));
		assertFalse(bishop.isValidMove(new Coord(0, 0)));
	}


	@Test
	void legalMove() {
		List<Coord> legalMoves = bishop.legalMove();
		assertEquals(7, legalMoves.size());
	}
}
