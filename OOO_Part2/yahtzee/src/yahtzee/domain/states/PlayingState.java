package yahtzee.domain.states;

import yahtzee.domain.DomainException;
import yahtzee.domain.Turn;
import yahtzee.domain.YahtzeeFacade;

public class PlayingState implements State {
	private YahtzeeFacade context;

	public PlayingState(YahtzeeFacade context) {
		this.context = context;
	}
	
	@Override
	public boolean mayRoll() {
		return true;
	}

	@Override
	public boolean mayRegister() {
		return false;
	}

	@Override
	public void roll() {
		context.getTurn().roll();
	}

	@Override
	public void registerPlayer(String name) {
		throw new DomainException("Players can't be registered anymore");
	}

	@Override
	public void setAside(int dieValue) {
		context.getTurn().setAside(dieValue);
	}

	@Override
	public void endTurn() {
		context.switchToNextPlayer();
	}

	@Override
	public void nextGame() {
		throw new DomainException("Current game is not finished yet!");
	}
}
