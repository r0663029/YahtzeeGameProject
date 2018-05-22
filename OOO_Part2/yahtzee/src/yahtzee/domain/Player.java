package yahtzee.domain;

/**
 * This class represents a player.
 */
public class Player {
    private final String name;
    private Scoreboard scoreboard;

    /**
     * Create a new player with the given name.
     */
    public Player(String name) {
        this.name = name;
        this.scoreboard = new Scoreboard();
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
     * Get the scoreboard for a specific player
     */

    public Scoreboard getScoreboard(){ return this.scoreboard;}

    @Override
    public String toString() { return getName();}
}


