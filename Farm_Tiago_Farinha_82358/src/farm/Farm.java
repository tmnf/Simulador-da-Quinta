package farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import animals.Sheep;
import objects.Farmer;
import objects.Land;
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
	private Tomato tomato;
	private Cabage cabage;
	
	private static final String SAVE_FNAME = "config/savedGame";

	private static final int MIN_X = 5;
	private static final int MIN_Y = 5;

	private static Farm INSTANCE;

	private int max_x;
	private int max_y;

	private Farm(int max_x, int max_y) {
		if (max_x < 5 || max_y < 5)
			throw new IllegalArgumentException();

		this.max_x = max_x;
		this.max_y = max_y;

		INSTANCE = this;

		ImageMatrixGUI.setSize(max_x, max_y);

		loadScenario();

	}

	private void registerAll() {

		images = new ArrayList<ImageTile>();

		//Adicionar Imagens
		farmer = new Farmer(new Point2D(0, 0));
		sheep = new Sheep(new Point2D(0, 3));
		tomato = new Tomato(new Point2D(2,1));
		
		images.add(sheep);
		images.add(farmer);
		images.add(tomato);
		images.add(cabage);
		
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
		
		System.out.println("Update sent " + a);
		sheep.addCiclo();
		tomato.addCiclo();
		cabage.addCiclo();
		
		if (a instanceof Integer) {
			int key = (Integer) a;
			if (Direction.isDirection(key)) {
				System.out.println("Update is a Direction " + Direction.directionFor(key));
				farmer.move(Direction.directionFor(key));
			}
			
			
			
		}

		ImageMatrixGUI.getInstance().setStatusMessage("Points: ");
		ImageMatrixGUI.getInstance().update();
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
		Farm f = new Farm(20, 15);
		f.play();
	}

}
