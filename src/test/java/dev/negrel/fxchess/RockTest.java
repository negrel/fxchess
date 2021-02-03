package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RockTest {
	Rock rock;

	public RockTest() throws IllegalPositionException {
		rock = new Rock(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	void isValidMove() {
		assertTrue(rock.isValidMove(new Coord(5, 0)));
		assertTrue(rock.isValidMove(new Coord(-5, 0)));
		assertTrue(rock.isValidMove(new Coord(0, 5)));
		assertTrue(rock.isValidMove(new Coord(0, -5)));


		assertFalse(rock.isValidMove(new Coord(1, 5)));
		assertFalse(rock.isValidMove(new Coord(-1, -1)));
		assertFalse(rock.isValidMove(new Coord(-1, 5)));
		assertFalse(rock.isValidMove(new Coord(5, 1)));
		assertFalse(rock.isValidMove(new Coord(-5, -5)));
		assertFalse(rock.isValidMove(new Coord(-5, 1)));
		assertFalse(rock.isValidMove(new Coord(0, 0)));
	}
}
