package yahtzee.ui.events;

import javafx.event.Event;

import yahtzee.ui.GameBoard;
import static yahtzee.ui.events.GameBoardEvent.CHOOSE_CATEGORY;

public class ChooseCategoryEvent extends GameBoardEvent {

    private final String category;

    public ChooseCategoryEvent(String category) {
	super(CHOOSE_CATEGORY);
	this.category = category;
    }

    public String getCategory() {
	return category;
    }
}
