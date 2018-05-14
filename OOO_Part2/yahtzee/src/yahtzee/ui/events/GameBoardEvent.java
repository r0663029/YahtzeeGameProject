package yahtzee.ui.events;

import javafx.event.Event;
import javafx.event.EventType;

import yahtzee.ui.GameBoard;

public class GameBoardEvent extends Event {

    public static final EventType<GameBoardEvent> YAHTZEE = new EventType<>("Yahtzee");
    public static final EventType<GameBoardEvent> ROLL = new EventType<>(YAHTZEE, "Roll");
    public static final EventType<GameBoardEvent> SET_ASIDE_DIE = new EventType<>(YAHTZEE, "Set aside a die");

    public GameBoardEvent(Event source, GameBoard target, EventType<GameBoardEvent> type) {
	super(source, target, type);
    }
}
