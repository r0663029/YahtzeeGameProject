package yahtzee.domain.states;

import yahtzee.domain.DomainException;
import yahtzee.domain.YahtzeeFacade;

public class GameEndedState implements State {
	private YahtzeeFacade context;

	public GameEndedState(YahtzeeFacade context) {
		this.context = context;
	}
	
	@Override
	public boolean mayRoll() {
		return false;
	}

	@Override
	public boolean mayRegister() {
		return false;
	}

	@Override
	public void roll() {
		throw new DomainException("Player is not allowed to roll the dice once the game is over");
	}

	@Override
	public void registerPlayer(String name) {
		throw new DomainException("Players can't be registered anymore");
	}

	@Override
	public void setAside(int dieValue) {
		throw new DomainException("Player is not allowed to set the dice once the game is over");
	}

	@Override
	public void setBack(int dieValue) {
		throw new DomainException("Player is not allowed to set back dice in this state");
	}

	@Override
	public void endTurn() {
		throw new DomainException("Player is not allowed to end his turn as the game is over");
	}

	@Override
	public void nextGame() {
		context = new YahtzeeFacade();
		context.setCurrentState(context.getGameNotStartedState());
	}
	
}
