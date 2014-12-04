import java.awt.*;

/**
 * Created by zam on 11/30/2014.
 */
public class GameCollision {

    public void addCollision(GamePlayer gamePlayer, GameImage gameImage) {

        Rectangle gamePlayerIntercept = new Rectangle(gamePlayer.getX(), gamePlayer.getY(),
                                                    gamePlayer.gameSpriteImage.width, gamePlayer.gameSpriteImage.height);
        Rectangle gameImageIntercept = new Rectangle(gameImage.getX(), gameImage.getY(),
                gameImage.getImage().getWidth(null), gameImage.getImage().getHeight(null));

        if(gamePlayerIntercept.intersects(gameImageIntercept)) {
            gamePlayer.Reverse();
        }
    }

}
