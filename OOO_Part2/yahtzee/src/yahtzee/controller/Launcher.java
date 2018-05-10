package yahtzee.controller;

import java.util.Map;
import java.util.HashMap;
import javafx.application.Application;
import javafx.stage.Stage;

import yahtzee.domain.YahtzeeFacade;
import yahtzee.ui.RegisterUI;
import yahtzee.ui.GameBoard;

/**
 * This class launches the Yahtzee game.
 */
public class Launcher extends Application {

    private YahtzeeFacade yahtzee;
    private Map<String, GameBoard> boards;

    public static void main(String[] args) {
	Launcher launcher = new Launcher();
	launcher.launch();
    }

    public Launcher() {
	super();

	yahtzee = new YahtzeeFacade();
	boards = new HashMap<>();
    }

    /**
     * Start the game.
     */
    @Override
    public void start(Stage primaryStage) {
	RegisterUI registerUI = new RegisterUI();
	String name;
	GameBoard board;
	while(yahtzee.mayRegister()) {
	    do {
		name = registerUI.showAndWait();
	    } while(name == "" || yahtzee.playerAlreadyRegistered(name));
	    yahtzee.registerPlayer(name);

	    board = new GameBoard();
	    board.show();
	    boards.put(name, board);
	}
    }
}
