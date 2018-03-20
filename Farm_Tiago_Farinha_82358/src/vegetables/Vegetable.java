package vegetables;

import farm.Farm;
import objects.Estado;
import objects.Objeto;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends Objeto {

	public Vegetable(Point2D p) {
		super(p);
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public void interact() {
		
		if (getName().equals(Estado.RUINED.getState() + getClass().getSimpleName().toLowerCase())) 
			remove();
		else if (getName().equals(getClass().getSimpleName().toLowerCase()))
			cut();
		else if (getName().equals(Estado.SMALL.getState() + getClass().getSimpleName().toLowerCase()))
			setCuidado(true);
	}

	public void cut() {
		Farm.getInstance().addPontos(this.getPontos());
		Farm.getInstance().removeImage(this);
	}

	public void remove() {
		Farm.getInstance().removeImage(this);
	}

	public abstract int getPontos();

}
