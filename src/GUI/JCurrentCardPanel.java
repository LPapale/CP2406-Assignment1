package GUI;

import Cards.BaseCard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 23/10/2016.
 */
public class JCurrentCardPanel extends JPanel {

    public JCurrentCardPanel(){
        setSize(200,300);
    }

    public void updateCurrentCard(BaseCard card){
        removeAll();
        if(card==null){
            JLabel cardLabel = new JLabel();
            cardLabel.setBackground(Color.BLUE);
            add(cardLabel);
        }else {
            String cardName;
            String fileName;
            ImageIcon cardImage;
            ImageIcon image;
            Image scaledImage;

            // Get card file
            fileName = card.getFileName();
            //Get card name
            cardName = card.getCardTitle();
            //Get card image
            image = new ImageIcon("src\\GUI\\images\\" + fileName);
            // Scale card image
            scaledImage = image.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
            cardImage = new ImageIcon(scaledImage);
            // Create card label
            JLabel cardLabel = new JLabel(cardImage);
            cardLabel.setName(cardName);
            add(cardLabel);
        }
    }
}
