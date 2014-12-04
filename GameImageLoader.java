import javax.swing.*;
import java.awt.*;

/**
 * Created by zam on 11/30/2014.
 */
public class GameImageLoader {

    public Image Loader(String i) {
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/" + i + ".png"));
        return imageIcon.getImage();
    }

}
