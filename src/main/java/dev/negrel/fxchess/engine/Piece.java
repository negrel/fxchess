package dev.negrel.fxchess.engine;

import dev.negrel.fxchess.engine.board_exception.IllegalMoveException;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Piece is a generic class that define a chess Piece.
 * All the pieces defined in the piece package inherit from
 * this class.
 */
public abstract class Piece implements Movable, Serializable {
	/**
	 * The color of this piece (white or black).
	 */
	public final Color color;
	/**
	 * The chess board on which this piece is.
	 */
	protected final ChessBoard board;
	/**
	 * The coordinate of this piece on the chessboard.
	 */
	protected Coord coord;

	/**
	 * Instantiate a new Piece object.
	 *
	 * @param board The chess board on which this piece is.
	 * @param coord The coordinate of this piece on the chessboard.
	 * @param color The color of this piece (white or black).
	 * @throws IllegalPositionException if the given coordinate is out of the chessboard.
	 */
	public Piece(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		this.board = board;
		this.color = color;
		this.coord = coord;

		this.board.setOccupation(coord, this);
	}

	protected List<Coord> legalCoords(@NotNull Coord to) {
		List<Coord> result = new ArrayList<>();

		int diffX = coord.getX() - to.getX();
		int incX = diffX > 0 ? 1 : -1;

		int diffY = coord.getY() - to.getY();
		int incY = diffY > 0 ? 1 : -1;

		boolean ok = false;

		if (diffX == 0 && diffY != 0) {
			for (int i = to.getY(); i != coord.getY(); i += incY) {
				Coord c = new Coord(coord.getX(), i);
				if (ok || isLegalMove(c)) {
					result.add(c);
					ok = true;
				}
			}
		} else if (diffY == 0 && diffX != 0) {
			for (int i = to.getX(); i != coord.getX(); i += incX) {
				Coord c = new Coord(i, coord.getY());
				if (ok || isLegalMove(c)) {
					result.add(c);
					ok = true;
				}
			}
		} else if (diffX == diffY) {
			for (int i = -diffX; i != 0; i += incX) {
				Coord c = new Coord(coord.getX() + i, coord.getY() + (i * -incY));
				boolean isLegal = isLegalMove(c);
				if (ok || isLegal) {
					result.add(c);
					ok = true;
				}
			}
		}

		return result;
	}

	/**
	 * Checks whether this Piece can legally move to the given coordinate.
	 *
	 * @param destination The destination of the piece.
	 * @return true if this Piece can move to the destination.
	 */
	public boolean isLegalMove(@NotNull Coord destination) {
		boolean isValid = isValidMove(destination);
		boolean isLegalDst = isLegalDestination(destination);
		boolean isLegalPath = board.isLegalPath(this.coord, destination);
		return isValid && isLegalDst && isLegalPath;
	}

	/**
	 * @return the coordinate of this piece on the chessboard.
	 * @see Coord
	 * @see ChessBoard
	 */
	public Coord getCoord() {
		return coord;
	}

	/**
	 * Checks whether this Piece can be at the given coordinate.
	 *
	 * @param destination the case to check.
	 * @return true if the destination is legal and false otherwise.
	 */
	public boolean isLegalDestination(@NotNull Coord destination) {
		try {
			Piece p = (Piece) board.getMovable(destination);
			if (p == null)
				return true;
			if (!p.color.equals(color))
				return true;

		} catch (IllegalPositionException ignored) {
		}

		return false;
	}

	/**
	 * Checks whether the given coordinate is a valid move for the piece.
	 *
	 * @param destination The destination of the piece.
	 * @return true if the move is valid or false otherwise.
	 */
	protected abstract boolean isValidMove(@NotNull Coord destination);

	protected abstract char toChar();

	/**
	 * Move the piece to the given position
	 *
	 * @param destination The destination of the piece.
	 * @throws IllegalMoveException     if the destination is an invalid move for this piece.
	 * @throws IllegalPositionException if the destination is out of the chessboard.
	 */
	public void move(Coord destination) throws IllegalMoveException, IllegalPositionException {
		if (!isLegalMove(destination)) {
			throw new IllegalMoveException(this, destination);
		}

		board.setOccupation(coord, null);
		board.setOccupation(destination, this);
		coord = destination;
	}


	/**
	 * @return the list of legal coordinate this Piece can move to.
	 */
	public abstract List<Coord> legalMove();

	@Override
	public String toString() {
		String color = "\33".concat(this.color.equals(Color.WHITE) ? "[30;107m" : "[97;40m");
		return color.concat(String.valueOf(toChar())).concat("\033[39;m");
	}

}
