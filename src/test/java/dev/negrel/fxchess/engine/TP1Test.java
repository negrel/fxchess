package dev.negrel.fxchess.engine;

import dev.negrel.fxchess.engine.board_exception.IllegalMoveException;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import dev.negrel.fxchess.engine.piece.King;
import dev.negrel.fxchess.engine.piece.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TP1Test {
	@Test
	void ex1() throws IllegalPositionException, IllegalMoveException {
		ChessBoard board = new ChessBoard();
		board.smartPrint();

		King king = new King(board, new Coord(2, 3), Color.BLACK);
		board.smartPrint();

		king.move(new Coord(3, 3));
		board.smartPrint();
	}

	@Test
	void ex2() throws IllegalPositionException {
		ChessBoard board = new ChessBoard();

		King king = new King(board, new Coord(0, 0), Color.BLACK);

		assertThrows(IllegalPositionException.class, () -> king.move(new Coord(-1, -1)));
	}

	@Test
	void ex3() throws IllegalPositionException {
		ChessBoard board = new ChessBoard();

		Queen queen = new Queen(board, new Coord(0, 0), Color.BLACK);

		assertThrows(IllegalMoveException.class, () -> queen.move(new Coord(1, 2)));
	}
}
