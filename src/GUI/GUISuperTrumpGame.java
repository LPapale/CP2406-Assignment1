package GUI;

import Cards.BaseCard;
import Cards.TrumpCard;
import Deck.*;
import Players.AIPlayer;
import Players.BasePlayer;
import Players.GUIHumanPlayer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
    private static JGameOverDialog gameOverDialog;

    private static boolean turnFinished=false;

    private static boolean gameFinished = false;
    private static int firstPlayer;
    private static int playerID;



    public static void main(String[] args){
        menu=new JMenuFrame();
        menu.setVisible(true);
        gameOverDialog=new JGameOverDialog();
        gameOverDialog.setVisible(false);
    }

    public static ActionListener NewGameActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberOfPlayers=menu.getNumberOfPlayers();
            humanPlayerName=menu.getPlayerName();
            System.out.println("Number of players is: "+numberOfPlayers);
            System.out.println("Human player name is: "+humanPlayerName);
            menu.setVisible(false);
            game=new JGameFrame(numberOfPlayers-1);
            trumpDialog=new JChooseTrumpDialog();
            trumpDialog.setVisible(false);
            handMouseListener.setEnabled(false);
            deckMouseListener.setEnabled(false);
            game.setVisible(true);
            startNewGame();
        }
    };

    public static ActionListener GameOverListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameOverDialog.setVisible(false);
            game.dispose();
            trumpDialog.dispose();
            menu.setVisible(true);
            menu.validate();
            menu.repaint();
            turnFinished=false;
            gameFinished=false;
            winners.removeAll(winners);
        }
    };

    public static ActionListener SetTrumpCategoryListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            trumpCategory=trumpDialog.getTrumpCategory();
            game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
            currentCard=null;
            System.out.println("Player set Trump category to "+trumpCategory);
            trumpDialog.setVisible(false);
            handMouseListener.setEnabled(true);
            deckMouseListener.setEnabled(true);
        }
    };


    public static NewMouseListener handMouseListener=new NewMouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            new Thread() {
                public void run() {
                    GUIHumanPlayer humanPlayer = (GUIHumanPlayer) players[0];
                    // Check if player is the last active player
                    if (numberOfActivePlayers() <= 1) {
                        System.out.println(humanPlayer.getName() + " won the round!");
                        // Get trump category from player
                        trumpCategory = players[playerID].pickTrumpCategory();
                        System.out.println("Trump category is " + trumpCategory);
                        game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                        activateAllPlayers();
                        currentCard = null;
                        //Update current card panel
                        game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                        game.validate();
                        game.repaint();
                        trumpDialog.setVisible(true);
                    }

                    if (isEnabled() & humanPlayer.isActive() & !humanPlayer.isFinished()) {
                        turnFinished = false;
                        JLabel cardClicked = (JLabel) e.getSource();
                        String cardName = cardClicked.getName();
                        System.out.println(cardName + " was clicked");
                        BaseCard card = humanPlayer.getCardFromHand(cardName);
                        if (currentCard == null) {
                            humanPlayer.removeFromHand(cardName);
                            currentCard = card;
                            if (card.getCardType().equals("Play")) {
                                setEnabled(false);
                                turnFinished = true;
                            } else {
                                activateAllPlayers();
                                if (card.getCardTitle().equals("The Geologist")){
                                    trumpDialog.setVisible(true);
                                } else {
                                    trumpCategory = ((TrumpCard) card).getSubtitle();
                                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                                    System.out.println("Trump cat set to " + trumpCategory);
                                }
                            }
                        } else if (humanPlayer.validCard(trumpCategory, currentCard, card)) {
                            if (card.getCardType().equals("Play")) {
                                handMouseListener.setEnabled(false);
                                deckMouseListener.setEnabled(false);
                                turnFinished = true;
                            } else {
                                activateAllPlayers();
                                if (card.getCardTitle().equals("The Geologist")) {
                                    trumpDialog.setVisible(true);
                                } else {
                                    trumpCategory = ((TrumpCard) card).getSubtitle();
                                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                                    System.out.println("Trump cat set to " + trumpCategory);
                                }
                            }
                            currentCard = card;
                            humanPlayer.removeFromHand(cardName);
                        } else {
                            System.out.println("Not a valid card");
                        }
                        //Update current card panel
                        game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                        // Update hand panel
                        game.handPnl.addHand(humanPlayer.getHand());
                        game.validate();
                        game.repaint();
                        ((GUIHumanPlayer) players[0]).printHand();

                        if (humanPlayer.getHandSize() == 0) {
                            System.out.println("****************" + humanPlayer.getName() + " Finished**************");
                            humanPlayer.finish();
                            winners.add(humanPlayer.getName());
                        }

                        // Check if game is finished
                        if (winners.size() == (numberOfPlayers - 1)) {
                            gameFinished = true;
                            gameOverDialog.setVisible(true);

                            // Display winners
                            System.out.println("Game.Game over!");
                            System.out.println("The winner is " + winners.get(0));
                            System.out.println("2nd place goes to " + winners.get(1));
                            if (winners.size() > 2) {
                                System.out.println("3rd place goes to " + winners.get(2));
                            }
                            for (int i = 3; i < winners.size(); i++) {
                                System.out.println("" + (i - 1) + "th place goes to " + winners.get(i));
                            }
                        }

                        if (turnFinished) {
                            AIPlay();
                        }
                    }
                }
            }.start();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    public static NewMouseListener deckMouseListener=new NewMouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            new Thread() {
                public void run() {
                    if (isEnabled()) {
                        // Pick up card if deck still has cards
                        if (deck.length() > 0) {
                            players[0].setCard(deck.dealCard());
                        }
                        if (deck.length() == 0) {
                            game.cardPnl.deckPnl.clear();
                        }
                        players[0].deactivate();
                        handMouseListener.setEnabled(false);
                        deckMouseListener.setEnabled(false);
                        turnFinished = true;
                        game.addPlayerHand(players[0].getHand());
                        AIPlay();
                    }
                }
            }.start();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private static void AIPlay() {
        playerID++;
        if(playerID==numberOfPlayers){
            playerID=0;
        }
        //Play while next player is an AIPlayer
        while (playerID!=0&!gameFinished){

            BasePlayer player=players[playerID];
            if(player.isActive()&!player.isFinished()) {
                // Check if player is the last active player
                if(numberOfActivePlayers()<=1){
                    System.out.println(player.getName()+" won the round!");
                    // Get trump category from player
                    trumpCategory= players[playerID].pickTrumpCategory();
                    System.out.println("Trump category is "+trumpCategory);
                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                    activateAllPlayers();
                    currentCard=null;
                    //Update current card panel
                    game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                    game.validate();
                    game.repaint();
                }
                // Player takes turn
                play(player);
                // Check if player has finished
                if (player.getHandSize() == 0) {
                    System.out.println("****************" + player.getName() + " Finished**************");
                    player.finish();
                    game.playersPanel.setPlayerState(playerID-1,"Finished");
                    winners.add(player.getName());
                }
            }

            // Check if game is finished
            if(winners.size()==(numberOfPlayers-1)){
                gameFinished=true;
                gameOverDialog.setVisible(true);
                // Display winners
                System.out.println("Game.Game over!");
                System.out.println("The winner is "+winners.get(0));
                System.out.println("2nd place goes to "+winners.get(1));
                if(winners.size()>2) {
                    System.out.println("3rd place goes to " + winners.get(2));
                }
                for(int i=3;i<winners.size();i++){
                    System.out.println(""+(i-1)+"th place goes to "+winners.get(i));
                }
            }

            playerID++;
            if(playerID==numberOfPlayers& players[0].isActive()&!players[0].isFinished()){
                playerID=0;
            }else if(playerID==numberOfPlayers){
                playerID=1;
            }
        }

        if(players[0].isActive()&!players[0].isFinished()) {

            if(numberOfActivePlayers()<=1){
                System.out.println(players[0].getName()+" won the round!");
                if(playerID>0){
                    game.playersPanel.setPlayerLabel(playerID - 1, "Won the round!");
                }
                activateAllPlayers();
                currentCard=null;
                //Update current card panel
                game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                game.validate();
                game.repaint();
                trumpDialog.setVisible(true);
            }
            handMouseListener.setEnabled(true);
            deckMouseListener.setEnabled(true);
        }

    }


    private static void startNewGame(){
        // Create players
        players = new BasePlayer[numberOfPlayers];
        // Create human player
        players[0] = new GUIHumanPlayer(humanPlayerName);

        // Create Players.AIPlayer
        Collections.shuffle(aINames,random);
        System.out.println("The AI players are:");
        for(int i=1; i<(numberOfPlayers);i++){
            players[i]=new AIPlayer(aINames.get(i-1));
            game.setPlayerName(i-1, aINames.get(i-1));
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
        ((GUIHumanPlayer)players[0]).printHand();
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
        new Thread() {
            public void run() {
                if (firstPlayer != 0) {
                    // Get trump category from first player
                    trumpCategory = players[firstPlayer].pickTrumpCategory();
                    System.out.println("Trump category is " + trumpCategory);
                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                    // Get first card
                    currentCard = players[firstPlayer].playCard(trumpCategory);
                    System.out.println(players[playerID].getName() + " Played " + currentCard.getCardTitle() + ".");
                    game.playersPanel.setPlayerLabel(playerID-1,"Played " + currentCard.getCardTitle());

                    System.out.println(currentCard.getCategoryDetails(trumpCategory) + ".\n");
                    // Set next player
                    playerID++;
                    if (playerID == numberOfPlayers) {
                        playerID = 0;
                    }
                    //Play while next player is an AIPlayer
                    while (playerID != 0 & !gameFinished) {
                        play(players[playerID]);
                        playerID++;
                        if (playerID == numberOfPlayers) {
                            playerID = 0;
                        }
                    }
                    //Update current card panel
                    game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                    game.validate();
                    game.repaint();
                    handMouseListener.setEnabled(true);
                    deckMouseListener.setEnabled(true);
                } else {
                    // Get trump category from player
                    trumpDialog.setVisible(true);
                }
            }
        }.start();
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
                if(deck.length()==0){
                    game.cardPnl.deckPnl.clear();
                }
                player.deactivate();
                System.out.println(player.getName() + " passed.\n");
                game.playersPanel.setPlayerLabel(playerID-1,"Passed");
            } else if (newCard.getCardType().equals("Trump")) {
                // Activate all payers
                activateAllPlayers();
                // Print played card
                System.out.println(player.getName() + " played " + newCard.getCardTitle()+".");
                game.playersPanel.setPlayerLabel(playerID-1,"Played " + newCard.getCardTitle());
                // set trump category
                if (newCard.getCardTitle().equals("The Geologist")) {
                    trumpCategory = player.pickTrumpCategory();
                } else {
                    trumpCategory = ((TrumpCard) newCard).getSubtitle();
                }
                System.out.println(newCard.getCategoryDetails(trumpCategory)+".\n");
                System.out.println("Trump Category is now " + trumpCategory);
                game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                // Set current card as new card
                currentCard = newCard;
                //Update current card panel
                game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                game.validate();
                game.repaint();
                // replay since trump card was played
                play(player);
            } else {
                System.out.println(player.getName() + " played " + newCard.getCardTitle()+".");
                game.playersPanel.setPlayerLabel(playerID-1,"Played " + newCard.getCardTitle());
                System.out.println(newCard.getCategoryDetails(trumpCategory)+".\n");
                // Check if round winning combination was played
                if(currentCard!=null){
                    if(currentCard.getCardTitle().equals("The Geophysicist")&newCard.getCardTitle().equals("Magnetite")){
                        System.out.println(player.getName()+" played the round winning combination!");
                        game.playersPanel.setPlayerLabel(playerID-1,"Played the round winning combination!");
                        activateAllPlayers();
                        // Player restarts new round
                        play(player);
                    }
                }
                currentCard = newCard;
                //Update current card panel
                game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                game.validate();
                game.repaint();
            }
        }
    }


    private static void activateAllPlayers(){
        int i=0;
        for(BasePlayer player:players){
            player.activate();
            if(i>0) {
                game.playersPanel.setPlayerLabel(i - 1, "");
            }
            i++;
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
