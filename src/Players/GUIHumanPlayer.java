package Players;

import Cards.BaseCard;
import Cards.PlayCard;
import Cards.TrumpCard;

/**
 * Created by Admin on 23/10/2016.
 */
public class GUIHumanPlayer extends BasePlayer{

    public GUIHumanPlayer(String name){
        super(name, "Human");
    }

    public boolean validCard(String trumpCategory, BaseCard lastCard, BaseCard newCard){
        boolean isValidChoice=false;
        if(lastCard.getCardType().equals("Trump")|newCard.getCardType().equals("Trump")){
            isValidChoice=true;
        }else {
            PlayCard newPlayCard=(PlayCard)newCard;
            PlayCard lastPlayCard=(PlayCard)lastCard;

            switch (trumpCategory) {
                case ("Hardness"):
                    if(newPlayCard.getHighestHardness()>lastPlayCard.getHighestHardness()){
                        isValidChoice=true;
                    }
                    break;
                case ("Specific gravity"):
                    if(newPlayCard.getHighestSpecificGravity()>lastPlayCard.getHighestSpecificGravity()){
                        isValidChoice=true;
                    }
                    break;
                case "Cleavage":
                    if (newPlayCard.getCleavageIndex() > lastPlayCard.getCleavageIndex()) {
                        isValidChoice=true;
                    }
                    break;
                case "Crustal abundance":
                    if (newPlayCard.getCrustalAbundanceIndex() > lastPlayCard.getCrustalAbundanceIndex()) {
                        isValidChoice=true;
                    }
                    break;
                case "Economic value":
                    if (newPlayCard.getEconomicValueIndex() > lastPlayCard.getEconomicValueIndex()) {
                        isValidChoice=true;
                    }
                    break;
            }
        }
        return isValidChoice;
    }

    public void removeFromHand(String cardName){
        int cardIndex=getCardIndex(cardName);
        hand.remove(cardIndex);
    }

    public BaseCard getCardFromHand(String cardName){
        int cardIndex=getCardIndex(cardName);
        BaseCard card=hand.get(cardIndex);
        return card;
    }

    private int getCardIndex(String cardName){
        int index=-1;
        String cardTitle;
        for(int i=0; i<getHandSize(); i++){
            cardTitle= hand.get(i).getCardTitle();
            if(cardTitle.equals(cardName)){
                index=i;
            }
        }
        return index;
    }

    public void printHand(){
        int i=0;
        for(BaseCard card:hand){
            i++;
            System.out.println(""+i+"."+card.getCardDetails()+".\n");
        }
    }

    @Override
    public String pickTrumpCategory() {
        return null;
    }

    @Override
    public BaseCard playCard(String trumpCategory) {
        return null;
    }

    @Override
    public BaseCard playCard(String trumpCategory, BaseCard card) {
        return null;
    }

}
