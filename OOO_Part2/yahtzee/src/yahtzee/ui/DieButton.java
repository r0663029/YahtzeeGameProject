package yahtzee.ui;

import javafx.scene.control.Button;

public class DieButton extends Button {

    private int numberOfEyes;

    public DieButton(int numberOfEyes) {
	super();
        setNumberOfEyes(numberOfEyes);
    }

    public DieButton() {
	super();
	this.numberOfEyes = 0;
    }

    public int getNumberOfEyes() {
	return numberOfEyes;
    }

    public void setNumberOfEyes(int numberOfEyes) {
	this.numberOfEyes = numberOfEyes;
	this.setText(Integer.toString(numberOfEyes));
    }
}
