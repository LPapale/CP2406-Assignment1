/**
 * Created by Admin on 23/09/2016.
 */
public class TrumpCard extends BaseCard {
    private String subtitle;
    public TrumpCard(String title, String fileName, String imageName, String subtitle){
        super("Trump", title, fileName, imageName);
        this.subtitle=subtitle;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
