/**
 * Created by Admin on 21/09/2016.
 */
public class PlayCard extends BaseCard {
    private String chemicalFormula;
    private String classification;
    private String occurrence;
    private String crustalAbundance;
    private String hardness;
    private String cleavage;
    private String economicValue;
    private String specificGravity;
    private double highestHardness;
    private double highestSpecificGravity;



    PlayCard(String name, String fileName, String imageName, String chemicalFormula, String classification, String occurrence, String crustalAbundance, String hardness, String cleavage, String economicValue, String specificGravity) {
        super("Play", name, fileName, imageName);
        this.hardness=hardness;
        this.chemicalFormula=chemicalFormula;
        this.classification = classification;
        this.occurrence=occurrence;
        this.crustalAbundance=crustalAbundance;
        this.hardness=hardness;
        this.cleavage=cleavage;
        this.economicValue=economicValue;
        this.specificGravity=specificGravity;
        String[] hardnessRange=hardness.split("-");
        highestHardness=Double.parseDouble(hardnessRange[hardnessRange.length-1]);

        String[] specificGravityRange=specificGravity.split("-");
        highestSpecificGravity=Double.parseDouble(specificGravityRange[specificGravityRange.length-1]);

    }

    public String getHardness() {
        return hardness;
    }

    public double getHighestHardness() {
        return highestHardness;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public double getHighestSpecificGravity() {
        return highestSpecificGravity;
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public String getClassification() {
        return classification;
    }

    public String getCrustalAbundance() {
        return crustalAbundance;
    }

    public String getEconomicValue() {
        return economicValue;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public static void main(String[] args) {
        PlayCard card = new PlayCard("me", "nowhere.txt", "noimsge.png", "SGit", "none", "nj", "mff", "5-8", "jsdafbds", "lkfdsjb", "5-9");
        System.out.println("Card name is: " + card.getCardName());
        System.out.println("Card type is: " + card.getCardTitle());
        System.out.println("Card hardness is: " + card.getHardness());
        System.out.println("Highest Card hardness is: " + card.getHighestHardness());
        System.out.println("Card specific gravity is: " + card.getSpecificGravity());
        System.out.println("Card highest specific gravity is: " + card.getHighestSpecificGravity());
        System.out.println("Card chemical formula is: " + card.getChemicalFormula());
        System.out.println("Card classification: " + card.getClassification());
        System.out.println("Card crustal abundance: " + card.getCrustalAbundance());
        System.out.println("Card economic value is: " + card.getEconomicValue());
        System.out.println("Card cleavage: " + card.getCleavage());
        System.out.println("Card occurrence: " + card.getOccurrence());
    }
}