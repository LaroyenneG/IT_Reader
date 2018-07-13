import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public abstract class Controller {

    public static final double DIF_LEVEL_WARNING = 1.0;

    Model model;
    View view;

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;
    }


    public void checkClipBoard(double measure) {

        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);

        DataFlavor flavor = DataFlavor.stringFlavor;
        if (transferable.isDataFlavorSupported(flavor)) {


            String text = null;

            try {
                text = (String) transferable.getTransferData(flavor);
            } catch (UnsupportedFlavorException | IOException ignored) {
                text = "0";
            }

            double value;
            try {
                value = Double.parseDouble(text);
            } catch (NumberFormatException e) {
                value = 0.0;
            }


            if (Math.abs((measure - value) / value) * 100.0 > DIF_LEVEL_WARNING) {
                view.changeRed();
                view.displayInfo("Attention valeur incorrecte !");
            } else {
                view.displayInfo("Ok valeur correcte");
                view.changeGreen();
            }
        }
    }


    public void sendToClipboard() {
        StringSelection selection = new StringSelection(String.valueOf(model.getMeasure()));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }
}
