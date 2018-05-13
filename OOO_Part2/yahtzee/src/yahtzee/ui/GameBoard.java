package yahtzee.ui;

import static javafx.geometry.Pos.CENTER;

import java.util.List;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;

import yahtzee.domain.YahtzeeFacade;

import yahtzee.ui.events.GameBoardEvent;
import static yahtzee.ui.events.GameBoardEvent.ROLL;

/**
 * This class defines the main window the players interact with.
 *
 * The objects of this class show a particular player a game board, and an
 * indication of which player's turn it is.
 */
public class GameBoard extends Stage {

    private static final double spacing = 5;

    private final YahtzeeFacade model;

    private Label diceLabel;
    private Label footer;

    public GameBoard(YahtzeeFacade model) {
	super();

	this.model = model;
	this.setScene(createScene());
    }

    /**
     * Update the name displayed as current player.
     */
    public void updateCurrentPlayer() {
	footer.setText(model.getCurrentPlayerName() + " is playing.");
    }

    /**
     * Update the dice shown to the user.
     *
     * @param dice List of integers representing the values of the dice.
     */
    public void updateDice(List<Integer> dice) {
	diceLabel.setText(formatDiceString(dice));
    }

    private Scene createScene() {
	VBox rootPane = new VBox(spacing);
	rootPane.setAlignment(CENTER);

	rootPane.getChildren().add(createHeaderPane());
	rootPane.getChildren().add(createMainPane());
	rootPane.getChildren().add(createFooterPane(model.getCurrentPlayerName()));

	return new Scene(rootPane);
    }

    private Pane createHeaderPane() {
	HBox headerPane = new HBox(spacing);
	headerPane.setAlignment(CENTER);

	headerPane.getChildren().add(new Label("Yahtzee"));

	return headerPane;
    }

    private Pane createMainPane() {
	VBox mainPane = new VBox(spacing);
	mainPane.setPrefSize(500, 500);

	diceLabel = new Label("You haven't rolled any dice yet.");
	mainPane.getChildren().add(diceLabel);

	Button roll = new Button("roll remaining dice.");
	roll.setOnAction(event -> this.fireEvent(new GameBoardEvent(event, this, ROLL)));
	mainPane.getChildren().add(roll);

	return mainPane;
    }

    private Pane createFooterPane(String firstPlayer) {
	HBox footerPane = new HBox(spacing);
	footerPane.setAlignment(CENTER);

	footer = new Label(firstPlayer + " may start the game.");
	footerPane.getChildren().add(footer);

	return footerPane;
    }

    private String formatDiceString(List<Integer> dice) {
	String str = " ";

	for (Integer die : dice) {
	    str += die + " ";
	}

	return str;
    }
}
