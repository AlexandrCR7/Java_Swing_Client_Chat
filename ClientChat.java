import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientChat extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    JPanel panelTop;
    JPanel panelBottom;
    JTextField password;
    JTextField ip;
    JTextField port;
    JTextField messageForSend;
    JTextArea textWindow;
    JTextField loginText;
    JButton login, send;
    JList<String> listOfLogins = new JList<>();
    List<String> listOfNames = new ArrayList<>();
    ;
    String text = "";
    String line;

    public List<String> mainList() {
        return listOfNames;
    }

    public String stringReader() {
        try (BufferedReader br = new BufferedReader(new FileReader("LoggFile.txt"))) {
            line = br.readLine();
            while (br.readLine() != null) {
                line = line + br.readLine() + "\n";
            }
            return line;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    ClientChat(ServerWindow serverWindow){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        panelTop = new JPanel(new GridLayout(2, 1));
        panelTop.setBackground(Color.WHITE);
        login = new JButton("Login");
        password = new JTextField("123456");
        ip = new JTextField("123.0.0.1");
        port = new JTextField("7575");
        loginText = new JTextField("Alexandr");

        textWindow = new JTextArea();

        panelBottom = new JPanel(new GridLayout(1, 1));
        messageForSend = new JTextField();
        send = new JButton("Send");
        textWindow.setText(stringReader());
        text = stringReader();

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isWork()) {
                    listOfNames.add(loginText.getText());
                } else {
                    textWindow.setText("You cant log in. Server has been stopped.");
                }
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> list = new ArrayList<>();
                if (serverWindow.isWork()) {
                    list.add(messageForSend.getText());
                    for (String string : list) {
                        text = text + string + "\n";

                        try (FileWriter fileWriter = new FileWriter("LoggFile.txt", true)){
                            fileWriter.write(messageForSend.getText() + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    textWindow.setText(text);
                    messageForSend.setText("");

                } else {
                    textWindow.setText("You cant send a message. Server has been stopped.");}
            }
        });

        System.out.println(listOfNames);
        panelTop.add(ip);
        panelTop.add(port);
        panelTop.add(loginText);
        panelTop.add(password);
        panelTop.add(login);

        panelBottom.add(messageForSend);
        panelBottom.add(send);

        add(panelTop, BorderLayout.NORTH);
        add(textWindow, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}

