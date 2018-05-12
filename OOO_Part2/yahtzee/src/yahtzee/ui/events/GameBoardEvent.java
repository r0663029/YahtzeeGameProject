package yahtzee.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

import yahtzee.ui.GameBoard;

public class GameBoardEvent extends Event {

    public static final EventType<GameBoardEvent> YAHTZEE = new EventType<>("Yahtzee");
    public static final EventType<GameBoardEvent> ROLL = new EventType<>(YAHTZEE, "Roll");

    public GameBoardEvent(Event source, GameBoard target, EventType<GameBoardEvent> type) {
	super(source, target, type);
    }
}
