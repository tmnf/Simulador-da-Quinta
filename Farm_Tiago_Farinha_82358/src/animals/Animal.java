package animals;

import objects.Objeto;
import pt.iul.ista.poo.utils.Point2D;


public abstract class Animal extends Objeto {

	public Animal(Point2D p) {
		super(p);
	}
	
	@Override
	public int getLayer() {
		return 2;
	}
	
}
