package GUI;

import Cards.BaseCard;
import Cards.TrumpCard;
import Deck.*;
import Players.AIPlayer;
import Players.BasePlayer;
import Players.HumanPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Admin on 22/10/2016.
 */
public class GUISuperTrumpGame {
    static String trumpCategory;
    static BaseCard newCard;
    static   BaseCard currentCard;
    private static String[] aINamesList={"Ultron", "GLaDOS", "Skynet", "A.L.I.E"};
    private static ArrayList<String> aINames=new ArrayList<>(Arrays.asList(aINamesList));
    private static Deck deck;
    private static BasePlayer[] players;
    private static Random random= new Random(System.nanoTime());
    private static ArrayList<String> winners = new ArrayList<>();
    private static int numberOfPlayers;
    private static JMenuFrame menu;
    private static JGameFrame game;
    private static JChooseTrumpDialog trumpDialog;
    private static String humanPlayerName;

    public static void main(String[] args){
        menu=new JMenuFrame();
        menu.setVisible(true);
        game=new JGameFrame();
        game.setVisible(false);
        trumpDialog=new JChooseTrumpDialog();
        trumpDialog.setVisible(false);


    }

    public static ActionListener NewGameActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Yay one part down 999999999 to go");
            numberOfPlayers=menu.getNumberOfPlayers();
            humanPlayerName=menu.getPlayerName();
            System.out.println("Number of players is: "+numberOfPlayers);
            System.out.println("Human player name is: "+humanPlayerName);
            menu.setVisible(false);
            game.setVisible(true);
            startNewGame();
        }
    };

    public static ActionListener SetTrumpCategoryListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            trumpCategory=trumpDialog.getTrumpCategory();
            System.out.println("Player set Trump category to "+trumpCategory);
            trumpDialog.setVisible(false);
        }
    };

    private static void startNewGame(){
        // Create players
        players = new BasePlayer[numberOfPlayers];
        // Create human player
        players[0] = new HumanPlayer(humanPlayerName);

        boolean gameFinished = false;
        int firstPlayer;
        int playerID;
        // Create Players.AIPlayer
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

        game.addPlayerHand(players[0].getHand());
        /*try{
            Thread.sleep(5000);}
        catch (InterruptedException e){
            // Continue
        }*/

        // Select first player
        firstPlayer=random.nextInt(numberOfPlayers);
        //firstPlayer=1;
        playerID=firstPlayer;
        System.out.println(players[firstPlayer].getName()+" plays first");

        if(firstPlayer!=0) {
            // Get trump category from first player
            trumpCategory = players[firstPlayer].pickTrumpCategory();
            System.out.println("Trump category is " + trumpCategory);
            // Get first card
            currentCard = players[firstPlayer].playCard(trumpCategory);
            System.out.println(players[playerID].getName()+" Played "+ currentCard.getCardTitle()+".");
            System.out.println(currentCard.getCategoryDetails(trumpCategory)+".\n");
            // Set next player
            playerID++;
            if(playerID==numberOfPlayers){
                playerID=0;
            }
            //Play while next player is an AIPlayer
            while (playerID!=0){
                play(players[playerID]);
                playerID++;
                if(playerID==numberOfPlayers){
                    playerID=0;
                }
            }
        }else{
            // Get trump category from player
            trumpDialog.setVisible(true);
        }

            /*/ Ensure a card was played
        while(currentCard==null){
            playerID++;
            currentCard= players[playerID].playCard(trumpCategory);
        }*/

    }

    private static void play(BasePlayer player){
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


    private static void activateAllPlayers(){
        for(BasePlayer player:players){
            player.activate();
        }
    }

    private static int numberOfActivePlayers(){
        int numberOfActivePlayers=0;
        for(BasePlayer player:players){
            if(player.isActive()&!player.isFinished()){
                numberOfActivePlayers++;
            }
        }
        return numberOfActivePlayers;
    }

}