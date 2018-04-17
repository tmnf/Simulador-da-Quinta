package animals;

import farm.Farm;
import objects.ObjectStatus;
import pt.iul.ista.poo.utils.Point2D;

public class Egg extends ObjectStatus  {
	
	private static final int PONTOS = 1;
	private static final int ECLODE = 20;

	public Egg(Point2D p) {
		super(p);
	}

	@Override
	public void updateStatus() {
		if(getCiclos() >= ECLODE)
			Farm.getInstance().addImage(new Chicken(new Point2D(0,0)));
		setEstado("egg");
	}

	@Override
	public void interact() {
		Farm.getInstance().addPontos(PONTOS);
		remove();
	}
	
	

}
