package yahtzee.domain;

import java.util.ArrayList;
import java.util.List;

public class Throw {
	private List<Die> dicesThrown = new ArrayList<Die>();
	
	public Throw(int amount) {
		addDice(amount);
	}
	
	private void addDice(int amount) {
		for (int i = 0; i < amount; i++) {
			this.dicesThrown.add(new Die());
		}
	}
	
	public List<Integer> getValues() {
		List<Integer> dices = new ArrayList<>();
		for (int i = 0; i < this.dicesThrown.size(); i++) {
			dices.add(dicesThrown.get(i).getEyes());
		}
		return dices;
	}
}
