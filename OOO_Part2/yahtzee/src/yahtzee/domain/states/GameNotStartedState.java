package yahtzee.domain.states;

import yahtzee.domain.DomainException;
import yahtzee.domain.Player;
import yahtzee.domain.PlayerGroup;
import yahtzee.domain.YahtzeeFacade;

public class GameNotStartedState implements State {
	private YahtzeeFacade context;
	
	public GameNotStartedState(YahtzeeFacade context) {
		this.context = context;
	}
	
	@Override
	public boolean mayRoll() {
		return false;
	}

	@Override
	public boolean mayRegister() {
		return true;
	}

	@Override
	public void roll() {
		throw new DomainException("Player is not allowed to roll the dice at this state");
	}

	@Override
	public void registerPlayer(String name) {
		context.getPlayerGroup().addPlayer(new Player(name));
		context.setCurrentState(context.getRegisteringState());
	}

	@Override
	public void setAside(int dieValue) {
		throw new DomainException("Player is not allowed to set the dice aside at this state");
	}

	@Override
	public void setBack(int dieValue) {
		throw new DomainException("Player is not allowed to set back dice in this state");
	}

	@Override
	public void endTurn() {
		throw new DomainException("Player is not allowed to end his turn as there are not turns yet");
	}

	@Override
	public void nextGame() {
		throw new DomainException("Current game is not finished yet!");
	}
}
