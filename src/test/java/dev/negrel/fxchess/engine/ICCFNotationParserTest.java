package dev.negrel.fxchess.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ICCFNotationParserTest {
	NotationParser parser;

	@BeforeEach
	void classInit() {
		this.parser = new ICCFNotationParser();
	}

	@Test
	void parseValidMove() throws InvalidNotationException {
		Move move = this.parser.parseMove("1234");
		assertTrue(move.equals(new Move(new Coord(1, 2), new Coord(3, 4))));
	}

	@Test
	void parseTooShortMove() {
		assertThrows(InvalidNotationException.class, () -> this.parser.parseMove("12"));
	}

	@Test
	void parseTooLongMove() {
		assertThrows(InvalidNotationException.class, () -> this.parser.parseMove("1234567"));
	}

	@Test
	void parseInvalidMove() {
		assertThrows(InvalidNotationException.class, () -> this.parser.parseMove("abcd"));
	}
}
