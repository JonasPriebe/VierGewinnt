package MeinVersuch;

import java.io.IOException;

public class LighthouseView implements MainView {

	private Spielfeld spielfeld;
	LighthouseDisplay display = null;

	public LighthouseView(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;

		// Try connecting to the display
		try {
			display = LighthouseDisplay.getDisplay();
			display.setToken("API-TOK_0Wcs-/zvu-VA6k-+urg-j37T");
			display.setUsername("NickSchmahl");
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void update(Spielfeld spielfeld) {
		// Send data to the display
		try {
			// This array contains for every window (14 rows, 28 columns) three
			// bytes that define the red, green, and blue component of the color
			// to be shown in that window. See documentation of LighthouseDisplay's
			// send(...) method.
			byte[] data = new byte[14 * 28 * 3];

			for(int row = 0; row < 7; row++) {
				for(int col = 0; col < 7; col++) {
					int player = spielfeld.getValueAt(row, col);
					switch(player) {
					case 0: // white
						for(int i = 0; i < 12; i++) {
							data[84 * (2 * row + 1) + 12 * col + i] = (byte) 255;
							data[84 * 2 * row + 12 * col + i] = (byte) 255;
						}
						break;
					case 1:  // yellow
						for(int i = 0; i < 4; i++) {
							// red color
							data[84 * 2 * row + 12 * col + 3 * i] = (byte) 255;
							data[84 * (2 * row + 1) + 12 * col + 3 * i] = (byte) 255;
							// green color
							data[84 * 2 * row + 12 * col + 1 + 3 * i] = (byte) 255;
							data[84 * (2 * row + 1) + 12 * col + 1 + 3 * i] = (byte) 255;
						}
						break;
					case 2: // blue
						for(int i = 0; i < 4; i++) {
							data[84 * 2 * row + 12 * col + 2 + 3 * i] = (byte) 255;
							data[84 * (2 * row + 1) + 12 * col + 2 + 3 * i] = (byte) 255;
						}
						break;
					}	
				}
			}

			display.sendImage(data);
		} catch (IOException e1) {
			System.out.println("Connection failed: " + e1.getMessage());
			e1.printStackTrace();
		}
	}

}