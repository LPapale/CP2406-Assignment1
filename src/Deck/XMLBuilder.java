package Deck; /**
 * Created by Admin on 26/09/2016.
 */
import Cards.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

public class XMLBuilder {

   private ArrayList<BaseCard> deck=new ArrayList<>();

    public XMLBuilder() {
        final int NUMBEROFCARDS = 60;
        String hardness="";
        String specificGravity="";
        try {
            File inputFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            NodeList nList = doc.getElementsByTagName("dict");

            for (int i = 1; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                Element element = (Element) node;
                NodeList keyList = element.getElementsByTagName("key");
                NodeList stringList = element.getElementsByTagName("string");
                // Determine the number of occurrence fields
                int occurrences = stringList.getLength() - 11;

                if (keyList.item(3).getTextContent().equals("play")) {

                    String fileName = stringList.item(0).getTextContent();
                    String imageName = stringList.item(1).getTextContent();
                    String title=stringList.item(2).getTextContent();
                    String chemistry = stringList.item(3).getTextContent();
                    String classification = stringList.item(4).getTextContent();
                    String crystalSystem = stringList.item(5).getTextContent();
                    // Add occurrence fields
                    String occurrence = stringList.item(6).getTextContent();
                    for (int occ = 1; occ < occurrences; occ++) {
                        occurrence = occurrence + " " + stringList.item(6 + occ).getTextContent();
                    }
                    // add fields after occurrence
                    String hardnessInput = stringList.item(6 + occurrences).getTextContent();
                    String specificGravityInput = stringList.item(7 + occurrences).getTextContent();
                    String cleavage = stringList.item(8 + occurrences).getTextContent();
                    String crustalAbundance = stringList.item(9 + occurrences).getTextContent();
                    String economicValue = stringList.item(10 + occurrences).getTextContent();
                    // Check hardness input format
                        // Check for single number or "-" separator
                    if((hardnessInput.matches("[0-9]*\\.?[0-9]+-[0-9]*\\.?[0-9]+"))|(hardnessInput.matches("[0-9]*\\.?[0-9]+"))){
                        hardness=hardnessInput;
                        // Check for " " separator
                    }else if(hardnessInput.matches("[0-9]*\\.?[0-9]+ [0-9]*\\.?[0-9]+")){
                        String[] hardnessRange=hardnessInput.split(" ");
                        hardness=String.format("%.1f-%.1f", Double.parseDouble(hardnessRange[0]) , Double.parseDouble(hardnessRange[1]));
                        // check for " - " separator
                    }else if(hardnessInput.matches("[0-9]*\\.?[0-9]+ - [0-9]*\\.?[0-9]+")){
                        String[] hardnessRange=hardnessInput.split(" ");
                        hardness=String.format("%.1f-%.1f", Double.parseDouble(hardnessRange[0]) , Double.parseDouble(hardnessRange[2]));
                    }else {
                        System.out.println("ERROR IN HARDNESS "+ hardnessInput);
                    }

                    if((specificGravityInput.matches("[0-9]*\\.?[0-9]+-[0-9]*\\.?[0-9]+"))|(specificGravityInput.matches("[0-9]*\\.?[0-9]+"))){
                        specificGravity=specificGravityInput;
                        // Check for " " separator
                    }else if(specificGravityInput.matches("[0-9]*\\.?[0-9]+ [0-9]*\\.?[0-9]+")){
                        String[] specificGravityRange=specificGravityInput.split(" ");
                        specificGravity=String.format("%.1f-%.1f", Double.parseDouble(specificGravityRange[0]) , Double.parseDouble(specificGravityRange[1]));
                    }else if(specificGravityInput.matches("[0-9]*\\.?[0-9]+ - [0-9]*\\.?[0-9]+")){
                        String[] specificGravityRange=specificGravityInput.split(" ");
                        specificGravity=String.format("%.1f-%.1f", Double.parseDouble(specificGravityRange[0]) , Double.parseDouble(specificGravityRange[2]));
                    }else {
                        System.out.println("ERROR IN SPECIFIC GRAVITY "+ specificGravityInput);
                    }



                    deck.add(new PlayCard(title, fileName, imageName, chemistry, classification, occurrence, crustalAbundance, hardness, cleavage, economicValue,specificGravity, crystalSystem));
                } else if (keyList.item(3).getTextContent().equals("trump")) {
                    String fileName = stringList.item(0).getTextContent();
                    String imageName = stringList.item(1).getTextContent();
                    String title = stringList.item(2).getTextContent();
                    String subtitle = stringList.item(3).getTextContent();
                    // Add card to deck
                    deck.add(new TrumpCard(title, fileName, imageName, subtitle));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check for the correct number of cards
        if(deck.size()!=NUMBEROFCARDS){
            System.out.println("Something went wrong: Unexpected number of cards in the deck!\nThe number of cards in the deck is "+ deck.size());
        }


    }

    public ArrayList<BaseCard> getDeck() {
        return deck;
    }

}
