package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates a dialog that shows the user when the game is over
 * and allows them to return to the menu to start a new game
 */
public class JGameOverDialog extends JDialog {
    private JLabel label=new JLabel("GAME OVER!");
    private JButton button=new JButton("Return to menu");
    private GridLayout grid=new GridLayout(3,1,5,5);

    public JGameOverDialog(){
        setTitle("Game Over");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(250,250);
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(grid);

        add(label);
        add(button);
        button.addActionListener(GUISuperTrumpGame.GameOverListener);
    }
}
