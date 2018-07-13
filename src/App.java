import javax.swing.*;

public class App {

    public static final String COPY_RIGTH = "Guillaume LAROYENNE 2018";

    public static void main(String[] args) {

        System.out.println(COPY_RIGTH);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Model model = new Model();
                View view = new View();

                view.displayMeasure(115.02);

                ControlGroup controlGroup = new ControlGroup(model, view);
            }
        });
    }
}
