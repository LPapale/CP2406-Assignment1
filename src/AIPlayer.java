import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 24/09/2016.
 */
public class AIPlayer extends BasePlayer {
    public AIPlayer(String name) {
        super(name, "AI");
    }


    @Override
    public BaseCard playCard(String trumpCategory, BaseCard lastCard) {
       try{
        Thread.sleep(1000);}
        catch (InterruptedException e){
            // Continue
        }

        int cardIndex = -1;
        BaseCard cardToPlay;
        if (lastCard.getCardType().equals("Play")) {
            PlayCard lastPlayCard = (PlayCard) lastCard;

            switch (trumpCategory){
                case "Hardness":
                    double hardnessToBeat= lastPlayCard.getHighestHardness();
                    cardIndex=chooseHardnessCard(hardnessToBeat);
                    break;
                case "Specific gravity":
                    double specificGravityToBeat = lastPlayCard.getHighestSpecificGravity();
                    cardIndex=chooseSpecificGravityCard(specificGravityToBeat);
                    break;
                case "Cleavage":
                    int cleavageToBeat = lastPlayCard.getCleavageIndex();
                    cardIndex=chooseCleavageCard(cleavageToBeat);
                    break;
                case "Crustal abundance":
                    int crustalAbundanceToBeat = lastPlayCard.getCrustalAbundanceIndex();
                    cardIndex=chooseCrustalAbundanceCard(crustalAbundanceToBeat);
                    break;
                case "Economic value":
                    int economicValueToBeat = lastPlayCard.getEconomicValueIndex();
                    cardIndex=chooseEconomicValueCard(economicValueToBeat);
                    break;
            }

        } else {
            System.out.println("Last card is not a Play Card.");
        }

        if (cardIndex != -1) {
            cardToPlay = hand.get(cardIndex);
            hand.remove(cardIndex);
        } else {
            cardIndex=chooseTrumpCard();
            if (cardIndex != -1) {
                cardToPlay = hand.get(cardIndex);
                hand.remove(cardIndex);
            } else {
                cardToPlay=null;
            }
        }
        return cardToPlay;
    }

    @Override
    public BaseCard playCard(String trumpCategory) {
        int cardIndex = -1;
        BaseCard cardToPlay;

        switch (trumpCategory){
            case "Hardness":
                cardIndex=chooseHardnessCard(0);
                break;
            case "Specific gravity":
                cardIndex=chooseSpecificGravityCard(0);
                break;
            case "Cleavage":
                cardIndex=chooseCleavageCard(-1);
                break;
            case "Crustal abundance":
                cardIndex=chooseCrustalAbundanceCard(-1);
                break;
            case "Economic value":
                cardIndex=chooseEconomicValueCard(-1);
                break;
        }

        if (cardIndex != -1) {
            cardToPlay = hand.get(cardIndex);
            hand.remove(cardIndex);
        } else {
            cardIndex=chooseTrumpCard();
            if (cardIndex != -1) {
                cardToPlay = hand.get(cardIndex);
                hand.remove(cardIndex);
            } else {
                cardToPlay=null;
            }
        }


        return cardToPlay;
    }

    @Override
    public String pickTrumpCategory() {
        ArrayList<String> trumpCategories= TrumpCategoryArrays.getTrumpCategoriesArray();
        Random random=new Random();
        int index=random.nextInt(trumpCategories.size());
        return trumpCategories.get(index);
    }

    private int chooseHardnessCard(double hardnessToBeat) {
        double lowestPlayableHardness = Double.MAX_VALUE;
        int currentCardIndex = -1;
        int cardIndex = -1;
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Play")) {
                PlayCard currentPlayCard = (PlayCard) card;

                if ((currentPlayCard.getHighestHardness() > hardnessToBeat) & (currentPlayCard.getHighestHardness() < lowestPlayableHardness)) {
                    cardIndex = currentCardIndex;
                    lowestPlayableHardness = currentPlayCard.getHighestHardness();
                }

            }

        }
        return cardIndex;
    }

    private int chooseSpecificGravityCard(double specificGravityToBeat){
        double lowestPlayableGravity = Double.MAX_VALUE;
        int currentCardIndex = -1;
        int cardIndex = -1;
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Play")) {
                PlayCard currentPlayCard = (PlayCard) card;

                if ((currentPlayCard.getHighestSpecificGravity() > specificGravityToBeat) & (currentPlayCard.getHighestSpecificGravity() < lowestPlayableGravity)) {
                    cardIndex = currentCardIndex;
                    lowestPlayableGravity = currentPlayCard.getHighestSpecificGravity();
                }

            }

        }
        return cardIndex;
    }

    private int chooseCleavageCard(double cleavageToBeat){
        int lowestPlayableCleavage = Integer.MAX_VALUE;
        int currentCardIndex = -1;
        int cardIndex = -1;
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Play")) {
                PlayCard currentPlayCard = (PlayCard) card;

                if ((currentPlayCard.getCleavageIndex() > cleavageToBeat) & (currentPlayCard.getCleavageIndex() < lowestPlayableCleavage)) {
                    cardIndex = currentCardIndex;
                    lowestPlayableCleavage = currentPlayCard.getCleavageIndex();
                }

            }

        }
        return cardIndex;
    }

    private int chooseCrustalAbundanceCard(int crustalAbundanceToBeat){
        int lowestPlayableCrustalAbundance = Integer.MAX_VALUE;
        int currentCardIndex = -1;
        int cardIndex = -1;
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Play")) {
                PlayCard currentPlayCard = (PlayCard) card;

                if ((currentPlayCard.getCrustalAbundanceIndex() > crustalAbundanceToBeat) & (currentPlayCard.getCrustalAbundanceIndex() < lowestPlayableCrustalAbundance)) {
                    cardIndex = currentCardIndex;
                    lowestPlayableCrustalAbundance = currentPlayCard.getCrustalAbundanceIndex();
                }

            }

        }
        return cardIndex;
    }

    private int chooseEconomicValueCard(int economicValueToBeat){
        int lowestPlayableEconomicValue = Integer.MAX_VALUE;
        int currentCardIndex = -1;
        int cardIndex = -1;
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Play")) {
                PlayCard currentPlayCard = (PlayCard) card;

                if ((currentPlayCard.getEconomicValueIndex() > economicValueToBeat) & (currentPlayCard.getEconomicValueIndex() < lowestPlayableEconomicValue)) {
                    cardIndex = currentCardIndex;
                    lowestPlayableEconomicValue = currentPlayCard.getCrustalAbundanceIndex();
                }
            }
        }
        return cardIndex;
    }

    private int chooseTrumpCard(){
        int currentCardIndex = -1;
        int cardIndex = -1;
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Trump")) {

                    cardIndex = currentCardIndex;

            }
        }
        return cardIndex;
    }

}