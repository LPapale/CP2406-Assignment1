/**
 * This class implements the functionality of a human player including playing and picking a trump category.
 */
public class HumanPlayer extends BasePlayer{
    private InputReader inputReader=new InputReader();
    public HumanPlayer(String name){
        super(name, "Human");
    }
    private PlayCard magnetite=new PlayCard("Magnetite","Slide46.jpg","Slide46","Fe_3 O_4","oxide (spinel)","isometric","igneous metamorphic sedimentary", "5.5-6","none","moderate","5.2","very high");
    private TrumpCard geophysicist=new TrumpCard("The Geophysicist","Slide59.jpg","Slide59","Specific gravity");

@Override
public BaseCard playCard(String trumpCategory, BaseCard lastCard){

    int choice;
    BaseCard cardToPlay=null; //Pass if no choice is made
    boolean validChoice=false;
    // Show trump category and card to beat
    System.out.println(getName()+" it is your turn to play!");
    System.out.println("The current trump category is "+trumpCategory);
    System.out.println("The card to beat is: "+lastCard.getCategoryDetails(trumpCategory)+".\n");

    // See if layer wishes to play, pass or quit.
    showMenu();
    choice=inputReader.getMenuChoice(1,3);

    if(choice==3){
        System.exit(1);
    }else if(choice==1) {
        // Reprint card to beat
        System.out.println("The current trump category is "+trumpCategory);
        System.out.println("The card to beat is: "+lastCard.getCategoryDetails(trumpCategory)+".\n");
        // Show hand
        System.out.println("Please pick a card:");
        printHand();
        System.out.println("Or enter " + (hand.size() + 1) + " to pass.\n");
        // Ensure a valid card is played
        while (!validChoice) {
            // Get card choice
            choice = inputReader.getMenuChoice(1, hand.size() + 1);
            // Check if player passed
            if (choice == hand.size() + 1) {
                // Player passed
                validChoice = true;
            } else {
                // Check if card can be played
                cardToPlay = hand.get(choice - 1);
                validChoice = validCard(trumpCategory, lastCard, cardToPlay);
                if (!validChoice) {
                    System.out.println("Invalid choice! You must play a card with " + trumpCategory + " greater than that of " + lastCard.getCardTitle() + ".");
                    System.out.println("Please choose a different card or pass.");
                } else {
                    // If card is valid remove from hand
                    hand.remove(choice - 1);
                }
            }
        }
    }
    return cardToPlay;
}

    @Override
    public BaseCard playCard(String trumpCategory) {
        int choice;
        BaseCard cardToPlay=null; //Pass if no choice is made
        System.out.println(getName()+" it is your turn to play!");
        System.out.println("The current trump category is "+trumpCategory+".\n");
        // See if layer wishes to play, pass or quit.
        showMenu();
        choice=inputReader.getMenuChoice(1,3);
        if(choice==3){
            System.out.println("Exiting game.");
            System.exit(1);
        }else if(choice==1) {
            // display hand
            System.out.println("Please pick a card:");
            printHand();
            System.out.println("Enter " + (hand.size() + 1) + " to pass.");
            choice = inputReader.getMenuChoice(1, hand.size() + 1);
            if (choice == hand.size() + 1) {
                // player passed
                cardToPlay = null;
            } else {
                cardToPlay = hand.get(choice - 1);
                hand.remove(choice - 1);
            }
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
            PlayCard newPlayCard=(PlayCard)newCard;
            PlayCard lastPlayCard=(PlayCard)lastCard;

            switch (trumpCategory) {
                case ("Hardness"):
                    if(newPlayCard.getHighestHardness()>lastPlayCard.getHighestHardness()){
                    isValidChoice=true;
                    }
                    break;
                case ("Specific gravity"):
                    if(newPlayCard.getHighestSpecificGravity()>lastPlayCard.getHighestSpecificGravity()){
                        isValidChoice=true;
                    }
                    break;
                case "Cleavage":
                    if (newPlayCard.getCleavageIndex() > lastPlayCard.getCleavageIndex()) {
                        isValidChoice=true;
                    }
                    break;
                case "Crustal abundance":
                    if (newPlayCard.getCrustalAbundanceIndex() > lastPlayCard.getCrustalAbundanceIndex()) {
                        isValidChoice=true;
                    }
                    break;
                case "Economic value":
                    if (newPlayCard.getEconomicValueIndex() > lastPlayCard.getEconomicValueIndex()) {
                        isValidChoice=true;
                    }
                    break;
            }
        }
        return isValidChoice;
    }

    public void printHand(){
        int i=0;
        for(BaseCard card:hand){
            i++;
            System.out.println(""+i+"."+card.getCardDetails()+".\n");
        }
    }

    private void showMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1. Play");
        System.out.println("2. Pass");
        System.out.println("3. Quit");
    }
}
