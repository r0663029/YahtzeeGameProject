package yahtzee.domain.states;

import yahtzee.domain.DomainException;
import yahtzee.domain.YahtzeeFacade;

public class PlayingState implements State {
	private YahtzeeFacade context;
	private int counter;

	public PlayingState(YahtzeeFacade context) {
		this.context = context;
		counter = context.getPlayerGroup().getPlayers().size() * 13;
	}
	
	@Override
	public boolean mayRoll() {
		return context.getTurn().mayRoll();
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
	public void setBack(int dieValue) {
		context.getTurn().setBack(dieValue);
	}

	@Override
	public void endTurn() {
		System.out.println(counter);
		if(counter > 0) {
			context.switchToNextPlayer();
			counter--;
		}
		else {
			context.setCurrentState(new GameEndedState(context));
		}
	}

	@Override
	public void nextGame() {
		throw new DomainException("Current game is not finished yet!");
	}

	
}
