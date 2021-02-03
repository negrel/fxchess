package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PawnTest {
	@Test
	void isValidMoveBlackPawn() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.BLACK);

		assertTrue(pawn.isValidMove(new Coord(0, 1)));
		assertFalse(pawn.isValidMove(new Coord(0, -1)));
	}

	@Test
	void isValidMoveWhitePawn() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.WHITE);

		assertTrue(pawn.isValidMove(new Coord(0, -1)));
		assertFalse(pawn.isValidMove(new Coord(0, 1)));
	}

	@Test
	void isValidMoveInvalidMove() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.WHITE);

		assertFalse(pawn.isValidMove(new Coord(1, 1)));
		assertFalse(pawn.isValidMove(new Coord(1, 5)));
		assertFalse(pawn.isValidMove(new Coord(-1, -1)));
		assertFalse(pawn.isValidMove(new Coord(-1, 5)));
		assertFalse(pawn.isValidMove(new Coord(0, 0)));
	}
}
