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

    }


    public boolean isConnect() {
        return true;
    }

    public double readValue() {
        return Math.random() * 1000;
    }
}
