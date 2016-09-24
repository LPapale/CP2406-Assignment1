/**
 * Created by Admin on 24/09/2016.
 */
public class AIPlayer extends BasePlayer {
   public AIPlayer (String name){
       super(name,"AI");
   }


    @Override
    public BaseCard playCard(String trumpCategory, String currentValue) {
        int cardIndex=-1;
        String value=null;
        int currentCardIndex=-1;
        for (BaseCard card:hand){
            currentCardIndex++;
            if (card.getCardTitle().equals("Play")){
                PlayCard playCard=(PlayCard) card;


            }else if (card.getCardTitle().equals("Trump")){
                TrumpCard trumpCard=(TrumpCard) card;
            }
        }
        return null;
    }
}
