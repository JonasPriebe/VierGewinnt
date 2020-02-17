import java.awt.Color;

import javax.swing.*;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class VierGewinnt extends GraphicsProgram {
	JComboBox<String> color1;       // Methods for getting the state in the model or controller
	JComboBox<String> color2;		
	
	// The menu
	public void init() {
		JLabel player1 = new JLabel("Player 1");
		color1 = new JComboBox<String>();
		color1.addItem("Yellow");
		color1.addItem("Blue");
		color1.addItem("Red");
		color1.addItem("Black");
		color1.addItem("Green");  // Can of course be more colors
		add(player1, SOUTH);
		add(color1, SOUTH);
		
		JLabel player2 = new JLabel("Player 1");
		color2 = new JComboBox<String>();
		color2.addItem("Yellow");
		color2.addItem("Blue");
		color2.addItem("Red");
		color2.addItem("Black");
		color2.addItem("Green");  // Can of course be more colors
		add(player2, SOUTH);
		add(color2, SOUTH);
		
		JButton play = new JButton("Play");
		add(play, SOUTH);
		addActionListeners();
	}
	

	public static void main(String[] args) {
		new VierGewinnt().start();
	}

	class Modell {
		
	}
	
	/** The view for our game. */
	class view extends GraphicsProgram{
		/** The background rectangle. */
		private GRect background;
		/** The stones. */
		private GOval stone;
		/** The array in which all the stones are saved. */
		private GOval stones[][] = new GOval[6][7];
		
		/**
		 * Constructor for the view. Creates the background and an "empty" grid.
		 */
		public view() {
			background = new GRect(getWidth(), getHeight());
			background.setFilled(true);
			background.setColor(Color.WHITE);
			add(background);
			
			double size = getHeight() / 8 - 5;
			
			for(int i = 5; i >= 0; i--) {
				for(int j = 0; j < 7; j++) {
					double x = (j + 1) * getWidth() / 9;
					double y = ((5 - i) + 1) * getHeight() / 8;
					stone = new GOval(x, y, size, size);
					stone.setFilled(true);
					stone.setColor(Color.BLACK);      // get the information from the model
													  // about the color
					stones[5 - i][j] = stone;
					add(stones[5 - i][j]);
				}
			}
		}
	}
	
}
