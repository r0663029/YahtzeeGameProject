package yahtzee.domain;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Integer> dicesAside = new ArrayList<Integer>(5);
    private List<Integer> dicesThrown;
    private int counterThrows;

    public Turn() {
    	counterThrows = 0;
    }

    public List<Integer> roll() throws DomainException {
        if (mayRoll()) {
            counterThrows++;
            dicesThrown = new ArrayList<>();
            for (int i = 0; i < (5 - dicesAside.size()); i++) {
                dicesThrown.add((int) (Math.random() * 6 + 1));
            }
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
        if (dicesAside.size()< 6 && counterThrows < 3) {
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
