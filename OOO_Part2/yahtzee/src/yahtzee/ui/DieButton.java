package yahtzee.ui;

import javafx.scene.control.Button;

public class DieButton extends Button {

    private int numberOfEyes;

    public DieButton(int numberOfEyes) {
	super();
	this.numberOfEyes = numberOfEyes;
	this.setText(Integer.toString(numberOfEyes));
    }

    public int getNumberOfEyes() {
	return numberOfEyes;
    }
}
