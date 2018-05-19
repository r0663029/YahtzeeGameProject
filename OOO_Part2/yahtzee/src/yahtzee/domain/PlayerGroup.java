package yahtzee.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * The objects of this class remembers a group of players, pressumably playing
 * the same match, and who's turn it is.
 */
public class PlayerGroup {
	private final List<Player> players;
	private int currentPlayerIndex;

	public PlayerGroup() {
		players = new ArrayList<>();
		currentPlayerIndex = 0;
	}

	/**
	 * Add the given player to this PlayerGroup.
	 *
	 * @param player The player to be added.
	 */
	public void addPlayer(Player player) {
		if (players.size() == 0) {
			player.setActive(true);
		}
		players.add(player);
	}

	/**
	 * Return the player who's turn it is.
	 *
	 * @return The player who may make a move.
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public void switchToNextPlayer() {
		getCurrentPlayer().setActive(false);
		if (currentPlayerIndex < getPlayers().size() - 1) {
			currentPlayerIndex++;
		} else {
			currentPlayerIndex = 0;
		}
		getPlayers().get(currentPlayerIndex).setActive(true);
	}

	/**
	 * Check if this PlayerGroup contains a player with the given name.
	 *
	 * @param name The name of the player to look for.
	 * @return True if this PlayerGroup contains a player with the given name.
	 * False otherwise.
	 */
	public boolean contains(String name) {
		for (Player player : players) {
			if (player.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	private Player getHighestScoringPlayer() {
		Player highestPlayer = getPlayers().get(0);
		for (Player player : getPlayers()) {
			if (player.getScoreboard().getTotalScore()> highestPlayer.getScoreboard().getTotalScore()) {
				highestPlayer = player;
			}
		}
		return highestPlayer;
	}
	
	public String getHighScore() {
		return "Speler " + getHighestScoringPlayer().getName() + " heeft de hoogste score met een score van: " + getHighestScoringPlayer().getScoreboard().getTotalScore();
	}
}
