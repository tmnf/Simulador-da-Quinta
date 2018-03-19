package objects;

import java.util.Random;

import farm.Farm;
import interfaces.Interactable;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Cabage;
import vegetables.Tomato;
import vegetables.Vegetable;
import animals.Animal;

public abstract class FarmObject implements ImageTile, Interactable {

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

	// Interacts

	@Override
	public void interact() {
		// ============ MUDAR ISTO DE MODO A UNIFICAR CODIGO PARA INSERIR CLASSES
		// FACILMENTE===//

		if (getName().equals("sheep"))// Arranjar isto + Interação entre ovelha e vegetal
			((Animal) this).setCuidado(true);
		else if (getName().equals("bad_cabage") || getName().equals("bad_tomato"))
			remove();
		else if (getName().equals("cabage") || getName().equals("tomato"))
			cut();
		else if (getName().equals("small_cabage") || getName().equals("small_tomato"))
			((Vegetable) this).setCuidado(true);
		else if (getName().equals("plowed"))
			plant();
		else
			plow();
	}

	public void plow() {
		if (this instanceof Land)
			((Land) this).setPlowed();
	}

	public void cut() { // Mudar para generalizar a colheita
		if (getName().equals("cabage"))
			Farm.getInstance().addPontos(2);
		if (getName().equals("tomato"))
			Farm.getInstance().addPontos(3);
		Farm.getInstance().removeImage(this);
	}

	public void remove() {
		Farm.getInstance().removeImage(this);
	}

	public void plant() { // Se adicionar mais vegetais devo adicionar aqui individualmente?
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
