package yahtzee.domain.score;

public interface ScoreCategory {
	public abstract void inputDice(int[] dice);
	
	public abstract void setScore(int score);
	
	public abstract int getScore();
	
	public abstract boolean isUsed();
	
	public String getNameOfCategory();
	
}
