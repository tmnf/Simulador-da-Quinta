package objects;

import interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;

public class Land extends FarmObject implements Interactable{
	
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
