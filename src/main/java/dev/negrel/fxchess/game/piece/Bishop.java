package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.Piece;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

/**
 * Bishop defines the bishop chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#bishop">https://www.chess.com/terms/chess-pieces#bishop</a>
 */
public class Bishop extends Piece {
	public Bishop(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		return (diffX == diffY && diffY > 0);
	}

	public String toString() {
		return "B";
	}
}


