import java.util.ArrayList;

/**
 * Created by Admin on 26/09/2016.
 */
public class TrumpCategoryArrays {

    public static ArrayList<String> getCleavageArray(){
        ArrayList<String> cleavages=new ArrayList<>();
        String[] cleavageList={"none","poor/none", "1 poor", "2 poor", "1 good", "1 good, 1 poor", "2 good", "3 good",
                "1 perfect", "1 perfect, 1 good", "1 perfect, 2 good", "3 perfect", "4 perfect", "6 perfect"};

    for(String val:cleavageList){
        cleavages.add(val);
    }
    return cleavages;
    }

    public static ArrayList<String> getCrustalAbundanceArray(){
        ArrayList<String> crustalAbundances=new ArrayList<>();
        String[] crustalAbundanceList={"ultratrace","trace", "low","moderate", "high", "very high"};
        for(String val:crustalAbundanceList){
            crustalAbundances.add(val);
        }
        return crustalAbundances;
    }

    public static ArrayList<String> getEconomicValeArray(){
        ArrayList<String> economicValue=new ArrayList<>();
        String[] economicValueList={"trivial", "low","moderate", "high", "very high", "I'm rich!"};
        for(String val:economicValueList){
            economicValue.add(val);
        }
        return economicValue;
    }
    public static ArrayList<String> getTrumpCategoriesArray(){
        ArrayList<String> trumpCategories=new ArrayList<>();
        String[] trumpCategoriesList={"Hardness", "Specific gravity","Cleavage", "Crustal abundance", "Economic value"};
        for(String val:trumpCategoriesList){
            trumpCategories.add(val);
        }
        return trumpCategories;
    }

}
