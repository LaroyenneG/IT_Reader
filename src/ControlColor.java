import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ControlColor extends Controller {

    private Thread threadColor;

    public ControlColor(Model model, View view) {
        super(model, view);

        threadColor = null;

        buildThread();
    }


    public void buildThread() {

        threadColor = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {


                    Transferable contenu = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);

                    DataFlavor flavor = DataFlavor.stringFlavor;
                    if (contenu.isDataFlavorSupported(flavor)) {


                        String text = null;

                        try {
                            text = (String) contenu.getTransferData(flavor);
                        } catch (UnsupportedFlavorException | IOException ignored) {
                            text = "0";
                        }

                        double value;
                        try {
                            value = Double.parseDouble(text);
                        } catch (NumberFormatException e) {
                            value = 0.0;
                        }

                        double measure = model.getMeasure();

                        if (value != measure) {
                            view.changeRed();
                        } else {
                            view.changeGreen();
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        threadColor.start();
    }
}
