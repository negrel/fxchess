package dev.negrel.fxchess.game;

import org.jetbrains.annotations.NotNull;

public class SameColorException extends Exception {
	SameColorException(@NotNull Piece p, @NotNull Coord to) {
		super(
			String.format("Can't move the piece %s from %s to %s because the destination contain a piece of the same color.",
				p.toString(), p.getCoord(), to)
		);
	}
}
