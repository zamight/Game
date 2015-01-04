import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameTerrain {

    public int[][] arrayItems = new int[50][50];
    public int width = 16;
    public int height = 16;
    public GameSpriteImage gameSpriteImage;
    public int[] pointX;
    public int[] pointY;
    public int TerrainID = 0;

    public void loadImagePallet(GameSpriteImage gameSpriteImage1) {
        gameSpriteImage = gameSpriteImage1;
        int rows = gameSpriteImage.image.getWidth(null) / 16;
        int cols = gameSpriteImage.image.getHeight(null) / 16;
        int row = 0;
        int col = 0;
        int i = 0;

        pointX = new int[rows * cols];
        pointY = new int[rows * cols];

        while(cols > col) {
            while(rows > row) {
                pointY[i] = col * 16;
                pointX[i] = row * 16;
                ++row;
                i++;
            }
            row = 0;
            ++col;
        }
        //System.exit(1);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setItem(int x, int y) {
        arrayItems[y][x] = TerrainID;
    }

    public int getTerrainID() {
        return TerrainID;
    }

    public void setTerrain(int[][] terrain) {
        arrayItems = terrain;
    }

    public void setTerrainID(int terrainID) {
        TerrainID = terrainID;
    }
}
