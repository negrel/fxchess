package dev.negrel.fxchess.engine.piece;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Color;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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

	@Test
	void legalMove() throws IllegalPositionException {
		Pawn pawn = new Pawn(new ChessBoard(), new Coord(), Color.WHITE);

		List<Coord> legalMoves = pawn.legalMove();
		assertEquals(1, legalMoves.size());
	}
}
