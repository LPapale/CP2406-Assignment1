package Deck;

import Cards.BaseCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class handles creating, shuffling and dealing of cards in the deck
 */
public class Deck {
    private final int numberOfCards=60;
    private ArrayList<BaseCard> deck=new ArrayList<>(numberOfCards);

    public Deck(ArrayList<BaseCard> cards){
        this.deck=cards;
    }

    public void shuffleDeck(){
        Random randomSeed=new Random(System.nanoTime());
        Collections.shuffle(deck, randomSeed);
    }

    public ArrayList<BaseCard> dealHand(){
        ArrayList<BaseCard> hand=new ArrayList<>(8);
        // Get first 8 cards and then remove them from the deck
        for(int i=0;i<8;i++){
            hand.add(deck.get(0));
            deck.remove(0);
        }
        return hand;
    }

    public BaseCard dealCard(){
        BaseCard card;
        // Get first card and then remove it from the deck
        card=deck.get(0);
        deck.remove(0);
        return card;
    }

    public int length(){
     return deck.size();
 }

}
