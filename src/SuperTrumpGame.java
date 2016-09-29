import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 24/09/2016.
 */
public class SuperTrumpGame {
    InputReader inputReader = new InputReader();
    String trumpCategory;
    BaseCard newCard;
    BaseCard currentCard;
    private String[] aINames={"Ultron", "GLaDOS", "Skynet", "A.L.I.E"};
    private Deck deck;
    private BasePlayer[] players;

    public SuperTrumpGame(String name){
        Random random=new Random(System.nanoTime());
        //Get the number of players
        int numberOfPlayers=inputReader.getNumberOfPlayers();

        ArrayList<String> winners=new ArrayList<>();
        // Create players
        players=new BasePlayer[numberOfPlayers];
        // Create human player
        players[0]=new HumanPlayer(name);
        // Create AIPlayer
        for(int i=1; i<(numberOfPlayers);i++){
            players[i]=new AIPlayer(aINames[i-1]);
        }
        // activate all players
        activateAllPlayers();
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
        boolean gameFinished=false;
        int firstPlayer=random.nextInt(numberOfPlayers);
        int playerID=firstPlayer;
        System.out.println(players[playerID].getName()+" plays first");
        trumpCategory= players[playerID].pickTrumpCategory();
        System.out.println("Trump category is "+trumpCategory);
        currentCard= players[playerID].playCard(trumpCategory);

        /* make sure a card was played
        while(currentCard==null){
            playerID++;
            BasePlayer player=players[playerID];
            currentCard= players[playerID].playCard(trumpCategory);
        }*/
        System.out.println(players[playerID].getName()+" Played "+ currentCard.getCardTitle()+".");
        System.out.println(currentCard.getCategoryDetails(trumpCategory)+".\n");
        if(playerID==numberOfPlayers-1){
            playerID=-1;
        }
        while(!gameFinished){
            playerID++;
            BasePlayer player=players[playerID];


            if(player.isActive()&!player.isFinished()) {
                // If player is the last active player pick
                if(numberOfActivePlayers()<=1){
                    trumpCategory= players[playerID].pickTrumpCategory();
                    System.out.println("Trump category is "+trumpCategory);
                    activateAllPlayers();
                    currentCard=null;
                }

                play(player);

                if (player.getHandSize() == 0) {
                    System.out.println("****************" + player.getName() + " Finished**************");
                    player.finish();
                    winners.add(player.getName());
                }

            }


            if(winners.size()==(numberOfPlayers-1)){
                    gameFinished=true;
                    System.out.println("Game over!");
                    System.out.println("The winner is "+winners.get(0));
                    System.out.println("2nd place goes to "+winners.get(1));
                    System.out.println("3rd place goes to "+winners.get(2));
                    for(int i=4;i<winners.size();i++){
                        System.out.println(""+(i-1)+"th place goes to "+winners.get(i));
                    }

            }




        if(playerID==numberOfPlayers-1){
            playerID=-1;
        }

    }

    }
    private void play(BasePlayer player){
        if(player.getHandSize()>0) {
            if(currentCard==null){
                newCard = player.playCard(trumpCategory);
            }
            else if (currentCard.getCardType().equals("Trump")) {
                newCard = player.playCard(trumpCategory);
            } else {
                newCard = player.playCard(trumpCategory, currentCard);
            }

            if (newCard == null) {
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
                System.out.println("Trump Category is now " + trumpCategory);


                // Set current card as new card
                currentCard = newCard;
                System.out.println(currentCard.getCategoryDetails(trumpCategory)+".\n");
                // replay
                play(player);
            } else {
                System.out.println(player.getName() + " played " + newCard.getCardTitle()+".");

                currentCard = newCard;
                System.out.println(currentCard.getCategoryDetails(trumpCategory)+".\n");
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
