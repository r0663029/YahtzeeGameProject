package yahtzee.domain;

import java.util.ArrayList;
import java.util.List;

public class Turn {
    private List<Integer> dicesAside = new ArrayList<Integer>(5);
    private List<Integer> dicesThrown;
    private int counter = 0;

    public Turn() {
    }

    public List<Integer> roll() throws DomainException {
        if (mayRoll()) {
            counter++;
            dicesThrown = new ArrayList<>();
            for (int i = 0; i < (5 - dicesAside.size()); i++) {
                dicesThrown.add((int) (Math.random() * 6 + 1));
            }
        }
        else {
            throw new DomainException("Player is not allowed to roll anymore");
        }
        return dicesThrown;
    }

    public void setAside(List<Integer> diceIndices) throws DomainException {
        if (diceIndices.size() + dicesAside.size() > 5 ) {
            throw new DomainException("Dices aside can't be larger than 5");
        }
        dicesAside.addAll(diceIndices);
    }

    public List<Integer> getDices() {
        return this.dicesThrown;
    }

    public boolean mayRoll() {
        if (dicesAside.size()< 6 && counter < 3) {
            return false;
        }
        return true;
    }
}
