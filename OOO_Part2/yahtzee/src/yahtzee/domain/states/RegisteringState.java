package yahtzee.domain.states;

import yahtzee.domain.DomainException;
import yahtzee.domain.Player;
import yahtzee.domain.YahtzeeFacade;

public class RegisteringState implements State {
	private YahtzeeFacade context;

	public RegisteringState(YahtzeeFacade context) {
		this.context = context;
	}
	
	@Override
	public boolean mayRoll() {
		return true;
	}

	@Override
	public boolean mayRegister() {
		return true;
	}

	@Override
	public void roll() {
		context.getTurn().roll();
		context.setCurrentState(context.getPlayingState());
	}

	@Override
	public void registerPlayer(String name) {
		context.getPlayerGroup().addPlayer(new Player(name));
	}

	@Override
	public void setAside(int dieValue) {
		throw new DomainException("Player is not allowed to set the dice aside at this state");
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
