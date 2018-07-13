public class ThreadReadSensors extends Thread {

    private Model model;
    private View view;
    private Controller controller;

    public ThreadReadSensors(Model model, View view, Controller controller) {
        super();

        this.model = model;
        this.view = view;
        this.controller = controller;
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

            if (model.getAuto()) {
                controller.sendToClipboard();
            } else {
                controller.checkClipBoard(model.getMeasure());
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}