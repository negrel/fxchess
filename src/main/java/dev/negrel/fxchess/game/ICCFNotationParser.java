package dev.negrel.fxchess.game;

/*
 * ICCFNotationParser is a parser for the international notation admitted by the ICCF.
 */
public class ICCFNotationParser implements NotationParser {
	public Move parseMove(String rawMove) throws InvalidNotationException {
		if (rawMove.length() != 4) {
			throw new InvalidNotationException("ICCF", rawMove, "must have a length of 4");
		}
		int[] positions = new int[4];

		for (int i = 0; i < 4; i++) {
			try {
				int p = Integer.parseInt(rawMove.substring(i, i + 1));

				positions[i] = p;
			} catch (NumberFormatException e) {
				throw new InvalidNotationException("ICCF", rawMove, "must be composed of four integer");
			}
		}

		return new Move(new Coord(positions[0], positions[1]), new Coord(positions[2], positions[3]));
	}
}
