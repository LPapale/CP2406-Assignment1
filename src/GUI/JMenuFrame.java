package GUI;

import javax.swing.*;
import java.awt.*;
/**
 * This class creates menu to get the players name and number of players
 */

public class JMenuFrame extends JFrame{
    GridLayout grid=new GridLayout(4,1);
    JLabel titleLabel=new JLabel("Welcome!!");
    JPanel titlePnl=new JPanel();
    JLabel nameLabel=new JLabel("Enter your name:");
    JPanel namePnl=new JPanel();
    JTextField nameField=new JTextField("Bob the builder");
    JPanel numberOfPlayersPnl=new JPanel();
    JLabel numberOfPlayersLabel= new JLabel("Select the number of players:");
    JComboBox numberOfPlayers=new JComboBox();
    JButton newGameButton=new JButton("Start new game");

    public JMenuFrame(){
        super("Super Trump Menu");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(grid);
        // add number of player options
        for(int i=3;i<=5;i++){
            numberOfPlayers.addItem(i);
        }
        // Add components to panels
        titlePnl.add(titleLabel);
        namePnl.add(nameLabel);
        namePnl.add(nameField);
        numberOfPlayersPnl.add(numberOfPlayersLabel);
        numberOfPlayersPnl.add(numberOfPlayers);
        // Add panels to frame
        add(titlePnl);
        add(namePnl);
        add(numberOfPlayersPnl);
        add(newGameButton);
        newGameButton.addActionListener(GUISuperTrumpGame.NewGameActionListener);
    }

    public int getNumberOfPlayers(){
        return (int) numberOfPlayers.getSelectedItem();
    }

    public String getPlayerName(){
        return nameField.getText();
    }

}
