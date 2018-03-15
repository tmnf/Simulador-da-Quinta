package farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

import animals.Animal;
import animals.Sheep;
import interfaces.Interactable;
import interfaces.Updatable;
import objects.Farmer;
import objects.Land;
import objects.Plowed;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Cabage;
import vegetables.Tomato;
import vegetables.Vegetable;

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
		sheep = new Sheep(new Point2D(2,2));

		images.add(sheep);
		images.add(farmer);

		addLand();

		ImageMatrixGUI.getInstance().addImages(images);
		ImageMatrixGUI.getInstance().update();
	}

	private void loadScenario() {
		registerAll();
	}

	@Override
	public void update(Observable gui, Object a) {

		int key = (Integer) a;
		boolean dentro = isInside(sheep.getPosition());
		if (key == 32)
			action = true;

		if (action == true)
			action(key);
		else
			justMove(key);

		addCycle();
		sheep.Position();

		ImageMatrixGUI.getInstance().setStatusMessage("Points: " + pontos);
		ImageMatrixGUI.getInstance().addImages(images);
		ImageMatrixGUI.getInstance().update();
	}

	// ============================Movimentos/TriggerAction/Ciclos)================//
	private void action(int key) {

		if (Direction.isDirection(key)) {
			farmer.Position(Direction.directionFor(key));
			if (isInside(farmer.getNova())) {
				action = false;
				doAction();
			}
		}
	}

	private void justMove(int key) {
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

	// =====================Acções/Intereções========================//

	private void takeCare(ImageTile x) {
		if (x instanceof Interactable)
			((Interactable) x).takeCare();
	}

	private void plow() {
		images.add(new Plowed(farmer.getNova()));
	}

	private void plant() {
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

	private void cut(ImageTile x) {
		if (x.getName().equals("cabage"))
			pontos += 2;
		if (x.getName().equals("tomato"))
			pontos += 3;
		images.remove(x);
	}

	private void remove(ImageTile x) {
		images.remove(x);
	}

	private void doAction() {
		for (ImageTile x : images)
			if (ruined(x)) {
				remove(x);
				return;
			}
		for (ImageTile x : images)
			if (grown(x)) {
				cut(x);
				return;
			}
		for (ImageTile x : images)
			if (planted(x)) {
				takeCare(x);
				return;
			}
	
		for (ImageTile x : images)
			if (plowed(x)) {
				plant();
				return;
			}
		plow();

	}

	// =====================Estados do Solo========================//
	private boolean planted(ImageTile x) {
		if (x.getPosition().equals(farmer.getNova())
				&& (x.getName().equals("small_cabage") || x.getName().equals("small_tomato")))
			return true;
		return false;

	}

	private boolean grown(ImageTile x) {
		if (x.getPosition().equals(farmer.getNova()) && (x.getName().equals("cabage") || x.getName().equals("tomato")))
			return true;
		return false;

	}

	private boolean ruined(ImageTile x) {
		if (x.getPosition().equals(farmer.getNova())
				&& (x.getName().equals("bad_cabage") || x.getName().equals("bad_tomato")))
			return true;
		return false;
	}

	private boolean plowed(ImageTile x) {
		if (x.getPosition().equals(farmer.getNova()) && x.getName().equals("plowed"))
			return true;
		return false;
	}

	// =========================Aux==========================//
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

	private void addLand() {
		for (int x = 0; x != max_x; x++)
			for (int z = 0; z != max_y; z++)
				images.add(new Land(new Point2D(x, z)));
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
		Farm f = new Farm(14, 14);
		f.play();
	}

}
