package yahtzee.ui;

import static javafx.geometry.Pos.CENTER;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.Scene;

/**
 * This class defines the main window the players interact with.
 *
 * The objects of this class show a particular player a game board, and an
 * indication of which player's turn it is.
 */
public class GameBoard extends Stage {

    private static final double spacing = 5;
    private String currentPlayerName;

    private Label footer;

    public GameBoard() {
	super();

	VBox rootPane = new VBox(spacing);
	rootPane.setAlignment(CENTER);

	HBox headerPane = new HBox(spacing);
	headerPane.setAlignment(CENTER);
	rootPane.getChildren().add(headerPane);

	Pane mainPane = new Pane();
	mainPane.setPrefSize(500, 500);
	rootPane.getChildren().add(mainPane);

	HBox footerPane = new HBox(spacing);
	footerPane.setAlignment(CENTER);
	rootPane.getChildren().add(footerPane);

	Label header = new Label("Yahtzee");
	footer = new Label("The game will start after all players have registered.");


	headerPane.getChildren().add(header);
	footerPane.getChildren().add(footer);

	Scene scene = new Scene(rootPane);
	this.setScene(scene);
    }

    /**
     * Change who's turn it is.
     *
     * @param currentPlayerName The name to be displayed as current player.
     */
    public void setCurrentPlayer(String currentPlayerName) {
	this.currentPlayerName = currentPlayerName;
	footer.setText(currentPlayerName + " is playing.");
    }
}
