import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class Sensors {

	private SerialPort serialPort;
	private String portID;

	private static Sensors instance = null;

	private Sensors() {
		portID = "";
	}

	public static Sensors getInstance() {

		if (instance == null) {
			instance = new Sensors();
		}

		return instance;
	}

	public String[] listPortIdentifiers() {

		String[] ports = null;

		Enumeration enumeration = CommPortIdentifier.getPortIdentifiers();

		if (enumeration != null) {

			List<String> list = new ArrayList<>();

			while (enumeration.hasMoreElements()) {
				list.add(((CommPortIdentifier) enumeration.nextElement()).getName());
			}

			ports = new String[list.size()];

			for (int i = 0; i < ports.length; i++) {
				ports[i] = list.get(i);
			}
		}

		return ports;
	}

	public synchronized void connect() {

		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
		int i = 1;
		while (ports.hasMoreElements()) {
			CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
			System.out.println("Port n°" + i++);
			System.out.println("\tNom\t:\t" + port.getName());
			String type = null;
			if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				type = "Serie";
			} else {
				type = "Parallèle";
			}
			System.out.println("\tType\t:\t" + type);
			String etat = null;
			if (port.isCurrentlyOwned()) {
				etat = "Possédé par " + port.getCurrentOwner();
			} else {
				etat = "Libre";
			}
			System.out.println("\tEtat\t:\t" + etat + "\n");
		}

		notifyAll();
	}

	public synchronized void waitIsConnect() {

		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public double readValue() {
		return Math.random() * 1000;
	}
}
