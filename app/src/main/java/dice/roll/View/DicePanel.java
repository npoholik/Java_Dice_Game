package dice.roll.View;

import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DicePanel extends JPanel {

    private JButton[] dieButtons;
    private JLabel[] dotLabels;
    ArrayList<ImageIcon> diceDB = new ArrayList<ImageIcon>();

    public DicePanel(ActionListener buttonClick) {
        this.initializeDiceDB();
        this.dieButtons = new JButton[3];
        this.dotLabels = new JLabel[3];
        this.setLayout(new GridLayout(2,3));
    
        for(int i = 0; i < 3; i++) {
            JLabel dots = new JLabel();
            dots.setBorder(BorderFactory.createEmptyBorder(0,20,10,10));
            dots.setPreferredSize(new Dimension(60,70));
            dotLabels[i] = dots;
            this.add(dots);
        }
        for(int j = 0; j < 3; j++) {
            JButton roll = new JButton("Roll Dice " + (j+1));
            roll.setFocusPainted(false);
            roll.addActionListener(buttonClick);
            dieButtons[j] = roll;
            this.add(roll);
        }
    }

    public void setLabel(int num, int dieVal) {
        dotLabels[num].setIcon(diceDB.get(dieVal));
    }

    public void disableButtons() {
        for (int i = 0; i < 3; i++) {
            dieButtons[i].setEnabled(false);
        }
    }

    public void enableButtons() {
        for (int i = 0; i < 3; i++) {
            dieButtons[i].setEnabled(true);
        }
    }
    
    public void initializeDiceDB() {
        this.loadDiceDB(new File("src/main/java/dice/roll/View/Images/diceone.png").getAbsolutePath());
        this.loadDiceDB(new File("src/main/java/dice/roll/View/Images/dicetwo.png").getAbsolutePath());
        this.loadDiceDB(new File("src/main/java/dice/roll/View/Images/dicethree.png").getAbsolutePath());
        this.loadDiceDB(new File("src/main/java/dice/roll/View/Images/dicefour.png").getAbsolutePath());
        this.loadDiceDB(new File("src/main/java/dice/roll/View/Images/dicefive.png").getAbsolutePath());
        this.loadDiceDB(new File("src/main/java/dice/roll/View/Images/dicesix.png").getAbsolutePath());
    }

    public void loadDiceDB(String filename) {
        ImageIcon diceIcon = new ImageIcon(filename);
        Image diceImage = diceIcon.getImage();
        Image diceScale = diceImage.getScaledInstance(60,60, java.awt.Image.SCALE_SMOOTH);
        diceIcon = new ImageIcon(diceScale);
        diceDB.add(diceIcon);
    }

}