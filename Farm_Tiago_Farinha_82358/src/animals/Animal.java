package animals;

import farm.Farm;
import objects.FarmObject;
import objects.Objeto;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;


public abstract class Animal extends Objeto {

	private boolean podeComer;
	private int ciclosCuidado;
	
	public Animal(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void interact() {
		setCuidado(true);
	}
	
	public void comer() {//Melhorar istoooooo
		FarmObject mod = null;
		podeComer = false;
		for(FarmObject x : Farm.getInstance().getLista())
			if(x.getPosition().equals(this.getPosition())) 
				if(x instanceof Vegetable)
					if(x.getName().equals(x.getClass().getSimpleName().toLowerCase())){
					podeComer = true;
					mod = x;}
		if(podeComer) {
			((Vegetable) mod).remove();
			setCuidado(true);
		}
	}
	
	public int getCiclosCuidado() {
		return ciclosCuidado;
	}
	public void setCiclosCuidado(int n) {
		ciclosCuidado = n;
	}
	
	
}
