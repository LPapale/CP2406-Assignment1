package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Cards.*;

/**
 * This class creates the game frame
 */
public class JGameFrame extends JFrame {
    BorderLayout border =new BorderLayout();
    JHandPanel handPnl=new JHandPanel();
    JCardPanel cardPnl=new JCardPanel();
    JPlayersPanel playersPanel;
    JPanel panel=new JPanel(border);
    JPanel westPnl=new JPanel();
    JPanel eastPnl=new JPanel();


    public JGameFrame(int numberOfAIPlayers){
        super("Super Trump Game");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playersPanel=new JPlayersPanel(numberOfAIPlayers);
        // Set panel sizes
        playersPanel.setPreferredSize(new Dimension(1000, 200));
        eastPnl.setPreferredSize(new Dimension(200,200));
        westPnl.setPreferredSize(new Dimension(200,200));
        // Add panels
        panel.add(playersPanel, BorderLayout.NORTH);
        panel.add(cardPnl, BorderLayout.CENTER);
        panel.add(handPnl,BorderLayout.SOUTH);
        panel.add(eastPnl, BorderLayout.EAST);
        panel.add(westPnl, BorderLayout.WEST);
        add(panel);
    }

    public void addPlayerHand(ArrayList<BaseCard> hand){
        handPnl.addHand(hand);
    }

    public void setPlayerName(int player, String name){
        playersPanel.setPlayerName(player, name);
    }

    public void setPlayerState(int player, String state){
        playersPanel.setPlayerState(player, state);
    }

    public void setInfoLabel(String info){
        cardPnl.infoLabel.setText(info);
    }

}
