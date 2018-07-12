public class Model {

    public static final int SIZE = 1000;

    private int cursor;
    private double[] messure;

    public Model() {
        messure = new double[SIZE];
        cursor = 0;
    }

    public void setNewMesure(int m) {

        if (cursor < SIZE) {
            messure[cursor] = m;
        }
    }

    public double getMessure() {

        double m = 0;

        for (int i = 0; i < SIZE; i++) {
            m += messure[i];
        }

        return m / SIZE;
    }
}
