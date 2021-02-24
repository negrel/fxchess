package dev.negrel.fxchess.game;

/*
 * NotationParser is a generic interface for chess notation parser.
 */
public interface NotationParser {
	Move parseMove(String rawMove);
}
