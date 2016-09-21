
public class BaseCard {
    private enum CardType{TRUNP, PLAY};
    private CardType cardType;
    private String cardName;
    private String fileName;
    private String imageName;

    public BaseCard(CardType type, String name, String fileName, String imageName){
        this.cardType=type;
        this.cardName=name;
        this.fileName=fileName;
        this.imageName=imageName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImageName() {
        return imageName;
    }
}
