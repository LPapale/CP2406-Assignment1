package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates a dialog that shows the user when the game is over
 * and allows them to return to the menu to start a new game
 */
public class JGameOverDialog extends JDialog {
    private JLabel label=new JLabel("GAME OVER!");
    private JButton button=new JButton("Return to menu");

    public JGameOverDialog(ArrayList winners){
        setTitle("Game Over");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(250,250);
        setResizable(false);
        setAlwaysOnTop(true);
        GridLayout grid=new GridLayout(winners.size()+2,1,5,5);
        setLayout(grid);

        add(label);
        //  Add winners labels
        JLabel winner1=new JLabel("The winner is "+winners.get(0));
        JLabel winner2=new JLabel("2nd place goes to "+winners.get(1));
        add(winner1);
        add(winner2);
        if(winners.size()>2) {
            JLabel winner=new JLabel("3rd place goes to " + winners.get(2));
            add(winner);
        }
        for(int i=3;i<winners.size();i++){
            JLabel winner=new JLabel(""+(i+1)+"th place goes to "+winners.get(i));
            add(winner);
        }
        add(button);
        button.addActionListener(GUISuperTrumpGame.GameOverListener);
    }
}
