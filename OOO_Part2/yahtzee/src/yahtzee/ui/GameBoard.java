package yahtzee.ui;

import static javafx.geometry.Pos.CENTER;

import java.util.List;
import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import yahtzee.domain.YahtzeeFacade;

import yahtzee.ui.events.GameBoardEvent;
import yahtzee.ui.events.SetAsideDieEvent;
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

    private VBox dicePanel;
    private Label noDiceRolledMessage;
    private ObservableList<Node> diceThrown;
    private ObservableList<Node> diceAside;
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
     * @param diceThrown Collection of integers representing the values of the
     * dice most recently thrown.
     * @param diceAside Collection of integers representing the values of the
     * dice set aside so far.
     */
    public void updateDice(Collection<Integer> diceThrown, Collection<Integer> diceAside) {
	this.diceThrown.clear();
	this.diceAside.clear();

	for (int die : diceThrown) {
	    this.diceThrown.add(createDice(die, true));
	}
	for (int die : diceAside) {
	    this.diceAside.add(createDice(die, false));
	}
    }

    private DieButton createDice(int numberOfEyes, boolean active) {
	DieButton die = new DieButton(numberOfEyes);

	if (active) {
	    die.setOnAction(this::setAsideDieHandler);
	} else {
	    die.setDisable(true);
	}

	return die;
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

	mainPane.getChildren().add(createDiceDisplay());

	Button roll = new Button("roll remaining dice.");
	roll.setOnAction(this::rollHandler);
	mainPane.getChildren().add(roll);

	return mainPane;
    }

    private void rollHandler(Event event) {
	noDiceRolledMessage.setVisible(false);
	dicePanel.setVisible(true);

	this.fireEvent(new GameBoardEvent(event, this, ROLL));
    }

    private Pane createDiceDisplay() {
	Label diceThrownLabel = new Label("Dice thrown:");
	HBox diceThrownPanel = new HBox(spacing);
	diceThrown = diceThrownPanel.getChildren();

	Label diceAsideLabel = new Label("Dice aside:");
	HBox diceAsidePanel = new HBox(spacing);
	diceAside = diceAsidePanel.getChildren();

        dicePanel = new VBox(spacing);
	dicePanel.getChildren().add(diceThrownLabel);
	dicePanel.getChildren().add(diceThrownPanel);
	dicePanel.getChildren().add(diceAsideLabel);
	dicePanel.getChildren().add(diceAsidePanel);
        dicePanel.setVisible(false);

	noDiceRolledMessage = new Label("You haven't rolled any dice yet.");

	VBox diceDisplay = new VBox(spacing);
	diceDisplay.getChildren().add(noDiceRolledMessage);
	diceDisplay.getChildren().add(dicePanel);

	return diceDisplay;
    }

    private void setAsideDieHandler(Event event) {
	DieButton targetDieButton = (DieButton) event.getTarget();
	int numberOfEyes = targetDieButton.getNumberOfEyes();
	this.fireEvent(new SetAsideDieEvent(event, this, numberOfEyes));
    }

    private Pane createFooterPane(String firstPlayer) {
	HBox footerPane = new HBox(spacing);
	footerPane.setAlignment(CENTER);

	footer = new Label(firstPlayer + " may start the game.");
	footerPane.getChildren().add(footer);

	return footerPane;
    }
}
