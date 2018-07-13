import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBottom extends Controller implements ActionListener {

    public ControlBottom(Model model, View view) {
        super(model, view);

        view.setBottomController(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        try {
            StringSelection selection = new StringSelection(String.valueOf(model.getMessure()));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        } catch (NoMeasureException e) {
            e.printStackTrace();
        }
    }
}
