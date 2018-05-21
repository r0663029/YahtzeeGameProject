package yahtzee.ui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import yahtzee.domain.YahtzeeFacade;

import yahtzee.ui.events.GameBoardEvent;
import yahtzee.ui.events.SetAsideDieEvent;
import static yahtzee.ui.events.GameBoardEvent.ROLL;

public class DicePane extends VBox {
    private static final double spacing = 5;

    private final YahtzeeFacade model;

    private ObservableList<Node> diceThrownButtons;
    private ObservableList<Node> diceAsideButtons;
    private Button rollButton;
    private boolean active;

    public DicePane(YahtzeeFacade model) {
	super(spacing);

	this.model = model;
	active = false;

	Label diceThrownLabel = new Label("Dice thrown:");
	HBox diceThrownPane = new HBox(spacing);
	diceThrownButtons = diceThrownPane.getChildren();
	populateDiceThrownButtons();

	Label diceAsideLabel = new Label("Dice aside:");
	HBox diceAsidePane = new HBox(spacing);
	diceAsideButtons = diceAsidePane.getChildren();
	populateDiceAsideButtons();

	rollButton = new Button("roll remaining dice");
	rollButton.setOnAction(event -> fireEvent(new GameBoardEvent(ROLL)));

	this.getChildren().add(diceThrownLabel);
	this.getChildren().add(diceThrownPane);
	this.getChildren().add(diceAsideLabel);
	this.getChildren().add(diceAsidePane);
	this.getChildren().add(rollButton);
    }

    public void update() {
	updateDiceThrown();
	updateDiceAside();

	if (active) {
	    rollButton.setDisable( ! model.mayRoll());
	}
    }

    public void activate() {
	for (Node btn : diceThrownButtons) {
	    btn.setDisable(false);
	}
	rollButton.setDisable(false);
	active = true;
    }

    public void deactivate() {
	for (Node btn : diceThrownButtons) {
	    btn.setDisable(true);
	}
	rollButton.setDisable(true);
	active = false;
    }

    private void populateDiceThrownButtons() {
	for (int i = 0; i < 5; i++) {
	    DieButton btn = new DieButton();
	    btn.setOnAction(event -> fireEvent(new SetAsideDieEvent(btn.getNumberOfEyes())));
	    btn.setVisible(false);

	    diceThrownButtons.add(btn);
	}
    }

    private void populateDiceAsideButtons() {
	for (int i = 0; i < 5; i++) {
	    DieButton btn = new DieButton();
	    btn.setDisable(true);
	    btn.setVisible(false);

	    diceAsideButtons.add(btn);
	}
    }

    private void updateDiceThrown() {
	List<Integer> diceThrown = model.getDiceThrown();
	DieButton btn;
	int i;
	for (i = 0; i < diceThrown.size(); i++) {
	    btn = (DieButton)diceThrownButtons.get(i);
	    btn.setNumberOfEyes(diceThrown.get(i));
	    btn.setVisible(true);
	}

	for (/*i = diceThrown.length*/; i < diceThrownButtons.size(); i++) {
	    btn = (DieButton)diceThrownButtons.get(i);
	    btn.setVisible(false);
	}
    }

    private void updateDiceAside() {
	List<Integer> diceAside = model.getDiceAside();
	DieButton btn;
	int i;
	for (i = 0; i < diceAside.size(); i++) {
	    btn = (DieButton)diceAsideButtons.get(i);
	    btn.setNumberOfEyes(diceAside.get(i));
	    btn.setVisible(true);
	}

	for (/*i = diceAside.length*/; i < diceAsideButtons.size(); i++) {
	    btn = (DieButton)diceAsideButtons.get(i);
	    btn.setVisible(false);
	}
    }
}
