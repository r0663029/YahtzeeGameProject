package yahtzee.controller;

import java.util.Map;
import java.util.HashMap;
import javafx.event.Event;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;

import yahtzee.domain.YahtzeeFacade;
import yahtzee.ui.RegisterUI;
import yahtzee.ui.GameBoard;
import yahtzee.ui.events.SetAsideDieEvent;
import yahtzee.ui.events.ChooseCategoryEvent;
import static yahtzee.ui.events.GameBoardEvent.YAHTZEE;
import static yahtzee.ui.events.GameBoardEvent.ROLL;
import static yahtzee.ui.events.GameBoardEvent.SET_ASIDE_DIE;
import static yahtzee.ui.events.GameBoardEvent.CHOOSE_CATEGORY;

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
	if ( ! yahtzee.mayRegister()) {
	    // Request is invalid.
	    Alert error = new Alert(ERROR);
	    error.setTitle("Registry failed");
	    error.setHeaderText(null);
	    error.setContentText("Oeps, the game is no longer open for registry!");

	    error.show();
	} else {
	    if ( ! name.equals("") && ! yahtzee.playerAlreadyRegistered(name)) {
		yahtzee.registerPlayer(name);

		createBoard(name);
	    }

	    if (yahtzee.mayRegister()) {
		(new RegisterUI(this::registerCallback)).show();
	    }
	}
    }

    private void createBoard(String name) {
	GameBoard board = new GameBoard(yahtzee);
	board.addEventHandler(YAHTZEE, this::handleUpdates);
	board.addEventHandler(ROLL, this::handleRollRequest);
	board.addEventHandler(SET_ASIDE_DIE, this::handleSetAsideDieRequest);
	board.addEventHandler(CHOOSE_CATEGORY, this::handleChooseCategory);
	board.show();
	boards.put(board, name);
    }

    private void handleUpdates(Event event) {
	((GameBoard)event.getTarget()).update();
    }

    private void handleRollRequest(Event event) {
	yahtzee.roll();
    }

    private void handleSetAsideDieRequest(Event event) {
	yahtzee.setAside(((SetAsideDieEvent)event).getPayload());
    }

    private void handleChooseCategory(Event event) {
	yahtzee.setScore(((ChooseCategoryEvent)event).getCategory());
    }
}
