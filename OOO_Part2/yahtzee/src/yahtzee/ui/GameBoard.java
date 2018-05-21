package yahtzee.ui;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import yahtzee.domain.YahtzeeFacade;

/**
 * This class defines the main window the players interact with.
 *
 * The objects of this class show a particular player a game board.
 */
public class GameBoard extends VBox {
    private static final double spacing = 5;

    private final YahtzeeFacade model;

    private DicePane dicePane;
    private ChooseCategoryPane chooseCategoryPane;

    public GameBoard(YahtzeeFacade model) {
	super(spacing);
	this.setPrefSize(500, 500);

	dicePane = new DicePane(model);
	chooseCategoryPane = new ChooseCategoryPane(model);

	this.getChildren().add(dicePane);
	this.getChildren().add(chooseCategoryPane);

	this.model = model;
    }

    /**
     * Update this board based on the current state of the model.
     */
    public void update() {
	dicePane.update();
	chooseCategoryPane.update();
    }

    public void activate() {
	dicePane.activate();
	chooseCategoryPane.activate();
    }

    public void deactivate() {
	dicePane.deactivate();
	chooseCategoryPane.deactivate();
    }
}
