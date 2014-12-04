import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by zam on 12/1/2014.
 */
public class GameButton implements KeyListener {

    public GamePlayer gamePlayer;

    public GameButton(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePlayer.moveLeft();
        }
        else {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gamePlayer.moveRight();
            }
            else {

                if (e.getKeyCode() == KeyEvent.VK_UP) {

                    gamePlayer.moveUp();
                }
                else {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        gamePlayer.moveDown();
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
