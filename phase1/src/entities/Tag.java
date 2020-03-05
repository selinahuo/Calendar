package entities;

import java.util.ArrayList;

public class Tag{
    String name;
    ArrayList<String> events;
    String id;

    public Tag(String name, ArrayList<String> events, String id){
        this.name = name;
        this.id =id;
    }
}
