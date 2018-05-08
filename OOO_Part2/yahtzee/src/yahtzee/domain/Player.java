package yahtzee.domain;

/**
 * This class represents a player.
 */
public class Player {
    private final String name;

    /**
     * Create a new player with the given name.
     */
    public Player(String name) {
	this.name = name;
    }

    /**
     * Return the name of this player.
     *
     * @return the name of this player.
     */
    public String getName() {
	return name;
    }
}
