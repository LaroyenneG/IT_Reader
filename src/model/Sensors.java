package model;

import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


public class Sensors {

    private static final int TIME_OUT = 3000;
    private static final int BAUD_RATE = 9600;
    public static final char[] SCALE_MESSAGE_WEIGHT = {'R', 'M'};
    public static final double UNITY_CONVERTER = 1.0;

    public static final double INVALID_VALUE = -Double.MAX_VALUE;

    public static final int CONNECTED_ERROR = -1;
    public static final int PORT_ERROR = -2;
    public static final int COMMUNICATION_ERROR = -3;
    public static final int USE_ERROR = -4;
    public static final int GOOD_STATE = 0;

    private static Sensors instance = null;
    private SerialPort serialPort;
    private String portName;
    private CommPortIdentifier portID;
    private BufferedReader input;
    private OutputStream output;

    private boolean connect;

    private boolean debugMode;

    private Sensors() {
        portName = "";
        serialPort = null;
        portID = null;
        input = null;
        output = null;
        connect = false;
        debugMode = false;
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
            return CONNECTED_ERROR;
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
                return PORT_ERROR;
            }

        } catch (UnsupportedCommOperationException | IOException | NoSuchPortException e) {
            e.printStackTrace();
            return COMMUNICATION_ERROR;
        } catch (PortInUseException e) {
            e.printStackTrace();
            return USE_ERROR;
        }

        return GOOD_STATE;
    }

    public synchronized void waitIsConnect() {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Scale communication
     */
    public double readValue() {

        double value = INVALID_VALUE;

        try {
            for (char c : SCALE_MESSAGE_WEIGHT) {
                output.write(c);
            }

            output.flush();

            if (debugMode) {
                System.out.println("DEBUG  : Send Message : " + Arrays.toString(SCALE_MESSAGE_WEIGHT));
                System.out.println("PORT : " + serialPort);
            }

            String responseMessage = input.readLine();

            if (debugMode) {
                System.out.println("Answer : " + responseMessage);
                System.out.println("===========================================================");
            }

            value = Double.parseDouble(responseMessage) * UNITY_CONVERTER;

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return value;
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

    public void enableDebug() {
        debugMode = true;
    }
}
