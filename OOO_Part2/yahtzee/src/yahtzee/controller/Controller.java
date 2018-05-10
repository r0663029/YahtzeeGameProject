package yahtzee.controller;

import java.util.Map;
import java.util.HashMap;

import yahtzee.domain.YahtzeeFacade;
import yahtzee.ui.RegisterUI;
import yahtzee.ui.GameBoard;

/**
 * Controller of the Yahtzee game
 */
public class Controller {

    private YahtzeeFacade yahtzee;
    private Map<String, GameBoard> boards;

    public Controller() {
	yahtzee = new YahtzeeFacade();
	boards = new HashMap<>();
    }

    /**
     * Start the game
     */
    public void start() {
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
