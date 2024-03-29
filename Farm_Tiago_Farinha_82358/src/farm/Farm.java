package farm;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import animals.Animal;
import animals.Chicken;
import animals.Sheep;
import entities.Farmer;
import entities.Land;
import entities.Rock;
import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import objects.PositionUtil;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Farm implements Observer, Serializable {

	private static final long serialVersionUID = 1L;

	private Farmer farmer;

	private List<FarmObject> gameObjects;

	public static final int SPACE = 32;
	public static final int S = 83;
	public static final int L = 76;
	public static final int B = 66;

	private Dimension dimension;

	private boolean action;
	private int pontos, ciclos;

	private static final String CONFIG = "Configs/config.txt";
	private static final String SAVE = "Configs/savedGame.sav";

	private static final int MIN_X = 5;
	private static final int MIN_Y = 5;

	private static Farm INSTANCE;

	private int max_x;
	private int max_y;

	private Farm(int max_x, int max_y) {
		if (max_x < MIN_X || max_y < MIN_Y)
			throw new IllegalArgumentException();

		action = false;

		this.max_x = max_x;
		this.max_y = max_y;

		INSTANCE = this;

		ImageMatrixGUI.setSize(max_x, max_y);
		dimension = ImageMatrixGUI.getInstance().getGridDimension();

		loadScenario();
	}

	private void registerAll() {
		gameObjects = new ArrayList<>();

		farmer = new Farmer(new Point2D(0, 0));

		addImage(farmer);

		Random rnd = new Random();
		for (int a = 0; a != 2; a++) { // Adicionar 2 de cada
			addImage(new Sheep(new Point2D(rnd.nextInt(max_x), rnd.nextInt(max_y))));
			addImage(new Chicken(new Point2D(rnd.nextInt(max_x), rnd.nextInt(max_y))));
		}
		addLand();

		ImageMatrixGUI.getInstance().setStatusMessage("Pontos: " + pontos + " | Ciclos: " + ciclos);
	}

	@Override
	public void update(Observable gui, Object a) {
		int key = (Integer) a;
		if (key == SPACE) // Iniciar ac��o
			action = true;
		if (key == S)
			FileLoader.saveGame(SAVE);
		if (key == L) {
			loadGame();
		}
		if (key == B)
			new Window();

		if (Direction.isDirection(key)) {
			farmer.Position(Direction.directionFor(key));
			if (action == true)
				action(key);
			else {
				farmer.move();
				addCycle();
			}
		}
		ImageMatrixGUI.getInstance().setStatusMessage("Pontos: " + pontos + " | Ciclos: " + ciclos);
		ImageMatrixGUI.getInstance().update();
	}

	// ============================(Cycles e Actions)================//
	private void action(int key) {
		if (PositionUtil.isInside(farmer.getNova())) {
			action = false;
			applyAction();
		} else
			action = false;
	}

	private void addCycle() {
		for (FarmObject x : getUpdatables())
			((Updatable) x).addCycle();
		ciclos++;
	}

	public void refresh() {
		ImageMatrixGUI.getInstance().setStatusMessage("Pontos: " + pontos + " | Ciclos: " + ciclos);
		ImageMatrixGUI.getInstance().update();
	}
	// =====================Diferentes Objetos e
	// AplicarA��o========================//

	public ArrayList<FarmObject> getInteratables(Point2D pos) {
		ArrayList<FarmObject> list = new ArrayList<>();
		for (FarmObject x : gameObjects)
			if (x instanceof Interactable && x.getPosition().equals(pos))
				list.add(x);
		return list;
	}

	public ArrayList<FarmObject> getUpdatables() {
		ArrayList<FarmObject> list = new ArrayList<>();
		for (FarmObject x : gameObjects)
			if (x instanceof Updatable)
				list.add(x);
		return list;
	}

	private void applyAction() {
		FarmObject x = PositionUtil.getMajorObject(farmer.getNova());
		if (x != null)
			((Interactable) x).interactWith(farmer);
	}

	// =========================Aux==========================//
	private void loadScenario() {
		registerAll();
	}

	public boolean colides(Point2D x) {
		for (FarmObject p : Farm.getInstance().getLista())
			if (p.getPosition().equals(x) && (p instanceof Animal || p instanceof Farmer))
				return true;
		return false;
	}

	public void addPontos(int p) {
		pontos += p;
	}

	public void takePontos(int p) {
		pontos -= p;
	}

	private void addLand() {
		for (int x = 0; x != max_x; x++)
			for (int z = 0; z != max_y; z++)
				if (Math.random() >= 0.9)
					addImage(new Rock(new Point2D(x, z)));
				else
					addImage(new Land(new Point2D(x, z)));
	}

	public void addImage(FarmObject x) {
		gameObjects.add(x);
		ImageMatrixGUI.getInstance().addImage((ImageTile) x);
	}

	private void addImages(List<FarmObject> list) {
		for (FarmObject x : list)
			addImage(x);
	}

	public void removeImage(FarmObject x) {
		gameObjects.remove(x);
		ImageMatrixGUI.getInstance().removeImage((ImageTile) x);
	}

	public void loadGame() {
		Farm loaded = FileLoader.loadGame(SAVE);
		if (loaded != null)
			if (dimension.equals(loaded.getDim())) {
				ImageMatrixGUI.getInstance().clearImages();
				gameObjects.clear();
				addImages(loaded.getLista());
				farmer = loaded.getFarmer();
				pontos = loaded.getPontos();
				ciclos = loaded.getCiclos();
				Window.aviso("Jogo Carregado");
			} else {
				Window.aviso("Dimens�o errada! Mudar para: " + (int) loaded.getDim().getWidth() + "x"
						+ (int) loaded.getDim().getHeight());
			}
	}

	// ===========================Getters=============================//
	public List<FarmObject> getLista() {
		return gameObjects;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public int getPontos() {
		return pontos;
	}

	public int getCiclos() {
		return ciclos;
	}

	public Dimension getDim() {
		return dimension;
	}

	// A��es De Inicializa��o
	private void play() {
		ImageMatrixGUI.getInstance().addObserver(this);
		ImageMatrixGUI.getInstance().go();
	}

	public static Farm getInstance() {
		assert (INSTANCE != null);
		return INSTANCE;
	}

	public static void main(String[] args) {
		String[] dim = FileLoader.readTextFile(CONFIG);
		Farm f = new Farm(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]));
		f.play();
	}

}
