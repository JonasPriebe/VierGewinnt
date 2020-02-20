import java.awt.event.*;
import javax.swing.*;

public class ActionController implements ActionListener {

	private Spielfeld spielfeld;
	String[] colors = { "Yellow", "Blue", "Red", "Black", "Green" };
	JComboBox<String> color1 = new JComboBox<String>(colors);
	JComboBox<String> color2 = new JComboBox<String>(colors);

	public ActionController(Spielfeld spielfeld, JPanel panel) {
		this.spielfeld = spielfeld;

		panel.add(new JLabel("Player 1"));
		color1.addActionListener(this);
		panel.add(color1);
		panel.add(new JLabel("Player 2"));
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
		if (e.getActionCommand().equals("color1")) {
			String color = color1.getSelectedItem().toString();
			color1.setSelectedItem(color1.getSelectedItem());
			spielfeld.setColor1(color);
			
		}
		if (e.getActionCommand().equals("color2")) {
			String color = color2.getSelectedItem().toString();
			spielfeld.setColor2(color);
			
		}
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
			System.exit(0);
			
		}

	}

}