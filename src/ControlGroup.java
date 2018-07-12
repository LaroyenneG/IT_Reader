public class ControlGroup {

    private ControlButtom controlButtom;
    private ControlMenu controlMenu;

    public ControlGroup(Model model, View view) {

        controlMenu = new ControlMenu(model, view);
        controlButtom = new ControlButtom(model, view);
    }
}
