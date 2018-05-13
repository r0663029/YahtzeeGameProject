package yahtzee.domain.score.upper;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

public class Twos implements ScoreCategory  {
	private static final String categoryName = "Twos";
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
		setScore(calculateScore(dice));
	}
	
	public int calculateScore(int[] dice){
		int result = 0;
		for (int i = 0; i < dice.length; i++) {
			int value = dice[i];
			if (value == 2) {
				result+=2;
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
