package MeinVersuch;

import java.awt.Color;
import javax.swing.*;
import acm.graphics.*;

public class GraphicalView extends GCompound implements MainView {

	private GRect background;
	private Spielfeld spielfeld;
	
	public GraphicalView(Spielfeld spielfeld, JPanel panel) {
		this.spielfeld = spielfeld;
		background = new GRect(0, panel.getHeight() / 7 - 20 + 5, panel.getWidth(), 3);
		background.setColor(Color.blue);
		background.setFilled(true);
		add(background);
	}

	@Override
	public void update(Spielfeld spielfeld) {
		removeAll();
		add(background);
		
		int[][] data = spielfeld.getState();
		
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				GOval stone = new GOval(55, 55);
				stone.setFilled(true);
				stone.setColor(Color.black);
				add(stone, 50 + col * 90, row * 60);
			}
		}
	}	
}
