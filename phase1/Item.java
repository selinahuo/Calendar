package phase1;

import java.util.ArrayList;

public class Item {
    String name;
    ArrayList<String> events;

    public Item(String name, ArrayList<String> events){
        this.name = name;
        this.events = events;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<String> getEvents(){
        return this.events;
    }

}
