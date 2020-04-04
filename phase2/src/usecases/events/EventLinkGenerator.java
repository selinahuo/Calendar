package usecases.events;

import entities.CalendarEvent;

import java.net.URLEncoder;

public class EventLinkGenerator {
    public static String getEventDirections(CalendarEvent event) {
        String url = "https://www.google.com/maps/dir//" + encodeURL(event.getLocation());
        return url;
    }

    public static String getEventWeather(CalendarEvent event) {
        String url = "https://www.google.com/search?q=" + encodeURL(event.getLocation() + " weather");
        return url;
    }

    public static String getEventTwitterShare(CalendarEvent event) {
        String eventString = String.format("%s @ %s",
                event.getName(),
                event.getLocation()
        );
        String url = "https://twitter.com/?status=" + encodeURL(eventString);
        return url;
    }

    public static String getEventEmailShare(CalendarEvent event) {
        String eventString = String.format("%s @ %s",
                event.getName(),
                event.getLocation()
        );
        String url = "mailto:?subject=" + encodeURL(eventString);
        return url;
    }

    private static String encodeURL(String url) {
        try {
            return URLEncoder.encode( url, "UTF-8" ).replace("+", "%20");
        } catch(Exception e) {
            return null;
        }
    }
}
