import java.awt.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zam on 11/30/2014.
 */
public class GameCollision {

    public CopyOnWriteArrayList<Integer> arrayListRectangle = new CopyOnWriteArrayList<Integer>();
    public Iterator<Integer> iteratorRectangle;
    public GameTerrain gameTerrain;
    Rectangle gamePlayerIntercept;

    public void addCollision(int i) {
        arrayListRectangle.add(i);
    }

    public void linkGameTerrain(GameTerrain gameTerrain) {
        this.gameTerrain = gameTerrain;
    }

    public boolean checkCollisions(GamePlayer gamePlayer) {

        gamePlayerIntercept = new Rectangle(gamePlayer.gameSpriteImage.x + gamePlayer.SPEEDX, gamePlayer.gameSpriteImage.y + gamePlayer.SPEEDY,
                gamePlayer.gameSpriteImage.width, gamePlayer.gameSpriteImage.height);


        iteratorRectangle = arrayListRectangle.iterator();
        int i;
        Rectangle rectangle;
        boolean col = false;
        while (iteratorRectangle.hasNext()) {
            i = iteratorRectangle.next();
            rectangle = new Rectangle(gameTerrain.pointX[i], gameTerrain.pointY[i],16,16);
            //System.out.println("Rectangle2 X: " + rectangle.getX() + " Y: " + rectangle.getY() + "," + rectangle.getWidth() + rectangle.getHeight());

            if(gamePlayerIntercept.getBounds().intersects(rectangle) || rectangle.getBounds().intersects(gamePlayerIntercept)) {
                System.out.println("IT supose to be working!");
                if(gamePlayer.SPEEDX != 0) {
                    gamePlayer.SPEEDX = 0;
                }
                if(gamePlayer.SPEEDY != 0) {
                    gamePlayer.SPEEDY = 0;
                }
                col = true;
            }
        }
        return col;
    }

}
