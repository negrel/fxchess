package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PawnTest {
	@Test
	void isValidMoveBlackPawn() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.BLACK);

		assertTrue(pawn.isValidMove(new Coord(0, 1)));
		assertTrue(pawn.isValidMove(new Coord(-1, 1)));
		assertTrue(pawn.isValidMove(new Coord(1, 1)));

		assertFalse(pawn.isValidMove(new Coord(0, -1)));
	}

	@Test
	void isValidMoveWhitePawn() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.WHITE);

		assertTrue(pawn.isValidMove(new Coord(0, -1)));
		assertTrue(pawn.isValidMove(new Coord(-1, -1)));
		assertTrue(pawn.isValidMove(new Coord(1, -1)));

		assertFalse(pawn.isValidMove(new Coord(0, 1)));
	}

	@Test
	void isValidMoveInvalidMove() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.WHITE);

		assertFalse(pawn.isValidMove(new Coord(1, 5)));
		assertFalse(pawn.isValidMove(new Coord(1, 0)));
		assertFalse(pawn.isValidMove(new Coord(-1, 0)));
		assertFalse(pawn.isValidMove(new Coord(0, 0)));
	}
}
