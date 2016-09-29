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
    private String crystalSystem;
    private double highestHardness;
    private double highestSpecificGravity;



    PlayCard(String title, String fileName, String imageName, String chemicalFormula, String classification, String occurrence, String crustalAbundance, String hardness, String cleavage, String economicValue, String specificGravity, String crystalSystem) {
        super("Play", title, fileName, imageName);
        this.hardness=hardness;
        this.chemicalFormula=chemicalFormula;
        this.classification = classification;
        this.occurrence=occurrence;
        this.crustalAbundance=crustalAbundance;
        this.hardness=hardness;
        this.cleavage=cleavage;
        this.economicValue=economicValue;
        this.specificGravity=specificGravity;
        this.crystalSystem=crystalSystem;

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

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public int getCleavageIndex(){
        return TrumpCategoryArrays.getCleavageArray().indexOf(cleavage);
    }

    public int getCrustalAbundanceIndex(){
        return TrumpCategoryArrays.getCrustalAbundanceArray().indexOf(crustalAbundance);
    }

    public int getEconomicValueIndex(){
        return TrumpCategoryArrays.getEconomicValeArray().indexOf(economicValue);
    }

    @Override
    public String getCardDetails() {
        return "Name: "+getCardTitle()+", Hardness "+ getHardness()+ ", Specific gravity "+getSpecificGravity()+
                ", cleavage "+getCleavage()+", Crustal abundance "+ getCrustalAbundance()+", Economic value "+
                getEconomicValue()+",\n Chemistry " + getChemicalFormula() + ", Classification " + getClassification() +
                ", Crystal system "+getCrystalSystem()+", Occurrence "+getOccurrence();
    }

    @Override
    public String getCategoryDetails(String trumpCategory) {
        String data="";
        switch (trumpCategory){
            case "Hardness":
                data="Hardness: "+getHardness();
                break;
            case "Specific gravity":
                data="Specific gravity: "+getSpecificGravity();
                break;
            case "Cleavage":
                data="Cleavage: "+getCleavage();
                break;
            case "Crustal abundance":
                data="Crustal abundance: "+getCrustalAbundance();
                break;
            case "Economic value":
                data="Economic value: "+getEconomicValue();
                break;
        }
        return "Name: "+getCardTitle()+", "+data;
    }
}