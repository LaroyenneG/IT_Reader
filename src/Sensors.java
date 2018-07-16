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

    private boolean connect;

    private Sensors() {

        portName = "";
        serialPort = null;
        portID = null;
        input = null;
        connect = false;
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

        if (!connect) {


            Enumeration enumeration = CommPortIdentifier.getPortIdentifiers();

            List<String> list = new ArrayList<String>();

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

    public synchronized void connect() throws PortInUseException {

        if (connect) {
            return;
        }

        try {

            portID = CommPortIdentifier.getPortIdentifier(portName);

            serialPort = (SerialPort) portID.open("Envoi", 2000);

            serialPort.setSerialPortParams(1200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

            connect = true;

            notifyAll();

        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        }
    }

    public synchronized void waitIsConnect() {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double readValue() {
        try {
            System.out.println(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Math.random() * 1000;
    }

    public boolean isConnect() {

        return connect;
    }
}
