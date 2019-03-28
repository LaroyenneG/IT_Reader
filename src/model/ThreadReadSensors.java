package model;

import controller.Controller;
import view.View;

public class ThreadReadSensors extends Thread {

    public static final int THREAD_CLOCK = 2000;

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

            boolean correct = true;

            for (int i = 0; i < measure.length; i++) {

                measure[i] = sensors.readValue();

                if (sensors.readValue() == Sensors.INVALID_VALUE) {
                    correct = false;
                    break;
                }
            }

            if (correct) {
                model.setNewMeasures(measure);
            }

            try {
                model.compute();

                view.displayMeasure(model.getMeasure());

                if (view.autoValue()) {
                    controller.sendToClipboard();
                } else {
                    controller.checkClipBoard(model.getMeasure());
                }

                Thread.sleep(THREAD_CLOCK);

            } catch (NoMeasureException e) {
                model.reset();
                controller.errorReading(sensors.getPortName());
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                interrupt();
            }
        }
    }
}
