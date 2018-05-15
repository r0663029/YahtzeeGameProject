package yahtzee.domain.score.lower;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

import java.util.List;

public class ThreeOfAKind implements ScoreCategory  {
	private static final String categoryName = "Three Of A Kind";
	private int score = -1;

	@Override
	public void inputDice(List<Integer> dice) {
		if (dice == null) {
			throw new DomainException("The array 'dice' was null.");
		}
		int diceLength = dice.size();
		if (diceLength != 5) {
			throw new DomainException("The array 'dice' is not 5 long.");
		}
		if (isValidThreeOfAKind(dice)) {
			setScore(calculateScore(dice));
		} else {
			setScore(0);
		}
	}
	
	public boolean isValidThreeOfAKind(List<Integer> dice) {
		int[] valueCounts = new int[6];
	    for (int i = 0; i < dice.size(); i++) {
	    		int value = dice.get(i);
	    		valueCounts[(value - 1)] += 1;
	    }
	    for (int i = 0; i < valueCounts.length; i++) {
	    		if (valueCounts[i] >= 3) {
	    			return true;
	    		}
	    }
	    return false;
	}
	  
	public int calculateScore(List<Integer> dice) {
		int result = 0;
		for (int i = 0; i < dice.size(); i++) {
			  int score = dice.get(i);
			  result += score;
		}
		return result;
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
		return this.categoryName;
	}


}
