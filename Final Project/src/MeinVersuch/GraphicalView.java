package MeinVersuch;

import java.awt.Color;
import javax.swing.*;

import MeinVersuch.FancyStones.FancyStone;
import acm.graphics.*;

public class GraphicalView extends GCompound implements MainView {

	/** The background of the game. */
	private GCompound background;
	/** The space on the top of the canvas. */
	private static final double SPACE_UD = 50;
	/** The space on the left of the canvas. */
	private static final double SPACE_LR = 120;
	/** The size of the stones. */
	private static final double SIZE = 55;
	/** The height of the canvas. (tested) */
	private static final double HEIGHT = 469.0;
	/** The width of the canvas. */
	private static final double WIDTH = 754.0;
	/** The width of the playground. */
	private static final double PLAY_WIDTH = WIDTH - SPACE_LR;
	/** The height of the playground. */
	private static final double PLAY_HEIGHT = HEIGHT - SPACE_UD;
	/** Distance between the stones for the rows. */
	private static double distanceRow;
	/** Distance between the stones for the columns. */
	private static double distanceCol;
	/**
	 * Counter for the usage of update method. Is used to determine when show the
	 * background.
	 */
	private static int counter = 0;

	/**
	 * Creates a new instance of this view.
	 * 
	 * @param spielfeld the playground for the information.
	 * @param panel     the panel where is should be shown. (Of course the center.)
	 */
	public GraphicalView(Spielfeld spielfeld, JPanel panel) {
		this.distanceRow = ((PLAY_HEIGHT) / 7 - SIZE) / 2;
		this.distanceCol = ((PLAY_WIDTH) / 7 - SIZE) / 2;
		background = new GCompound();
		for (int i = 0; i < 8; i++) {
			GLine col = new GLine(SPACE_LR / 2 + i * (PLAY_WIDTH) / 7 - distanceCol, 66.5,
					SPACE_LR / 2 + i * (PLAY_WIDTH) / 7 - distanceCol, PLAY_HEIGHT + 7.5);
			col.setColor(Color.LIGHT_GRAY);
			background.add(col);
		}
		for (int i = 1; i < 8; i++) {
			GLine row = new GLine(43, 10 - distanceRow + i * (PLAY_HEIGHT) / 7, PLAY_WIDTH + 41.5,
					10 - distanceRow + i * (PLAY_HEIGHT) / 7);
			if (i == 1) {
				row.setColor(Color.black);
			} else {
				row.setColor(Color.LIGHT_GRAY);
			}
			background.add(row);
		}
	}

	@Override
	public void update(Spielfeld spielfeld) {
		
		int winner = spielfeld.checkWin();
		removeAll();
		switch (winner) {
		case 1: // "Animation" when the player 1 has won.
			GLabel winner1 = new GLabel("Player 1 has won!");
			winner1.setColor(spielfeld.getColor1());
			winner1.setFont("Arial-bold-80");
			add(winner1, 0, 200);
			break;
		case 2: // "Animation" when the player 2 has won.
			GLabel winner2 = new GLabel("Player 2 has won!");
			winner2.setColor(spielfeld.getColor2());
			winner2.setFont("Arial-bold-80");
			add(winner2, 0, 200);
			break;
		case 3: // "Animation" if it is a draw.
			GLabel draw = new GLabel("It's a draw.");
			draw.setColor(Color.black);
			draw.setFont("Arial-bold-80");
			add(draw, 0 , 200);
			break;
		default: // If no one has won.
			if (counter > 0) {
				add(background);
			}

			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					int player = spielfeld.getValueAt(row, col);
					GCompound stone = new FancyStone();
					switch (player) { // Switch for the colors of the stones.
					case 0:
						stone.setColor(Color.white);
						break;
					case 1:
						stone.setColor(spielfeld.getColor1());
						break;
					case 2:
						stone.setColor(spielfeld.getColor2());
						break;
					}
					add(stone, SPACE_LR / 2 + col * (PLAY_WIDTH) / 7, 10 + row * (PLAY_HEIGHT) / 7);
				}
			}
			counter++;
		}
	}

	/** Returns the size of a single stone on the playground. */
	public static double getSIZE() {
		return GraphicalView.SIZE;
	}

}