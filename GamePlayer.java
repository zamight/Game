import java.awt.*;

public class GamePlayer {

    public GameSpriteImage gameSpriteImage;
    public int x;
    public int y;
    public int SPEED = 10;
    public int rx;
    public int ry;
    public int HEALTH = 1;

    public GamePlayer(GameSpriteImage gameSpriteImage, int x, int y) {
        this.gameSpriteImage = gameSpriteImage;
        this.x = x;
        this.y = y;
    }

    public void Reverse() {
        this.x = rx;
        this.y = ry;
    }

    public void moveRight() {
        rx = x;
        ry = y;

        x += SPEED;
        gameSpriteImage.x += SPEED;
        gameSpriteImage.frameY = (gameSpriteImage.height * 2) + gameSpriteImage.y2;

           if(gameSpriteImage.frameX + gameSpriteImage.x2 < (gameSpriteImage.width * 2) + gameSpriteImage.x2) {
               gameSpriteImage.frameX = (gameSpriteImage.width * 2) + gameSpriteImage.x2;
           }
           else
           {
               gameSpriteImage.frameX += gameSpriteImage.width;
           }

        if(gameSpriteImage.frameX >= (gameSpriteImage.width * 4) + gameSpriteImage.x2) {
            gameSpriteImage.frameX = (gameSpriteImage.width * 2) + gameSpriteImage.x2;
        }
    }

    public void moveLeft() {
        rx = x;
        ry = y;

        x -= SPEED;
        if(x < 0) {
            x = 0;
        }

        gameSpriteImage.x -= SPEED;
        gameSpriteImage.frameY = (gameSpriteImage.height * 2) + gameSpriteImage.y2;
        gameSpriteImage.frameX += gameSpriteImage.width;
        if(gameSpriteImage.frameX >= (gameSpriteImage.width * 2) + gameSpriteImage.x2) {
            gameSpriteImage.frameX = 0 + gameSpriteImage.x2;
        }
    }

    public void moveUp() {
        rx = x;
        ry = y;

        y -= SPEED;
        if(y < 0){
            y = 0;
        }

        gameSpriteImage.y -= SPEED;
        gameSpriteImage.frameY = (gameSpriteImage.height * 1) + gameSpriteImage.y2;
        gameSpriteImage.frameX += gameSpriteImage.width;
        if(gameSpriteImage.frameX >= 64 + gameSpriteImage.x2) {
            gameSpriteImage.frameX = 0 + gameSpriteImage.x2;
        }
    }

    public void moveDown() {
        rx = x;
        ry = y;

        y += SPEED;

        gameSpriteImage.y += SPEED;
        gameSpriteImage.frameY = (gameSpriteImage.height * 0) + gameSpriteImage.y2;
        gameSpriteImage.frameX += gameSpriteImage.width;
        if(gameSpriteImage.frameX >= 64 + gameSpriteImage.x2) {
            gameSpriteImage.frameX = 0 + gameSpriteImage.x2;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSPEED() {
        return SPEED;
    }
}
