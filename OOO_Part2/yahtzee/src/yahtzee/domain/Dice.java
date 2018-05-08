package yahtzee.domain;

public class Dice {
	private int eyes;
	
	public Dice() {};
	
	public int roll() {
		return (int) (Math.random() *6 +1);
	}
		
	public int getEyes() {
		return this.eyes;
	}

}
