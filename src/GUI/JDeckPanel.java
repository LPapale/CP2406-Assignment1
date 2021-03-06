package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 23/10/2016.
 */
public class JDeckPanel extends JPanel {
    ImageIcon cardImage;
    ImageIcon image;
    Image scaledImage;

    public JDeckPanel(){
        setSize(200,300);
        //Get image icon
        image= new ImageIcon("src\\GUI\\images\\Slide65.jpg");
        // Scale card image
        scaledImage=image.getImage().getScaledInstance(200,300, Image.SCALE_DEFAULT);
        cardImage=new ImageIcon(scaledImage);
        // Create card label
        JLabel cardLabel=new JLabel(cardImage);
        add(cardLabel);
        cardLabel.addMouseListener(GUISuperTrumpGame.deckMouseListener);
    }

    public void clear(){
        removeAll();
        setSize(200,300);
        //Get image icon
        image= new ImageIcon("src\\GUI\\images\\Slide66.jpg");
        // Scale card image
        scaledImage=image.getImage().getScaledInstance(200,300, Image.SCALE_DEFAULT);
        cardImage=new ImageIcon(scaledImage);
        // Create card label
        JLabel cardLabel=new JLabel(cardImage);
        add(cardLabel);
        cardLabel.addMouseListener(GUISuperTrumpGame.deckMouseListener);
    }
}
