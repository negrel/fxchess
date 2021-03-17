package dev.negrel.fxchess.engine.piece;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Color;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.Piece;
import dev.negrel.fxchess.engine.board_exception.IllegalMoveException;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Pawn defines the pawn chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#pawn">https://www.chess.com/terms/chess-pieces#pawn</a>
 */
public class Pawn extends Piece {
	private boolean isFirstMove = true;

	public Pawn(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	private int getDirection() {
		return color == Color.BLACK ? -1 : 1;
	}

	@Override
	protected boolean isValidMove(@NotNull Coord destination) {
		int diffX = destination.getX() - coord.getX();
		int diffY = destination.getY() - coord.getY();

		int direction = getDirection();

		boolean isEating = false;
		try {
			isEating = board.isOccupied(destination);
		} catch (IllegalPositionException ignored) {
		}

		if (Math.abs(diffX) == 1 && diffY == direction && isEating)
			return true;

		return diffX == 0 && (diffY == direction || (isFirstMove && diffY == direction * 2));
	}

	public char toChar() {
		return 'P';
	}

	@Override
	public void move(Coord destination) throws IllegalMoveException, IllegalPositionException {
		super.move(destination);
		isFirstMove = false;
	}

	public List<Coord> legalMove() {
		List<Coord> result = new ArrayList<>();
		int direction = getDirection();

		if (isFirstMove)
			result.add(new Coord(coord.getX(), coord.getY() + direction * 2));

		result.add(new Coord(coord.getX(), coord.getY() + direction));

		Coord diagonal = new Coord(coord.getX() + 1, coord.getY() + direction);
		try {
			Piece other = (Piece) board.getMovable(diagonal);
			if (other != null && !other.color.equals(this.color))
				result.add(diagonal);
		} catch (IllegalPositionException ignored) {
		}

		diagonal = new Coord(coord.getX() - 1, coord.getY() + direction);
		try {
			Piece other = (Piece) board.getMovable(diagonal);
			if (other != null && !other.color.equals(this.color))
				result.add(diagonal);
		} catch (IllegalPositionException ignored) {
		}

		return result;
	}
}


