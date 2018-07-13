import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlCheckBox extends Controller implements ActionListener {

    private Thread clipboardThread;
    private boolean run;

    public ControlCheckBox(Model model, View view) {
        super(model, view);

        clipboardThread = null;
        run = false;

        view.setCheckBoxController(this);
    }


    private void startClipBoard() {

        run = true;

        if (clipboardThread == null) {
            clipboardThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (run) {

                        sendToClipboard();

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        clipboardThread.start();
    }

    private void stopClipBoard() {
        run = false;
        clipboardThread = null;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (view.autoValue()) {
            view.changeBlue();
            view.displayInfo("Mode automatique activé");
            view.disableBottom();
            startClipBoard();
        } else {
            view.changeWhite();
            view.displayInfo("");
            view.enableBottom();
            stopClipBoard();
        }
    }
}
