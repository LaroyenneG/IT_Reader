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

    public double readValue() {
        return Math.random();
    }
}
