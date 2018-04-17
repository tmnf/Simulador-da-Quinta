package objects;

import farm.Farm;
import interfaces.Interactable;
import interfaces.Updatable;
import pt.iul.ista.poo.utils.Point2D;

public abstract class ObjectStatus extends FarmObject implements Updatable, Interactable {

	private String estado;
	private int ciclos;
	private boolean cuidado;

	public ObjectStatus(Point2D p) {
		super(p);
		updateStatus();
		cuidado = false;
	}

	public int getCiclos() {
		return ciclos;
	}

	@Override
	public String getName() {
		return estado;
	}

	public void setEstado(String state) {
		estado = state;
	}
	public String getEstado(){
		return estado;
	}

	public void sumCicles(int n) {
		ciclos += n;
	}

	public boolean getCuidado() {
		return cuidado;
	}

	@Override
	public void addCiclo() {
		ciclos++;
		updateStatus();
	}

	public void setCuidado(boolean x) {
		cuidado = x;
	}

	public void takeCare() {
		cuidado = true;
	}

	public void resetCiclo() {
		ciclos = 0;
		updateStatus();
	}

	public void remove() {
		Farm.getInstance().removeImage(this);
	}

}