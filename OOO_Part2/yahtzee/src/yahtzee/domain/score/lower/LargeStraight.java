package yahtzee.domain.score.lower;

import java.util.Arrays;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

public class LargeStraight implements ScoreCategory {
	private static final String categoryName = "Large Straight";
	private int score = -1;

	@Override
	public void inputDice(int[] dice) {
		if (dice == null) {
			throw new DomainException("The array 'dice' was null.");
		}
		int diceLength = dice.length;
		if (diceLength != 5) {
			throw new DomainException("The array 'dice' is not 5 long.");
		}
		if (isValidLargeStraight(dice)) {
			setScore(40);
		} else {
			setScore(0);
		}
	}

	private boolean isValidLargeStraight(int[] dice) {
		int count = 0;
		boolean valid = false;
		Arrays.sort(dice);
		for (int i = 0; i < dice.length - 1; i++) {
			if (dice[(i + 1)] == dice[i] + 1) {
				count++;
			} else {
				if (dice[(i + 1)] == dice[i]) {
	        		continue;
	        }
	        count = 0;
			}
			if (count == 4) {
				valid = true;
				break;
			}
		}
	    return valid;
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
		return this.categoryName;
	}

}
