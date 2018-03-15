package vegetables;

import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends FarmObject implements Interactable, Updatable {

	private String estado;
	private int ciclos;
	private boolean cuidado;
	
	public Vegetable(Point2D p) {
		super(p);
		getStatus();
		cuidado = false;
	}
	
	@Override
	public int getLayer() {
		return 2;
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
	@Override
	public void takeCare() {
		cuidado = true;
	}
	
	public void setCiclo(int n) {
		ciclos += n;
	}
	
	public boolean getCuidado() {
		return cuidado;
	}
	@Override
	public void addCiclo() {
		ciclos++;
		getStatus();
	}
}
