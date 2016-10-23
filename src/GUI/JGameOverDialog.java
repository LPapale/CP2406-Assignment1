package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 23/10/2016.
 */
public class JGameOverDialog extends JDialog {
    private JLabel label=new JLabel("GAME OVER!");

    private JButton button=new JButton("Return to menu");
    private GridLayout grid=new GridLayout(3,1,5,5);



    public JGameOverDialog(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setLayout(grid);
        setSize(250,250);


        add(label);

        add(button);
        button.addActionListener(GUISuperTrumpGame.GameOverListener);
    }
}
