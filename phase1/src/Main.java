import controller.CommandLineController;
import view.ViewManager;

public class Main {
    public static void main(String[] args) {
        ViewManager vm = new ViewManager(new CommandLineController());
        vm.run();
    }
}
