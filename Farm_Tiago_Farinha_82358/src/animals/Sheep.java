package animals;

import pt.iul.ista.poo.utils.Point2D;

public class Sheep extends Animal {

	private static final int Fome = 10;
	private static final int faminto = 50;

	public Sheep(Point2D p) {
		super(p);
		setEstado("sheep");
	}

	@Override
	public void updateStatus() {

		if (getCiclos() >= Fome && getCiclos() < faminto)
			startMoving();
		else if (getCiclos() >= faminto) {
			setEstado("famished_sheep");
			stopMoving();
		}

	}
	
	 @Override
	public void interact() {
		 setCuidado(true);
		 setEstado("sheep");
	}

	@Override
	public void addCiclo() {

		if (getCuidado()) {
			resetCiclo();
			setCuidado(false);
			stopMoving();
		}

		sumCicles(1);
		Position();
		updateStatus();
	}

}
