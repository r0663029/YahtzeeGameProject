package yahtzee.ui;

import java.util.Collection;

import static javafx.geometry.Pos.CENTER;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class PlayerStage extends Stage {
    private static final int spacing = 5;

    public PlayerStage(Collection<Node> widgets) {
	super();

	VBox headerPane = new VBox(spacing);
	headerPane.setAlignment(CENTER);
	headerPane.getChildren().add(new Label("Yahtzee"));

	VBox rootPane = new VBox(spacing);
	rootPane.getChildren().add(headerPane);
	rootPane.getChildren().addAll(widgets);

	this.setScene(new Scene(rootPane));
    }
}
