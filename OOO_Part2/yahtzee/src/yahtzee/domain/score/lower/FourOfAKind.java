package yahtzee.domain.score.lower;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

import java.util.List;

public class FourOfAKind implements ScoreCategory {
	private static final String categoryName = "Four Of A Kind";
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
	    		if (valueCounts[i] >= 4) {
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
	  
	private void setScore(int score) {
		if (score >= 0) {
		    this.score  = score;
		} else {
			throw new DomainException("The score was smaller or equal to 0.");
		}
	}

	@Override
	public int getScore() {
		return score;
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

	@Override
	public int suggestScore( List<Integer> dice) {
		return calculateScore(dice);
	}


}
