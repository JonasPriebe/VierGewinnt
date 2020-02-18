import acm.program.GraphicsProgram;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class VierGewinnt extends GraphicsProgram {
	public   void setPlayer1Col(String color) {
		color.toUpperCase();
		Player1col= Color.getColor(color); 
	}
	JComboBox<String> color1; // Methods for getting the state in the model or controller
	JComboBox<String> color2;
	/**
	 * Color of Player1 tokens.
	 */
	public Color Player1col=null;
	/**
	 * Color of Player2 tokens.
	 */
	public Color Player2col=null;
	/**
	 * Representation of the playing field.
	 */
	public int[][] Spielfeld = new int[7][7];
	// The menu
	public void init() {
		JLabel player1 = new JLabel("Player 1");
		color1 = new JComboBox<String>();
		color1.addItem("Yellow");
		color1.addItem("Blue");
		color1.addItem("Red");
		color1.addItem("Black");
		color1.addItem("Green"); // Can of course be more colors
		add(player1, SOUTH);
		add(color1, SOUTH);
		addActionListeners();
		JLabel player2 = new JLabel("Player 2");
		color2 = new JComboBox<String>();
		color2.addItem("Yellow");
		color2.addItem("Blue");
		color2.addItem("Red");
		color2.addItem("Black");
		color2.addItem("Green"); // Can of course be more colors
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
	
		/**
		 * Used to count the turns to determine the player at turn later on.
		 */
		int turnCounter = 1;
		/**
		 * Player1 gametoken.
		 */
		final int token1 = 1;
		/**
		 * Player2 gametoken.
		 */
		final int token2 = 2;
		
		
	
		
		public Modell() {

		}
		public void setToken(int collumn) {
			if (checkValid(collumn)) {
			if (collumnAvailable(collumn)) {
				Spielfeld[collumn][determineRow(collumn)]=turn();
				turnCounter++;
			}
			}
		}
		/**
		 * Method to start a new game which a
		 */
		public void startGame() {
			for (int n = 0; n < 6; n++) {
				for (int m = 0; m < 7; m++) {
					Spielfeld[n][m] = 0;
				}
			}
			turnCounter=1;
		}

		/**
		 * Method to check which players turn it is .
		 * 
		 * @return either 1 or 2 depending which players turn it is.
		 */
		public int turn() {
			if (turnCounter % 2 == 0) {
				return 2;
			} else {
				return 1;
			}

		}
		/**
		 * Method to set color of the tokens for player1.
		 * @param input color chosen in controller.
		 */
		public Color getColor1() {
			Color col1 = Player1col;
			return col1;
		}
		/**
		 * Method to set color of the tokens for player2.
		 * @param input color chosen in controller.
		 */
		public Color getColor2() {
			Color col2 = Player2col;
			return col2;
		}
		/**
		 * Checks if a row is not full, ergo if the player is able to put a token in it.
		 * 
		 * @param collumn where the player wants to put a token.
		 * @return if the row is available.
		 */
		public boolean collumnAvailable(int collumn) {
			if (Spielfeld[5][collumn] == 0) {
				return true;
			} else {
				return false;
			}

		}

		/**
		 * Method to get the value in a certain field.
		 * 
		 * @param collumn of the field.
		 * @param row     of the field.
		 * @return the value which is either 0,1,2.
		 */
		public int getValue(int row, int collumn) {
			int value = 0;
			value = Spielfeld[row][collumn];
			return value;
		}

		/**
		 * Determines which row the token falls in.
		 * 
		 * @param collumn the collumn in which the token is placed.
		 * @return the row in which the token falls, returns -1 if collumn is full.
		 */
		public int determineRow(int collumn) {
			for (int n = 0; n <7; n++) {
				if (Spielfeld[n][collumn] == 0) {
					return n;
				}
			}
			return -1;

		}

		/**
		 * Method to check if a move is valid.
		 * 
		 * @param collumn of the wanted field.
		 * @param row     of the wanted field.
		 * @return if move is valid or not.
		 */
		public boolean checkValid(int collumn) {
			if (0 <= collumn && collumn < 7 == true) {
				if (collumnAvailable(collumn)) {

					return true;

				}
			}
			return false;

		}

		/**
		 * Method to check the rows for any winning combinations.
		 * 
		 * @return if there is a winning combination and if there is return the winner.
		 */
		public int rowCheck() {
			int checkType = 0;
			int currentType = 0;
			int count = 0;
			for (int n = 6; n >= 0; n--) {
				count = 0;
				for (int m = 0; m < 7; m++) {
					if (getValue(n, m) != 0) {
						currentType = getValue(n, m);
						if (count == 0) {
							checkType = currentType;
							count++;
						}
						if (currentType == checkType) {
							count++;
						} else {
							count = 1;
							checkType = currentType;
						}
						if (count == 4) {
							return checkType;
						}

					}

				}
			}
			return 0;

		}

		/**
		 * Method to check the collumns for any winning combinations.
		 * 
		 * @return if there is a winning combination and if there is return the winner.
		 */

		public int collumnCheck() {
			int checkType = 0;
			int currentType = 0;
			int count = 0;
			for (int n = 0; n < 7; n++) {
				count = 0;
				for (int m = 6; m >= 0; m--) {
					if (getValue(n, m) != 0) {
						currentType = getValue(n, m);
						if (count == 0) {
							checkType = currentType;
							count++;
						}
						if (currentType == checkType) {
							count++;
						} else {
							count = 1;
							checkType = currentType;
						}
						if (count == 4) {
							return checkType;
						}

					}

				}
			}
			return 0;

		}

		/**
		 * Method to check a right diagonal winning combination.
		 * 
		 * @return if there is a winning combination and if there is one which player
		 *         wins.
		 */
		public int diagonalRight() {
			int checkType = 0;
			int count = 0;

			for (int n = 0; n < 4; n++) {
				for (int m = 0; m < 3; m++) {
					count = 0;
					if (Spielfeld[m][n] != 0) {

						checkType = Spielfeld[m][n];

						for (int k = 1; k < 4; k++) {
							if (Spielfeld[m + k][n + k] == checkType) {
								count++;
							}
							if (count == 3) {
								return checkType;
							}
						}

					}
				}

			}
			return 0;
		}

		/**
		 * Method to check a left diagonal winning combination.
		 * 
		 * @return if there is a winning combination and if there is one which player
		 *         wins.
		 */
		public int diagonalLeft() {
			int checkType = 0;
			int count = 0;
			for (int n = 6; n > 3; n--) {
				for (int m = 0; m < 3; m++) {
					count = 0;
					if (Spielfeld[m][n] != 0) {
						checkType = Spielfeld[m][n];

						for (int k = 1; k < 4; k++) {
							if (Spielfeld[m + k][n - k] == checkType) {
								count++;
							}
							if (count == 3) {
								return checkType;
							}
						}

					}

				}
			}
			return 0;
		}
		/**
		 * Method that checks for all possible winning combinations.
		 * @return the winner if there is one.
		 */
		public int checkWinningCombination() {
			if (rowCheck() != 0) {
				return rowCheck();
			}
			if (collumnCheck() != 0) {
				return collumnCheck();
			}
			if (diagonalRight() != 0) {
				return diagonalRight();
			}
			if (diagonalLeft() != 0) {
				return diagonalLeft();
			}
			return 0;

		}

	}


	class Controller {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("color1")){
				setPlayer1Col(color1.getSelectedItem()+"");
				
			
			}
		}

	}
	class View extends GraphicsProgram {
		Modell n = new Modell();
		/** The background rectangle. */
		private GRect background;
		/** The stones. */
		private GOval stone;
		/** The array in which all the stones are saved. */
		private GOval stones[][] = new GOval[6][7];

		/**
		 * Constructor for the view. Creates the background and an "empty" grid.
		 */
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
					if(n.turn()==1) {
					stone.setColor(Player1col); }     // get the information from the model
					else if (n.turn()==2) {
						stone.setColor(Player2col);
					}
					// about the color
					stones[5 - i][j] = stone;
					add(stones[5 - i][j]);
				}
			}
		}
	}

}