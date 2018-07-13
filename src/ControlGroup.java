public class ControlGroup {

    private ControlButton controlButtom;
    private ControlMenu controlMenu;
    private ControlCheckBox controlCheckBox;
    private ControlSensors controlSensors;
    private ControlColor controlColor;


    public ControlGroup(Model model, View view) {

        controlMenu = new ControlMenu(model, view);
        controlButtom = new ControlButton(model, view);
        controlCheckBox = new ControlCheckBox(model, view);
        controlSensors = new ControlSensors(model, view);
        controlColor = new ControlColor(model, view);
    }
}
