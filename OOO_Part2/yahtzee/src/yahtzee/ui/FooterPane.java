package yahtzee.ui;

import static javafx.geometry.Pos.CENTER;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import yahtzee.domain.YahtzeeFacade;

public class FooterPane extends VBox {
    private static final int spacing = 5;

    private YahtzeeFacade model;

    private Label footer;

    public FooterPane(YahtzeeFacade model) {
	super(spacing);
	this.setAlignment(CENTER);

	this.model = model;

	footer = new Label(model.getCurrentPlayerName() + " may start the game");
	this.getChildren().add(footer);
    }

    public void update() {
	footer.setText(model.getCurrentPlayerName() + " is playing.");
    }
}
