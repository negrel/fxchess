package dev.negrel.fxchess.engine.piece;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Color;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.Piece;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Rook defines the Rook chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#rook">https://www.chess.com/terms/chess-pieces#rook</a>
 */
public class Rook extends Piece {
	public Rook(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(@NotNull Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		return (diffX > 0 && diffY == 0) || (diffY > 0 && diffX == 0);
	}

	public char toChar() {
		return 'R';
	}

	@Override
	public List<Coord> legalMove() {
		List<Coord> result = new ArrayList<>();

		// Horizontal check
		result.addAll(
			legalCoords(new Coord(0, coord.getY()))
		);

		result.addAll(
			legalCoords(new Coord(7, coord.getY()))
		);

		// Vertical check
		result.addAll(
			legalCoords(new Coord(coord.getX(), 0))
		);

		result.addAll(
			legalCoords(new Coord(coord.getX(), 7))
		);
		return result;
	}
}


