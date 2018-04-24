package animals;

import farm.Farm;
import pt.iul.ista.poo.utils.Point2D;

public class Egg extends Animal {

	private static final long serialVersionUID = 1L;
	
	private static final int PONTOS = 1;
	private static final int ECLODE = 20;
	
	private boolean ecloded;

	public Egg(Point2D p) {
		super(p);
		setEstado("egg");
	}

	@Override
	public void updateStatus() {
		if (getCiclos() >= ECLODE) {
			Position();
			ecloded = true;
			Farm.getInstance().addImage(new Chicken(getNova()));
			Farm.getInstance().removeImage(this);
		}
	}

	@Override
	public void interact() {
		Farm.getInstance().addPontos(PONTOS);
		Farm.getInstance().removeImage(this);
	}
	public boolean getEclode() {
		return ecloded;
	}

}
