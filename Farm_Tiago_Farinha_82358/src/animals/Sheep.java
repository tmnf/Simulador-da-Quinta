package animals;

import pt.iul.ista.poo.utils.Point2D;

public class Sheep extends Animal {

	private static final int poucaFome = 10;
	private static final int faminto = 20;

	public Sheep(Point2D p) {
		super(p);
	}

	@Override
	public void getStatus() {
		// ADICIONAR MOVIMENTO ALEATORIO
		if (getCiclos() >= faminto)
			setEstado("famished_sheep");
		else
			setEstado("sheep");
	}

}
