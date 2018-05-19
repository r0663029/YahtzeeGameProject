package yahtzee.domain;

import yahtzee.domain.score.ScoreCategory;
import yahtzee.domain.states.*;

import java.util.*;

/**
 * This class is a facade to the Yahtzee model.
 */
public class YahtzeeFacade {

	private final PlayerGroup playerGroup;
	private Turn turn;
	private State registeringState;
	private State gameNotStartedState;
	private State currentState;
	private State playingState;
	private State gameEndedState;

	public YahtzeeFacade() {
		playerGroup = new PlayerGroup();
		turn = new Turn();
		gameNotStartedState = new GameNotStartedState(this);
		registeringState = new RegisteringState(this);
		playingState =  new PlayingState(this);
		gameEndedState = new GameEndedState(this);
		currentState = gameNotStartedState;
	}

	/**
	 * Register the given player in an upcoming match.
	 *
	 * @param name The name of the player to register.
	 */
	public void registerPlayer(String name) {
		getCurrentState().registerPlayer(name);
	}

	/**
	 * Return the name of the current player.
	 *
	 * @return The name of the current player.
	 */
	public String getCurrentPlayerName() {
		return playerGroup.getCurrentPlayer().getName();
	}

	/**
	 * Return the current player.
	 *
	 * @return the current player.
	 */

	private Player getCurrentPlayer() {
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
	 * Method to check if registering a new player is allowed
	 *
	 * @return boolean to allow registeren.
	 */

	public boolean mayRegister() {
		return getCurrentState().mayRegister();
	}

	/**
	 * rolls random dice
	 */
	public void roll() {
		getCurrentState().roll();
	}

	/**
	 * @param dieValue representing the eyes of a single die.
	 */

	public void setAside(int dieValue) {
		getCurrentState().setAside(dieValue);
	}

	/**
	 * @return List of integers representing all the dice placed aside
	 */

	public List<Integer> getDiceAside() {
		return getTurn().getDicesAside();
	}

	/**
	 * @return List of integers representing all the dice thrown
	 */

	public List<Integer> getDiceThrown() {
		return getTurn().getDicesThrown();
	}

	/**
	 * Method to check if a person is allowed to roll the dice
	 *
	 * @return boolean to allow rolling.
	 */

	public boolean mayRoll() {
		return getCurrentState().mayRoll();
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

	private List<Player> getRegisteredPlayers() {
		return getPlayerGroup().getPlayers();
	}

	/**
	 * Get the Scoreboard for the current player.
	 *
	 * @return Scoreboard of the current player.
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
	 * 
	 * @return Map with the name of the category and its score per player
	 */

	public Map<String, Integer> getCategoriesAsMap() {
		return getScoreboard().getCategoriesAsMap();
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
	
	public void switchToNextPlayer() {
		getPlayerGroup().switchToNextPlayer();
		resetTurn();
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(State state) {
		currentState = state;
	}

	public Turn getTurn() {
		return turn;
	}

	private void resetTurn() {
		this.turn = new Turn();
	}

	public State getRegisteringState() {
		return registeringState;
	}

	public State getGameNotStartedState() {
		return gameNotStartedState;
	}

	public State getPlayingState() {
		return playingState;
	}

	public State getGameEndedState() {
		return gameEndedState;
	}
	
	public void endTurn() {
		getCurrentState().endTurn();
	}
}