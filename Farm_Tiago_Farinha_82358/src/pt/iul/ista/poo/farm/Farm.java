package pt.iul.ista.poo.farm;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import pt.iul.ista.poo.farm.objects.Farmer;
import pt.iul.ista.poo.farm.objects.Land;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Farm implements Observer {

	
	private Farmer farmer;
	// TODO
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
		
		// Não usar ImageMatrixGUI antes de inicializar o tamanho		
		// TODO

		loadScenario();

	}

	private void registerAll() {
		// TODO
		List<ImageTile> images = new ArrayList<ImageTile>();

		// images.addAll(...);
		farmer = new Farmer(new Point2D(0,0));
		images.add(farmer);
		
	//criar metodo void
		for(int x = 0; x!= max_x; x++)
			for(int z = 0; z != max_y; z++)
				images.add(new Land(new Point2D(x,z)));
				

		ImageMatrixGUI.getInstance().addImages(images);
		ImageMatrixGUI.getInstance().update();
	}

	

	private void loadScenario() {
		// TODO
		registerAll();
	}

	@Override
	public void update(Observable gui, Object a) {
		
		farmer.move();
		System.out.println("Update sent " + a);
		// TODO
		if (a instanceof Integer) {
			int key = (Integer) a;
			if (Direction.isDirection(key)) {
				System.out.println("Update is a Direction " + Direction.directionFor(key));
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
		Farm f = new Farm(5, 7);
		f.play();
	}

}
