package yahtzee.controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class launches the Yahtzee game.
 */
public class Launcher extends Application {

    public static void main(String[] args) {
	Launcher launcher = new Launcher();
	launcher.launch();
    }

    public Launcher() {
	super();
    }

    /**
     * Launch the game.
     */
    @Override
    public void start(Stage primaryStage) {
        (new Controller()).start();
    }
}
