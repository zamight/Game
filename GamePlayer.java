import java.awt.*;

public class GamePlayer {

    public int id = 0;
    public GameSpriteImage gameSpriteImage;
    public int x;
    public int y;
    public int SPEEDDefault = 10;
    public int SPEEDX = 0;
    public int SPEEDY = 0;
    public int rx;
    public int ry;
    public int HEALTH = 1;
    public int woodcuttingEXP = 0;
    public String name = "Zamight";

    public GamePlayer(int id, GameSpriteImage gameSpriteImage, int x, int y, String name) {
        this.gameSpriteImage = gameSpriteImage;
        this.x = x;
        this.y = y;
        this.gameSpriteImage.x = x;
        this.gameSpriteImage.y = y;
        this.id = id;
        this.name = name;
    }

    public void Reverse() {
        this.x = rx;
        this.y = ry;
    }

    public void moveRight() {
        rx = x;
        ry = y;

        x += SPEEDX;
        gameSpriteImage.x += SPEEDX;
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

        SPEEDX = 0;
    }

    public void moveLeft() {
        rx = x;
        ry = y;

        x += SPEEDX;
        if(x < 0) {
            x = 0;
        }

        gameSpriteImage.x += SPEEDX;
        gameSpriteImage.frameY = (gameSpriteImage.height * 2) + gameSpriteImage.y2;
        gameSpriteImage.frameX += gameSpriteImage.width;
        if(gameSpriteImage.frameX >= (gameSpriteImage.width * 2) + gameSpriteImage.x2) {
            gameSpriteImage.frameX = 0 + gameSpriteImage.x2;
        }
        SPEEDX = 0;
    }

    public void moveUp() {
        rx = x;
        ry = y;

        y += SPEEDY;
        if(y < 0){
            y = 0;
        }

        gameSpriteImage.y += SPEEDY;
        gameSpriteImage.frameY = (gameSpriteImage.height * 1) + gameSpriteImage.y2;
        gameSpriteImage.frameX += gameSpriteImage.width;
        if(gameSpriteImage.frameX >= 64 + gameSpriteImage.x2) {
            gameSpriteImage.frameX = 0 + gameSpriteImage.x2;
        }
        SPEEDY = 0;
    }

    public void moveDown() {
        rx = x;
        ry = y;

        y += SPEEDY;

        gameSpriteImage.y += SPEEDY;
        gameSpriteImage.frameY = (gameSpriteImage.height * 0) + gameSpriteImage.y2;
        gameSpriteImage.frameX += gameSpriteImage.width;
        if(gameSpriteImage.frameX >= 64 + gameSpriteImage.x2) {
            gameSpriteImage.frameX = 0 + gameSpriteImage.x2;
        }
        SPEEDY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void gainWoodcuttingExp(int exp) {
        this.woodcuttingEXP += exp;
    }
}
