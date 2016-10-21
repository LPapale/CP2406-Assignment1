package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        for(int i=3;i<=5;i++){
            numberOfPlayers.addItem(i);
        }

        add(nameLabel);
        add(nameField);
        add(numberOfPlayersLabel);
        add(numberOfPlayers);
        add(newGameButton);
        newGameButton.addActionListener(newGameListener);
    }

    public static void main(String[] arguments)
    {
        JMenuFrame cframe = new JMenuFrame();
        cframe.setSize(350,150);
        cframe.setVisible(true);
    }

    public static ActionListener newGameListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          System.out.println("Start new game");
        }
    };
}
