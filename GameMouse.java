import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by zam on 12/1/2014.
 */
public class GameMouse implements MouseListener, MouseMotionListener {

    public GameTerrain gameTerrain;
    int idX, idY;

    public GameMouse(GameTerrain gameTerrain){
        this.gameTerrain = gameTerrain;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            idX = e.getX() / 25;
            idY = e.getY() / 25;
            gameTerrain.setItem(idX, idY);
            System.out.println("x: " + idX);
            System.out.println("y: " + idY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        idX = e.getX() / 25;
        idY = e.getY() / 25;
    }
}
