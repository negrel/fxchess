package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PieceTest {
	PieceImpl piece;

	public PieceTest() throws IllegalPositionException {
		piece = new PieceImpl(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void move() {
		Coord coord = new Coord(1, 1);
		piece.validMove = true;

		assertDoesNotThrow(() -> piece.move(coord));
		assertEquals(coord, piece.getCoord());
	}

	@Test
	void moveThrowIllegalPositionException() {
		Coord invalidCoord = new Coord(-1, -1);
		piece.validMove = true;

		assertThrows(IllegalPositionException.class, () -> piece.move(invalidCoord));
	}

	@Test
	void moveThrowIllegalMoveException() {
		Coord invalidCoord = new Coord(-1, -1);
		piece.validMove = false;

		assertThrows(IllegalMoveException.class, () -> piece.move(invalidCoord));
	}


	private static class PieceImpl extends Piece {
		public boolean validMove;

		public PieceImpl(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
			super(board, coord, color);
		}

		@Override
		protected boolean isValidMove(Coord c) {
			return validMove;
		}
	}
}
