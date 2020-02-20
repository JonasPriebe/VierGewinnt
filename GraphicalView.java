
import java.awt.Color;

import acm.graphics.*;

public class GraphicalView extends GCompound {

	private GRect background;
	private GOval stone;
	private GOval stones[][] = new GOval[7][7];
	private Modell modell;

	public GraphicalView() {
		background = new GRect(getWidth(), getHeight());
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}

	public void update(Modell modell) {
		this.modell = modell;

		double size = getHeight() / 8 - 5;

		for (int i = 6; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				double x = (j + 1) * getWidth() / 9;
				double y = ((6 - i) + 1) * getHeight() / 8;
				stone = new GOval(x, y, size, size);
				stone.setFilled(true);
				stone.setColor(Color.BLACK); // get the information from the model
												// about the color
				stones[6 - i][j] = stone;
				add(stones[6 - i][j]);
			}
		}
	}

}
