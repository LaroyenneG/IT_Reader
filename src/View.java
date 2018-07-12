import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JMenuBar menu;
    private JPanel panel;

    private JLabel screen;
    private JButton copy;

    private JCheckBox auto;

    private JLabel statusInfo;

    private GridLayout gridLayout;

    private Color backGroundColor;


    public View() {

        backGroundColor = Color.WHITE;

        gridLayout = new GridLayout(0, 1);

        panel = new JPanel();
        panel.setLayout(gridLayout);

        statusInfo = new JLabel("");
        statusInfo.setHorizontalAlignment(SwingConstants.CENTER);
        statusInfo.setVerticalAlignment(SwingConstants.CENTER);

        screen = new JLabel("");
        screen.setHorizontalAlignment(SwingConstants.CENTER);
        screen.setVerticalAlignment(SwingConstants.CENTER);

        screen.setFont(new Font("Arial", Font.BOLD, 25));
        screen.setForeground(Color.CYAN);
        screen.setBackground(Color.BLACK);
        screen.setOpaque(true);

        copy = new JButton("Copier dans le presse-papier");
        copy.setHorizontalAlignment(SwingConstants.CENTER);
        copy.setVerticalAlignment(SwingConstants.CENTER);

        auto = new JCheckBox("Mode automatique");
        auto.setHorizontalAlignment(SwingConstants.CENTER);
        auto.setVerticalAlignment(SwingConstants.CENTER);
        auto.setOpaque(false);


        panel.add(screen);
        panel.add(statusInfo);
        panel.add(auto);
        panel.add(copy);
        panel.setOpaque(true);

        menu = new JMenuBar();

        setDefaultCloseOperation(3);

        setSize(250, 300);
        setResizable(false);
        setVisible(true);

        setJMenuBar(menu);
        setContentPane(panel);

        color();
    }


    public void setBackGroundColor(Color color) {
        backGroundColor = color;
    }

    public void color() {

        panel.setBorder(BorderFactory.createLineBorder(backGroundColor, 7));
        screen.setBorder(BorderFactory.createLineBorder(backGroundColor, 5));
        panel.setBackground(backGroundColor);
    }
}
