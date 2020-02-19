package MeinVersuch;

import java.util.*;

public class Spielfeld {

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
	 * Checks a single row for a winning situation.
	 * 
	 * @param row the row.
	 * @return The player, which has won or {@code 0} if no one has won.
	 */
	public int CheckRow(int row) {
		int counter = 0;
		int player = 0;
		for (int col = 0; col < 7; col++) {
			if (counter == 4) {
				return player;
			}
			if (counter > 0) {
				if (this.spielfeld[row][col] == this.spielfeld[row][col - 1]) {
					counter++;
				} else {
					counter = 0;
					player = 0;
				}
			}
			if (this.spielfeld[row][col] == 1 || this.spielfeld[row][col] == 2) {
				player = this.spielfeld[row][col];
				counter = 1;
			}
		}
		return 0;
	}

	/**
	 * Checks a single column for a winning situation.
	 * 
	 * @param col the column.
	 * @return The player to win or {@code 0} if no one has won.
	 */
	public int CheckColumn(int col) {
		int counter = 0;
		int player = 0;
		for (int row = 1; row < 7; row++) {
			if (counter == 4) {
				return player;
			}
			if (counter > 0) {
				if (this.spielfeld[row][col] == this.spielfeld[row - 1][col]) {
					counter++;
				} else {
					counter = 0;
					player = 0;
				}
			}
			if (this.spielfeld[row][col] == 1 || this.spielfeld[row][col] == 2) {
				player = this.spielfeld[row][col];
				counter = 1;
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
	
}
