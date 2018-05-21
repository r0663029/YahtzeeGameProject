package yahtzee.domain.score;

import java.util.List;

public interface ScoreCategory {
	public abstract void inputDice(List<Integer> dice);
	public abstract int getScore();
	public abstract boolean isUsed();
	public String getNameOfCategory();
	public int suggestScore(List<Integer> dice);
	
}
