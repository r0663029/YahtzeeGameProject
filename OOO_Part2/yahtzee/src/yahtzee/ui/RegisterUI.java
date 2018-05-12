package yahtzee.ui;

import static javafx.stage.Modality.NONE;
import javafx.scene.control.TextInputDialog;
import java.util.function.Consumer;

/**
 * The objects of this class manage the user interaction necessary for the
 * registration of new players.
 */
public class RegisterUI {

    private final TextInputDialog dialog;

    public RegisterUI(Consumer<String> callback) {

	dialog = new TextInputDialog();
	dialog.initModality(NONE);
	dialog.setTitle("Register player");
	dialog.setHeaderText(null);
	dialog.setContentText("Please enter the player's name:");
	dialog.resultProperty()
	    .addListener((observable, oldValue, newValue) -> callback.accept(newValue));
    }

    /**
     * Show a dialog box asking the user for a name.
     *
     * Once the user has entered and confirmed a name, the callback function
     * (passed to the constructor) is called with the entered name as argument.
     */
    public void show() {
	dialog.show();
    }
}
