package objects;

import farm.Farm;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public class Land extends FarmObject{
	
	private String estado;

	public Land(Point2D p) {
		super(p);
		estado = "land";
	}

	@Override
	public String getName() {
		return estado;
	}
	
	public void setEstado(String state) {
		estado=state;
	}
}
