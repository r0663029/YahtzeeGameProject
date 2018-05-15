package yahtzee.domain.score.lower;

import yahtzee.domain.DomainException;
import yahtzee.domain.score.ScoreCategory;

import java.util.List;

public class FullHouse implements ScoreCategory {
	private static final String categoryName = "Full House";
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
		if (isValidFullHouse(dice)) {
			setScore(25);
		} else {
			setScore(0);
		}
	}

	private boolean isValidFullHouse(List<Integer> dice) {
		int[] valueCounts = new int[6];
		for (int i=0; i< dice.size(); i++) {
			int value = dice.get(i);
    			valueCounts[(value - 1)] += 1;
		}
		boolean check2 = false;
		boolean check3 = false;
		for (int i: valueCounts) {
		    check2 |= (i==2); 
		    check3 |= (i==3); 
		}
		return (check2 && check3);
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
	public void setScore(int score) {
		if (score >= 0) {
		    this.score = score;
		} else {
			throw new DomainException("The score was smaller or equal to 0.");
		}	
	}
	
	@Override
	public String getNameOfCategory() {
		return this.categoryName;
	}

}
