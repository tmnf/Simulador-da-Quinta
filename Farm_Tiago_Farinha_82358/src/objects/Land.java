package objects;

import java.util.Random;

import farm.Farm;
import interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Cabbage;
import vegetables.Tomato;
import vegetables.Vegetable;

public class Land extends FarmObject implements Interactable {

	private String estado;

	public Land(Point2D p) {
		super(p);
		estado = "land";
	}

	@Override
	public String getName() {
		return estado;
	}

	public void setUnplowed() {
		estado = "land";
	}

	@Override
	public void interact() {
		if (!isPlanted())
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

	private boolean isPlanted() {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x instanceof Vegetable && x.getPosition().equals(getPosition()))
				return true;
		return false;
	}

}
