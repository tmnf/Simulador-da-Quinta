package objects;

import java.util.Random;

import farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Cabage;
import vegetables.Tomato;
import vegetables.Vegetable;

public abstract class FarmObject implements ImageTile {

	private Point2D position;

	public FarmObject(Point2D p) {
		this.position = p;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName().toLowerCase();
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	public boolean isInside(Point2D point) {
		if (ImageMatrixGUI.getInstance().isWithinBounds(point))
			return true;
		else
			return false;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public void interact() {
		// if(getName().equals("sheep")) //Arranjar isto
		// ((Animal) this).setCuidado(true);
		if (getName().equals("bad_cabage") || getName().equals("bad_tomato"))
			remove();
		else if (getName().equals("cabage") || getName().equals("tomato"))
			cut();
		else if (getName().equals("small_cabage") || getName().equals("small_tomato"))
			((Vegetable) this).setCuidado(true);
		else if (this.getName().equals("plowed"))
			plant();
		else
			plow();
	}

	public void plow() {
		if (this instanceof Land)
			((Land) this).setEstado("plowed");
	}

	public void cut() {
		if (getName().equals("cabage"))
			Farm.getInstance().addPontos(2);
		if (getName().equals("tomato"))
			Farm.getInstance().addPontos(3);
		Farm.getInstance().removeImage(this);
	}

	public void remove() {
		Farm.getInstance().removeImage(this);
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

}
