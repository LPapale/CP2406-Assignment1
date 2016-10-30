package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 23/10/2016.
 */
public class JPlayersPanel extends JPanel{
    JAIPlayerPanel[] playerPanels;
    JPlayersPanel(int numberOfPlayers){
        GridLayout grid=new GridLayout(1,numberOfPlayers,5,5);
        setLayout(grid);
        setSize(new Dimension(1000,200));
        playerPanels=new JAIPlayerPanel[numberOfPlayers];
        // Create a panel for each AIPlayer
        for(int i=0;i<numberOfPlayers;i++){
             JAIPlayerPanel playersPanel=new JAIPlayerPanel();
            playerPanels[i]=playersPanel;
            add(playerPanels[i]);
        }
    }
    public void setPlayerName(int player, String name){
        playerPanels[player].setName(name);
    }

    public void setPlayerState(int player, String state){
        playerPanels[player].setState(state);
    }

    public void setPlayerLabel(int player, String label){
        playerPanels[player].setLabel(label);
    }
}
