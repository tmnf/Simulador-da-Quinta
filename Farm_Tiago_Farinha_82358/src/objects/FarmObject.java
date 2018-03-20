package objects;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

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

}
