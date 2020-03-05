package entities;

import java.util.ArrayList;

public class Memo{
    String note;
    String name;
    ArrayList<String> events;
    String id;


    public Memo(String name, ArrayList<String> events, String note, String id) {
        this.name = name;
        this.events = events;
        this.note = note;
        this.id = id;
    }
}
