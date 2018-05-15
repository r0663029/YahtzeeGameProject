package yahtzee.domain;

import yahtzee.domain.score.ScoreCategory;

import java.util.*;

/**
 * This class is a facade to the Yahtzee model.
 */
public class YahtzeeFacade {

	private final PlayerGroup playerGroup;
	private boolean gameStarted = false;

	public YahtzeeFacade() {
		playerGroup = new PlayerGroup();
	}

	/**
	 * Register the given player in an upcoming match.
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
	 * @return True if a player already registered with the given name.
	 * False otherwise.
	 */
	public boolean playerAlreadyRegistered(String name) {
		return playerGroup.contains(name);
	}

	/**
	 * Check wether more players may be registered.
	 *
	 * @return True if more players may be registered for this match.
	 * False otherwise.
	 */
	public boolean mayRegister() {
		if (!getGameStarted()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return List of random integers based on how many spots there are still available.
	 * This is calculated by subtracting 5 minus the dices places aside.
	 */
	public List<Integer> roll() {
		setGameStarted(true);
		return playerGroup.getCurrentPlayer().getTurn().roll();
	}

	/**
	 * @return Turn object for the current active player
	 */
	public Turn getTurnOfCurrentPlayer() {
		return playerGroup.getCurrentPlayer().getTurn();
	}

	/**
	 * @param dieValue representing the index in de list of thrown dice.
	 */

	public void setAside(int dieValue) {
		getTurnOfCurrentPlayer().setAside(dieValue);
	}

	/**
	 * @return List of integers representing all the dices placed aside
	 */

	public List<Integer> getDiceAside() {
		return getTurnOfCurrentPlayer().getDicesAside();
	}

	/**
	 * @return List of integers representing all the dices thrown
	 */

	public List<Integer> getDiceThrown() {
		return getTurnOfCurrentPlayer().getDicesThrown();
	}

	/**
	 * Method to check if a person is allowed to roll the dice
	 *
	 * @return true if a player may roll. False if not.
	 */

	public boolean mayRoll() {
		return playerGroup.getCurrentPlayer().getTurn().mayRoll();
	}

	/**
	 * Change the isActive status of a player
	 */

	public void setActive() {
		getCurrentPlayer().setActive(true);
	}

	/**
	 * Get the current playerGroup
	 *
	 * @return PlayerGroup consisting of all the players
	 */

	public PlayerGroup getPlayerGroup() {
		return playerGroup;
	}

	/**
	 * @return List of all the registered players in the playerGroup
	 */

	public List<Player> getRegisteredPlayers() {
		return getPlayerGroup().getPlayers();
	}

	/**
	 * Get the Scoreboard for the current player.
	 *
	 * @return PlayerGroup consisting of all the players
	 */

	public Scoreboard getScoreboard() {
		return getCurrentPlayer().getScoreboard();
	}

	/**
	 * Get the CategoryList for the current player.
	 *
	 * @return List<ScoreCategory> for all the categories for the current player.
	 */

	public List<ScoreCategory> getCategoryList() {
		return getCurrentPlayer().getScoreboard().getCategoryList();
	}

	/**
	 * Get the CategoryList for the current player .
	 *
	 * @return PlayerGroup consisting of all the categories as Strings
	 */

	public List<String> getCategoryListStrings() {
		return getCurrentPlayer().getScoreboard().getCategoryListAsString();
	}

	/**
	 * Get the Scoreboard for the current player.
	 *
	 * @param categoryName name of the category. Example: Three Of A Kind
	 * @return a specific ScoreCategory for the current player based on a String
	 */

	public ScoreCategory getCategory(String categoryName) {
		return getCurrentPlayer().getScoreboard().getCategory(categoryName);
	}

	/**
	 * Get the score for a specific category based on the name of the category.
	 *
	 * @param categoryName Name of the category
	 * @return integer of the current score for the chosen category.
	 */

	public int getScoreForCategory(String categoryName) {
		ScoreCategory category = getCategory(categoryName);
		return category.getScore();
	}

	/**
	 * Set the score of a specific category based on the dice aside.
	 *
	 * @param categoryName of the category needed to define the score of the dice. example: Three Of A Kind
	 */
	public void setScore( String categoryName) {
		getScoreboard().setScore(categoryName, getDiceAside());
	}

	/**
	 * Changes the current active player to the next player in the PlayerGroup List
	 * Sets the isActive boolean to true for the current player
	 * Increases the currentPlayerIndex of the playergroup to loop through all the different players. If the end of the list is reached
	 * resets the index to 0.
	 * Creates a new Turn object for the new active player
	 */
	
	public Map<String, Integer> getCategoriesAsMap() {
		Map<String, Integer> categoryMap = new LinkedHashMap<>();
		for (int i = 0; i < getCategoryList().size(); i++) {
			categoryMap.put(getCategoryListStrings().get(i), getCategoryList().get(i).getScore());
		}
		return categoryMap;
	}
	
	public void switchToNextPlayer() {
		getPlayerGroup().switchToNextPlayer();
	}

	public boolean getGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
}
