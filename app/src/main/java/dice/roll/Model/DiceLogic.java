package dice.roll.Model;

import dice.roll.GameObserver;

import java.util.ArrayList;
import java.util.Random;


public class DiceLogic {
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private int numericVal;

    public DiceLogic() {
        numericVal = 1;
    }

    public void register(GameObserver obs) {
        observers.add(obs);
    }

    public void unregister(GameObserver obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for(GameObserver obs : observers) {
            obs.update();
        }
    }

    public void randomlyRoll() {
        Random rnd = new Random();
        this.numericVal = rnd.nextInt(6)+1;
        this.notifyObservers();
    }

    public int getNumericValue() {
        return numericVal;
    }
}
