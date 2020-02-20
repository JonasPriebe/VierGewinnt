import java.awt.event.*;

/** This class handles all the KeyInputs that are possible in the game. */
public class KeyController implements KeyListener {
	/** The model that is the base of this controller. */
	private Spielfeld spielfeld;

	/** Creates a new instance of this class. */
	public KeyController(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT :
			spielfeld.turnRight();
			break;
		case KeyEvent.VK_LEFT:
			spielfeld.turnLeft();
			
			
			
			break;
		case KeyEvent.VK_ENTER:
			
			spielfeld.enterStone();
			spielfeld.setTurn(spielfeld.getTurn() + 1);
			spielfeld.startTurn();
			
			break;
		}
	}

	@Override	// Isn't needed.
	public void keyReleased(KeyEvent e) {
	}

	@Override   // Isn't needed.
	public void keyTyped(KeyEvent e) {
	}

}
