package model;

public class Model {

    public static final int SIZE = 1000;

    private int cursor;
    private double[] measures;
    private double measure;

    public Model() {
        measures = new double[SIZE];
        cursor = 0;
    }

    private void setNewMeasure(double m) {

        if (cursor < SIZE) {
            measures[cursor] = m;
            cursor++;
        }
    }

    public synchronized void setNewMeasures(double[] measures) {

        for (double v : measures) {
            setNewMeasure(v);
        }
    }


    public synchronized void reset() {
        cursor = 0;
    }

    public synchronized double getMeasure() {
        return measure;
    }

    public synchronized void compute() throws NoMeasureException {

        if (cursor <= 0) {
            throw new NoMeasureException("No measure in memory");
        }

        double m = 0;

        for (int i = 0; i < cursor; i++) {
            m += measures[i];
        }

        measure = m / cursor;

        reset();
    }
}
