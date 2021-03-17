package dev.negrel.fxchess.engine;

public class InvalidNotationException extends Exception {
	public InvalidNotationException(String notationName, String rawNotation, String errMsg) {
		super(String.format("\"%s\" is an invalid move in the \"%s\" notation: %s", rawNotation, notationName, errMsg));
	}
}
