package animals;

import java.util.Random;

import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public abstract class Animal extends FarmObject implements Interactable, Updatable {
	
	private String estado;
	private int ciclos;
	private boolean cuidado;
	

	public Animal(Point2D p) {
		super(p);
		getStatus();
	}
	
	@Override
	public int getLayer() {
		return 2;
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
	@Override
	public void takeCare() {
		cuidado = true;
	}
	
	public boolean getCuidado() {
		return cuidado;
	}
	public void setCiclo(int n) {
		ciclos+=n;
	}
	
	
	

}
