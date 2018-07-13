import java.awt.*;
import java.awt.datatransfer.StringSelection;

public abstract class Controller {

    Model model;
    View view;

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;
    }


    public void sendToClipboard() {
        StringSelection selection = new StringSelection(String.valueOf(model.getMeasure()));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }
}
