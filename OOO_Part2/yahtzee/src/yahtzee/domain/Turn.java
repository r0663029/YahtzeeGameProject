package yahtzee.domain;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Integer> dicesAside;
	private List<Integer> dicesThrown;
    private int counterThrows;

    public Turn() {
    	counterThrows = 0;
		dicesThrown = new ArrayList<>(5);
		dicesAside = new ArrayList<Integer>(5);
    }

    public List<Integer> roll() throws DomainException {
        if (mayRoll()) {
            counterThrows++;
            dicesThrown = new ArrayList<>();
			Throw lastThrow = new Throw(5 - getDicesAside().size());
			dicesThrown = lastThrow.getValues();
            if (counterThrows == 3) {
            	dicesAside.addAll(dicesThrown);
            	dicesThrown.clear();
			}
        }
        else {
            throw new DomainException("Player is not allowed to roll anymore");
        }
        return dicesThrown;
    }

    public void setAside(int dieValue) throws DomainException {
        if (dicesAside.size() > 5 ) {
            throw new DomainException("Dices aside can't be larger than 5");
        }
        int die = dicesThrown.remove(dicesThrown.indexOf(dieValue));
        dicesAside.add(die);
    }

    public List<Integer> getDicesAside() {
        return this.dicesAside;
    }

    public List<Integer> getDicesThrown() {
        return this.dicesThrown;
    }

    public boolean mayRoll() {
        if (dicesAside.size() < 5 && counterThrows < 3) {
            return true;
        }
        return false;
    }

    public int getCounterThrows() {
        return counterThrows;
    }

    public void setCounterThrows(int counterThrows) {
        this.counterThrows = counterThrows;
    }
}
