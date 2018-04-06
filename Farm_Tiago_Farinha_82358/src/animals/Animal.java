package animals;

import farm.Farm;
import objects.FarmObject;
import objects.Objeto;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public abstract class Animal extends Objeto {

	private int ciclosCuidado;
	private Vegetable vegetal;

	public Animal(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void interact() {
		if (getName().equals(getClass().getSimpleName().toLowerCase()))
			setCuidado(true);
		else
			remove();
	}

	public void comer() {
		if (podeComer()) {
			vegetal.remove();
			setCuidado(true);
		}
	}

	private boolean podeComer() {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x.getPosition().equals(this.getPosition()))
				if (x instanceof Vegetable)
					if (x.getName().equals(x.getClass().getSimpleName().toLowerCase())) {
						vegetal = (Vegetable) x;
						return true;
					}
		return false;
	}

	public int getCiclosCuidado() {
		return ciclosCuidado;
	}

	public void setCiclosCuidado(int n) {
		ciclosCuidado = n;
	}

}
