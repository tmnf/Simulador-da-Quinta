package vegetables;

import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends FarmObject implements Interactable, Updatable {

	private String estado;
	private int ciclos;
	
	public Vegetable(Point2D p) {
		super(p);
		getStatus();
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
	
	@Override
	public void addCiclo() {
		ciclos++;
		getStatus();
	}
	
	public int getCiclos() {
		return ciclos;
	}
	@Override
	public String getName() {
		return estado;
	}
	
	public void setEstado(String state) {
		estado=state;
	}

}
