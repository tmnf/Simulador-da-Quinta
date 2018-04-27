package objects;

import pt.iul.ista.poo.utils.Point2D;
import java.util.Random;

import farm.Farm;
import interfaces.Interactable;
import vegetables.Cabbage;
import vegetables.Tomato;

public class Land extends FarmObject implements Interactable {

	private static final long serialVersionUID = 1L;
	
	private String estado;

	public Land(Point2D p) {
		super(p);
		estado = "land";
	}

	@Override
	public String getName() {
		return estado;
	}
	@Override 
	public int getPriority() {
		return 0;
	}

	public void setUnplowed() {
		estado = "land";
	}

	@Override
	public void interact(FarmObject x) {
		if(x instanceof Farmer)
			if (getName().equals("plowed")) {
				plant();
			} else
				estado = "plowed";
	}

	public void plant() {
		Random rnd = new Random();
		int rand = rnd.nextInt(2);
		switch (rand) {
		case 1:
			Farm.getInstance().addImage(new Tomato(getPosition()));
			break;
		default:
			Farm.getInstance().addImage(new Cabbage(getPosition()));
			break;
		}
	}

}
