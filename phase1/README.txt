To run the program make sure you are running the code from phase1 directory instead of the group directory (because of relative paths).

Username: Michael
Password: 123

Functionality:

Events
    Can get events by name, returning multiple events if events have the same name.
    Can get events relative to the current date
    Can create a single new event
    Can view a single event and its associated properties (series, alerts, tags, memos).
        * This can be accessed from the EventList view. One way to reach this view is to navigate to future events.

Alerts
    Can get overdue (ringing) alerts
    Can acknowledge/dismiss these alerts
    Can create an individual alert for an event
    Can create a frequency alert for an event that rings multiple times before the date of the event

Series
    Get all events that share a series (try searching for events part of the "Parties" series)
    Create a series by combining existing events
    Create a series by using an event formula that denotes the first occurrence, frequency, and number of repetitions.

Notes (Memo/Tag)
    Memo
        Can get a list of all the user's memos
        Get events that share a memo
        Create a new memo
        Attach the memo to an event

    Note
        Get events by a tag name (try searching for events tagged with "fun")
        Tag an event

Design:
    Entities store the business entities. Entity specific logic as validation can be added to these classes later.
    Use cases contain all the different manager for different functions of the program.
        Data repository interfaces are also stored at this level.
        IEventManager is used by all other managers to be able to manipulate events for their needs
        UseCaseManager is a soft facade for all the managers
    Data access contain the serialize repository implementation for the different entities and have the functionality defined by use cases
    Controller contains the CommandLine controller that bridges the gap between the UseCaseManager and different command line implementations.
        It delivers the data so that the views are dumb classes that simply display native data types.
    View contains view manager and its various views are used for the user interface and display information delivered by controller.
        Views are dumb classes.

    CalendarAppBuilder:
        Builds the calendar app and abstracts the specific implementations of services such as the repository.
        These could be switched out of the builder at a later date.
    Main:
        Calls the builder to create the application using the current service implementations.

    Misc:
        Data classes are used to store non-business specific data classes. In this case a quintuple (5-uple).
        *.ser files are used by the serial repository implementations to store and query for the entities.
