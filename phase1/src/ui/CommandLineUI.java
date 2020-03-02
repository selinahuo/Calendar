package ui;

import java.util.Scanner;

import controller.SimpleController;

public class CommandLineUI {
    private SimpleController controller;
    private String userID;

    public CommandLineUI(SimpleController controller) {
        this.controller = controller;
    }

    public void run() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter event name:");
            String eventName = input.nextLine();
            System.out.println(controller.createEvent(eventName));
        }
    }
}
