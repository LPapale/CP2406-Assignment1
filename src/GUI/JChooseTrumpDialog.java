package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates dialog that allows the user to set the trump category
 */
public class JChooseTrumpDialog extends JDialog{
    private JLabel label=new JLabel("Select a trump category");
    private JComboBox trumpChoice;
    private JButton button=new JButton("Confirm");
    private GridLayout grid=new GridLayout(3,1,5,5);

    public JChooseTrumpDialog(){
        setTitle("Pick a trump category");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(250,250);
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(grid);

        //Set ComboBox items
        String[] trumpCategories={"Hardness", "Specific gravity","Cleavage", "Crustal abundance", "Economic value"};
        trumpChoice=new JComboBox(trumpCategories);
        add(label);
        add(trumpChoice);
        add(button);
        button.addActionListener(GUISuperTrumpGame.SetTrumpCategoryListener);
    }

    public String getTrumpCategory(){
        return (String)trumpChoice.getSelectedItem();
    }
}


