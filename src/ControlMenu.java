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

        switch (actionEvent.getActionCommand()) {

            case View.CODE_EXIT:

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

                Sensors sensors = Sensors.getInstance();

                sensors.connect();

                notifyAll();

                break;

            default:
                System.exit(-3);
        }

    }
}
