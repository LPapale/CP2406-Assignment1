package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Cards.*;

/**
 * Created by Admin on 22/10/2016.
 */
public class JGameFrame extends JFrame {
    BorderLayout border =new BorderLayout();
    JHandPanel handPnl=new JHandPanel();
    JCardPanel cardPnl=new JCardPanel();


    public JGameFrame(){
        setLayout(border);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(handPnl, BorderLayout.SOUTH);
        add(cardPnl,BorderLayout.CENTER);
    }

    public void addPlayerHand(ArrayList<BaseCard> hand){
        handPnl.addHand(hand);
    }

}
