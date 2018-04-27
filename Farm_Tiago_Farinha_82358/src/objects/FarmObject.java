package objects;

import pt.iul.ista.poo.utils.Point2D;
import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;

import farm.Farm;
import interfaces.Interactable;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;

public abstract class FarmObject implements ImageTile, Serializable {

	private static final long serialVersionUID = 1L;

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

	public static FarmObject getMajorObject() {
		PriorityQueue<FarmObject> toInteract = new PriorityQueue<>(new Comparator<FarmObject>() {
			@Override
			public int compare(FarmObject o1, FarmObject o2) {
				return ((Interactable) o2).getPriority() - ((Interactable) o1).getPriority();
			}
		});
		for (FarmObject x : Farm.getInstance().getInteratables(Farm.getInstance().getFarmer().getNova()))
				toInteract.add(x);
		return toInteract.poll();
	}

}
