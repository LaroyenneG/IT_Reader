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

            if (!sensors.isConnect()) {
                view.lock();
                sensors.waitIsConnect();
                view.unlock();
            }

            double[] measure = new double[Model.SIZE];

            for (int i = 0; i < measure.length; i++) {

                if (sensors.readValue() == Sensors.INVALID_VALUE) {
                    sensors.disconnect();
                    break;
                }

                measure[i] = sensors.readValue();
            }

            model.setNewMeasures(measure);

            try {

                model.compute();

                view.displayMeasure(model.getMeasure());

                if (view.autoValue()) {
                    controller.sendToClipboard();
                } else {
                    controller.checkClipBoard(model.getMeasure());
                }

                Thread.sleep(2000);

            } catch (NoMeasureException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
