import java.awt.*;

public class GameEditor {

    public GameMouse gameMouse;
    public GameRectangle gameRectangle;

    public GameEditor(GameMouse gameMouse) {
        this.gameMouse = gameMouse;
        gameRectangle = new GameRectangle(0, 0, 25, 25, new Color(255,0,0));
    }

    public GameRectangle gameRectangle() {
        gameRectangle.setX(gameMouse.idX * 25);
        gameRectangle.setY(gameMouse.idY * 25);
        return gameRectangle;
    }

    public void resetGameRectanglePosition() {
        gameRectangle.setX(gameMouse.idX * 25);
        gameRectangle.setY(gameMouse.idY * 25);
    }

}
