import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlCheckBox extends Controller implements ActionListener {


    public ControlCheckBox(Model model, View view) {
        super(model, view);


        view.setCheckBoxController(this);
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (view.autoValue()) {
            view.changeBlue();
            view.displayInfo("Mode automatique activé");
            view.disableBottom();
            model.setAuto(true);
        } else {
            view.changeWhite();
            view.displayInfo("");
            view.enableBottom();
            model.setAuto(false);
        }
    }
}
