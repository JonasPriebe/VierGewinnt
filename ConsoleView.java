
public class ConsoleView {

	Modell modell;
	int[][] game = new int[7][7];
	
	public ConsoleView(Modell modell) {
		this.modell = modell;
		for(int i = 6; i >= 0; i--) {
			for(int j = 0; j < 7; j++) {
				System.out.print(game[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void update() {
		game = modell.getState();
		for(int i = 6; i >= 0; i--) {
			for(int j = 0; j < 7; j++) {
				System.out.print(game[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
