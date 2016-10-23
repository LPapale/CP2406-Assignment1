package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Cards.*;

/**
 * Created by Admin on 22/10/2016.
 */
public class JGameFrame extends JFrame {
    JHandPanel handPnl=new JHandPanel();
    JDeckPanel deckPnl= new JDeckPanel();
    BorderLayout border =new BorderLayout();
    JCurrentCardPanel currentCardPnl= new JCurrentCardPanel();


    public JGameFrame(){
        setLayout(border);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(handPnl, BorderLayout.SOUTH);
        add(currentCardPnl, BorderLayout.CENTER);
        add(deckPnl, BorderLayout.WEST);
    }

    public void addPlayerHand(ArrayList<BaseCard> hand){
        handPnl.addHand(hand);
    }

}
