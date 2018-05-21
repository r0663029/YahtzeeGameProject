package yahtzee.ui.events;

import javafx.event.Event;

import yahtzee.ui.GameBoard;
import static yahtzee.ui.events.GameBoardEvent.SET_ASIDE_DIE;

public class SetAsideDieEvent extends GameBoardEvent {

    private final int die;

    public SetAsideDieEvent(int die) {
	super(SET_ASIDE_DIE);
	this.die = die;
    }

    public int getDie() {
	return die;
    }
}
