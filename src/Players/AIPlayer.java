package Players;

import java.util.ArrayList;
import java.util.Random;
import Cards.BaseCard;
import Cards.PlayCard;
import Cards.TrumpCategoryArrays;

/**
 * This class implements the functionality of an AI player including playing and picking a trump category.
 */
public class AIPlayer extends BasePlayer {
    public AIPlayer(String name) {
        super(name, "AI");
    }

    @Override
    public BaseCard playCard(String trumpCategory, BaseCard lastCard) {
        int cardIndex = -1;
        BaseCard cardToPlay;
        // Pause
        try{
        Thread.sleep(1000);}
        catch (InterruptedException e){
            // Continue
        }
        if (lastCard.getCardType().equals("Play")) {
            PlayCard lastPlayCard = (PlayCard) lastCard;
            // Choose card based on trump category
            switch (trumpCategory){
                case "Hardness":
                    double hardnessToBeat = lastPlayCard.getHighestHardness();
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
        // Check if a valid play card was available
        if (cardIndex != -1) {
            cardToPlay = hand.get(cardIndex);
            hand.remove(cardIndex);
        } else {
            // Check if trump card is available
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
        // Pause
        try{
            Thread.sleep(1000);}
        catch (InterruptedException e){
            // Continue
        }
        // Choose lowest card in trump category
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
        // Check if valid card is available
        if (cardIndex != -1) {
            cardToPlay = hand.get(cardIndex);
            hand.remove(cardIndex);
        } else {
            //Check if trump card is available
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
        // Get trump categories
        ArrayList<String> trumpCategories= TrumpCategoryArrays.getTrumpCategoriesArray();
        // Select random category
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
            // Get card that has the lowest value that beats the current value
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
            // Get card that has the lowest value that beats the current value
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
            // Get card that has the lowest value that beats the current value
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
            // Get card that has the lowest value that beats the current value
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
            // Get card that has the lowest value that beats the current value
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
        // get  the index of the last trump card
        for (BaseCard card : hand) {
            currentCardIndex++;
            if (card.getCardType().equals("Trump")) {
                cardIndex = currentCardIndex;
            }
        }
        return cardIndex;
    }
}