package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

public class TP2Test {
	@Test
	void ex1() throws IllegalMoveException, IllegalPositionException, InvalidNotationException {
		Game g = new Game(new ICCFNotationParser());
		g.play("1234");
	}
}
