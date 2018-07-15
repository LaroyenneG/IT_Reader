import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Sensors {

    private static Sensors instance = null;
    private SerialPort serialPort;
    private String portName;
    private CommPortIdentifier portID;
    private BufferedReader input;

    private Sensors() {
        portName = "";
        serialPort = null;
        portID = null;
        input = null;
    }

    public static Sensors getInstance() {

        if (instance == null) {
            instance = new Sensors();
        }

        return instance;
    }

    public synchronized void setPortName(String name) {
        portName = name;
    }

    public synchronized String[] listPortIdentifiers() {

        String[] ports = null;

        Enumeration enumeration = CommPortIdentifier.getPortIdentifiers();

        List<String> list = new ArrayList<>();

        while (enumeration.hasMoreElements()) {
            list.add(((CommPortIdentifier) enumeration.nextElement()).getName());
        }

        ports = new String[list.size()];

        for (int i = 0; i < ports.length; i++) {
            ports[i] = list.get(i);
        }

        return ports;
    }

    public synchronized void connect() {

        try {

            portID = CommPortIdentifier.getPortIdentifier(portName);

            serialPort = (SerialPort) portID.open("Envoi", 2000);

            serialPort.setSerialPortParams(1200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

        } catch (PortInUseException | UnsupportedCommOperationException | IOException | NoSuchPortException e) {
            e.printStackTrace();
        }

	    /*
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

*/
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
