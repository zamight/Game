import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MainGame extends JFrame implements Runnable {

    public GameDisplay gameDisplay = new GameDisplay();
    public Thread thread;
    public GameImageLoader gameImageLoader = new GameImageLoader();
    public GamePlayer gamePlayer;
    public GameCollision gameCollision;
    public GameTerrain gameTerrain;
    public GameEditor gameEditor;
    public GameMouse gameMouse;
    public GameSpriteImage gamePlayerSpriteImage;
    public GameImage gameImageTree;
    public GameSave gameSave = new GameSave();

    public MainGame(String title){

        gameCollision = new GameCollision();

        gamePlayerSpriteImage = new GameSpriteImage(gameImageLoader.Loader("playerLayout"), 50, 70, 16, 18);

        //Images
        gamePlayer = new GamePlayer(gamePlayerSpriteImage, 0, 0);

        //Terrain
        Image imageDirt1 = gameImageLoader.Loader("dirtRoad");
        Image imageDirt2 = gameImageLoader.Loader("sideDirtWalk");
        Image imageDirt3 = gameImageLoader.Loader("dirt");

        gameImageTree = new GameImage(gameImageLoader.Loader("tree"), 50, 50);
        GameOpen gameOpen = new GameOpen();

        gameTerrain = new GameTerrain();
        gameTerrain.setTerrain(gameOpen.openTerrain("terrain_1"));
        gameTerrain.loadRoad(imageDirt1, imageDirt2, imageDirt3);
        //End Terrain
        gameMouse = new GameMouse(gameTerrain);
        gameEditor = new GameEditor(gameMouse);


        gameDisplay.addMouseListener(gameMouse);
        gameDisplay.addKeyListener(new GameButton(gamePlayer));
        gameDisplay.addMouseMotionListener(gameMouse);

        gameDisplay.setFocusable(true);

        //DevMenuBar
        JMenuBar jMenuBar = new JMenuBar();
            JMenu jMenuTerrain = new JMenu("Terrain");
                JMenuItem jMenuItemTerrainChangePathDirt = new JMenuItem("Change To Dirt");
                JMenuItem jMenuItemTerrainChangePathSideDirt = new JMenuItem("Change To Side Dirt");
                JMenuItem jMenuItemTerrainChangePathUpDirt = new JMenuItem("Change To Up Dirt");
                JMenuItem jMenuItemTerrainChangeGrass = new JMenuItem("Change To Grass");


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

        jMenuTerrain.add(jMenuItemTerrainChangePathDirt);
        jMenuTerrain.add(jMenuItemTerrainChangePathSideDirt);
        jMenuTerrain.add(jMenuItemTerrainChangePathUpDirt);
        jMenuTerrain.add(jMenuItemTerrainChangeGrass);

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
    }

    public static void main(String[] args){
        new MainGame("Gayyy");
    }

    @Override
    public void run() {

        int i = 0;

        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 50;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int terrainX = 0;
        int terrainY = 0;

        while(true){

            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);
            double fps = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;

            try{
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );

                //Game Terrain Draw
                for (int[] s : gameTerrain.arrayItems) {
                    for(int terrainValue : s) {
                        if(terrainValue == 1) {
                            gameDisplay.drawImage(gameTerrain.imageUpDirt, gameTerrain.getWidth() * terrainX, gameTerrain.getHeight() * terrainY);
                        }
                        if(terrainValue == 2) {
                            gameDisplay.drawImage(gameTerrain.imageSideDirt, gameTerrain.getWidth() * terrainX, gameTerrain.getHeight() * terrainY);
                        }
                        if(terrainValue == 3) {
                            gameDisplay.drawImage(gameTerrain.imageDirt, gameTerrain.getWidth() * terrainX, gameTerrain.getHeight() * terrainY);
                        }
                        ++terrainX;
                    }
                    terrainX = 0;
                    ++terrainY;
                }
                terrainY = 0;

                gameDisplay.drawString(fps + "", 10, 10, new Color(0,0,255));
                gameDisplay.drawRectangle(gameEditor.gameRectangle());
                gameDisplay.drawImagePortion(gamePlayer);

                gameDisplay.drawImage(gameImageTree.getImage(), gameImageTree.getX(), gameImageTree.getY());
                gameCollision.addCollision(gamePlayer, gameImageTree);

                gameDisplay.repaint();

                i++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ConcurrentModificationException cmx) {
                System.out.println("EAT A DICK");
            }
        }

    }

}