package MeinVersuch.FancyStones;

import java.awt.*;

import MeinVersuch.GraphicalView;
import acm.graphics.*;

public class FancyStone extends GCompound {

	GOval background; 
	GOval littleCircle;
	GOval smallestCircle;
	
	public FancyStone() {
		background = new GOval(GraphicalView.getSIZE(), GraphicalView.getSIZE());
		background.setFilled(true);
		this.add(background);
		
		littleCircle = new GOval(GraphicalView.getSIZE() - 20, GraphicalView.getSIZE() - 20);
		littleCircle.setFilled(true);
		littleCircle.setColor(Color.white);
		this.add(littleCircle, 10, 10);
		
		smallestCircle = new GOval(GraphicalView.getSIZE() - 30, GraphicalView.getSIZE() - 30);
		smallestCircle.setFilled(true);
		this.add(smallestCircle, 15, 15);
	}
	
	@Override
	public void setColor(Color color) {
		background.setColor(color);
		smallestCircle.setColor(color);
	}
	
}
