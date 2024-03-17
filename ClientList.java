import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientList extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    JPanel generalPanel;

    List<String> list = new ArrayList<>();
    JButton showList;
    String[] elements1;

    ClientList(ClientChat clientChat) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        setAlwaysOnTop(false);
        setTitle("Client List");
        showList = new JButton("Show Client List");
        JList list = new JList();
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(100, 100));

        showList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elements1 = new String[clientChat.listOfNames.size()];
                    for (int i = 0; i < clientChat.listOfNames.size(); i++) {
                        elements1[i] = clientChat.listOfNames.get(i);
                    }
                list.setListData(elements1);
            }
        });


        generalPanel = new JPanel(new GridLayout(1, 1));
        generalPanel.add(list);
        add(generalPanel);
        add(showList, BorderLayout.NORTH);
        setVisible(true);
        repaint();
    }
}
