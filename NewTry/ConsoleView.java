package MeinVersuch;

public class ConsoleView implements MainView {

	@Override
	public void update(Spielfeld spielfeld) {
		int[][] status = spielfeld.getState();
		
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				System.out.print(status[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
