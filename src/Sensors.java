import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Sensors {

    private static final int TIME_OUT = 3000;
    private static final int BAUD_RATE = 9600;

    public static final double INVALID_VALUE = -100.0;

    private static Sensors instance = null;

    private SerialPort serialPort;
    private String portName;
    private CommPortIdentifier portID;
    private BufferedReader input;
    private OutputStream output;

    private boolean connect;

    private Sensors() {

        portName = "";
        serialPort = null;
        portID = null;
        input = null;
        output = null;
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

            Object object = portID.open("scale", TIME_OUT);

            if (object instanceof SerialPort) {

                serialPort = (SerialPort) object;

                serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_2, SerialPort.PARITY_NONE);

                output = serialPort.getOutputStream();
                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

                setConnect(true);

            } else {
                return -2;
            }

        } catch (UnsupportedCommOperationException | IOException | NoSuchPortException e) {
            e.printStackTrace();
            return -3;
        } catch (PortInUseException e) {
            e.printStackTrace();
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

            System.out.println("===========================================================");

            output.write('R');
            output.write('M');
            output.flush();

            System.out.println(serialPort);
            System.out.println(input);

            System.out.println(input.read());


            System.out.println("===========================================================");


        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }

        System.exit(0);

        return 0.0;
    }

    public void setConnect(boolean b) {

        connect = b;

        if (connect) {
            notifyAll();
        }
    }

    public void disconnect() {

        if (input != null && output != null) {
            connect = false;
            try {
                input.close();
                output.close();
                serialPort.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnect() {
        return connect;
    }

    public String getPortName() {

        return portName;
    }
}
