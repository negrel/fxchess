package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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

	@Test
	void legalMove() {
		List<Coord> legalMoves = queen.legalMove();
		System.out.println(legalMoves);
		assertEquals(21, legalMoves.size());
	}
}
