package farm;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import animals.Animal;
import animals.Chicken;
import animals.Egg;
import animals.Sheep;
import interfaces.Interactable;
import interfaces.Updatable;
import objects.FarmObject;
import objects.Farmer;
import objects.Land;
import objects.Rock;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import vegetables.Vegetable;

@SuppressWarnings("serial")
public class Farm implements Observer, Serializable {

	private Farmer farmer;

	private List<FarmObject> images;

	private List<FarmObject> buffer;
	private List<FarmObject> toRemove;

	public static final int SPACE = 32;
	public static final int S = 83;
	public static final int I = 73;

	private Dimension dimension;

	private boolean action;
	private int pontos;

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
		images = new ArrayList<>();
		buffer = new ArrayList<>();
		toRemove = new ArrayList<>();

		farmer = new Farmer(new Point2D(0, 0));

		addImage(farmer);

		Random rnd = new Random();
		for (int a = 0; a != 2; a++) { // Adicionar 2 de cada
			addImage(new Sheep(new Point2D(rnd.nextInt(max_x), rnd.nextInt(max_y))));
			addImage(new Chicken(new Point2D(rnd.nextInt(max_x), rnd.nextInt(max_y))));
		}
		addLand();
	}

	@Override
	public void update(Observable gui, Object a) {
		int key = (Integer) a;

		if (key == SPACE) // Iniciar acção
			action = true;

		if (key == S)
			Save.saveGame(SAVE);
		if (key == I) {
			loadGame();
		}

		if (action == true)
			action(key);
		else
			justMove(key);

		feedIfPossible();
		releaseBuffer();
		removeFromGame();

		ImageMatrixGUI.getInstance().setStatusMessage("Points: " + pontos);
		ImageMatrixGUI.getInstance().update();
	}

	// ============================Movimentos/TriggerAction/Ciclos/Alimentação)================//
	private void action(int key) {

		if (Direction.isDirection(key)) {
			farmer.Position(Direction.directionFor(key));
			if (farmer.isInside(farmer.getNova())) {
				action = false;
				doAction();
			} else
				action = false;
		}
	}

	private void justMove(int key) {
		if (Direction.isDirection(key)) {
			farmer.Position(Direction.directionFor(key));
			farmer.move();
			addCycle();
		}
	}

	private void addCycle() {
		for (FarmObject x : images)
			if (x instanceof Updatable)
				((Updatable) x).addCiclo();
	}

	private ArrayList<Animal> searchAnimals() { // Procurar todos os animais atualmente em jogo
		ArrayList<Animal> animais = new ArrayList<>();
		Iterator<FarmObject> it = images.iterator();
		while (it.hasNext()) {
			FarmObject x = it.next();
			if (x instanceof Animal)
				animais.add((Animal) x);
		}
		return animais;
	}

	private void feedIfPossible() { // Alimentar todos caso seja possivel no local em que estao
		for (Animal x : searchAnimals())
			x.comer();
	}
	// =====================Acções/Intereções========================//

	private void doAction() {

		ArrayList<FarmObject> toModify = new ArrayList<>();
		for (FarmObject x : images)
			if (x.getPosition().equals(farmer.getNova()))
				toModify.add(x);

		applyAction(toModify);

	}

	private void applyAction(List<FarmObject> lista) { // Melhor Metodo para isto???

		for (FarmObject x : lista) {
			if (x instanceof Animal) {
				((Animal) x).interact();
				return;
			}
		}
		for (FarmObject x : lista) {
			if (x instanceof Egg) {
				((Egg) x).interact();
				return;
			}
		}
		for (FarmObject x : lista) {
			if (x instanceof Interactable)
				if (x instanceof Vegetable) {
					((Vegetable) x).interact();
					return;
				}
		}
		for (FarmObject x : lista) {
			if (x instanceof Interactable)
				if (x instanceof Land) {
					((Land) x).interact();
					return;
				}
		}

	}

	// =========================Aux==========================//
	private void loadScenario() {
		registerAll();
	}

	public void addPontos(int p) {
		pontos += p;
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
		images.add(x);
		ImageMatrixGUI.getInstance().addImage((ImageTile) x);
	}

	public void addImages(List<FarmObject> list) {
		for (FarmObject x : list)
			addImage(x);
	}

	public void removeImage(FarmObject x) {
		images.remove(x);
		ImageMatrixGUI.getInstance().removeImage((ImageTile) x);
	}

	public void loadGame() {
		Farm loaded = Save.loadGame(SAVE);
		if (dimension.equals(loaded.getDim())) {
			ImageMatrixGUI.getInstance().clearImages();
			images = new ArrayList<>();
			addImages(loaded.getLista());
			farmer = loaded.getFarmer();
			pontos = loaded.getPontos();
			System.out.println("Jogo carregado com sucesso.");
		} else {
			System.out.println("Dimensões incompativeis; Ficheiro gravado : " + (int) loaded.getDim().getWidth() + "x"
					+ (int) loaded.getDim().getHeight() + "\n" + "Este ficheiro: " + dimension.width + "x"
					+ dimension.height);
		}
	}

	// Getters//////////////////////
	public List<FarmObject> getLista() {
		return images;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public int getPontos() {
		return pontos;
	}

	public Dimension getDim() {
		return dimension;
	}

	// Verificar estes metodos.=============================================
	public void addToBuffer(FarmObject x) {
		buffer.add(x);
	}

	public void releaseBuffer() {
		for (FarmObject x : buffer)
			addImage(x);
		buffer = new ArrayList<>();
	}

	public void addToTrash(FarmObject x) {
		toRemove.add(x);
	}

	public void removeFromGame() {
		for (FarmObject x : toRemove)
			removeImage(x);
		toRemove = new ArrayList<>();
	}
	///////////////////////////////////////////////////

	public boolean colides(Point2D x) {
		for (FarmObject p : Farm.getInstance().getLista())
			if (p.getPosition().equals(x) && (p instanceof Animal || p instanceof Farmer))
				return true;
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
		String[] dim = Save.readFile(CONFIG);
		Farm f = new Farm(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]));
		f.play();
	}

}
