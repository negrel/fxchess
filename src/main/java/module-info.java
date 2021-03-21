module dev.negrel.fxchess {
	requires javafx.controls;
	requires org.jetbrains.annotations;

	opens dev.negrel.fxchess.engine.piece;
	opens dev.negrel.fxchess.engine;

	exports dev.negrel.fxchess.gui;
	exports dev.negrel.fxchess.engine.piece;
	exports dev.negrel.fxchess.engine;
}
