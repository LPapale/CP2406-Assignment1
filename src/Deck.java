import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Admin on 23/09/2016.
 */
public class Deck {
    private final int numberOfCards=60;
    private ArrayList<BaseCard> deck=new ArrayList<>(numberOfCards);

    public Deck(ArrayList<BaseCard> deck){
        this.deck=deck;
    }

    public void shuffleDeck(){
        Random randomSeed=new Random(System.nanoTime());
        Collections.shuffle(deck, randomSeed);
    }
 public ArrayList dealHand(){
     ArrayList<BaseCard> hand=new ArrayList<>(8);
     for(int i=0;i<8;i++){
         hand.add(deck.get(0));
         deck.remove(0);
     }
     return hand;
 }

 public BaseCard dealCard(){
     BaseCard card;
     card=deck.get(0);
     deck.remove(0);
     return card;
 }

 public int length(){
     return deck.size();
 }

}
