import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainGame extends JFrame implements Runnable {

    public GameDisplay gameDisplay = new GameDisplay();
    public Thread thread;
    public GameImageLoader gameImageLoader = new GameImageLoader();
    public CopyOnWriteArrayList<GamePlayer> gamePlayer = new CopyOnWriteArrayList<GamePlayer>();
    public GameCollision gameCollision;
    public GameTerrain gameTerrain;
    public GameEditor gameEditor;
    public GameMouse gameMouse;
    public GameSpriteImage gamePlayerSpriteImage;
    public GameImage gameImageTree;
    public GameImage gameCrownAdmin;
    public GameSave gameSave = new GameSave();
    public GameClientSocket gameClientSocket;
    public Iterator<GamePlayer> iterGamePlayer;
    public Color textColor = new Color(0,0,255);

    public MainGame(String title, GameClientSocket gameSocket){

        gameClientSocket = gameSocket;

        gameCollision = new GameCollision();

        gamePlayerSpriteImage = new GameSpriteImage(gameImageLoader.Loader("playerLayout"), 100, 70, 16, 18);

        //Images
        //gamePlayer = new GamePlayer(gamePlayerSpriteImage, 0, 0);
        //gameCrownAdmin = new GameImage(gameImageLoader.Loader("zamCrown"), 0, 0);

        //Terrain
        final GameSpriteImage mapTile = new GameSpriteImage(gameImageLoader.Loader("maptile"), 0, 0, 16, 16);

        gameImageTree = new GameImage(gameImageLoader.Loader("tree"), 50, 50);
        GameOpen gameOpen = new GameOpen();

        gameTerrain = new GameTerrain();
        gameTerrain.setTerrain(gameOpen.openTerrain("terrain_1"));
        gameTerrain.loadImagePallet(mapTile);
        //End Terrain
        gameMouse = new GameMouse(gameTerrain);
        gameEditor = new GameEditor(gameMouse);

        gameDisplay.linkTerrain(gameTerrain);
        gameCollision.linkGameTerrain(gameTerrain);


        gameDisplay.addMouseListener(gameMouse);
        gameDisplay.addKeyListener(new GameButton(gameCollision, this));
        gameDisplay.addMouseMotionListener(gameMouse);

        gameDisplay.setFocusable(true);

        //DevMenuBar
        JMenuBar jMenuBar = new JMenuBar();
            JMenu jMenuTerrain = new JMenu("Terrain");
                JMenuItem jMenuItemTerrainChangePathDirt = new JMenuItem("Change To Dirt");
                JMenuItem jMenuItemTerrainChangePathSideDirt = new JMenuItem("Change To Side Dirt");
                JMenuItem jMenuItemTerrainChangePathUpDirt = new JMenuItem("Change To Up Dirt");
                JMenuItem jMenuItemTerrainChangeGrass = new JMenuItem("Change To Grass");
        JMenuItem jMenuItemTerrainChangeExtended = new JMenuItem("Extended");


        jMenuItemTerrainChangePathDirt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTerrain.setTerrainID(3);
            }
        });
        jMenuItemTerrainChangePathSideDirt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTerrain.setTerrainID(2);
            }
        });
        jMenuItemTerrainChangePathUpDirt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTerrain.setTerrainID(1);
            }
        });
        jMenuItemTerrainChangeGrass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTerrain.setTerrainID(0);
            }
        });
        jMenuItemTerrainChangeExtended.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: get rid of this!
                JFrame gameTileSelector = new JFrame();
                GameTileSelector gameTile = new GameTileSelector(mapTile.image, gameTerrain);
                gameTileSelector.add(gameTile);
                gameTileSelector.setSize(225,225);
                gameTileSelector.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                gameTileSelector.setTitle("Tile Selector");
                gameTileSelector.setLocationRelativeTo(null);
                gameTileSelector.setVisible(true);
                gameTile.setFocusable(true);
            }
        });

        jMenuTerrain.add(jMenuItemTerrainChangePathDirt);
        jMenuTerrain.add(jMenuItemTerrainChangePathSideDirt);
        jMenuTerrain.add(jMenuItemTerrainChangePathUpDirt);
        jMenuTerrain.add(jMenuItemTerrainChangeGrass);
        jMenuTerrain.add(jMenuItemTerrainChangeExtended);

        JMenu jMenuPlayer = new JMenu("Player");
        JMenuItem jMenuItemChangePlayer1 = new JMenuItem("Player 1");
        JMenuItem jMenuItemChangePlayer2 = new JMenuItem("Player 2");
        JMenuItem jMenuItemChangePlayer3 = new JMenuItem("Player 3");

        jMenuItemChangePlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayerSpriteImage.x2 = 0;
                gamePlayerSpriteImage.y2 = 0;
                gamePlayerSpriteImage.frameX = 0;
                gamePlayerSpriteImage.frameY = 0;
            }
        });

        jMenuItemChangePlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayerSpriteImage.x2 = 64;
                gamePlayerSpriteImage.y2 = 0;
                gamePlayerSpriteImage.frameX = 64;
                gamePlayerSpriteImage.frameY = 0;
            }
        });

        jMenuItemChangePlayer3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayerSpriteImage.x2 = 128;
                gamePlayerSpriteImage.y2 = 0;
                gamePlayerSpriteImage.frameX = 128;
                gamePlayerSpriteImage.frameY = 0;
            }
        });

        jMenuPlayer.add(jMenuItemChangePlayer1);
        jMenuPlayer.add(jMenuItemChangePlayer2);
        jMenuPlayer.add(jMenuItemChangePlayer3);

        JMenu jMenuFile = new JMenu("File");
        JMenuItem jMenuItemFileSave = new JMenuItem("Save Terrain");
        jMenuItemFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    gameSave.saveTerrain("terrain_1", gameTerrain.arrayItems);
                    System.out.println("Attempting to save...");
            }
        });

        jMenuFile.add(jMenuItemFileSave);


        jMenuBar.add(jMenuTerrain);
        jMenuBar.add(jMenuPlayer);
        jMenuBar.add(jMenuFile);
        setJMenuBar(jMenuBar);
        //End DevMenuBar

        add(gameDisplay);
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
        thread = new Thread(this, "Game");
        thread.start();

       gameCollision.addCollision(3);
        gameCollision.addCollision(4);
        gameCollision.addCollision(5);
        gameCollision.addCollision(6);
    }

    @Override
    public void run() {

        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 50;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        GameRectangle gameRectangle1 = new GameRectangle(48, 0, 16, 16, new Color(255,0,0));
        GameRectangle gameRectangle2 = new GameRectangle(64, 0, 16, 16, new Color(255,0,0));
        GameRectangle gameRectangle3 = new GameRectangle(80, 0, 16, 16, new Color(255,0,0));
        GameRectangle gameRectangle4 = new GameRectangle(96, 0, 16, 16, new Color(255,0,0));


        while(true){

            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);
            double fps = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;

            try{
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );

                gameDisplay.drawString(fps + "", 10, 10, textColor);
                gameDisplay.drawRectangle(gameEditor.gameRectangle());

                gameDisplay.drawRectangle(gameRectangle1);
                gameDisplay.drawRectangle(gameRectangle2);
                gameDisplay.drawRectangle(gameRectangle3);
                gameDisplay.drawRectangle(gameRectangle4);

                iterGamePlayer = gamePlayer.iterator();
                GamePlayer gamePlayerWhile;
                while (iterGamePlayer.hasNext()) {
                    gamePlayerWhile = iterGamePlayer.next();
                    gameDisplay.drawImagePortion(gamePlayerWhile.gameSpriteImage);
                    gameDisplay.drawString(gamePlayerWhile.getName(), gamePlayerWhile.gameSpriteImage.x+16, gamePlayerWhile.gameSpriteImage.y, textColor);
                }

                gameDisplay.repaint();
                gameDisplay.setDoubleBuffered(true);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ConcurrentModificationException cmx) {
                System.out.println("EAT A DICK");
            }
        }

    }

}