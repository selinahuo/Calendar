package entities;

import java.util.ArrayList;

public class Memo extends Item{
    String note;

    public Memo(String name, ArrayList<String> events, String note) {
        super(name, events);
        this.note = note;
    }
}
