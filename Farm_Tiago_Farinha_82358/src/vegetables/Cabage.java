package vegetables;

import pt.iul.ista.poo.utils.Point2D;

public class Cabage extends Vegetable {

	private static final int mature = 10;
	private static final int rotten = 30;

	private static final int pontos = 3;

	public Cabage(Point2D p) {
		super(p);

	}

	@Override
	public void addCiclo() {

		if (getCuidado())
			sumCicles(3);
		else
			sumCicles(1);
		updateStatus();

	}

	@Override
	public int getPontos() {
		return pontos;
	}

	@Override
	public int getRotten() {
		return rotten;
	}

	@Override
	public int getMature() {
		return mature;
	}

}
