package farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

import animals.Sheep;
import interfaces.Updatable;
import objects.Farmer;
import objects.Land;
import objects.Planted;
import objects.Plowed;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Cabage;
import vegetables.Tomato;

public class Farm implements Observer {

	private Farmer farmer;
	private Sheep sheep;
	private List<ImageTile> images;

	private boolean action;
	private int pontos;

	private static final String SAVE_FNAME = "config/savedGame";

	private static final int MIN_X = 5;
	private static final int MIN_Y = 5;

	private static Farm INSTANCE;

	private int max_x;
	private int max_y;

	private Farm(int max_x, int max_y) {
		if (max_x < 5 || max_y < 5)
			throw new IllegalArgumentException();

		action = false;

		this.max_x = max_x;
		this.max_y = max_y;

		INSTANCE = this;

		ImageMatrixGUI.setSize(max_x, max_y);

		loadScenario();

	}

	private void registerAll() {

		images = new ArrayList<ImageTile>();

		// Adicionar Imagens
		farmer = new Farmer(new Point2D(0, 0));
		sheep = new Sheep(new Point2D(0, 3));

		images.add(sheep);
		images.add(farmer);

		addLand();

		ImageMatrixGUI.getInstance().addImages(images);
		ImageMatrixGUI.getInstance().update();
	}

	private void addLand() {
		for (int x = 0; x != max_x; x++)
			for (int z = 0; z != max_y; z++)
				images.add(new Land(new Point2D(x, z)));
	}

	private void loadScenario() {
		registerAll();
	}

	@Override
	public void update(Observable gui, Object a) {
		// System.out.println("Update sent " + a);
		int key = (Integer) a;
		if (key == 32)
			action = true;

		action(key);
		addCycle();

		ImageMatrixGUI.getInstance().setStatusMessage("Points: " + pontos);
		ImageMatrixGUI.getInstance().update();
	}

	private void action(int key) {
		if (action == true) {
			if (Direction.isDirection(key)) {
				farmer.Position(Direction.directionFor(key));
				if (isInside(farmer.getNova()))
					action = false;
//					System.out.println(checkLand());
				switch (checkLand()) {
				case 1:
					Plant();
				case 2:
					TakeCare();
				default:
					Plow();
				}
			}

		} else
			justMove(key);

	}

	public void justMove(int key) {
		if (Direction.isDirection(key)) {
			farmer.Position(Direction.directionFor(key));
			if (isInside(farmer.getNova()))
				farmer.move();
		}
	}

	private void addCycle() {
		for (ImageTile x : images)
			if (x instanceof Updatable)
				((Updatable) x).addCiclo();
	}

	private void TakeCare() {

	}

	private void Plow() {
		images.add(new Plowed(farmer.getNova()));
		ImageMatrixGUI.getInstance().addImages(images);
	}

	private int checkLand() {
		for (ImageTile x : images) {
			boolean pos = x.getPosition().equals(farmer.getNova());
			if (pos && x.getName().equals("plowed")) 
				return 1;
			if (pos && x.getName().equals("planted")) 
				return 2;
		}
		return 0;
	}

	public void Plant() {
		Random rnd = new Random();
		int rand = rnd.nextInt(2);

		switch (rand) {
		case 1:
			images.add(new Cabage(farmer.getNova()));
			break;
		default:
			images.add(new Tomato(farmer.getNova()));
			break;
		}
	}

	public int getX() {
		return max_x;
	}

	public int getY() {
		return max_y;
	}

	public boolean isInside(Point2D point) {
		if (ImageMatrixGUI.getInstance().isWithinBounds(point))
			return true;
		else
			return false;
	}

	// Não precisa de alterar nada a partir deste ponto
	private void play() {
		ImageMatrixGUI.getInstance().addObserver(this);
		ImageMatrixGUI.getInstance().go();
	}

	public static Farm getInstance() {
		assert (INSTANCE != null);
		return INSTANCE;
	}

	public static void main(String[] args) {
		Farm f = new Farm(5, 5);
		f.play();
		// f.checkLand();
	}

}
