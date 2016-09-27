import java.util.ArrayList;


/**
 * Created by Admin on 23/09/2016.
 */


public abstract class BasePlayer {
    private String type;
    private String name;


    public BasePlayer(String name, String type){
        this.name=name;
        this.type=type;


    }
    protected ArrayList<BaseCard> hand=new ArrayList<>();

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

    public String getName(){
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract BaseCard playCard(String trumpCategory, BaseCard card);

    public abstract BaseCard playCard(String trumpCategory);

    public abstract String pickTrumpCategory();
}
