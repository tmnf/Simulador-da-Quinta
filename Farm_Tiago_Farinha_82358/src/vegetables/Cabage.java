package vegetables;

import pt.iul.ista.poo.utils.Point2D;

public class Cabage extends Vegetable {
	
	private static final int mature = 10;
	private static final int rotten = 30;

	public Cabage(Point2D p) {
		super(p);

	}

	@Override
	public void getStatus() {
		if (getCiclos() > mature && getCiclos() < rotten)
			setEstado("cabage");
		else if (getCiclos() >= rotten)
			setEstado("bad_cabage");
		else
			setEstado("small_cabage");
	}

	@Override
	public void addCiclo() {

		if (getCuidado())
			setCiclo(2);
		else
			setCiclo(1);
		getStatus();
		

	}

}
