package objects;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import farm.Farm;
import interfaces.Interactable;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class PositionUtil {

	public static FarmObject getMajorObject(Point2D pos) {
		PriorityQueue<FarmObject> toInteract = new PriorityQueue<>(new Comparator<FarmObject>() {
			@Override
			public int compare(FarmObject o1, FarmObject o2) {
				return ((Interactable) o2).getPriority() - ((Interactable) o1).getPriority();
			}
		});
		for (FarmObject x : Farm.getInstance().getInteratables(pos))
			toInteract.add(x);
		return toInteract.poll();
	}

	public static Point2D getNewPosition(Point2D pos) {
		List<Point2D> points = Direction.getNeighbourhoodPoints(pos);
		Collections.shuffle(points);
		for (Point2D x : points)
			if (!Farm.getInstance().colides(x) && isInside(x))
				return x;
		return pos;
	}

	public static boolean isInside(Point2D point) {
		if (ImageMatrixGUI.getInstance().isWithinBounds(point))
			return true;
		return false;
	}

}
