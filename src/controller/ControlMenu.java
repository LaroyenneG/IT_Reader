package controller;

import model.Model;
import model.Sensors;
import view.View;

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

    private void connection() {

        try {
            Sensors sensors = Sensors.getInstance();

            String[] values = sensors.listPortIdentifiers();

            if (values != null && values.length >= 1) {

                String result = (String) JOptionPane.showInputDialog(dialog, "Choisez un port : ", "Connexion", JOptionPane.QUESTION_MESSAGE,
                        null, values, "");

                if (result != null) {

                    sensors.setPortName(result);

                    switch (sensors.connect()) {

                        case Sensors.GOOD_STATE:
                            JOptionPane.showMessageDialog(dialog, "Connection réalisé", "OK", JOptionPane.INFORMATION_MESSAGE);
                            break;

                        case Sensors.CONNECTED_ERROR:
                            JOptionPane.showMessageDialog(dialog, "Déjà connecté", "Attention", JOptionPane.WARNING_MESSAGE);
                            break;

                        case Sensors.PORT_ERROR:
                            JOptionPane.showMessageDialog(dialog, "Port de communication invalide", "Erreur #002", JOptionPane.ERROR_MESSAGE);
                            break;

                        case Sensors.COMMUNICATION_ERROR:
                            JOptionPane.showMessageDialog(dialog, "Erreur de lecture avec le matérielle", "Erreur #003", JOptionPane.ERROR_MESSAGE);
                            break;

                        case Sensors.USE_ERROR:
                            JOptionPane.showMessageDialog(dialog, "Le port est déjà utilisé\n#002", "Erreur", JOptionPane.ERROR_MESSAGE);
                            break;

                        default:
                            System.err.println("Invalid state");
                            System.exit(-1);
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Erreur de configuration matérielle", "Erreur #004", JOptionPane.ERROR_MESSAGE);
            }
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Mauvaise configuration des libraires binaire", "Erreur #005", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {

            case View.CODE_EXIT:
                Sensors.getInstance().disconnect();
                System.exit(0);
                break;

            case View.CODE_CREDIT:
                JOptionPane.showMessageDialog(dialog, "Programme réalisé par :\nGuillaume LAROYENNE\nle 13/07/2018", "Credit", JOptionPane.INFORMATION_MESSAGE);
                break;

            case View.CODE_HELP:

                JOptionPane.showMessageDialog(dialog, "Ce programme permet de lire le poids mesuré par une balance du type IT100.\n" +
                        "Le poids mesuré sera stocké dans le presse-papier de votre ordinateur.\n\n" +
                        "Pour utiliser le programme :\n" +
                        "Connectez-vous à la balance : Balance -> Connexion.\n" +
                        "Lorsque la connexion est réalisé le poids est automatiquement affichée.\n\n" +
                        "Pour récupérer la valeur du poids :\n" +
                        "Vous pouvez cliquer sur le bouton de l'interface ou sélectionner le mode automatique.", "Aide", JOptionPane.INFORMATION_MESSAGE);
                break;

            case View.CODE_CONNEXION:
                connection();
                break;

            default:
                System.err.println("Invalid state");
                System.exit(-1);
        }
    }
}
