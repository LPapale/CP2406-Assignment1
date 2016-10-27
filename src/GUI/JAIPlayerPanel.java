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
        setSize(200,200);
        setLayout(grid);
        add(nameLabel);
        add(stateLabel);
        add(label);
        setBackground(Color.CYAN);
    }

    public void setName(String name){
        nameLabel.setText(name);
    }

    public void setState(String  state) {
        stateLabel.setText(state);
        if(state.equals("Finished")){
            setBackground(Color.YELLOW);
        }
    }

    public void setLabel(String label){
        this.label.setText(label);
    }
}
