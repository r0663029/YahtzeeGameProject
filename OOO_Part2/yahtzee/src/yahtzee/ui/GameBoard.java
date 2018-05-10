package yahtzee.ui;

import static javafx.geometry.Pos.CENTER;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.Scene;

import yahtzee.domain.YahtzeeFacade;

/**
 * This class defines the main window the players interact with.
 *
 * The objects of this class show a particular player a game board, and an
 * indication of which player's turn it is.
 */
public class GameBoard extends Stage {

    private static final double spacing = 5;

    private final YahtzeeFacade model;

    private Label footer;

    public GameBoard(YahtzeeFacade model) {
	super();

	this.model = model;
	this.setScene(createScene());
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
	Pane mainPane = new Pane();
	mainPane.setPrefSize(500, 500);

	return mainPane;
    }

    private Pane createFooterPane(String firstPlayer) {
	HBox footerPane = new HBox(spacing);
	footerPane.setAlignment(CENTER);

	footer = new Label(firstPlayer + " may start the game.");
	footerPane.getChildren().add(footer);

	return footerPane;
    }

    /**
     * Update this GameBoard based the model given to the constructor.
     */
    public void update() {
	footer.setText(model.getCurrentPlayerName() + " is playing.");
    }
}
