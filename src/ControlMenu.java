import gnu.io.PortInUseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu extends Controller implements ActionListener {

    private JDialog dialog;

    public ControlMenu(Model model, View view) {
        super(model, view);

        dialog = new JDialog();
        view.setMenuController(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getActionCommand().equals(View.CODE_CONNEXION)) {
            Sensors sensors = Sensors.getInstance();


            String[] values = sensors.listPortIdentifiers();

            if (values != null) {
                String result = (String) JOptionPane.showInputDialog(dialog, "Choisir un port : ", "Connexion", JOptionPane.QUESTION_MESSAGE,
                        null, values, "Titan");

                if (result != null) {
                    try {
                        sensors.setPortName(result);
                        sensors.connect();
                    } catch (PortInUseException e) {
                        JOptionPane.showMessageDialog(dialog, "Le port est déjà utilisé\n#002", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Erreur de configuration matérielle\n#001", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
