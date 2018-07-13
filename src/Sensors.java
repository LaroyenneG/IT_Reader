import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class Sensors {

    private static Sensors instance = null;

    private Sensors() {

    }

    public static Sensors getInstance() {

        if (instance == null) {
            instance = new Sensors();
        }

        return instance;
    }

    public void connect() {

        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        int i = 1;
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            System.out.println("Port n°" + i++);
            System.out.println("\tNom\t:\t" + port.getName());
            String type = null;
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) type = "Serie";
            else type = "Parallèle";
            System.out.println("\tType\t:\t" + type);
            String etat = null;
            if (port.isCurrentlyOwned()) etat = "Possédé par " + port.getCurrentOwner();
            else etat = "Libre";
            System.out.println("\tEtat\t:\t" + etat + "\n");
        }
    }


    public boolean isConnect() {
        return true;
    }

    public double readValue() {
        return Math.random() * 1000;
    }
}
