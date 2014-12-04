import java.awt.*;
import java.util.ArrayList;

public class GameTerrain {

    public int[][] arrayItems = new int[50][50];
    public int width = 25;
    public int height = 25;
    public Image imageUpDirt;
    public Image imageSideDirt;
    public Image imageDirt;
    public int TerrainID = 0;

    public void loadRoad(Image image1, Image image2, Image image3) {
        this.imageUpDirt = image1;
        this.imageSideDirt = image2;
        this.imageDirt = image3;

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
