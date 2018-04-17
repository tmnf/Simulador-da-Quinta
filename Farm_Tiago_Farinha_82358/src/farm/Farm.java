package farm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;

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

public class Farm implements Observer {

	private Farmer farmer;

	private List<FarmObject> images;
	private ArrayList<Animal> animais;

	public static final int SPACE = 32;
	public static final int P = 80;

	private boolean action;
	private int pontos;

	private static final String CONFIG = "Configs/config.txt";

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

		loadScenario();
	}

	private void registerAll() {
		images = new ArrayList<>();

		farmer = new Farmer(new Point2D(0, 0));

		images.add(farmer);

		Random rnd = new Random();
		for (int a = 0; a != 2; a++) {
			images.add(new Sheep(new Point2D(rnd.nextInt(max_x), rnd.nextInt(max_y))));
			images.add(new Chicken(new Point2D(rnd.nextInt(max_x), rnd.nextInt(max_y))));
		}
		addLand();

		for (FarmObject x : images)
			ImageMatrixGUI.getInstance().addImage((ImageTile) x);
		ImageMatrixGUI.getInstance().update();
	}

	private void loadScenario() {
		registerAll();
	}

	@Override
	public void update(Observable gui, Object a) {
		int key = (Integer) a;

		if (key == SPACE) // Iniciar acção
			action = true;

		if (key == P) 
			addImage(new Egg(new Point2D(0, 0)));

		if (action == true)
			action(key);
		else
			justMove(key);

		feedIfPossible();

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

	private void searchAnimals() { // Procurar todos os animais atualmente em jogo
		animais = new ArrayList<>();
		for (FarmObject x : images)
			if (x instanceof Animal)
				animais.add((Animal) x);
	}

	private void feedIfPossible() { // Alimentar todos caso seja possivel no local em que estao
		searchAnimals();
		for (Animal x : animais)
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

	private void applyAction(List<FarmObject> lista) {

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

	public void removeImage(FarmObject x) {
		images.remove(x);
		ImageMatrixGUI.getInstance().removeImage((ImageTile) x);
	}

	public List<FarmObject> getLista() {
		return images;
	}

	public static String[] readFile(String s) {
		String[] info = new String[100];
		try {
			Scanner fileScanner = new Scanner(new File(s));
			int i = 0;
			while (fileScanner.hasNext()) {
				info[i] = fileScanner.next();
				i++;
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado.");
		}
		return info;
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
		String[] dim = readFile(CONFIG);
		Farm f = new Farm(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]));
		f.play();
	}

}
