package yahtzee.domain;

/**
 * This class is a facade to the Yahtzee model.
 */
public class YahtzeeFacade {

    private final PlayerGroup playerGroup;

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
}
