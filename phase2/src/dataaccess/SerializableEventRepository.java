package dataaccess;

import entities.CalendarEvent;
import usecases.events.IEventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

class SerializableEventRepository extends SerializableRepository<CalendarEvent> implements IEventRepository {
    SerializableEventRepository() { super("./events.ser"); }

    private class SortAscendingDate implements Comparator<CalendarEvent> {
        @Override
        public int compare(CalendarEvent o1, CalendarEvent o2) {
            return o1.getStart().compareTo(o2.getStart());
        }
    }

    private ArrayList<CalendarEvent> fetchSortedPlural(IFilter<CalendarEvent> filter) {
        ArrayList<CalendarEvent> events = fetchPlural(filter);
        events.sort(new SortAscendingDate());
        return events;
    }

    @Override
    public void saveEvent(CalendarEvent event) {
        saveItem(event);
    }

    @Override
    public CalendarEvent fetchEventByEventID(String eventID) {
        return fetchSingular((CalendarEvent e) -> e.getEventID().equals(eventID));
    }

    @Override
    public CalendarEvent fetchEventByEventIDAndOwnerID(String eventID, String ownerID) {
        return fetchSingular((CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID));
    }

    @Override
    public CalendarEvent fetchEventByEventIDAndUserID(String eventID, String userID) {
        return fetchSingular(
                (CalendarEvent e) ->
                        e.getEventID().equals(eventID)
                        && (e.getUserID().equals(userID) || e.getCollaborators().contains(userID))
        );
    }

    @Override
    public CalendarEvent fetchEventByAlertIDAndOwnerID(String alertID, String ownerID) {
        return fetchSingular(
                (CalendarEvent e) -> e.getAlertID().equals(alertID) && e.getUserID().equals(ownerID)
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByNameAndUserID(String name, String userID) {
        return fetchSortedPlural(
                (CalendarEvent e) ->
                        e.getName().equals(name)
                        && (e.getUserID().equals(userID) || e.getCollaborators().contains(userID))
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartBeforeAndUserID(LocalDateTime start, String userID) {
        return fetchSortedPlural(
                (CalendarEvent e) ->
                        e.getStart().isBefore(start)
                        && (e.getUserID().equals(userID) || e.getCollaborators().contains(userID))
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartBeforeAndEndAfterAndUserID(LocalDateTime start, LocalDateTime end,
                                                                               String userID) {
        return fetchSortedPlural(
                (CalendarEvent e) ->
                        e.getStart().isBefore(start)
                        && e.getEnd().isAfter(end)
                        && (e.getUserID().equals(userID) || e.getCollaborators().contains(userID))
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartAfterAndUserID(LocalDateTime after, String userID) {
        return fetchSortedPlural(
                (CalendarEvent e) ->
                        e.getStart().isAfter(after)
                        && (e.getUserID().equals(userID) || e.getCollaborators().contains(userID))
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsStartBeforeAndStartAfterAndUserID(LocalDateTime before,
                                                                                 LocalDateTime after, String userID) {
        return fetchSortedPlural(
                (CalendarEvent e) ->
                        e.getStart().isBefore(before)
                        && e.getStart().isAfter(after)
                        && (e.getUserID().equals(userID) || e.getCollaborators().contains(userID))
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByAlertIDAndOwnerID(String alertID, String ownerID) {
        return fetchSortedPlural(
                (CalendarEvent e) ->
                        e.getAlertID().equals(alertID)
                        && e.getUserID().equals(ownerID)
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByCalendarIDAndOwnerID(String calendarID, String ownerID) {
        return fetchSortedPlural(
                (CalendarEvent e) -> e.getCalendarID().equals(calendarID) && e.getUserID().equals(ownerID)
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByTagIDAndOwnerID(String tagID, String ownerID) {
        return fetchSortedPlural(
                (CalendarEvent e) -> e.getTagIDs().contains(tagID) && e.getUserID().equals(ownerID)
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsByMemoIDAndOwnerID(String memoID, String ownerID) {
        return fetchSortedPlural(
                (CalendarEvent e) -> e.getMemoID().equals(memoID) && e.getUserID().equals(ownerID)
        );
    }

    @Override
    public ArrayList<CalendarEvent> fetchEventsBySeriesIDAndOwnerID(String seriesID, String ownerID) {
        return fetchSortedPlural(
                (CalendarEvent e) -> e.getSeriesID().equals(seriesID) && e.getUserID().equals(ownerID)
        );
    }

    @Override
    public boolean editEventName(String eventID, String name, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setName(name)
        );
    }

    @Override
    public boolean editEventStart(String eventID, LocalDateTime start, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setStart(start)
        );
    }

    @Override
    public boolean editEventEnd(String eventID, LocalDateTime end, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setEnd(end)
        );
    }


    @Override
    public boolean editEventLocation(String eventID, String location, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setLocation(location)
        );
    }


    @Override
    public boolean editEventCalendarID(String eventID, String calendarID, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setCalendarID(calendarID)
        );
    }


    @Override
    public boolean editTagIDs(String eventID, ArrayList<String> tagIDs, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setTagIDs(tagIDs)
        );
    }


    @Override
    public boolean editMemoID(String eventID, String memoID, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setMemoID(memoID)
        );
    }


    @Override
    public boolean editSeriesID(String eventID, String seriesID, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setSeriesID(seriesID)
        );
    }


    @Override
    public boolean editAlertID(String eventID, String alertID, String ownerID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID),
                (CalendarEvent e) -> e.setAlertID(alertID)
        );
    }

    @Override
    public boolean addCollaborator(String eventID, String collaboratorID) {
        return editSingular(
                (CalendarEvent e) -> e.getEventID().equals(eventID) && !e.getCollaborators().contains(collaboratorID),
                (CalendarEvent e) -> e.addCollaborator(collaboratorID)
        );
    }

    @Override
    public boolean deleteEvent(String eventID, String ownerID) {
        return deleteSingular((CalendarEvent e) -> e.getEventID().equals(eventID) && e.getUserID().equals(ownerID));
    }
}
