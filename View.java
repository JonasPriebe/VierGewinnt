import java.awt.Color;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.*;

public class View extends GraphicsProgram {

	private GRect background;
	private GOval stone;
	private GOval stones[][] = new GOval[6][7];

	public View() {
		background = new GRect(getWidth(), getHeight());
		background.setFilled(true);
		background.setColor(Color.WHITE);
		add(background);

		double size = getHeight() / 8 - 5;

		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				double x = (j + 1) * getWidth() / 9;
				double y = ((5 - i) + 1) * getHeight() / 8;
				stone = new GOval(x, y, size, size);
				stone.setFilled(true);
				stone.setColor(Color.BLACK); // get the information from the model
												// about the color
				stones[5 - i][j] = stone;
				add(stones[5 - i][j]);
			}
		}
	}

}
