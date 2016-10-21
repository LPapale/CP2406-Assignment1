import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This class implements the Super Trump game logic
 */
public class SuperTrumpGame {
    InputReader inputReader = new InputReader();
    String trumpCategory;
    BaseCard newCard;
    BaseCard currentCard;
    private String[] aINamesList={"Ultron", "GLaDOS", "Skynet", "A.L.I.E"};
    private ArrayList<String> aINames=new ArrayList<>(Arrays.asList(aINamesList));
    private Deck deck;
    private BasePlayer[] players;
    private Random random= new Random(System.nanoTime());
    private ArrayList<String> winners = new ArrayList<>();
    private int numberOfPlayers;

    public SuperTrumpGame(String name) {
        //Get the number of players
        numberOfPlayers = inputReader.getNumberOfPlayers();
        // Create players
        players = new BasePlayer[numberOfPlayers];
        // Create human player
        players[0] = new HumanPlayer(name);

    }

       public void startNewGame(){
           boolean gameFinished = false;
           int firstPlayer;
           int playerID;
           // Create AIPlayer
        Collections.shuffle(aINames,random);
        System.out.println("The AI players are:");
        for(int i=1; i<(numberOfPlayers);i++){
            players[i]=new AIPlayer(aINames.get(i-1));
            System.out.println(aINames.get(i-1));
        }
        // activate all players
        activateAllPlayers();

        //Create deck and deal cards
        System.out.println("Shuffling cards...\n");
        try{
            Thread.sleep(2500);}
        catch (InterruptedException e){
            // Continue
        }
        // Create deck builder
        XMLBuilder deckBuilder=new XMLBuilder();
        // Create deck
        deck=new Deck(deckBuilder.getDeck());
        // Shuffle deck
        deck.shuffleDeck();
        // Deal cards
        for(BasePlayer player:players) {
            player.setHand(deck.dealHand());
        }
        // Show human play their hand
        System.out.println("The cards in your hand are:");
        ((HumanPlayer)players[0]).printHand();
        // Pause
        try{
            Thread.sleep(5000);}
        catch (InterruptedException e){
            // Continue
        }

        // Select first player
        firstPlayer=random.nextInt(numberOfPlayers);
        playerID=firstPlayer;
        System.out.println(players[firstPlayer].getName()+" plays first");
        // Get trump category from first player
        trumpCategory= players[firstPlayer].pickTrumpCategory();
        System.out.println("Trump category is "+trumpCategory);
        // Get first card
        currentCard= players[firstPlayer].playCard(trumpCategory);
        // Ensure a card was played
        while(currentCard==null){
            playerID++;
            currentCard= players[playerID].playCard(trumpCategory);
        }
        System.out.println(players[playerID].getName()+" Played "+ currentCard.getCardTitle()+".");
        System.out.println(currentCard.getCategoryDetails(trumpCategory)+".\n");

        //Reset counter if current player is last player
        if(playerID==numberOfPlayers-1){
            playerID=-1;
        }

        while(!gameFinished){
            // Select next player
            playerID++;
            BasePlayer player=players[playerID];

            if(player.isActive()&!player.isFinished()) {
                // Check if player is the last active player
                if(numberOfActivePlayers()<=1){
                    System.out.println(player.getName()+" won the round!");
                    // Get trump category from player
                    trumpCategory= players[playerID].pickTrumpCategory();
                    System.out.println("Trump category is "+trumpCategory);
                    activateAllPlayers();
                    currentCard=null;
                }
                // Player takes turn
                play(player);
                // Check if player has finished
                if (player.getHandSize() == 0) {
                    System.out.println("****************" + player.getName() + " Finished**************");
                    player.finish();
                    winners.add(player.getName());
                }
            }
            // Check if game is finished
            if(winners.size()==(numberOfPlayers-1)){
                gameFinished=true;
                // Display winners
                System.out.println("Game over!");
                System.out.println("The winner is "+winners.get(0));
                System.out.println("2nd place goes to "+winners.get(1));
                if(winners.size()>2) {
                    System.out.println("3rd place goes to " + winners.get(2));
                }
                for(int i=3;i<winners.size();i++){
                    System.out.println(""+(i-1)+"th place goes to "+winners.get(i));
                }
            }
            //Reset counter if current player is last player
            if(playerID==numberOfPlayers-1){
                playerID=-1;
            }
        }
    }

    private void play(BasePlayer player){
        // Only play if player has cards remaining.
        if(player.getHandSize()>0) {
            // Get players card
            if(currentCard==null){
                // if there is no current card play chooses based on trump category only
                newCard = player.playCard(trumpCategory);
            }
            else if (currentCard.getCardType().equals("Trump")) {
                // if the last card is a trump card player chooses based on trump category only
                newCard = player.playCard(trumpCategory);
            } else {
                // If last card was a play card player choose based on trump category and last card
                newCard = player.playCard(trumpCategory, currentCard);
            }
            // Check if player passed
            if (newCard == null) {
                // Pick up card if deck still has cards
                if(deck.length()>0) {
                    player.setCard(deck.dealCard());
                }
                player.deactivate();
                System.out.println(player.getName() + " passed.\n");
            } else if (newCard.getCardType().equals("Trump")) {
                // Activate all payers
                activateAllPlayers();
                // Print played card
                System.out.println(player.getName() + " played " + newCard.getCardTitle()+".");
                // set trump category
                if (newCard.getCardTitle().equals("The Geologist")) {
                    trumpCategory = player.pickTrumpCategory();
                } else {
                    trumpCategory = ((TrumpCard) newCard).getSubtitle();
                }
                System.out.println(newCard.getCategoryDetails(trumpCategory)+".\n");
                System.out.println("Trump Category is now " + trumpCategory);
                // Set current card as new card
                currentCard = newCard;
                // replay since trump card was played
                play(player);
            } else {
                System.out.println(player.getName() + " played " + newCard.getCardTitle()+".");
                System.out.println(newCard.getCategoryDetails(trumpCategory)+".\n");
                // Check if round winning combination was played
                if(currentCard!=null){
                    if(currentCard.getCardTitle().equals("The Geophysicist")&newCard.getCardTitle().equals("Magnetite")){
                        System.out.println(player.getName()+" played the round winning combination!");
                        activateAllPlayers();
                        // Player restarts new round
                        play(player);
                    }
                }
                currentCard = newCard;
            }
        }
    }

    private void activateAllPlayers(){
        for(BasePlayer player:players){
            player.activate();
        }
    }

    private int numberOfActivePlayers(){
        int numberOfActivePlayers=0;
        for(BasePlayer player:players){
            if(player.isActive()&!player.isFinished()){
                numberOfActivePlayers++;
            }
        }
        return numberOfActivePlayers;
    }

}
