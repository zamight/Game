import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zam on 12/22/2014.
 */
public class GameServer extends JFrame {

    public CopyOnWriteArrayList<GameServerSocket> gameServerSocketList = new CopyOnWriteArrayList<GameServerSocket>();
    public Iterator<GameServerSocket> iterGameSockets;
    public int playerCount = 0;
    public GameCollision gameCollision = new GameCollision();
    public GameTerrain gameTerrain;
    public GameOpen gameOpen;
    public String lastCMD;

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public GameServer() {

        setVisible(true);
        JPanel jPanel = new JPanel();
        Button button = new Button("Delete All Threads");
        jPanel.add(button);
        Button buttonResend = new Button("Resend Last Command!");
        jPanel.add(buttonResend);
        add(jPanel);

        gameOpen = new GameOpen();
        GameImageLoader gameImageLoader = new GameImageLoader();

        //Terrain
        final GameSpriteImage mapTile = new GameSpriteImage(gameImageLoader.Loader("maptile"), 0, 0, 16, 16);

        gameTerrain = new GameTerrain();
        gameTerrain.setTerrain(gameOpen.openTerrain("terrain_1"));
        gameTerrain.loadImagePallet(mapTile);
        //End Terrain

        gameCollision.linkGameTerrain(gameTerrain);

        gameCollision.addCollision(3);
        gameCollision.addCollision(4);
        gameCollision.addCollision(5);
        gameCollision.addCollision(6);



        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameServerSocketList.clear();
            }
        });
        buttonResend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendAllPlayerCommands(lastCMD);
            }
        });

    }

    public void addPlayer(GameServerSocket gameServerSocket) {
        gameServerSocketList.add(gameServerSocket);

        playerCount++;

    }

    public void sendPlayerToEveryone(GamePlayer gamePlayer) {
        iterGameSockets = gameServerSocketList.iterator();
        GameServerSocket gameServerSockets;
        while (iterGameSockets.hasNext()) {
            gameServerSockets = iterGameSockets.next();
            gameServerSockets.eMessage(gamePlayer.id + ":makeCharacter:" + gamePlayer.gameSpriteImage.x
                    + ":" + gamePlayer.gameSpriteImage.y + ":" + gamePlayer.name + ",");
            System.out.println("Broadcast(New Player): " + gamePlayer.getName() + "(" + gamePlayer.id + ")");
        }
    }

    public void sendAllPlayerCommands(String s) {
        iterGameSockets = gameServerSocketList.iterator();
        GameServerSocket gameServerSockets;
        while (iterGameSockets.hasNext()) {
            gameServerSockets = iterGameSockets.next();
                gameServerSockets.eMessage(s);
            System.out.println("Broadcast(Command): " + s);
        }
    }

}
