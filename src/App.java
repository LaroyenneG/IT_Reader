import javax.swing.*;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                Model model = new Model();
                View view = new View();

                ControlGroup controlGroup = new ControlGroup(model, view);

                view.lock();
                view.askControl();
            }
        });
    }
}
