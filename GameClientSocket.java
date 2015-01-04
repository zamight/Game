import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

/**
 * Created by zam on 12/8/2014.
 */
public class GameClientSocket implements Runnable {

        private Socket connection;
        private MainGame mainGame;

    public GameClientSocket(MainGame mainGame1) {

        this.mainGame = mainGame1;

        // Define a host Server
        String host = "localhost";
        //Define a port
        int port = 4321;

        StringBuffer instr = new StringBuffer();
        String TimeStamp;
        System.out.println("SocketClient initialized");

        try {
            //Obtain an address object of the server
            InetAddress address = InetAddress.getByName(host);
            //Establish a socket connection
            connection = new Socket(address, port);
            //Instantiate a BufferedOutPutStream object
            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            //Instantiate an outputstreamwrite object with the optional character encoding
            OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");

            TimeStamp = new Date().toString();
            String process = "Call the Socket Server 0n "+ host + " port " + port + " at " + TimeStamp;
/*
            //Write across the socket connection and flush the buffer
            osw.write(process);
            osw.flush();
            System.out.println("Error: 3");


            osw.write("Your gay!");
            System.out.println("Error: 4");

            osw.flush();
            int i = 0;
            while(i < 5) {
                //osw.write("Your gay!");
                //osw.flush();
                i++;
            }
            osw.write("Your gay!" + (char) 13);
            osw.flush();
*/
            Thread r = new Thread(this);
            r.start();
        } catch (IOException f) {
            System.out.println("IOException: " + f);
        } catch (Exception g) {
            System.out.println("Exception: " + g);
        }
    }

    public void run() {

        BufferedInputStream is = null;
        InputStreamReader isr = null;
        int character;
        int id = 0;
        String[] arrayStepComma = null;
        String[] arrayProcess = null;

        while (connection.isConnected()) {

            StringBuffer command = new StringBuffer();

            try{

                is = new BufferedInputStream(connection.getInputStream());
                isr = new InputStreamReader(is);
                while ((character = isr.read()) != 13) {
                    command.append((char) character);
                }
            }
            catch(Exception e){
                System.out.println();
            }

            System.out.println(command);
            arrayStepComma = command.toString().split(",");
            for (String process : arrayStepComma) {
                System.out.println(process);
                arrayProcess = process.toString().split(":");
                id = Integer.parseInt(arrayProcess[0]);
                if (process.toString().contains(":makeGame")) {
                    mainGame = new MainGame("Title Here", this);
                }
                if (process.toString().contains("Key:Up")) {
                    mainGame.gamePlayer.get(id).SPEEDY -= mainGame.gamePlayer.get(id).SPEEDDefault;
                    mainGame.gameCollision.checkCollisions(mainGame.gamePlayer.get(id));
                    mainGame.gamePlayer.get(id).moveUp();
                }
                if (process.toString().contains("Key:Left")) {
                    mainGame.gamePlayer.get(id).SPEEDX -= mainGame.gamePlayer.get(id).SPEEDDefault;
                    mainGame.gameCollision.checkCollisions(mainGame.gamePlayer.get(id));
                    mainGame.gamePlayer.get(id).moveLeft();
                }
                if (process.toString().contains("Key:Right")) {
                    mainGame.gamePlayer.get(id).SPEEDX = mainGame.gamePlayer.get(id).SPEEDDefault;
                    mainGame.gameCollision.checkCollisions(mainGame.gamePlayer.get(id));
                    mainGame.gamePlayer.get(id).moveRight();
                }
                if (process.toString().contains("Key:Down")) {
                    mainGame.gamePlayer.get(id).SPEEDY = mainGame.gamePlayer.get(id).SPEEDDefault;
                    mainGame.gameCollision.checkCollisions(mainGame.gamePlayer.get(id));
                    mainGame.gamePlayer.get(id).moveDown();
                }
                if (process.toString().contains("makeCharacter")) {
                    System.out.println("makeCharacer Command: 1lkjh");
                    mainGame.gamePlayer.add(new GamePlayer(id, new GameSpriteImage(mainGame.gameImageLoader.Loader("playerLayout"), 100, 70, 16, 18), Integer.parseInt(arrayProcess[2]), Integer.parseInt(arrayProcess[3]), arrayProcess[4]));
                    System.out.println("makeCharacer Command: 2");
                }
                if (process.toString().contains("ping")) {
                    //Do nothing
                }

                /*String returnCode = "Your gay" + (char) 13;
                BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
                OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
                osw.write(returnCode);
                osw.flush();
                System.out.println("Error: 1");
                */
            }
        }
    }

    public void iMessage(String s) {
        //Instantiate an outputstreamwrite object with the optional character encoding
        try {
            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
            osw.write(s + (char) 13);
            osw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Was Called");

    }
}
