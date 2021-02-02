package dev.negrel.fxchess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KingTest {
	King king;

	public KingTest() {
		king = new King(new ChessBoard(), new Coord(), Color.BLACK);
	}

	@Test
	public void move() {
		Coord c = new Coord(1, 1);
		king.move(c);

		assertEquals(king.getCoord(), c);
	}
}
