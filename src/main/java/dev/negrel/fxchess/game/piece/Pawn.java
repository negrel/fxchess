package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.Piece;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

/**
 * Pawn defines the pawn chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#pawn">https://www.chess.com/terms/chess-pieces#pawn</a>
 */
public class Pawn extends Piece {
	public Pawn(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord destination) {
		int diffX = destination.getX() - coord.getX();
		int diffY = destination.getY() - coord.getY();

		int y = (color == Color.BLACK ? -1 : 1);

		if (Math.abs(diffX) == 1 && diffY == y)
			return true;

		return diffX == 0 && diffY == y;
	}

	public char toChar() {
		return 'P';
	}
}


