package objects;

import pt.iul.ista.poo.utils.Point2D;

public class Land extends FarmObject{

	public Land(Point2D p) {
		super(p);
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	
	

}
