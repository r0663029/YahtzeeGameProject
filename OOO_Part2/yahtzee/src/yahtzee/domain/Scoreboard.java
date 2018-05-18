package yahtzee.domain;

import yahtzee.domain.score.ScoreCategory;
import yahtzee.domain.score.lower.*;
import yahtzee.domain.score.upper.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, Integer> getCategoriesAsMap() {
		Map<String, Integer> categoryMap = new LinkedHashMap<>();
		for (int i = 0; i < getCategoryList().size(); i++) {
			
			categoryMap.put(getCategoryList().get(i).getNameOfCategory(), getCategoryList().get(i).getScore());
		}
		return categoryMap;
	}
	
	


}
