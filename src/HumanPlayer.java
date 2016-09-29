/**
 * Created by Admin on 23/09/2016.
 */
public class HumanPlayer extends BasePlayer{
    private InputReader inputReader=new InputReader();
    public HumanPlayer(String name){
        super(name, "Human");
    }
@Override
public BaseCard playCard(String trumpCategory, BaseCard lastCard){

    int choice;
    BaseCard cardToPlay=null;
    boolean validChoice=false;
    System.out.println(getName()+" it is your turn to play!");
    System.out.println("The current trump category is "+trumpCategory);
    System.out.println("The card to beat is: "+lastCard.getCategoryDetails(trumpCategory)+".\n");

    System.out.println("Please pick a card:");
    printHand();
    System.out.println("Or enter "+(hand.size()+1)+" to pass.\n");
    while (!validChoice){

        choice=inputReader.getMenuChoice(1,hand.size()+1);
        if(choice==hand.size()+1){
            // Player passed
            validChoice=true;
        }else {
            cardToPlay = hand.get(choice - 1);
            validChoice = validCard(trumpCategory, lastCard, cardToPlay);
            if(!validChoice){
                System.out.println("Invalid choice pick again!");
            }else {
                hand.remove(choice-1);
            }
        }
    }

    return cardToPlay;
}

    @Override
    public BaseCard playCard(String trumpCategory) {
        int choice;
        BaseCard cardToPlay;
        System.out.println(getName()+" it is your turn to play!");
        System.out.println("The current trump category is "+trumpCategory+".\n");
        System.out.println("Please pick a card:");
        printHand();
        System.out.println("Enter "+(hand.size()+1)+" to pass.");
        choice=inputReader.getMenuChoice(1,hand.size()+1);
        if(choice==hand.size()+1){
            cardToPlay=null;
        }else{
        cardToPlay = hand.get(choice - 1);
        hand.remove(choice-1);
        }


        return cardToPlay;
    }

    @Override
    public String pickTrumpCategory() {
        System.out.println(getName()+" it is your turn to play!\n");
        System.out.println("The cards in your hand are:");
        printHand();
        System.out.println("Please pick a trump category.");
        int i=0;
        int choice;
        String trumpCategory;
        for(String category:TrumpCategoryArrays.getTrumpCategoriesArray()){
            i++;
            System.out.println(""+i+". "+category);
        }
        choice=inputReader.getMenuChoice(1,TrumpCategoryArrays.getTrumpCategoriesArray().size());
        trumpCategory=TrumpCategoryArrays.getTrumpCategoriesArray().get(choice-1);
        return trumpCategory;

    }

    private boolean validCard(String trumpCategory, BaseCard lastCard, BaseCard newCard){
        boolean isValidChoice=false;
        if(lastCard.getCardType().equals("Trump")|newCard.getCardType().equals("Trump")){
            isValidChoice=true;
        }else {
            PlayCard newCard1=(PlayCard)newCard;
            PlayCard lastCard1=(PlayCard)lastCard;

            switch (trumpCategory) {
                case ("Hardness"):
                    if(newCard1.getHighestHardness()>lastCard1.getHighestHardness()){
                    isValidChoice=true;
                    }
                    break;
                case ("Specific gravity"):
                    if(newCard1.getHighestSpecificGravity()>lastCard1.getHighestSpecificGravity()){
                        isValidChoice=true;
                    }
                    break;
                case "Cleavage":
                    if (newCard1.getCleavageIndex() > lastCard1.getCleavageIndex()) {
                        isValidChoice=true;
                    }
                    break;
                case "Crustal abundance":
                    if (newCard1.getCrustalAbundanceIndex() > lastCard1.getCrustalAbundanceIndex()) {
                        isValidChoice=true;
                    }
                    break;
                case "Economic value":
                    if (newCard1.getEconomicValueIndex() > lastCard1.getEconomicValueIndex()) {
                        isValidChoice=true;
                    }
                    break;
            }
        }
        return isValidChoice;
    }
    private void printHand(){
        int i=0;
        for(BaseCard card:hand){
            i++;
            System.out.println(""+i+"."+card.getCardDetails()+".\n");
        }
    }
}
