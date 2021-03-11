package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.Piece;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Queen defines the queen chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#queen">https://www.chess.com/terms/chess-pieces#queen</a>
 */
public class Queen extends Piece {
	public Queen(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(@NotNull Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		if (diffX == 0 && diffY == 0)
			return false;

		return diffX == diffY || (diffX > 0 && diffY == 0) || (diffY > 0 && diffX == 0);
	}

	public char toChar() {
		return 'Q';
	}

	public List<Coord> legalMove() {
		List<Coord> result = new ArrayList<>();

		// Horizontal check
		result.addAll(
			legalCoords(new Coord(0, coord.getY()))
		);

		result.addAll(
			legalCoords(new Coord(8, coord.getY()))
		);

		// Vertical check
		result.addAll(
			legalCoords(new Coord(coord.getX(), 0))
		);

		result.addAll(
			legalCoords(new Coord(coord.getX(), 8))
		);

		// Diagonal check
		result.addAll(legalCoords(
			new Coord(
				Math.max(0, coord.getX() - coord.getY()),
				Math.max(0, coord.getY() - coord.getX())
			)
		));

		result.addAll(legalCoords(
			new Coord(
				coord.getX() + (7 - coord.getX()),
				coord.getY() + (7 - coord.getY())
			)
		));

		result.addAll(legalCoords(
			new Coord(
				Math.max(0, coord.getX() - coord.getY()),
				coord.getY() + (7 - coord.getY())
			)
		));

		result.addAll(legalCoords(
			new Coord(
				coord.getX() + (7 - coord.getX()),
				Math.max(0, coord.getY() - coord.getX())
			)
		));

		return result;
	}
}


