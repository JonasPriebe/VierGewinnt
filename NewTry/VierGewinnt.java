package MeinVersuch;

import javax.swing.*;
import acm.program.GraphicsProgram;

public class VierGewinnt extends GraphicsProgram {
	
	
	public void init() {
		Spielfeld spielfeld = new Spielfeld();
		
		ConsoleView cView = new ConsoleView();
		spielfeld.addView(cView);
		
		KeyController cControl = new KeyController(spielfeld);
		getGCanvas().addKeyListener(cControl);
		
		JPanel panel = getRegionPanel(SOUTH);
		ActionController aControl = new ActionController(spielfeld, panel);
		addActionListeners();
	}

	public static void main(String[] args) {
		new VierGewinnt().start();
	}

}
