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
    JPlayersPanel playersPanel;


    public JGameFrame(int numberOfAIPlayers){
        setLayout(border);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playersPanel=new JPlayersPanel(numberOfAIPlayers);
        playersPanel.setPreferredSize(new Dimension(1000,200));
        add(playersPanel, BorderLayout.NORTH);
        add(handPnl, BorderLayout.SOUTH);
        add(cardPnl,BorderLayout.CENTER);
    }

    public void addPlayerHand(ArrayList<BaseCard> hand){
        handPnl.addHand(hand);
    }

    public void setPlayerName(int player, String name){
        playersPanel. setPlayerName(player, name);
    }

    public void setPlayerState(int player, String state){
        playersPanel.setPlayerState(player, state);
    }

}
