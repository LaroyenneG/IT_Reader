import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu extends Controller implements ActionListener {

    public ControlMenu(Model model, View view) {
        super(model, view);

        view.setMenuController(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
