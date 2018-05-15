package yahtzee.domain.score.upper;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

import java.util.List;

public class Sixes implements ScoreCategory  {
	private static final String categoryName = "Sixes";
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
		setScore(calculateScore(dice));

	}
	
	public int calculateScore(List<Integer> dice) {
		int result = 0;
		for (int i = 0; i < dice.size(); i++) {
			int value = dice.get(i);
			if (value == 6) {
				result+=6;
			}
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
