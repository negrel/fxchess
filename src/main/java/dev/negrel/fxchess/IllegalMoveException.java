package dev.negrel.fxchess;

class IllegalMoveException extends Exception {
	public IllegalMoveException(Piece p, Coord c) {
		super(String.format("Piece %s at %s can't move at %s", p, p.getCoord().toString(), c.toString()));
	}
}
