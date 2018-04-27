package vegetables;

import pt.iul.ista.poo.utils.Point2D;

import objects.Estado;

public class Tomato extends Vegetable {

	private static final long serialVersionUID = 1L;

	private static final int MATURE = 15;
	private static final int ROTTEN = 25;

	private static final int pontos = 2;

	public Tomato(Point2D p) {
		super(p);
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= getMature() && getCiclos() < getRotten() && getCuidado())
			setEstado(getClass().getSimpleName().toLowerCase());
		else if (getCiclos() >= getRotten())
			setEstado(Estado.RUINED.getPrefix() + getClass().getSimpleName().toLowerCase());
		else
			setEstado(Estado.SMALL.getPrefix() + getClass().getSimpleName().toLowerCase());
	}

	@Override
	public int getPontos() {
		return pontos;
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
		setCuidado(true);
		sumCicles(1);
	}

}
