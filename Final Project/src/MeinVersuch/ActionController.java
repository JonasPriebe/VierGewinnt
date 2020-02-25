package MeinVersuch;

import java.awt.event.*;
import javax.swing.*;

/**
 * The action controller for the menu of the gane.
 */
public class ActionController implements ActionListener {

	/** The playground of the game. */
	private Spielfeld spielfeld;
	/** Array for the colors the players can be. */
	String[] colors = { "Yellow", "Blue", "Red", "Black", "Green" };
	/** ComboBox for the color of player 1. */
	JComboBox<String> color1 = new JComboBox<String>(colors);
	/** ComboBox for the color of player 2. */
	JComboBox<String> color2 = new JComboBox<String>(colors);

	/** 
	 * Constructor for the action controller.
	 * @param spielfeld the playground of the game.
	 * @param panel the panel where it should be displayed at.
	 */
	public ActionController(Spielfeld spielfeld, JPanel panel) {
		this.spielfeld = spielfeld;

		panel.add(new JLabel("Player 1"));
		color1.setSelectedIndex(0);
		color1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!spielfeld.getGameOn()) {
					int color = color1.getSelectedIndex();
					spielfeld.setColor1(color);
				} else {

				}
			}
		});
		panel.add(color1);
		
		panel.add(new JLabel("Player 2"));
		color2.setSelectedIndex(1);
		color2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!spielfeld.getGameOn()) {
					int color = color2.getSelectedIndex();
					spielfeld.setColor2(color);
				} else {

				}
			}
		});
		panel.add(color2);

		JButton play = new JButton("Play");
		play.addActionListener(this);
		panel.add(play);

		JButton restart = new JButton("Restart");
		restart.addActionListener(this);
		panel.add(restart);

		JButton quit = new JButton("Quit");
		quit.addActionListener(this);
		panel.add(quit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Play")) {
			if (spielfeld.getGameOn()) {
				// Can't use it if the game runs.
			} else {
				spielfeld.startGame();
			}
		} else if (e.getActionCommand().equals("Restart")) {
			if (!spielfeld.getGameOn()) {
				// Can't use it if the game runs not.
			} else {
				spielfeld.startGame();
			}
		} else if (e.getActionCommand().equals("Quit")) {
			
			System.exit(0);
		}

	}

}