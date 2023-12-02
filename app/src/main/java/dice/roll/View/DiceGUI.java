package dice.roll.View;

import dice.roll.ControllerInterface;
import dice.roll.GameObserver;
import dice.roll.Model.DiceLogic;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DiceGUI implements ActionListener, GameObserver {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton allRoll;
    private JLabel valueLabel;
    private DicePanel dicePanel;

    private ControllerInterface controller;
    private ArrayList<DiceLogic> threeDice;

    public static final int timeDelay = 100;
    protected Timer timer;
    private int iterations;
    private String choice;

    public DiceGUI(ControllerInterface controller, ArrayList<DiceLogic> threeDice) {
        this.controller = controller;
        this.threeDice = threeDice;

        for (DiceLogic obs: threeDice ) {
            obs.register(this);
        }

        this.mainFrame = new JFrame("Dice Roll Game :)");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(new Color(245,245,220));
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        this.dicePanel = new DicePanel(this);
        this.dicePanel.setOpaque(false);

        this.allRoll = new JButton("Roll All Dice");
        this.allRoll.setFocusPainted(false);
        this.allRoll.addActionListener(this);
        this.allRoll.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.valueLabel = new JLabel("Current Total: -");
        this.valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.mainPanel.add(dicePanel);
        this.mainPanel.add(allRoll);
        this.mainPanel.add(valueLabel);

        this.mainFrame.add(mainPanel);
        
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);

        timer = new Timer(timeDelay, this);
        timer.setRepeats(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof JButton) {
            JButton buttonPressed = (JButton)event.getSource();
            this.choice = buttonPressed.getText();

            this.dicePanel.disableButtons();
            this.allRoll.setEnabled(false);
            this.setClock();
        }
        else if (source instanceof Timer) {
            this.controller.userPressed(choice);
            iterations++;

            if (iterations > 10) {
                this.clearClock();
                this.dicePanel.enableButtons();
                this.allRoll.setEnabled(true);
            }
        }
        else {
            return;
        }
    }

    @Override
    public void update() {
        int sum = 0;
        for(int i = 0; i < 3; i++) {
            dicePanel.setLabel(i, this.threeDice.get(i).getNumericValue() -1);
            sum = sum + this.threeDice.get(i).getNumericValue();
        }
        valueLabel.setText("Current Total: " + sum);
    }

    private void setClock() {
        this.iterations = 0;
        this.timer.start();
    }

    private void clearClock() {
        timer.stop();
    }
}