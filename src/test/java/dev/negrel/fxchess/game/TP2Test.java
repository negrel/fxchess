package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TP2Test {
	@Test
	void ex1() throws IllegalMoveException, IllegalPositionException, InvalidNotationException {
		Game g = new Game(new ICCFNotationParser());
		g.smartPrint();
		g.play("1213");
		g.smartPrint();
	}

	@Test
	void ex2() throws IllegalMoveException, IllegalPositionException, InvalidNotationException, IOException {
		Game g = new Game(new ICCFNotationParser());
		g.play("1213");
		g.play("2223");

		g.writeToFile("game_ex2");
	}
}
