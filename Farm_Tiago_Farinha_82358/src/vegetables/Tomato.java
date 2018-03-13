package vegetables;

import pt.iul.ista.poo.utils.Point2D;

public class Tomato extends Vegetable {

	// private static final int stopsMature = 10;
	private static final int mature = 15;
	private static final int rotten = 25;

	public Tomato(Point2D p) {
		super(p);
	}

	@Override
	public void getStatus() {
		if (getCiclos() >= mature && getCiclos() < rotten)
			setEstado("tomato");
		else if (getCiclos() >= rotten)
			setEstado("bad_tomato");
		else
			setEstado("small_tomato");
	}
}
