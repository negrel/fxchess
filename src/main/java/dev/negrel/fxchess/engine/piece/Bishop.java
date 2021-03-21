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
 * Bishop defines the bishop chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#bishop">https://www.chess.com/terms/chess-pieces#bishop</a>
 */
public class Bishop extends Piece {
	public Bishop(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(@NotNull Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		return (diffX == diffY && diffY > 0);
	}


	public char toChar() {
		return 'B';
	}

	@Override
	public List<Coord> legalMove() {
		List<Coord> result = new ArrayList<>();

		// Diagonal check
		// Bottom right
		result.addAll(legalCoords(
			new Coord(
				coord.getX() + Math.min(7 - coord.getX(), 7 - coord.getY()),
				coord.getY() + Math.min(7 - coord.getX(), 7 - coord.getY())
			)
		));

		// Top left
		result.addAll(legalCoords(
			new Coord(
				coord.getX() - Math.min(coord.getX(), coord.getY()),
				coord.getY() - Math.min(coord.getX(), coord.getY())
			)
		));

		// Bottom left
		result.addAll(legalCoords(
			new Coord(
				coord.getX() - Math.max(7 - coord.getX(), 7 - coord.getY()),
				coord.getY() + Math.max(7 - coord.getX(), 7 - coord.getY())
			)
		));

		// Top right
		result.addAll(legalCoords(
			new Coord(
				coord.getX() + Math.max(7 - coord.getX(), 7 - coord.getY()),
				coord.getY() - Math.max(7 - coord.getX(), 7 - coord.getY())
			)
		));

		return result;
	}
}


