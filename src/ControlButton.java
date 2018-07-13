import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButton extends Controller implements ActionListener {

    public ControlButton(Model model, View view) {
        super(model, view);

        view.setBottomController(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        sendToClipboard();
        checkClipBoard(model.getMeasure());
    }
}
