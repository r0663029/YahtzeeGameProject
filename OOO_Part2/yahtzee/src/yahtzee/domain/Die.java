package yahtzee.domain;

public class Die {
	
	private int eyes;
	
	public Die() {
		roll();
	}
	
	private int roll() {
		this.eyes = (int) (Math.random() * 6 + 1);
		return eyes;
	}
	
	public int getEyes() {
		return this.eyes;
	}
	
	
}
