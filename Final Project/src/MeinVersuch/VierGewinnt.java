package MeinVersuch;

import javax.swing.*;
import acm.program.GraphicsProgram;

public class VierGewinnt extends GraphicsProgram {
	
	public void init() {
		// The playground for the game.
		Spielfeld spielfeld = new Spielfeld();
		
		// The display in the console.
		ConsoleView cView = new ConsoleView();
		spielfeld.addView(cView);
		
		// The panel where the graphics should be drawn at.
		JPanel center = getRegionPanel(CENTER);
		GraphicalView gView = new GraphicalView(spielfeld, center);
		add(gView);
		spielfeld.addView(gView);
		
		LighthouseView lView = new LighthouseView(spielfeld);
		spielfeld.addView(lView);
		
		// The controller for the keys.
		KeyController cControl = new KeyController(spielfeld);
		getGCanvas().addKeyListener(cControl);
		
		// The panel where the action should be shown at.
		JPanel panel = getRegionPanel(SOUTH);
		ActionController aControl = new ActionController(spielfeld, panel);
		addActionListeners();
	}

	public static void main(String[] args) {
		new VierGewinnt().start();
	}

}
