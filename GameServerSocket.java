import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class GameServerSocket implements Runnable {

    private Socket connection;
    private String TimeStamp;
    private int ID;
    private GameServer gameServer;
    public GamePlayer gamePlayer;
    private GameImageLoader gameImageLoader = new GameImageLoader();
    private Iterator<GameServerSocket> iterGameSocket;
    private String returnCode = "";

    public int getID() {
        return ID;
    }

    public static void main(String[] args) {
        int port = 4321;
        int count = 0;
        try{
            ServerSocket socket1 = new ServerSocket(port);
            System.out.println("MultipleSocketServer Initialized");
            GameServer gameServer = new GameServer();
            while (true) {
                Socket connection = socket1.accept();
                Runnable runnable = new GameServerSocket(connection, count++, gameServer);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }
        catch (Exception e) {}
    }
    GameServerSocket(Socket s, int i, GameServer gameServer) {
        this.connection = s;
        this.ID = i;
        this.gameServer = gameServer;
    }

    public void run() {

        BufferedOutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedInputStream is = null;
        InputStreamReader isr = null;

            while (connection.isConnected()) {
                try {
                is = new BufferedInputStream(connection.getInputStream());
                isr = new InputStreamReader(is);
                int character;
                StringBuffer process = new StringBuffer();
                while ((character = isr.read()) != 13) {
                    process.append((char) character);
                }
                System.out.println(process);
                    if(process.toString().contains("username") && process.toString().contains("password")) {
                        System.out.println("Attempting To Login: " + process);
                         returnCode += ID + ":makeGame,";
                        String[] arrayProcess = process.toString().split(":");
                        //Send All Current Players
                        gameServer.addPlayer(this);
                        gamePlayer = new GamePlayer(ID, new GameSpriteImage(gameImageLoader.Loader("playerLayout"), 100, 70, 16, 18), 20, 20, arrayProcess[1]);
                        iterGameSocket = gameServer.gameServerSocketList.iterator();
                        GameServerSocket gameServerSockets;
                        while (iterGameSocket.hasNext()) {
                            gameServerSockets = iterGameSocket.next();
                            System.out.println("Debug: 4");
                            if(gameServerSockets != this) {
                                returnCode += gameServerSockets.gamePlayer.id + ":makeCharacter:" + gameServerSockets.gamePlayer.gameSpriteImage.x +
                                        ":" + gameServerSockets.gamePlayer.gameSpriteImage.y + ":" + gameServerSockets.gamePlayer.name + ",";
                            }
                        }
                        eMessage(returnCode);
                        returnCode = "";

                        gameServer.sendPlayerToEveryone(gamePlayer);


                        //iMessage(ID + ":makeCharacter:" + gamePlayer.gameSpriteImage.x + ":" + gamePlayer.gameSpriteImage.y + ":" + gamePlayer.name + (char) 13);

                    }
                    else {
                        if (process.toString().contains("Key:Up")) {
                            System.out.println("Attempting To Move: " + process);
                            gamePlayer.SPEEDY -= gamePlayer.SPEEDDefault;
                            if(!gameServer.gameCollision.checkCollisions(gamePlayer)) {
                                gamePlayer.moveUp();
                                returnCode += ID + ":Key:Up";
                            }
                        }
                        if (process.toString().contains("Key:Right")) {
                            System.out.println("Attempting To Move: " + process);
                            gamePlayer.SPEEDX = gamePlayer.SPEEDDefault;
                            if(!gameServer.gameCollision.checkCollisions(gamePlayer)) {
                                gamePlayer.moveRight();
                                returnCode += ID + ":Key:Right";
                            }
                        }
                        if (process.toString().contains("Key:Left")) {
                            System.out.println("Attempting To Move: " + process);
                            gamePlayer.SPEEDX -= gamePlayer.SPEEDDefault;
                            if(!gameServer.gameCollision.checkCollisions(gamePlayer)) {
                                gamePlayer.moveLeft();
                                returnCode += ID + ":Key:Left";
                            }
                        }
                        if (process.toString().contains("Key:Down")) {
                            System.out.println("Attempting To Move: " + process);
                            gamePlayer.SPEEDY =  gamePlayer.SPEEDDefault;
                            if(!gameServer.gameCollision.checkCollisions(gamePlayer)) {
                                gamePlayer.moveDown();
                                returnCode += ID + ":Key:Down";
                            }
                        }
                        if (process.toString().contains("ping")) {
                            System.out.println("Attempting To Ping back");
                            returnCode += ID + ":ping";
                        }
                        if(returnCode != "") {
                            gameServer.sendAllPlayerCommands(returnCode + (char) 13);
                        }
                    }
                    gameServer.lastCMD = returnCode;
                    returnCode = "";
            }
            catch(Exception e){
                try {
                    connection.close();
                    System.out.println("Connection Lost Successful close");
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                gameServer.gameServerSocketList.remove(this);

            }

                /*
                try {
                    os = new BufferedOutputStream(connection.getOutputStream());
                    osw = new OutputStreamWriter(os, "US-ASCII");
                    osw.write(returnCode + (char) 13);
                    osw.flush();
                    returnCode = "";
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                */
            }
    }

    public void iMessage(String s) {

        returnCode += s;

    }

    public void eMessage(String s) {
        try {
            BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
            osw.write(s + (char) 13);
            osw.flush();
            System.out.println(s);
        }
        catch (IOException e) {
            System.out.print(e);
        }
    }

    public void destroySelf(GameServerSocket gameServerSocket) {

    }
}