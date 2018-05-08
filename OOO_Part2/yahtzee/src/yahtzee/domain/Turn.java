package yahtzee.domain;
import java.util.List;
import java.util.ArrayList;

public class Turn {
	private List<Integer> saved = new ArrayList<Integer>();
	
	public Turn() {};
	
	public void addSaved(int number) {
		this.saved.add(number);
	}
	
	public int getFreeSpots() {
		return 5 - (saved.size());
	}
}
