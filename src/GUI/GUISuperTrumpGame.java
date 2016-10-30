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
 * This class implements the logig for the game and controls the user interface
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
        // Create and open menu
        menu=new JMenuFrame();
        menu.setVisible(true);
    }

    public static ActionListener NewGameActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get game parameters
            numberOfPlayers=menu.getNumberOfPlayers();
            humanPlayerName=menu.getPlayerName();
            // Hide menu
            menu.setVisible(false);
            // Create game
            game=new JGameFrame(numberOfPlayers-1);
            trumpDialog=new JChooseTrumpDialog();
            trumpDialog.setVisible(false);
            handMouseListener.setEnabled(false);
            deckMouseListener.setEnabled(false);
            // Show game
            game.setVisible(true);
            game.setInfoLabel("Nice to meet you "+humanPlayerName);
            startNewGame();
        }
    };

    public static ActionListener GameOverListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Remove frames from old game
            gameOverDialog.setVisible(false);
            game.dispose();
            trumpDialog.dispose();
            // Clear parameters of old game
            turnFinished=false;
            gameFinished=false;
            winners.removeAll(winners);
            // Show menu
            menu.setVisible(true);
            menu.validate();
            menu.repaint();
        }
    };

    public static ActionListener SetTrumpCategoryListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Set trump category
            trumpCategory=trumpDialog.getTrumpCategory();
            // Update labels
            game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
            game.setInfoLabel("You set the trump category to "+trumpCategory+" it is your turn to play!");
            // Clear current card
            currentCard=null;
            // Allow user to play
            handMouseListener.setEnabled(true);
            deckMouseListener.setEnabled(true);
            // Hide dialog
            trumpDialog.setVisible(false);
        }
    };

    public static NewMouseListener handMouseListener=new NewMouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            new Thread() {
                public void run() {
                    GUIHumanPlayer humanPlayer = (GUIHumanPlayer) players[0];

                    if (isEnabled() & humanPlayer.isActive() & !humanPlayer.isFinished()) {
                        turnFinished = false;
                        // Get card that was played
                        JLabel cardClicked = (JLabel) e.getSource();
                        String cardName = cardClicked.getName();
                        BaseCard card = humanPlayer.getCardFromHand(cardName);

                        if (currentCard == null) {
                            //If there is no last card card played is valid
                            humanPlayer.removeFromHand(cardName);
                            currentCard = card;
                            if (card.getCardType().equals("Play")) {
                                handMouseListener.setEnabled(false);
                                deckMouseListener.setEnabled(false);
                                turnFinished = true;
                                game.setInfoLabel("You played "+card.getCardTitle());
                            } else {
                                activateAllPlayers();
                                if (card.getCardTitle().equals("The Geologist")){
                                    trumpDialog.setVisible(true);
                                } else {
                                    trumpCategory = ((TrumpCard) card).getSubtitle();
                                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                                }
                                game.setInfoLabel("You played "+card.getCardTitle()+" play again!");
                            }
                        } else if (humanPlayer.validCard(trumpCategory, currentCard, card)) {
                            if (card.getCardType().equals("Play")) {
                                //Check for winning combination
                                if(currentCard.getCardTitle().equals("The Geophysicist")&card.getCardTitle().equals("Magnetite")){
                                    game.setInfoLabel("You played the round winning combination!");
                                    activateAllPlayers();
                                    currentCard = null;
                                    //Update current card panel
                                    game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                                    trumpDialog.setVisible(true);
                                    turnFinished=false;
                                }else{
                                    turnFinished = true;
                                    game.setInfoLabel("You played "+card.getCardTitle());
                                    handMouseListener.setEnabled(false);
                                    deckMouseListener.setEnabled(false);
                                }
                            } else {
                                activateAllPlayers();
                                // Set trump category
                                if (card.getCardTitle().equals("The Geologist")) {
                                    trumpDialog.setVisible(true);
                                } else {
                                    trumpCategory = ((TrumpCard) card).getSubtitle();
                                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                                }
                                game.setInfoLabel("You played "+card.getCardTitle()+" play again!");
                            }
                            // Update current card
                            currentCard = card;
                            // Remove card from users hand
                            humanPlayer.removeFromHand(cardName);
                        } else {
                            game.setInfoLabel("Invalid selection you must beat the current card or pass!");
                        }
                        //Update current card panel
                        game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                        // Update hand panel
                        game.handPnl.addHand(humanPlayer.getHand());
                        game.validate();
                        game.repaint();

                        if (humanPlayer.getHandSize() == 0) {
                            game.setInfoLabel("You have finished the game");
                            humanPlayer.finish();
                            winners.add(humanPlayer.getName());
                        }

                        // Check if game is finished
                        if (winners.size() == (numberOfPlayers - 1)) {
                            gameFinished = true;
                            gameOverDialog=new JGameOverDialog(winners);
                            gameOverDialog.setVisible(true);
                        }

                        if (turnFinished) {
                            // Allow AIPlayers to take their turn
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
                        game.setInfoLabel("You passed!");
                        // Disable user from playing
                        handMouseListener.setEnabled(false);
                        deckMouseListener.setEnabled(false);
                        turnFinished = true;
                        // Update user hand
                        game.addPlayerHand(players[0].getHand());
                        // Allow AIPlayers to take their turn
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
                    game.playersPanel.setPlayerLabel(playerID - 1, "Won the round!");
                    try{
                        Thread.sleep(2500);}
                    catch (InterruptedException e){
                    // Continue
                    }
                    // Get trump category from player
                    trumpCategory= players[playerID].pickTrumpCategory();
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
                    player.finish();
                    game.setPlayerState(playerID-1,"Finished");
                    winners.add(player.getName());
                }
            }

            // Check if game is finished
            if(winners.size()==(numberOfPlayers-1)){
                gameFinished=true;
                // Open game over dialog
                gameOverDialog=new JGameOverDialog(winners);
                gameOverDialog.setVisible(true);
            }
            // Set ID to next player
            playerID++;
            if(playerID==numberOfPlayers& players[0].isActive()&!players[0].isFinished()){
                // Set playerID to human player if human player is active and has not finished
                playerID=0;
            }else if(playerID==numberOfPlayers){
                // Set playerID to first AIPlayer
                playerID=1;
            }
        }

        if(players[0].isActive()&!players[0].isFinished()) {

            if(numberOfActivePlayers()<=1){
                if(playerID>0){
                    game.playersPanel.setPlayerLabel(playerID - 1, "Won the round!");
                    game.setInfoLabel("It is your turn to play!");
                }else {
                    //Human player won the round
                    game.setInfoLabel("You won the round!");
                }
                activateAllPlayers();
                currentCard=null;
                //Update current card panel
                game.cardPnl.currentCardPnl.updateCurrentCard(currentCard);
                game.validate();
                game.repaint();
                trumpDialog.setVisible(true);
            }else{
                game.setInfoLabel("It is your turn to play!");
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
        for(int i=1; i<(numberOfPlayers);i++){
            players[i]=new AIPlayer(aINames.get(i-1));
            game.setPlayerName(i-1, aINames.get(i-1));
        }
        // activate all players
        activateAllPlayers();

        //Create deck and deal cards
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
        // Show user their cards
        game.addPlayerHand(players[0].getHand());
        // Select first player
        firstPlayer=random.nextInt(numberOfPlayers);
        //firstPlayer=1;
        playerID=firstPlayer;
        new Thread() {
            public void run() {
                if (firstPlayer != 0) {
                    // Get trump category from first player
                    trumpCategory = players[firstPlayer].pickTrumpCategory();
                    // Update trump category label
                    game.cardPnl.trumpCategoryLabel.setText(trumpCategory);
                    // Get first card
                    currentCard = players[firstPlayer].playCard(trumpCategory);
                    game.playersPanel.setPlayerLabel(playerID-1,"Played " + currentCard.getCardTitle());

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
                game.playersPanel.setPlayerLabel(playerID-1,"Passed");
            } else if (newCard.getCardType().equals("Trump")) {
                // Activate all payers
                activateAllPlayers();
                // Print played card
                game.playersPanel.setPlayerLabel(playerID-1,"Played " + newCard.getCardTitle());
                // set trump category
                if (newCard.getCardTitle().equals("The Geologist")) {
                    trumpCategory = player.pickTrumpCategory();
                } else {
                    trumpCategory = ((TrumpCard) newCard).getSubtitle();
                }
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
                game.playersPanel.setPlayerLabel(playerID-1,"Played " + newCard.getCardTitle());
                // Check if round winning combination was played
                if(currentCard!=null){
                    if(currentCard.getCardTitle().equals("The Geophysicist")&newCard.getCardTitle().equals("Magnetite")){
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
