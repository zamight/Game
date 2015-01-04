import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zam on 12/9/2014.
 */
public class GameLogin extends JFrame {
    private JTextField jTextFieldUsername;
    private JTextField jTextFieldPassword;
    private JButton loginButton;
    private JPanel jPanel;
    private GameClientSocket gameClientSocket;

    public static void main(String[] args) {

        new GameLogin("Game Login");

    }

    public GameLogin(String title) {
        add(jPanel);
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        add(jPanel);
        setVisible(true);
        System.out.println("Error: 7");

        gameClientSocket = new GameClientSocket(null);
        System.out.println("Error: 6");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Error: 6");
                gameClientSocket.iMessage("username:" + jTextFieldUsername.getText() + ":password:" + jTextFieldPassword.getText());
            }
        });
    }

}
