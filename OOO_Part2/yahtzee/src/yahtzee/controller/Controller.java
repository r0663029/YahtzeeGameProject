package yahtzee.controller;

import java.util.Map;
import java.util.HashMap;
import javafx.event.Event;

import yahtzee.domain.YahtzeeFacade;
import yahtzee.ui.RegisterUI;
import yahtzee.ui.GameBoard;
import static yahtzee.ui.events.GameBoardEvent.ROLL;

/**
 * Controller of the Yahtzee game
 */
public class Controller {

    private YahtzeeFacade yahtzee;
    private Map<GameBoard, String> boards;

    public Controller() {
	yahtzee = new YahtzeeFacade();
	boards = new HashMap<>();
    }

    /**
     * Start the game
     */
    public void start() {
	(new RegisterUI(this::registerCallback)).show();
    }

    private void registerCallback(String name) {
	if ( ! name.equals("") && ! yahtzee.playerAlreadyRegistered(name)) {
	    yahtzee.registerPlayer(name);

	    createBoard(name);
	}

	if (yahtzee.mayRegister()) {
	    (new RegisterUI(this::registerCallback)).show();
	}
    }

    private void createBoard(String name) {
	GameBoard board = new GameBoard(yahtzee);
	board.addEventHandler(ROLL, this::handleRollRequest);
	board.show();
	boards.put(board, name);
    }

    private void handleRollRequest(Event event) {
	((GameBoard)event.getTarget()).updateDice(yahtzee.roll(), yahtzee.getDiceAside());
    }
}
