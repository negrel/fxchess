package dev.negrel.fxchess.game;

/*
 * ICCFNotationParser is a parser for the international notation admitted by the ICCF.
 */
public class ICCFNotationParser implements NotationParser {
	public Move parseMove(String rawMove) {
		String fromX = rawMove.substring(0, 1);
		String fromY = rawMove.substring(1, 2);

		String toX = rawMove.substring(2, 3);
		String toY = rawMove.substring(3, 4);

		return new Move(Coord.fromString(fromX, fromY), Coord.fromString(toX, toY));
	}
}
