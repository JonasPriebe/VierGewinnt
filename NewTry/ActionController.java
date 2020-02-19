package MeinVersuch;

import java.awt.event.*;
import javax.swing.*;

public class ActionController implements ActionListener {

	private Spielfeld spielfeld;

	public ActionController(Spielfeld spielfeld, JPanel panel) {
		this.spielfeld = spielfeld;

		panel.add(new JLabel("Player 1"));
		String[] colors = { "Yellow", "Blue", "Red", "Black", "Green" };
		JComboBox color1 = new JComboBox(colors);
		color1.addActionListener(this);
		panel.add(color1);

		panel.add(new JLabel("Player 2"));
		JComboBox color2 = new JComboBox(colors);
		color2.addActionListener(this);
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

			} else {
				spielfeld.startGame();
			}
		} else if (e.getActionCommand().equals("Restart")) {
			if (!spielfeld.getGameOn()) {

			} else {
				spielfeld.startGame();
			}
		} else if (e.getActionCommand().equals("Quit")) {
			if (!spielfeld.getGameOn()) {

			} else {
				System.exit(0);
			}
		}

	}

}
