package dev.negrel.fxchess.engine;

import org.jetbrains.annotations.NotNull;

public class OtherPlayerPieceException extends Exception {
	OtherPlayerPieceException(@NotNull Color player, @NotNull Piece p) {
		super(
			String.format("Player %s is trying to move the piece %s owned by %s.",
				player.name(), p.toString(), player.equals(Color.BLACK) ? Color.WHITE : Color.BLACK)
		);
	}
}
