import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class View extends JFrame {

    private JMenuBar menu;
    private JPanel panel;

    private JLabel screen;
    private JButton messure;

    private JCheckBox auto;

    private JLabel statusInfo;

    private GridLayout gridLayout;

    private List<JLabel> jLabelList;


    public View() {

        jLabelList = new ArrayList<>();

        gridLayout = new GridLayout(0, 1);

        statusInfo = new JLabel("Collé dans le prespapier !");
        jLabelList.add(statusInfo);

        screen = new JLabel("152.3 kg");
        jLabelList.add(screen);

        screen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        screen.setFont(new Font("Arial", Font.BOLD, 20));

        messure = new JButton("Mesurer");

        auto = new JCheckBox("Mode automatique");

        panel = new JPanel();

        panel.setLayout(gridLayout);


        panel.add(screen);
        panel.add(statusInfo);
        panel.add(auto);
        panel.add(messure);

        menu = new JMenuBar();

        setDefaultCloseOperation(3);

        setSize(200, 300);
        setResizable(false);
        setVisible(true);

        setJMenuBar(menu);

        setContentPane(panel);

        centering();
    }


    private void centering() {

        for (JLabel label : jLabelList) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
    }


}
