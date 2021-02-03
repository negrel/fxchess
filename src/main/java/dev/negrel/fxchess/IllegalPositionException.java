package dev.negrel.fxchess;

class IllegalPositionException extends Exception {
	IllegalPositionException(Coord c) {
		super(c.toString());
	}
}
