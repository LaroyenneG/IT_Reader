import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu extends Controller implements ActionListener {

    public ControlMenu(Model model, View view) {
        super(model, view);

        view.setMenuController(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {

            case View.CODE_EXIT:

                System.exit(0);
                break;

            case View.CODE_CREDIT:
                break;

            case View.CODE_HELP:
                break;

            case View.CODE_CONNEXION:

                Sensors sensors = Sensors.getInstance();

                sensors.connect();

                notifyAll();

                break;

            default:
                System.exit(-3);
        }

    }
}
