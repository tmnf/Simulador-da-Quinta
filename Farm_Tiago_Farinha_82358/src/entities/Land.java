package entities;

import pt.iul.ista.poo.utils.Point2D;
import java.util.Random;

import farm.Farm;
import interfaces.Interactable;
import objects.FarmObject;
import vegetables.Cabbage;
import vegetables.Tomato;

public class Land extends FarmObject implements Interactable {

	private static final long serialVersionUID = 1L;
	
	private String state;

	public Land(Point2D p) {
		super(p);
		state = "land";
	}

	@Override
	public String getName() {
		return state;
	}
	
	@Override 
	public int getPriority() {
		return 0;
	}

	public void setUnplowed() {
		state = "land";
	}

	@Override
	public void interactWith(FarmObject x) {
		if(x instanceof Farmer)
			if (getName().equals("plowed")) {
				plant();
			} else
				state = "plowed";
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
