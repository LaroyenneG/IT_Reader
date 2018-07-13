public class ThreadReadSensors extends Thread {

    private Model model;
    private View view;

    public ThreadReadSensors(Model model, View view) {
        super();

        this.model = model;
        this.view = view;
    }

    @Override
    public void run() {

        Sensors sensors = Sensors.getInstance();

        while (!isInterrupted()) {
            while (!sensors.isConnect()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            double[] measure = new double[Model.SIZE];

            for (int i = 0; i < measure.length; i++) {
                measure[i] = sensors.readValue();
            }

            model.setNewMeasures(measure);

            try {
                model.compute();
                view.displayMeasure(model.getMeasure());
            } catch (NoMeasureException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
