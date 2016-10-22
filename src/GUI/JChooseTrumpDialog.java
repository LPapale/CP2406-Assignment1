package GUI;

import javax.swing.*;

import java.awt.*;

/**
 * Created by Admin on 22/10/2016.
 */
public class JChooseTrumpDialog extends JDialog{
    private JLabel label=new JLabel("Select trump a category");
    private JComboBox trumpChoice;
    private JButton button=new JButton("Confirm");
    private GridLayout grid=new GridLayout(3,1,5,5);
    private JPanel buttonPnl=new JPanel();


    public JChooseTrumpDialog(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setLayout(grid);
        setSize(250,250);
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


