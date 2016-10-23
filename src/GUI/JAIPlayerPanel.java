package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 23/10/2016.
 */
public class JAIPlayerPanel extends JPanel{
    GridLayout grid=new GridLayout(3,1);
    JLabel nameLabel=new JLabel();
    JLabel stateLabel=new JLabel();
    JLabel label=new JLabel();
    JAIPlayerPanel(){
        setLayout(grid);
        add(nameLabel);
        add(stateLabel);
        add(label);
    }

    public void setName(String name){
        nameLabel.setText(name);
    }

    public void setState(String  state) {
        stateLabel.setText(state);
    }

    public void setLabel(String label){
        this.label.setText(label);
    }
}
