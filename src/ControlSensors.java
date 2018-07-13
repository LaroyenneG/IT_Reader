public class ControlSensors extends Controller {

    private ThreadReadSensors threadReadSensors;

    public ControlSensors(Model model, View view) {
        super(model, view);

        threadReadSensors = new ThreadReadSensors(model, view);
        threadReadSensors.start();
    }
}
