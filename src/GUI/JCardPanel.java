package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 23/10/2016.
 */
public class JCardPanel extends JPanel {
    BorderLayout border=new BorderLayout();
    JDeckPanel deckPnl= new JDeckPanel();
    JPanel topPnl=new JPanel();
    JCurrentCardPanel currentCardPnl= new JCurrentCardPanel();
    JLabel trumpLabel=new JLabel("Trump category is:");
    JLabel trumpCategoryLabel=new JLabel();
    JPanel bottomPnl=new JPanel();
    JLabel infoLabel=new JLabel("Welcome");

    JCardPanel(){
        setLayout(border);
        //setBackground(new Color(53,153,255));
        topPnl.setLayout(new FlowLayout());
        topPnl.add(trumpLabel);
        topPnl.add(trumpCategoryLabel);
        bottomPnl.add(infoLabel);
        add(topPnl, BorderLayout.NORTH);
        add(currentCardPnl, BorderLayout.EAST);
        add(deckPnl, BorderLayout.WEST);
        add(bottomPnl, BorderLayout.SOUTH);
    }
}
