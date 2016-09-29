
public abstract class BaseCard {
    private String cardType;
    private String cardTitle;
    private String fileName;
    private String imageName;

    public BaseCard(String type, String title, String fileName, String imageName){
        this.cardType=type;
        this.cardTitle=title;
        this.fileName=fileName;
        this.imageName=imageName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImageName() {
        return imageName;
    }

    public abstract String getCardDetails();

    public abstract String getCategoryDetails(String trumpCategory);
}
