package GUI;

import Cards.BaseCard;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates a panel of labels to hold all cards in a hand
 */
public class JHandPanel extends JPanel {
    GridLayout grid=new GridLayout(1, 0, 5, 0);
    public JHandPanel(){
        setLayout(grid);
        setSize(1000,1000);
    }

    public void addHand(ArrayList<BaseCard> hand){
        removeAll();
        String cardName;
        String fileName;

        ImageIcon cardImage;
        ImageIcon image;
        Image scaledImage;
        for(BaseCard card:hand){
            // Get card file
            fileName=card.getFileName();
            //Get card name
            cardName=card.getCardTitle();
            //Get card image
            image= new ImageIcon("src\\GUI\\images\\"+fileName);
            // Scale card image
            scaledImage=image.getImage().getScaledInstance(200,300, Image.SCALE_DEFAULT);
            cardImage=new ImageIcon(scaledImage);
            // Create card label
            JLabel cardLabel=new JLabel(cardImage);
            cardLabel.setName(cardName);
            add(cardLabel);
            cardLabel.addMouseListener(GUISuperTrumpGame.handMouseListener);
        }
    }
}
