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


    public Player getCurrentPlayer() {
	return playerGroup.getCurrentPlayer();
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
        return playerGroup.getCurrentPlayer().getTurn().roll();
    }

    /**
     Set a list of dices aside chosen by the player
     */

    public void setAside(List<Integer> diceIndices) {
        turn.setAside(diceIndices);
    }

    /**
     Method to get a list of all the dices which have been placed aside
     */

    public List<Integer> getDiceAside() {
        return turn.getDicesAside();
    }

    /**
     Method to get a list of all the dices thrown
     */

    public List<Integer> getDiceThrown() {
        return turn.getDicesThrown();
    }

    /**
     Method to check if a person is allowed to roll the dice
     */

    public boolean mayRoll() {
        return playerGroup.getCurrentPlayer().getTurn().mayRoll();
    }

    /**
     Method used to transfer a chosen dice from the thrown dice list to the aside dice list
     */

    public void chooseDice(int diceId) {
        this.getCurrentPlayer().getTurn().chooseDie(diceId);
    }

    /**
     * Change the isActive status of a player
     */

    public void setActive() {
        getCurrentPlayer().setActive(true);
    }

    /**
     * Get the current playergroup
     */

    public PlayerGroup getPlayerGroup() {
        return playerGroup;
    }

}
