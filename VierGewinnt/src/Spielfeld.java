import java.awt.Color;
import java.util.*;

public class Spielfeld {
	/**
	 * Color of player 1.
	 */
	private Color color1= Color.yellow;
	/**
	 * Color player 2.
	 */
	private Color color2= Color.blue;
	
	/** The field. */
	private int[][] spielfeld;
	/** The set in which all the views are saved. */
	private Set<MainView> views = new HashSet<MainView>();
	/** Keeps track of, in what turn the game is. */
	private int turn;
	/** Keeps track if the game is started or not. */
	private boolean gameOn = false;
	
	/** Constructor for the field. */
	public Spielfeld() {
		this.spielfeld = new int[7][7];
	}

	/**
	 * @param row the row.
	 * @param col the column.
	 * @return the value in the wanted place.
	 */
	public int getValueAt(int row, int col) {
		return spielfeld[row][col];
	}

	/**
	 * @return the field.
	 */
	public int[][] getState() {
		return this.spielfeld;
	}

	/** @return the turn. */
	public int getTurn() {
		return this.turn;
	}

	/**
	 * @return the player of the turn.
	 */
	public int getPlayer() {
		this.CheckWin();
		if (this.turn % 2 == 1) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * @param state {@code true} if the game is started and {@code false} if not.
	 * @return the state of the game.
	 */
	public boolean getGameOn() {
		return this.gameOn;
	}

	/**
	 * @param row   the row.
	 * @param col   the column.
	 * @param value the new value.
	 */
	public void setValueAt(int row, int col, int value) {

		this.spielfeld[row][col] = value;

		this.updateViews();
	}

	/** Sets the turn of the game to a certain value. */
	public void setTurn(int value) {
		this.turn = value;
	}

	/**
	 * Checks if the game is won after a turn or not.
	 */
	public void CheckWin() {
		for (int i = 1; i < 7; i++) {
			int player = checkRow(i);
			if (player != 0) {
				System.exit(0);
			}
		}
		for (int i = 0; i < 7; i++) {
			int player = checkColumn(i);
			if (player != 0) {
				System.exit(0);
			}

		}
		if (diagonalRight() != 0) {
			
			System.exit(0);
		}
	}

	/**
	 * Checks a single row for a winning situation.
	 * 
	 * @param index the row to check.
	 * @return The player, that has won or {@code 0} if no one has won.
	 */
	public int checkRow(int index) {
		if (index < 1) {
			throw new IllegalArgumentException();
		}
		int counter = 0;
		int player = 0;
		for (int col = 0; col < 7; col++) {
			if (this.spielfeld[index][col] != 0 && counter == 0) {
				player = this.spielfeld[index][col];
				counter = 1;
			}
			if (col > 0 && this.spielfeld[index][col] == this.spielfeld[index][col - 1]) {
				counter++;
			} else if (col > 0 && this.spielfeld[index][col] != this.spielfeld[index][col - 1]) {
				counter = 0;
				player = 0;
			}
			if (counter == 4) {

				return player;
			}
		}
		return 0;
	}

	/**
	 * Checks a column for a winning situation.
	 * 
	 * @param index the column to check.
	 * @return The player to win or {@code 0} if no one has won.
	 */
	public int checkColumn(int index) {
		int counter = 0;
		int player = 0;
		for (int row = 1; row < 7; row++) {
			if (this.spielfeld[row][index] != 0 && counter == 0) {
				counter = 1;
				player = this.spielfeld[row][index];
			}
			if (row > 1 && this.spielfeld[row][index] == this.spielfeld[row - 1][index]) {
				counter++;
			} else if (row > 1 && this.spielfeld[row][index] != this.spielfeld[row - 1][index]) {
				counter = 0;
				player = 0;
			}
			if (counter == 4) {
				return player;
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
	public int diagonalLeft() {
		int player = 0;
		int counter = 0;

		for (int row = 6; row > 3; row--) {
			for (int col = 6; col > 2; col--) {
				if (this.spielfeld[row][col] != 0 && counter == 0) {
					counter = 1;
					player = this.spielfeld[row][col];
				}
				if (counter == 1) {
					if (player == this.spielfeld[row - 1][col - 1]) {
						counter++;
						if (player == this.spielfeld[row - 2][col - 2]) {
							counter++;
							if (player == this.spielfeld[row - 3][col - 3]) {
								counter++;

							}
						}
					}
				}
				else {
					counter =0;
				}
				if (counter ==4) {
					return player ;
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
	public int diagonalRight() {
		int player = 0;
		int counter = 0;

		for (int row = 6; row > 3; row--) {
			for (int col = 0; col <4; col++) {
				if (this.spielfeld[row][col] != 0 && counter == 0) {
					counter = 1;
					player = this.spielfeld[row][col];
				}
				if (counter == 1) {
					if (player == this.spielfeld[row - 1][col +1]) {
						counter++;
						if (player == this.spielfeld[row - 2][col + 2]) {
							counter++;
							if (player == this.spielfeld[row - 3][col +3]) {
								counter++;

							}
						}
					}
				}
				else {
					counter =0;
				}
				if (counter ==4) {
					return player ;
				}

			}
		}
		return 0;
	}

	/**
	 * Adds a new view to the set.
	 * 
	 * @param view the view.
	 */
	public void addView(MainView view) {
		views.add(view);
		view.update(this);
	}

	/** Updates all views. */
	public void updateViews() {
		for (MainView view : views) {
			view.update(this);
		}
	}

	/**
	 * Turns the stone on step to the right. Does nothing if it is in the most-right
	 * column.
	 */
	public void turnRight() {
		for (int col = 0; col < 6; col++) {
			if (this.spielfeld[0][col] != 0) {
				this.setValueAt(0, col, 0);
				this.setValueAt(0, col + 1, this.getPlayer());
				break;
			}
		}
	}

	/**
	 * Turns the stone one step to the right. Does nothing if it is in the most-left
	 * column.
	 */
	public void turnLeft() {
		for (int col = 1; col < 7; col++) {
			if (this.spielfeld[0][col] != 0) {
				this.setValueAt(0, col, 0);
				this.setValueAt(0, col - 1, this.getPlayer());
				break;
			}
		}
	}

	/**
	 * Determines which row the token falls in.
	 * 
	 * @param collumn the column in which the token is placed.
	 * @return the row in which the token falls, returns 0 if column is full.
	 */
	public int determineRow(int col) {
		for (int row = 1; row < 7; row++) {
			if (this.spielfeld[row][col] != 0) {
				return row - 1;
			}
		}
		return 6;
	}

	/**
	 * Enters the stone in the wanted place. Checks for the column in which the
	 * selected stone is and places it in the uppermost spot.
	 */
	public void enterStone() {
		int column = 0;
		for (int col = 0; col < 7; col++) {
			if (this.spielfeld[0][col] != 0) {
				column = col;
			}
		}
		this.setValueAt(0, column, 0);
		int row = this.determineRow(column);
		if (row != 0) {
			this.setValueAt(row, column, this.getPlayer());
		}
	}

	/**
	 * Starts a new turn of the game, ergo puts in the first row and first column a
	 * a new stone from a player.
	 */
	public void startTurn() {
		this.setValueAt(0, 0, this.getPlayer());
	}

	/**
	 * Starts a new game.
	 * 
	 * @param spielfeld the field that should be started.
	 */
	public void startGame() {
		this.spielfeld = new int[7][7];
		this.setTurn(1);
		this.setValueAt(0, 0, this.getPlayer());
		this.gameOn = true;
	}
	/**
	 * Getter for color1.
	 * @return the color of player1.
	 */
	public Color getColor1(){
		return this.color1;
	}
	/**
	 * Getter for color2.
	 * @return color of player2.
	 */
	public Color getColor2() {
		return this.color2;
	}
	/**
	 * Setter for player 1 color.
	 * @param color is the output of JCombobox which will be put in here.
	 */
	public void setColor1(String color) {
		this.color1 = Color.getColor(color);
	}
	/**
	 * Setter for player 2 color.
	 * @param color is the output of JCombobox which will be put in here.
	 */
	public void setColor2(String color) {
		this.color2 = Color.getColor(color);
	}
	
}
