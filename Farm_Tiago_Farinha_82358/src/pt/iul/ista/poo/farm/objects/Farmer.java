package pt.iul.ista.poo.farm.objects;

import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Farmer extends FarmObject {

	public Farmer(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "sheep";
	}
	
	public void move() {
		Point2D actual = getPosition();
		Point2D nova = actual.plus(new Vector2D(1,0));
		setPosition(nova);
	}
	
	
}
