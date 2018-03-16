package vegetables;

import objects.Objeto;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends Objeto{

	public Vegetable(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
}
