package yahtzee.ui.events;

import javafx.event.Event;

import yahtzee.ui.GameBoard;
import static yahtzee.ui.events.GameBoardEvent.SET_ASIDE_DIE;

public class SetAsideDieEvent extends GameBoardEvent {

    private final int payload;

    public SetAsideDieEvent(Event source, GameBoard target, int payload) {
	super(source, target, SET_ASIDE_DIE);
	this.payload = payload;
    }

    public int getPayload() {
	return payload;
    }
}
