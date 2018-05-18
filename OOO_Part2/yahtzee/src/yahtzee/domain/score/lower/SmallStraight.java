package yahtzee.domain.score.lower;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

public class SmallStraight implements ScoreCategory {
	private static final String categoryName = "Small Straight";
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
		if (isValidSmallStraight(dice)) {
			setScore(30);
		} else {
			setScore(0);
		}
	}

	private boolean isValidSmallStraight(List<Integer> dice) {
		int count = 0;
		boolean valid = false;
		Collections.sort(dice);
		for (int i = 0; i < dice.size() - 1; i++) {
			if (dice.get(i+1) == dice.get(i+1)) {
				count++;
			} else {
				if (dice.get(i+1) == dice.get(i)) {
	        		continue;
	        }
	        count = 0;
			}
			if (count == 3) {
				valid = true;
				break;
			}
		}
	    return valid;
	}

	private void setScore(int score) {
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
