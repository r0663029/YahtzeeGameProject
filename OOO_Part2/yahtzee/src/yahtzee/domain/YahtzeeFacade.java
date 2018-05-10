package yahtzee.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is a facade to the Yahtzee model.
 */
public class YahtzeeFacade {

    private final PlayerGroup playerGroup;
    private Turn turn;

    public YahtzeeFacade() {
	playerGroup = new PlayerGroup();
    }

    /**
     * Register the given player in an upcomming match.
     *
     * @param name The name of the player to register.
     */
    public void registerPlayer(String name) {
	playerGroup.addPlayer(new Player(name));
    }

    /**
     * Return the name of the player who's turn it is.
     *
     * @return The name of the player who may make a move.
     */
    public String getCurrentPlayerName() {
	return playerGroup.getCurrentPlayer().getName();
    }

    /**
     * Check wether the given name has already been registered.
     *
     * @param name The name of the player to look for.
     *
     * @return True if a player already registered with the given name.
     *         False otherwise.
     */
    public boolean playerAlreadyRegistered(String name) {
	return playerGroup.contains(name);
    }

    /**
     * Check wether more players may be registered.
     *
     * @return True if more players may be registered for this match.
     *         False otherwise.
     */
    public boolean mayRegister() {
	return true;
    }
    /**
    generates random integers based on how many spots there are still available.
    This is calculated by subtracting 5 minus the dices places aside.
    */
    public List<Integer> roll () {
        return turn.roll();
    }

    /**
     Set a list of dices aside chosen by the player
     */

    public void setAside(List<Integer> diceIndices) {
        turn.setAside(diceIndices);
    }

    public boolean mayRoll() {
        return turn.mayRoll();
    }
}
