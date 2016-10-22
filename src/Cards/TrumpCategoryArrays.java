package Cards;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates arrays of each of the non numerical trump values so that they can be sorted
 */
public class TrumpCategoryArrays {

    public static ArrayList<String> getCleavageArray(){
        String[] cleavageList={"none","poor/none", "1 poor", "2 poor", "1 good", "1 good, 1 poor", "2 good", "3 good",
                "1 perfect", "1 perfect, 1 good", "1 perfect, 2 good", "3 perfect", "4 perfect", "6 perfect"};
        ArrayList<String> cleavages=new ArrayList<>(Arrays.asList(cleavageList)) ;
        return cleavages;
    }

    public static ArrayList<String> getCrustalAbundanceArray(){
        String[] crustalAbundanceList={"ultratrace","trace", "low","moderate", "high", "very high"};
        ArrayList<String> crustalAbundances=new ArrayList<>(Arrays.asList(crustalAbundanceList));
        return crustalAbundances;
    }

    public static ArrayList<String> getEconomicValeArray(){
        String[] economicValueList={"trivial", "low","moderate", "high", "very high", "I'm rich!"};
        ArrayList<String> economicValue=new ArrayList<>(Arrays.asList(economicValueList));
        return economicValue;
    }

    public static ArrayList<String> getTrumpCategoriesArray(){
        String[] trumpCategoriesList={"Hardness", "Specific gravity","Cleavage", "Crustal abundance", "Economic value"};
        ArrayList<String> trumpCategories=new ArrayList<>(Arrays.asList(trumpCategoriesList));
        return trumpCategories;
    }
}
