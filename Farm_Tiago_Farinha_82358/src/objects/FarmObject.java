package objects;

import pt.iul.ista.poo.utils.Point2D;
import java.io.Serializable;

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

	public void setPosition(Point2D position) {
		this.position = position;
	}

}
