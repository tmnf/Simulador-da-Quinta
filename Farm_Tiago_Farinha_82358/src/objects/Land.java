package objects;

import java.util.Random;

import farm.Farm;
import interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Cabage;
import vegetables.Tomato;
import vegetables.Vegetable;

public class Land extends FarmObject implements Interactable {

	private String estado;
	private boolean plantado;

	public Land(Point2D p) {
		super(p);
		estado = "land";
	}

	@Override
	public String getName() {
		return estado;
	}

	public void setPlowed() {
		estado = "plowed";
	}

	@Override
	public void interact() {
		plantado = isPlanted();
		if (!plantado)
			if (getName().equals("plowed"))
				plant();
			else
				setPlowed();
	}

	public void plant() {
		Random rnd = new Random();
		int rand = rnd.nextInt(2);
		switch (rand) {
		case 1:
			Farm.getInstance().addImage(new Tomato(getPosition()));
			break;
		default:
			Farm.getInstance().addImage(new Cabage(getPosition()));
			break;
		}
	}
	
	private boolean isPlanted() {
		for(FarmObject x : Farm.getInstance().getLista())
			if(x instanceof Vegetable && x.getPosition().equals(getPosition()))
				return true;
		return false;
	}
}
