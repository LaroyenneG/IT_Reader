public class ControlGroup {

    private ControlBottom controlButtom;
    private ControlMenu controlMenu;
    private ControlCheckBox controlCheckBox;

    public ControlGroup(Model model, View view) {

        controlMenu = new ControlMenu(model, view);
        controlButtom = new ControlBottom(model, view);
        controlCheckBox = new ControlCheckBox(model, view);
    }
}
