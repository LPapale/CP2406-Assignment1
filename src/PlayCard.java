import com.sun.xml.internal.fastinfoset.util.StringArray;

/**
 * Created by Admin on 21/09/2016.
 */
public class PlayCard extends BaseCard {
    private String chemicalFormular;
    private String clasification;
    private String occurrence;
    private String crustalAbundance;
    private String hardness;
    private String cleavage;
    private String economic_value;
    private double highestHardness;;


    PlayCard(String name, String fileName, String imageName, String chemicalFormular, String clasification, String occurrence, String crustalAbundance, String hardness, String cleavage, String economic_value) {
        super("Play", name, fileName, imageName);

    }


    public static void main(String[] args) {
        PlayCard card = new PlayCard("me", "nowhere.txt", "noimsge.png", );
        System.out.println(card.getCardName());
        System.out.println(card.getCardType());
    }
}