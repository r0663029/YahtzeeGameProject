package yahtzee.domain;

import yahtzee.domain.score.ScoreCategory;
import yahtzee.domain.score.lower.*;
import yahtzee.domain.score.upper.*;

import java.util.*;

public class Scoreboard {
	private List<ScoreCategory> categoryList = new ArrayList<>();

	public Scoreboard() {
		categoryList.add(new Aces());
		categoryList.add(new Twos());
		categoryList.add(new Threes());
		categoryList.add(new Fours());
		categoryList.add(new Fives());
		categoryList.add(new Sixes());
		categoryList.add(new Chance());
		categoryList.add(new ThreeOfAKind());
		categoryList.add(new FourOfAKind());
		categoryList.add(new SmallStraight());
		categoryList.add(new LargeStraight());
		categoryList.add(new FullHouse());
		categoryList.add(new Yahtzee());
	}

	public List<ScoreCategory> getCategoryList () {
		return this.categoryList;
	}
	
	public List<String> getCategoryListAsString () {
		List<String> categoryStrings = new ArrayList<String>();
		for (ScoreCategory category: categoryList )  {
			if (!category.isUsed()) {
				categoryStrings.add(category.getNameOfCategory());
			}
		}
		return categoryStrings;
	}

	private Map<String, Integer> getCategoriesAsMapNoTotals() {
		Map<String, Integer> categoryMap = new LinkedHashMap<>();
		for (int i = 0; i < getCategoryList().size(); i++) {
			categoryMap.put(getCategoryList().get(i).getNameOfCategory(), getCategoryList().get(i).getScore());
		}
		return categoryMap;
	}
	
	public Map<String, Integer> getCategoriesAsMap() {
		Map<String, Integer> categoryMap = this.getCategoriesAsMapNoTotals();
		categoryMap.put("Lower section total: ", this.getLowerScore());
		categoryMap.put("upper section total: ", this.getUpperScore());
		categoryMap.put("Grand total: ", this.getTotalScore());
		return categoryMap;
	}

	public ScoreCategory getCategory(String categoryName) {
		for (ScoreCategory category: categoryList )  {
			if (categoryName.equals(category.getNameOfCategory())) {
				return category;
			}
		}
		return null;
	}
	
	public void setScore(String categoryName, List<Integer> dice) {
		ScoreCategory category = getCategory(categoryName);
		category.inputDice(dice);
	}
	
	public int getTotalScore() {
		int score = 0;
		for (Map.Entry<String, Integer> entry : getCategoriesAsMapNoTotals().entrySet()) {
			score += entry.getValue();
		}
		return score;
	}
	
	private int getLowerScore() {
		int score = 0;
		List<Integer> scores = new ArrayList<>(getCategoriesAsMapNoTotals().values());
		for (int i = 0; i < 5; i++) {
			score += scores.get(i);
		}
		return score;
	}
	
	private int getUpperScore() {
		return getTotalScore()-getLowerScore();
	}
}
