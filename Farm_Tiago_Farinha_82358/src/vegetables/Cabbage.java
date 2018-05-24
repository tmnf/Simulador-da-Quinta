package vegetables;

import pt.iul.ista.poo.utils.Point2D;

/**
 * Represents an object of the type Cabbage
 * 
 * @author Tiago Farinha
 *
 * @version 1.0
 */

public class Cabbage extends Vegetable {

	private static final long serialVersionUID = 1L;

	/**
	 * Number of cycles until the cabbage gets mature
	 */
	private static final int MATURE = 10;
	/**
	 * Number of cycles until the cabbage gets rotten
	 */
	private static final int ROTTEN = 30;
	/**
	 * Number of points attributed to the player when picked up a mature cabbage
	 */
	private static final int PONTOS = 3;

	/**
	 * Creates a new cabbage
	 * 
	 * @param p
	 *            Position where the new cabbage will be located at the game grid
	 */
	public Cabbage(Point2D p) {
		super(p);

	}

	/**
	 * Adds one cycle to the object's life and changes its state according to the object's current condition
	 */
	@Override
	public void addCycle() {
		if (getCareInfo()) {
			setCare(false);
		}
		sumCycles(1);
		updateState();
	}

	/**
	 * Applies the defined conditions established to the cabbage object when interacted by a player
	 */
	@Override
	public void takeCare() {
		super.takeCare();
		sumCycles(1);
		updateState();
	}

	/**Returns the number of points the player earns when a mature cabbage is collected
	 * @return Number of points
	 */
	@Override
	public int getPontos() {
		return PONTOS;
	}

	/**Returns the number of cycles the cabbage takes to get rotten
	 * @return Cycles until rotten
	 */
	@Override
	public int getRotten() {
		return ROTTEN;
	}

	/**Returns the number of cycles the cabbage takes to get mature
	 * @return Cycles until mature
	 */
	@Override
	public int getMature() {
		return MATURE;
	}

}
