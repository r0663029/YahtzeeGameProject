package yahtzee.domain;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Integer> diceAside;
	private List<Integer> diceThrown;
    private int counterThrows;

    public Turn() {
    	counterThrows = 0;
		diceThrown = new ArrayList<>(5);
		diceAside = new ArrayList<>(5);
    }

    public void roll() throws DomainException {
        if (mayRoll()) {
            counterThrows++;
            diceThrown = new ArrayList<>();
            for (int i = 0; i< (5 - getDicesAside().size()); i++) {
            	diceThrown.add(new Die().getEyes());
			}
            if (counterThrows == 3) {
            	diceAside.addAll(diceThrown);
            	diceThrown.clear();
			}
        }
        else {
            throw new DomainException("Player is not allowed to roll anymore");
        }
    }

    public void setAside(int dieValue) throws DomainException {
        if (diceAside.size() > 5 ) {
            throw new DomainException("Dices aside can't be larger than 5");
        }
        int die = diceThrown.remove(diceThrown.indexOf(dieValue));
        diceAside.add(die);
    }

    public List<Integer> getDicesAside() {
        return this.diceAside;
    }

    public List<Integer> getDicesThrown() {
        return this.diceThrown;
    }

    public boolean mayRoll() {
        if (diceAside.size() < 5 && counterThrows < 3) {
            return true;
        }
        return false;
    }
}
