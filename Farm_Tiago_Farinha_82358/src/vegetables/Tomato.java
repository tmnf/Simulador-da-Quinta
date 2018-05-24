package vegetables;

import pt.iul.ista.poo.utils.Point2D;

import objects.Estado;

public class Tomato extends Vegetable {

	private static final long serialVersionUID = 1L;

	private static final int MATURE = 15;
	private static final int ROTTEN = 25;

	private static final int PONTOS = 2;

	public Tomato(Point2D p) {
		super(p);
	}

	@Override
	public void updateState() {
		if (getCycles() >= getMature() && getCycles() < getRotten() && getCareInfo())
			setState(getClass().getSimpleName().toLowerCase());
		else if (getCycles() >= getRotten())
			setState(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase());
		else
			setState(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase());
	}

	@Override
	public int getPontos() {
		return PONTOS;
	}

	@Override
	public int getRotten() {
		return ROTTEN;
	}

	@Override
	public int getMature() {
		return MATURE;
	}

	@Override
	public void takeCare() {
		setCare(true);
		sumCycles(1);
	}

}
