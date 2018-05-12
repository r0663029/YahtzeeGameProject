package yahtzee.domain;

/**
 * This class represents a player.
 */
public class Player {
    private final String name;
    private Turn turn;
    private boolean isActive;

    /**
     * Create a new player with the given name.
     */
    public Player(String name) {

        this.name = name;
        this.turn = new Turn();
        isActive = false;
    }

    /**
     * Return the name of this player.
     *
     * @return the name of this player.
     */
    public String getName() {
	return name;
    }

    /**
     * get the turn object of a player
     */

    public Turn getTurn() {
        return turn;
    }

    /**
     * Set the turn object of a player.
     */

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * get the status of the isActive boolean.
     */

    public boolean getActive() {
        return isActive;
    }

    /**
     * Change the isActive boolean
     */

    public void setActive(boolean mayRoll) {
        this.isActive = mayRoll;
    }
}
