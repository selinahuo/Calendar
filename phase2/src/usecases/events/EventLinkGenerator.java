package usecases.events;

import entities.CalendarEvent;

import java.net.URLEncoder;

public class EventLinkGenerator {
    public static String getEventDirections(CalendarEvent event) {
        String url = "https://www.google.com/maps/dir//" + event.getLocation();
        return encodeURL(url);
    }

    public static String getEventWeather(CalendarEvent event) {
        String url = "https://www.google.com/search?q=" + event.getLocation() + " weather";
        return encodeURL(url);
    }

    public static String getEventTwitterShare(CalendarEvent event) {
        String eventString = String.format("%s @ %s from %s to %s.",
                event.getName(),
                event.getLocation(),
                event.getStart().toString(),
                event.getEnd().toString()
        );
        String url = "https://twitter.com/?status=" + eventString;
        return encodeURL(url);
    }

    public static String getEventEmailShare(CalendarEvent event) {
        String eventString = String.format("%s @ %s from %s to %s.",
                event.getName(),
                event.getLocation(),
                event.getStart().toString(),
                event.getEnd().toString()
        );
        String url = "mailto:?subject=" + eventString;
        return encodeURL(url);
    }

    private static String encodeURL(String url) {
        try {
            return URLEncoder.encode( url, "UTF-8" );
        } catch(Exception e) {
            return null;
        }
    }
}
