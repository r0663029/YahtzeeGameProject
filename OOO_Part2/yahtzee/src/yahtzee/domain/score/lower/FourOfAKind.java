package yahtzee.domain.score.lower;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

public class FourOfAKind implements ScoreCategory {
	private static final String categoryname = "Four Of A Kind";
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
		if (isValidThreeOfAKind(dice)) {
			setScore(40);
		} else {
			setScore(0);
		}
	}
	
	public boolean isValidThreeOfAKind(int[] dice) {
		int[] valueCounts = new int[6];
	    for (int i = 0; i < dice.length; i++) {
	    		int value = dice[i];
	    		valueCounts[(value - 1)] += 1;
	    }
	    for (int i = 0; i < valueCounts.length; i++) {
	    		if (valueCounts[i] >= 4) {
	    			return true;
	    		}
	    }
	    return false;
	}
	  
	public int calculateScore(int[] dice) {
		int result = 0;
		for (int i = 0; i < dice.length; i++) {
			int score = dice[i];
			result += score;
		}
		return result;
	}
	  
	@Override
	public void setScore(int score) {
		if (score >= 0) {
		    this.score  = score;
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
