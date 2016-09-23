
public class BaseCard {
    private String cardTitle;
    private String cardName;
    private String fileName;
    private String imageName;

    public BaseCard(String title, String name, String fileName, String imageName){
        this.cardTitle=title;
        this.cardName=name;
        this.fileName=fileName;
        this.imageName=imageName;
    }

    public String getCardTitle() {
        return cardTitle;
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
