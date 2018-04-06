package vegetables;

import pt.iul.ista.poo.utils.Point2D;

public class Tomato extends Vegetable {

	private int ciclosCuidado;

	private static final int stopsGrowing = 10;
	private static final int mature = 15;
	private static final int rotten = 25;
	private static final int pontos = 2;

	public Tomato(Point2D p) {
		super(p);
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= mature && getCiclos() < rotten)
			setEstado("tomato");
		else if (getCiclos() >= rotten)
			setEstado("bad_tomato");
		else
			setEstado("small_tomato");
	}

	@Override
	public void addCiclo() {

		if (getCuidado())
			ciclosCuidado = 0;

		if (ciclosCuidado < stopsGrowing) {
			if (getCuidado() == false) {
				ciclosCuidado++;
			}
			sumCicles(1);
			updateStatus();
		}
	}
	
	@Override
	public int getPontos() {
		return pontos;
	}

}
