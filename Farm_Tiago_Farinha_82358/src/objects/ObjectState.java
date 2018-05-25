package objects;

import entities.Land;
import farm.Farm;
import interfaces.Interactable;
import interfaces.Updatable;
import pt.iul.ista.poo.utils.Point2D;

public abstract class ObjectState extends FarmObject implements Updatable, Interactable {

	private static final long serialVersionUID = 1L;

	private String state;
	private int cycles;
	private boolean wasCared;

	public ObjectState(Point2D p) {
		super(p);
		wasCared = false;
	}

	public int getCycles() {
		return cycles;
	}

	@Override
	public String getName() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void sumCycles(int n) {
		cycles += n;
	}

	public boolean getCareInfo() {
		return wasCared;
	}

	@Override
	public void addCycle() {
		cycles++;
		updateState();
	}

	public void setCare(boolean x) {
		wasCared = x;
	}

	public void takeCare() {
		wasCared = true;
	}

	public void resetCycle() {
		cycles = 0;
		updateState();
	}

	public void UnplowLand(Point2D pos) {
		for (FarmObject x : Farm.getInstance().getLista())
			if (x instanceof Land && x.getPosition().equals(pos))
				((Land) x).setUnplowed();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "- Pos: " + getPosition();
	}

}
