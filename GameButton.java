import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by zam on 12/1/2014.
 */
public class GameButton implements KeyListener {

    public GameCollision gameCollision;
    private MainGame mainGame;

    public GameButton(GameCollision gameCollision, MainGame mainGame) {
        this.gameCollision = gameCollision;
        this.mainGame = mainGame;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT :
                mainGame.gameClientSocket.iMessage("Key:Left");
                break;
            case KeyEvent.VK_RIGHT :
                mainGame.gameClientSocket.iMessage("Key:Right");
                break;
            case KeyEvent.VK_UP :
                mainGame.gameClientSocket.iMessage("Key:Up");
                break;
            case KeyEvent.VK_DOWN :
                mainGame.gameClientSocket.iMessage("Key:Down");
                break;
            case KeyEvent.VK_L :
                mainGame.gameClientSocket.iMessage("You Pressed Left!");
                System.out.println("??????????????????????");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
