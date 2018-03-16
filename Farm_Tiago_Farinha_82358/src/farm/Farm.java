package farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import animals.Sheep;
import interfaces.Updatable;
import objects.FarmObject;
import objects.Farmer;
import objects.Land;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Farm implements Observer {

	private Farmer farmer;
	private Sheep sheep;
	private List<FarmObject> images;

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

		images = new ArrayList<>();

		// Adicionar Imagens
		farmer = new Farmer(new Point2D(0, 0));
		sheep = new Sheep(new Point2D(7, 7));

		images.add(farmer);
		images.add(sheep);

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
		if (key == 32)
			action = true;

		if (action == true)
			action(key);
		else
			justMove(key);
		
		sheep.Position();
		addCycle();
		

		ImageMatrixGUI.getInstance().setStatusMessage("Points: " + pontos);
		ImageMatrixGUI.getInstance().update();
	}

	// ============================Movimentos/TriggerAction/Ciclos)================//
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
		}
	}

	private void addCycle() {
		for (FarmObject x : images)
			if (x instanceof Updatable)
				((Updatable) x).addCiclo();
	}

	// =====================Acções/Intereções========================//

	private void doAction() {
		FarmObject mod = null;
		for (FarmObject x : images)
			if (x.getPosition().equals(farmer.getNova()))
				mod = x;
		mod.interact();

	}

	// =========================Aux==========================//

	public void addPontos(int p) {
		pontos += p;
	}

	private void addLand() {
		for (int x = 0; x != max_x; x++)
			for (int z = 0; z != max_y; z++)
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
