package dev.negrel.fxchess.engine.piece;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Color;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RookTest {
	Rook rook;

	public RookTest() throws IllegalPositionException {
		rook = new Rook(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(rook.isValidMove(new Coord(5, 0)));
		assertTrue(rook.isValidMove(new Coord(-5, 0)));
		assertTrue(rook.isValidMove(new Coord(0, 5)));
		assertTrue(rook.isValidMove(new Coord(0, -5)));


		assertFalse(rook.isValidMove(new Coord(1, 5)));
		assertFalse(rook.isValidMove(new Coord(-1, -1)));
		assertFalse(rook.isValidMove(new Coord(-1, 5)));
		assertFalse(rook.isValidMove(new Coord(5, 1)));
		assertFalse(rook.isValidMove(new Coord(-5, -5)));
		assertFalse(rook.isValidMove(new Coord(-5, 1)));
		assertFalse(rook.isValidMove(new Coord(0, 0)));
	}

	@Test
	void legalMove() throws IllegalPositionException {
		List<Coord> legalMoves = rook.legalMove();
		assertEquals(14, legalMoves.size());
	}
}
