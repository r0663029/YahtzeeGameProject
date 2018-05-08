package yahtzee.domain;

import java.util.List;
import java.util.ArrayList;

public class Throw {
	private List<Dice> dices = new ArrayList<Dice>();
	private List<Integer> values = new ArrayList<Integer>();

	public Throw(int amount) {
		Dice dice = new Dice();
		for (int i = 0; i < amount; i++) {
			values.add(dice.roll());
			i++;
		}
	}
	
	public List<Integer> getValues() {
		return this.values;
	}

	public List<Dice> getDices() {
		return dices;
	}

	public void setDices(List<Dice> dices) {
		this.dices = dices;
	}
}
