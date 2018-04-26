package interfaces;

import objects.FarmObject;

public interface Interactable {

	void interact(FarmObject x);
	int getPriority();

}
