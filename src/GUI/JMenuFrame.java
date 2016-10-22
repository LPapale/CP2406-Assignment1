package GUI;

import javax.swing.*;
import java.awt.*;


public class JMenuFrame extends JFrame{
    GridLayout grid=new GridLayout(4,2);
    FlowLayout flow = new FlowLayout();
    JLabel nameLabel=new JLabel("Enter your name:");
    JTextField nameField=new JTextField("Bob the builder");
    JLabel numberOfPlayersLabel= new JLabel("Select the number of players:");
    JComboBox numberOfPlayers=new JComboBox();
    JButton newGameButton=new JButton("Start new game");

    public JMenuFrame(){
        super("Super Trump Menu");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(grid);
        // add number of player options
        for(int i=3;i<=5;i++){
            numberOfPlayers.addItem(i);
        }

        add(nameLabel);
        add(nameField);
        add(numberOfPlayersLabel);
        add(numberOfPlayers);
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
