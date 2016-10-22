package GUI;

import Cards.BaseCard;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates a panel of labels to hold all cards in a hand
 */
public class JHandPanel extends JPanel {
    GridLayout grid=new GridLayout(0, 1, 5, 0);
    public JHandPanel(){
            setLayout(grid);
    }

    public void addHand(ArrayList<BaseCard> hand){
        removeAll();
        String cardName;
        String fileName;
        ImageIcon cardImage;
        for(BaseCard card:hand){
            // Get card file
            fileName=card.getFileName();
            //Get card name
            cardName=card.getCardTitle();
            //Get card image
            cardImage= new ImageIcon("src\\GUI\\images\\"+fileName);
            // Create card label
            JLabel cardLabel=new JLabel(cardImage);
            cardLabel.setName(cardName);
            // TODO add action listeners
        }
    }
}
