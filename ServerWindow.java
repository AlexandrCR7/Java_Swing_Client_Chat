import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private boolean isServerWorking;
    JTextArea textWindow = new JTextArea();

    JButton start;
    JButton stop;

    public boolean isWork(){
        if(isServerWorking){
            return true;
        } else {
            return false;
        }
    }
    ServerWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setTitle("Chat Server");
        start = new JButton("Start Server");
        stop = new JButton("Stop Server");

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                textWindow.setText("Server has been stopped.");
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                textWindow.setText("Server is working now.");
            }
        });

        start.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    isServerWorking = true;
                    textWindow.setText("Server is working now.");
                }

            }
        });

        JPanel panAllWindow = new JPanel(new GridLayout(3, 1));
        panAllWindow.add(start);
        panAllWindow.add(stop);
        panAllWindow.add(textWindow, BorderLayout.NORTH);

        add(panAllWindow);

        setVisible(true);
    }
}
