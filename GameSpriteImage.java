import java.awt.*;

/**
 * Created by zam on 12/2/2014.
 */
public class GameSpriteImage {

    //Dont need x,y; Player class has it.
    int x, y;
    public int x2 = 0;
    public int y2 = 0;
    public int width, height;
    public int frameX, frameY;
    public Image image;

    public GameSpriteImage(Image image, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frameX = 0;
        this.frameY = 0;
        this.image = image;
    }

}
