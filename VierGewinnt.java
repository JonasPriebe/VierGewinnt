import java.awt.Color;
import javax.swing.*;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class VierGewinnt extends GraphicsProgram {
	
	public void init() {
		Modell modell = new Modell();
		
		JPanel panel = getRegionPanel(SOUTH);
		
		ActionController controller1 = new ActionController(modell, panel);
	}

	public static void main(String[] args) {
		new VierGewinnt().start();
	}

}
