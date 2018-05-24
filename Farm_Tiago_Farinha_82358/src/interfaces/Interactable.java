package interfaces;

import objects.FarmObject;

public interface Interactable {

	void interactWith(FarmObject x);

	int getPriority();

}
