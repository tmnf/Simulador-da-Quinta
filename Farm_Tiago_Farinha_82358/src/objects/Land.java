package objects;

import pt.iul.ista.poo.utils.Point2D;

public class Land extends FarmObject{

	private String estado;
	private int stateNum;
	
	public Land(Point2D p) {
		super(p);
	}
	
	@Override
	public int getLayer() {
		return 0;
	}

}
