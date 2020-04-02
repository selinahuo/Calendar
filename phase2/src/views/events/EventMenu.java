package views.events;

import controller.Controller;
import controller.viewmodels.ListModel;
import controller.viewmodels.ViewModel;
import views.LocalStorage;
import views.View;
import views.general.MainMenu;
import views.users.UserList;

import java.util.Scanner;

public class EventMenu extends View {
    public EventMenu(LocalStorage localStorage, ViewModel model, Controller controller) {
        super(localStorage, model, controller);
    }

    private void inputPrompt() {
        System.out.println("Please select one of the following choices by entering a number:");
        System.out.println("[1] Create event");
        System.out.println("[2] Create full day holiday");
        System.out.println("[3] List events by name");
        System.out.println("[4] List past/current/future events");
        System.out.println("[5] List events using time frame (hour, day, week, month)");
        System.out.println("[~] Return to main menu");
    }

    @Override
    public View run() {
        Scanner input = new Scanner(System.in);
        printTitle("Event Menu");
        while (true) {
            inputPrompt();
            String selection = input.nextLine();
            switch(selection) {
                case "1":
                    System.out.println("Enter event name:");
                    String createName = input.nextLine();
                    System.out.println("Enter event start time (yyyy-mm-dd hh:mm):");
                    String createStart = input.nextLine();
                    System.out.println("Enter event end time (yyyy-mm-dd hh:mm):");
                    String createEnd = input.nextLine();
                    System.out.println("Enter event location:");
                    String createLocation = input.nextLine();
                    System.out.println("Enter calendar ID (leave blank for no calendar):");
                    String createCalendar = input.nextLine();
                    String eventID = getController().createEvent(createName, createStart, createEnd, createLocation,
                            getLocalStorage().getUserID(), createCalendar);
                    if (eventID == null) {
                        printError("Something went wrong creating the event.");
                        break;
                    }
                    // TODO send to event single page
                    return new EventMenu(getLocalStorage(), null, getController());
                case "2":
                    System.out.println("Enter event name:");
                    String holidayName = input.nextLine();
                    System.out.println("Enter event year:");
                    int holidayYear = input.nextInt();
                    System.out.println("Enter event month (1-12)");
                    int holidayMonth = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter day of week (m/tu/w/th/f/sa/su):");
                    String holidayWeekDay = input.nextLine();
                    System.out.println("Enter event location:");
                    String holidayLocation = input.nextLine();
                    System.out.println("Enter calendar ID (leave blank for no calendar):");
                    String holidayCalendar = input.nextLine();
                    String holidayEventID = getController().createHolidayEvent(holidayName, holidayYear, holidayMonth,
                            holidayWeekDay, holidayLocation, getLocalStorage().getUserID(), holidayCalendar);
                    if (holidayEventID == null) {
                        printError("Something went wrong creating the event.");
                        break;
                    }
                    // TODO send to event single page
                    return new EventMenu(getLocalStorage(), null, getController());
                case "3":
                    System.out.println("Enter event name:");
                    String eventName = input.nextLine();
                    ListModel nameModel = getController().getEventsByName(eventName, getLocalStorage().getUserID());
                    return new EventList(getLocalStorage(), nameModel, getController());
                case "4":
                    System.out.println("Choose [p]ast/[f]uture (or any value for current events):");
                    String relativeTime = input.nextLine();
                    if (relativeTime.equals("p")) {
                        ListModel pastEvents = getController().getPastEvents(getLocalStorage().getUserID());
                        printTitle("Past Events");
                        return new EventList(getLocalStorage(), pastEvents, getController());
                    } else if (relativeTime.equals("f")) {
                        ListModel futureEvents = getController().getFutureEvents(getLocalStorage().getUserID());
                        printTitle("Future Events");
                        return new EventList(getLocalStorage(), futureEvents, getController());
                    } else {
                        ListModel currentEvents = getController().getCurrentEvents(getLocalStorage().getUserID());
                        printTitle("Current Events");
                        return new EventList(getLocalStorage(), currentEvents, getController());
                    }
                case "5":
                    System.out.println("View events by [h]our/[d]ay/[w]eek/[m]onth (defaults to day)");
                    String timeFrame = input.nextLine();
                    switch (timeFrame) {
                        case "h":
                            System.out.println("Enter day to filter (yyyy-mm-dd):");
                            String hourDate = input.nextLine();
                            System.out.println("Enter hour of the day (0-23):");
                            int hour = input.nextInt();
                            input.nextLine();
                            ListModel hourlyEvents = getController().getHourlyEvents(hourDate, hour, getLocalStorage().getUserID());
                            printTitle("Hour Events");
                            return new EventList(getLocalStorage(), hourlyEvents, getController());
                        case "w":
                            System.out.println("Enter first day of week (yyyy-mm-dd):");
                            String weekDate = input.nextLine();
                            ListModel weeklyEvents = getController().getWeeklyEvents(weekDate, getLocalStorage().getUserID());
                            printTitle("Week Events");
                            return new EventList(getLocalStorage(), weeklyEvents, getController());
                        case "m":
                            System.out.println("Enter year of month to filter (0-yyyy):");
                            int monthYear = input.nextInt();
                            System.out.println("Enter month (1-12)");
                            int monthFilter = input.nextInt();
                            ListModel monthlyEvents = getController().getMonthlyEvents(monthYear, monthFilter, getLocalStorage().getUserID());
                            printTitle("Month Events");
                            return new EventList(getLocalStorage(), monthlyEvents, getController());
                        default: // Day
                            System.out.println("Enter day to filter (yyyy-mm-dd):");
                            String dayDate = input.nextLine();
                            ListModel dailyEvents = getController().getDailyEvents(dayDate, getLocalStorage().getUserID());
                            printTitle("Day Events");
                            return new EventList(getLocalStorage(), dailyEvents, getController());
                    }
                case "~":
                    return new MainMenu(getLocalStorage(), null, getController());
                default:
                    printInputError();
            }
        }
    }
}

