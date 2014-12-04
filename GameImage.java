import java.awt.*;

/**
 * Created by zam on 11/30/2014.
 */
public class GameImage {

    public Image image;
    public int x;
    public int y;

    public  GameImage(Image image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
