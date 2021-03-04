package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.Piece;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

/**
 * Knight defines the knight chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#knight">https://www.chess.com/terms/chess-pieces#knight</a>
 */
public class Knight extends Piece {
	public Knight(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		return (diffX == 2 && diffY == 1) || (diffY == 2 && diffX == 1);
	}

	public char toChar() {
		return 'k';
	}

	// The knight piece jump above piece therefore, it's path is always valid.
	protected boolean isValidPath(Coord destination) {
		return true;
	}
}


