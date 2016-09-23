import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 23/09/2016.
 */
public class Deck {
    private final int numberOfCards=60;
    private List<BaseCard> deck=new ArrayList<>(numberOfCards);

    Deck{
        deck=DeckBuilder.newDeck;
    }

    public void shuffleDeck(){
        Random rand=new Random(System.nanoTime());
        Collections.shuffle(deck, rand);
    }
 public BaseCard[] getHand(){
     BaseCard[] hand=new BaseCard[8];
     for(int i=0;i<8;i++){
         hand[i]=deck.get(0);
         deck.remove(0);
     }
     return hand;
 }

 public BaseCard getCard(){
     BaseCard card=new BaseCard;
     card=deck.get(0);
     deck.remove(0);
     return card;
 }

 public int length(){
     return deck.size();
 }

}
