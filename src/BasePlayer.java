import java.util.ArrayList;


/**
 * Created by Admin on 23/09/2016.
 */
public abstract class BasePlayer {
    private String type;
    private ArrayList<BaseCard> hand=new ArrayList<>();

    public BasePlayer(String type){
        this.type=type;
    }

    public void setHand(ArrayList<BaseCard> hand){
        this.hand = hand;
    }

    public void setCard(BaseCard card){
        hand.add(card);
    }

    public int getHandSize(){
        return hand.size();
    }

    public abstract BaseCard playCard(String trumpCategory, String currentValue);
}
