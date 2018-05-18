package yahtzee.domain.states;

public interface State {
	
	public boolean mayRoll();
	public boolean mayRegister();
	public void roll();
	public void registerPlayer(String name);
	public void setAside(int dieValue);
	public void endTurn();
	public void nextGame();
}
