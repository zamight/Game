import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ConcurrentModificationException;

public class GameTileSelector extends JPanel implements MouseListener {

    Image image;
    int x = 0;
    int y = 0;
    public GameTerrain gameTerrain;

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
        g.setColor(new Color(255,0,0));
        g.drawRect(x * 16, y * 16, 16, 16);
}

    public GameTileSelector(Image image, GameTerrain gameTerrain) {
        this.image = image;
        setFocusable(true);
        addMouseListener(this);
        this.gameTerrain = gameTerrain;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX() / 16;
        y = e.getY() / 16;
        int imageCols = image.getWidth(null) / 16; //Give us row Col Number
        System.out.println((imageCols * y)+ x);
        gameTerrain.setTerrainID((imageCols * y)+ x);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
