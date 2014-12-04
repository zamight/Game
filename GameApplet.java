import javax.swing.*;


public class GameApplet extends JApplet {

    MainGame mainGame;

    public void init() {
        mainGame = new MainGame("No Title Needed");
        add(mainGame.gameDisplay);
        setJMenuBar(mainGame.getJMenuBar());
    }

}
