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

    public synchronized int connect() {

        if (connect) {
            return -1;
        }

        try {

            portID = CommPortIdentifier.getPortIdentifier(portName);

            Object object = portID.open("scale", 2000);

            if (object instanceof SerialPort) {

                serialPort = (SerialPort) portID.open("scale", 2000);

                serialPort.setSerialPortParams(1200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

                setConnect(true);

            } else {
                return -2;
            }

        } catch (UnsupportedCommOperationException | IOException | NoSuchPortException e) {
            return -3;
        } catch (PortInUseException e) {
            return -4;
        }

        return 0;
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

            String data = input.readLine();

            System.out.println(data);

        } catch (IOException e) {
            return 0.0;
        }

        return Math.random() * 1000;
    }

    public void setConnect(boolean b) {

        connect = b;
        if (connect) {
            notifyAll();
        }
    }
}
