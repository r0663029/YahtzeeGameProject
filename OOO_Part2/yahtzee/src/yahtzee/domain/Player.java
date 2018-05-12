package yahtzee.domain;

/**
 * This class represents a player.
 */
public class Player {
    private final String name;
    private Turn turn;

    /**
     * Create a new player with the given name.
     */
    public Player(String name) {

        this.name = name;
        this.turn = new Turn();
    }

    /**
     * Return the name of this player.
     *
     * @return the name of this player.
     */
    public String getName() {
	return name;
    }


    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
