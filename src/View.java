import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    public static final String CODE_HELP = "HELP";
    public static final String CODE_CONNEXION = "CONNEXION";
    public static final String CODE_CREDIT = "CREDIT";
    public static final String CODE_EXIT = "EXIT";

    private JPanel panel;

    private JMenuBar menu;

    private JMenuItem help;
    private JMenuItem credit;
    private JMenuItem connexion;
    private JMenuItem exit;

    private JMenu options;
    private JMenu scale;

    private JLabel screen;
    private JButton copy;

    private JCheckBox auto;

    private JLabel statusInfo;

    private GridLayout gridLayout;

    private Color backGroundColor;

    public View() {

        backGroundColor = Color.WHITE;

        statusInfo = new JLabel("");
        statusInfo.setHorizontalAlignment(SwingConstants.CENTER);
        statusInfo.setVerticalAlignment(SwingConstants.BOTTOM);
        statusInfo.setFont(new Font("Arial", Font.ITALIC, 15));

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

        gridLayout = new GridLayout(0, 1);

        panel = new JPanel();
        panel.setLayout(gridLayout);
        panel.add(screen);
        panel.add(statusInfo);
        panel.add(auto);
        panel.add(copy);
        panel.setOpaque(true);

        menu = new JMenuBar();
        buildMenu();

        setDefaultCloseOperation(3);

        setSize(250, 300);
        setResizable(false);
        setVisible(true);

        setJMenuBar(menu);
        setContentPane(panel);
        setTitle("Scale connector");

        color();
    }

    public void buildMenu() {

        help = new JMenuItem("Aide");
        help.setActionCommand(CODE_HELP);

        exit = new JMenuItem("Quiter");
        exit.setActionCommand(CODE_EXIT);

        credit = new JMenuItem("Credit");
        credit.setActionCommand(CODE_CREDIT);

        connexion = new JMenuItem("Connexion");
        connexion.setActionCommand(CODE_CONNEXION);

        options = new JMenu("Options");
        scale = new JMenu("Balance");

        scale.add(connexion);

        options.add(help);
        options.add(credit);
        options.add(exit);

        menu.add(options);
        menu.add(scale);
    }


    public void displayInfo(String msg) {

        statusInfo.setText(msg);
    }

    private void color() {

        panel.setBorder(BorderFactory.createLineBorder(backGroundColor, 7));
        screen.setBorder(BorderFactory.createLineBorder(backGroundColor, 5));
        panel.setBackground(backGroundColor);
    }

    public void changeRed() {
        backGroundColor = Color.RED;
        color();
    }

    public void changeWhite() {
        backGroundColor = Color.WHITE;
        color();
    }

    public void changeGreen() {
        backGroundColor = Color.GREEN;
        color();
    }

    public void changeBlue() {
        backGroundColor = Color.decode("#c1d9ff");
        color();
    }

    public void changeGray() {
        backGroundColor = Color.GRAY;
        color();
    }

    public void disableBottom() {

        copy.setEnabled(false);
    }

    public void enableBottom() {

        copy.setEnabled(true);
    }

    public boolean autoValue() {

        return auto.isSelected();
    }

    public void displayMeasure(double m) {

        screen.setText(String.valueOf(m));
    }


    /* Controllers */

    public void setMenuController(ActionListener listener) {

        connexion.addActionListener(listener);
        exit.addActionListener(listener);
        credit.addActionListener(listener);
        help.addActionListener(listener);
    }

    public void setBottomController(ActionListener listener) {
        copy.addActionListener(listener);
    }

    public void setCheckBoxController(ActionListener listener) {
        auto.addActionListener(listener);
    }

    public void lock() {

        copy.setEnabled(false);
        auto.setEnabled(false);
        panel.setFocusable(false);

        changeGray();
    }

    public void unlock() {

        copy.setEnabled(true);
        auto.setEnabled(true);
        panel.setFocusable(true);

        changeWhite();
    }

    public void askControl() {

        JDialog dialog = new JDialog();

        JOptionPane.showMessageDialog(dialog, "Noubliez pas de tarer la balance", "Rappel tarage", JOptionPane.WARNING_MESSAGE);
    }
}
