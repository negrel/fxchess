package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayTUI {
	public static void main(String[] args) {
		Game g = new Game();
		g.smartPrint();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.printf("Player %s turn, enter your move using the ICCF notation:%n", g.getRound() % 2 == 0 ? Color.BLACK : Color.WHITE);
			try {
				String rawMove = br.readLine();
				g.play(rawMove);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (IllegalPositionException | IllegalMoveException | InvalidNotationException | OtherPlayerPieceException | SameColorException e) {
				System.out.println(e.toString());
				continue;
			}
			g.smartPrint();
		}
	}
}
