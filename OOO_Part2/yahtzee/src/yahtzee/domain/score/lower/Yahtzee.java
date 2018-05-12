package yahtzee.domain.score.lower;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

public class Yahtzee implements ScoreCategory {
	private static final String categoryname = "Yahtzee";
	private int score = -1;

	@Override
	public void inputdice(int[] dice) {
		if (dice == null) {
			throw new DomainException("The array 'dice' was null.");
		}
		int diceLength = dice.length;
		if (diceLength != 5) {
			throw new DomainException("The array 'dice' is not 5 long.");
		}
		if (isValidYahtzee(dice)) {
			setScore(50);
		} else {
			setScore(0);
		}
		//TODO bonus score
	}

	private boolean isValidYahtzee(int[] dice) {
		int[] valueCounts = new int[5];
	    for (int i = 0; i < dice.length; i++) {
	    		int value = dice[i];
	    		valueCounts[(value - 1)] += 1;
	    }
	    for (int i = 0; i < valueCounts.length; i++) {
	    		if (valueCounts[i] == 5) {
	    			return true;
	    		}
	    }
	    return false;
	}

	@Override
	public void setScore(int score) {
		if (score >= 0) {
		    this.score = score;
		} else {
			throw new DomainException("The score was smaller or equal to 0.");
		}
	}

	@Override
	public int getScore() {
		if (isUsed()) {
			return this.score;
		}
		return 0;
	}

	@Override
	public boolean isUsed() {
		if (this.score == -1) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getNameOfCategory() {
		return this.categoryname;
	}
}