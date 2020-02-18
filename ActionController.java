import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActionController implements ActionListener {
	
	JComboBox<Color> color1;
	JComboBox<Color> color2;
	Modell modell;
	
	public ActionController(Modell modell, JPanel panel) {
		this.modell = modell;
		
		JLabel player1 = new JLabel("Player 1");
		color1 = new JComboBox<Color>();
		color1.addItem(Color.YELLOW);
		color1.addItem(Color.BLUE);
		color1.addItem(Color.RED);
		color1.addItem(Color.BLACK);
		color1.addItem(Color.GREEN); // Can of course be more colors
		color1.addActionListener(this);
		panel.add(player1);
		panel.add(color1);

		JLabel player2 = new JLabel("Player 2");
		color2 = new JComboBox<Color>();
		color2.addItem(Color.YELLOW);
		color2.addItem(Color.BLUE);
		color2.addItem(Color.RED);
		color2.addItem(Color.BLACK);
		color2.addItem(Color.GREEN); // Can of course be more colors
		color2.addActionListener(this);
		panel.add(player2);
		panel.add(color2);

		JButton play = new JButton("Play");
		play.addActionListener(this);
		panel.add(play);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Play")) {
			modell.startGame();
		}
		
	}

}
