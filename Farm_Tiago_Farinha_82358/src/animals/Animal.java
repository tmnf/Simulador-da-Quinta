package animals;

import farm.Farm;
import objects.FarmObject;
import objects.ObjectStatus;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

public abstract class Animal extends ObjectStatus {

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
			remove(); //Caso a ovelha tenha morrido, � removida do jogo
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
