package vegetables;

import pt.iul.ista.poo.utils.Point2D;

public class Cabbage extends Vegetable {

	private static final int MATURE = 10;
	private static final int ROTTEN = 30;

	private static final int pontos = 3;

	public Cabbage(Point2D p) {
		super(p);

	}

	@Override
	public void addCiclo() {
		if (getCuidado()) {
			sumCicles(1); // OU VAI INCREMENTANDO SEMPRE QUE FOR CUIDADA? DO TIPO: +2, +3, +4...?
			setCuidado(false);
		}
		sumCicles(1);
		updateStatus();
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

}
