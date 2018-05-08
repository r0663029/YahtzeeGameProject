package yahtzee.ui;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * The objects of this class manage the user interaction necessary for the
 * registration of new players.
 */
public class RegisterUI {

    /**
     * Show a dialog box asking the user for a name.
     *
     * @return The name provided by the user, or "", if none was provided.
     */
    public String showAndWait() {
	TextInputDialog dialog = new TextInputDialog();
	dialog.setTitle("Register player");
	dialog.setHeaderText(null);
	dialog.setContentText("Please enter the player's name:");

	Optional<String> result = dialog.showAndWait();
	if (result.isPresent()){
	    return result.get();
	}

	return result.get();
    }
}
