package MeinVersuch;

import java.awt.Color;
import javax.swing.*;
import acm.graphics.*;

public class GraphicalView extends GCompound implements MainView {

	private GCompound background;
	private static final double SPACE_UP = 10;
	private static final double SPACE_LEFT = 55;
	private static final double SIZE = 55;
	private static double distanceRow;
	private static double distanceCol;
	private static int counter = 0;

	public GraphicalView(Spielfeld spielfeld, JPanel panel) {
		this.distanceRow = (panel.getHeight() - SPACE_UP) / 8;
		this.distanceCol = (panel.getWidth() - SPACE_LEFT) / 7;
		double LINE_COL_DISTANCE = (distanceCol - SIZE) / 2 + 10;
		double LINE_ROW_DISTANCE = (distanceRow - SIZE) / 2 + 5;
		background = new GCompound();
		for (int i = 0; i < 8; i++) {
			GLine col = new GLine(LINE_COL_DISTANCE + i * distanceCol, distanceRow + LINE_ROW_DISTANCE,
					LINE_COL_DISTANCE + i * distanceCol, LINE_ROW_DISTANCE + 7 * distanceRow);
			col.setColor(Color.LIGHT_GRAY);
			background.add(col);
		}
		for (int i = 1; i < 8; i++) {
			GLine row = new GLine(LINE_COL_DISTANCE, LINE_ROW_DISTANCE + i * distanceRow,
					LINE_COL_DISTANCE + 7 * distanceCol, LINE_ROW_DISTANCE + i * distanceRow);
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
		case 1:
			GLabel winner1 = new GLabel("Player 1 has won!");
			winner1.setColor(spielfeld.getColor1());
			winner1.setFont("Arial-bold-80");
			add(winner1, 0, 200);
			break;
		case 2:
			GLabel winner2 = new GLabel("Player 2 has won!");
			winner2.setColor(spielfeld.getColor2());
			winner2.setFont("Arial-bold-80");
			add(winner2, 0, 200);
			break;
		default:

			if (counter > 0) {
				add(background);
			}

			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					GOval stone = new GOval(55, 55);
					stone.setFilled(true);
					int player = spielfeld.getValueAt(row, col);
					switch (player) {
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
					add(stone, SPACE_LEFT + col * distanceCol, SPACE_UP + row * distanceRow);
				}
			}
			counter++;
		}
	}
}
