import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zam on 11/30/2014.
 */
public class GameDisplay extends JPanel {

    public CopyOnWriteArrayList<GameString> listGameString = new CopyOnWriteArrayList<GameString>();
    public CopyOnWriteArrayList<GameImage> listGameImage = new CopyOnWriteArrayList<GameImage>();
    public CopyOnWriteArrayList<GameRectangle> listGameRectangle = new CopyOnWriteArrayList<GameRectangle>();
    public CopyOnWriteArrayList<GamePlayer> listGameSpriteImage = new CopyOnWriteArrayList<GamePlayer>();

    public Iterator<GameString> iterGameString;
    public Iterator<GameImage> iterGameImage;
    public Iterator<GameRectangle> iterGameRectnagle;
    public Iterator<GamePlayer> iterGameGameSpriteImage;


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(147, 211, 88));
        iterGameImage = listGameImage.iterator();
        GameImage gameImage;
        while (iterGameImage.hasNext()) {
            gameImage = iterGameImage.next();
            g.drawImage(gameImage.image, gameImage.x, gameImage.y, null);
        }


        iterGameString = listGameString.iterator();
        GameString gameString;
        while (iterGameString.hasNext()) {
            gameString = iterGameString.next();
            g.setColor(gameString.color);
            g.drawString(gameString.s, gameString.x, gameString.y);
            g.setColor(Color.GRAY);
        }

        iterGameGameSpriteImage = listGameSpriteImage.iterator();
        GamePlayer gameSpriteImage;
        while (iterGameGameSpriteImage.hasNext()) {
            gameSpriteImage = iterGameGameSpriteImage.next();
            g.drawImage(gameSpriteImage.gameSpriteImage.image, gameSpriteImage.x, gameSpriteImage.y, gameSpriteImage.x + gameSpriteImage.gameSpriteImage.width,
                    gameSpriteImage.y + gameSpriteImage.gameSpriteImage.height, gameSpriteImage.gameSpriteImage.frameX, gameSpriteImage.gameSpriteImage.frameY,
                    gameSpriteImage.gameSpriteImage.frameX
                            + gameSpriteImage.gameSpriteImage.width, gameSpriteImage.gameSpriteImage.frameY + gameSpriteImage.gameSpriteImage.height, this);
        }

        iterGameRectnagle = listGameRectangle.iterator();
        GameRectangle gameRectangle;
        while (iterGameRectnagle.hasNext()) {
            gameRectangle = iterGameRectnagle.next();
            g.setColor(gameRectangle.getColor());
            g.drawRect(gameRectangle.getX(), gameRectangle.getY(), gameRectangle.getWidth(), gameRectangle.getHeight());
            g.setColor(Color.GRAY);
        }

        listGameString.clear();
        listGameImage.clear();
        listGameRectangle.clear();
    }

    public void drawString(String s, int x, int y, Color color) {
        listGameString.add(new GameString(s, x, y, color));
    }

    public void drawImage(Image image, int x, int y) {
        listGameImage.add(new GameImage(image, x, y));
    }

    public void drawRectangle(GameRectangle gameRectangle) {
        listGameRectangle.add(gameRectangle);
    }

    public void drawImagePortion(GamePlayer gameSpriteImage) {
        listGameSpriteImage.add(gameSpriteImage);
    }
}
