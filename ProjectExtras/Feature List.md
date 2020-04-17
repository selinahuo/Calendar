# Feature List

The purpose of this section is to describe how to access each feature through the menus

## Phase I Features
* Create and view events (past, current, future), events have a start time and end time, memo, and tags
    *  To create a new event, the user would first go to the [Events Menu] from the [Main Menu], and select [Create Event]. The user would then be prompted to enter the name, start time, end time, and location of the event. 
       The user can also choose to attach a memo to the event. The user would go to the [Note Menu], then select [Create memo]. By entering the Memo information and the event ID of the desired event, the user has attached the memo to the event. 
       While on the [Note Menu], the user can also choose to attach a tag to the event by selecting [Create Tag].
* Set individual alerts/ Set alerts for a certain frequency 
    * The user can choose to set individual alerts or a chain of alerts that notify the user under a certain frequency. Going to the [Alert Menu] from the [Main Menu], the user can select [Create a new Alert] and then choose which type of alert to attach to the event. 
      For both types of alert, the user would be prompt to enter the alert name and the eventID of the desired event. For individual alert, the user can enter a start time of the alert to ring. If the user didn’t provide a valid time, then the program would default the time to the current local time. For frequency alerts, the user would provide a start time for first ring time, then the user would decide on the frequency (hourly, daily, or weekly) of which the alert would repeat. 
* Create series by creating multiple events under the desired duration and frequency, or link individual events together
    * The user can directly create a series of events by going to the [Series Menu] from the [Main Menu], and select [Create series by combining events] from the [Create Series Menu]. From here, the user can create multiple events under the desired frequency. 
      The user can also link individual events together by selecting [Create series by combining events], from this meny, the user can choose to add multiple events one by one into the new series. 
* Find events by date, name, series name, and tag
    * How to find events by date: Go to [Event Menu] and select [5] List events using time frame (hour, day, week, month). Here the user can enter h,d,w,m to see all the events occurring in the hour, day, week, or month. The user would then proceed to enter the specific start time of the desired time frame. 
    * How to find events by name: In the same [Event Menu], the user can select [3] List events by name. By entering the event’s name, the user can view all the events that are under the entered name. 
    * How to find event’s by tag: Go to the [Note Menu] for the [Main Menu], and select [4] List all tags. From the [Tag List] menu, the user can select [1] Display all events attached to the tag. By entering the Tag ID, the user can view all the events that are listed under the desired tag.

## Phase II Features
* Multiple calendars per user: 
    * The user can create multiple calendars, which will each have their own events, memos, alerts, etc. In order to create them, the user can go to the [Calendar Menu] from the [Main Menu]. Once that is selected, he can create a new calendar, and add events to it. Since the memos, tags etc. are connected to the event, all the calendar needs to keep track of is the events that are added to it.
    * Once a calendar is created, the user can choose to edit its name, add events to it, view the events that are linked to it, or delete itself.
    * Each user contains their own calendars, which cannot be accessed by other users.
* Multiple users have the same event in their calendar and the Invitations
    * The user can create invitations, which allow them to invite other users to have the same event as them. The inviter will retain the ability to edit everything related to the event, but the invitee will be unable to edit any of its info.
    * The user accesses the [Invitations Menu] from the [Main Menu], where he can see the incoming invitation, outgoing invitations, or can invite a seperate user to an event.
* Deletion\editing memos, tags, events, alerts, calendars
    * In order to delete memos, the user must navigate to the [Note Menu] from the [Main Menu], and then select the [List All Memos] option. Then, the user must select the [Delete Memo] option and input the ID of the memo to delete. This menus also has the options to edit the name or note of a memo.
    * The process to delete a tag is extremely similar, however instead of selecting [List All Memos] from the [Note Menu], the user must select [List All Tags], and then the [Delete Tag] option, and input the ID of the tag to delete. Tags do not support editing since they only have a name, so instead a user can simply create a new one and delete the old one.
    * In order to delete an event, the user must access the [Event Menu] from the [Main Menu], and select an option which lists events, and then select the [View Individual Event] option, which will open allow the user to have access to all the functionalities for separate events. The user can then select the [Delete Event] option, which deletes the event, and disassociates tags, memos, etc. that were associated with the event. The options to edit an event’s name, time and location are also in this menu.
    * To delete an Alert, the user would go to the [Alert List Menu] from [Alert menu], and select [Delete Alert]. The user only has to enter an alert ID of the alert that wished to be deleted, then the alert would then be eliminated. The user can double check by going to the [Alert List Menu] to see all the alerts for this user. The options to edit an alert’s name or time are also in this menu.
* One specific feature to our team: Finding the directions to the location of the event, and finding the weather using the location of the event.
    * In order to share information about an event on social media, the user must enter the [Event Menu], and select an option to view events from there. Then, they must choose [View Individual Event], which will allow them to enter the single event menu, which has the options to [Get Event Directions] and [Get Event Weather]. The directions opens google maps with the location of the selected, and the weather searches the weather for the location of the event.

## Optional Features 
* Invitations
    * The user can create invitations, which allow them to invite other users to have the same event as them. The inviter will retain the ability to edit everything related to the event, but the invitee will be unable to edit any of its info.
    * The user accesses the [Invitations Menu] from the [Main Menu], where he can see the incoming invitation, outgoing invitations, or can invite a separate user to an event.
    * As part of the invitation the two users can exchange messages.
* Viewing events by day/hour/week/month
    * In order to access this functionality, the user must go to the [Event Menu] from the [Main Menu], and select the [List Events Using Time Frame] option. The user must then input information based on how he wants the events displayed, and then the events will be shown.
    * We also added the feature to schedule holiday events in the [Event Menu].
* Share events on social media
    * In order to share information about an event on social media, the user must enter the [Event Menu], and select an option to view events from there. Then, they must choose [View Individual Event], which will allow them to enter the single event menu, which has the options to [Share Event On Twitter] or [Share event using Email].