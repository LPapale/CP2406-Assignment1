
public class BaseCard {
    private String cardType;
    private String cardName;
    private String fileName;
    private String imageName;

    public BaseCard(String type, String name, String fileName, String imageName){
        this.cardType=type;
        this.cardName=name;
        this.fileName=fileName;
        this.imageName=imageName;
    }

    public String getCardType() {
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
