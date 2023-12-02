package dice.roll.Controller;

import dice.roll.ControllerInterface;
import dice.roll.GameObserver;

import dice.roll.View.DiceGUI;
import dice.roll.Model.DiceLogic;

import java.util.ArrayList;

public class DiceController implements ControllerInterface{
    private ArrayList<DiceLogic> logics = new ArrayList<DiceLogic>();
    private GameObserver view;

    public DiceController() {
        for (int i = 0; i < 3; i++) {
            DiceLogic instance = new DiceLogic();
            logics.add(instance);
        }
        this.view = new DiceGUI(this,logics);
        for (int i = 0; i < 3; i++) {
            logics.get(i).randomlyRoll();
        }
    }

    @Override
    public void userPressed(String command) {
        if (command.equals("Roll All Dice")) {
            for (int i = 0; i < 3; i++) {
                logics.get(i).randomlyRoll();
            }
        }
        else {
            int dieNum = (Integer.parseInt(command.substring(command.length()-1))) - 1;
            logics.get(dieNum).randomlyRoll();
        }
    }
}
