import java.awt.*;

public class GameEditor {

    public GameMouse gameMouse;
    public GameRectangle gameRectangle;

    public GameEditor(GameMouse gameMouse) {
        this.gameMouse = gameMouse;
        gameRectangle = new GameRectangle(0, 0, 16, 16, new Color(255,0,0));
    }

    public GameRectangle gameRectangle() {
        gameRectangle.setX(gameMouse.idX * 16);
        gameRectangle.setY(gameMouse.idY * 16);
        return gameRectangle;
    }

    public void resetGameRectanglePosition() {
        gameRectangle.setX(gameMouse.idX * 16);
        gameRectangle.setY(gameMouse.idY * 16);
    }

}
